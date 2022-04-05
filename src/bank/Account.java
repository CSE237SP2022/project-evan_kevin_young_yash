package bank;

import java.util.Random;

public class Account {
	private String accountType;
	private String accountNumber;
	private String user;
	private long balance;
	
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
		else {
			throw new IllegalArgumentException("invalid account type, can only be saving and checking");
		}
			
	}
	public Account(String name, String type) {
		if(checkType(type)) {
			this.user=name;
			this.balance=0;
			this.accountNumber=generateAccountNumber();
			this.accountType=updateType(type);
		}
		else {
			throw new IllegalArgumentException("invalid account type, can only be saving and checking");
		}
	}
	
	public Account(String name,String type, long balance) {
		if(checkType(type)) {
			this.user=name;
			this.balance=balance;
			this.accountNumber=generateAccountNumber();
			this.accountType=updateType(type);
		}
		else {
			throw new IllegalArgumentException("invalid account type, can only be saving and checking");
		}
		
	}
	
	public String getAccountNumber() {
		return this.accountNumber;
	}
	
	public String getUser() {
		return this.user;
	}
	
	public long getBalance() {
		return this.balance;
	}
	
	public String getAccountType() {
		return this.accountType;
	}
	

	public static void main(String[] args) {
		// -generated method stub
		Account accountOne=new Account("Evan","checking");
		System.out.print(String.valueOf(accountOne.getAccountNumber()));

	}

}
