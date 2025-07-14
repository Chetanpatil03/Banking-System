import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        return ;
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
