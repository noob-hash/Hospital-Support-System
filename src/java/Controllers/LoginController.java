package Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Services.DatabaseServices.DatabaseConnection;
import Models.TableData;

public class LoginController extends SecureAuth {

//    public static void main(String[] args) {
//         try {
//            LoginController l = new LoginController();
//         } catch (Exception e) {
//             // TODO Auto-generated catch block
//             e.printStackTrace();
//         }
//        
//    }
    public boolean login(String username, String password) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        String salt = null, storedHash = null, providedHash = null;

        boolean Result = false;
        try {
            ResultSet UId = databaseConnection.GetData("user_credentials", new TableData("username", username), "UniqueId");
            if (UId != null && UId.next()) {
                String UniqueId = UId.getString("UniqueId");
                ResultSet s = databaseConnection.GetData("sea", new TableData("SaltId", UniqueId), "Salt");
                s.next();
                salt = s.getString("Salt");
                // retrieve hashed and salted password from database
                storedHash = getStoredHash(username);
                if (storedHash == null) {
                    return false;
                }
                // create hash of provided password to compare to stored hash
                providedHash = createHash(password, salt);
                // compare hashes and return result
                Result = storedHash.equals(providedHash);
            } else {
                System.out.println("No such user");
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return Result;
    }

    private static String getStoredHash(String username) {
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            ResultSet rs = databaseConnection.GetData("user_credentials", new TableData("Username",username), "Password");

            if (rs.next()) {
                return rs.getString("password");
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

// // usage example
// Connection conn = // establish database connection
// if (SecureLogin.login("username", "password", conn)) {
// // login successful, create secure session
// } else {
// // login failed, display error message
// }
