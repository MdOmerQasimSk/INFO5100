/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.maple.frontend.eventAdminScreen;

import com.maple.backend.controller.EventController;
import com.maple.backend.controller.WorkRequestController;
import com.maple.backend.model.Event;
import com.maple.backend.model.User;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mohammedomerqasimshaik
 */

public class EventAdminDashboard extends javax.swing.JPanel {
    
    WorkRequestController workRequestController;
    EventController eventController;
    ArrayList<User> userData;
    
    int eventAdminId;
    int publicClick = 0;
    int privateClick = 0;
    
    public EventAdminDashboard(ArrayList<User> userData) throws SQLException {
        initComponents();
        workRequestController = new WorkRequestController();
        eventController = new EventController();
        this.userData = userData;
        eventAdminId = userData.get(0).getID();
        populateCardData();
        populateTableData("ALL");
        table.fixTable(jScrollPane1); //apply table theme
    }
    
    //Table Data
    private void populateTableData(String type) throws SQLException {
        
        DefaultTableModel dtmodel = (DefaultTableModel) table.getModel();
        dtmodel.setRowCount(0);
        ArrayList<Event> eventList = new ArrayList<>(); 
        ArrayList<Event> publicEventList = new ArrayList<>();
        ArrayList<Event> privateEventList = new ArrayList<>();
        
        ArrayList<Event> publicCompletedEventList = new ArrayList<>();
        ArrayList<Event> privateCompletedEventList = new ArrayList<>();
           
        if(type.equalsIgnoreCase("PUBLIC")){
            publicEventList = eventController.getPublicEventList();
            publicEventList.stream()
                .filter(evt -> !evt.getStatus().equalsIgnoreCase("PENDING"))
                .forEach(evt -> publicCompletedEventList.add(evt));
            for(Event evt: publicCompletedEventList){
                Object[] obj = new Object[5];
                obj[0] = evt.getEventName();
                obj[1] = evt.getEventType();
                obj[2] = evt.getEventFrom();
                obj[3] = evt.getEventTo();
                obj[4] = evt.getEventManagerID();
                dtmodel.addRow(obj);
            }
        } else if(type.equalsIgnoreCase("PRIVATE")){
            privateEventList = eventController.getPrivateEventList();
            privateEventList.stream()
                .filter(evt -> !evt.getStatus().equalsIgnoreCase("PENDING"))
                .forEach(evt -> privateCompletedEventList.add(evt));
            for(Event evt: privateCompletedEventList){
                Object[] obj = new Object[5];
                obj[0] = evt.getEventName();
                obj[1] = evt.getEventType();
                obj[2] = evt.getEventFrom();
                obj[3] = evt.getEventTo();
                obj[4] = evt.getEventManagerID();
                dtmodel.addRow(obj);
            }
        } else{ 
            publicEventList = eventController.getPublicEventList();
            publicEventList.stream()
                .filter(evt -> !evt.getStatus().equalsIgnoreCase("PENDING"))
                .forEach(evt -> publicCompletedEventList.add(evt));
            for(Event evt: publicCompletedEventList){
                Object[] obj = new Object[5];
                obj[0] = evt.getEventName();
                obj[1] = evt.getEventType();
                obj[2] = evt.getEventFrom();
                obj[3] = evt.getEventTo();
                obj[4] = evt.getEventManagerID();
                dtmodel.addRow(obj);
            }
            privateEventList = eventController.getPrivateEventList();
            privateEventList.stream()
                .filter(evt -> !evt.getStatus().equalsIgnoreCase("PENDING"))
                .forEach(evt -> privateCompletedEventList.add(evt));
            for(Event evt: privateCompletedEventList){
                Object[] obj = new Object[5];
                obj[0] = evt.getEventName();
                obj[1] = evt.getEventType();
                obj[2] = evt.getEventFrom();
                obj[3] = evt.getEventTo();
                obj[4] = evt.getEventManagerID();
                dtmodel.addRow(obj);
            }  
        }
    }
    
