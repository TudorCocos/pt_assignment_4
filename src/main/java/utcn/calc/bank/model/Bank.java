package utcn.calc.bank.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Bank implements BankProc, Serializable{

	private HashMap<Person,Set<Account>> persons;
	public Bank() {
		persons= new HashMap<Person,Set<Account>>();
	}
	public HashMap<Person, Set<Account>> getPersons() {
		return persons;
	}
	public void setPersons(HashMap<Person, Set<Account>> persons) {
		this.persons = persons;
	}
	
	public boolean isWellFormed() {
		return !persons.isEmpty();
	}
	
	public void addPerson(Person p) {
		assert !persons.containsKey(p);
		assert isWellFormed();
		int oldSize = persons.size();
		persons.put(p,new HashSet<Account>());
		assert oldSize+1==persons.size();
	}
	
	
	public void removePerson(Person p) {
		assert persons.containsKey(p);
		assert isWellFormed();
		int oldSize = persons.size();
		persons.remove(p);
		assert oldSize-1==persons.size();
	}
	
	
	public int addAccount(Person p, Account a) {
		for(Account acc: persons.get(p))
			if(acc.getIban()==a.getIban())
				return -1;
		persons.get(p).add(a);
		return 1;
	}
	
	
	public int removeAccount(Person p, Account a) {
		for(Account acc: persons.get(p))
			if(acc.getIban()==a.getIban()) {
				persons.get(p).remove(a);
				return 1;
			}
		return -1;
	}
	public void deposit(Person p, Account a, double amount) {
		if(persons.get(p).contains(a)) {
			Set<Account> accounts = persons.get(p);
			for(Account acc: accounts) {
				if(acc.equals(a)) {
					acc.deposit(amount);
					break;
				}
			}
		}
	}
	
	public void writeToFile() {
		assert isWellFormed();
		System.out.println("Writing objects...");
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("data.ser"))) {
            os.writeObject(persons);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void readFromFile() {
		System.out.println("Reading objects...");
        try (ObjectInputStream os = new ObjectInputStream(new FileInputStream("data.ser"))) {
            persons = (HashMap<Person,Set<Account>>)os.readObject();
            //System.out.println(persons);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
	}
	
	public static void main() {
		Bank b1 = new Bank();
		Person p1 = new Person("1970908123456", "Tudor", 20, "Alexandru Roman nr 5");
		Person p2 = new Person("1970908123456", "Toader", 20, "Alexandru Roman nr 5");
		Account a1 = new SpendingAccount(123);
		Account a3 = new SpendingAccount(1);
		a3.deposit(10.3);
		Account a2 = new SavingsAccount(3173,5.7);
		b1.addPerson(p1);
		System.out.println(b1.persons);
		b1.addAccount(p1,a1);
		System.out.println(b1.persons);
		b1.addAccount(p1,a2);
		System.out.println(b1.persons);
		b1.addAccount(p2,a3);
		System.out.println(b1.persons);
		b1.writeToFile();
		b1.readFromFile();
	}
}
