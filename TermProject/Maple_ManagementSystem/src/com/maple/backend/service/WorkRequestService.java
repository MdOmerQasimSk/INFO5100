/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maple.backend.service;

import com.maple.backend.model.Catering;
import com.maple.backend.model.Event;
import com.maple.backend.model.Hotel;
import com.maple.backend.model.TravelAgent;
import com.maple.backend.model.WorkRequest;
import com.maple.backend.repository.EnterpriseRepository;
import com.maple.backend.repository.EventRepository;
import com.maple.backend.repository.UserRepository;
import com.maple.backend.repository.WorkRequestRepository;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mohammedomerqasimshaik
 */
public class WorkRequestService {
    
    WorkRequestRepository workRequestRepository;
    
    UserRepository userRepository;
    
    EnterpriseRepository enterpriseRepository;
    
    EnterpriseService enterpriseService;
    
    UserService userService;
    
    EventService eventService;
    
    EventRepository eventRepository;

    public WorkRequestService() throws SQLException {
        workRequestRepository = new WorkRequestRepository();
        userRepository = new UserRepository();
        enterpriseRepository = new EnterpriseRepository();
        enterpriseService = new EnterpriseService();
        userService = new UserService();
        eventService = new EventService();
        eventRepository = new EventRepository();
    }
    
    private ArrayList<WorkRequest> workRequestDataMapper(ResultSet rs) throws SQLException{
        ArrayList<WorkRequest> workRequestList = new ArrayList<>();
        //Mapping logic
        while(rs.next()){
            WorkRequest wk = new WorkRequest();
            wk.setID(Integer.parseInt(rs.getString("WK_ID")));
            wk.setType(rs.getString("TYPE"));
            wk.setFromID(Integer.parseInt(rs.getString("FROM_ID")));
            wk.setToID(Integer.parseInt(rs.getString("TO_ID")));
            wk.setStatus(rs.getString("STATUS"));
            wk.setEventID(Integer.parseInt(rs.getString("EVENT_ID")));
            wk.setEventManagerID(Integer.parseInt(rs.getString("EVENT_MANAGER_ID")));

            workRequestList.add(wk);
        }
        return workRequestList;
    }
    
    private ArrayList<Hotel> hotelDataMapper(ResultSet rs) throws SQLException{
        ArrayList<Hotel> hotelList = new ArrayList<>();
        //Mapping logic
        while(rs.next()){
            Hotel hotel = new Hotel();
            hotel.setHotelID(Integer.parseInt(rs.getString("H_ID")));
            hotel.setHotelName(rs.getString("H_NAME"));
            hotel.setHotelAddress(rs.getString("H_ADDRESS"));
            hotel.setHotelArea(rs.getString("H_AREA"));
            hotel.setHotelType(rs.getString("H_TYPE"));
            hotel.setCapacity(rs.getString("H_CAPACITY"));
            hotel.setHotelAdmin(rs.getString("H_ADMIN_ID"));
            hotel.setBookedDates(rs.getString("H_BOOKED_DATES"));
            hotel.setPhoto(rs.getString("H_PHOTO"));
            hotel.setEmail(rs.getString("H_EMAIL"));
            hotel.setPhone(rs.getString("H_PHONE"));
            hotel.setStatus(rs.getString("H_STATUS"));
            hotelList.add(hotel);
        }
        return hotelList;
    }
    
    private ArrayList<Catering> cateringDataMapper(ResultSet rs) throws SQLException{
        ArrayList<Catering> cateringList = new ArrayList<>();
        //Mapping logic
        while(rs.next()){
            Catering catering = new Catering();
            catering.setCateringID(Integer.parseInt(rs.getString("C_ID")));
            catering.setCateringName(rs.getString("C_NAME"));
            catering.setCateringAddress(rs.getString("C_ADDRESS"));
            catering.setCateringArea(rs.getString("C_AREA"));
            catering.setPhoto(rs.getString("C_PHOTO"));
            catering.setCapacity(rs.getString("C_CAPACITY"));
            catering.setBookedDates(rs.getString("C_BOOKED_DATES"));
            catering.setCateringAdmin(rs.getString("C_ADMIN"));
            catering.setEmail(rs.getString("C_EMAIL"));
            catering.setPhone(rs.getString("C_PHONE"));
            catering.setStatus(rs.getString("C_STATUS"));
            cateringList.add(catering);
        }
        return cateringList;
    }
    
