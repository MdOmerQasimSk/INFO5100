/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maple.backend.controller;

import com.maple.backend.model.Catering;
import com.maple.backend.model.Hotel;
import com.maple.backend.model.TravelAgent;
import com.maple.backend.service.EnterpriseService;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author mohammedomerqasimshaik
 */
public class EnterpriseController {
    
    EnterpriseService enterpriseService;

    public EnterpriseController() throws SQLException {
        enterpriseService = new EnterpriseService();
    }
    
    public ResultSet getEnterpriseData(String type, int id) throws SQLException{
        return enterpriseService.getEnterpriseDataService(type, id);
    }
    
     public ResultSet getEnterpriseDataEnterprise(String type, int id) throws SQLException{
        return enterpriseService.getEnterpriseDataServiceEnterprise(type, id);
    }
    
    public void insertHotelData(Hotel hotelData) throws SQLException{
        enterpriseService.insertHotelDataService(hotelData);
    }
    
    public ArrayList<Hotel> getAllHotelData() throws SQLException{
        return enterpriseService.getAllHotelDataService();
    }
    
    public ArrayList<Catering> getAllCateringData() throws SQLException{
        return enterpriseService.getAllCateringDataService();
    }
    
    public ArrayList<TravelAgent> getAllTravelData() throws SQLException{
        return enterpriseService.getAllTravelDataService();
    }
    
    public void insertCateringData(Catering cateringData) throws SQLException{
        enterpriseService.insertCateringDataService(cateringData);
    }
    
    public void insertTravelData(TravelAgent travelAgent) throws SQLException{
        enterpriseService.insertTravelDataService(travelAgent);
    }
    
}
