/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ResourceClasses;

import DAOClasses.DoctorDAO;
import JavaClasses.Doctor;
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
@Path("/doctors")
public class DoctorResource {
    // Logger for logging messages
    private static final Logger LOGGER = Logger.getLogger(DoctorResource.class.getName());

    // Doctor Data Access Object
    private DoctorDAO doctorDAO = new DoctorDAO();

    // Method to fetch doctor by doctor ID
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{doctorId}")
    public String getDoctorById(@PathParam("doctorId") int doctorId) {
        LOGGER.log(Level.INFO, "Fetching doctor with ID: {0}", doctorId);
        Doctor doctor = doctorDAO.getDoctorById(doctorId);
        if (doctor != null) {
            try {
                return new ObjectMapper().writeValueAsString(doctor);
            } catch (JsonProcessingException e) {
                LOGGER.log(Level.SEVERE, "Error occurred while processing JSON", e);
                return "Error occurred while processing JSON.";
            }
        } else {
            LOGGER.log(Level.WARNING, "Doctor with ID {0} not found", doctorId);
            return "Doctor with ID " + doctorId + " not found.";
        }
    }

    // Method to fetch all doctors
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllDoctors() {
        LOGGER.log(Level.INFO, "Fetching all doctors");
        List<Doctor> doctorList = doctorDAO.getAllDoctors();
        try {
            return new ObjectMapper().writeValueAsString(doctorList);
        } catch (JsonProcessingException e) {
            LOGGER.log(Level.SEVERE, "Error occurred while processing JSON", e);
            return "Error occurred while processing JSON.";
        }
    }
    
    // Method to add a new doctor
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addDoctor(String doctorJson) {
        LOGGER.log(Level.INFO, "Adding new doctor");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Doctor doctor = objectMapper.readValue(doctorJson, Doctor.class);
            doctorDAO.addDoctor(doctor);

            return objectMapper.writeValueAsString(doctor);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred while adding doctor", e);
            return "Error occurred while adding doctor: " + e.getMessage();
        }
    }
    
    // Method to delete a doctor
    @DELETE
    @Path("/{doctorId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteDoctor(@PathParam("doctorId") int doctorId) {
        LOGGER.log(Level.INFO, "Deleting doctor with ID: {0}", doctorId);
        doctorDAO.deleteDoctor(doctorId);
    }
    
    // Method to update a doctor
    @PUT
    @Path("/{doctorId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateDoctor(@PathParam("doctorId") int doctorId, String doctorJson) {
        LOGGER.log(Level.INFO, "Updating doctor with ID: {0}", doctorId);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Doctor doctor = objectMapper.readValue(doctorJson, Doctor.class);
            doctor.setId(doctorId);
            doctorDAO.updateDoctor(doctor);

            return objectMapper.writeValueAsString(doctor);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred while updating doctor", e);
            return "Error occurred while updating doctor: " + e.getMessage();
        }
    }
}
