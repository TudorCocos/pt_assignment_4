package utcn.calc.bank.model;

public class SpendingAccount extends Account{
	
	public SpendingAccount(int iban) {
		super.setIban(iban);
		super.setAmount(0.0);
		super.setType(this.getClass().getSimpleName());
	}
	
	public String toString() {
		return "*Spending Account* IBAN: "+super.getIban()+" Current ammount: "+super.getAmount()+" RON\n";
	}
}
