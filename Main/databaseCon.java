package Main;
import Transactions.Deposit;
import Transactions.Withdrawal;
import Accounts.Bank;
import Accounts.SavingsAccount;
import Accounts.CheckingAccount;
import Accounts.Account;
import Exceptions.InsufficientFundsException;
import Exceptions.InvalidTransactionException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class databaseCon 
{
	
	public static Connection Conn;
	public static Statement stmt;
	static String username = "root";
    static String password = "tshego02TK";
    static String databaseURL= "jdbc:mysql://localhost:3306/accountDetails"; // name of file accountDetails
    public static int ID;
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) 
    {
    	try
    	{
    		
    		Class.forName("com.mysql.cj.jdbc.Driver");
    	    Conn = DriverManager.getConnection(databaseURL, username, password);
    	    Bank log = new Bank();
    	    databaseCon logg = new databaseCon(); 
			System.out.println("Successfully connected to database");
			System.out.println("--------------------------------------------------------");
			System.out.println(" ");
			System.out.println("***********WELCOME TO MZANZI BANKING SYSTEM***********");
	        System.out.println("--------------------------------------------------------");
	        System.out.print("1. Register \n");
	        System.out.print("2. Login \n");
	        System.out.print("3. Exit \n");
	        System.out.print("Choose an option: ");
	        System.out.println("");
	        int answer = sc.nextInt();
	        if (answer == 1)
	        {
	         logg.register();
	        }
	        else if(answer == 2)
	        {
	          log.login();
	        }
	        else if(answer == 3)
	        {
	         log.logout();   
	        }
	        
	        while (true) {
                System.out.println("--------------Main Menu-----------------");
                System.out.println("1. Create New Account");
                System.out.println("2. View Existing Accounts");
                System.out.println("3. Perform Tanscations");
                System.out.println("4. View Mzanzi Registed Clients");
                System.out.println("5. Log out");
                System.out.print("Choose an option: ");
                String choice = sc.nextLine();
                System.out.println();
                switch (choice) {
                    case "1":
                    	createAccount();
                        break;
                    case "2":
                    	viewAccounts();
                        break;
                    case "3":
                    	PerformTransactions();
                        break;
                    case "4":
                    	ViewClientsInfo();
                        break;
                    case "5":
                        System.out.println("Thank you for using the Mzanzi Banking System. Goodbye!!");
                        Conn.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again. \n");
                }
	        }
			
    	}
    	catch (SQLException e) 
        {
            System.out.println("Database error encountered: " + e.getMessage());
        } 
        catch (ClassNotFoundException ex) 
        {
            System.out.println("MySql connection not successful " + ex);
        }
	
	}
    
public static void register()
{
	try
	{
		System.out.println("Please enter ID: ");
		ID = sc.nextInt();
		sc.nextLine();
		System.out.println("Please enter Name: ");
		String Name = sc.nextLine();
		System.out.println("Please enter Surname: ");
		String Surname = sc.nextLine();
		System.out.println("Please enter Phone Number: ");
		int Phone_Number = sc.nextInt();
		sc.nextLine();
		System.out.println("Please enter your Email: ");
		String Email = sc.nextLine();
		System.out.println("Please enter Password: ");
		String Password = sc.nextLine();
		
		
		//Name of table should be user
		String query = "INSERT INTO ClientInfo(ID,Name,Surname,Phone_Number,Email,Password) VALUES(?,?,?,?,?,?)";
		PreparedStatement pstmt = Conn.prepareStatement(query);
		pstmt.setInt(1, ID);
		pstmt.setString(2, Name);
		pstmt.setString(3, Surname);
		pstmt.setInt(4, Phone_Number);
		pstmt.setString(5, Email);
		pstmt.setString(6, Password);
		
		pstmt.executeUpdate();
		pstmt.close();
		
		System.out.println("Cleint has been registed successfully!");
	}
	catch(Exception e)
	{
		System.out.println("Error occured" + e.getMessage());
	}
	
}

