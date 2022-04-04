package bank;
import java.util.Scanner;

import java.io.PrintWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class Account1 {

	private String accountType;
	private int accountNumber;
	private String user;
	private long balance;
	private Scanner sc;

	public static int generateAccountNumber() {
		Random random = new Random();
		char[] digits = new char[8];
		for (int i = 0; i < 8; i++) {
			digits[i] = (char) (random.nextInt(10) + '0');
		}
		return Integer.parseInt(new String(digits));
	}  

	public void openAccount(File accountsInfo) {
		this.sc=new Scanner(System.in);
		System.out.println("You wish to create an account, please type in the type of your account, you can choose from saving and checking");
		String type=sc.next();
		if(type.equals("saving") || type.equals("checking")) {
			this.accountType=type;
			this.accountNumber=generateAccountNumber();
			System.out.println("What's your username?");
			this.user=sc.next();
			this.balance=0;
			System.out.println("Account created successfully");
			System.out.println("Your account number: "+this.accountNumber);
			System.out.println("Your username: "+this.user);
			System.out.println("Your balance: "+this.balance);
			try {
				PrintWriter myWriter=new PrintWriter(new FileOutputStream(
						accountsInfo, 
						true ));
				StringBuilder sbs=new StringBuilder();
				sbs.append("\n");
				sbs.append(this.accountNumber);
				sbs.append(",");
				sbs.append(this.user);
				sbs.append(",");
				sbs.append(this.accountType);
				sbs.append(",");
				sbs.append(this.balance);
				myWriter.append(sbs.toString());
				myWriter.close();	

			} catch (IOException e) {
				System.out.println("no accountInfo file");
				e.printStackTrace();
			}

		}
		else {
			System.out.println("Only checking and saving accounts are allowed. Please try again!");
		}

	}

	public static long provideUserBalance(int accountNumber, File accountsInfo) {
		try {
			Scanner inputStream = new Scanner(accountsInfo);
			while (inputStream.hasNext()) {
				String data = inputStream.next();
				String[] allData = data.split(",");
				int dataAccountNumber = Integer.parseInt(allData[0]);
				if (dataAccountNumber == accountNumber) {
					long dataBalance = Long.parseLong(allData[2]);
					inputStream.close();
					return dataBalance;
				}
			} 
			inputStream.close();
		}
		catch (IOException io) {
			System.out.println(io);
		}
		return 0;
	}
	
	public void withdraw(File accountsInfo) {
		this.sc=new Scanner(System.in);
		System.out.println("You wish to make a withdrawal, please type in your account number");
		int acctNum=Integer.parseInt(sc.next());
		System.out.println("Please type the amount that you wish to withdraw");
		String amount=sc.next();
		try {
			Scanner inputStream = new Scanner(accountsInfo);
            while (inputStream.hasNext()) {
            	String data = inputStream.next();
                String[] allData= data.split(","); 
                int fileAcctNum = 0; 
                try { 
                	fileAcctNum = Integer.parseInt(allData[0]);
                }
                catch(NumberFormatException e) { 
                }
                String currentBalance = allData[3];
                if(fileAcctNum == acctNum) { 
                	int newBalance = Integer.parseInt(currentBalance) - Integer.parseInt(amount);
                	String finalBalance = String.valueOf(newBalance); 
                	StringBuilder sbs=new StringBuilder();
                	sbs.append(Integer.toString(acctNum));
    				sbs.append(",");
    				sbs.append(allData[1]);
    				sbs.append(",");
    				sbs.append(allData[2]);
    				sbs.append(",");
    				sbs.append(finalBalance);
    				String newData = sbs.toString();
    				System.out.println(newData);
                	PrintWriter myWriter=new PrintWriter(new FileOutputStream(
 						   accountsInfo, 
 						true ));
                	myWriter.append(newData);
                	myWriter.close();
                	break; 
                }
            }
            inputStream.close(); 
        }
        catch (IOException io) {
            System.out.println(io);
        }
	}

	public void deposit(File accountsInfo) {
		this.sc=new Scanner(System.in);
		System.out.println("You wish to make a deposit, please type in your account number");
		int acctNum=Integer.parseInt(sc.next());
		System.out.println("Please type the amount that you wish to deposit");
		String amount=sc.next();
		try {
			Scanner inputStream = new Scanner(accountsInfo);
            while (inputStream.hasNext()) {
            	String data = inputStream.next();
                String[] allData= data.split(","); 
                int fileAcctNum = 0; 
                try { 
                	fileAcctNum = Integer.parseInt(allData[0]);
                }
                catch(NumberFormatException e) { 
                }
                String currentBalance = allData[3];
                if(fileAcctNum == acctNum) { 
                	int newBalance = Integer.parseInt(currentBalance) + Integer.parseInt(amount);
                	String finalBalance = String.valueOf(newBalance); 
                	StringBuilder sbs=new StringBuilder();
                	sbs.append(Integer.toString(acctNum));
    				sbs.append(",");
    				sbs.append(allData[1]);
    				sbs.append(",");
    				sbs.append(allData[2]);
    				sbs.append(",");
    				sbs.append(finalBalance);
    				String newData = sbs.toString();
    				System.out.println(newData);
                	PrintWriter myWriter=new PrintWriter(new FileOutputStream(
 						   accountsInfo, 
 						true ));
                	myWriter.append(newData);
                	myWriter.close();
                	break; 
                }
            }
            inputStream.close(); 
        }
        catch (IOException io) {
            System.out.println(io);
        }
	}

	public static void main(String[] args) {
		File accountsInfo=new File("src/accountsInfo.csv");
		Scanner sc=new Scanner(System.in);
		Scanner argsScanner=new Scanner(System.in);
		Account1 newAccount=new Account1();
		try {
			if(accountsInfo.createNewFile()) {

			}
			else {
				System.out.println("Instructions: type 'open' to open an account; type 'deposit' to make a deposit; type 'withdraw' to make a withdrawal"
						+ "; type 'balance' to get your current balance");
				String next = argsScanner.next();
				if(next.equals("open")) {
					newAccount.openAccount(accountsInfo);
				} else if(next.equals("balance")) {
					System.out.println("Enter your account number: ");
					newAccount.provideUserBalance(argsScanner.nextInt(), accountsInfo);
				} else if(next.equals("deposit")) {
					newAccount.deposit(accountsInfo);
				} else if(next.equals("withdraw")) {
					newAccount.withdraw(accountsInfo);
				}


			}


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sc.close();


	}

}