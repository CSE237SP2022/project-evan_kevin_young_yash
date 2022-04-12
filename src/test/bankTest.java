package test;
import bank.Bank;
import bank.User;
import bank.Account;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class bankTest {
	
	@Test
	void testIsInteger() {
		Bank test = new Bank("TestBank"); 
		boolean result = test.isInteger(" "); 
		assertEquals(result,false);
		result = test.isInteger("a6f2"); 
		assertEquals(result,false);
		result = test.isInteger("6758"); 
		assertEquals(result,true);
	}
	
	@Test
	void testCheckUsername() {
		Bank test = new Bank("TestBank"); 
		LinkedList<User> users = new LinkedList<User>();  
		User young = new User("Young","123456789"); 
		User evan = new User("Evan", "evanzhong12345");
		users.add(young); 
		users.add(evan);
		test.setUsers(users);
		boolean result = test.checkUsernameExist("Evan"); 
		assertEquals(result,true);
		result = test.checkUsernameExist("Young1"); 
		assertEquals(result,false);
	}
	
	@Test
	void testGetSingleAccount() {
		Bank test = new Bank("TestBank"); 
		LinkedList<Account> accounts = new LinkedList<Account>();  
		Account youngChecking = new Account("Young", "checking", 1200); 
		youngChecking.setAccountNumber("6666666");
		Account evanSaving = new Account("Evan", "saving", 1200); 
		evanSaving.setAccountNumber("567891");
		accounts.add(youngChecking);
		accounts.add(evanSaving);
		test.setAccounts(accounts);
		Account result = test.getSingleAccount("6666666"); 
		assertEquals(result, youngChecking);
		
		boolean thrown = false; 
		try {
			test.getSingleAccount("123456");
		}catch(IllegalArgumentException e){
			thrown = true; 
		}
		assertTrue(thrown);
	}
	
	@Test
	void testSetUpUser() {
		Bank test = new Bank("TestBank"); 
		LinkedList<User> users = new LinkedList<User>();
		User young = new User("Young","123456789"); 
		User evan = new User("Evan", "evanzhong12345");
		users.add(young); 
		users.add(evan);
		test.setUsers(users);
		
		User result = test.setUpUser("Young","newPassword");
		assertEquals(result, null);
		
		result = test.setUpUser("Kevin","123456789");
		User user = new User("Kevin","123456789");
		assertEquals(result.getUsername(), user.getUsername());	
		assertEquals(result.getPassword(), user.getPassword());	
	}
	
	@Test
	void testLoginAccount() {
		Bank test = new Bank("TestBank"); 
		LinkedList<User> users = new LinkedList<User>();
		User young = new User("Young","123456789"); 
		User evan = new User("Evan", "evanzhong12345");
		users.add(young); 
		users.add(evan);
		test.setUsers(users);
		
		User result = test.logIntoAccount("Young","newPassword");
		assertEquals(result, null);
		
		result = test.logIntoAccount("Evan", "evanzhong12345");
		assertEquals(result.getUsername(), evan.getUsername());	
		assertEquals(result.getPassword(), evan.getPassword());	
	}
	
	@Test
	void testOpenAccount() {
		Bank test = new Bank("TestBank"); 
		User young = new User("Young","123456789");
		Account result = test.openAccountForUser(young, "checking", "Yes", "5600"); 
		Account check = new Account("Young","checking", 5600); 
		assertEquals(result.getUser(), check.getUser());
		assertEquals(result.getBalance(), check.getBalance());
		assertEquals(result.getAccountType(), check.getAccountType());
		
		User evan = new User("Evan", "evanzhong12345");
		result = test.openAccountForUser(evan, "saving", "No", "5600"); 
		check = new Account("Evan","saving"); 
		assertEquals(result.getUser(), check.getUser());
		assertEquals(result.getBalance(), check.getBalance());
		assertEquals(result.getAccountType(), check.getAccountType());
		
		User yash = new User("Yash", "12345678910");	
		boolean thrown = true; 
		try {
			result = test.openAccountForUser(yash, "incorrect", "No", "5600");
		}catch(Exception e){
			thrown = false;
		}
		assertTrue(thrown); 
		
	}
	
	
}
