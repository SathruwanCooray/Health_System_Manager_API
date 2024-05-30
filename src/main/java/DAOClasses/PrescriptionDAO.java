/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOClasses;

import Application.DatabaseClass;
import JavaClasses.MedicalRecord;
import JavaClasses.Patient;
import JavaClasses.Prescription;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author sathru
 */
public class PrescriptionDAO {
    // Logger for logging messages
    private static final Logger LOGGER = Logger.getLogger(PrescriptionDAO.class.getName());

    // Static maps to store prescriptions, patients, and medical records
    private static Map<Integer, Prescription> prescriptions = new HashMap<>();
    private static Map<Integer, Patient> patients = DatabaseClass.getPatients();
    private static Map<Integer, MedicalRecord> medicalRecords = DatabaseClass.getMedicalRecords();

    // Variable to track the ID number
    private static int idNumber = DatabaseClass.getPrescriptions().size();

    // Method to fetch all prescriptions
    public List<Prescription> getAllPrescriptions() {
        LOGGER.log(Level.INFO, "Fetching all prescriptions");
        return new ArrayList<>(prescriptions.values());
    }

    // Method to fetch a prescription by ID
    public Prescription getPrescriptionById(int prescriptionId) {
        LOGGER.log(Level.INFO, "Fetching prescription with ID: {0}", prescriptionId);
        return prescriptions.get(prescriptionId);
    }

    // Method to add a new prescription
    public Prescription addPrescription(Prescription prescription) {
        LOGGER.log(Level.INFO, "Adding new prescription");
        Patient patient = prescription.getPatient();

        // Logic to handle patient
        if (patient.getId() == 0) {
            Integer newPatientId = patients.size() + 1;
            patient.setId(newPatientId);
            DatabaseClass.getPatients().put(newPatientId, patient);
        } else {
            if (patients.containsKey(patient.getId())) {
                Patient existingPatient = patients.get(patient.getId());
                prescription.setPatient(existingPatient);
                patient.setMedicalRecord(existingPatient.getMedicalRecord());
            } else {
                LOGGER.log(Level.WARNING, "Patient with ID {0} not found", patient.getId());
                return null;
            }
        }

        // Logic to handle medical record
        MedicalRecord medicalRecord = patient.getMedicalRecord();
        if (medicalRecord.getId() == 0) {
            Integer newMedicalRecordId = medicalRecords.size() + 1;
            medicalRecord.setId(newMedicalRecordId);
            DatabaseClass.getMedicalRecords().put(newMedicalRecordId, medicalRecord);
        } else {
            if (medicalRecords.containsKey(medicalRecord.getId())) {
                MedicalRecord existingMedicalRecord = medicalRecords.get(medicalRecord.getId());
                patient.setMedicalRecord(existingMedicalRecord);
            } else {
                LOGGER.log(Level.WARNING, "Medical record with ID {0} not found", medicalRecord.getId());
                return null;
            }
        }

        // Increment idNumber and set ID for the prescription
        idNumber++;
        prescription.setId(idNumber);
        prescriptions.put(prescription.getId(), prescription);
        return prescription;
    }

    // Method to update a prescription
    public Prescription updatePrescription(Prescription prescription) {
        LOGGER.log(Level.INFO, "Updating prescription with ID: {0}", prescription.getId());
        if (prescriptions.containsKey(prescription.getId())) {
            prescriptions.put(prescription.getId(), prescription);
            return prescription;
        } else {
            LOGGER.log(Level.WARNING, "Prescription with ID {0} not found", prescription.getId());
            return null;
        }
    }

    // Method to delete a prescription
    public Prescription deletePrescription(int prescriptionId) {
        LOGGER.log(Level.INFO, "Deleting prescription with ID: {0}", prescriptionId);
        return prescriptions.remove(prescriptionId);
    }
}
