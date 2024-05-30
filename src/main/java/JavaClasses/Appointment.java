/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JavaClasses;

/**
 *
 * @author sathru
 */
public class Appointment {
    // Attributes of an appointment
    private int id;
    private String date;
    private String time;
    private String associatedParticipants;
    private Patient patient;
    private Doctor doctor;

    // Constructor with parameters to initialize an appointment
    public Appointment(int id, Patient patient, Doctor doctor, String date, String time, String associatedParticipants) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.time = time;
        this.associatedParticipants = associatedParticipants;
    }
    
    // Default constructor
    public Appointment() {
    }

    // Getter and setter methods for each attribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAssociatedParticipants() {
        return associatedParticipants;
    }

    public void setAssociatedParticipants(String associatedParticipants) {
        this.associatedParticipants = associatedParticipants;
    }
}