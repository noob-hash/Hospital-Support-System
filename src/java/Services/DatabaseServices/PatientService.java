/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.DatabaseServices;

import Models.Appoinment;
import Models.Doctor;
import Models.Patient;
import Models.Schedule;
import Models.User;
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
public class PatientService {
    public Patient GetPatient(int id){
        Patient patient = null;
        try {
            Connection con = new DatabaseConnection().ConnectionEstablishment();
            String statement = "Select * from user where id = ? and Role = 'P'";
            PreparedStatement ps = con.prepareStatement(statement);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                patient = new Patient(rs.getInt("Id"), rs.getString("Name"), rs.getString("D_O_B"), User.Gender.valueOf(rs.getString("Gender")),rs.getString("Phone"),rs.getString("email"),rs.getString("Address"),User.Role.valueOf(rs.getString("Role")),rs.getString("Phone"));         
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return patient;
    }
    
    public List<Patient> PatientList(){
        List<Patient> patientList = new ArrayList<>();
        try {
            Connection con = new DatabaseConnection().ConnectionEstablishment();
            String statement = "Select * from user where Role = 'P'";
            PreparedStatement ps = con.prepareStatement(statement);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Patient patient = new Patient(rs.getInt("Id"), rs.getString("Name"), rs.getString("D_O_B"), User.Gender.valueOf(rs.getString("Gender")),rs.getString("Phone"),rs.getString("email"),rs.getString("Address"),User.Role.valueOf(rs.getString("Role")),rs.getString("Phone"));         
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return patientList;
    }
    
    public List<Appoinment> AppoinmentList(){
        List<Appoinment> appoinmentList = new ArrayList<>();
        try {
            Connection con = new DatabaseConnection().ConnectionEstablishment();
            String statement = "Select * from user where Role = 'P'";
            PreparedStatement ps = con.prepareStatement(statement);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Appoinment appoinment = new Appoinment(new User(rs.getInt("Id"), rs.getString("Name"), User.Gender.valueOf(rs.getString("Gender")), rs.getString("D_O_B"),rs.getString("Phone"),rs.getString("email"),rs.getString("Address"),User.Role.valueOf(rs.getString("Role"))), new Schedule(rs.getString("date"),rs.getString("startTime"),rs.getString("endTime")), new Doctor());         
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return appoinmentList;
    }
    
    public void MakeAppoinment(Appoinment appoinment){
        try {
            Connection con = new DatabaseConnection().ConnectionEstablishment();
            String statement = "Insert into schedule(date, startTime, endTime, user) values (?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(statement);
            ps.setString(1, appoinment.getSechedule().getDate().toString());
            ps.setString(2, appoinment.getSechedule().getVisitTime().toString());
            ps.setString(3, appoinment.getSechedule().getEndTime().toString());
            ps.setInt(4, appoinment.getUser().getId());
            System.out.println(ps);
            ps.executeUpdate();
            ps.close();
            
            statement = "Insert into appoinment(user_id, doctor_id) values (?,?);";
            ps = con.prepareStatement(statement);
            ps.setInt(1, appoinment.getUser().getId());
            ps.setInt(2, appoinment.getDoctor().getId());
            System.out.println(ps);
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Appoinment> AppoinmentList(String id){
        List<Appoinment> appoinmentList = new ArrayList<>();
        try {
            Connection con = new DatabaseConnection().ConnectionEstablishment();
            String statement = "Select * from user inner join schedule on user.Id = schedule.user where Phone=?  and Role = 'P';";
            PreparedStatement ps = con.prepareStatement(statement);
            ps.setString(1, id);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Appoinment appoinment = new Appoinment(new User(rs.getInt("Id"), rs.getString("Name"), User.Gender.valueOf(rs.getString("Gender")), rs.getString("D_O_B"),rs.getString("Phone"),rs.getString("email"),rs.getString("Address"),User.Role.valueOf(rs.getString("Role"))), new Schedule(rs.getString("date"),rs.getString("startTime"),rs.getString("endTime")), new Doctor());         
                appoinmentList.add(appoinment);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return appoinmentList;
    }
    
    public int[] PatientCount(){
        int[] Result=new int[12];
        try {
            Connection con = new DatabaseConnection().ConnectionEstablishment();
            String statement = "Select count(Id) from user Role = 'P'";
            PreparedStatement ps = con.prepareStatement(statement);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Patient patient = new Patient(rs.getInt("Id"), rs.getString("Name"), rs.getString("D_O_B"), User.Gender.valueOf(rs.getString("Gender")),rs.getString("Phone"),rs.getString("email"),rs.getString("Address"),User.Role.valueOf(rs.getString("Role")),rs.getString("Phone"));         
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Result;
    }
}
