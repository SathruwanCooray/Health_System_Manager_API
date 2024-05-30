/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ResourceClasses;

import DAOClasses.BillingDAO;
import JavaClasses.Billing;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sathru
 */
@Path("/billings")
public class BillingResource {
    // Logger for logging messages
    private static final Logger LOGGER = Logger.getLogger(BillingResource.class.getName());

    // Billing Data Access Object
    private BillingDAO billingDAO = new BillingDAO();

    // Method to fetch all billings
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllBillings() {
        LOGGER.log(Level.INFO, "Fetching all billings");
        try {
            List<Billing> billingList = billingDAO.getAllBillings();
            return new ObjectMapper().writeValueAsString(billingList);
        } catch (JsonProcessingException e) {
            LOGGER.log(Level.SEVERE, "Error occurred while processing JSON", e);
            return "Error occurred while processing JSON.";
        }
    }

    // Method to fetch billing by billing ID
    @GET
    @Path("/{billingId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getBillingById(@PathParam("billingId") int billingId) {
        LOGGER.log(Level.INFO, "Fetching billing with ID: {0}", billingId);
        try {
            Billing billing = billingDAO.getBillingById(billingId);
            if (billing != null) {
                return new ObjectMapper().writeValueAsString(billing);
            } else {
                LOGGER.log(Level.WARNING, "Billing with ID {0} not found", billingId);
                return "Billing with ID " + billingId + " not found.";
            }
        } catch (JsonProcessingException e) {
            LOGGER.log(Level.SEVERE, "Error occurred while processing JSON", e);
            return "Error occurred while processing JSON.";
        }
    }

    // Method to add a new billing
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addBilling(String billingJson) {
        try {
            LOGGER.log(Level.INFO, "Adding new billing");
            ObjectMapper objectMapper = new ObjectMapper();
            Billing billing = objectMapper.readValue(billingJson, Billing.class);
            Billing addedBilling = billingDAO.addBilling(billing);
            return objectMapper.writeValueAsString(addedBilling);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred while adding billing", e);
            return "Error occurred while adding billing: " + e.getMessage();
        }
    }

    // Method to delete a billing
    @DELETE
    @Path("/{billingId}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteBilling(@PathParam("billingId") int billingId) {
        LOGGER.log(Level.INFO, "Deleting billing with ID: {0}", billingId);
        billingDAO.deleteBilling(billingId);
    }

    // Method to update a billing
    @PUT
    @Path("/{billingId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateBilling(@PathParam("billingId") int billingId, String billingJson) {
        LOGGER.log(Level.INFO, "Updating billing with ID: {0}", billingId);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Billing billing = objectMapper.readValue(billingJson, Billing.class);
            billing.setId(billingId);
            billingDAO.updateBilling(billing);

            return objectMapper.writeValueAsString(billing);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred while updating billing", e);
            return "Error occurred while updating billing: " + e.getMessage();
        }
    }
}
