package account;
import java.util.Scanner;

import java.io.PrintWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class Account {

	private String accountType;
	private long accountNumber;
	private String user;
	private long balance;
	private Scanner sc;
	
	public static long generateAccountNumber() {
		Random random = new Random();
	    char[] digits = new char[12];
	    for (int i = 0; i < 12; i++) {
	        digits[i] = (char) (random.nextInt(10) + '0');
	    }
	    return Long.parseLong(new String(digits));
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
	
	public static long provideUserBalance(long accountNumber, File accountsInfo) {
		try {
			Scanner inputStream = new Scanner(accountsInfo);
			while (inputStream.hasNext()) {
				String data = inputStream.next();
				String[] allData = data.split(",");
				long dataAccountNumber = Long.parseLong(allData[0]);
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

	
	
	public static void main(String[] args) {
	    File accountsInfo=new File("src/accountsInfo.csv");
	    Scanner sc=new Scanner(System.in);
	    Scanner argsScanner=new Scanner(System.in);
	    Account newAccount=new Account();
	    try {
	    	if(accountsInfo.createNewFile()) {
	    		
	    	}
	    	else {
	    		System.out.println("Instructions: type 'open' to open an account; type");
	    		String next = argsScanner.next();
	    		if(next.equals("open")) {
	    		newAccount.openAccount(accountsInfo);
	    		} else if(next.equals("balance")) {
	    			System.out.println("Enter your account number: ");
	    			newAccount.provideUserBalance(argsScanner.nextLong(), accountsInfo);
	    		}

	    		
	    	}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	    
	}

}
