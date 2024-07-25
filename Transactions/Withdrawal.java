package Transactions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Withdrawal {
    public static void MakeWithdrawal(Connection conn, String accountNum, double amount) {
        try {
            String query = "SELECT Balance FROM accountdetails.transactions WHERE Account_Number = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, accountNum);
            ResultSet r = stmt.executeQuery();
            if (r.next()) {
                double currentBalance = r.getDouble("Balance");
                if (currentBalance >= amount) {
                    double newBalance = currentBalance - amount;
                    System.out.println("Withdrawal successful! New Balance: R " + newBalance);

                    String updateQuery = "UPDATE accountdetails.transactions SET Balance = ? WHERE Account_Number = ?";
                    PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                    updateStmt.setDouble(1, newBalance);
                    updateStmt.setString(2, accountNum);
                    updateStmt.executeUpdate();
                    updateStmt.close();
                } else {
                    System.out.println("Insufficient funds!");
                }
            } else {
                System.out.println("Account not found!");
            }
            r.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Could not make withdrawal! ");
            e.printStackTrace();
        }
    }
}