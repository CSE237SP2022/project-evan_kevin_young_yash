package bank;
import java.util.LinkedList;

import bank.Account;

public class User {
	public String name;
	private String password;
	private LinkedList<Account> accounts;
	
	public User(String name, String password) {
		if(name=="") {
			throw new IllegalArgumentException("name cannot be empty");
		}
		if(password.length()<8) {
			throw new IllegalArgumentException("Password has to be at least 8 characters long");
		}
		this.name=name;
		this.password=password;
		this.accounts=new LinkedList<Account>();
	}
	
	public Account openAccount(String type) {
		Account account=new Account(this.name,type);
		this.accounts.add(account);
		return account;
	}
	
	public Account openAccount(String type, int balance) {
		Account account=new Account(this.name,type,balance);
		this.accounts.add(account);
		return account;
	}
	
	public LinkedList<Account> getAccounts(){
		return this.accounts;
	}
	
	public Account getSingleAccount(String accountNumber) {
		for (Account account : this.accounts) {
			if (account.getAccountNumber().equals(accountNumber)) {
				return account;
			}
		} return null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
