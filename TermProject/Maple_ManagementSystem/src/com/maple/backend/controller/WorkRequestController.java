/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maple.backend.controller;

import com.maple.backend.model.Catering;
import com.maple.backend.model.Event;
import com.maple.backend.model.Hotel;
import com.maple.backend.model.TravelAgent;
import com.maple.backend.model.WorkRequest;
import com.maple.backend.service.WorkRequestService;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author mohammedomerqasimshaik
 */
public class WorkRequestController {
    
    WorkRequestService workRequestService; 

    public WorkRequestController() throws SQLException {
        workRequestService = new WorkRequestService();
    }
    
    //get all workRequest Data
    public ArrayList<WorkRequest> getAllWorkRequestData() throws SQLException{
        return workRequestService.getAllWorkRequestData();
    }
    
    // get workRequest based on toId
    public ArrayList<WorkRequest> getWorkRequestByRoleService(int toId) throws SQLException{
        return workRequestService.getWorkRequestByRole(toId);
    }
      
    // get enterprise data from HOTEL table based on toId
    public ArrayList<Hotel> getHotelEnterpriseData(int toId, String status) throws SQLException{
        return workRequestService.getHotelDataService(toId, status);
    }
    
    // get enterprise data from CATERING table based on toId
    public ArrayList<Catering> getCateringEnterpriseData(int toId, String status) throws SQLException{
        return workRequestService.getCateringDataService(toId, status);
    }

    // get enterprise data from TRAVELAGENT table based on toId
    public ArrayList<TravelAgent> getTravelAgentEnterpriseData(int toId, String status) throws SQLException{
        return workRequestService.getTravelAgentDataService(toId, status);
    }
    
    // get workRequest based on eventId
    public ArrayList<WorkRequest> getWorkRequestByEventId(int eventId) throws SQLException{
        return workRequestService.getWorkRequestByEventID(eventId);
    }
    
    public void createWorkRequest(WorkRequest wk) throws SQLException {
        workRequestService.createWorkRequestService(wk);
    }
 
  
    //update status 
    public void updateStatus(String enterpriseName, String enterpriseType, String status) throws SQLException{
        workRequestService.updateStatusService(enterpriseName, enterpriseType, status);
    }
    
    //Event admin assigns event manager -> update EVENT and WORK_REQUEST table
    public void updateStatusForEventAdminToManager(String eventName, int managerId) throws SQLException{
        workRequestService.updateStatusEventAdminFlow(eventName, managerId);
    }
    
    //Hotel admin accepts/rejects event manager request -> update EVENT and WORK_REQUEST table
    public void updateHotelAdminWorkFlowStatus(String eventName, int hotelAdminId, String status) throws SQLException{
        workRequestService.updateHotelAdminWorkFlowStatusService(eventName, hotelAdminId, status);
    }
    
    //Catering admin accepts/rejects event manager request -> update EVENT and WORK_REQUEST table
    public void updateCateringAdminWorkFlowStatus(String eventName, int cateringAdminId, String status) throws SQLException{
        workRequestService.updateCateringAdminWorkFlowStatusService(eventName, cateringAdminId, status);
    }
    
    //TravelAgent admin accepts/rejects event manager request -> update EVENT and WORK_REQUEST table
    public void updateTravelAgentAdminWorkFlowStatus(String eventName, int travelAdminId, String status) throws SQLException{
        workRequestService.updateTravelAgentAdminWorkFlowStatusService(eventName, travelAdminId, status);
    }
    
    
    
}
