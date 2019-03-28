package utcn.calc.bank.model;
/**
 * 
 * @invariant isWellFormed
 *
 */

public interface BankProc {
	/**
	*@pre : Person p is not currently in the bank and isWellFormed  
	*@post : A new client has been added (size has increased)
	*/
	public void addPerson(Person p);
	/**
	*@pre : Person p is currently in the bank and isWellFormed  
	*@post : The client has been removed (size has decreased)
	*/
	public void removePerson(Person p);
	
	public int addAccount(Person p, Account a);
	public int removeAccount(Person p, Account a);
	public void deposit(Person p, Account a, double amount);
	
	public void writeToFile();
	public void readFromFile();
}
