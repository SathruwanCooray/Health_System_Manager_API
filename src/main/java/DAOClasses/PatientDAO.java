/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOClasses;

import Application.DatabaseClass;
import JavaClasses.MedicalRecord;
import JavaClasses.Patient;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sathru
 */
public class PatientDAO {
    // Logger for logging messages
    private static final Logger LOGGER = Logger.getLogger(PatientDAO.class.getName());
    
    // Static maps to store patients and medical records
    private static Map<Integer, Patient> patients = DatabaseClass.getPatients();
    private static Map<Integer, MedicalRecord> medicalRecords = DatabaseClass.getMedicalRecords();
    
    // Variable to track the ID number
    private static int idNumber = DatabaseClass.getPatients().size();

    // Method to fetch all patients
    public List<Patient> getAllPatients() {
        LOGGER.log(Level.INFO, "Fetching all patients from the database");
        return new ArrayList<>(patients.values());
    }

    // Method to fetch a patient by ID
    public Patient getPatientById(int patientId) {
        LOGGER.log(Level.INFO, "Fetching patient with ID: {0} from the database", patientId);
        return patients.get(patientId);
    }

    // Method to add a new patient
    public Patient addPatient(Patient patient) {
        LOGGER.log(Level.INFO, "Adding patient: {0} to the database", patient.getName());
        
        // Logic to handle medical record
        Integer medicalRecordId = patient.getMedicalRecord().getId();
        if (medicalRecordId == 0) {
            Integer newMedicalRecordId = medicalRecords.size() + 1;
            patient.getMedicalRecord().setId(newMedicalRecordId);
            DatabaseClass.getMedicalRecords().put(newMedicalRecordId, patient.getMedicalRecord());
        } else {
            MedicalRecord existingMedicalRecord = medicalRecords.get(medicalRecordId);
            if (existingMedicalRecord != null) {
                patient.setMedicalRecord(existingMedicalRecord);
            } else {
                LOGGER.log(Level.WARNING, "Medical record with ID {0} does not exist", medicalRecordId);
                return null;
            }
        }

        // Increment idNumber and set ID for the patient
        idNumber++;
        patient.setId(idNumber);
        patients.put(patient.getId(), patient);
        return patient;
    }

    // Method to delete a patient
    public Patient deletePatient(int patientId) {
        LOGGER.log(Level.INFO, "Deleting patient with ID: {0} from the database", patientId);
        return patients.remove(patientId);
    }

    // Method to update a patient
    public Patient updatePatient(Patient patient) {
        // Check for invalid patient object or ID
        if (patient == null || patient.getId() <= 0) {
            LOGGER.log(Level.WARNING, "Invalid patient object or ID");
            return null;
        } else {
            LOGGER.log(Level.INFO, "Updating patient with ID: {0}", patient.getId());
            patients.put(patient.getId(), patient);
            return patient;
        }
    }
}
