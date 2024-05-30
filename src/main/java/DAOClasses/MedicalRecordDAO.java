/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOClasses;

import Application.DatabaseClass;
import JavaClasses.MedicalRecord;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sathru
 */
public class MedicalRecordDAO {
    // Logger for logging messages
    private static final Logger LOGGER = Logger.getLogger(MedicalRecordDAO.class.getName());

    // Static map to store medical records
    private static Map<Integer, MedicalRecord> medicalRecords = DatabaseClass.getMedicalRecords();

    // Variable to track the identity number
    private static int identityNumber = DatabaseClass.getMedicalRecords().size();

    // Method to fetch all medical records
    public List<MedicalRecord> getAllMedicalRecords() {
        LOGGER.log(Level.INFO, "Fetching all medical records");
        return new ArrayList<>(medicalRecords.values());
    }

    // Method to fetch a medical record by ID
    public MedicalRecord getMedicalRecordById(int medicalRecordId) {
        LOGGER.log(Level.INFO, "Fetching medical record with ID: {0}", medicalRecordId);
        return medicalRecords.get(medicalRecordId);
    }

    // Method to add a new medical record
    public void addMedicalRecord(MedicalRecord medicalRecord) {
        LOGGER.log(Level.INFO, "Adding new medical record");
        // Increment identityNumber and set ID for the medical record
        identityNumber++;
        medicalRecord.setId(identityNumber);
        medicalRecords.put(medicalRecord.getId(), medicalRecord);
    }

    // Method to delete a medical record
    public void deleteMedicalRecord(int medicalRecordId) {
        LOGGER.log(Level.INFO, "Deleting medical record with ID: {0}", medicalRecordId);
        medicalRecords.remove(medicalRecordId);
    }

    // Method to update a medical record
    public void updateMedicalRecord(MedicalRecord updatedMedicalRecord) {
        LOGGER.log(Level.INFO, "Updating medical record with ID: {0}", updatedMedicalRecord.getId());
        medicalRecords.put(updatedMedicalRecord.getId(), updatedMedicalRecord);
    }
}
