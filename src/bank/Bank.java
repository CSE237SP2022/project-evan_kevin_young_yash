package bank;
import java.util.LinkedList;
import java.util.Scanner;
import bank.Account;
import bank.User;

public class Bank {
	
	public String name;
	private LinkedList<User> users;
	
	
	public Bank(String name) {
		this.name=name;
		this.users=new LinkedList<User>();
		
	}
	
	public boolean checkUsernameExist(String username) {
		for (User user : this.users) {
			if(user.getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}
	
	public User setUpUser(Scanner input) {
		while (true) {
		System.out.println("What is your username?");
		String username=input.next();
		if(checkUsernameExist(username)) {
			System.out.println("This username already exists, please choose another one!");
			continue;
		}
		System.out.println("What's your password?");
		String password1=input.next();
		System.out.println("Please retype your password");
		String password2=input.next();
		if(!password1.equals(password2)) {
			System.out.println("Passwords do not match, try again");
			continue;
		}
		try {
		      new User(username,password1);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			continue;
		}
		User user=new User(username, password1);
		System.out.println("You have successfully create your profile");
		users.add(user);
		return user;
		}
	}
	
	public int logIntoAccount(String username, String password) {
		for (User user : this.users) {
			if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
				return this.users.indexOf(user);
			}
		}
		return -1;
	}
	
	public User getUser(Scanner input, String username) {
		System.out.println("Enter your password");
		String password = input.next();
		int accountIndex = this.logIntoAccount(username, password);
		while (accountIndex < 0) {
			System.out.println("Wrong username/password combination! Try Again!");
			username = input.next();
			password = input.next();
			accountIndex = this.logIntoAccount(username, password);
		}
		return this.users.get(accountIndex);
	}
	
	public Account openAccountForUser(Scanner input, User user) {
		while(true) {
		System.out.println("What's your desired account type");
		String type=input.next();
		System.out.println("Do you want to put in any money now? Please type in Yes or No");
		String yesOrNo=input.next();
		if(yesOrNo.equalsIgnoreCase("Yes")) {
			System.out.println("How much money do you want to put in? Please type in a number");
			String balance=input.next();
			try {
			    Integer.parseInt(balance);
			  } catch (NumberFormatException e) {
			    System.out.println("Balance has to be a number");
			    continue;
			 }
			try {
			   user.openAccount(type, Integer.parseInt(balance));
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
			Account account=user.openAccount(type, Integer.parseInt(balance));
			return account;
		}
		else if (yesOrNo.equalsIgnoreCase("no")){
			try {
				   user.openAccount(type);
				}
				catch(Exception e) {
					System.out.println(e.getMessage());
					continue;
				}
				Account account=user.openAccount(type);
				return account;
		}
		else {
			System.out.println("Please type yes or no for this question");
			continue;
		}
		}
	}
	
	public void accountBalance(Scanner input, Bank bank, User user) {
		System.out.println("Please put in your account number");
		String accountNumber = input.next();
		try {
		Account accountOfUser = bank.getUser(input, user.getUsername()).getSingleAccount(accountNumber);
		System.out.println("Your balance is: $" + accountOfUser.getBalance());
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	    
	   
	}
	
	
	
	public void processArguments(Scanner input, Bank bank, User user) {
		System.out.println("What would you like to do? Write open to open your account, balance to get your account balance, quit to quit");
		String argument = input.next();
		if (argument.equals("open")) {
			Account accountOpened=bank.openAccountForUser(input, user);
			System.out.println("Here is your account information");
			System.out.println("Your account number: "+accountOpened.getAccountNumber());
			System.out.println("Your account type: "+accountOpened.getAccountType());
			System.out.println("Your balance: "+accountOpened.getBalance());
			processArguments(input,bank,user);
		} 
		else if (argument.equals("balance")){
			 bank.accountBalance(input, bank, user);
			 processArguments(input,bank,user);
		}
		else if(argument.equals("quit")) {
			
		}
		
	}
	
	
	
	public static void main(String[] args) {
		Bank bankOfAmerica = new Bank("Bank of America");
		Scanner input =new Scanner(System.in);
		System.out.println("Hi, welcome to the bank, please create your profile");
		User user=bankOfAmerica.setUpUser(input);
		bankOfAmerica.processArguments(input, bankOfAmerica, user);
		
		
		
		
	}

}