public static void createAccount()
{
	try
	{
		System.out.println("Please Enter your ID: ");
		 ID = sc.nextInt();
		 sc.nextLine();
		System.out.println("Please Enter your name: ");
		String Name = sc.nextLine();
		System.out.println("Please Enter your Surname: ");
		String Surname = sc.nextLine();
		System.out.println("Please Enter your Phone Number: ");
		int Phone_Number = sc.nextInt();
		sc.nextLine();
		System.out.println("Please Enter your Email: ");
		String Email = sc.nextLine();
		System.out.println("Please Enter your AccountType: ");
		String AccountType = sc.nextLine();
		System.out.println("Please Enter your Intial Deposit: ");
		double Balance = sc.nextDouble();
		sc.nextLine();
		System.out.println("Please Enter your Suggested Account Number: ");
		String Account_Number = sc.nextLine();
		System.out.println("Please Enter your Password: ");
		String Password = sc.nextLine();
		
		String query = "INSERT INTO user(ID,Name,Surname,Phone_Number,Email,AccountType,Balance,Account_Number,Password) VALUES(?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = Conn.prepareStatement(query);
		pstmt.setInt(1, ID);
		pstmt.setString(2, Name);
		pstmt.setString(3, Surname);
		pstmt.setInt(4, Phone_Number);
		pstmt.setString(5, Email);
		pstmt.setString(6, AccountType);
		pstmt.setDouble(7, Balance);
		pstmt.setString(8, Account_Number);
		pstmt.setString(9, Password);
		pstmt.executeUpdate();
		pstmt.close();
		System.out.print("Account has successfully been created!!");
		
	}
	catch (SQLException e) 
    {
        System.out.println("Error occured: " + e.getMessage());
    } 
	
}
public static void viewAccounts()
{
	try
	{
		String query = "SELECT * FROM user";
		PreparedStatement pstmt = Conn.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery(query);
		if(!rs.isBeforeFirst())
        {
        	System.out.println("No accounts Found at the Moment. ");
        }
		else
		{
			System.out.println("**********************Accounts Avilable***********************");
			while(rs.next())
			{
				int ID = rs.getInt("id");
				String Name = rs.getString("name");
				String Surname = rs.getString("surname");
				int Phone_Number = rs.getInt("phone_number");
				String Email = rs.getString("email");
				String AccountType = rs.getString("accounttype");
				double Balance = rs.getDouble("balance");
				String Account_Number = rs.getString("account_number");
				String Password = rs.getString("password");
				
				
				System.out.println("ID: "+ ID + "\n"+ "Name: "+Name+ "\n"+"Surname: "+Surname+ "\n"+"Phone_Number: "+Phone_Number + "\n"+"Email: "+Email+ "\n"+"Account Type: "+AccountType+ "\n"+"Balance: "+Balance+ "\n"+"Account Number: "+Account_Number + "\n" +"Password: "+Password+ "\n");
			}
			System.out.println();
		}
		pstmt.close();
		rs.close();
	}
	catch (SQLException e) 
    {
        System.out.println("Error occured: " + e.getMessage());
    } 
	
 }

//Retriving Customer/Account


public static void ViewClientsInfo()
{
	try
	{
		String qquery = "SELECT * FROM clientinfo";
		PreparedStatement ppstmt = Conn.prepareStatement(qquery);
		ResultSet rss = ppstmt.executeQuery(qquery);
		if(!rss.isBeforeFirst())
        {
        	System.out.println("No Clients Found!! ");
        }
		else
		{
			System.out.println("********************** Mzanzi Clents Avilable ***********************");
			while(rss.next())
			{
				ID = rss.getInt("id");
				String Name = rss.getString("name");
				String Surname = rss.getString("surname");
				int Phone_Number = rss.getInt("phone_number");
				String Email = rss.getString("email");
				String Password = rss.getString("password");
				
				
				System.out.println("ID: "+ ID + "\n"+ "Name: "+Name+ "\n"+"Surname: "+Surname+ "\n"+"Phone_Number: "+Phone_Number + "\n"+"Email: "+Email+ "\n");
			}
			System.out.println();
		}
		ppstmt.close();
		rss.close();
	}
	catch (SQLException e) 
    {
        System.out.println("Error occured: " + e.getMessage());
        e.printStackTrace();
    } 
}

