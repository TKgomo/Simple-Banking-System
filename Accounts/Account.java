package Accounts;

public abstract class Account
{
static double transactionFee = 2.0;
static double interestRate = 0.03;
    double balanceSavings = 5000;
    double balanceCheck = 10000;
    double withdraw;
    double deposit;
    public double amount;
    
    public void deposit()
    {
    }
    public void withdraw()
    {
    }
    public void displaySaving()
    {
    	System.out.println("Your Transaction Fee is R " + transactionFee);
        System.out.println("Your New balance is: R " + balanceSavings);
    }
    public void displayCheck()
    {
    	System.out.println("Your Transaction Fee is R " + transactionFee);
        System.out.println("Your New balance is: R " + balanceCheck);  
    }
    public void transferFunds()
    {
    }
    public void checkBalance()
    {
    }
    
    public static void setTransactionFee(double fee) {
        transactionFee = fee;
    }

    public static void setInterestRate(double rate) {
        interestRate = rate;
    }

    public static double getTransactionFee() {
        return transactionFee;
    }


    public static double getInterestRate() {
        return interestRate;
    }
}
