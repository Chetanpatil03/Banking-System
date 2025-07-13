import java.sql.Connection;
import java.util.Scanner;

public class User {

    Connection conn;
    Scanner sc;

    public User(Connection conn,Scanner sc){
        this.conn = conn;
        this.sc = sc;
    }

}
