/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maple.backend.model;

import java.util.ArrayList;

/**
 *
 * @author mohammedomerqasimshaik
 */
public class UserDirectory {
    
    private ArrayList<User> userList;

    public UserDirectory(ArrayList<User> userList) {
        this.userList = userList;
    }
    
    public ArrayList<User> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }
    
    //Add user
    public void addWorkUser(User user) {
        userList.add(user);
    }
    
    //Remove user
    public void removeUser(User user){
       userList.remove(user);
    } 
    
    //Update user
    public void updateUser(User user) {
        //Iterate to find userId
        for (User us : userList) {
            if (us.getID() == user.getID()) {
                us.setEmail(user.getEmail());
                us.setName(user.getName());
                us.setPassword(user.getPassword());
                us.setPhoto(user.getPhoto());
                us.setPostalCode(user.getPostalCode());
                us.setRole(user.getRole());
                us.setStatus(user.getStatus());
                us.setUserName(user.getUserName());
            }
        }
    }
}
