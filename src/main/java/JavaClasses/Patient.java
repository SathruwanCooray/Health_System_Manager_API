/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JavaClasses;

/**
 *
 * @author sathru
 */
public class Patient extends Person {
    // Current health status of the patient
    private String currentHealthStatus;
    // Medical record of the patient
    private MedicalRecord medicalRecord;

    // Constructor with parameters to initialize a patient
    public Patient(int id, String name, String contactInfo, String address, String currentHealthStatus, MedicalRecord medicalRecord) {
        super(id, name, contactInfo, address);
        this.currentHealthStatus = currentHealthStatus;
        this.medicalRecord = medicalRecord;
    }

    // Default constructor
    public Patient() {
    }

    // Getter and setter for the current health status
    public String getCurrentHealthStatus() {
        return currentHealthStatus;
    }

    public void setCurrentHealthStatus(String currentHealthStatus) {
        this.currentHealthStatus = currentHealthStatus;
    }

    // Getter and setter for the medical record
    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }
}