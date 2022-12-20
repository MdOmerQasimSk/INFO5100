/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author omerq
 */
public class EmployeeCatalogue {
    
    private ArrayList<Employee> employeeList;

    public EmployeeCatalogue() {
        employeeList = new ArrayList<Employee>();
    }

    public ArrayList<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(ArrayList<Employee> employeeList) {
        this.employeeList = employeeList;
    }
    
    //Add employee profile
    public void addEmployee(Employee employee){
       employeeList.add(employee); 
    }
    
    //Update employee profile
    public void updateEmployee(Employee employee, ArrayList<Employee> list){
       //Iterate to find emp
       for(Employee emp: list){ 
           if(employee.getEmpId() == emp.getEmpId()){
               emp.setName(employee.getName());
               emp.setGender(employee.getGender());
               emp.setAge(employee.getAge());
               emp.setLevel(employee.getLevel());
               emp.setStartDate(employee.getStartDate());
               emp.setTeamInfo(employee.getTeamInfo());
               emp.setPositionTitle(employee.getPositionTitle());
               emp.setPhoneNumber(employee.getPhoneNumber());
               emp.setEmail(employee.getEmail());
               emp.setEmpPhoto(employee.getEmpPhoto());
           }
        }
    }
    
    
    //Remove employee profile
    public void removeEmployee(Employee employee){
       employeeList.remove(employee);
    }   
    
    //Advanced Search feature
    public ArrayList<Employee> searchByOption(String searchType, String searchValue, ArrayList<Employee> list){
        ArrayList<Employee> filteredList = new ArrayList<Employee>();
        
        //Search based on selected dropdown value
        if(searchType.equalsIgnoreCase("Emp ID")){
           list.stream().filter(emp -> emp.getEmpId()==Integer.parseInt(searchValue)).forEach(emp -> filteredList.add(emp));
        } else if(searchType.equalsIgnoreCase("Name")){
           list.stream().filter(emp -> emp.getName().equalsIgnoreCase(searchValue)).forEach(emp -> filteredList.add(emp));
        } else if(searchType.equalsIgnoreCase("Team")){
           list.stream().filter(emp -> emp.getTeamInfo().equalsIgnoreCase(searchValue)).forEach(emp -> filteredList.add(emp));
        } else if(searchType.equalsIgnoreCase("Level")){
           list.stream().filter(emp -> emp.getLevel().equalsIgnoreCase(searchValue)).forEach(emp -> filteredList.add(emp));
        } else if(searchType.equalsIgnoreCase("Title")){
           list.stream().filter(emp -> emp.getPositionTitle().equalsIgnoreCase(searchValue)).forEach(emp -> filteredList.add(emp));
        } else if(searchType.equalsIgnoreCase("Telephone")){
           list.stream().filter(emp -> emp.getPhoneNumber().equalsIgnoreCase(searchValue)).forEach(emp -> filteredList.add(emp));
        } else if(searchType.equalsIgnoreCase("Email")){
           list.stream().filter(emp -> emp.getEmail().equalsIgnoreCase(searchValue)).forEach(emp -> filteredList.add(emp));
        } else if(searchType.equalsIgnoreCase("Start Date")){
           list.forEach(emp -> {
               String startDate = new SimpleDateFormat("dd-MM-yyyy").format(emp.getStartDate());
               if(startDate.equalsIgnoreCase(searchValue)) filteredList.add(emp);
           });
        } else {
            return filteredList; //return null when nothing is selected
        }
        
        return filteredList;
    }
}
