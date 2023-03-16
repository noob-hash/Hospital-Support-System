/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.time.LocalDateTime;

/**
 *
 * @author Subin
 */
public class Transaction {
    private int id;
    private String operator;
    private int quantity;
    private LocalDateTime transactionDateTime;

    public Transaction() {
    }

    public Transaction(int id, String operator, int quantity, LocalDateTime dateTime) {
        this.id = id;
        this.operator = operator;
        this.quantity = quantity;
        this.transactionDateTime = dateTime;
    }

    //to insert transaction data to database
    public Transaction(String operator, int quantity, LocalDateTime transactionDateTime) {
        this.operator = operator;
        this.quantity = quantity;
        this.transactionDateTime = LocalDateTime.now();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getDateTime() {
        return transactionDateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.transactionDateTime = dateTime;
    }

    //when you need json data of trnsaction
    @Override
    public String toString() {
        return "Transaction{" + "id=" + id 
                + ", operator=" + operator 
                + ", quantity=" + quantity 
                + ", transactionDateTime=" + transactionDateTime 
                + '}';
    }
    
}
