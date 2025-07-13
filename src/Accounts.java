import java.sql.Connection;
import java.util.Scanner;

public class Accounts {
    private Connection conn;
    private Scanner sc;

    public Accounts(Connection conn,Scanner sc){
        this.conn = conn;
        this.sc = sc;
    }
}
