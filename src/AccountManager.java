import java.sql.Connection;
import java.util.Scanner;

public class AccountManager {
    private Connection conn;
    private Scanner sc;

    public AccountManager(Connection conn,Scanner sc){
        this.conn = conn;
        this.sc = sc;
    }
}
