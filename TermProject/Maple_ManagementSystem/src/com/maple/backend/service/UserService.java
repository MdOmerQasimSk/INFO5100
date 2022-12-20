/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maple.backend.service;

import com.maple.backend.model.User;
import com.maple.backend.repository.UserRepository;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;


/**
 *
 * @author DKapoor
 */
public class UserService {
    UserRepository userRepository;
    
    private ArrayList<User> userDataMapper(ResultSet rs) throws SQLException{
        ArrayList<User> userData = new ArrayList<>();
        //Mapping logic
        while(rs.next()){
            User u = new User();
            u.setID(Integer.parseInt(rs.getString("ID")));
            u.setEmail(rs.getString("email"));
            u.setPhoneNum(rs.getString("phoneNumber"));
            u.setName(rs.getString("name"));
            u.setStatus(rs.getString("status"));
            u.setPassword(rs.getString("password"));
            u.setUserName(rs.getString("userName"));
            u.setPostalCode(rs.getString("postalCode"));
            u.setRole(rs.getString("role"));
            u.setPhoto(rs.getString("photo"));
            userData.add(u);
        }
        return userData;
    }

    public UserService() throws SQLException {
        userRepository = new UserRepository();
    }
    public void insertUserDataService(int ID, String role, String name, String email, String phoneNum, String userName, String password, String path, String postalCode, String status) throws SQLException{
        userRepository.insertUserData(ID, role, name, email, phoneNum, userName, password, path, postalCode, status);
    }
    public ArrayList<User> getUserDataService(String role) throws SQLException{
        ArrayList<User> userData = new ArrayList<>();
        ResultSet resultSet = userRepository.getUserData(role);
        userData = userDataMapper(resultSet);

        return userData;
                
    }
    
    public ArrayList<User> getAllUsers() throws SQLException{
        ArrayList<User> userData = new ArrayList<>();
        ResultSet resultSet = userRepository.getAllUsers();
        userData = userDataMapper(resultSet);

        return userData;
    }
    
    public ArrayList<User> getEventManagerListService() throws SQLException{
        ArrayList<User> userData = new ArrayList<>();
        ArrayList<User> filteredUserData = new ArrayList<>();
        ResultSet resultSet = userRepository.getAllUsers();
        userData = userDataMapper(resultSet);
        userData.stream()
                .filter(user -> user.getRole().equalsIgnoreCase("EVENT MANAGER"))
                .forEach(user -> filteredUserData.add(user));
        return filteredUserData;
    }
    
    public ArrayList<User> getUserById(int id) throws SQLException {
        ArrayList<User> userData = new ArrayList<>();
        ResultSet resultSet = userRepository.getUserById(id);
        userData = userDataMapper(resultSet);

        return userData;
    }
    
    public int updateUserPasswordService(ArrayList<User> userData, String oldPwd, String newPwd, String confirmPwd) throws SQLException {
        if(newPwd.equals("") || confirmPwd.equals("") || oldPwd.equals("")){
            return -3;
        }
        if(oldPwd.equals(userData.get(0).getPassword()) && newPwd.equals(confirmPwd)){
            userRepository.updateUserPassword(newPwd, userData.get(0).getID());
            return 0;
        } else if(!newPwd.equals(confirmPwd)){
            return -1;
        } 
        return -2;
    }
    
    public int getUserByNameService(String name) throws SQLException {
        ArrayList<User> userData = new ArrayList<>();
        ResultSet resultSet = userRepository.getAllUsers();
        userData = userDataMapper(resultSet);
        ArrayList<User> filteredData = new ArrayList<>();
        userData.stream()
                .filter(user -> user.getName().equalsIgnoreCase(name))
                .forEach(user -> filteredData.add(user));
        
        return filteredData.get(0).getID();
    } 
    
    public ArrayList<User> getMapleAdminAndManagerDataService() throws SQLException {
        ArrayList<User> userData = new ArrayList<>();
        ResultSet resultSet = userRepository.getAllUsers();
        userData = userDataMapper(resultSet);
        ArrayList<User> filteredData = new ArrayList<>();
        userData.stream()
                .filter(user -> user.getRole().equalsIgnoreCase("EVENT ADMIN") || 
                        user.getRole().equalsIgnoreCase("BUSINESS ADMIN") || 
                        user.getRole().equalsIgnoreCase("EVENT MANAGER"))
                .forEach(user -> filteredData.add(user));
        
        return filteredData;
    } 
    
    public ArrayList<User> getMapleAdminDataService() throws SQLException {
        ArrayList<User> userData = new ArrayList<>();
        ResultSet resultSet = userRepository.getAllUsers();
        userData = userDataMapper(resultSet);
        ArrayList<User> filteredData = new ArrayList<>();
        userData.stream()
                .filter(user -> user.getRole().equalsIgnoreCase("EVENT ADMIN") || 
                        user.getRole().equalsIgnoreCase("BUSINESS ADMIN"))
                .forEach(user -> filteredData.add(user));
        
        return filteredData;
    } 
    
    public ArrayList<User> getMapleManagerDataService() throws SQLException {
        ArrayList<User> userData = new ArrayList<>();
        ResultSet resultSet = userRepository.getAllUsers();
        userData = userDataMapper(resultSet);
        ArrayList<User> filteredData = new ArrayList<>();
        userData.stream()
                .filter(user -> user.getRole().equalsIgnoreCase("EVENT MANAGER"))
                .forEach(user -> filteredData.add(user));
        
        return filteredData;
    }
    
}
