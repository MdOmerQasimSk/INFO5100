/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author omerq
 */
public class Patient {
    private int patientId;
    private String patientName;
    private int patientAge;
    private String patientTelNum;
    private String patientEmail;
    private String city;
    private String community;
    private String hospital;
    private String consultedDoctor;
    private VitalSign vitalSign;
    
    @Override
    public String toString() {
        return Integer.toString(patientId);
    }
    
    public String getPatientTelNum() {
        return patientTelNum;
    }

    public void setPatientTelNum(String patientTelNum) {
        this.patientTelNum = patientTelNum;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    
    
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getConsultedDoctor() {
        return consultedDoctor;
    }

    public void setConsultedDoctor(String consultedDoctor) {
        this.consultedDoctor = consultedDoctor;
    }

    public VitalSign getVitalSign() {
        return vitalSign;
    }

    public void setVitalSign(VitalSign vitalSign) {
        this.vitalSign = vitalSign;
    }

   
    
    
}
