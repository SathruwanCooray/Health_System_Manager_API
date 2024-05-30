/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOClasses;

import Application.DatabaseClass;
import JavaClasses.Appointment;
import JavaClasses.Doctor;
import JavaClasses.MedicalRecord;
import JavaClasses.Patient;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;;

/**
 *
 * @author sathru
 */
public class AppointmentDAO {
    // Logger for logging messages
    private static final Logger LOGGER = Logger.getLogger(AppointmentDAO.class.getName());

    // Static maps to store appointments, patients, doctors, and medical records
    private static Map<Integer, Appointment> appointments = DatabaseClass.getAppointments();
    private static Map<Integer, Patient> patients = DatabaseClass.getPatients();
    private static Map<Integer, Doctor> doctors = DatabaseClass.getDoctors();
    private static Map<Integer, MedicalRecord> medicalRecords = DatabaseClass.getMedicalRecords();

    // Variable to track the ID number
    private static int idNumber = DatabaseClass.getAppointments().size();

    // Method to fetch all appointments
    public List<Appointment> getAllAppointments() {
        LOGGER.log(Level.INFO, "Fetching all appointments");
        return new ArrayList<>(appointments.values());
    }

    // Method to fetch an appointment by ID
    public Appointment getAppointmentById(int appointmentId) {
        LOGGER.log(Level.INFO, "Fetching appointment with ID: {0}", appointmentId);
        return appointments.get(appointmentId);
    }

    // Method to fetch appointments by patient ID
    public List<Appointment> getAppointmentsByPatient(int patientId) {
        LOGGER.log(Level.INFO, "Fetching appointments for patient with ID: {0}", patientId);
        List<Appointment> patientAppointments = new ArrayList<>();
        for (Appointment appointment : appointments.values()) {
            if (appointment.getPatient().getId() == patientId) {
                patientAppointments.add(appointment);
            }
        }
        return patientAppointments;
    }

    // Method to fetch appointments by doctor ID
    public List<Appointment> getAppointmentsByDoctor(int doctorId) {
        LOGGER.log(Level.INFO, "Fetching appointments for doctor with ID: {0}", doctorId);
        List<Appointment> doctorAppointments = new ArrayList<>();
        for (Appointment appointment : appointments.values()) {
            if (appointment.getDoctor().getId() == doctorId) {
                doctorAppointments.add(appointment);
            }
        }
        return doctorAppointments;
    }

    // Method to add a new appointment
    public Appointment addAppointment(Appointment appointment) {
        LOGGER.log(Level.INFO, "Adding new appointment");
        // Logic to handle patient, medical record, and doctor
        // Increment idNumber and set ID for the appointment
        idNumber++;
        appointment.setId(idNumber);
        // Add the appointment to the appointments map
        appointments.put(appointment.getId(), appointment);
        return appointment;
    }

    // Method to delete an appointment
    public void deleteAppointment(int appointmentId) {
        LOGGER.log(Level.INFO, "Deleting appointment with ID: {0}", appointmentId);
        appointments.remove(appointmentId);
    }

    // Method to update an appointment
    public void updateAppointment(Appointment updatedAppointment) {
        LOGGER.log(Level.INFO, "Updating appointment with ID: {0}", updatedAppointment.getId());
        appointments.put(updatedAppointment.getId(), updatedAppointment);
    }
}
