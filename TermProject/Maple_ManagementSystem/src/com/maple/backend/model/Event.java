/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maple.backend.model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author mohammedomerqasimshaik
 */
public class Event {

    public int eventID;
    public int userID;
    public String eventType;
    public String eventName;
    public String eventDescription;
    public String eventArea;
    public String atendeesCount;
    public String eventFrom;
    public String eventTo;
    public int eventManagerID;
    public int chosenHotelID;
    public int chosenCateringID;
    public int chosenTravelAgentID;
    public String accomodationNeeded;
    public int accomodationCount;
    public String functionHallNeeded;
    public int functionHallCount;
    public String cateringNeeded;
    public int cateringCount;
    public String travelNeeded;
    public int travelCount;
    public String status;
 

    public int getAccomodationCount() {
        return accomodationCount;
    }

    public void setAccomodationCount(int accomodationCount) {
        this.accomodationCount = accomodationCount;
    }

    public int getFunctionHallCount() {
        return functionHallCount;
    }

    public void setFunctionHallCount(int functionHallCount) {
        this.functionHallCount = functionHallCount;
    }

    
    public int getCateringCount() {
        return cateringCount;
    }

    public void setCateringCount(int cateringCount) {
        this.cateringCount = cateringCount;
    }

    public String getTravelNeeded() {
        return travelNeeded;
    }

    public void setTravelNeeded(String travelNeeded) {
        this.travelNeeded = travelNeeded;
    }

    public int getTravelCount() {
        return travelCount;
    }

    public void setTravelCount(int travelCount) {
        this.travelCount = travelCount;
    }
  

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventArea() {
        return eventArea;
    }

    public void setEventArea(String eventArea) {
        this.eventArea = eventArea;
    }

    public String getAtendeesCount() {
        return atendeesCount;
    }

    public void setAtendeesCount(String atendeesCount) {
        this.atendeesCount = atendeesCount;
    }

    public String getEventFrom() {
        return eventFrom;
    }

    public void setEventFrom(String eventFrom) {
        this.eventFrom = eventFrom;
    }

    public String getEventTo() {
        return eventTo;
    }

    public void setEventTo(String eventTo) {
        this.eventTo = eventTo;
    }

    public int getEventManagerID() {
        return eventManagerID;
    }

    public void setEventManagerID(int eventManagerID) {
        this.eventManagerID = eventManagerID;
    }

    public int getChosenHotelID() {
        return chosenHotelID;
    }

    public void setChosenHotelID(int chosenHotelID) {
        this.chosenHotelID = chosenHotelID;
    }

    public int getChosenCateringID() {
        return chosenCateringID;
    }

    public void setChosenCateringID(int chosenCateringID) {
        this.chosenCateringID = chosenCateringID;
    }

    public int getChosenTravelAgentID() {
        return chosenTravelAgentID;
    }

    public void setChosenTravelAgentID(int chosenTravelAgentID) {
        this.chosenTravelAgentID = chosenTravelAgentID;
    }

    public String getAccomodationNeeded() {
        return accomodationNeeded;
    }

    public void setAccomodationNeeded(String accomodationNeeded) {
        this.accomodationNeeded = accomodationNeeded;
    }

    public String getFunctionHallNeeded() {
        return functionHallNeeded;
    }

    public void setFunctionHallNeeded(String functionHallNeeded) {
        this.functionHallNeeded = functionHallNeeded;
    }

    public String getCateringNeeded() {
        return cateringNeeded;
    }

    public void setCateringNeeded(String cateringNeeded) {
        this.cateringNeeded = cateringNeeded;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return  String.valueOf(eventID);
    }
    
    

}
