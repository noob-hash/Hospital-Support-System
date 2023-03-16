/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 *
 * @author Subin
 */
public class MedicalProduct {
    
    private int id;
    private String name; 
    private int quantity;
    private int buyingCost;
    private int sellingCost;
    private int discountPercentge;
    private int minProfitPercentage;
    private LocalDate createdDate;
    private LocalDate expiryDate;
    private LocalDate lastUpdated;

    //date formatter    
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public MedicalProduct() {
    }

    public MedicalProduct(int id, String name, int quantity, int buyingCost, int sellingCost, int discountPercentge, int minProfitPercentage, LocalDate createdDate, LocalDate expiryDate, LocalDate lastUpdated) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.buyingCost = buyingCost;
        this.sellingCost = sellingCost;
        this.discountPercentge = discountPercentge;
        this.minProfitPercentage = minProfitPercentage;
        this.createdDate = createdDate;
        this.expiryDate = expiryDate;
        this.lastUpdated = lastUpdated;
    }

    
    //for reasons
    public MedicalProduct(int id, String name, int quantity, int buyingCost, int sellingCost, int discountPercentge, int minProfitPercentage) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.buyingCost = buyingCost;
        this.sellingCost = sellingCost;
        this.discountPercentge = discountPercentge;
        this.minProfitPercentage = minProfitPercentage;
    }

    public MedicalProduct(int id, String name, int quantity, int buyingCost, int sellingCost, int discountPercentge, LocalDate createdDate, LocalDate expiryDate, LocalDate lastUpdated) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.buyingCost = buyingCost;
        this.sellingCost = sellingCost;
        this.discountPercentge = discountPercentge;
        this.createdDate = LocalDate.now();
        this.expiryDate = expiryDate;
        this.lastUpdated = LocalDate.now();
    }

    public MedicalProduct(int id, String name, int quantity, int buyingCost, int sellingCost, LocalDate createdDate, LocalDate expiryDate, LocalDate lastUpdated) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.buyingCost = buyingCost;
        this.sellingCost = sellingCost;
        this.discountPercentge = 0;
        this.createdDate = LocalDate.now();
        this.expiryDate = expiryDate;
        this.lastUpdated = LocalDate.now();
    }
    
    //insert product info to database
    public MedicalProduct(String name, int quantity, int buyingCost, int sellingCost, LocalDate expiryDate, int discountPercentge, int minProfitPercentage) {
        this.name = name;
        this.quantity = quantity;
        this.buyingCost = buyingCost;
        this.sellingCost = sellingCost;
        this.discountPercentge = discountPercentge;
        this.minProfitPercentage = minProfitPercentage;
        this.createdDate = LocalDate.now();
        this.lastUpdated = LocalDate.now();
        this.expiryDate = expiryDate;
    }
    
    //for update
     public MedicalProduct(String name, int quantity, int buyingCost, int sellingCost, LocalDate expiryDate, int minProfitPercentage) {
        this.name = name;
        this.quantity = quantity;
        this.buyingCost = buyingCost;
        this.sellingCost = sellingCost;
        this.discountPercentge = 0; //just for my sake
        this.minProfitPercentage = minProfitPercentage;
        this.lastUpdated = LocalDate.now();
        this.expiryDate = expiryDate;
    }

    //for billing
    public MedicalProduct(String name, int quantity, int sellingCost, int discountPercentge) {
        this.name = name;
        this.quantity = quantity;
        this.sellingCost = sellingCost;
        this.discountPercentge = discountPercentge;
    }
    
    //for presciption
    public MedicalProduct(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    //for search
    public MedicalProduct(int id, String name) {
        this.id = id;
        this.name = name;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getBuyingCost() {
        return buyingCost;
    }

    public void setBuyingCost(int buyingCost) {
        this.buyingCost = buyingCost;
    }

    public int getSellingCost() {
        return sellingCost;
    }

    public void setSellingCost(int sellingCost) {
        this.sellingCost = sellingCost;
    }

    public int getDiscountPercentge() {
        return discountPercentge;
    }

    public void setDiscountPercentge(int discountPercentge) {
        this.discountPercentge = discountPercentge;
    }

    public int getMinProfitPercentage() {
        return minProfitPercentage;
    }

    public void setMinProfitPercentage(int minProfitPercentage) {
        this.minProfitPercentage = minProfitPercentage;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    //when you need json data of department
    @Override
    public String toString() {
        return "MedicalProduct{" + "id=" + id 
                + ", name=" + name 
                + ", quantity=" + quantity 
                + ", buyingCost=" + buyingCost 
                + ", sellingCost=" + sellingCost 
                + ", discountPercentge=" + discountPercentge 
                + ", minProfitPercentage=" + minProfitPercentage 
                + ", createdDate=" + createdDate 
                + ", expiryDate=" + expiryDate 
                + ", lastUpdated=" + lastUpdated 
                + ", dateFormat=" + dateFormat 
                + '}';
    }

}
