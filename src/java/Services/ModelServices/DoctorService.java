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
    
    public int[] GendersCount(String identifier) {
        int[] Result = new int[2];
        try {
            Connection con = new DatabaseConnection().ConnectionEstablishment();
            String statement = "SELECT user.Gender,COUNT(user.Id) AS patient_no FROM `user`  inner join appoinment on user.Id = appoinment.user_id and appoinment.doctor_id = (SELECT user.Id from user where user.Phone='"+identifier+"')  where Role ='P' GROUP BY user.Gender;";
            PreparedStatement ps = con.prepareStatement(statement);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            int i = 0;
            while (rs.next()) {
                Result[i] = rs.getInt("patient_no");
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Result;
    }
    
    public List<Integer> AgeCount(String identifier) {
        List<Integer> Result = new ArrayList<>();
        try {
            Connection con = new DatabaseConnection().ConnectionEstablishment();
            String statement = "SELECT\n"
                    + "    SUM(IF(TIMESTAMPDIFF(YEAR, user.D_O_B, CURDATE()) < 20,1,0)) as '15-',\n"
                    + "    SUM(IF(TIMESTAMPDIFF(YEAR, user.D_O_B, CURDATE()) BETWEEN 15 and 29,1,0)) as '15-30',\n"
                    + "    SUM(IF(TIMESTAMPDIFF(YEAR, user.D_O_B, CURDATE()) BETWEEN 30 and 44,1,0)) as '30 - 45',\n"
                    + "    SUM(IF(TIMESTAMPDIFF(YEAR, user.D_O_B, CURDATE()) BETWEEN 45 and 59,1,0)) as '45 - 60',\n"
                    + "    SUM(IF(TIMESTAMPDIFF(YEAR, user.D_O_B, CURDATE()) BETWEEN 60 and 74,1,0)) as '60-75',\n"
                    + "    SUM(IF(TIMESTAMPDIFF(YEAR, user.D_O_B, CURDATE()) BETWEEN 75 and 89,1,0)) as '75 - 90',\n"
                    + "    SUM(IF(TIMESTAMPDIFF(YEAR, user.D_O_B, CURDATE()) > 90,1,0)) as '90+'\n"
                    + "FROM `user` inner join appoinment on user.Id = appoinment.user_id and appoinment.doctor_id = (SELECT user.Id from user where user.Phone='"+identifier+"') where Role='P';";
            PreparedStatement ps = con.prepareStatement(statement);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                for(int i=1; i<=7; i++){
                    Result.add(rs.getInt(i));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Result;
    }
    
    public List<Integer> PatientCountCurr(String identifier) {
        List<Integer> Result = new ArrayList<>();
        try {
            Connection con = new DatabaseConnection().ConnectionEstablishment();
            String statement = "SELECT DATE_FORMAT(date, '%Y') AS year, Month(date) as month, COUNT(schedule.id) AS patient_no FROM schedule inner join appoinment on schedule.user = appoinment.user_id and appoinment.id=schedule.id inner join user on appoinment.doctor_id = user.Id where YEAR(date)=Year(CurDate()) and user.Phone = '"+identifier+"' GROUP BY MONTH(date), YEAR(date);";
            PreparedStatement ps = con.prepareStatement(statement);
            ResultSet rs = ps.executeQuery();
            int i=1;
            while (rs.next()) {
                if(rs.getInt(2)>i){
                    for(;i<rs.getInt(2);i++){
                        Result.add(0);
                    }
                }
                Result.add(rs.getInt(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Result;
    }
    
    /**
     *
     * @return array of patient count for a year in month wise (needs to
     * complete sql)
     */
    public List<Integer> PatientCountPre(String identifier) {
        List<Integer> Result = new ArrayList<>();
        try {
            Connection con = new DatabaseConnection().ConnectionEstablishment();
            String statement = "SELECT DATE_FORMAT(date, '%Y') AS year, Month(date) as month, COUNT(schedule.id) AS patient_no FROM schedule  inner join appoinment on schedule.user = appoinment.user_id and appoinment.id=schedule.id inner join user on appoinment.doctor_id = user.Id where YEAR(date)=Year(CurDate())-1 and user.Phone = '"+identifier+"' GROUP BY MONTH(date), YEAR(date);";
            PreparedStatement ps = con.prepareStatement(statement);
            ResultSet rs = ps.executeQuery();
            int i=1;
            while (rs.next()) {
                if(rs.getInt(2)>i){
                    for(;i<rs.getInt(2);i++){
                        Result.add(0);
                    }
                }
                Result.add(rs.getInt(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Result;
    }
    
    public List<String> DepartmentNames() {
        List<String> Result = new ArrayList<>();
        try {
            Connection con = new DatabaseConnection().ConnectionEstablishment();
            String statement = "Select department.Name, COUNT(appoinment.user_id) as Pcount from department INNER JOIN appoinment INNER JOIN doctor on appoinment.doctor_id = doctor.User_Id AND doctor.id = department.id GROUP BY department.Name";
            PreparedStatement ps = con.prepareStatement(statement);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Result.add(rs.getString("Name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Result;
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
                Appoinment appoinment = new Appoinment(new User(rs.getInt("user.Id"), rs.getString("Name"), User.Gender.valueOf(rs.getString("Gender")), rs.getString("D_O_B"), rs.getString("Phone"), rs.getString("email"), rs.getString("Address"), rs.getInt("Deleted"), User.Role.valueOf(rs.getString("Role"))), new Schedule(rs.getInt("schedule.id"),rs.getString("date"), rs.getString("startTime"), rs.getString("endTime")), new Doctor());
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
    
    public void addDoctor(Doctor doc){
        try {
            new UserService().SaveNewUserCredentials(doc.getUser());
            int id = new UserService().GetUser(doc.getUser().getPhone()).getId();
            String st = "Insert into doctor(Specialization, Education, User_Id) values(?,?,?);";
            PreparedStatement stmt = new DatabaseConnection().Statement(st);
            stmt.setString(1, doc.getSpeacialization());
            stmt.setString(2, doc.getEducation());
            stmt.setInt(3, id);
            System.out.println(stmt);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DoctorService.class.getName()).log(Level.SEVERE, null, ex);
        }
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
