package Controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public interface Authentication {
    public boolean LoggedIn(HttpServletRequest request);
    public boolean LogIn(String Username, String Password);
    public void logOut(HttpServletRequest request, HttpServletResponse response);
}
