/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application;
import ResourceClasses.AppointmentResource;
import ResourceClasses.BillingResource;
import ResourceClasses.DoctorResource;
import ResourceClasses.MedicalRecordResource;
import ResourceClasses.PrescriptionResource;
import ResourceClasses.PatientResource;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author sathru
 */
import org.glassfish.jersey.server.ResourceConfig;

public class MyApplicationConfig extends ResourceConfig {
    // Constructor to configure the application by registering resource classes.
    public MyApplicationConfig() {
        // Register resource classes for doctors, patients, appointments, medical records,prescriptions, and billing.
        register(DoctorResource.class);
        register(PatientResource.class);
        register(AppointmentResource.class);
        register(MedicalRecordResource.class);
        register(PrescriptionResource.class);
        register(BillingResource.class);
    }
}