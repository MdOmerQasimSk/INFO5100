/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maple.backend.model;

/**
 *
 * @author bharv
 */
public class Catering {
    
    private int cateringID;
    private String cateringAddress;
    private String cateringName;
    private String cateringArea;
    private String cateringType;
    private String photo;
    private String capacity;
    private String bookedDates;
    private String cateringAdmin;
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
        return cateringName;
    }

    public int getCateringID() {
        return cateringID;
    }

    public void setCateringID(int cateringID) {
        this.cateringID = cateringID;
    }

    public String getCateringAddress() {
        return cateringAddress;
    }

    public void setCateringAddress(String cateringAddress) {
        this.cateringAddress = cateringAddress;
    }

    public String getCateringName() {
        return cateringName;
    }

    public void setCateringName(String cateringName) {
        this.cateringName = cateringName;
    }

    public String getCateringArea() {
        return cateringArea;
    }

    public void setCateringArea(String cateringArea) {
        this.cateringArea = cateringArea;
    }

    public String getCateringType() {
        return cateringType;
    }

    public void setCateringType(String cateringType) {
        this.cateringType = cateringType;
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

    public String getCateringAdmin() {
        return cateringAdmin;
    }

    public void setCateringAdmin(String cateringAdmin) {
        this.cateringAdmin = cateringAdmin;
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
