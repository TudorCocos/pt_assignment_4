package utcn.calc.bank.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Gui extends JFrame {

	private JPanel contentPane;
	private JTextField txtCnp;
	private JTextField txtName;
	private JTextField txtAge;
	private JTextField txtAddress;
	private JTextField txtIban;
	private JTextField txtAmount;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	
	private JTextArea textArea;
	private JButton btnAddClient;
	private JButton btnRemoveClient;
	private JButton btnAddAccount;
	private JButton btnRemoveAccount;
	private JRadioButton rdBtnSpending;
	private JRadioButton rdBtnSavings;
	private JButton btnDeposit;
	private JButton btnWithdraw;
	private JButton btnRefreshClients;
	private JButton btnRefreshAccounts;
	private JButton btnEditClient;
	private JButton btnEditAccount;

	public JTextField cnpField() {
		return txtCnp;
	}
	public JTextField nameField() {
		return txtName;
	}
	public JTextField ageField() {
		return txtAge;
	}
	public JTextField addressField() {
		return txtAddress;
	}
	public JTextField ibanField() {
		return txtIban;
	}
	public JTextField amountField() {
		return txtAmount;
	}
	public JTextArea messageArea() {
		return textArea;
	}
	public JButton addClientBtn() {
		return btnAddClient;
	}
	public JButton removeClientBtn() {
		return btnRemoveClient;
	}
	public JButton addAccountBtn() {
		return btnAddAccount;
	}
	public JButton removeAccountBtn() {
		return btnRemoveAccount;
	}
	public JButton depositBtn() {
		return btnDeposit;
	}
	public JButton withdrawBtn() {
		return btnWithdraw;
	}
	public JButton editClientBtn() {
		return btnEditClient;
	}
	public JButton editAccountBtn() {
		return btnEditAccount;
	}
	public JButton refreshClientsBtn() {
		return btnRefreshClients;
	}
	public JButton refreshAccountsBtn() {
		return btnRefreshAccounts;
	}
	public boolean isSpending() {
		return rdBtnSpending.isSelected();
	}
	public boolean isSavings() {
		return rdBtnSavings.isSelected();
	}
	public void printClients(String[] clientsString, Object[][] clientsMatrix) {
		JTable clientsTable = new JTable(clientsMatrix,clientsString);
		clientsTable.setVisible(true);
		scrollPane_1 = new JScrollPane(clientsTable);
		scrollPane_1.setBounds(30, 30, 650, 230);
		contentPane.add(scrollPane_1);
		scrollPane_1.setVisible(true);
	}
	public void printAccounts(String[] accountString, Object[][] accountMatrix) {
		JTable accountsTable = new JTable(accountMatrix,accountString);
		scrollPane_2 = new JScrollPane(accountsTable);
		scrollPane_2.setBounds(30, 310, 650, 230);
		contentPane.add(scrollPane_2);
	}
	public Gui() {
		setTitle("BANK");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setBackground(SystemColor.control);
		textArea.setBounds(30, 580, 650, 130);
		contentPane.add(textArea);
		
		JLabel lblClients = new JLabel("Clients");
		lblClients.setBounds(30, 10, 150, 20);
		contentPane.add(lblClients);
		
		JLabel lblAccounts = new JLabel("Accounts");
		lblAccounts.setBounds(30, 290, 150, 20);
		contentPane.add(lblAccounts);
		
		txtCnp = new JTextField();
		txtCnp.setHorizontalAlignment(SwingConstants.CENTER);
		txtCnp.setText("CNP");
		txtCnp.setBounds(720, 30, 120, 30);
		contentPane.add(txtCnp);
		txtCnp.setColumns(10);
		
		txtName = new JTextField();
		txtName.setText("Name");
		txtName.setHorizontalAlignment(SwingConstants.CENTER);
		txtName.setColumns(10);
		txtName.setBounds(870, 30, 120, 30);
		contentPane.add(txtName);
		
		txtAge = new JTextField();
		txtAge.setText("Age");
		txtAge.setHorizontalAlignment(SwingConstants.CENTER);
		txtAge.setColumns(10);
		txtAge.setBounds(720, 70, 120, 30);
		contentPane.add(txtAge);
		
		txtAddress = new JTextField();
		txtAddress.setText("Address");
		txtAddress.setHorizontalAlignment(SwingConstants.CENTER);
		txtAddress.setColumns(10);
		txtAddress.setBounds(870, 70, 120, 30);
		contentPane.add(txtAddress);
		
		btnAddClient = new JButton("Add Client");
		btnAddClient.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnAddClient.setBounds(720, 110, 120, 30);
		contentPane.add(btnAddClient);
		
		btnRemoveClient = new JButton("Remove Client");
		btnRemoveClient.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnRemoveClient.setBounds(870, 111, 120, 30);
		contentPane.add(btnRemoveClient);
		
		txtIban = new JTextField();
		txtIban.setText("IBAN");
		txtIban.setHorizontalAlignment(SwingConstants.CENTER);
		txtIban.setColumns(10);
		txtIban.setBounds(720, 310, 120, 30);
		contentPane.add(txtIban);
		
		txtAmount = new JTextField();
		txtAmount.setText("Amount");
		txtAmount.setHorizontalAlignment(SwingConstants.CENTER);
		txtAmount.setColumns(10);
		txtAmount.setBounds(870, 310, 120, 30);
		contentPane.add(txtAmount);
		
		btnAddAccount = new JButton("Add Account");
		btnAddAccount.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnAddAccount.setBounds(720, 410, 120, 30);
		contentPane.add(btnAddAccount);
		
		btnRemoveAccount = new JButton("Remove Account");
		btnRemoveAccount.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnRemoveAccount.setBounds(870, 410, 120, 30);
		contentPane.add(btnRemoveAccount);
		
		rdBtnSpending = new JRadioButton("Spending Account");
		rdBtnSpending.setFont(new Font("Tahoma", Font.PLAIN, 10));
		rdBtnSpending.setBounds(720, 360, 120, 30);
		contentPane.add(rdBtnSpending);
		
		rdBtnSavings = new JRadioButton("Savings Account");
		rdBtnSavings.setFont(new Font("Tahoma", Font.PLAIN, 10));
		rdBtnSavings.setBounds(870, 360, 120, 30);
		contentPane.add(rdBtnSavings);
		
		btnDeposit = new JButton("Deposit ");
		btnDeposit.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnDeposit.setBounds(720, 505, 120, 30);
		contentPane.add(btnDeposit);
		
		btnWithdraw = new JButton("Withdraw");
		btnWithdraw.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnWithdraw.setBounds(870, 505, 120, 30);
		contentPane.add(btnWithdraw);
		
		btnRefreshClients = new JButton("Refresh Clients");
		btnRefreshClients.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnRefreshClients.setBounds(720, 190, 120, 30);
		contentPane.add(btnRefreshClients);
		
		btnRefreshAccounts = new JButton("Refresh Accounts");
		btnRefreshAccounts.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnRefreshAccounts.setBounds(870, 190, 120, 30);
		contentPane.add(btnRefreshAccounts);
		
		btnEditClient = new JButton("Edit Client");
		btnEditClient.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnEditClient.setBounds(720, 150, 120, 30);
		contentPane.add(btnEditClient);
		
		btnEditAccount = new JButton("Edit Account");
		btnEditAccount.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnEditAccount.setBounds(720, 455, 120, 30);
		contentPane.add(btnEditAccount);
	}
	
	public static void main (String[] args) {	
	}
}
