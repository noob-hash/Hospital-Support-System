/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.ModelServices;

import Models.Appoinment;
import Models.Department;
import Models.Doctor;
import Models.Schedule;
import Models.User;
import Services.DatabaseServices.DatabaseConnection;
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
public class DoctorService {
    
    /**
     * 
     * @param id doctor's id
     * @return Doctor model with information on said doctor
     */
    public Doctor GetDoctor(int id){
        Doctor doctor = null;
        try {
            Connection con = new DatabaseConnection().ConnectionEstablishment();
            String statement = "Select * from user inner join doctor on user.Id = doctor.User_Id where user.Id = ? and Role = 'D'";
            PreparedStatement ps = con.prepareStatement(statement);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                doctor = new Doctor(rs.getInt("Id"), rs.getString("Name"), rs.getString("D_O_B"), User.Gender.valueOf(rs.getString("Gender")),rs.getString("Phone"),rs.getString("email"),rs.getString("Address"),User.Role.valueOf(rs.getString("Role")),rs.getString("Phone"),rs.getString("Specialization"), rs.getString("Education"));         
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doctor;
    }
    
    /**
     * 
     * @return all departments in database as list of said model
     */
    public List<Department> DepartmentList(){
        List<Department> departmentList = new ArrayList<>();
        
        try {
            Connection con = new DatabaseConnection().ConnectionEstablishment();
            String statement = "Select * from department;";
            PreparedStatement ps = con.prepareStatement(statement);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Department department = new Department(rs.getInt("id"),rs.getString("Name"),rs.getString("Contact"), rs.getString("Details"));
                departmentList.add(department);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return departmentList;
    }
    
    /**
     * 
     * @param id of department
     * @return department model
     */
    public Department GetDepartment(int id){
        Department department = null;
        try {
            Connection con = new DatabaseConnection().ConnectionEstablishment();
            String statement = "Select * from department where id = '" + id + "';";
            PreparedStatement ps = con.prepareStatement(statement);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                department = new Department(rs.getInt("id"),rs.getString("Name"),rs.getString("Contact"), rs.getString("Details"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return department;
    }
    
    public List<User> PatientList(String identifier) {
        List<User> patientList = new ArrayList<>();
        try {
            User doctor = new UserService().GetUser(identifier);
            Connection con = new DatabaseConnection().ConnectionEstablishment();
            String statement = "Select * from appoinment inner join user on appoinment.user_id = user.Id and appoinment.doctor_id = ? where Role = 'P'";
            PreparedStatement ps = con.prepareStatement(statement);
            ps.setInt(1,doctor.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User patient = new User(rs.getInt("Id"), rs.getString("Name"), User.Gender.valueOf(rs.getString("Gender")), rs.getString("D_O_B"), rs.getString("Phone"), rs.getString("email"), rs.getString("Address"), User.Role.valueOf(rs.getString("Role")), rs.getString("Phone"));
                patientList.add(patient);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return patientList;
    }
    
    public List<Appoinment> AppoinmentList(String identifier) {
        List<Appoinment> appoinmentList = new ArrayList<>();
        try {
            User user = new UserService().GetUser(identifier);
            Connection con = new DatabaseConnection().ConnectionEstablishment();
            String statement = "Select * from schedule inner join user on schedule.user = user.Id inner join appoinment on appoinment.doctor_id = ? and appoinment.id = schedule.id where role='P';";
            PreparedStatement ps = con.prepareStatement(statement);
            ps.setInt(1, user.getId());
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Appoinment appoinment = new Appoinment(new User(rs.getInt("user.Id"), rs.getString("Name"), User.Gender.valueOf(rs.getString("Gender")), rs.getString("D_O_B"), rs.getString("Phone"), rs.getString("email"), rs.getString("Address"), User.Role.valueOf(rs.getString("Role"))), new Schedule(rs.getInt("schedule.id"),rs.getString("date"), rs.getString("startTime"), rs.getString("endTime")), new Doctor());
                appoinmentList.add(appoinment);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return appoinmentList;
    }
    
    /**
     * 
     * @return List of doctor model containing all user in database with role doctor
     */
    public List<Doctor> DoctorList(){
        List<Doctor> doctorList = new ArrayList<>();
        try {
            Connection con = new DatabaseConnection().ConnectionEstablishment();
            String statement = "Select * from user inner join doctor on user.Id = doctor.User_Id where Role = 'D' ;";
            PreparedStatement ps = con.prepareStatement(statement);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                System.out.println("b"+rs.getInt("Id"));
                Doctor doctor = new Doctor(rs.getInt("Id"), rs.getString("Name"), rs.getString("D_O_B"), User.Gender.valueOf(rs.getString("Gender")),rs.getString("Phone"),rs.getString("email"),rs.getString("Address"),User.Role.valueOf(rs.getString("Role")),rs.getString("Phone"),rs.getString("Specialization"), rs.getString("Education"));         
                doctorList.add(doctor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doctorList;
    }
    
    public int TotalNumber(){
        int Result = 0;
        try {
            String statement = "Select Count(Id) as total from user where Role = 'D';";
            
            PreparedStatement ps = new DatabaseConnection().Statement(statement);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Result = rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PatientService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Result;
    }
}
