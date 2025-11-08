import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;


//main class that have menu of the banking app

public class Banking_App {

    private static final String url = "jdbc:mysql://localhost:3306/bank_sys";
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

            while (true){
                System.out.println("******* WELCOME TO BANKING SYSTEM *******");
                System.out.println();
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.println("Enter your choice : ");
                int choice1 = sc.nextInt();
//                sc.nextLine();

                switch (choice1){
                    case 1:
                        user.register();
                        break;
                    case 2:
                        email = user.login();
                        if (email != null){
                            System.out.println();
                            System.out.println("User logged in!");

                            if (!accounts.accountExist(email)){
                                System.out.println();
                                System.out.println("1. Open a new Bank account : ");
                                System.out.println("2. Exit");
                                if (sc.nextInt() == 1){
                                    account_number = accounts.openAccount(email);
                                    System.out.println("Account created successfully");
                                    System.out.println("Your account number is : "+account_number);
                                }
                                else {
                                    break;
                                }
                            }

                            account_number = accounts.getAccountNumber(email);
                            int choice2 = 0;
                            while (choice2 != 5){
                                System.out.println();
                                System.out.println("1. Debit Money");
                                System.out.println("2. Credit Money");
                                System.out.println("3. Transfer Money");
                                System.out.println("4. Check balance");
                                System.out.println("5. LOG OUT");
                                System.out.println("Enter choice : ");
                                choice2 = sc.nextInt();
                                switch (choice2){
                                    case 1:
                                        accountManager.debit_money(account_number);
                                        break;
                                    case 2:
                                        accountManager.credit_money(account_number);
                                        break;
                                    case 3:
                                        accountManager.transfer_money(account_number);
                                        break;
                                    case 4:
                                        accountManager.getBalance(account_number);
                                        break;
                                    case 5:
                                        break;
                                    default:
                                        System.out.println("Enter valid choice....");
                                        break;
                                }

                            }

                        }
                        else {
                            System.out.println("Incorrect Email or Password!....");
                        }

                    case 3:
                        System.out.println("THANK YOU FOR USING BANKING SYSTEM!!!");
                        System.out.println("Exiting system!.....");
                        return;
                    default:
                        System.out.println("Enter valid choice");
                        break;
                }
            }




        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