    //Card Data
    private void populateCardData() throws SQLException{
        
        //Load Icons
        jPrivateEvent.setIcon(new ImageIcon(getClass().getResource("/com/maple/icons/hotel.png")));
        jPublicEvent.setIcon(new ImageIcon(getClass().getResource("/com/maple/icons/hotel.png")));
          
        //Set Description
        jPrivateEvent.setDescription("Private Events");
        jPublicEvent.setDescription("Public Events");
        
        ArrayList<Event> publicCompletedEventList = new ArrayList<>();
        eventController.getPublicEventList().stream()
                .filter(evt -> !evt.getStatus().equalsIgnoreCase("PENDING"))
                .forEach(evt -> publicCompletedEventList.add(evt));
        
        ArrayList<Event> privateCompletedEventList = new ArrayList<>();
        eventController.getPrivateEventList().stream()
                .filter(evt -> !evt.getStatus().equalsIgnoreCase("PENDING"))
                .forEach(evt -> privateCompletedEventList.add(evt));
        //Assign Request Values
        jPrivateEvent.setValues("# " + privateCompletedEventList.size());
        jPublicEvent.setValues("# " + publicCompletedEventList.size());
       
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPrivateEvent = new com.maple.resources.Card();
        jTablePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new com.maple.resources.Table();
        jLocation = new javax.swing.JLabel();
        jRefreshTableBtn = new com.maple.resources.ButtonBadges();
        jPublicEvent = new com.maple.resources.Card();

        setBackground(new java.awt.Color(245, 241, 241));
        setMaximumSize(new java.awt.Dimension(1196, 720));
        setMinimumSize(new java.awt.Dimension(1196, 720));
        setPreferredSize(new java.awt.Dimension(1196, 720));
        setRequestFocusEnabled(false);

        jPrivateEvent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPrivateEventMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPrivateEventMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPrivateEventMouseExited(evt);
            }
        });

        jTablePanel.setBackground(new java.awt.Color(255, 255, 255));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Event Name", "Event Type", "Event From", "Event To", "Event Manager"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(3).setResizable(false);
            table.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout jTablePanelLayout = new javax.swing.GroupLayout(jTablePanel);
        jTablePanel.setLayout(jTablePanelLayout);
        jTablePanelLayout.setHorizontalGroup(
            jTablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jTablePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE)
                .addContainerGap())
        );
        jTablePanelLayout.setVerticalGroup(
            jTablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jTablePanelLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jLocation.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLocation.setForeground(new java.awt.Color(4, 72, 210));
        jLocation.setText("Admin / Dashboard");

        jRefreshTableBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/maple/icons/refresh.png"))); // NOI18N
        jRefreshTableBtn.setMaximumSize(new java.awt.Dimension(20, 20));
        jRefreshTableBtn.setMinimumSize(new java.awt.Dimension(20, 20));
        jRefreshTableBtn.setPreferredSize(new java.awt.Dimension(20, 20));
        jRefreshTableBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRefreshTableBtnActionPerformed(evt);
            }
        });

        jPublicEvent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPublicEventMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPublicEventMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPublicEventMouseExited(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLocation)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jRefreshTableBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(350, 350, 350)
                        .addComponent(jPrivateEvent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(60, 60, 60)
                    .addComponent(jPublicEvent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(894, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLocation)
                .addGap(30, 30, 30)
                .addComponent(jPrivateEvent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jRefreshTableBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(95, 95, 95)
                    .addComponent(jPublicEvent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(509, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jRefreshTableBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRefreshTableBtnActionPerformed
        try { 
            populateTableData("ALL");
            
            //Reset card colors
            jPrivateEvent.setBackground(Color.white);
            jPublicEvent.setBackground(Color.white);
            //Reset card click counter
            publicClick = 0;
            privateClick = 0;
            populateCardData();
        } catch (SQLException ex) {
            Logger.getLogger(EventAdminDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jRefreshTableBtnActionPerformed

    private void jPrivateEventMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPrivateEventMouseClicked
        //Apply CSS
        jPrivateEvent.setBackground(Color.CYAN);
        jPublicEvent.setBackground(Color.white);
        
        try {
            //Refresh Table Data
            populateTableData("PRIVATE");
        } catch (SQLException ex) {
            
        }
        
        publicClick = 0;
        privateClick = 1;
    }//GEN-LAST:event_jPrivateEventMouseClicked

    private void jPrivateEventMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPrivateEventMouseEntered
        // TODO add your handling code here:
        jPrivateEvent.setBackground(Color.CYAN);
    }//GEN-LAST:event_jPrivateEventMouseEntered

    private void jPrivateEventMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPrivateEventMouseExited
        // TODO add your handling code here:
        if(privateClick==1 && publicClick==0){
            jPrivateEvent.setBackground(Color.CYAN);
            return;
        }
            jPrivateEvent.setBackground(Color.white);
            privateClick = 0;
    }//GEN-LAST:event_jPrivateEventMouseExited

    private void jPublicEventMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPublicEventMouseClicked
        // TODO add your handling code here:
        //Apply CSS
        jPrivateEvent.setBackground(Color.white);
        jPublicEvent.setBackground(Color.CYAN);
        
        try {
            //Refresh Table Data
            populateTableData("PUBLIC");
        } catch (SQLException ex) {
            
        }
        
        publicClick = 1;
        privateClick = 0;
    }//GEN-LAST:event_jPublicEventMouseClicked

    private void jPublicEventMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPublicEventMouseEntered
        // TODO add your handling code here:
        jPublicEvent.setBackground(Color.CYAN);
    }//GEN-LAST:event_jPublicEventMouseEntered

    private void jPublicEventMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPublicEventMouseExited
        // TODO add your handling code here:
        if(publicClick==1 && privateClick==0 ){
            jPublicEvent.setBackground(Color.CYAN);
            return;
        }
        jPublicEvent.setBackground(Color.white);
        publicClick = 0;
    }//GEN-LAST:event_jPublicEventMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLocation;
    private com.maple.resources.Card jPrivateEvent;
    private com.maple.resources.Card jPublicEvent;
    private com.maple.resources.ButtonBadges jRefreshTableBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jTablePanel;
    private com.maple.resources.Table table;
    // End of variables declaration//GEN-END:variables


}
