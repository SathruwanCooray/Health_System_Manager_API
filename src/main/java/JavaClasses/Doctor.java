/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JavaClasses;

/**
 *
 * @author sathru
 */
public class Doctor extends Person {
    // Specialization of the doctor
    private String specialization;

    // Constructor with parameters to initialize a doctor
    public Doctor(int id, String name, String contactInfo, String address, String specialization) {
        super(id, name, contactInfo, address);
        this.specialization = specialization;
    }

    // Default constructor
    public Doctor() {
    }
    
    // Getter and setter for the specialization
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
