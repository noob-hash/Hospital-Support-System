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
import Models.TableData;
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

    public void addRecord(MedicalRecord record) {
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
                User patient = new User(rs.getInt("Id"), rs.getString("Name"), User.Gender.valueOf(rs.getString("Gender")), rs.getString("D_O_B"), rs.getString("Phone"), rs.getString("email"), rs.getString("Address"), rs.getInt("Deleted"), User.Role.valueOf(rs.getString("Role")));
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
                Appoinment appoinment = new Appoinment(new User(rs.getInt("user.Id"), rs.getString("Name"), User.Gender.valueOf(rs.getString("Gender")), rs.getString("D_O_B"), rs.getString("Phone"), rs.getString("email"), rs.getString("Address"), rs.getInt("Deleted"), User.Role.valueOf(rs.getString("Role"))), new Schedule(rs.getInt("schedule.id"), rs.getString("date"), rs.getString("startTime"), rs.getString("endTime")), new Doctor());
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
                MedicalRecord record = new MedicalRecord(new PatientService().GetPatient(Integer.parseInt(rs.getString("U_ID"))), rs.getString("BP"), rs.getString("HB"), rs.getString("Height"), Integer.parseInt(rs.getString("Weight")), rs.getString("Symptoms"), rs.getString("Diagnostics"), rs.getString("TP"), rs.getString("DOD"));
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
                MedicalRecord record = new MedicalRecord(new PatientService().GetPatient(Integer.parseInt(rs.getString("U_ID"))), rs.getString("BP"), rs.getString("HB"), rs.getString("Height"), Integer.parseInt(rs.getString("Weight")), rs.getString("Symptoms"), rs.getString("Diagnostics"), rs.getString("TP"), rs.getString("DOD"));
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
    public List<Appoinment> DoctorAppoinment(int id) {
        List<Appoinment> appoinmentList = new ArrayList<>();
        try {
            Connection con = new DatabaseConnection().ConnectionEstablishment();
            String statement = "Select * from schedule inner join user on schedule.user = user.Id where role='P';";
            PreparedStatement ps = con.prepareStatement(statement);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Appoinment appoinment = new Appoinment(new User(rs.getInt("user.Id"), rs.getString("Name"), User.Gender.valueOf(rs.getString("Gender")), rs.getString("D_O_B"), rs.getString("Phone"), rs.getString("email"), rs.getString("Address"), rs.getInt("Deleted"), User.Role.valueOf(rs.getString("Role"))), new Schedule(rs.getString("date"), rs.getString("startTime"), rs.getString("endTime")), new Doctor());
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
     * @param appoinment model is passed using which data is inserted to
     * database
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
     * @param id phone number or email of user you want appoinment of (needs
     * fixing)
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
                Appoinment appoinment = new Appoinment(new User(rs.getInt("Id"), rs.getString("Name"), User.Gender.valueOf(rs.getString("Gender")), rs.getString("D_O_B"), rs.getString("Phone"), rs.getString("email"), rs.getString("Address"), rs.getInt("Deleted"), User.Role.valueOf(rs.getString("Role"))), new Schedule(rs.getInt("schedule.id"), rs.getString("date"), rs.getString("startTime"), rs.getString("endTime")), new Doctor());
                System.out.println(rs.getInt("Id"));
                appoinmentList.add(appoinment);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return appoinmentList;
    }

    public void DeleteAppoinment(int id) {
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
     * @return array of patient count for a year in month wise (needs to
     * complete sql)
     */
    public List<Integer> PatientCountCurr() {
        List<Integer> Result = new ArrayList<>();
        try {
            Connection con = new DatabaseConnection().ConnectionEstablishment();
            String statement = "SELECT DATE_FORMAT(date, '%Y') AS year, Month(date) as month, COUNT(id) AS patient_no FROM schedule  where YEAR(date)=Year(CurDate()) GROUP BY MONTH(date), YEAR(date);";
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
    public List<Integer> PatientCountPre() {
        List<Integer> Result = new ArrayList<>();
        try {
            Connection con = new DatabaseConnection().ConnectionEstablishment();
            String statement = "SELECT DATE_FORMAT(date, '%Y') AS year, Month(date) as month, COUNT(id) AS patient_no FROM schedule  where YEAR(date)= (Year(CurDate())-1) GROUP BY MONTH(date), YEAR(date);";
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

    public int[] GendersCount() {
        int[] Result = new int[2];
        try {
            Connection con = new DatabaseConnection().ConnectionEstablishment();
            String statement = "SELECT user.Gender,COUNT(id) AS patient_no FROM `user` where Role ='P' GROUP BY user.Gender;";
            PreparedStatement ps = con.prepareStatement(statement);
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
    
    public List<Integer> DepartmentPCount() {
        List<Integer> Result = new ArrayList<>();
        try {
            Connection con = new DatabaseConnection().ConnectionEstablishment();
            String statement = "Select department.Name, COUNT(appoinment.user_id) as Pcount from department INNER JOIN appoinment INNER JOIN doctor on appoinment.doctor_id = doctor.User_Id AND doctor.id = department.id GROUP BY department.Name";
            PreparedStatement ps = con.prepareStatement(statement);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Result.add(rs.getInt("Pcount"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Result;
    }

    public List<Integer> AgeCount() {
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
                    + "FROM `user` where Role='P';";
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

    public int TotalNumber() {
        int Result = 0;
        try {
            String statement = "Select Count(Id) as total from user where Role = 'P';";

            PreparedStatement ps = new DatabaseConnection().Statement(statement);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Result = rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PatientService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Result;
    }
}
