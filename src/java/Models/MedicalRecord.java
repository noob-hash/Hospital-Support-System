/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.time.LocalDate;

/**
 *
 * @author Subin
 */
public class MedicalRecord {
    
    private Patient patient;
    private LocalDate date;
    
    private String bloodPressure;
    private String heartPressure;
    private String height;
    private int weight;
    
    private String[] symptoms;
    private String[] diagnosis;
    private String[] treatment;
    private MedicalProduct[] medicines;
    
    private LabReport[] labreports;


    public MedicalRecord(Patient patient, LocalDate date, String bloodPressure, String heartPressure, String height, int weight, String[] symptoms, String[] diagnosis, String[] treatment, MedicalProduct[] medicines, LabReport[] labreports) {
        this.patient = patient;
        this.date = date;
        this.bloodPressure = bloodPressure;
        this.heartPressure = heartPressure;
        this.height = height;
        this.weight = weight;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.medicines = medicines;
        this.labreports = labreports;
    }

    public MedicalRecord(Patient patient, LocalDate date, String bloodPressure, String heartPressure, String height, int weight, String[] symptoms, String[] diagnosis, String[] treatment, MedicalProduct[] medicines) {
        this.patient = patient;
        this.date = date;
        this.bloodPressure = bloodPressure;
        this.heartPressure = heartPressure;
        this.height = height;
        this.weight = weight;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.medicines = medicines;
    }

    public MedicalRecord(Patient patient, LocalDate date, String bloodPressure, String heartPressure, String height, int weight, String[] symptoms, String[] diagnosis, String[] treatment) {
        this.patient = patient;
        this.date = date;
        this.bloodPressure = bloodPressure;
        this.heartPressure = heartPressure;
        this.height = height;
        this.weight = weight;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getHeartPressure() {
        return heartPressure;
    }

    public void setHeartPressure(String heartPressure) {
        this.heartPressure = heartPressure;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String[] getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String[] symptoms) {
        this.symptoms = symptoms;
    }

    public String[] getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String[] diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String[] getTreatment() {
        return treatment;
    }

    public void setTreatment(String[] treatment) {
        this.treatment = treatment;
    }

    public MedicalProduct[] getMedicines() {
        return medicines;
    }

    public void setMedicines(MedicalProduct[] medicines) {
        this.medicines = medicines;
    }

    public LabReport[] getLabreports() {
        return labreports;
    }

    public void setLabreports(LabReport[] labreports) {
        this.labreports = labreports;
    }
    
}
