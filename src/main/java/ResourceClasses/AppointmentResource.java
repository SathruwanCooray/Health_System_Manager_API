/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ResourceClasses;

import DAOClasses.AppointmentDAO;
import JavaClasses.Appointment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author sathru
 */
@Path("/appointments")
public class AppointmentResource {
    // Logger for logging messages
    private static final Logger LOGGER = Logger.getLogger(AppointmentResource.class.getName());

    // Appointment Data Access Object
    private AppointmentDAO appointmentDAO = new AppointmentDAO();

    // Method to fetch all appointments
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllAppointments() {
        LOGGER.log(Level.INFO, "Fetching all appointments");
        List<Appointment> appointmentList = appointmentDAO.getAllAppointments();
        try {
            return new ObjectMapper().writeValueAsString(appointmentList);
        } catch (JsonProcessingException e) {
            LOGGER.log(Level.SEVERE, "Error occurred while processing JSON", e);
            return "Error occurred while processing JSON.";
        }
    }
    
    // Method to fetch appointments by doctor ID
    @GET
    @Path("doctor/{doctorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAppointmentsByDoctorId(@PathParam("doctorId") int doctorId) {
        LOGGER.log(Level.INFO, "Fetching appointments for doctor with ID: {0}", doctorId);
        List<Appointment> appointmentList = appointmentDAO.getAppointmentsByDoctor(doctorId);
        try {
            return new ObjectMapper().writeValueAsString(appointmentList);
        } catch (JsonProcessingException e) {
            LOGGER.log(Level.SEVERE, "Error occurred while processing JSON", e);
            return "Error occurred while processing JSON.";
        }
    }
    
    // Method to fetch appointment by appointment ID
    @GET
    @Path("/{appointmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAppointmentById(@PathParam("appointmentId") int appointmentId) {
        LOGGER.log(Level.INFO, "Fetching appointment with ID: {0}", appointmentId);
        Appointment appointment = appointmentDAO.getAppointmentById(appointmentId);
        try {
            if (appointment != null) {
                return new ObjectMapper().writeValueAsString(appointment);
            } else {
                LOGGER.log(Level.WARNING, "Appointment with ID {0} not found", appointmentId);
                return "Appointment with ID " + appointmentId + " not found.";
            }
        } catch (JsonProcessingException e) {
            LOGGER.log(Level.SEVERE, "Error occurred while processing JSON", e);
            return "Error occurred while processing JSON.";
        }
    }
    
    // Method to fetch appointments by patient ID
    @GET
    @Path("patient/{patientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAppointsByPatientId(@PathParam("patientId") int patientId) {
        LOGGER.log(Level.INFO, "Fetching appointments for patient with ID: {0}", patientId);
        List<Appointment> appointmentList = appointmentDAO.getAppointmentsByPatient(patientId);
        try {
            return new ObjectMapper().writeValueAsString(appointmentList);
        } catch (JsonProcessingException e) {
            LOGGER.log(Level.SEVERE, "Error occurred while processing JSON", e);
            return "Error occurred while processing JSON.";
        }
    }

    // Method to add a new appointment
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addAppointment(String appointmentJson) {
        LOGGER.log(Level.INFO, "Adding new appointment");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Appointment appointment = objectMapper.readValue(appointmentJson, Appointment.class);
            appointmentDAO.addAppointment(appointment);

            return objectMapper.writeValueAsString(appointment);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred while adding appointment", e);
            return "Error occurred while adding appointment: " + e.getMessage();
        }
    }

    // Method to delete an appointment
    @DELETE
    @Path("/{appointmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteAppointment(@PathParam("appointmentId") int appointmentId) {
        LOGGER.log(Level.INFO, "Deleting appointment with ID: {0}", appointmentId);
        appointmentDAO.deleteAppointment(appointmentId);
    }

    // Method to update an appointment
    @PUT
    @Path("/{appointmentId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateAppointment(@PathParam("appointmentId") int appointmentId, String appointmentJson) {
        LOGGER.log(Level.INFO, "Updating appointment with ID: {0}", appointmentId);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Appointment appointment = objectMapper.readValue(appointmentJson, Appointment.class);
            appointment.setId(appointmentId);
            appointmentDAO.updateAppointment(appointment);

            return objectMapper.writeValueAsString(appointment);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred while updating appointment", e);
            return "Error occurred while updating appointment: " + e.getMessage();
        }
    }
}
