package Controllers;

import Services.DatabaseServices.DatabaseConnection;
import Models.TableData;
import Models.User;

public class SignupController extends SecureAuth {

    // hard coded table names for authentication
    protected final static String TABLENAME1 = "user_credentials";
    protected final static String TABLENAME2 = "sea";
    protected final static String TABLENAME3 = "user";

    public void UpdateUserDetails(String TableName, TableData values, TableData condition){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        if(!(values.columnName).contains("id")){
            databaseConnection.UpdateData(TableName, condition, values);
        }
    }

    public void UpdateUserDetails(String TableName, TableData condition, TableData... values){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.UpdateData(TableName, condition, values);
    }

    public void SaveNewUserCredentials(User userParent) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        
        String Salt = generateSalt();
        String encryptedPassword = SecureAuth.createHash(userParent.getPassword(), Salt);
                System.out.println(Salt + " , " + encryptedPassword);

        databaseConnection.InsertData(TABLENAME3, false, userParent.getName(), userParent.getGender().toString(), userParent.getDob().toString(), userParent.getPhone(), userParent.getEmail(), userParent.getAddress(), userParent.getRole().toString());
        databaseConnection.InsertData(TABLENAME2, Salt);
        databaseConnection.InsertData(TABLENAME1, userParent.getUsername(), encryptedPassword);

    }

}
