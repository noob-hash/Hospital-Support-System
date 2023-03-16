/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.time.LocalDate;

/**
 *
 * @author Subin
 */
public class Services {
    
    private int id;
    private String name;
    private int price;
    private boolean isAvailable;
    private LocalDate createdDate;
    private LocalDate lastUpdated;

    public Services() {
    }

    //all data
    public Services(int id, String name, int price, boolean isAvailable, LocalDate createdDate, LocalDate lastUpdated) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isAvailable = isAvailable;
        this.createdDate = createdDate;
        this.lastUpdated = lastUpdated;
    }

    //insert service data to database
    //first time
    public Services(String name, int price, boolean isAvailable, LocalDate createdDate, LocalDate lastUpdated) {
        this.name = name;
        this.price = price;
        this.isAvailable = isAvailable;
        this.createdDate = LocalDate.now();
        this.lastUpdated = LocalDate.now();
    }    
    
    //update service data to database
    public Services(String name, int price, boolean isAvailable, LocalDate lastUpdated) {
        this.name = name;
        this.price = price;
        this.isAvailable = isAvailable;
        this.lastUpdated = LocalDate.now();
    }

    //to sort service by avaibility
    //pass to view
    public Services(String name, int price, boolean isAvailable) {
        this.name = name;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    //sorted available service data from database
    //pass to view
    public Services(String name, int price) {
        this.name = name;
        this.price = price;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDate lastUpdated) {
        this.lastUpdated = LocalDate.now();
    }

    //when you need json data of services
    @Override
    public String toString() {
        return "Services{" + "id=" + id 
                + ", name=" + name 
                + ", price=" + price 
                + ", isAvailable=" + isAvailable 
                + ", createdDate=" + createdDate 
                + ", lastUpdated=" + lastUpdated 
                + '}';
    }
  
}
