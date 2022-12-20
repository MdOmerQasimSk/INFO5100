/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author omerq
 */
public class PatientCatalogue {
    
    private ArrayList<Patient> patientList;

    public PatientCatalogue() {
        patientList = new ArrayList<Patient>();
//        populatePatientData();
    }

    public ArrayList<Patient> getPatientList() {
        return patientList;
    }

    public void setPatientList(ArrayList<Patient> patientList) {
        this.patientList = patientList;
    }
    
    //Add Patient profile
    public void addPatient(Patient patient){
       patientList.add(patient); 
    }
    
    //Update Patient profile
    public void updatePatient(Patient patient, ArrayList<Patient> list){
       //Iterate to find patientId
       for(Patient pat: list){ 
           if(patient.getPatientId() == pat.getPatientId()){
               pat.setPatientName(patient.getPatientName());
               pat.setPatientAge(patient.getPatientAge());
               pat.setCity(patient.getCity());
               pat.setCommunity(patient.getCommunity());
               pat.setHospital(patient.getHospital());
               pat.setPatientTelNum(patient.getPatientTelNum());
               pat.setPatientEmail(patient.getPatientEmail());
               pat.setConsultedDoctor(patient.getConsultedDoctor());
           }
        }
    }
    
    
    //Remove Patient profile
    public void removePatient(Patient patient){
       patientList.remove(patient);
    }   
    
    //Advanced Search feature
    public ArrayList<Patient> searchByOption(String searchType, String searchValue, ArrayList<Patient> list){
        ArrayList<Patient> filteredList = new ArrayList<Patient>();
        
        //Search based on selected dropdown value
        if(searchType.equalsIgnoreCase("City")){
           list.stream().filter(doc -> doc.getCity().equalsIgnoreCase(searchValue)).forEach(doc -> filteredList.add(doc));
        } else if(searchType.equalsIgnoreCase("Community")){
           list.stream().filter(doc -> doc.getCommunity().equalsIgnoreCase(searchValue)).forEach(doc -> filteredList.add(doc));
        } else if(searchType.equalsIgnoreCase("Hospital")){
           list.stream().filter(doc -> doc.getHospital().equalsIgnoreCase(searchValue)).forEach(doc -> filteredList.add(doc));
        } else if(searchType.equalsIgnoreCase("ID")){
           list.stream().filter(doc -> doc.getPatientId()==Integer.parseInt(searchValue)).forEach(doc -> filteredList.add(doc));
        } else if(searchType.equalsIgnoreCase("Doctor")){
           list.stream().filter(doc -> doc.getConsultedDoctor().equalsIgnoreCase(searchValue)).forEach(doc -> filteredList.add(doc));
        } else {
            return filteredList; //return null when nothing is selected
        }    
        return filteredList;
    }
   
}
