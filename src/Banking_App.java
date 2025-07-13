import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Banking_App {

    private static final String url = "jdbc:mysql://localhost:3306/Banking_sys";
    private static final String user = "root";
    private static final String pass = "root";



    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        System.out.println("Main class and main function");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            Connection conn = DriverManager.getConnection(url,user,pass);

            Scanner sc = new Scanner(System.in);

            User user = new User(conn,sc);
            Accounts accounts = new Accounts(conn,sc);
            AccountManager accountManager = new AccountManager(conn,sc);

            String email;
            long account_number;





        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
