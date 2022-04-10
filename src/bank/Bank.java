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
		System.out.println("You have successfully created your profile");
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
	
	public void deposit(Scanner input, Bank bank, User user) {
		System.out.println("Please put in your account number");
		String accountNumber = input.next();
		try {
			Account accountOfUser = bank.getUser(input, user.getUsername()).getSingleAccount(accountNumber);
			System.out.println("Please put in the amount you want to deposit");
			String balance=input.next(); 
			accountOfUser.setDepositBalance(accountOfUser.getBalance(), Integer.parseInt(balance));
			System.out.println("Your balance is now: $" + accountOfUser.getBalance());
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void withdrawal(Scanner input, Bank bank, User user) {
		System.out.println("Please put in your account number");
		String accountNumber = input.next();
		try {
			Account accountOfUser = bank.getUser(input, user.getUsername()).getSingleAccount(accountNumber);
			System.out.println("Please put in the amount you want to withdraw");
			String balance = input.next();
			if (accountOfUser.getBalance() >= Integer.parseInt(balance)) {
				accountOfUser.setWithdrawBalance(accountOfUser.getBalance(), Integer.parseInt(balance));
				System.out.println("Your balance is now: $" + accountOfUser.getBalance());
			}
			else {
				System.out.println("The amount you entered exceeds your balance of $" + accountOfUser.getBalance());
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void transfer(Scanner input, Bank bank, User user) {
		while(true) {

				System.out.println("Please put in the account number for the first account");
				String accountNumberOne = input.next();
				System.out.println("Please put in the account number for the first account");
				String accountNumberTwo = input.next();
				Account accountOfUserOne = bank.getUser(input, user.getUsername()).getSingleAccount(accountNumberOne);
				Account accountOfUserTwo = bank.getUser(input, user.getUsername()).getSingleAccount(accountNumberTwo);
				System.out.println("Which account would you like to transfer to (type in account number)?");
				String accountTo = input.next();
				System.out.println("Would you like to withdraw or deposit from" + accountTo + "?");
				String response = input.next();
			
				if (accountTo == accountOfUserTwo.getAccountNumber()) {
					if (response == "withdraw" || response == "Withdraw") {
						System.out.println("Please put in the amount you want to withdraw from this account to the other account");
						String balance = input.next();
						if (accountOfUserTwo.getBalance() >= Integer.parseInt(balance)) {
							accountOfUserTwo.setWithdrawBalance(accountOfUserTwo.getBalance(), Integer.parseInt(balance));
							accountOfUserOne.setDepositBalance(accountOfUserOne.getBalance(), Integer.parseInt(balance));
							System.out.println("The balance of" + accountOfUserTwo.getAccountNumber() + " is now: $" + accountOfUserTwo.getBalance());
							System.out.println("The balance of" + accountOfUserOne.getAccountNumber() + " is now: $" + accountOfUserOne.getBalance());
						}
						else {
							System.out.println("The amount you entered exceeds the balance of $" + accountOfUserTwo.getAccountNumber());
							return;
						}
					}
					else if (response == "deposit" || response == "Deposit"){
						System.out.println("Please put in the amount you want to deposit to this account from the other account");
						String balance = input.next();
						if (accountOfUserOne.getBalance() >= Integer.parseInt(balance)) {
							accountOfUserTwo.setDepositBalance(accountOfUserTwo.getBalance(), Integer.parseInt(balance));
							accountOfUserOne.setWithdrawBalance(accountOfUserOne.getBalance(), Integer.parseInt(balance));
							System.out.println("The balance of" + accountOfUserTwo.getAccountNumber() + " is now: $" + accountOfUserTwo.getBalance());
							System.out.println("The balance of" + accountOfUserOne.getAccountNumber() + " is now: $" + accountOfUserOne.getBalance());
						}
						else {
							System.out.println("The amount you entered exceeds the balance of $" + accountOfUserOne.getAccountNumber());
							return;
						}
			
					}
					else {
						System.out.println("Please retry");
					}
			
				}
				else if (accountTo == accountOfUserOne.getAccountNumber()) {
					if (response == "withdraw" || response == "Withdraw") {
						System.out.println("Please put in the amount you want to withdraw from this account to the other account");
						String balance = input.next();
						if (accountOfUserOne.getBalance() >= Integer.parseInt(balance)) {
							accountOfUserOne.setWithdrawBalance(accountOfUserOne.getBalance(), Integer.parseInt(balance));
							accountOfUserTwo.setDepositBalance(accountOfUserTwo.getBalance(), Integer.parseInt(balance));
							System.out.println("The balance of" + accountOfUserTwo.getAccountNumber() + " is now: $" + accountOfUserTwo.getBalance());
							System.out.println("The balance of" + accountOfUserOne.getAccountNumber() + " is now: $" + accountOfUserOne.getBalance());
						}
						else {
							System.out.println("The amount you entered exceeds the balance of $" + accountOfUserOne.getAccountNumber());
							return;
						}
					}
					else if (response == "deposit" || response == "Deposit"){
						System.out.println("Please put in the amount you want to deposit to this account from the other account");
						String balance = input.next();
						if (accountOfUserTwo.getBalance() >= Integer.parseInt(balance)) {
							accountOfUserOne.setDepositBalance(accountOfUserTwo.getBalance(), Integer.parseInt(balance));
							accountOfUserTwo.setWithdrawBalance(accountOfUserOne.getBalance(), Integer.parseInt(balance));
							System.out.println("The balance of" + accountOfUserTwo.getAccountNumber() + " is now: $" + accountOfUserTwo.getBalance());
							System.out.println("The balance of" + accountOfUserOne.getAccountNumber() + " is now: $" + accountOfUserOne.getBalance());
						}
						else {
							System.out.println("The amount you entered exceeds the balance of $" + accountOfUserTwo.getAccountNumber());
							return;
						}
			
					}
				else {
						System.out.println("Please retry");
				}
			
				}
			else {
					System.out.println("Please retry");
			}
			
			
			
			/*catch(Exception e) {
				System.out.println(e.getMessage());
			}
			*/
		}
	}
	
	
	public void processArguments(Scanner input, Bank bank, User user) {
		System.out.println("What would you like to do? Write open to open your account, balance to get your account balance, deposit to make a deposit, withdraw to make a withdrawal, or quit to quit");
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
		else if(argument.equals("deposit")) {
			bank.deposit(input, bank, user);
			processArguments(input,bank,user);
		}
		else if(argument.equals("withdraw")){
			bank.withdrawal(input, bank, user);
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
