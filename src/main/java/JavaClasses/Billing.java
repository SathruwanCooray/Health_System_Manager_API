/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JavaClasses;

/**
 *
 * @author sathru
 */
public class Billing {
    // Attributes of a billing record
    private int id;
    private Patient patient; 
    private double totalAmount;
    private String billingDate; 
    private boolean paid; 

    // Constructor with parameters to initialize a billing record
    public Billing(int id, Patient patient, double totalAmount, String billingDate, boolean paid) {
        this.id = id;
        this.patient = patient;
        this.totalAmount = totalAmount;
        this.billingDate = billingDate;
        this.paid = paid;
    }

    // Default constructor
    public Billing() {
    }
    
    // Getter and setter methods for each attribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(String billingDate) {
        this.billingDate = billingDate;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}