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
public class HospitalCatalogue {
   
    private ArrayList<Hospital> hospitalList;

    public HospitalCatalogue() {
        hospitalList = new ArrayList<Hospital>();
        populateHospitalData();
    }

    public ArrayList<Hospital> getHospitalList() {
        return hospitalList;
    }

    public void setHospitalList(ArrayList<Hospital> hospitalList) {
        this.hospitalList = hospitalList;
    }
    
    //Add hospital profile
    public void addHospital(Hospital hospital){
       hospitalList.add(hospital); 
    }
    
    //Update hospital profile
    public void updateHospital(Hospital hospital, ArrayList<Hospital> list){
      
       //Iterate to find hospitalId
       for(Hospital hosp: list){ 
           if(hospital.getHospitalID() == hosp.getHospitalID()){
               hosp.setHospitalName(hospital.getHospitalName());
               hosp.setCity(hospital.getCity());
               hosp.setCommunity(hospital.getCommunity());
               hosp.setAddress(hospital.getAddress());
               hosp.setContactNumber(hospital.getContactNumber());
               hosp.setEmail(hospital.getEmail());
           }
        }
    }
    
    
    //Remove hospital profile
    public void removeHospital(Hospital hospital){
       hospitalList.remove(hospital);
    }
    
    //Advanced Search feature
    public ArrayList<Hospital> searchByOption(String searchType, String searchValue, ArrayList<Hospital> list){
        ArrayList<Hospital> filteredList = new ArrayList<Hospital>();
        
        //Search based on selected dropdown value
        if(searchType.equalsIgnoreCase("City")){
           list.stream().filter(hosp -> hosp.getCity().equalsIgnoreCase(searchValue)).forEach(hosp -> filteredList.add(hosp));
        } else if(searchType.equalsIgnoreCase("Community")){
           list.stream().filter(hosp -> hosp.getCommunity().equalsIgnoreCase(searchValue)).forEach(hosp -> filteredList.add(hosp));
        } else {
            return filteredList; //return null when nothing is selected
        }    
        return filteredList;
    }
    
    //default data 
    private void populateHospitalData(){
        Hospital hosp = new Hospital();
        hosp.setHospitalID(1000);
        hosp.setAddress("RoadNo1");
        hosp.setCity("Hyderabad");
        hosp.setCommunity("KPHB");
        hosp.setContactNumber("1234567789");
        hosp.setEmail("ashok@gmail.com");
        hosp.setHospitalName("Ashok Hospital");
        hospitalList.add(hosp);
        
        
        Hospital hosp1 = new Hospital();
        hosp1.setHospitalID(1001);
        hosp1.setAddress("RoadNo2");
        hosp1.setCity("Hyderabad");
        hosp1.setCommunity("HiTech");
        hosp1.setContactNumber("1234567789");
        hosp1.setEmail("hitech@gmail.com");
        hosp1.setHospitalName("Apollo City Hospital");
        hospitalList.add(hosp1);
        
        
        Hospital hosp2 = new Hospital();
        hosp2.setHospitalID(1002);
        hosp2.setAddress("RoadNo3");
        hosp2.setCity("Mumbai");
        hosp2.setCommunity("Andheri");
        hosp2.setContactNumber("1234567789");
        hosp2.setEmail("andheri@gmail.com");
        hosp2.setHospitalName("Children Hospital");
        hospitalList.add(hosp2);
        
        
        Hospital hosp3 = new Hospital();
        hosp3.setHospitalID(1003);
        hosp3.setAddress("RoadNo4");
        hosp3.setCity("Delhi");
        hosp3.setCommunity("GandhiNagar");
        hosp3.setContactNumber("1234567789");
        hosp3.setEmail("drive@gmail.com");
        hosp3.setHospitalName("Sri Hospital");
        hospitalList.add(hosp3);
    }
}
