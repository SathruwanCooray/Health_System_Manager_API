/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JavaClasses;

import java.util.Map;

/**
 *
 * @author sathru
 */
public class MedicalRecord {
    // Attributes of a medical record
    private int id;
    private Map<String, String> diagnoses;
    private Map<String, String> treatments;

    // Constructor with parameters to initialize a medical record
    public MedicalRecord(int id, Map<String, String> diagnoses, Map<String, String> treatments) {
        this.id = id;
        this.diagnoses = diagnoses;
        this.treatments = treatments;
    }

    // Default constructor
    public MedicalRecord() {
    }

    // Getter and setter methods for each attribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<String, String> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(Map<String, String> diagnoses) {
        this.diagnoses = diagnoses;
    }

    public Map<String, String> getTreatments() {
        return treatments;
    }

    public void setTreatments(Map<String, String> treatments) {
        this.treatments = treatments;
    }
}