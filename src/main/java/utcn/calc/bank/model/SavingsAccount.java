package utcn.calc.bank.model;

public class SavingsAccount extends Account{
	
	public SavingsAccount(int iban,double amount) {
		super.setIban(iban);
		super.setAmount(amount);
		super.setType(this.getClass().getSimpleName());
	}
	
	public int withdraw(double amount) {
		double sumToWithdraw = amount+amount*0.2;
		if(super.getAmount()>=sumToWithdraw) {
			super.withdraw(sumToWithdraw);
			return 1;
		}
		else
			return -1;
	}
	
	
	public String toString() {
		return "*Savings Account* IBAN: "+super.getIban()+" Current ammount: "+super.getAmount()+" RON\n";
	}
}
