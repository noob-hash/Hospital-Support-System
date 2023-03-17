package Controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * 
 * @author Subin
 */
public interface Authentication {
    
    /**
     * 
     * @param request from jsp is passed
     * @return true if user is logged in else return false
     */
    public boolean LoggedIn(HttpServletRequest request);
    
    
    /**
     * 
     * @param Username
     * @param Password
     * @return true if username and password match that from database else false
     */
    public boolean LogIn(String Username, String Password);
    
    /**
     * 
     * @param request to get cookies and session data of the user
     * @param response to destroy session and redirect
     */
    public void logOut(HttpServletRequest request, HttpServletResponse response);
}
