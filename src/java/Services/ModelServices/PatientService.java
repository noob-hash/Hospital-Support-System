/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.ModelServices;

import Models.Appoinment;
import Models.Doctor;
import Models.MedicalRecord;
import Models.Patient;
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
public class PatientService {

    /**
     *
     * @param id we get patient info of provided patient id
     * @return Patient model
     */
    public Patient GetPatient(int id) {
        Patient patient = null;
        try {
            Connection con = new DatabaseConnection().ConnectionEstablishment();
            String statement = "Select * from user where id = ? and Role = 'P'";
            PreparedStatement ps = con.prepareStatement(statement);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            System.out.println(rs);
            while (rs.next()) {
                System.out.println("a");
                patient = new Patient(rs.getInt("Id"), rs.getString("Name"), rs.getString("D_O_B"), User.Gender.valueOf(rs.getString("Gender")), rs.getString("Phone"), rs.getString("email"), rs.getString("Address"), User.Role.valueOf(rs.getString("Role")), rs.getString("Phone"));
                System.out.println(patient.getId());
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return patient;
    }
    
    public void addRecord(MedicalRecord record){
        try {
            Connection con = new DatabaseConnection().ConnectionEstablishment();
            String statement = "Insert into history(BP, HB, Height, Weight, Symptoms, Diagnostics, TP, U_ID) values (?,?,?,?,?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(statement);
            ps.setString(1, record.getBloodPressure());
            ps.setString(2, record.getHeartPressure());
            ps.setString(3, record.getHeight());
            ps.setString(4, String.valueOf(record.getWeight()));
            ps.setString(5, record.getSymptoms());
            ps.setString(6, record.getDiagnosis());
//            ps.setString(7, record.getLabreports().getResult());
            ps.setString(7, record.getTreatment());
//            ps.setString(8, record.getMedicines().getName());
            ps.setString(8, String.valueOf(record.getPatient().getId()));
            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @return List of all existing Patient user
     */
    public List<User> PatientList() {
        List<User> patientList = new ArrayList<>();
        try {
            Connection con = new DatabaseConnection().ConnectionEstablishment();
            String statement = "Select * from user where Role = 'P'";
            PreparedStatement ps = con.prepareStatement(statement);
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
    
    /**
     * 
     * @return list of all appoinments (needs fixing sql query)
     */
    public List<Appoinment> AppoinmentList() {
        List<Appoinment> appoinmentList = new ArrayList<>();
        try {
            Connection con = new DatabaseConnection().ConnectionEstablishment();
            String statement = "Select * from schedule inner join user on schedule.user = user.Id where role='P';";
            PreparedStatement ps = con.prepareStatement(statement);
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
    
    public List<MedicalRecord> PatientHistory(int id) {
        List<MedicalRecord> recordList = new ArrayList<>();
        try {
            Connection con = new DatabaseConnection().ConnectionEstablishment();
            String statement = "Select * from history where U_ID = ? ORDER by DOD DESC LIMIT 1;";
            PreparedStatement ps = con.prepareStatement(statement);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MedicalRecord record = new MedicalRecord( new PatientService().GetPatient(Integer.parseInt(rs.getString("U_ID"))), rs.getString("BP"),rs.getString("HB"),rs.getString("Height"),Integer.parseInt(rs.getString("Weight")),rs.getString("Symptoms"),rs.getString("Diagnostics"),rs.getString("TP"), rs.getString("DOD"));
                recordList.add(record);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return recordList;
    }
    
    public List<MedicalRecord> PatientHistory(String identifier) {
        List<MedicalRecord> recordList = new ArrayList<>();
        try {
            User u = new UserService().GetUser(identifier);
            Connection con = new DatabaseConnection().ConnectionEstablishment();
            String statement = "Select * from history where U_ID = ? ORDER by DOD DESC;";
            PreparedStatement ps = con.prepareStatement(statement);
            ps.setInt(1, u.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MedicalRecord record = new MedicalRecord( new PatientService().GetPatient(Integer.parseInt(rs.getString("U_ID"))), rs.getString("BP"),rs.getString("HB"),rs.getString("Height"),Integer.parseInt(rs.getString("Weight")),rs.getString("Symptoms"),rs.getString("Diagnostics"),rs.getString("TP"), rs.getString("DOD"));
                recordList.add(record);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return recordList;
    }
    
    
    
    /**
     * 
     * @param id of doctor
     * @return list of appoinment for the doctor(incomplete)
     */
    public List<Appoinment> DoctorAppoinment(int id){
        List<Appoinment> appoinmentList = new ArrayList<>();
        try {
            Connection con = new DatabaseConnection().ConnectionEstablishment();
            String statement = "Select * from schedule inner join user on schedule.user = user.Id where role='P';";
            PreparedStatement ps = con.prepareStatement(statement);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Appoinment appoinment = new Appoinment(new User(rs.getInt("user.Id"), rs.getString("Name"), User.Gender.valueOf(rs.getString("Gender")), rs.getString("D_O_B"), rs.getString("Phone"), rs.getString("email"), rs.getString("Address"), User.Role.valueOf(rs.getString("Role"))), new Schedule(rs.getString("date"), rs.getString("startTime"), rs.getString("endTime")), new Doctor());
                appoinmentList.add(appoinment);
                System.out.println(appoinment.getUser().getId());
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return appoinmentList;
    }

    /**
     * 
     * @param appoinment model is passed using which data is inserted to database
     */
    public void MakeAppoinment(Appoinment appoinment) {
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

    /**
     * 
     * @param id phone number or email of user you want appoinment of (needs fixing)
     * @return List of all appoinments of said user
     */
    public List<Appoinment> AppoinmentList(String id) {
        List<Appoinment> appoinmentList = new ArrayList<>();
        try {
            Connection con = new DatabaseConnection().ConnectionEstablishment();
            String statement = "Select * from user inner join schedule on user.Id = schedule.user where Phone=?  and Role = 'P';";
            PreparedStatement ps = con.prepareStatement(statement);
            ps.setString(1, id);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Appoinment appoinment = new Appoinment(new User(rs.getInt("Id"), rs.getString("Name"), User.Gender.valueOf(rs.getString("Gender")), rs.getString("D_O_B"), rs.getString("Phone"), rs.getString("email"), rs.getString("Address"), User.Role.valueOf(rs.getString("Role"))), new Schedule(rs.getInt("schedule.id"),rs.getString("date"), rs.getString("startTime"), rs.getString("endTime")), new Doctor());
                System.out.println(rs.getInt("Id"));
                appoinmentList.add(appoinment);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return appoinmentList;
    }

    public void DeleteAppoinment(int id){
        try {
            Connection con = new DatabaseConnection().ConnectionEstablishment();
            String statement = "Delete from schedule where id = ?";
            PreparedStatement ps = con.prepareStatement(statement);
            ps.setInt(1, id);
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PatientService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 
     * @return array of patient count for a year in month wise (needs to complete sql) 
     */
    public int[] PatientCount() {
        int[] Result = new int[12];
        try {
            Connection con = new DatabaseConnection().ConnectionEstablishment();
            String statement = "Select count(Id) from user Role = 'P'";
            PreparedStatement ps = con.prepareStatement(statement);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Patient patient = new Patient(rs.getInt("Id"), rs.getString("Name"), rs.getString("D_O_B"), User.Gender.valueOf(rs.getString("Gender")), rs.getString("Phone"), rs.getString("email"), rs.getString("Address"), User.Role.valueOf(rs.getString("Role")), rs.getString("Phone"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Result;
    }
}
