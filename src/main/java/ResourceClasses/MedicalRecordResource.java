/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ResourceClasses;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import DAOClasses.MedicalRecordDAO;
import JavaClasses.MedicalRecord;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sathru
 */
@Path("/medicalrecords")
public class MedicalRecordResource {
    // Logger for logging messages
    private static final Logger LOGGER = Logger.getLogger(MedicalRecordResource.class.getName());
    
    // MedicalRecord Data Access Object
    private MedicalRecordDAO medicalRecordDAO = new MedicalRecordDAO();

    // Method to fetch all medical records
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllMedicalRecords() {
        LOGGER.log(Level.INFO, "Fetching all medical records");
        List<MedicalRecord> medicalRecordList = medicalRecordDAO.getAllMedicalRecords();
        try {
            return new ObjectMapper().writeValueAsString(medicalRecordList);
        } catch (JsonProcessingException e) {
            LOGGER.log(Level.SEVERE, "Error occurred while processing JSON.", e);
            return "Error occurred while processing JSON.";
        }
    }

    // Method to fetch medical record by medical record ID
    @GET
    @Path("/{medicalRecordId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMedicalRecordById(@PathParam("medicalRecordId") int medicalRecordId) {
        LOGGER.log(Level.INFO, "Fetching medical record with ID: {0}", medicalRecordId);
        MedicalRecord medicalRecord = medicalRecordDAO.getMedicalRecordById(medicalRecordId);
        try {
            if (medicalRecord != null) {
                return new ObjectMapper().writeValueAsString(medicalRecord);
            } else {
                return "Medical record with ID " + medicalRecordId + " not found.";
            }
        } catch (JsonProcessingException e) {
            LOGGER.log(Level.SEVERE, "Error occurred while processing JSON.", e);
            return "Error occurred while processing JSON.";
        }
    }

    // Method to add a new medical record
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addMedicalRecord(String medicalRecordJson) {
        LOGGER.log(Level.INFO, "Adding new medical record");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            MedicalRecord medicalRecord = objectMapper.readValue(medicalRecordJson, MedicalRecord.class);
            medicalRecordDAO.addMedicalRecord(medicalRecord);

            return objectMapper.writeValueAsString(medicalRecord);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred while adding medical record.", e);
            return "Error occurred while adding medical record: " + e.getMessage();
        }
    }

    // Method to delete a medical record
    @DELETE
    @Path("/{medicalRecordId}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteMedicalRecord(@PathParam("medicalRecordId") int medicalRecordId) {
        LOGGER.log(Level.INFO, "Deleting medical record with ID: {0}", medicalRecordId);
        medicalRecordDAO.deleteMedicalRecord(medicalRecordId);
    }

    // Method to update a medical record
    @PUT
    @Path("/{medicalRecordId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateMedicalRecord(@PathParam("medicalRecordId") int medicalRecordId, String medicalRecordJson) {
        LOGGER.log(Level.INFO, "Updating medical record with ID: {0}", medicalRecordId);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            MedicalRecord medicalRecord = objectMapper.readValue(medicalRecordJson, MedicalRecord.class);
            medicalRecord.setId(medicalRecordId);
            medicalRecordDAO.updateMedicalRecord(medicalRecord);

            return objectMapper.writeValueAsString(medicalRecord);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred while updating medical record.", e);
            return "Error occurred while updating medical record: " + e.getMessage();
        }
    }
}