    private ArrayList<TravelAgent> travelAgentDataMapper(ResultSet rs) throws SQLException{
        ArrayList<TravelAgent> travelAgentList = new ArrayList<>();
        //Mapping logic
        while(rs.next()){
            TravelAgent travelAgent = new TravelAgent();
            travelAgent.setTravelAgentID(Integer.parseInt(rs.getString("TA_ID")));
            travelAgent.setTravelAgentName(rs.getString("TA_NAME"));
            travelAgent.setTravelAgentAddress(rs.getString("TA_ADDRESS"));
            travelAgent.setTravelAgentArea(rs.getString("TA_AREA"));
            travelAgent.setPhoto(rs.getString("TA_PHOTO"));
            travelAgent.setCapacity(rs.getString("TA_CAPACITY"));
            travelAgent.setBookedDates(rs.getString("TA_BOOKED_DATES"));
            travelAgent.setTravelAgentAdmin(rs.getString("TA_ADMIN"));
            travelAgent.setEmail(rs.getString("TA_EMAIL"));
            travelAgent.setPhone(rs.getString("TA_PHONE"));
            travelAgent.setStatus(rs.getString("TA_STATUS"));
            travelAgentList.add(travelAgent);
        }
        return travelAgentList;
    }
    
    public ArrayList<WorkRequest> getAllWorkRequestData() throws SQLException{
        ResultSet resultSet = workRequestRepository.getWorkRequestData();
        return workRequestDataMapper(resultSet); 
    }
    
    public ArrayList<WorkRequest> getWorkRequestByRole(int toId) throws SQLException{
        ArrayList<WorkRequest> filteredWorkRequestList = new ArrayList<>();
        ResultSet resultSet = workRequestRepository.getWorkRequestData();
        ArrayList<WorkRequest> workRequestList = workRequestDataMapper(resultSet);
        
        workRequestList.stream()
                .filter(wk -> wk.getToID()==toId)
                .forEach(wk -> filteredWorkRequestList.add(wk));
        
        return filteredWorkRequestList;
    }
    

    public ArrayList<Hotel> getHotelDataService(int toId, String status)throws SQLException{

        ArrayList<Hotel> hotelDataList = new ArrayList<>();
        ArrayList<Hotel> hotelFilteredList = new ArrayList<>();
        ResultSet resultSet = workRequestRepository.getHotelData(toId);
        hotelDataList = hotelDataMapper(resultSet);
        if(!status.equalsIgnoreCase("ALL")){
            hotelDataList.stream()
                .filter(hotel -> hotel.getStatus().equalsIgnoreCase(status))
                .forEach(hotel -> hotelFilteredList.add(hotel));
            return hotelFilteredList; 
        }
        return hotelDataList;
    }
    
    public ArrayList<Catering> getCateringDataService(int toId, String status)throws SQLException{
        ArrayList<Catering> cateringDataList = new ArrayList<>();
        ArrayList<Catering> cateringFilteredList = new ArrayList<>();
        ResultSet resultSet = workRequestRepository.getCateringData(toId);
        cateringDataList = cateringDataMapper(resultSet);
        if(!status.equalsIgnoreCase("ALL")){
            cateringDataList.stream()
                .filter(catering -> catering.getStatus().equalsIgnoreCase(status))
                .forEach(catering -> cateringFilteredList.add(catering));
            return cateringFilteredList; 
        }
        return cateringDataList; 
    }
    
