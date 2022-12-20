/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maple.backend.service;

import com.maple.backend.model.Catering;
import com.maple.backend.model.Hotel;
import com.maple.backend.model.TravelAgent;
import com.maple.backend.repository.EnterpriseRepository;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author mohammedomerqasimshaik
 */
public class EnterpriseService {
    
    EnterpriseRepository enterpriseRepository;

    public EnterpriseService() throws SQLException {
        enterpriseRepository = new EnterpriseRepository();
    }
    
    private ArrayList<Hotel> hotelMapper(ResultSet resultSet) throws SQLException{
        ArrayList<Hotel> hotelData = new ArrayList<>();
        
        while(resultSet.next()){
            Hotel hotel = new Hotel();
            hotel.setHotelID(Integer.parseInt(resultSet.getString("H_ID")));
            hotel.setHotelName(resultSet.getString("H_NAME"));
            hotel.setHotelAddress(resultSet.getString("H_ADDRESS"));
            hotel.setHotelArea(resultSet.getString("H_AREA"));
            hotel.setHotelType(resultSet.getString("H_TYPE"));
            hotel.setCapacity(resultSet.getString("H_CAPACITY"));
            hotel.setHotelAdmin(resultSet.getString("H_ADMIN_ID"));
            hotel.setBookedDates(resultSet.getString("H_BOOKED_DATES"));
            hotel.setPhoto(resultSet.getString("H_PHOTO"));
            hotel.setEmail(resultSet.getString("H_EMAIL"));
            hotel.setPhone(resultSet.getString("H_PHONE"));
            hotel.setStatus(resultSet.getString("H_STATUS"));
            hotel.setPhoto(resultSet.getString("H_PHOTO"));
            hotelData.add(hotel);
        }
        
        return hotelData; 
    }
    
    private ArrayList<Catering> cateringMapper(ResultSet resultSet) throws SQLException{
        ArrayList<Catering> cateringData = new ArrayList<>();
        
        while(resultSet.next()){
            Catering catering = new Catering();
            catering.setCateringID(Integer.parseInt(resultSet.getString("C_ID")));
            catering.setCateringName(resultSet.getString("C_NAME"));
            catering.setCateringAddress(resultSet.getString("C_ADDRESS"));
            catering.setCateringArea(resultSet.getString("C_AREA"));
            catering.setCapacity(resultSet.getString("C_CAPACITY"));
            catering.setCateringAdmin(resultSet.getString("C_ADMIN"));
            catering.setBookedDates(resultSet.getString("C_BOOKED_DATES"));
            catering.setPhoto(resultSet.getString("C_PHOTO"));
            catering.setEmail(resultSet.getString("C_EMAIL"));
            catering.setPhone(resultSet.getString("C_PHONE"));
            catering.setStatus(resultSet.getString("C_STATUS"));
            catering.setPhoto(resultSet.getString("C_PHOTO"));
            cateringData.add(catering);
        }
        
        return cateringData; 
    }
    
    private ArrayList<TravelAgent> travelMapper(ResultSet resultSet) throws SQLException{
        ArrayList<TravelAgent> travelData = new ArrayList<>();
        
        while(resultSet.next()){
            TravelAgent travel = new TravelAgent();
            travel.setTravelAgentID(Integer.parseInt(resultSet.getString("TA_ID")));
            travel.setTravelAgentName(resultSet.getString("TA_NAME"));
            travel.setTravelAgentAddress(resultSet.getString("TA_ADDRESS"));
            travel.setTravelAgentArea(resultSet.getString("TA_AREA"));
            travel.setCapacity(resultSet.getString("TA_CAPACITY"));
            travel.setTravelAgentAdmin(resultSet.getString("TA_ADMIN"));
            travel.setBookedDates(resultSet.getString("TA_BOOKED_DATES"));
            travel.setPhoto(resultSet.getString("TA_PHOTO"));
            travel.setEmail(resultSet.getString("TA_EMAIL"));
            travel.setPhone(resultSet.getString("TA_PHONE"));
            travel.setStatus(resultSet.getString("TA_STATUS"));
            travel.setPhoto(resultSet.getString("TA_PHOTO"));
            travelData.add(travel);
        }
        
        return travelData; 
    }
    
    public ResultSet getEnterpriseDataService(String type, int id) throws SQLException{
        return enterpriseRepository.getEnterpriseData(type, id);
    }
    
     public ResultSet getEnterpriseDataServiceEnterprise(String type, int id) throws SQLException{
        return enterpriseRepository.getEnterpriseDataEnterprise(type, id);
    }
    
    public void insertHotelDataService(Hotel hotelData) throws SQLException{
        enterpriseRepository.insertHotelData(hotelData);
    }
    
    public void insertCateringDataService(Catering cateringData) throws SQLException{
        enterpriseRepository.insertCateringData(cateringData);
    }
    
    public void insertTravelDataService(TravelAgent travelData) throws SQLException{
        enterpriseRepository.insertTravelData(travelData);
    }
    
    public ArrayList<Hotel> getAllHotelDataService() throws SQLException{
        ResultSet resultSet = enterpriseRepository.getAllHotelData();
        return hotelMapper(resultSet);
    }
    
    public ArrayList<Catering> getAllCateringDataService() throws SQLException{
        ResultSet resultSet = enterpriseRepository.getAllCateringData();
        return cateringMapper(resultSet);
    }
    
    public ArrayList<TravelAgent> getAllTravelDataService() throws SQLException{
        ResultSet resultSet = enterpriseRepository.getAllTravelData();
        return travelMapper(resultSet);
    }
    
    
    
}
