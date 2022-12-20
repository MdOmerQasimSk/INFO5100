/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maple.backend.repository;

import com.maple.DBConnection.JDBC;
import com.maple.backend.model.WorkRequest;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mohammedomerqasimshaik
 */
public class WorkRequestRepository {
    
    JDBC obj;

    public WorkRequestRepository() throws SQLException {
        obj = JDBC.getInstance();
    }
    
    public ResultSet getWorkRequestData() throws SQLException{
        String fetchQuery = "SELECT * FROM WORK_REQUEST";
        return this.obj.query(fetchQuery, new String[]{});           
    }
    
//    public void addWorkRequestData(WorkRequest workRequest) throws SQLException{
//        String insertQuery = "INSERT INTO WORK_REQUEST";
//        this.obj.update(insertQuery, new String[]{});           
//    }
    
    public void updateWorkRequestDataStatus(int wkId, String status) throws SQLException{
        String updateQuery = "UPDATE WORK_REQUEST SET STATUS = '" + status + "' WHERE WK_ID = " + wkId;
        this.obj.update(updateQuery, new String[]{});           
    }
    
    public void updateWorkRequestDataEventManagerId(int wkId, int eventManagerId) throws SQLException{
        String updateQuery = "UPDATE WORK_REQUEST SET EVENT_MANAGER_ID = '" + eventManagerId + "' WHERE WK_ID = " + wkId;
        this.obj.update(updateQuery, new String[]{});           
    }
    
    public void updateWorkRequestDataStatus(int wkId) throws SQLException{
        String updateQuery = "UPDATE WORK_REQUEST SET STATUS = 'ASSIGNED MANAGER' WHERE WK_ID = " + wkId;
        this.obj.update(updateQuery, new String[]{});           
    }
    
    public ResultSet getHotelData(int toId) throws SQLException{
        String fetchQuery = "SELECT * FROM HOTEL WHERE H_ADMIN_ID IN (SELECT FROM_ID FROM WORK_REQUEST WHERE TO_ID = " + toId + ")";
        return this.obj.query(fetchQuery, new String[]{}); 
    }
    
    public ResultSet getCateringData(int toId) throws SQLException{
        String fetchQuery = "SELECT * FROM CATERING WHERE C_ADMIN IN (SELECT FROM_ID FROM WORK_REQUEST WHERE TO_ID = " + toId + ")";
        return this.obj.query(fetchQuery, new String[]{}); 
    }
    
    public ResultSet getTravelAgentData(int toId) throws SQLException{
        String fetchQuery = "SELECT * FROM TRAVELAGENT WHERE TA_ADMIN IN (SELECT FROM_ID FROM WORK_REQUEST WHERE TO_ID = " + toId + ")";
        return this.obj.query(fetchQuery, new String[]{}); 
    }
   
    public void createWorkRequest(WorkRequest wr) throws SQLException{
        String insertQuery = "INSERT INTO WORK_REQUEST (WK_ID, TYPE, FROM_ID, TO_ID, STATUS, EVENT_ID, EVENT_MANAGER_ID)" + 
        "values('" + wr.getID() + "','" + wr.getType() + "','" + wr.getFromID() + "','" + wr.getToID() + "','" + wr.getStatus() + "','" + wr.getEventID() + "','" + wr.getEventManagerID() + "')";
        this.obj.update(insertQuery, new String[]{});       
    }
    
    
    //Event admin
    
    
}
