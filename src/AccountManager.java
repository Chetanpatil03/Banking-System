import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AccountManager {
    private Connection conn;
    private Scanner sc;

    public AccountManager(Connection conn,Scanner sc){
        this.conn = conn;
        this.sc = sc;
    }

    public void debit_money(long acc_number) throws SQLException{
        sc.nextLine();
        System.out.print("Enter amount : ");
        double amount = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter security PIN : ");
        String security_pin = sc.nextLine();

        try{
            conn.setAutoCommit(false);

            if (acc_number != 0){
                PreparedStatement preparedStatement = conn.prepareStatement("select * from accounts where acc_number = ? and security_pin = ?");
                preparedStatement.setLong(1,acc_number);
                preparedStatement.setString(1,security_pin);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()){
                    double current_balance = resultSet.getDouble("balance");
                    if (amount <= current_balance){
                        String debit_query = "update accounts set balance = balance - ? where acc_number = ?";

                        PreparedStatement preparedStatement1 = conn.prepareStatement(debit_query);
                        preparedStatement1.setDouble(1,amount);
                        preparedStatement1.setLong(2,acc_number);

                        int affectedRows = preparedStatement.executeUpdate();

                        if (affectedRows > 0){
                            System.out.println("Rs. "+amount+" debited Successfully");
                            conn.commit();
                            conn.setAutoCommit(true);
                            return;
                        }
                        else {
                            System.out.println("TRANSACTION FAILED !");
                            conn.rollback();
                            conn.setAutoCommit(true);
                        }

                    }
                    System.out.println("Insufficient Balance.....");
                }
                else {
                    System.out.println("Invalid Security PIN ......");
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        conn.setAutoCommit(true);
    }

    public void credit_money(long acc_number) throws SQLException{
        sc.nextLine();
        System.out.print("Enter amount : ");
        double amount = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter security PIN : ");
        String security_pin = sc.nextLine();

        try{
            conn.setAutoCommit(false);

            if (acc_number != 0){
                PreparedStatement preparedStatement = conn.prepareStatement("select * from accounts where acc_number = ? and security_pin = ?");
                preparedStatement.setLong(1,acc_number);
                preparedStatement.setString(1,security_pin);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()){

                    String credit_query = "update accounts set balance = balance + ? where acc_number = ?";
                    PreparedStatement preparedStatement1 = conn.prepareStatement(credit_query);
                    preparedStatement1.setDouble(1,amount);
                    preparedStatement1.setLong(2,acc_number);

                    int affectedRows = preparedStatement.executeUpdate();

                    if (affectedRows > 0){
                        System.out.println("Rs. "+amount+" credited Successfully");
                        conn.commit();
                        conn.setAutoCommit(true);
                        return;
                    }
                    else {
                        System.out.println("TRANSACTION FAILED !");
                        conn.rollback();
                        conn.setAutoCommit(true);
                    }

                }
                else {
                    System.out.println("Invalid Security PIN ......");
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        conn.setAutoCommit(true);
    }

    public void getBalance(long acc_number){
        sc.nextLine();
        System.out.print("Enter Security PIN: ");
        String security_pin = sc.nextLine();

        try{
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT balance FROM accounts WHERE acc_number = ? and security_pin = ?");
            preparedStatement.setLong(1,acc_number);
            preparedStatement.setString(2,security_pin);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                double balance = resultSet.getDouble("balance");
                System.out.println("Balance : "+balance);
            }
            else {
                System.out.println("INVALID PIN.....");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void transfer_money(long sender_AccNumber) throws SQLException{
        sc.nextLine();
        System.out.print("Enter Receiver Account number : ");
        long receiver_accNo = sc.nextLong();
        System.out.print("Enter Amount : ");
        double amount = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter Security PIN : ");
        String security_pin = sc.nextLine();

        try {
            conn.setAutoCommit(false);

            if (sender_AccNumber != 0 && receiver_accNo !=0){

                PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM accounts where acc_number = ? and security_pin = ?");
                preparedStatement.setLong(1,sender_AccNumber);
                preparedStatement.setString(2,security_pin);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()){
                    double current_balance = resultSet.getDouble("balance");

                    if (amount <= current_balance){
                        String debit_query = "UPDATE accounts SET balance = balance - ? where acc_number = ?";
                        String credit_query = "UPDATE accounts SET balance = balance + ? where acc_number = ?";

                        PreparedStatement debitPreparedStatement = conn.prepareStatement(debit_query);
                        debitPreparedStatement.setDouble(1,amount);
                        debitPreparedStatement.setLong(2,sender_AccNumber);

                        PreparedStatement creditPreparedStatement = conn.prepareStatement(credit_query);
                        creditPreparedStatement.setDouble(1,amount);
                        creditPreparedStatement.setLong(2,receiver_accNo);

                        int rowAffected1 = debitPreparedStatement.executeUpdate();
                        int rowAffected2 = creditPreparedStatement.executeUpdate();

                        if (rowAffected2 > 0 && rowAffected1 > 0){
                            System.out.println("Transaction successful!");
                            System.out.println("Rs."+amount+" transferred Successfully to "+sender_AccNumber);
                            conn.commit();
                            conn.setAutoCommit(true);
                            return;
                        }
                        else {
                            System.out.println("Transaction FAILED.......");
                            conn.rollback();
                            conn.setAutoCommit(true);
                        }
                    }
                    else {
                        System.out.println("Insufficient Balance.....");
                    }
                }
                else {
                    System.out.println("Invalid Security PIN........");
                }

            }
            else {
                System.out.println("Invalid account number !............");
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        conn.setAutoCommit(true);
    }
}
