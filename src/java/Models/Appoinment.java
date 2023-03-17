/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Subin
 */
public class Appoinment {
    private User user;
    private Schedule sechedule;
    private Doctor doctor;

    public Appoinment(User user, Schedule sechedule, Doctor doctor) {
        this.user = user;
        this.sechedule = sechedule;
        this.doctor = doctor;
    }
    
    public Appoinment(Schedule sechedule, Doctor doctor) {
        this.sechedule = sechedule;
        this.doctor = doctor;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Schedule getSechedule() {
        return sechedule;
    }

    public void setSechedule(Schedule sechedule) {
        this.sechedule = sechedule;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    
    
}
