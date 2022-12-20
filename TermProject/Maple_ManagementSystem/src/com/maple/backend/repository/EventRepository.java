/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maple.backend.repository;

import com.maple.DBConnection.JDBC;
import com.maple.backend.model.Event;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author deva
 */
public class EventRepository {
    JDBC obj;

    public EventRepository() throws SQLException {
        obj = JDBC.getInstance();
    }
    
    public void createAnEvent(Event newEvent) throws SQLException{
//        System.out.println("create repo line 1");
        String sql = "insert into event(status, event_id,event_type,event_name,event_description,event_area,attendees_count,accomodation_needed,accomodation_count,function_hall_needed,function_hall_count,catering_needed,catering_count,travel_needed,travel_count,event_from,event_to,event_manager_id,chosen_hotel_id,chosen_catering_id,chosen_travel_id, event_user_id) values('" + newEvent.getStatus()+ "','" + newEvent.getEventID() + "','" + newEvent.getEventType() + "','" + newEvent.getEventName() + "','" + newEvent.getEventDescription() + "','" + newEvent.getEventArea() + "','" + newEvent.getAtendeesCount() + "','" + newEvent.getAccomodationNeeded() + "','" + newEvent.getAccomodationCount() + "','" + newEvent.getFunctionHallNeeded() + "','" + newEvent.getFunctionHallCount() + "','" + newEvent.getCateringNeeded()+"','" + newEvent.getCateringCount()+"','" + newEvent.getTravelNeeded()+"','" + newEvent.getTravelCount()+"','" + newEvent.getEventFrom()+ "','" + newEvent.getEventTo()+ "','" + newEvent.getEventManagerID()+ "','" + newEvent.getChosenHotelID()+ "','" + newEvent.getChosenCateringID()+ "','" + newEvent.getChosenTravelAgentID()+ "','" + newEvent.getUserID()+ "')";
//        System.out.println("create " + sql);

        obj.update(sql, new String[]{});
//        System.out.println("create end");
//        String fetchQuery = "INSERT";
//        this.obj.update(fetchQuery, new String[]{});        
    }
     
    public ResultSet getEventData() throws SQLException{
        String fetchQuery = "SELECT * FROM EVENT";
        return obj.query(fetchQuery, new String[]{});          
    }
    
    public ResultSet getFilteredEventData(int userid, String status, String type) throws SQLException{
        String fetchQuery = "SELECT * FROM EVENT WHERE event_user_id = '" + userid + "' AND event_type = '" + type + "' AND status = '" + status + "'" ;  // add type, date, status filters
        return this.obj.query(fetchQuery, new String[]{});           
    }
    
    public void updateEventAdminFlowEventManagerId(int eventId, int eventManagerId) throws SQLException{
        String updateQuery = "UPDATE EVENT SET EVENT_MANAGER_ID = '" + eventManagerId + "' WHERE EVENT_ID = " + eventId;
        this.obj.update(updateQuery, new String[]{});           
    }
    
    public void updateEventAdminFlowStatus(int eventId) throws SQLException{
        String updateQuery = "UPDATE EVENT SET STATUS = 'ASSIGNED MANAGER' WHERE EVENT_ID = " + eventId;
        this.obj.update(updateQuery, new String[]{});           
    }
    
    public void updateHotelAdminFlowChosenHotelId(int hotelId, int eventId) throws SQLException{
        String updateQuery = "UPDATE EVENT SET CHOSEN_HOTEL_ID = '" + hotelId + "' WHERE EVENT_ID = " + eventId;
        this.obj.update(updateQuery, new String[]{});           
    }
    
    public void updateCateringAdminFlowChosenCateringId(int cateringId, int eventId) throws SQLException{
        String updateQuery = "UPDATE EVENT SET CHOSEN_CATERING_ID = '" + cateringId + "' WHERE EVENT_ID = " + eventId;
        this.obj.update(updateQuery, new String[]{});           
    }
    
    public void updateTravelAgentAdminFlowChosenCateringId(int travelId, int eventId) throws SQLException{
        String updateQuery = "UPDATE EVENT SET CHOSEN_TRAVEL_ID = '" + travelId + "' WHERE EVENT_ID = " + eventId;
        this.obj.update(updateQuery, new String[]{});           
    }
    

}
