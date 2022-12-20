/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author omerq
 */
public class Person {
    
    private int personId;
    private String personName;
    private int personAge;
    private String personAddress;
    private String personTelNum;
    private String personEmail;
    private String personGender;
    private String personComments;
    
    private String existingUser;
    
    //Hospital variables
    private String city;
    private String community;
    private String hospital;
    
    //Doctor variables
    private String name;
    

    @Override
    public String toString() {
        return Integer.toString(personId);
    }
    
    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public int getPersonAge() {
        return personAge;
    }

    public void setPersonAge(int personAge) {
        this.personAge = personAge;
    }

    public String getPersonAddress() {
        return personAddress;
    }

    public void setPersonAddress(String personAddress) {
        this.personAddress = personAddress;
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

    public String getPersonTelNum() {
        return personTelNum;
    }

    public void setPersonTelNum(String personTelNum) {
        this.personTelNum = personTelNum;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public String getPersonGender() {
        return personGender;
    }

    public void setPersonGender(String personGender) {
        this.personGender = personGender;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonComments() {
        return personComments;
    }

    public void setPersonComments(String personComments) {
        this.personComments = personComments;
    }

    public String getExistingUser() {
        return existingUser;
    }

    public void setExistingUser(String existingUser) {
        this.existingUser = existingUser;
    }

   
    
    
}
