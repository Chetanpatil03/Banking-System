import java.sql.Connection;
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
        String sql = "SELECT "
    }
}
