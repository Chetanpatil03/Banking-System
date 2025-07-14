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


    public String login(){
        sc.nextLine();
        System.out.print("Email : ");
        String email = sc.nextLine();
        System.out.print("Password : ");
        String pass = sc.nextLine();

        String login_query = "SELECT * FROM user WHERE email = ? AND password = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(login_query);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,pass);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                return email;
            }
            else {
                return null;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public boolean userExist(String email){

        String sql = "select * from user where email = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,email);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                return true;
            }
            else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }



}
