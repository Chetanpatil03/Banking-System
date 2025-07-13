import java.sql.Connection;
import java.util.Scanner;

public class Accounts {
    Connection conn;
    Scanner sc;

    public Accounts(Connection conn,Scanner sc){
        this.conn = conn;
        this.sc = sc;
    }
}
