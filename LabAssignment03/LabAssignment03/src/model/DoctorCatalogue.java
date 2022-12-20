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
public class DoctorCatalogue {
    
    private ArrayList<Doctor> doctorList;

    public DoctorCatalogue() {
        doctorList = new ArrayList<Doctor>();
        populateDoctorData();
    }

    public ArrayList<Doctor> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(ArrayList<Doctor> doctorList) {
        this.doctorList = doctorList;
    }
    
    //Add doctor profile
    public void addDoctor(Doctor doctor){
       doctorList.add(doctor); 
    }
    
    //Update doctor profile
    public void updateDoctor(Doctor doctor, ArrayList<Doctor> list){
       //Iterate to find doctorId
       for(Doctor doc: list){ 
           if(doctor.getDoctorId() == doc.getDoctorId()){
               doc.setName(doctor.getName());
               doc.setAge(doctor.getAge());
               doc.setGender(doctor.getGender());
               doc.setCity(doctor.getCity());
               doc.setCommunity(doctor.getCommunity());
               doc.setHospital(doctor.getHospital());
               doc.setPhoneNumber(doctor.getPhoneNumber());
               doc.setEmail(doctor.getEmail());
           }
        }
    }
    
    
    //Remove doctor profile
    public void removeDoctor(Doctor doctor){
       doctorList.remove(doctor);
    }   
    
    //Advanced Search feature
    public ArrayList<Doctor> searchByOption(String searchType, String searchValue, ArrayList<Doctor> list){
        ArrayList<Doctor> filteredList = new ArrayList<Doctor>();
        
        //Search based on selected dropdown value
        if(searchType.equalsIgnoreCase("City")){
           list.stream().filter(doc -> doc.getCity().equalsIgnoreCase(searchValue)).forEach(doc -> filteredList.add(doc));
        } else if(searchType.equalsIgnoreCase("Community")){
           list.stream().filter(doc -> doc.getCommunity().equalsIgnoreCase(searchValue)).forEach(doc -> filteredList.add(doc));
        } else if(searchType.equalsIgnoreCase("Hospital")){
           list.stream().filter(doc -> doc.getHospital().equalsIgnoreCase(searchValue)).forEach(doc -> filteredList.add(doc));
        } else if(searchType.equalsIgnoreCase("ID")){
           list.stream().filter(doc -> doc.getDoctorId()==Integer.parseInt(searchValue)).forEach(doc -> filteredList.add(doc));
        } else {
            return filteredList; //return null when nothing is selected
        }    
        return filteredList;
    }
    
    private void populateDoctorData(){
        Doctor doctor = new Doctor();
        doctor.setDoctorId(1000);
        doctor.setName("DrMishra");
        doctor.setAge(35);
        doctor.setCity("Delhi");
        doctor.setCommunity("GandhiNagar");
        doctor.setEmail("mishra@gmail.com");
        doctor.setGender("Male");
        doctor.setPhoneNumber("1522662622");
        doctor.setHospital("Sri Hospital");
        doctorList.add(doctor);
    }
   
}
