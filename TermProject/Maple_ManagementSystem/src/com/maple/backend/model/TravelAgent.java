/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maple.backend.model;

/**
 *
 * @author bharv
 */
public class TravelAgent {
    
    private int travelAgentID;
    private String travelAgentAddress;
    private String travelAgentName;
    private String travelAgentArea;
    private String travelAgentType;
    private String photo;
    private String capacity;
    private String bookedDates;
    private String travelAgentAdmin;
    private String email;
    private String phone;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
    @Override
    public String toString() {
        return travelAgentName;
    }

    public int getTravelAgentID() {
        return travelAgentID;
    }

    public void setTravelAgentID(int travelAgentID) {
        this.travelAgentID = travelAgentID;
    }

    public String getTravelAgentAddress() {
        return travelAgentAddress;
    }

    public void setTravelAgentAddress(String travelAgentAddress) {
        this.travelAgentAddress = travelAgentAddress;
    }

    public String getTravelAgentName() {
        return travelAgentName;
    }

    public void setTravelAgentName(String travelAgentName) {
        this.travelAgentName = travelAgentName;
    }

    public String getTravelAgentArea() {
        return travelAgentArea;
    }

    public void setTravelAgentArea(String travelAgentArea) {
        this.travelAgentArea = travelAgentArea;
    }

    public String getTravelAgentType() {
        return travelAgentType;
    }

    public void setTravelAgentType(String travelAgentType) {
        this.travelAgentType = travelAgentType;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getBookedDates() {
        return bookedDates;
    }

    public void setBookedDates(String bookedDates) {
        this.bookedDates = bookedDates;
    }

    public String getTravelAgentAdmin() {
        return travelAgentAdmin;
    }

    public void setTravelAgentAdmin(String travelAgentAdmin) {
        this.travelAgentAdmin = travelAgentAdmin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    
    
    
}