public static void PerformTransactions()
{
	//I made a console and database option to make question 1 and 4 Relevant. The use of interfaces,abstract class etc.
	System.out.println("Would you like to perfom Databse Connected Transations or Console Application?");
	System.out.println("1. Database ");
	System.out.println("2. Console");
	int choice = sc.nextInt();
	
	if(choice == 1)
	{	
		System.out.println("What transaction would you like to perform? ");
		System.out.println("1. Deposit ");
		System.out.println("2. Withdrawal");
		int choose = sc.nextInt();
		if(choose == 1)
		{
			System.out.print("On which account Number would you like to transfer money? " );
			String Account_Number = sc.next();
			System.out.print("How much would you like to deposit? ");
			double amount = sc.nextDouble();
			Deposit.MakeDeposit(Conn, Account_Number, amount);
		}
		else
		{
			System.out.print("On which account Number would you like to transfer money? " );
			String Account_Number = sc.next();
			System.out.print("How much would you like to Withdraw? ");
			double amount = sc.nextDouble();
			Withdrawal.MakeWithdrawal( Conn, Account_Number, amount);
			
		}
	}
	else
	{
		System.out.println("On which Account would you like to perform your transactions: ");
        System.out.println("Savings Account, Press: 1");
        System.out.println("Check Account, Press: 2");
        int accountType;
        while (true) {
            try {
                accountType = sc.nextInt();
                if (accountType == 1 || accountType == 2) {
                    break;
                }
                throw new InvalidTransactionException("Invalid transaction option. Please enter 1 or 2.");
            } catch (InvalidTransactionException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); // consume the invalid input
            }
        }
        Account acc;
        if (accountType == 1) {
            System.out.println("Balance in Savings Account: R5000.0 ");
            System.out.println(" ");
            System.out.println("Deposit, Withdraw, TransferFunds or CheckBalance? ");
            System.out.println(" ");
            acc = new SavingsAccount();
        } else {
            System.out.println("Balance in Check Account: R10000.0 ");
            System.out.println(" Deposit, Withdraw, TransferFunds or CheckBalance? ");
            System.out.println(" ");
            acc = new CheckingAccount();
        }
        int operation;
        while (true) {
            System.out.println("What operation would you like to perform? ");
            System.out.println("Deposit, Press: 1");
            System.out.println("Withdraw, Press: 2");
            System.out.println("Transfer Money, Press: 3");
            System.out.println("Check Balance, Press: 4");
            try {
                operation = sc.nextInt();
                if (operation >= 1 && operation <= 4) {
                    break;
                }
                throw new InvalidTransactionException("Invalid operation option. Please enter 1, 2, 3, or 4.");
            } catch (InvalidTransactionException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); // consume the invalid input
            }
        }
        if (operation == 1) {
            System.out.println("How much would you like to deposit: ");
            acc.amount = sc.nextDouble();
            acc.deposit();
            System.out.println(" ");
            acc.displaySaving();
        } else if (operation == 2) {
            System.out.println("How much would you like to withdraw: ");
            acc.amount = sc.nextDouble();
            acc.withdraw();
            System.out.println(" ");
            acc.displayCheck();
        } else if (operation == 3) {
            if (accountType == 1) {
                System.out.println("How much would you like to transfer? ");
            	acc.amount = sc.nextDouble();
            	acc.transferFunds();
            	System.out.println(" ");    
                acc.displaySaving();;
            } else {
                System.out.println("How much would you like to transfer? ");
            	acc.amount = sc.nextDouble();
            	acc.transferFunds();
            	System.out.println(" ");    
                acc.displayCheck();
            }
        } else if (operation == 4) {
            acc.checkBalance();
        }
	}

}

}

class Thread1 extends SavingsAccount implements Runnable
{
	public void run()
	{
		super.deposit();
		super.withdraw();
		super.transferFunds();
	
	}
	
}

class Thread2 extends CheckingAccount implements Runnable
{
	public void run()
	{
		super.deposit();
		super.withdraw();
		super.transferFunds();
	
	}	
	
}




