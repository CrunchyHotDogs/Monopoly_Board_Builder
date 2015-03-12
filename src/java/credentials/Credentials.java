package credentials;


import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Kyle
 */
public class Credentials {
    public static Connection getConnection() {
        Connection connection = null;
        boolean FLAG = true;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String jdbc = "jdbc:mysql://localhost/mbb";
            String user = "root";
            String pass = "";
            connection = DriverManager.getConnection(jdbc, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            FLAG = false;
            Logger.getLogger(Credentials.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(Credentials.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return connection;
    }
}