    public ArrayList<TravelAgent> getTravelAgentDataService(int toId, String status)throws SQLException{
        ArrayList<TravelAgent> travelAgentDataList = new ArrayList<>();
        ArrayList<TravelAgent> travelAgentFilteredList = new ArrayList<>();
        ResultSet resultSet = workRequestRepository.getTravelAgentData(toId);
        travelAgentDataList = travelAgentDataMapper(resultSet);
        if(!status.equalsIgnoreCase("ALL")){
            travelAgentDataList.stream()
                .filter(travel -> travel.getStatus().equalsIgnoreCase(status))
                .forEach(travel -> travelAgentFilteredList.add(travel));
            return travelAgentFilteredList; 
        }
        return travelAgentDataList; 
    }
    
    public ArrayList<WorkRequest> getWorkRequestByEventID(int eventId) throws SQLException{
        ArrayList<WorkRequest> filteredWorkRequestList = new ArrayList<>();
        ResultSet resultSet = workRequestRepository.getWorkRequestData();
        ArrayList<WorkRequest> workRequestList = workRequestDataMapper(resultSet);
        
        workRequestList.stream()
                .filter(wk -> wk.getEventID()==eventId)
                .forEach(wk -> filteredWorkRequestList.add(wk));
        
        return filteredWorkRequestList;
    }
    
