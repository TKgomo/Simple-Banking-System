package Accounts;

import static Accounts.Account.transactionFee;

import Accounts.BankingOperations;

public class CheckingAccount extends Account implements BankingOperations
{
    @Override
        public void deposit()
        {
         balanceCheck = balanceCheck + amount-transactionFee;
        }
        @Override
        public void withdraw()
        {
            if(amount <= balanceCheck)
            {
                balanceCheck = balanceCheck - amount-transactionFee;
            }
            else
                System.out.println("You do not have enough funds!!");  
        }
        //don't worry: You'll figure it out!
        @Override
        public void transferFunds()
        {
        	if(amount <= balanceCheck)
        	{
        	balanceCheck = balanceCheck - amount-transactionFee;
        	System.out.println("You have Successfully Transfered R" + amount + " To SavingsAccount!");
        	}
        	else
                System.out.println("You do not have enough funds to perform that Transaction!");  
        }
        @Override
        public void checkBalance()
        {
        	System.out.println("Your Balance in your Checking account is: R" +balanceCheck);
        }
}
