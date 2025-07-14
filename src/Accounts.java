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

    }
    public long getAccountNumber(String email){

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
