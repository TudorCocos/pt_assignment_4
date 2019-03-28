package utcn.calc.bank.model;

import java.io.Serializable;

public class Account implements Serializable{

	private int iban;
	private String type;
	private double amount;
	
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public int getIban() {
		return iban;
	}

	public void setIban(int iban) {
		this.iban = iban;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void deposit(double amount) {
		this.amount+=amount;
	}
	
	public int withdraw(double amount) {
		if(this.amount>=amount) {
			this.amount-=amount;
			return 1;
		}
		else
			return -1;
	}
}
