/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application;
import JavaClasses.Appointment;
import JavaClasses.Billing;
import JavaClasses.Doctor;
import JavaClasses.MedicalRecord;
import JavaClasses.Patient;
import JavaClasses.Prescription;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author sathru
 */

public class DatabaseClass {
    // Maps to store appointments, patients, doctors, medical records, prescriptions, and billing information.
    private static Map<Integer, Appointment> appointments = new HashMap<>();
    private static Map<Integer, Patient> patients = new HashMap<>();
    private static Map<Integer, Doctor> doctors = new HashMap<>();
    private static Map<Integer, MedicalRecord> medicalRecords = new HashMap<>();
    private static Map<Integer, Prescription> prescriptions = new HashMap<>();
    private static Map<Integer, Billing> billing = new HashMap<>();

    // Getters for accessing the stored information.

    public static Map<Integer, Appointment> getAppointments() {
        return appointments;
    }

    public static Map<Integer, Patient> getPatients() {
        return patients;
    }

    public static Map<Integer, Doctor> getDoctors() {
        return doctors;
    }

    public static Map<Integer, MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

    public static Map<Integer, Billing> getBilling() {
        return billing;
    }

    public static Map<Integer, Prescription> getPrescriptions() {
        return prescriptions;
    }
}