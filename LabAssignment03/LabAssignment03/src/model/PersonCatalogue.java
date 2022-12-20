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
public class PersonCatalogue {
    private ArrayList<Person> personList;

    public PersonCatalogue() {
        personList = new ArrayList<Person>();
        populatePeopleData();
    }

    public ArrayList<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(ArrayList<Person> personList) {
        this.personList = personList;
    }
    
    //Add person profile
    public void addPerson(Person person){
       personList.add(person); 
    }
    
    //Update person profile
    public void updatePerson(Person person, ArrayList<Person> list){
      
       //Iterate to find personId
       for(Person per: list){ 
           if(person.getPersonId() == per.getPersonId()){
               per.setPersonName(person.getPersonName());
               per.setPersonAge(person.getPersonAge());
               per.setCity(person.getCity());
               per.setCommunity(person.getCommunity());
               per.setPersonAddress(person.getPersonAddress());
               per.setPersonTelNum(person.getPersonTelNum());
               per.setPersonEmail(person.getPersonEmail());
           }
        }
    }
    
    
    //Remove person profile
    public void removePerson(Person person){
       personList.remove(person);
    }   
    
    //Advanced Search feature
    public ArrayList<Person> searchByOption(String searchType, String searchValue, ArrayList<Person> list){
        ArrayList<Person> filteredList = new ArrayList<Person>();
        
        //Search based on selected dropdown value
        if(searchType.equalsIgnoreCase("City")){
           list.stream().filter(per -> per.getCity().equalsIgnoreCase(searchValue)).forEach(per -> filteredList.add(per));
        } else if(searchType.equalsIgnoreCase("Community")){
           list.stream().filter(per -> per.getCommunity().equalsIgnoreCase(searchValue)).forEach(per -> filteredList.add(per));
        } else if(searchType.equalsIgnoreCase("ID")){
           list.stream().filter(per -> per.getPersonId()==Integer.parseInt(searchValue)).forEach(per -> filteredList.add(per));
        } else {
            return filteredList; //return null when nothing is selected
        }    
        return filteredList;
    }
   
    private void populatePeopleData(){
        Person person = new Person();
        person.setPersonId(1000);
        person.setPersonName("MdOmerQasim");
        person.setPersonAge(23);
        person.setPersonTelNum("1234567890");
        person.setPersonEmail("omer@gmail.com");
        person.setCity("Hyderabad");
        person.setCommunity("KPHB");
        person.setPersonAddress("FNO");
        personList.add(person);
    
    }
}
