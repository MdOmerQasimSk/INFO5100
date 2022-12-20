/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maple.backend.model;

import java.util.ArrayList;

/**
 *
 * @author bharv
 */
public class HotelDirectory {

    private ArrayList<Hotel> hotelList;

    public ArrayList<Hotel> getHotelList() {
        return hotelList;
    }

    public void setHotelList(ArrayList<Hotel> hotelList) {
        this.hotelList = hotelList;
    }

    //Add hotel profile
    public void addDoctor(Hotel hotel) {
        hotelList.add(hotel);
    }

    //Update hotel profile
    public void updateDoctor(Hotel hotel, ArrayList<Hotel> list) {
        //Iterate to find hotelID
        for (Hotel hot : list) {
            if (hotel.getHotelID() == hot.getHotelID()) {
                hot.setHotelID(hotel.getHotelID());
                hot.setHotelAddress(hotel.getHotelAddress());
                hot.setHotelName(hotel.getHotelName());
                hot.setHotelArea(hotel.getHotelArea());
                hot.setHotelType(hotel.getHotelType());
                hot.setPhoto(hotel.getPhoto());
                hot.setCapacity(hotel.getCapacity());
                hot.setBookedDates(hotel.getBookedDates());
                hot.setHotelAdmin(hotel.getHotelAdmin());
            }
        }
    }
    
    //Remove doctor profile
    public void removeDoctor(Hotel hotel){
       hotelList.remove(hotel);
    } 
    
//    //Advanced Search feature
//    public ArrayList<Hotel> searchByOption(String searchType, String searchValue, ArrayList<Hotel> list){
//        ArrayList<Hotel> filteredList = new ArrayList<Hotel>();
//        
//        //Search based on selected dropdown value
//        if(searchType.equalsIgnoreCase("HotelName")){
//           list.stream().filter(doc -> doc.getHotelName().equalsIgnoreCase(searchValue)).forEach(doc -> filteredList.add(doc));
//        } else if(searchType.equalsIgnoreCase("HotelType")){
//           list.stream().filter(doc -> doc.getHotelType().equalsIgnoreCase(searchValue)).forEach(doc -> filteredList.add(doc));
//        } else {
//            return filteredList; //return null when nothing is selected
//        }    
//        return filteredList;
//    }
}
