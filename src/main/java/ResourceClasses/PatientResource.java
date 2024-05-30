/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ResourceClasses;


import DAOClasses.PatientDAO;
import JavaClasses.Patient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author sathru
 */
@Path("/patients")
public class PatientResource {
    // Logger for logging messages
    private static final Logger LOGGER = Logger.getLogger(PatientResource.class.getName());
    
    // Patient Data Access Object
    private PatientDAO patientDAO = new PatientDAO();

    // Method to fetch patient by ID
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{patientId}")
    public String getPatientById(@PathParam("patientId") int patientId) {
        LOGGER.log(Level.INFO, "Fetching patient by ID: {0}", patientId);
        Patient patient = patientDAO.getPatientById(patientId);
        if (patient != null) {
            try {
                return new ObjectMapper().writeValueAsString(patient);
            } catch (JsonProcessingException e) {
                LOGGER.log(Level.SEVERE, "Error occurred while processing JSON: {0}", e.getMessage());
                return "Error occurred while processing JSON.";
            }
        } else {
            LOGGER.log(Level.WARNING, "Patient with ID {0} not found", patientId);
            return "Patient with ID " + patientId + " not found.";
        }
    }

    // Method to fetch all patients
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllPatients() {
        LOGGER.log(Level.INFO, "Fetching all patients");
        List<Patient> patientList = patientDAO.getAllPatients();
        try {
            return new ObjectMapper().writeValueAsString(patientList);
        } catch (JsonProcessingException e) {
            LOGGER.log(Level.SEVERE, "Error occurred while processing JSON: {0}", e.getMessage());
            return "Error occurred while processing JSON.";
        }
    }

    // Method to add a new patient
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addPatient(String patientJson) {
        try {
            LOGGER.log(Level.INFO, "Adding new patient");
            ObjectMapper objectMapper = new ObjectMapper();
            Patient patient = objectMapper.readValue(patientJson, Patient.class);
            patientDAO.addPatient(patient);

            return objectMapper.writeValueAsString(patient);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred while adding patient: {0}", e.getMessage());
            e.printStackTrace();
            return "Error occurred while adding patient: " + e.getMessage();
        }
    }

    // Method to delete a patient
    @DELETE
    @Path("/{patientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deletePatient(@PathParam("patientId") int patientId) {
        LOGGER.log(Level.INFO, "Deleting patient with ID: {0}", patientId);
        patientDAO.deletePatient(patientId);
    }

    // Method to update a patient
    @PUT
    @Path("/{patientId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String updatePatient(@PathParam("patientId") int patientId, String patientJson) {
        try {
            LOGGER.log(Level.INFO, "Updating patient with ID: {0}", patientId);
            ObjectMapper objectMapper = new ObjectMapper();
            Patient patient = objectMapper.readValue(patientJson, Patient.class);
            patient.setId(patientId);
            patientDAO.updatePatient(patient);

            return objectMapper.writeValueAsString(patient);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred while updating patient: {0}", e.getMessage());
            e.printStackTrace();
            return "Error occurred while updating patient: " + e.getMessage();
        }
    }
}
