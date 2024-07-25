package Transactions;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Main.databaseCon;

public class TransactionsInfo {
    private String Account_Number;
    private double Transaction_amount;
    private double Balance;
    private static String query;

    public TransactionsInfo(String Account_Number, double Transaction_amount, double Balance) {
        this.Account_Number = Account_Number;
        this.Transaction_amount = Transaction_amount;
        this.Balance = Balance;
    }

    public static ArrayList<TransactionsInfo> Tr() {
        ArrayList<TransactionsInfo> T = new ArrayList<>();
        try {
            query = "SELECT * FROM accountdetails.transactions";
            PreparedStatement stmt = databaseCon.Conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String accountNumber = rs.getString("Account_Number");
                double transactionAmount = rs.getDouble("Transaction_Amount");
                double balance = rs.getDouble("Balance");

                T.add(new TransactionsInfo(accountNumber, transactionAmount, balance));
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
        return T;
    }

    public void showTransactions() {
        ArrayList<TransactionsInfo> transactionList = TransactionsInfo.Tr();
        for (TransactionsInfo transaction : transactionList) {
            System.out.println("Account Number: " + transaction.Account_Number);
            System.out.println("Balance: " + transaction.Balance);
            System.out.println("Transaction Amount: " + transaction.Transaction_amount);
        }
    }
}

