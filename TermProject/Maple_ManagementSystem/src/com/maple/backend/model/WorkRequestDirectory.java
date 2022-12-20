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
public class WorkRequestDirectory {
    
    private ArrayList<WorkRequest> workRequestList;

    public WorkRequestDirectory() {
        workRequestList = new ArrayList<>();
    }
    
    public ArrayList<WorkRequest> getWorkRequestList() {
        return workRequestList;
    }

    public void setWorkRequestList(ArrayList<WorkRequest> workRequestList) {
        this.workRequestList = workRequestList;
    }
    
    //Add workRequest
    public void addWorkRequest(WorkRequest workRequest) {
        workRequestList.add(workRequest);
    }
    
    //Remove workRequest
    public void removeWorkRequest(WorkRequest workRequest){
       workRequestList.remove(workRequest);
    } 
    
    //Update workRequest
    public void updateWorkRequest(WorkRequest workRequest) {
        //Iterate to find workRequestID
        for (WorkRequest wk : workRequestList) {
            if (wk.getID() == workRequest.getID()) {
                wk.setEventID(workRequest.getEventID());
                wk.setEventManagerID(workRequest.getEventManagerID());
                wk.setFromID(workRequest.getFromID());
                wk.setStatus(workRequest.getStatus());
                wk.setToID(workRequest.getToID());
                wk.setType(workRequest.getType());
            }
        }
    }
    
}
