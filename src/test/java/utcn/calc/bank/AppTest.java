package utcn.calc.bank;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import utcn.calc.bank.model.Account;
import utcn.calc.bank.model.Bank;
import utcn.calc.bank.model.Person;
import utcn.calc.bank.model.SavingsAccount;

public class AppTest {
    
	@Test
    public void testDeposit()
    {
        Bank bank = new Bank();
        bank.readFromFile();
        Person p1 = new Person("1770823987654","Andrei",40,"Republicii 6");
        bank.addPerson(p1);
        Account a1 = new SavingsAccount(12,34.7);
        bank.addAccount(p1, a1);
        a1.deposit(65.0);
        //34.7 + 65 = 99.7
        assertEquals("test deposit",a1.getAmount(),99.7,0.01);
    }
	
	@Test
    public void testWithdraw()
    {
        Bank bank = new Bank();
        bank.readFromFile();
        Person p1 = new Person("2840823987654","Andreea",33,"Republicii 6A");
        bank.addPerson(p1);
        Account a1 = new SavingsAccount(1234,100.0);
        bank.addAccount(p1, a1);
        a1.withdraw(10.0);
        
        //100.0 - (10.0 + 10.0*0.2) = 100.0 - 12.0 = 88.0 
        
        assertEquals("test deposit",a1.getAmount(),88.0,0.01);
    }
}
