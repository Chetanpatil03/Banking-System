import java.sql.*;
import java.util.Scanner;

public class User {

    private Connection conn;
    private Scanner sc;

    public User(Connection conn,Scanner sc){
        this.conn = conn;
        this.sc = sc;
    }

    public void register(){
        sc.nextLine();
        System.out.print("Enter full name : ");
        String full_name = sc.nextLine();
        System.out.print("Email : ");
        String email = sc.nextLine();
        System.out.print("Password : ");
        String pass = sc.nextLine();

        if (userExist(email)){
            System.out.println("User already exists for this Email.......");
            return;
        }

        String register = "insert into user(full_name,email,password) values(?,?,?)";

        try{
            PreparedStatement preparedStatement = conn.prepareStatement(register);
            preparedStatement.setString(1,full_name);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3,pass);

            int affectedRows = preparedStatement.executeUpdate();

            if(affectedRows > 0){
                System.out.println("Registration successful!......");
            }
            else {
                System.out.println("Registration failed");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean userExist(String email){

        String sql = "select * from user where email = '"+email+"'";
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

}
