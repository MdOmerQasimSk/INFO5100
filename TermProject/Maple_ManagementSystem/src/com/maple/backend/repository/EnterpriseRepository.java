/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maple.backend.repository;

import com.maple.DBConnection.JDBC;
import com.maple.backend.model.Catering;
import com.maple.backend.model.Hotel;
import com.maple.backend.model.TravelAgent;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mohammedomerqasimshaik
 */
public class EnterpriseRepository {
    
    JDBC obj;

    public EnterpriseRepository() throws SQLException {
        obj = JDBC.getInstance();
    }
    
    public ResultSet getAllHotelData() throws SQLException{
        String fetchQuery = "SELECT * FROM HOTEL";
        return this.obj.query(fetchQuery, new String[]{}); 
    }
    
    public ResultSet getAllCateringData() throws SQLException{
        String fetchQuery = "SELECT * FROM CATERING";
        return this.obj.query(fetchQuery, new String[]{}); 
    }
    
    public ResultSet getAllTravelData() throws SQLException{
        String fetchQuery = "SELECT * FROM TRAVELAGENT";
        return this.obj.query(fetchQuery, new String[]{}); 
    }
    
    public ResultSet getEnterpriseDataEnterprise(String type, int id) throws SQLException{
        
        String fetchQuery = "";
        
        if(type.equalsIgnoreCase("HOTEL")){
            fetchQuery = "SELECT * FROM HOTEL WHERE H_ID = " + id;
        } else if (type.equalsIgnoreCase("CATERING")){
            fetchQuery = "SELECT * FROM CATERING WHERE C_ID = " + id;
        } else if (type.equalsIgnoreCase("TRAVEL")){
            fetchQuery = "SELECT * FROM TRAVELAGENT WHERE TA_ID = " + id;
        }
        
        return this.obj.query(fetchQuery, new String[]{});           
    }
    
    public ResultSet getEnterpriseData(String type, int id) throws SQLException{
        
        String fetchQuery = "";
        
        if(type.equalsIgnoreCase("HOTEL")){
            fetchQuery = "SELECT * FROM HOTEL WHERE H_ADMIN_ID = " + id;
        } else if (type.equalsIgnoreCase("CATERING")){
            fetchQuery = "SELECT * FROM CATERING WHERE C_ADMIN = " + id;
        } else if (type.equalsIgnoreCase("TRAVEL")){
            fetchQuery = "SELECT * FROM TRAVELAGENT WHERE TA_ADMIN = " + id;
        }
        
        return this.obj.query(fetchQuery, new String[]{});           
    }
    
    public void insertHotelData(Hotel hotelData) throws SQLException{
        String insertQuery = "INSERT INTO HOTEL (H_ID, H_NAME, H_ADDRESS, H_AREA, H_TYPE, H_CAPACITY, H_ADMIN_ID, H_BOOKED_DATES, H_PHOTO, H_EMAIL, H_PHONE, H_STATUS)" + 
        "values('" + hotelData.getHotelID() + "','" + hotelData.getHotelName() + "','" + hotelData.getHotelAddress() + "','" + hotelData.getHotelArea() + "','" + hotelData.getHotelType() + "','" + hotelData.getCapacity() + "','" + hotelData.getHotelAdmin() 
                + "','" + hotelData.getBookedDates() + "','" + hotelData.getPhoto() + "','" + 
                hotelData.getEmail()+ "','" + hotelData.getPhone() + "','" + hotelData.getStatus() + "')";
        this.obj.update(insertQuery, new String[]{}); 
    }
    
    public void insertCateringData(Catering cateringData) throws SQLException{
        String insertQuery = "INSERT INTO CATERING (C_ID, C_NAME, C_ADDRESS, C_AREA, C_CAPACITY, C_ADMIN, C_BOOKED_DATES, C_PHOTO, C_EMAIL, C_PHONE, C_STATUS)" + 
        "values('" + cateringData.getCateringID() + "','" + cateringData.getCateringName() + "','" + cateringData.getCateringAddress() + "','" + cateringData.getCateringArea() + "','" + cateringData.getCapacity() + "','" + cateringData.getCateringAdmin() 
                + "','" + cateringData.getBookedDates() + "','" + cateringData.getPhoto() + "','" + 
                cateringData.getEmail()+ "','" + cateringData.getPhone() + "','" + cateringData.getStatus() + "')";
        this.obj.update(insertQuery, new String[]{}); 
    }
    
    public void insertTravelData(TravelAgent travelAgent) throws SQLException{
        String insertQuery = "INSERT INTO TRAVELAGENT (TA_ID, TA_NAME, TA_ADDRESS, TA_AREA, TA_CAPACITY, TA_ADMIN, TA_BOOKED_DATES, TA_PHOTO, TA_EMAIL, TA_PHONE, TA_STATUS)" + 
        "values('" + travelAgent.getTravelAgentID() + "','" + travelAgent.getTravelAgentName() + "','" + travelAgent.getTravelAgentAddress() + "','" + travelAgent.getTravelAgentArea() + "','" + travelAgent.getCapacity() + "','" + travelAgent.getTravelAgentAdmin() 
                + "','" + travelAgent.getBookedDates() + "','" + travelAgent.getPhoto() + "','" + 
                travelAgent.getEmail()+ "','" + travelAgent.getPhone() + "','" + travelAgent.getStatus() + "')";
        this.obj.update(insertQuery, new String[]{}); 
    }
    
    public void updateHotelStatus(int hotelAdminId, String status) throws SQLException {
        String sql = "UPDATE HOTEL SET H_STATUS = '" + status + "' WHERE H_ADMIN_ID = '" + hotelAdminId + "'";
        this.obj.update(sql, new String[]{});
    }
    
    public void updateCateringStatus(int cateringAdminId, String status) throws SQLException {
        String sql = "UPDATE CATERING SET C_STATUS = '" + status + "' WHERE C_ADMIN = '" + cateringAdminId + "'";
        this.obj.update(sql, new String[]{});
    }
    
    public void updateTravelStatus(int travelAdminId, String status) throws SQLException {
        String sql = "UPDATE TRAVELAGENT SET TA_STATUS = '" + status + "' WHERE TA_ADMIN = '" + travelAdminId + "'";
        this.obj.update(sql, new String[]{});
    }
    
    
}
