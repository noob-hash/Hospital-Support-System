/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import Models.Appoinment;
import Models.Department;
import Models.Doctor;
import Models.Patient;
import Models.Schedule;
import Models.TableData;
import Models.User;
import Services.DatabaseServices.DatabaseConnection;
import Services.ModelServices.DoctorService;
import Services.ModelServices.PatientService;
import Services.ModelServices.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.URLEncoder;
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
@WebServlet(urlPatterns = {"/Controller"})
public class Servlet extends HttpServlet {

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

        if (page.equalsIgnoreCase("LoginPage")) {
            boolean loggedIn = new UserService().LoggedIn(request);
            if (loggedIn) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("Controller?page=dashboard");
                dispatcher.include(request, response);
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("pages/Login.jsp");
                dispatcher.include(request, response);
            }
        }

        if (page.equalsIgnoreCase("RegisterPage")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("pages/Register.jsp");
            dispatcher.include(request, response);
        }

        if (page.equalsIgnoreCase("login")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            PrintWriter out = response.getWriter();

            boolean login = new UserService().LogIn(username, password);
            boolean loggedIn = new UserService().LoggedIn(request);
            if (login || loggedIn) {

                User users = new UserService().GetUser(username);

                String UserName = users.getName();
                String Role = users.getRole().toString();
                String Password = password;
                System.out.println(Role);
                String user = URLEncoder.encode(UserName, "UTF-8");
                String role = URLEncoder.encode(Role, "UTF-8");

                if (request.getParameter("Remember") != null) {
                    Cookie cookie2 = new Cookie("Username", username);
                    Cookie cookie3 = new Cookie("Password", Password);

                    response.addCookie(cookie2);
                    response.addCookie(cookie3);
                }

                Cookie cookie1 = new Cookie("Name", user);
                Cookie cookie4 = new Cookie("Role", role);

                response.addCookie(cookie1);
                response.addCookie(cookie4);

                HttpSession session = request.getSession(true);
                session.setAttribute("Username", username);
                session.setAttribute("Name", user);
                session.setAttribute("Role", role);
                session.setAttribute("Password", Password);

                RequestDispatcher dispatcher = request.getRequestDispatcher("Controller?page=dashboard");
                dispatcher.include(request, response);

            } else {
                out.println("Incorrect username/ password");
                RequestDispatcher dispacher = request.getRequestDispatcher("pages/Login.jsp");
                dispacher.include(request, response);
            }
        }

        if (page.equalsIgnoreCase("Dashboard")) {
            String sRole = "", cRole = "";
            HttpSession session = request.getSession();
            if (new UserService().LoggedIn(request)) {
                sRole = (session.getAttribute("Role") != null) ? (String) session.getAttribute("Role") : "";
                Cookie[] cookie = request.getCookies();
                for (Cookie c : cookie) {
                    if (c.getName().equalsIgnoreCase("Role")) {
                        cRole = c.getValue();
                    }
                }
            }

            if (sRole == null && cRole == null) {
                RequestDispatcher dispacher = request.getRequestDispatcher("pages/Login.jsp");
                dispacher.forward(request, response);
            }
            if (sRole.equalsIgnoreCase("A") || cRole.equalsIgnoreCase("A")) {
                List<User> userData = new ArrayList<>();
                try {
                    ResultSet result = new DatabaseConnection().GetData("user", new TableData("Role", "P"), new TableData("Role", "P"));
                    while (result.next()) {
                        User userP = new User(Integer.parseInt(result.getString("Id")), result.getString("Name"), User.Gender.valueOf(result.getString("Gender")), result.getString("D_O_B"), result.getString("Phone"), result.getString("email"), result.getString("Address"), User.Role.valueOf(result.getString("Role")));
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
                        User userP = new User(Integer.parseInt(result.getString("Id")), result.getString("Name"), User.Gender.valueOf(result.getString("Gender")), result.getString("D_O_B"), result.getString("Phone"), result.getString("email"), result.getString("Address"), User.Role.valueOf(result.getString("Role")));
                        userData.add(userP);
                    }
                    request.setAttribute("UserData", userData);
                    RequestDispatcher dispacher = request.getRequestDispatcher("pages/Dashboard-doctor.jsp");
                    dispacher.forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                RequestDispatcher dispacher = request.getRequestDispatcher("index.jsp");
                dispacher.forward(request, response);
            }
        }

        if (page.equalsIgnoreCase("doctorList")) {
            String sRole = "", cRole = "";
            HttpSession session = request.getSession();
            if (new UserService().LoggedIn(request)) {
                sRole = (session.getAttribute("Role") != null) ? (String) session.getAttribute("Role") : "";
                Cookie[] cookie = request.getCookies();
                for (Cookie c : cookie) {
                    if (c.getName().equalsIgnoreCase("Role")) {
                        cRole = c.getValue();
                    }
                }
            }

            if (sRole == null && cRole == null) {
                RequestDispatcher dispacher = request.getRequestDispatcher("pages/Login.jsp");
                dispacher.forward(request, response);
            }
            if (sRole.equalsIgnoreCase("A") || cRole.equalsIgnoreCase("A")) {

                List<Doctor> doctorList = new DoctorService().DoctorList();
                request.setAttribute("doctorList", doctorList);

                RequestDispatcher dispacher = request.getRequestDispatcher("pages/DoctorList.jsp");
                dispacher.forward(request, response);

            } else {
                RequestDispatcher dispacher = request.getRequestDispatcher("Controller?page=logout");
                dispacher.forward(request, response);
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
            System.out.println(Password + "," + ConfirmPassword);
            int comparision = Password.compareTo(ConfirmPassword);
            PrintWriter out = response.getWriter();

            if (comparision == 0) {
                if (new UserService().UserExists(Phone)) {
                    out.println("User already exists");
                    RequestDispatcher dispacher = request.getRequestDispatcher("pages/Register.jsp");
                    dispacher.include(request, response);
                } else {
                    User userInfo = new User(
                            Name, gender, DOB, Phone,Email, Address, User.Role.P, Phone,Password
                    );
                    
                    new UserService().SaveNewUserCredentials(userInfo);
                    RequestDispatcher dispacher = request.getRequestDispatcher("pages/Login.jsp");
                    dispacher.forward(request, response);
                }
            } else {
                out.println("Your password do not match!");
                RequestDispatcher dispacher = request.getRequestDispatcher("pages/Register.jsp");
                dispacher.include(request, response);
            }
        }
        
        if (page.equalsIgnoreCase("appoinmentPage")) {

            String sRole = "", cRole = "", cId = "", sId = "";
            HttpSession session = request.getSession();
            if (new UserService().LoggedIn(request)) {
                sRole = (session.getAttribute("Role") != null) ? (String) session.getAttribute("Role") : "";
                sId = (session.getAttribute("Username") != null) ? (String) session.getAttribute("Username") : "";
                Cookie[] cookie = request.getCookies();
                for (Cookie c : cookie) {
                    if (c.getName().equalsIgnoreCase("Role")) {
                        cRole = c.getValue();
                    }
                    if (c.getName().equalsIgnoreCase("Username")) {
                        cId = c.getValue();
                    }
                }
            }
            if(cId != null){
                sId = cId;
            }
            if (sRole == null && cRole == null) {
                RequestDispatcher dispacher = request.getRequestDispatcher("pages/Login.jsp");
                dispacher.forward(request, response);
            }
            if (sRole.equalsIgnoreCase("A") || cRole.equalsIgnoreCase("A")) {

                List<Appoinment> appoinmentList = new PatientService().AppoinmentList();
                request.setAttribute("appoinmentList", appoinmentList);
                                
                RequestDispatcher dispacher = request.getRequestDispatcher("pages/Appoinment-admin.jsp");
                dispacher.forward(request, response);
            }
            if (sRole.equalsIgnoreCase("D") || cRole.equalsIgnoreCase("D")) {

                List<User> userData = new ArrayList<>();
                try {
                    ResultSet result = new DatabaseConnection().GetData("user", new TableData("Role", "P"), new TableData("Role", "P"));
                    while (result.next()) {
                        User userP = new User(Integer.parseInt(result.getString("Id")), result.getString("Name"), User.Gender.valueOf(result.getString("Gender")), result.getString("D_O_B"), result.getString("Phone"), result.getString("email"), result.getString("Address"), User.Role.valueOf(result.getString("Role")));
                        userData.add(userP);
                    }
                    request.setAttribute("UserData", userData);
                    RequestDispatcher dispacher = request.getRequestDispatcher("pages/Appoinment-doctor.jsp");
                    dispacher.forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (sRole.equalsIgnoreCase("P") || cRole.equalsIgnoreCase("P")) {

                List<Appoinment> appoinmentList = new PatientService().AppoinmentList(sId);
                request.setAttribute("appoinmentList", appoinmentList);
                RequestDispatcher dispacher = request.getRequestDispatcher("pages/Appoinment-patient.jsp");
                dispacher.forward(request, response);
            } else {
                RequestDispatcher dispacher = request.getRequestDispatcher("Controller?page=logout");
                dispacher.forward(request, response);
            }
        }

        if (page.equalsIgnoreCase("addAppoinment")) {
            String sRole = "", cRole = "",sId="", cId="";
            HttpSession session = request.getSession();
            if (new UserService().LoggedIn(request)) {
                sRole = (session.getAttribute("Role") != null) ? (String) session.getAttribute("Role") : "";
                sId = (session.getAttribute("Username") != null) ? (String) session.getAttribute("Username") : "";
                Cookie[] cookie = request.getCookies();
                for (Cookie c : cookie) {
                    if (c.getName().equalsIgnoreCase("Role")) {
                        cRole = c.getValue();
                    }
                    if (c.getName().equalsIgnoreCase("Username")) {
                        cId = c.getValue();
                    }
                }
            }

            if (sRole == null && cRole == null) {
                RequestDispatcher dispacher = request.getRequestDispatcher("pages/Login.jsp");
                dispacher.forward(request, response);
            }

            if (sRole.equalsIgnoreCase("P") || cRole.equalsIgnoreCase("P")) {
                String date = request.getParameter("date");
                String time = request.getParameter("time");
                int doctor = Integer.parseInt(request.getParameter("doctor"));
//                int docId = Integer.parseInt(request.getParameter("doctor"));
                if(cId != null){
                    sId = cId;
                }
                Appoinment appoinment = new Appoinment(new UserService().GetUser(sId), new Schedule(date,time),new DoctorService().GetDoctor(doctor));
                new PatientService().MakeAppoinment(appoinment);

                RequestDispatcher dispacher = request.getRequestDispatcher("Controller?page=appoinmentPage");
                dispacher.forward(request, response);
            } else {
                RequestDispatcher dispacher = request.getRequestDispatcher("Controller?page=logout");
                dispacher.forward(request, response);
            }
        }

        if (page.equalsIgnoreCase("department")) {

            String sRole = "", cRole = "";
            HttpSession session = request.getSession();
            if (new UserService().LoggedIn(request)) {
                sRole = (session.getAttribute("Role") != null) ? (String) session.getAttribute("Role") : "";
                Cookie[] cookie = request.getCookies();
                for (Cookie c : cookie) {
                    if (c.getName().equalsIgnoreCase("Role")) {
                        cRole = c.getValue();
                    }
                }
            }

            if (sRole == null && cRole == null) {
                RequestDispatcher dispacher = request.getRequestDispatcher("pages/Login.jsp");
                dispacher.forward(request, response);
            }

            if (sRole.equalsIgnoreCase("A") || cRole.equalsIgnoreCase("A")) {

                List<Department> departmentList = new UserService().GetDepartmentList();
                request.setAttribute("departmentList", departmentList);

                RequestDispatcher dispacher = request.getRequestDispatcher("pages/Department.jsp");
                dispacher.forward(request, response);
            } else {
                RequestDispatcher dispacher = request.getRequestDispatcher("Controller?page=logout");
                dispacher.forward(request, response);
            }

        }

        if (page.equalsIgnoreCase("patient")) {

            String sRole = "", cRole = "";
            HttpSession session = request.getSession();
            if (new UserService().LoggedIn(request)) {
                sRole = (session.getAttribute("Role") != null) ? (String) session.getAttribute("Role") : "";
                Cookie[] cookie = request.getCookies();
                for (Cookie c : cookie) {
                    if (c.getName().equalsIgnoreCase("Role")) {
                        cRole = c.getValue();
                    }
                }
            }

            if (sRole == null && cRole == null) {
                RequestDispatcher dispacher = request.getRequestDispatcher("pages/Login.jsp");
                dispacher.forward(request, response);
            }

            if (sRole.equalsIgnoreCase("A") || cRole.equalsIgnoreCase("A")) {

                List<User> patientList = new PatientService().PatientList();
                request.setAttribute("patientList", patientList);

                RequestDispatcher dispacher = request.getRequestDispatcher("pages/Patient-admin.jsp");
                dispacher.forward(request, response);
            } else {
                RequestDispatcher dispacher = request.getRequestDispatcher("Controller?page=logout");
                dispacher.forward(request, response);
            }
        }

        if (page.equalsIgnoreCase("logout")) {
            new UserService().logOut(request, response);
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
