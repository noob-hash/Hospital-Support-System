/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.DatabaseServices.ModelServices;

import Models.Doctor;
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
                Doctor doctor = new Doctor(rs.getInt("Id"), rs.getString("Name"), rs.getString("D_O_B"), User.Gender.valueOf(rs.getString("Gender")),rs.getString("Phone"),rs.getString("email"),rs.getString("Address"),User.Role.valueOf(rs.getString("Role")),rs.getString("Phone"),rs.getString("Specialization"), rs.getString("Education"));         
                doctorList.add(doctor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doctorList;
    }
}
