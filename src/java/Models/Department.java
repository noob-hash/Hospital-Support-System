/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Subin
 */
public class Department {
    
    private int id;
    private String name;
    private String contact;
    private String detail;

    public Department() {
    }

    public Department(int id, String name, String contact, String detail) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.detail = detail;
    }

    //to get department info
    public Department(String name, String contact, String detail) {
        this.name = name;
        this.contact = contact;
        this.detail = detail;

    }


    
    //for Patients maybe just to check available department
    //sort data to see active ones in database query
    public Department(String name) {
        this.name = name;
    }

    //for guest
    public Department(int id, String name) {
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    
    
}
