/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maple.backend.controller;

import com.maple.backend.model.Event;
import com.maple.backend.model.WorkRequest;
import com.maple.backend.service.EventService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author mohammedomerqasimshaik
 */
public class EventController {
    EventService eventService;
    
    public EventController() throws SQLException {
        eventService = new EventService();
    }
    
    // Get all events
    public int getEventsList() throws SQLException{
//        return eventService.getEventsListService();
        ArrayList<Event> eventList = eventService.getEventsListService();
        return eventList.size()+1;
    }
    
    
     // Get User events
    public ArrayList<Event> getFilteredEventsList(int userid, String status, String type) throws SQLException{
        return eventService.getFilteredEventsListService(userid, status, type);
    }
    
    // Create Event    
     public void createAnEvent(Event newEvent) throws SQLException{
//         System.out.println("usr controll");
        eventService.createAnEventService(newEvent);
    }
     
    public ArrayList<Event> getAllEventList() throws SQLException{
        return eventService.getAllEventListService();
    }
    
    public ArrayList<Event> getPublicEventList() throws SQLException{
        return eventService.getPublicEventList();
    }
    
    public ArrayList<Event> getPrivateEventList() throws SQLException{
        return eventService.getPrivateEventList();
    }
    
    public ArrayList<Event> getEventDataByEnterprise(ArrayList<WorkRequest> wkList, int toId) throws SQLException{
        return eventService.getEventDataByEnterpriseService(wkList, toId);
    }
}
