package utcn.calc.bank.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.Set;

import utcn.calc.bank.model.Account;
import utcn.calc.bank.model.Bank;
import utcn.calc.bank.model.Person;
import utcn.calc.bank.model.SavingsAccount;
import utcn.calc.bank.model.SpendingAccount;

public class Controller {

	private Bank bank;
	private Gui frame;
	private String[] clientsString= {"CNP","Name","Age","Address"}, accountsString= {"IBAN","Type","Amount"};
	private boolean isInteger(String input){	// long is used because CNP in integer value overflows
	   try{
		  Long.parseLong(input);
	      return true;
	   }
	   catch( Exception e){
	      return false;
	   }
	}
	private boolean isDouble(String input){
		   try{
			  Double.parseDouble(input);
		      return true;
		   }
		   catch( Exception e){
		      return false;
		   }
		}
	private boolean clientFieldCheck() {	// Name, Age and Address
		if(frame.nameField().getText().equals("Name") || frame.nameField().getText().equals("")){
			frame.messageArea().setText("Name Field is EMPTY!");
			return false;
		}
		if(frame.ageField().getText().equals("Age") || frame.ageField().getText().equals("")){
			frame.messageArea().setText("Age Field is EMPTY!");
			return false;
		}
		if(Integer.parseInt(frame.ageField().getText())<18) {
			frame.messageArea().setText("Minors are not allowed to open an account!");
			return false;
		}
		if(!isInteger(frame.ageField().getText())) {
			frame.messageArea().setText(frame.ageField().getText()+" is not a valid integer value for age!");
			return false;
		}
		if(frame.addressField().getText().equals("Address") || frame.addressField().getText().equals("")){
			frame.messageArea().setText("Address Field is EMPTY!");
			return false;
		}
		return true;
	}
	
	private boolean cnpCheck() {
		if(frame.cnpField().getText().equals("CNP") || frame.cnpField().getText().equals("")){
			frame.messageArea().setText("CNP Field is EMPTY!");
			return false;
		}
		if(frame.cnpField().getText().length()!=13 || !isInteger(frame.cnpField().getText())) {
			frame.messageArea().setText("CNP inputted is INVALID!");
			return false;
		}
		return true;
	}
	
	private boolean ibanCheck() {
		if(frame.ibanField().getText().equals("IBAN") || frame.ibanField().getText().equals("")) {
			frame.messageArea().setText("IBAN Field is EMPTY!");
			return false;
		}
		if(!isInteger(frame.ibanField().getText())) {
			frame.messageArea().setText("IBAN code is made of digits!");
			return false;
		}
		return true;
	}
	private boolean amountCheck() {
		if(frame.amountField().getText().equals("Amount") || frame.amountField().getText().equals("")) {
			frame.messageArea().setText("Amount Field is EMPTY!");
			return false;
		}
		if(!isDouble(frame.amountField().getText())) {
			frame.messageArea().setText("Amount must be made of digits!");
			return false;
		}
		if(Double.parseDouble(frame.amountField().getText())<=0.0) {
			frame.messageArea().setText("Amount must be a positive value!");
			return false;
		}
		return true;
	}
	
