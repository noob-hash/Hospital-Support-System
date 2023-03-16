/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Subin
 */
public class TransactionTracker {
    private int transactionId;
    private String transactionType; //bought or sold or returned
    //can be service transaction on medicalProduct transaction or both
    private int foreignId;

    public TransactionTracker() {
    }

    //insert transaction data to database
    public TransactionTracker(int transactionId, String transactionType,int foreignId) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.foreignId = foreignId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
    
    public int getForeignId() {
        return foreignId;
    }

    public void setForeignId(int foreignId) {
        this.foreignId = foreignId;
    }

    //if you need data in json format
    @Override
    public String toString() {
        return "TransactionTracker{" + "transactionId=" + transactionId 
                + ", transactionType=" + transactionType 
                + ", foreignId=" + foreignId 
                + '}';
    }
  
}
