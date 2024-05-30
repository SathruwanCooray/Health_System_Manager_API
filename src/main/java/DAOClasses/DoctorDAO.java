/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOClasses;

import Application.DatabaseClass;
import JavaClasses.Doctor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author sathru
 */
public class DoctorDAO {
    // Logger for logging messages
    private static final Logger LOGGER = Logger.getLogger(DoctorDAO.class.getName());

    // Static map to store doctors
    private static Map<Integer, Doctor> doctors = DatabaseClass.getDoctors();

    // Variable to track the ID number
    static int idNumber = DatabaseClass.getDoctors().size();
    
    // Method to fetch all doctors
    public List<Doctor> getAllDoctors(){
        LOGGER.log(Level.INFO, "Fetching all doctors");
        return new ArrayList<>(doctors.values());
    }
    
    // Method to fetch a doctor by ID
    public Doctor getDoctorById(int doctorId) {
        LOGGER.log(Level.INFO, "Fetching doctor with ID: {0}", doctorId);
        for (Doctor doctor : doctors.values()) {
            if (doctor.getId() == doctorId) {
                return doctor;
            }
        }
        LOGGER.log(Level.WARNING, "Doctor with ID {0} not found", doctorId);
        return null; 
    }
    
    // Method to add a new doctor
    public Doctor addDoctor(Doctor doctor){
        LOGGER.log(Level.INFO, "Adding new doctor");
        int idNumber = doctors.size() + 1;
        doctor.setId(idNumber);
        doctors.put(doctor.getId(), doctor);
        return doctor;
    }
    
    // Method to delete a doctor
    public Doctor deleteDoctor(int doctorId){
        LOGGER.log(Level.INFO, "Deleting doctor with ID: {0}", doctorId);
        return doctors.remove(doctorId);
    }
    
    // Method to update a doctor
    public Doctor updateDoctor(Doctor doctor){
        LOGGER.log(Level.INFO, "Updating doctor with ID: {0}", doctor.getId());
        if(doctor.getId() <= 0){
            LOGGER.log(Level.WARNING, "Invalid doctor object or ID");
            return null;
        } else {
            doctors.put(doctor.getId(), doctor);
            return doctor;
        }
    }
}