	private Object[][] makeMatrix(Set<?> pl){
		Object[][] data = new Object[pl.size()][4];
		int line=0,column=0;
		for(Object o: pl) {
			Field[] fields;
			if(o.getClass()==Person.class) {
				fields = o.getClass().getDeclaredFields();
			}
			else {
				fields = o.getClass().getSuperclass().getDeclaredFields();
			}
			column=0;
			for(Field f : fields) {
				f.setAccessible(true);
				Object value;
				try {
					value = f.get(o);
					data[line][column]=value;
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				column++;
			}
			line++;
		}
		return data;
	}
	
	public Controller() {
		bank = new Bank();
		bank.readFromFile();
		frame = new Gui();
		frame.setVisible(true);
		frame.printClients(clientsString, makeMatrix(bank.getPersons().keySet()));
		frame.addClientBtn().addActionListener(new ActionListener(){
			

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(cnpCheck()==true && clientFieldCheck()==true) {
					frame.messageArea().setText("");
					String cnp = frame.cnpField().getText();
					String name = frame.nameField().getText();
					int age = Integer.parseInt(frame.ageField().getText());
					String address = frame.addressField().getText();
					Person pers = new Person(cnp,name,age,address);
					bank.addPerson(pers);
					bank.writeToFile();
					bank.readFromFile();
					frame.printClients(clientsString, makeMatrix(bank.getPersons().keySet()));
				}
			}
		});
		
		
		frame.removeClientBtn().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(cnpCheck()==true && clientFieldCheck()==true) {
					frame.messageArea().setText("");
					String cnp = frame.cnpField().getText();
					String name = frame.nameField().getText();
					int age = Integer.parseInt(frame.ageField().getText());
					String address = frame.addressField().getText();
					Person pers = new Person(cnp,name,age,address);
					bank.removePerson(pers);
					bank.writeToFile();
					bank.readFromFile();
					frame.printClients(clientsString, makeMatrix(bank.getPersons().keySet()));
				}
			}
		});
		frame.editClientBtn().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(cnpCheck()==true && clientFieldCheck()==true) {
					frame.messageArea().setText("");
					String cnp = frame.cnpField().getText();
					String name = frame.nameField().getText();
					int age = Integer.parseInt(frame.ageField().getText());
					String address = frame.addressField().getText();
					for(Person p:bank.getPersons().keySet())
						if(p.getCnp().equals(cnp)) {
							p.setAge(age);
							p.setName(name);
							p.setAddress(address);
						}
					bank.writeToFile();
					bank.readFromFile();
					frame.printClients(clientsString, makeMatrix(bank.getPersons().keySet()));
				}
			}
		});
		frame.refreshClientsBtn().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.printClients(clientsString, makeMatrix(bank.getPersons().keySet()));
			}
		});
		frame.refreshAccountsBtn().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(cnpCheck()==true) {
					String cnp = frame.cnpField().getText();
					for(Person p: bank.getPersons().keySet())
						if(p.getCnp().equals(cnp))
							frame.printAccounts(accountsString, makeMatrix(bank.getPersons().get(p)));
				}
			}
		});
		frame.depositBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cnpCheck()==true && amountCheck()==true && ibanCheck()==true) {
					String cnp = frame.cnpField().getText();
					Double amount = Double.parseDouble(frame.amountField().getText());
					int iban = Integer.parseInt(frame.ibanField().getText());
					for(Person p: bank.getPersons().keySet())
						if(p.getCnp().equals(cnp))
							for(Account a: bank.getPersons().get(p))
								if(a.getIban()==iban)
									a.deposit(amount);
					bank.writeToFile();
					bank.readFromFile();
				}
			}
		});
		frame.withdrawBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cnpCheck()==true && amountCheck()==true && ibanCheck()==true) {
					String cnp = frame.cnpField().getText();
					Double amount = Double.parseDouble(frame.amountField().getText());
					int iban = Integer.parseInt(frame.ibanField().getText());
					for(Person p: bank.getPersons().keySet())
						if(p.getCnp().equals(cnp))
							for(Account a: bank.getPersons().get(p))
								if(a.getIban()==iban)
									a.withdraw(amount);
					bank.writeToFile();
					bank.readFromFile();
				}
			}
		});
		frame.addAccountBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cnpCheck()==true && amountCheck()==true && ibanCheck()==true) {
					String cnp = frame.cnpField().getText();
					Double amount = Double.parseDouble(frame.amountField().getText());
					int iban = Integer.parseInt(frame.ibanField().getText());
					if(frame.isSpending()==false && frame.isSavings()==false)
						frame.messageArea().setText("You need to pick an account type by using the radio buttons!");
					else
						if(frame.isSavings()==true && frame.isSpending()==true)
							frame.messageArea().setText("Can't have both radio buttons selected at the same time!");
						else
							if(frame.isSavings()==true) {
								SavingsAccount a = new SavingsAccount(iban,amount);
								for(Person p : bank.getPersons().keySet())
									if(p.getCnp().equals(cnp))
										if(bank.addAccount(p, a)==-1)
											frame.messageArea().setText("An account with the same IBAN already exists!");
							}
							else
								if(frame.isSpending()==true) {
									SpendingAccount a = new SpendingAccount(iban);
									for(Person p : bank.getPersons().keySet())
										if(p.getCnp().equals(cnp))
											if(bank.addAccount(p, a)==-1)
												frame.messageArea().setText("An account with the same IBAN already exists!");
								}
					bank.writeToFile();
					bank.readFromFile();
				}
			}
		});
		frame.removeAccountBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cnpCheck()==true && ibanCheck()==true) {
					String cnp = frame.cnpField().getText();
					int iban = Integer.parseInt(frame.ibanField().getText());
					for(Person p : bank.getPersons().keySet())
						if(p.getCnp().equals(cnp))
							for(Account a: bank.getPersons().get(p))
								if(a.getIban()==iban)
									if(bank.removeAccount(p, a)==-1)
										frame.messageArea().setText("No account with the given IBAN exists!");
					bank.writeToFile();
					bank.readFromFile();
				}
			}
		});
		frame.editAccountBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cnpCheck()==true && ibanCheck()==true &&amountCheck()==true) {
					String cnp = frame.cnpField().getText();
					int iban = Integer.parseInt(frame.ibanField().getText());
					for(Person p : bank.getPersons().keySet())
						if(p.getCnp().equals(cnp))
							for(Account a: bank.getPersons().get(p))
								if(a.getIban()==iban)
									a.setAmount(Double.parseDouble(frame.amountField().getText()));
					bank.writeToFile();
					bank.readFromFile();
				}
			}
		});
	}
}
