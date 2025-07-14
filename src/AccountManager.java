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
}
