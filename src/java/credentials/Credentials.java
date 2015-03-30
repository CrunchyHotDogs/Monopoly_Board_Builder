package credentials;


import java.sql.*;
/**
 * A simple object that is used to retrieve database connections.
 * @author Kyle
 */
public class Credentials {
    public static Connection getConnection() {
        Connection connection = null;
        boolean FLAG = true;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String jdbc = "jdbc:mysql://127.3.218.2/mbb";
            String user = "adminL2FLvIx";
            String pass = "FZQDVMs-SZMd";
            //Commented out the real database password so the connection fails
            //and the program uses the localhost database.
            //String pass = "";
            connection = DriverManager.getConnection(jdbc, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            FLAG = false;
            System.out.println(ex.getMessage());
        }
        
        //If the program can't get a connection to the server, try getting a connection on th localhost.
        if (FLAG == false) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                String jdbc = "jdbc:mysql://localhost/mbb";
                String user = "root";
                String pass = "";
                connection = DriverManager.getConnection(jdbc, user, pass);
            } catch (ClassNotFoundException | SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
        return connection;
    }
}
