/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

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

/**
 *
 * @author Subin
 */
public class Schedule {
    
    private int id;
    private LocalDate date;
    private LocalTime visitTime;
    private LocalTime endTime;

    public Schedule(int id, String date, String visitTime, String endTime) {
        this.id = id;
        setDate(date);
        this.visitTime = LocalTime.parse(visitTime);
        this.endTime = LocalTime.parse(endTime);
    }
    
    public Schedule( String date, String visitTime, String endTime) {
        setDate(date);
        this.visitTime = LocalTime.parse(visitTime);
        this.endTime = LocalTime.parse(endTime);
    }

    public Schedule(String date, String visitTime) {
        setDate(date);
        System.out.println(this.getDate());
        this.visitTime = LocalTime.parse(visitTime);
        this.endTime = this.visitTime.plusMinutes(45);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public LocalDate getDate() {
        return date;
    }

    public void setDate(String dateApp) {
        try {
            System.out.println("date:"+dateApp);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            //formatting user input date and saving it as Date type
            Date datea = dateFormat.parse(dateApp);
            //converting obtained Date object above to LocalDate Object as it is data type we need
            Instant instant = datea.toInstant();
            ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
            LocalDate dateAppoinment = zone.toLocalDate();
            //setting dob
            this.date = dateAppoinment;
        } catch (ParseException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public LocalTime getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(LocalTime visitTime) {
        this.visitTime = visitTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime visitTime) {
        this.endTime = this.visitTime.plusMinutes(45);
    }
    
    
    
}
