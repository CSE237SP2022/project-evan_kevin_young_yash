package bank;
import java.util.LinkedList;
import java.util.Scanner;
import bank.Account;
import bank.User;

public class Bank {
	
	public String name;
	private LinkedList<User> users;
	private LinkedList<String>passwords;
	
	
	public int logIntoAccount(String username, String password) {
		for (int i = 0; i < this.users.size(); i++) {
			if (username.equals(this.users.get(i).name)) {
				if (password.equals(this.passwords.get(i))) {
					return i;
				}
			}
		} return -1;
	}
	
	public User getUser(Scanner input, String argument) {
		String password = input.next();
		int accountIndex = this.logIntoAccount(argument, password);
		while (accountIndex < 0) {
			System.out.println("Wrong username/password combination! Try Again!");
			argument = input.next();
			password = input.next();
			accountIndex = this.logIntoAccount(argument, password);
		}
		return this.users.get(accountIndex);
	}
	
	public static void main(String[] args) {
		Bank bankOfAmerica = new Bank();
		Scanner input =new Scanner(System.in);
		System.out.println("Hi, welcome to the bank. What would you like to do? Write open to open your account or type in your username otherwise.");
		String argument = input.next();
		
		if (argument.equals("open")) {
			// write open account methodology here
		} else {
			String accountNumber = input.next();
			Account accountOfUser = bankOfAmerica.getUser(input, argument).getSingleAccount(accountNumber);
			String request = input.next();
			if (request.equals("balance")) {
				System.out.println("Your balance is: $" + accountOfUser.getBalance());
			}
		}
		
	}

}
