package Services.DatabaseServices;

import Models.Doctor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Models.TableData;

public class DatabaseConnection {

    private Connection con = null;

    // default username and password for mysql server is used in this case
    public DatabaseConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3307/hms", "root", "");
            // Note I am using port 3307 for most people it is port 3306
            // here hospital is database name, root is username and password field is left
            // empty
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // if different username and password is used
    public DatabaseConnection(String user, String password){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3307/hms", user, password);
            // Note I am using port 3307 for most people it is port 3306
            // here hospital is database name, root is username and password field is left
            // empty
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // if connection is needed in other places
    public Connection ConnectionEstablishment() {
        return con;
    }
}
