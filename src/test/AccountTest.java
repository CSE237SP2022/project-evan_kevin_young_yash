package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import account.Account;

class AccountTest {

	@Test
	void testCorrectBalance() {
		File accountsInfo=new File("src/testBalances.csv");
		long accountNum = 123456789123L;
		long currentBalance = Account.provideUserBalance(accountNum, accountsInfo);
		assertEquals(9, currentBalance, 0.00001);
	}
	
	@Test
	void testIncorrectBalance() {
		File accountsInfo=new File("src/testBalances.csv");
		long accountNum =234567891234L;
		long currentBalance = Account.provideUserBalance(accountNum, accountsInfo);
		boolean trueOrFalse = currentBalance == 9;
		assertFalse(trueOrFalse);
	}

}