    public void createWorkRequestService(WorkRequest wk) throws SQLException {
        try {
            workRequestRepository.createWorkRequest(wk);
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateStatusService(String enterpriseName, String enterpriseType, String status) throws SQLException{
        
        if(enterpriseType.equalsIgnoreCase("HOTEL")){
            //finding hotel based on name in HOTEL table
            ArrayList<Hotel> hotelData = enterpriseService.getAllHotelDataService();
            ArrayList<Hotel> filteredHotelList = new ArrayList<>();
            hotelData.stream()
                    .filter(hotel -> hotel.getHotelName().equalsIgnoreCase(enterpriseName))
                    .forEach(doc -> filteredHotelList.add(doc));
            //getting hotelAdminId from HOTEL table
            int hotelAdminId = Integer.parseInt(filteredHotelList.get(0).getHotelAdmin());
            //using hotelAdminId to find wkId in WORKREQUEST table
            ArrayList<WorkRequest> wk = getAllWorkRequestData();
            ArrayList<WorkRequest> filteredWRList = new ArrayList<>();
            wk.stream()
                    .filter(w -> w.getFromID()==hotelAdminId)
                    .forEach(w -> filteredWRList.add(w));
            //getting wkId from list
            int wkId = filteredWRList.get(0).getID();
            
            //update
            userRepository.updateUserStatus(hotelAdminId, status);
            workRequestRepository.updateWorkRequestDataStatus(wkId, status);
            enterpriseRepository.updateHotelStatus(hotelAdminId, status);
        } else if(enterpriseType.equalsIgnoreCase("CATERING")){
             //finding catering based on name in CATERING table
            ArrayList<Catering> cateringData = enterpriseService.getAllCateringDataService();
            ArrayList<Catering> filteredCateringList = new ArrayList<>();
            cateringData.stream()
                    .filter(catering -> catering.getCateringName().equalsIgnoreCase(enterpriseName))
                    .forEach(catering -> filteredCateringList.add(catering));
            //getting cateringAdminId from CATERING table
            int cateringAdminId = Integer.parseInt(filteredCateringList.get(0).getCateringAdmin());
            //using cateringAdminId to find wkId in WORKREQUEST table
            ArrayList<WorkRequest> wk = getAllWorkRequestData();
            ArrayList<WorkRequest> filteredWRList = new ArrayList<>();
            wk.stream()
                    .filter(w -> w.getFromID()==cateringAdminId)
                    .forEach(w -> filteredWRList.add(w));
            //getting wkId from list
            int wkId = filteredWRList.get(0).getID();
            
            //update
            userRepository.updateUserStatus(cateringAdminId, status);
            workRequestRepository.updateWorkRequestDataStatus(wkId, status);
            enterpriseRepository.updateCateringStatus(cateringAdminId, status);
        } else if(enterpriseType.equalsIgnoreCase("TRAVEL")){
            //finding travel based on name in TRAVEL table
            ArrayList<TravelAgent> travelData = enterpriseService.getAllTravelDataService();
            ArrayList<TravelAgent> filteredTravelList = new ArrayList<>();
            travelData.stream()
                    .filter(travel -> travel.getTravelAgentName().equalsIgnoreCase(enterpriseName))
                    .forEach(travel -> filteredTravelList.add(travel));
            //getting travelAdminId from TRAVELAGENT table
            int travelAdminId = Integer.parseInt(filteredTravelList.get(0).getTravelAgentAdmin());
            //using cateringAdminId to find wkId in WORKREQUEST table
            ArrayList<WorkRequest> wk = getAllWorkRequestData();
            ArrayList<WorkRequest> filteredWRList = new ArrayList<>();
            wk.stream()
                    .filter(w -> w.getFromID()==travelAdminId)
                    .forEach(w -> filteredWRList.add(w));
            //getting wkId from list
            int wkId = filteredWRList.get(0).getID();
            
            //update
            userRepository.updateUserStatus(travelAdminId, status);
            workRequestRepository.updateWorkRequestDataStatus(wkId, status);
            enterpriseRepository.updateTravelStatus(travelAdminId, status);
        }
        
     
        
    }
    
    public void updateStatusEventAdminFlow(String eventName, int eventManagerId) throws SQLException{
        
        
            //finding hotel based on name in HOTEL table
            ArrayList<Event> eventData = eventService.getAllEventListService();
            ArrayList<Event> filteredEventList = new ArrayList<>();
            eventData.stream()
                    .filter(evt -> evt.getEventName().equalsIgnoreCase(eventName))
                    .forEach(evt -> filteredEventList.add(evt));
            
            //getting eventId from EVENT table
            int eventId = filteredEventList.get(0).getEventID();
//            System.out.println("EVENT ID - " + eventId);
            //using hotelAdminId to find wkId in WORKREQUEST table
            ArrayList<WorkRequest> wk = getAllWorkRequestData();
            ArrayList<WorkRequest> filteredWRList = new ArrayList<>();
            wk.stream()
                    .filter(w -> w.getEventID()==eventId)
                    .forEach(w -> filteredWRList.add(w));
            
            //getting wkId from list
            int wkId = filteredWRList.get(0).getID();
//            System.out.println("WK ID - " + wkId);
            //update
            workRequestRepository.updateWorkRequestDataEventManagerId(wkId, eventManagerId);
            workRequestRepository.updateWorkRequestDataStatus(wkId);
            eventRepository.updateEventAdminFlowEventManagerId(eventId, eventManagerId);
            eventRepository.updateEventAdminFlowStatus(eventId);
            
        
    }
    
    public void updateHotelAdminWorkFlowStatusService(String eventName, int hotelAdminId, String status) throws SQLException{
        
        //finding hotel based on name in HOTEL table
        ArrayList<Event> eventData = eventService.getAllEventListService();
        ArrayList<Event> filteredEventList = new ArrayList<>();
        eventData.stream()
            .filter(evt -> evt.getEventName().equalsIgnoreCase(eventName))
            .forEach(evt -> filteredEventList.add(evt));
            
        //getting eventId from EVENT table
        int eventId = filteredEventList.get(0).getEventID();
        
        //get hotelID using hotelAdminId
        ArrayList<Hotel> hotelData = enterpriseService.getAllHotelDataService();
        ArrayList<Hotel> filteredHotelList = new ArrayList<>();
        hotelData.stream()
            .filter(hotel -> hotel.getHotelAdmin().equalsIgnoreCase(String.valueOf(hotelAdminId)))
            .forEach(doc -> filteredHotelList.add(doc));
        int hotelId = filteredHotelList.get(0).getHotelID();
        
        if(status.equalsIgnoreCase("ACCEPTED")){
            eventRepository.updateHotelAdminFlowChosenHotelId(hotelId, eventId);
        }
        
        
        ArrayList<WorkRequest> wk = getAllWorkRequestData();
        ArrayList<WorkRequest> filteredWRList = new ArrayList<>();
        wk.stream()
            .filter(w -> w.getEventID()==eventId && w.getType().equalsIgnoreCase("EVENTMANAGER_HOTELADMIN"))
            .forEach(w -> filteredWRList.add(w));
        int lastIndex = filteredWRList.size()-1;
        int wkId = filteredWRList.get(lastIndex).getID();
        workRequestRepository.updateWorkRequestDataStatus(wkId, status);
    }
    
    
    public void updateCateringAdminWorkFlowStatusService(String eventName, int cateringAdminId, String status) throws SQLException{
        
        //finding hotel based on name in HOTEL table
        ArrayList<Event> eventData = eventService.getAllEventListService();
        ArrayList<Event> filteredEventList = new ArrayList<>();
        eventData.stream()
            .filter(evt -> evt.getEventName().equalsIgnoreCase(eventName))
            .forEach(evt -> filteredEventList.add(evt));
            
        //getting eventId from EVENT table
        int eventId = filteredEventList.get(0).getEventID();
        
        //get cateringID using cateringAdminId
        ArrayList<Catering> cateringData = enterpriseService.getAllCateringDataService();
        ArrayList<Catering> filteredCateringList = new ArrayList<>();
        cateringData.stream()
            .filter(catering -> catering.getCateringAdmin().equalsIgnoreCase(String.valueOf(cateringAdminId)))
            .forEach(catering -> filteredCateringList.add(catering));
        int cateringId = filteredCateringList.get(0).getCateringID();
        
        if(status.equalsIgnoreCase("ACCEPTED")){
            eventRepository.updateCateringAdminFlowChosenCateringId(cateringId, eventId);
        }
        
        
        ArrayList<WorkRequest> wk = getAllWorkRequestData();
        ArrayList<WorkRequest> filteredWRList = new ArrayList<>();
        wk.stream()
            .filter(w -> w.getEventID()==eventId && w.getType().equalsIgnoreCase("EVENTMANAGER_CATERINGADMIN"))
            .forEach(w -> filteredWRList.add(w));
        int lastIndex = filteredWRList.size()-1;
        int wkId = filteredWRList.get(lastIndex).getID();
        workRequestRepository.updateWorkRequestDataStatus(wkId, status);
    }
    
    public void updateTravelAgentAdminWorkFlowStatusService(String eventName, int travelAdminId, String status) throws SQLException{
        
        //finding hotel based on name in HOTEL table
        ArrayList<Event> eventData = eventService.getAllEventListService();
        ArrayList<Event> filteredEventList = new ArrayList<>();
        eventData.stream()
            .filter(evt -> evt.getEventName().equalsIgnoreCase(eventName))
            .forEach(evt -> filteredEventList.add(evt));
            
        //getting eventId from EVENT table
        int eventId = filteredEventList.get(0).getEventID();
        
        //get cateringID using cateringAdminId
        ArrayList<TravelAgent> cateringData = enterpriseService.getAllTravelDataService();
        ArrayList<TravelAgent> filteredTravelList = new ArrayList<>();
        cateringData.stream()
            .filter(travel -> travel.getTravelAgentAdmin().equalsIgnoreCase(String.valueOf(travelAdminId)))
            .forEach(travel -> filteredTravelList.add(travel));
        int travelId = filteredTravelList.get(0).getTravelAgentID();
        
        if(status.equalsIgnoreCase("ACCEPTED")){
            eventRepository.updateTravelAgentAdminFlowChosenCateringId(travelId, eventId);
        }
        
        
        ArrayList<WorkRequest> wk = getAllWorkRequestData();
        ArrayList<WorkRequest> filteredWRList = new ArrayList<>();
        wk.stream()
            .filter(w -> w.getEventID()==eventId && w.getType().equalsIgnoreCase("EVENTMANAGER_TRAVELAGENTADMIN"))
            .forEach(w -> filteredWRList.add(w));
        int lastIndex = filteredWRList.size()-1;
        int wkId = filteredWRList.get(lastIndex).getID();
        workRequestRepository.updateWorkRequestDataStatus(wkId, status);
    }

    
}
