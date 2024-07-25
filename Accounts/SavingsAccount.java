package Accounts;

import Accounts.Account;
import Accounts.BankingOperations;

public class SavingsAccount extends Account implements BankingOperations {
	   @Override
	        public void deposit()
	        {
	         balanceSavings = balanceSavings + amount-transactionFee;
	         
	        }
	        @Override
	        public void withdraw()
	        {
	            if(amount <= balanceSavings)
	            {
	                balanceSavings = balanceSavings - amount-transactionFee;
	            }
	            else
	                System.out.println("You do not have enough funds!!");  
	        }
	        @Override
	        public void transferFunds()
	        {
	        	if(amount <= balanceSavings)
	        	{
		        	balanceSavings = balanceSavings - amount-transactionFee;
		        	System.out.println("You have Successfully Transfered R" + amount + " To CheckingAccount!");
	        	}
	        	else
	                System.out.println("You do not have enough funds to perform that Transaction!");
	        }
	        @Override
	        public void checkBalance()
	        {
	        	System.out.println("Your Balance in your Savings account is: R" +balanceSavings);
	        }  
	}