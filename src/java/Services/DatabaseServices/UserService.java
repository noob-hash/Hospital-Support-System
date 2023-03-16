/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.DatabaseServices;

import Controllers.Authentication;
import Controllers.SecureAuth;
import static Controllers.SecureAuth.createHash;
import Models.Appoinment;
import Models.Department;
import Models.TableData;
import Models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Subin
 */
public class UserService extends SecureAuth  implements Authentication {
    public User GetUser(int id){
        User user = null;
        try {
            Connection con = new DatabaseConnection().ConnectionEstablishment();
            String statement = "Select * from user where id = ?";
            PreparedStatement ps = con.prepareStatement(statement);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                user = new User(rs.getString("Name"), User.Gender.valueOf(rs.getString("Gender")),rs.getString("D_O_B"),rs.getString("Phone"),rs.getString("email"),rs.getString("Address"),User.Role.valueOf(rs.getString("Role")));         
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
    
    public User GetUser(String identifier){
        User user = null;
        try {
            Connection con = new DatabaseConnection().ConnectionEstablishment();
            String statement = "Select * from user where Phone = ? or email = ?";
            PreparedStatement ps = con.prepareStatement(statement);
            ps.setString(1, identifier);
            ps.setString(2, identifier);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                user = new User(rs.getInt("Id"),rs.getString("Name"), User.Gender.valueOf(rs.getString("Gender")),rs.getString("D_O_B"),rs.getString("Phone"),rs.getString("email"),rs.getString("Address"),User.Role.valueOf(rs.getString("Role")));         
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
//    ham tumare hai sanam
    public void SaveNewUserCredentials(User userParent) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        
        String Salt = generateSalt();
        System.out.println(userParent.getPassword());
        String encryptedPassword = createHash(userParent.getPassword(), Salt);
        
        databaseConnection.InsertData("user", false, userParent.getName(), userParent.getGender().toString(), userParent.getDob().toString(), userParent.getPhone(), userParent.getEmail(), userParent.getAddress(), userParent.getRole().toString());
        databaseConnection.InsertData("sea", Salt);
        databaseConnection.InsertData("user_credentials", userParent.getUsername(), encryptedPassword);

    }
    
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
    
    public boolean UserExists(String identifier){
        boolean exists = false;
        try {
            Connection con = new DatabaseConnection().ConnectionEstablishment();
            String statement = "Select * from user where Phone = ? or email = ?";
            PreparedStatement ps = con.prepareStatement(statement);
            ps.setString(1, identifier);
            ps.setString(2, identifier);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                exists = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exists;
    }
    
    public List<User> GetUserList(){
        List<User> userList = new ArrayList<>();
        try {
            Connection con = new DatabaseConnection().ConnectionEstablishment();
            String statement = "Select * from user";
            PreparedStatement ps = con.prepareStatement(statement);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                User user = new User(rs.getString("Name"), User.Gender.valueOf(rs.getString("Gender")),rs.getString("D_O_B"),rs.getString("Phone"),rs.getString("email"),rs.getString("Address"),User.Role.valueOf(rs.getString("Role")));         
                userList.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }
    
    public List<Department> GetDepartmentList(){
        List<Department> userList = new ArrayList<>();
        try {
            Connection con = new DatabaseConnection().ConnectionEstablishment();
            String statement = "Select * from department";
            PreparedStatement ps = con.prepareStatement(statement);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Department department = new Department(rs.getInt("Id"),rs.getString("Name"),rs.getString("Contact"),rs.getString("Details"));         
                userList.add(department);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
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
    
    @Override
    public boolean LogIn(String username, String password) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        String salt = null, storedHash = null, providedHash = null;
        boolean Result = false;
        try {
            ResultSet UId = databaseConnection.GetData("user_credentials", new TableData("username", username), "UniqueId");
            
            while(UId.next()) {
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
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Result;
    }

    @Override
    public boolean LoggedIn(HttpServletRequest request) {
        String sUser = null, sPass = null, cUser = null, cPass = null;
        HttpSession session = request.getSession(false);
        if (session != null) {
            sUser = (String) session.getAttribute("Username");
            sPass = (String) session.getAttribute("Password");
        }

        Cookie[] cookie = request.getCookies();

        for (Cookie c : cookie) {
            if (c.getName().equalsIgnoreCase("Username")) {
                cUser = c.getValue();
            } else if (c.getName().equalsIgnoreCase("Password")) {
                cPass = c.getValue();
            }
        }
        if(LogIn(cUser, cPass) || LogIn(sUser, sPass)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void logOut(HttpServletRequest request, HttpServletResponse response) {
        try {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }

            HttpSession session = request.getSession();
            session.invalidate();

            RequestDispatcher dispacher = request.getRequestDispatcher("pages/Login.jsp");
            dispacher.include(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
