/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import static Controllers.SignupController.TABLENAME3;
import Models.Department;
import Models.Doctor;
import Models.TableData;
import Models.User;
import Services.DatabaseServices.DatabaseConnection;
import Services.EmailServices.EmailSender;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Subin
 */
@WebServlet(urlPatterns = {"/ontroller"})
public class UserServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String page = request.getParameter("page");

        if(page.equalsIgnoreCase("login")){
            RequestDispatcher dispatcher = request.getRequestDispatcher("pages/Login.jsp");
            dispatcher.include(request, response);
        }
        
        if(page.equalsIgnoreCase("register")){
            RequestDispatcher dispatcher = request.getRequestDispatcher("pages/Register.jsp");
            dispatcher.include(request, response);
        }
        
        if (page.equalsIgnoreCase("performLogin")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            PrintWriter out = response.getWriter();

            LoginController loginController = new LoginController();

            boolean login = loginController.login(username, password);
            if (login) {
                DatabaseConnection db = new DatabaseConnection();
                ResultSet rs = db.GetData("user", new TableData("Phone", username), "Id", "Name", "Role");
                String UserName = null, Role = null, Id = null;
                try {
                    rs.next();
                    UserName = rs.getString("Name");
                    Role = rs.getString("Role");
                    Id = rs.getString("Id");

                } catch (SQLException ex) {
                    Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                String user = URLEncoder.encode(UserName, "UTF-8");
                String role = URLEncoder.encode(Role, "UTF-8");
                Cookie cookie1 = new Cookie("Username", user);
                Cookie cookie2 = new Cookie("Role", role);

                response.addCookie(cookie1);
                response.addCookie(cookie2);

                HttpSession session = request.getSession(true);
                session.setAttribute("Username", user);
                session.setAttribute("Role", role);

                RequestDispatcher dispatcher = request.getRequestDispatcher("Controller?page=dashboard");
                dispatcher.include(request, response);

            } else {
                out.println("Incorrect username/ password");
                RequestDispatcher dispacher = request.getRequestDispatcher("pages/Login.jsp");
                dispacher.include(request, response);
            }
        }

        if (page.equalsIgnoreCase("addUser")) {
            String Name = request.getParameter("Name");
            User.Gender gender = User.Gender.valueOf(request.getParameter("Gender"));
            String DOB = request.getParameter("DOB");
            String Phone = request.getParameter("Phone");
            String Email = request.getParameter("Email");
            String Address = request.getParameter("Address");
            String Password = request.getParameter("Password");
            String ConfirmPassword = request.getParameter("ConfirmPassword");

            int comparision = Password.compareTo(ConfirmPassword);
            PrintWriter out = response.getWriter();

            if (comparision == 0) {
                DatabaseConnection connection = new DatabaseConnection();
                if (connection.CheckDataExists(TABLENAME3, new TableData("phone", Phone))) {
                    out.println("User already exists");
                    RequestDispatcher dispacher = request.getRequestDispatcher("pages/Register.jsp");
                    dispacher.include(request, response);
                } else {
                    User userInfo = new User(
                            Name, gender, DOB, Phone,Email, Address, User.Role.P,  Password
                    );
                    SignupController signup = new SignupController();
                    signup.SaveNewUserCredentials(userInfo);
                    RequestDispatcher dispacher = request.getRequestDispatcher("pages/Login.jsp");
                    dispacher.forward(request, response);
                }
            } else {
                out.println("Your password do not match!");
                RequestDispatcher dispacher = request.getRequestDispatcher("pages/Register.jsp");
                dispacher.include(request, response);
            }
        }

        if (page.equalsIgnoreCase("logout")) {
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
        }

        if (page.equalsIgnoreCase("passwordReset")) {
            EmailSender emailSender = new EmailSender();
            DatabaseConnection connection = new DatabaseConnection();
            String email = request.getParameter("email");

            boolean exists = connection.CheckDataExists("User", new TableData("email", email));

            if (exists) {
                Random rnd = new Random();
                int number = rnd.nextInt(999999);
                String OTP = String.format("%06d", number);

                emailSender.EmailSpecifier(email, "No-reply: OTP valid for 10 minutes.", "Yout OTP is: " + OTP);
                RequestDispatcher dispacher = request.getRequestDispatcher("pages/enterOTP.html");
                dispacher.include(request, response);
            } else {
                PrintWriter out = response.getWriter();
                out.println("No user with email " + email + " exists.");
                RequestDispatcher dispacher = request.getRequestDispatcher("pages/Register.jsp");
                dispacher.include(request, response);
            }
        }

        if (page.equalsIgnoreCase("verifyOTP")) {
            String recievedAnswer = request.getParameter("OTP");
            String OTP;
            OTP = "878787";
            if (OTP.equals(recievedAnswer)) {
                RequestDispatcher dispacher = request.getRequestDispatcher("pages/ResetPassword.html");
                dispacher.include(request, response);
            } else {
                PrintWriter out = response.getWriter();
                out.println("Invalid OTP try again.");
                RequestDispatcher dispacher = request.getRequestDispatcher("pages/enterOTP.html");
                dispacher.include(request, response);
            }
        }

        if (page.equalsIgnoreCase("Dashboard")) {
            String sUser = null, sRole = null, cUser = null, cRole = null;
            HttpSession session = request.getSession(false);
            if (session != null) {
                sUser = (String) session.getAttribute("Username");
                sRole = (String) session.getAttribute("Role");
            } else {
                RequestDispatcher dispacher = request.getRequestDispatcher("pages/Login.jsp");
                dispacher.forward(request, response);
            }

            Cookie[] cookie = request.getCookies();

            for (Cookie c : cookie) {
                if (c.getName().equalsIgnoreCase("Username")) {
                    cUser = c.getValue();
                } else if (c.getName().equalsIgnoreCase("Role")) {
                    cRole = c.getValue();
                }
            }

            if(sRole == null && cRole == null){
                RequestDispatcher dispacher = request.getRequestDispatcher("pages/Login.jsp");
                dispacher.forward(request, response);
            }
            if (sRole.equalsIgnoreCase("A") || cRole.equalsIgnoreCase("A")) {
                List<User> userData = new ArrayList<>();
                try {
                    ResultSet result = new DatabaseConnection().GetData("user", new TableData("Role", "P"), new TableData("Role", "P"));
                    while (result.next()) {
                        User userP = new User(Integer.parseInt(result.getString("Id")), result.getString("Name"), User.Gender.valueOf(result.getString("Gender")),result.getString("D_O_B"), result.getString("Phone"), result.getString("email"), result.getString("Address"), User.Role.valueOf(result.getString("Role")));
                        userData.add(userP);
                    }
                    request.setAttribute("UserData", userData);
                    RequestDispatcher dispacher = request.getRequestDispatcher("pages/Dashboard-admin.jsp");
                    dispacher.forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (sRole.equalsIgnoreCase("P") || cRole.equalsIgnoreCase("P")) {
                RequestDispatcher dispacher = request.getRequestDispatcher("pages/Dashboard-patient.jsp");
                dispacher.forward(request, response);
            } else if (sRole.equalsIgnoreCase("D") || cRole.equalsIgnoreCase("D")) {

                List<User> userData = new ArrayList<>();
                try {
                    ResultSet result = new DatabaseConnection().GetData("user", new TableData("Role", "P"), new TableData("Role", "P"));
                    while (result.next()) {
                        User userP = new User(Integer.parseInt(result.getString("Id")), result.getString("Name"), User.Gender.valueOf(result.getString("Gender")),result.getString("D_O_B"), result.getString("Phone"), result.getString("email"), result.getString("Address"), User.Role.valueOf(result.getString("Role")));
                        userData.add(userP);
                    }
                    request.setAttribute("UserData", userData);
                    RequestDispatcher dispacher = request.getRequestDispatcher("pages/Dashboard-doctor.jsp");
                    dispacher.forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                RequestDispatcher dispacher = request.getRequestDispatcher("pages/Dashboard-doctor.jsp");
                dispacher.forward(request, response);
            } else {
                RequestDispatcher dispacher = request.getRequestDispatcher("index.jsp");
                dispacher.forward(request, response);
            }

        }

        if (page.equalsIgnoreCase("Appoinment")) {

            HttpSession session = request.getSession(false);
            String sUser = null, sRole = null, cUser = null, cRole = null;
            if (session != null) {
                sUser = (String) session.getAttribute("Username");
                sRole = (String) session.getAttribute("Role");
            } else {
                RequestDispatcher dispacher = request.getRequestDispatcher("pages/Login.jsp");
                dispacher.forward(request, response);
            }

            Cookie[] cookie = request.getCookies();

            for (Cookie c : cookie) {
                if (c.getName().equalsIgnoreCase("Username")) {
                    cUser = c.getValue();
                } else if (c.getName().equalsIgnoreCase("Role")) {
                    cRole = c.getValue();
                }
            }

            if (sRole.equalsIgnoreCase("A") || cRole.equalsIgnoreCase("A")) {
                RequestDispatcher dispacher = request.getRequestDispatcher("pages/Appoinment-admin.jsp");
                dispacher.forward(request, response);
            } else if (sRole.equalsIgnoreCase("P") || cRole.equalsIgnoreCase("P")) {
                RequestDispatcher dispacher = request.getRequestDispatcher("pages/Appoinment-patient.jsp");
                dispacher.forward(request, response);
            } else if (sRole.equalsIgnoreCase("D") || cRole.equalsIgnoreCase("D")) {
                RequestDispatcher dispacher = request.getRequestDispatcher("pages/Appoinment-doctor.jsp");
                dispacher.forward(request, response);
            } else {
                RequestDispatcher dispacher = request.getRequestDispatcher("index.jsp");
                dispacher.forward(request, response);
            }

        }

        if (page.equalsIgnoreCase("doctorList")) {
            String sUser = null, sRole = null, cUser = null, cRole = null;
            HttpSession session = request.getSession(false);
            if (session != null) {
                sUser = (String) session.getAttribute("Username");
                sRole = (String) session.getAttribute("Role");

            } else {
                RequestDispatcher dispacher = request.getRequestDispatcher("Controller?page=logout");
                dispacher.forward(request, response);
            }

            Cookie[] cookie = request.getCookies();

            for (Cookie c : cookie) {
                if (c.getName().equalsIgnoreCase("Username")) {
                    cUser = c.getValue();
                } else if (c.getName().equalsIgnoreCase("Role")) {
                    cRole = c.getValue();
                }
            }

            if (sRole.equalsIgnoreCase("A") || cRole.equalsIgnoreCase("A")) {

                try {
                    String[] tables = {"user", "doctor"};
                    String[] columns = {"Id", "id"};
                    
                    ResultSet rs = new DatabaseConnection().DoctorInfo(tables, columns, new TableData("Role", "D"));
                    List<Doctor> doctorList = new ArrayList<>();
                    while(rs.next()){
                        Doctor d = new Doctor(rs.getString("Name"),rs.getString("D_O_B"),User.Gender.valueOf(rs.getString("Gender")),rs.getString("Phone"),rs.getString("Email"),rs.getString("Address"),User.Role.valueOf(rs.getString("Role")),rs.getString("Phone"),rs.getString("Specialization"),rs.getString("Education"));
                        doctorList.add(d);
                    }
                    request.setAttribute("doctorList", doctorList);
                    RequestDispatcher dispacher = request.getRequestDispatcher("pages/DoctorList.jsp");
                    dispacher.forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        if (page.equalsIgnoreCase("newAppoinment")) {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");

            String depId = request.getParameter("department");
            String docId = request.getParameter("doctor");
            String message = request.getParameter("message");
            
            RequestDispatcher dispacher = request.getRequestDispatcher("index.jsp");
            dispacher.forward(request, response);

        }
        if (page.equalsIgnoreCase("department")) {
            String sUser = null, sRole = null, cUser = null, cRole = null;
            HttpSession session = request.getSession(false);
            if (session != null) {
                sUser = (String) session.getAttribute("Username");
                sRole = (String) session.getAttribute("Role");

            } else {
                RequestDispatcher dispacher = request.getRequestDispatcher("Controller?page=logout");
                dispacher.forward(request, response);
            }

            Cookie[] cookie = request.getCookies();

            for (Cookie c : cookie) {
                if (c.getName().equalsIgnoreCase("Username")) {
                    cUser = c.getValue();
                } else if (c.getName().equalsIgnoreCase("Role")) {
                    cRole = c.getValue();
                }
            }

            if (sRole.equalsIgnoreCase("A") || cRole.equalsIgnoreCase("A")) {
                List<Department> department = new ArrayList<>();
                System.out.println("We are there");
                try {
                    ResultSet rs = new DatabaseConnection().GetData("department");
                    while (rs.next()) {
                        Department dep = new Department();
                        dep.setId(rs.getInt("id"));
                        dep.setName(rs.getString("Name"));
                        dep.setContact(rs.getString("Contact"));
                        dep.setDetail(rs.getString("Details"));

                        department.add(dep);
                    }
                    System.out.println("We are here");
                    request.setAttribute("department", department);
                    RequestDispatcher dispacher = request.getRequestDispatcher("pages/Department.jsp");
                    dispacher.forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            RequestDispatcher dispacher = request.getRequestDispatcher("Controller?page=logout");
            dispacher.forward(request, response);
        }
        if (page.equalsIgnoreCase("addDepartment")) {
            String sUser = null, sRole = null, cUser = null, cRole = null;
            HttpSession session = request.getSession(false);
            if (session != null) {
                sUser = (String) session.getAttribute("Username");
                sRole = (String) session.getAttribute("Role");

            } else {
                RequestDispatcher dispacher = request.getRequestDispatcher("Controller?page=logout");
                dispacher.forward(request, response);
            }

            Cookie[] cookie = request.getCookies();

            for (Cookie c : cookie) {
                if (c.getName().equalsIgnoreCase("Username")) {
                    cUser = c.getValue();
                } else if (c.getName().equalsIgnoreCase("Role")) {
                    cRole = c.getValue();
                }
            }

            if (sRole.equalsIgnoreCase("A") || cRole.equalsIgnoreCase("A")) {
                Department dep = new Department();
                dep.setName(request.getParameter("Name"));
                dep.setContact(request.getParameter("Contact"));
                dep.setDetail(request.getParameter("Descciption"));
                System.out.println(dep.getName() + "," + dep.getContact() + "," + dep.getDetail());
                new DatabaseConnection().InsertData("department", dep.getName(), dep.getContact(), dep.getDetail());
                RequestDispatcher dispacher = request.getRequestDispatcher("Controller?page=department");
                dispacher.forward(request, response);
            }
            RequestDispatcher dispacher = request.getRequestDispatcher("Controller?page=logout");
            dispacher.forward(request, response);
        }

        if (page.equalsIgnoreCase("addDoctor")) {
            String sUser = null, sRole = null, cUser = null, cRole = null;
            HttpSession session = request.getSession(false);
            if (session != null) {
                sUser = (String) session.getAttribute("Username");
                sRole = (String) session.getAttribute("Role");

            } else {
                RequestDispatcher dispacher = request.getRequestDispatcher("Controller?page=logout");
                dispacher.forward(request, response);
            }

            Cookie[] cookie = request.getCookies();

            for (Cookie c : cookie) {
                if (c.getName().equalsIgnoreCase("Username")) {
                    cUser = c.getValue();
                } else if (c.getName().equalsIgnoreCase("Role")) {
                    cRole = c.getValue();
                }
            }

            if (sRole.equalsIgnoreCase("A") || cRole.equalsIgnoreCase("A")) {
                String Name = request.getParameter("Name");
                User.Gender gender = User.Gender.valueOf(request.getParameter("Gender"));
                String DOB = request.getParameter("DOB");
                String Phone = request.getParameter("Phone");
                String Email = request.getParameter("Email");
                String Address = request.getParameter("Address");
                String Password = "Password";
                String Specialization = request.getParameter("Specialization");
                String Education = request.getParameter("Education");
                Doctor doctor = new Doctor(Name,DOB, gender,Phone, Email, Address, User.Role.D, Phone, Password, Specialization, Education);
//                new DatabaseConnection().addDoctor(doctor);
                
//                new DatabaseConnection().InsertData("user",false,doctor.getName(),doctor.getGender().toString(), doctor.getDob().toString(), doctor.getPhone(), doctor.getEmail(), doctor.getAddress(),"D");
                new DatabaseConnection().InsertData("doctor",doctor.getSpeacialization(),doctor.getEducation());
                SignupController signup = new SignupController();
                signup.SaveNewUserCredentials(doctor.getUser());
                RequestDispatcher dispacher = request.getRequestDispatcher("Controller?page=doctorList");
                dispacher.forward(request, response);
            }
            RequestDispatcher dispacher = request.getRequestDispatcher("Controller?page=logout");
            dispacher.forward(request, response);
        }
        
        if (page.equalsIgnoreCase("patient")) {
            String sUser = null, sRole = null, cUser = null, cRole = null;
            HttpSession session = request.getSession(false);
            if (session != null) {
                sUser = (String) session.getAttribute("Username");
                sRole = (String) session.getAttribute("Role");

            } else {
                RequestDispatcher dispacher = request.getRequestDispatcher("Controller?page=logout");
                dispacher.forward(request, response);
            }

            Cookie[] cookie = request.getCookies();

            for (Cookie c : cookie) {
                if (c.getName().equalsIgnoreCase("Username")) {
                    cUser = c.getValue();
                } else if (c.getName().equalsIgnoreCase("Role")) {
                    cRole = c.getValue();
                }
            }

            if (sRole.equalsIgnoreCase("A") || cRole.equalsIgnoreCase("A")) {
                List<User> userData = new ArrayList<>();
                try {
                    ResultSet result = new DatabaseConnection().GetData("user", new TableData("Role", "P"), new TableData("Role", "P"));
                    while (result.next()) {
                        User userP = new User(Integer.parseInt(result.getString("Id")), result.getString("Name"), User.Gender.valueOf(result.getString("Gender")),result.getString("D_O_B"), result.getString("Phone"), result.getString("email"), result.getString("Address"), User.Role.valueOf(result.getString("Role")));
                        userData.add(userP);
                    }
                    request.setAttribute("UserData", userData);
                } catch (SQLException ex) {
                    Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                RequestDispatcher dispacher = request.getRequestDispatcher("pages/Patient-admin.jsp");
                dispacher.forward(request, response);
            }
            if (sRole.equalsIgnoreCase("D") || cRole.equalsIgnoreCase("D")) {
                List<User> userData = new ArrayList<>();
                try {
                    ResultSet result = new DatabaseConnection().GetData("user", new TableData("Role", "P"), new TableData("Role", "P"));
                    while (result.next()) {
                        User userP = new User(Integer.parseInt(result.getString("Id")), result.getString("Name"), User.Gender.valueOf(result.getString("Gender")),result.getString("D_O_B"), result.getString("Phone"), result.getString("email"), result.getString("Address"), User.Role.valueOf(result.getString("Role")));
                        userData.add(userP);
                    }
                    request.setAttribute("UserData", userData);
                    
                } catch (SQLException ex) {
                    Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                RequestDispatcher dispacher = request.getRequestDispatcher("pages/Patient-doctor.jsp");
                dispacher.forward(request, response);
            }
            RequestDispatcher dispacher = request.getRequestDispatcher("Controller?page=logout");
            dispacher.forward(request, response);

        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
