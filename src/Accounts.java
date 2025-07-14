import java.sql.*;
import java.util.Scanner;

public class Accounts {
    private Connection conn;
    private Scanner sc;

    public Accounts(Connection conn,Scanner sc){
        this.conn = conn;
        this.sc = sc;
    }

    public long openAccount(String email){
        if (!accountExist(email)){
            String sql = "INSERT INTO accounts(acc_number,full_name,email,balance,security_pin) VALUES(?,?,?,?,?)";
            sc.nextLine();
            System.out.print("Enter Full name : ");
            String full_name = sc.nextLine();
            System.out.println("Enter Initial Balance");
            double balance = sc.nextDouble();
            sc.nextLine();

            System.out.println("Enter security Pin:");
            String security_pin = sc.nextLine();

            try{
                long acc_number = generateAccountNumber();

                PreparedStatement preparedStatement = conn.prepareStatement(sql);

                preparedStatement.setLong(1,acc_number);
                preparedStatement.setString(2,full_name);
                preparedStatement.setString(3,email);
                preparedStatement.setDouble(4,balance);
                preparedStatement.setString(5,security_pin);

                int affectedRow = preparedStatement.executeUpdate();

                if (affectedRow > 0){
                    return acc_number;
                }
                else {
                    throw new RuntimeException("Account creation FAILED!......");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        throw new RuntimeException("Account creation FAILED!......");
    }
    public long getAccountNumber(String email){
        String sql = "SELECT acc_number from accounts where email = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,email);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                return resultSet.getLong("acc_number");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new RuntimeException("Account number Doesn't EXIST!.........");
    }
    public long generateAccountNumber(){
        try{
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT acc_number FROM accounts ORDER BY acc_number DESC LIMIT 1 ");
            if (resultSet.next()){
                long last_accNumber = resultSet.getLong("acc_number");
                return last_accNumber + 1;
            }
            else {
                return 10000100;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return 10000100;
    }
;
    public boolean accountExist(String email){
        String sql = "SELECT acc_number from accounts where email = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1,email);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                return true;
            }
            else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
