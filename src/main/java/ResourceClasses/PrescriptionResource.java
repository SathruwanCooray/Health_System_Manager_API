/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ResourceClasses;

import DAOClasses.PrescriptionDAO;
import JavaClasses.Prescription;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sathru
 */
@Path("/prescriptions")
public class PrescriptionResource {
    // Prescription Data Access Object
    private PrescriptionDAO prescriptionDAO = new PrescriptionDAO();
    
    // Logger for logging messages
    private static final Logger LOGGER = Logger.getLogger(PrescriptionResource.class.getName());

    // Method to fetch all prescriptions
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllPrescriptions() {
        LOGGER.log(Level.INFO, "Fetching all prescriptions");
        List<Prescription> prescriptionList = prescriptionDAO.getAllPrescriptions();
        try {
            return new ObjectMapper().writeValueAsString(prescriptionList);
        } catch (JsonProcessingException e) {
            LOGGER.log(Level.SEVERE, "Error occurred while processing JSON.", e);
            return "Error occurred while processing JSON.";
        }
    }

    // Method to fetch prescription by ID
    @GET
    @Path("/{prescriptionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPrescriptionById(@PathParam("prescriptionId") int prescriptionId) {
        LOGGER.log(Level.INFO, "Fetching prescription with ID: {0}", prescriptionId);
        Prescription prescription = prescriptionDAO.getPrescriptionById(prescriptionId);
        try {
            if (prescription != null) {
                return new ObjectMapper().writeValueAsString(prescription);
            } else {
                LOGGER.log(Level.WARNING, "Prescription with ID {0} not found", prescriptionId);
                return "Prescription with ID " + prescriptionId + " not found.";
            }
        } catch (JsonProcessingException e) {
            LOGGER.log(Level.SEVERE, "Error occurred while processing JSON.", e);
            return "Error occurred while processing JSON.";
        }
    }

    // Method to add a new prescription
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addPrescription(String prescriptionJson) {
        LOGGER.log(Level.INFO, "Adding new prescription");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Prescription prescription = objectMapper.readValue(prescriptionJson, Prescription.class);
            prescriptionDAO.addPrescription(prescription);

            return objectMapper.writeValueAsString(prescription);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred while adding prescription", e);
            return "Error occurred while adding prescription: " + e.getMessage();
        }
    }

    // Method to delete a prescription
    @DELETE
    @Path("/{prescriptionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deletePrescription(@PathParam("prescriptionId") int prescriptionId) {
        LOGGER.log(Level.INFO, "Deleting prescription with ID: {0}", prescriptionId);
        prescriptionDAO.deletePrescription(prescriptionId);
    }

    // Method to update a prescription
    @PUT
    @Path("/{prescriptionId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String updatePrescription(@PathParam("prescriptionId") int prescriptionId, String prescriptionJson) {
        LOGGER.log(Level.INFO, "Updating prescription with ID: {0}", prescriptionId);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Prescription prescription = objectMapper.readValue(prescriptionJson, Prescription.class);
            prescription.setId(prescriptionId);
            prescriptionDAO.updatePrescription(prescription);

            return objectMapper.writeValueAsString(prescription);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred while updating prescription", e);
            return "Error occurred while updating prescription: " + e.getMessage();
        }
    }
}
