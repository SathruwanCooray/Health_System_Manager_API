/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOClasses;
import Application.DatabaseClass;
import JavaClasses.Billing;
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
public class BillingDAO {
    // Logger for logging messages
    private static final Logger LOGGER = Logger.getLogger(BillingDAO.class.getName());

    // Static maps to store billings, patients, and medical records
    private static Map<Integer, Billing> billings = DatabaseClass.getBilling();
    private static Map<Integer, Patient> patients = DatabaseClass.getPatients();
    private static Map<Integer, MedicalRecord> medicalRecords = DatabaseClass.getMedicalRecords();

    // Variable to track the identity number
    private static int identityNumber = 0;

    // Method to fetch all billings
    public List<Billing> getAllBillings() {
        return new ArrayList<>(billings.values());
    }

    // Method to fetch a billing by ID
    public Billing getBillingById(int billingId) {
        return billings.get(billingId);
    }

    // Method to add a new billing
    public Billing addBilling(Billing billing) {
        LOGGER.log(Level.INFO, "Adding billing for patient: {0}", billing.getPatient().getName());
        try {
            // Logic to handle patient
            Patient patient = billing.getPatient();
            if (patients.containsKey(patient.getId())) {
                Patient existingPatient = patients.get(patient.getId());
                billing.setPatient(existingPatient);
            } else {
                throw new IllegalArgumentException("Patient with ID " + patient.getId() + " does not exist.");
            }

            // Increment identityNumber and set ID for the billing
            identityNumber++;
            billing.setId(identityNumber);
            billings.put(billing.getId(), billing);
            return billing;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error adding billing", e);
            return null;
        }
    }

    // Method to delete a billing
    public void deleteBilling(int billingId) {
        billings.remove(billingId);
    }

    // Method to update a billing
    public void updateBilling(Billing updatedBilling) {
        billings.put(updatedBilling.getId(), updatedBilling);
    }
}
