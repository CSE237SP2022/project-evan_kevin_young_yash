package test;
import bank.Account;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class accountTest {

	@Test
	void testExpectedExceptionOne() {
		assertThrows(IllegalArgumentException.class,()->{
			Account account=new Account("Jason","chec");
		});
		assertThrows(IllegalArgumentException.class,()->{
			Account account=new Account("Jason","sav",100);
		});
		
	}
	@Test
	void testExpectedExceptionNotThrow() {
		try {
			Account accountOne=new Account("Evan","Checking");
			Account accountTwo=new Account("Logan","Saving",100);
		}
		catch(Exception e) {
			fail("Should have not thrown any exception");
		}
	}
	@Test
	void testGetUser() {
		Account accountOne=new Account("Evan","Checking");
		assertEquals("Evan",accountOne.getUser());
		Account accountTwo=new Account("Logan","Saving",100);
		assertEquals("Logan",accountTwo.getUser());
		
	}
	@Test
	void testGetBalance() {
		Account accountOne=new Account("Evan","Checking");
		assertEquals(0,accountOne.getBalance());
		Account accountTwo=new Account("Logan","Saving",100);
		assertEquals(100,accountTwo.getBalance());
	}
	@Test
	void testGetAccountType() {
		Account accountOne=new Account("Evan","Checking");
		assertEquals("checking",accountOne.getAccountType());
		Account accountTwo=new Account("Logan","Saving",100);
		assertEquals("saving",accountTwo.getAccountType());	
	}
	@Test
	void testGetAccountNumber() {
		Account accountOne=new Account("Evan","Checking");
		String numOne=accountOne.getAccountNumber();
		assertEquals(8,numOne.length());
		Account accountTwo=new Account("Logan","Saving",100);
		String numTwo=accountTwo.getAccountNumber();
		assertEquals(8,numTwo.length());
	}
	
	@Test
	void testGetBalanceCorrectBalance() {
		Account accountOne = new Account("Yash", "Checking", 100);
		double accountBalance = accountOne.getBalance();
		assertEquals(100,accountBalance, 0.0001);
	}
	
	@Test
	void testGetBalanceIncorrectBalance() {
		Account accountOne = new Account("Yash", "Checking", 100);
		double accountBalance = accountOne.getBalance();
		boolean balanceCorrect = accountBalance == 90;
		assertFalse(balanceCorrect);
	}
	
	@Test
	void testSetBalanceCorrectBalance() {
		Account accountOne = new Account("Young", "Checking", 100);
		accountOne.setDepositBalance(100,55); 
		assertEquals(155,accountOne.getBalance(), 0.0001);
	}

}
