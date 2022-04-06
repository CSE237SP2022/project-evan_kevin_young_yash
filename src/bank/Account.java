package bank;

import java.util.Random;

public class Account {
	private String accountType;
	private String accountNumber;
	private String user;
	private double balance;
	
	public static String generateAccountNumber() {
		Random random = new Random();
		char[] digits = new char[8];
		for (int i = 0; i < 8; i++) {
			digits[i] = (char) (random.nextInt(10) + '0');
		}
		return new String(digits);
	}  
	
	public static boolean checkType(String type) {
		if(!(type.equalsIgnoreCase("saving")||type.equalsIgnoreCase("checking"))) {
			return false;
		}
		return true;
	}
	public static String updateType(String type) {
		if(type.equalsIgnoreCase("saving")){
			String s="saving";
			return s;
			}
		else if(type.equalsIgnoreCase("checking")) {
			String s="checking";
			return s;
		}
		return null;
			
	}
	public Account(String name, String type) {
		this.user=name;
		this.balance=0;
		this.accountNumber=generateAccountNumber();
		if(updateType(type)==null) {
			throw new IllegalArgumentException("invalid account type: can only be saving or checking");
		}
		this.accountType=updateType(type);
	}
	
	public Account(String name,String type, long balance) {
		this.user=name;
		this.balance=balance;
		this.accountNumber=generateAccountNumber();
		if(updateType(type)==null) {
			throw new IllegalArgumentException("invalid account type: can only be saving or checking");
		}
		this.accountType=updateType(type);
		
	}
	
	public String getAccountNumber() {
		return this.accountNumber;
	}
	
	public String getUser() {
		return this.user;
	}
	
	public double getBalance() {
		return this.balance;
	}
	
	public String getAccountType() {
		return this.accountType;
	}
	

	public static void main(String[] args) {
		// -generated method stub
	}

}
