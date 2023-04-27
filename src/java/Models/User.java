package Models;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.User to edit this template
 */
/**
 *
 * @author Subin
 */
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import Controllers.Authentication;


public class User {
 public enum Role{A, D, P, G};
    public enum Gender{M, F, O}

    private int id;
    private String name;
    private Gender gender;
    private LocalDate dob;
    private int age; //should not be in database
    private String phone;
    private String email;
    private String address;
    private Role role;
    public boolean active;
    
    //for login
    //guest user doesn't need to login
    //guest user is limited to viewing content
    //guest ser as potiential patients and
    //only be logging in can thay make appoinment or other actions
    private String username;
    private String password;

    public User() {
    }

    
    
    public User(int id, String name, Gender gender, String dob, String phone, String email, String address, Role role, String username, String password) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        setDob(dob);
        setAge();
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.role = role;
        this.username = username;
        this.password = password;
    }
    
    public User(int id, String name, Gender gender, String dob, String phone, String email, String address, int active, Role role) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        setDob(dob);
        setAge();
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.role = role;
        this.active = (active==1)?true:false;
    }

    public User(String name, Gender gender, String dob, String phone, String email, String address, Role role, String username, String password) {
        this.name = name;
        this.gender = gender;
        setDob(dob);
        setAge();
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.role = role;
        this.username = username;
        this.password = password;
    }
    
    public User(String name, Gender gender, String dob, String phone, String email, String address, Role role, String username) {
        this.name = name;
        this.gender = gender;
        setDob(dob);
        setAge();
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.role = role;
        this.username = username;
    }
    
    public User(String name, Gender gender, String dob, String phone, String email, String address, Role role) {
        this.name = name;
        this.gender = gender;
        setDob(dob);
        setAge();
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.role = role;
        this.username = phone ;
    }
    
    public User(int id,String name, Gender gender, String dob, String phone, String email, String address, Role role, String username) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        setDob(dob);
        setAge();
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.role = role;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(String dob) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            //formatting user input date and saving it as Date type
            Date date = dateFormat.parse(dob);
            //converting obrained Date object abve to LocalDate Object as it is data type we need
            Instant instant = date.toInstant();
            ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
            LocalDate DateOfBirth = zone.toLocalDate();
            //setting dob
            this.dob = DateOfBirth;
        } catch (ParseException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge() {
        //current date
        LocalDate now = LocalDate.now();

        int yearBetween = now.getYear() - dob.getYear(); //year difference
        int monthBetween = dob.getMonthValue() - now.getMonthValue(); //month difference

        //if month is same check if date ig greater to get age else forget it
        if (monthBetween < 0) {
            yearBetween--;
        } else {

            int dayBetween = dob.getDayOfMonth() - now.getDayOfMonth(); //day difference

            if (monthBetween == 0 && dayBetween < 0) {
                yearBetween--;
            }
        }

        this.age=yearBetween;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
}