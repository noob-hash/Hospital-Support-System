/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Subin
 */
public class Patient extends User {

    private User user;
    private MedicalProduct[] medicines;
    private MedicalRecord[] records;
    private Schedule[] schedule;

    public Patient(MedicalProduct[] medicines, MedicalRecord[] records, Schedule[] schedule, int id, String name, String dob, Gender gender, String phone, String email, String address, Role role, String username) {
        super(id, name,gender, dob, phone, email, address, role, username);
        this.medicines = medicines;
        this.records = records;
        this.schedule = schedule;
    }
    
    public Patient( int id, String name, String dob, Gender gender, String phone, String email, String address, Role role, String username) {
        super(id, name,gender, dob, phone, email, address, role, username);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MedicalProduct[] getMedicines() {
        return medicines;
    }

    public void setMedicines(MedicalProduct[] medicines) {
        this.medicines = medicines;
    }

    public MedicalRecord[] getRecords() {
        return records;
    }

    public void setRecords(MedicalRecord[] records) {
        this.records = records;
    }

    public Schedule[] getSchedule() {
        return schedule;
    }
    

    public void setSchedule(Schedule[] schedule) {
        this.schedule = schedule;
    }

}
