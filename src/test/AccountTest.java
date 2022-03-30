package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import account.Account;

class AccountTest {

	@Test
	void testCorrectBalance() {
		File accountsInfo=new File("src/testBalances.csv");
		int accountNum = 12345678;
		long currentBalance = Account.provideUserBalance(accountNum, accountsInfo);
		assertEquals(9, currentBalance, 0.00001);
	}
	
	@Test
	void testIncorrectBalance() {
		File accountsInfo=new File("src/testBalances.csv");
		int accountNum =23456789;
		long currentBalance = Account.provideUserBalance(accountNum, accountsInfo);
		boolean trueOrFalse = currentBalance == 9;
		assertFalse(trueOrFalse);
	}
	
	/*@Test
	void testCorrectDeposit() {
		File accountsInfo=new File("src/testBalances.csv");
		Account newAccount=new Account();
		int accountNum = 12345678;
		newAccount.deposit(accountsInfo);
		assertEquals(9, currentBalance, 0.00001);
	}
	
	@Test
	void testIncorrectBalance() {
		File accountsInfo=new File("src/testBalances.csv");
		int accountNum =23456789;
		long currentBalance = Account.provideUserBalance(accountNum, accountsInfo);
		boolean trueOrFalse = currentBalance == 9;
		assertFalse(trueOrFalse);
	}*/ 

}
