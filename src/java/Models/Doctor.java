/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Subin
 */
public class Doctor extends User {

    private User user;
    private String Speacialization;
    private String Education;

    /**
     *
     * @param Speacialization
     * @param Education
     * 
     * refer to super class User
     * @param id user id
     * @param name
     * @param dob
     * @param gender
     * @param phone
     * @param email
     * @param address
     * @param role 
     * @param username
     * @param password
     */
    public Doctor(String Speacialization, String Education, int id, String name, String dob, Gender gender, String phone, String email, String address, Role role, String username, String password) {
        super(id, name, gender, dob, phone, email, address, role, username, password);
        this.Speacialization = Speacialization;
        this.Education = Education;
        this.user = new User(id, name, gender, dob, phone, email, address, role, username, password);
    }

    public Doctor(String name, String dob, Gender gender, String phone, String email, String address, Role role, String username, String password, String Speacialization, String Education) {
        super(name, gender, dob, phone, email, address, role, username, password);
        this.Speacialization = Speacialization;
        this.Education = Education;
        this.user = new User(name, gender, dob, phone, email, address, role, username, password);
    }

    public Doctor(String name, String dob, Gender gender, String phone, String email, String address, Role role, String username, String Speacialization, String Education) {
        super(name, gender, dob, phone, email, address, role, username);
        this.Speacialization = Speacialization;
        this.Education = Education;
        this.user = new User(name, gender, dob, phone, email, address, role, username);
    }

    public Doctor(int id, String name, String dob, Gender gender, String phone, String email, String address, Role role, String username, String Speacialization, String Education) {
        super(id, name, gender, dob, phone, email, address, role, username);
        this.Speacialization = Speacialization;
        this.Education = Education;
        this.user = new User(name, gender, dob, phone, email, address, role, username);
    }

    public Doctor() {
    }

    public String getSpeacialization() {
        return Speacialization;
    }

    public void setSpeacialization(String Speacialization) {
        this.Speacialization = Speacialization;
    }

    public String getEducation() {
        return Education;
    }

    public void setEducation(String Education) {
        this.Education = Education;
    }

    public User getUser() {

        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
