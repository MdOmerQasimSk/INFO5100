/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.maple.frontend.eventAdminScreen;

import com.maple.backend.controller.EventController;
import com.maple.backend.controller.UserController;
import com.maple.backend.controller.WorkRequestController;
import com.maple.backend.model.Event;
import com.maple.backend.model.User;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mohammedomerqasimshaik
 */
public class EventAdminRequest extends javax.swing.JPanel {

    /**
     * Creates new form BusinessAdminRequest
     */
    WorkRequestController workRequestController;
    EventController eventController;
    UserController userController;
    ArrayList<User> userData;
    
    int eventAdminId;
    int publicClick = 0;
    int privateClick = 0;
    
    public EventAdminRequest(ArrayList<User> userData) throws SQLException {
        initComponents();
        workRequestController = new WorkRequestController();
        eventController = new EventController();
        userController = new UserController();
        this.userData = userData;
        eventAdminId = userData.get(0).getID();
        populateCardData();
        populateTableData("ALL");
        table.fixTable(jScrollPane2);
        jAcceptBtn.setEnabled(false);
        populateDropDown();
    }
    
    private void populateDropDown() throws SQLException{
        ArrayList<User> managerList = userController.getEventManagerList();
        ArrayList<String> filteredManagerList = new ArrayList<>();
        filteredManagerList.add("-- Select --");
        
        managerList.stream()
            .forEach(action -> filteredManagerList.add(action.getName()));
        jManagerDropdown.setModel(new DefaultComboBoxModel<>(filteredManagerList.toArray(new String[0])));
        
    }
    
    //Card Data
    public void populateCardData() throws SQLException{
        
        //Load Icons
        jPublicEvent.setIcon(new ImageIcon(getClass().getResource("/com/maple/icons/hotel.png")));
        jPrivateEvent.setIcon(new ImageIcon(getClass().getResource("/com/maple/icons/catering.png")));
        
        //Set Description
        jPublicEvent.setDescription("Public Event Requests");
        jPrivateEvent.setDescription("Private Event Requests");
        
        ArrayList<Event> publicPendingEventList = new ArrayList<>();
        eventController.getPublicEventList().stream()
                .filter(evt -> evt.getStatus().equalsIgnoreCase("PENDING"))
                .forEach(evt -> publicPendingEventList.add(evt));
        
        ArrayList<Event> privatePendingEventList = new ArrayList<>();
        eventController.getPrivateEventList().stream()
                .filter(evt -> evt.getStatus().equalsIgnoreCase("PENDING"))
                .forEach(evt -> privatePendingEventList.add(evt));
        //Assign Request Values
        jPrivateEvent.setValues("# " + privatePendingEventList.size());
        jPublicEvent.setValues("# " + publicPendingEventList.size());
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
                .filter(evt -> evt.getStatus().equalsIgnoreCase("PENDING"))
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
                .filter(evt -> evt.getStatus().equalsIgnoreCase("PENDING"))
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
                .filter(evt -> evt.getStatus().equalsIgnoreCase("PENDING"))
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
                .filter(evt -> evt.getStatus().equalsIgnoreCase("PENDING"))
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRoute = new javax.swing.JLabel();
        jPublicEvent = new com.maple.resources.Card();
        jPrivateEvent = new com.maple.resources.Card();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new com.maple.resources.Table();
        jRefreshTableBtn = new com.maple.resources.ButtonBadges();
        jAcceptBtn = new com.maple.resources.ButtonBadges();
        jManagerDropdown = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(245, 241, 241));
        setMaximumSize(new java.awt.Dimension(1196, 720));
        setMinimumSize(new java.awt.Dimension(1196, 720));
        setPreferredSize(new java.awt.Dimension(1196, 720));

        jRoute.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jRoute.setForeground(new java.awt.Color(4, 72, 210));
        jRoute.setText("Admin / Requests");

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

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

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
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Event Name", "Event Description", "Event From", "Event To", "Event Area"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(3).setResizable(false);
            table.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103))
        );

        jRefreshTableBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/maple/icons/refresh.png"))); // NOI18N
        jRefreshTableBtn.setMaximumSize(new java.awt.Dimension(20, 20));
        jRefreshTableBtn.setMinimumSize(new java.awt.Dimension(20, 20));
        jRefreshTableBtn.setPreferredSize(new java.awt.Dimension(20, 20));
        jRefreshTableBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRefreshTableBtnActionPerformed(evt);
            }
        });

        jAcceptBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/maple/icons/tick.png"))); // NOI18N
        jAcceptBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAcceptBtnActionPerformed(evt);
            }
        });

        jManagerDropdown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select --" }));
        jManagerDropdown.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jRefreshTableBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPublicEvent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(40, 40, 40)
                            .addComponent(jPrivateEvent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(86, 86, 86)
                            .addComponent(jManagerDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jAcceptBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jRoute)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(340, 340, 340))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jRoute)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPublicEvent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPrivateEvent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jManagerDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(jAcceptBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addComponent(jRefreshTableBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jPublicEventMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPublicEventMouseClicked
        
        try {
            
            //Disable buttons
            jAcceptBtn.setEnabled(false);
            
            //Apply CSS
            jPublicEvent.setBackground(Color.CYAN);
            jPrivateEvent.setBackground(Color.white);
            
            //Refresh Table Data
            populateTableData("PUBLIC");
            
            publicClick = 1;
            privateClick = 0;
        } catch (SQLException ex) {
            Logger.getLogger(EventAdminRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }//GEN-LAST:event_jPublicEventMouseClicked

    private void jAcceptBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAcceptBtnActionPerformed

        try {
            
            //Select row data
            int selectedRow = table.getSelectedRow();
            DefaultTableModel dtmodel = (DefaultTableModel) table.getModel();
            Object eventName = (Object) dtmodel.getValueAt(selectedRow, 0); //eventName
            String managerName = jManagerDropdown.getSelectedItem().toString();
            
            //find managerId 
            int managerId = userController.getUserByName(managerName);
            
            //update STATUS -> Assigned Manager, EVENT_MANAGER_ID in WORK_REQUEST & in event table
            workRequestController.updateStatusForEventAdminToManager(eventName.toString(), managerId);
            
            
            //refresh data in card and table
            populateCardData();
            populateTableData("ALL");
            //Reset card colors
            jPublicEvent.setBackground(Color.white);
            jPrivateEvent.setBackground(Color.white);
            //Reset card click counter
            publicClick = 0;
            privateClick = 0;
            
            //Disable buttons
            jAcceptBtn.setEnabled(false);
            
        } catch (SQLException ex) {
            Logger.getLogger(EventAdminRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jAcceptBtnActionPerformed

    private void jRefreshTableBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRefreshTableBtnActionPerformed
 
        try {
            //Disable buttons
            jAcceptBtn.setEnabled(false);
            
            populateTableData("ALL");
            
            //Reset card colors
            jPublicEvent.setBackground(Color.white);
            jPrivateEvent.setBackground(Color.white);
            
            //Reset card click counter
            publicClick = 0;
            privateClick = 0;
            populateCardData();
        } catch (SQLException ex) {
            Logger.getLogger(EventAdminRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jRefreshTableBtnActionPerformed

    private void jPublicEventMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPublicEventMouseEntered
        jPublicEvent.setBackground(Color.CYAN);
    }//GEN-LAST:event_jPublicEventMouseEntered

    private void jPublicEventMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPublicEventMouseExited
        if(publicClick==1 && privateClick==0 ){
            jPublicEvent.setBackground(Color.CYAN);
            return;
        }
        jPublicEvent.setBackground(Color.white);
        publicClick = 0;
    }//GEN-LAST:event_jPublicEventMouseExited

    private void jPrivateEventMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPrivateEventMouseClicked
        try {
            //Disable buttons
            jAcceptBtn.setEnabled(false);
            
            // Apply CSS
            jPublicEvent.setBackground(Color.white);
            jPrivateEvent.setBackground(Color.CYAN);
            
            //Refresh Table Data
            populateTableData("PRIVATE");
            
            publicClick = 0;
            privateClick = 1;
        } catch (SQLException ex) {
            Logger.getLogger(EventAdminRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jPrivateEventMouseClicked

    private void jPrivateEventMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPrivateEventMouseEntered
        jPrivateEvent.setBackground(Color.CYAN);
    }//GEN-LAST:event_jPrivateEventMouseEntered

    private void jPrivateEventMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPrivateEventMouseExited
        if(privateClick==1 && publicClick==0){
            jPrivateEvent.setBackground(Color.CYAN);
            return;
        }
        jPrivateEvent.setBackground(Color.white);
        privateClick = 0;
    }//GEN-LAST:event_jPrivateEventMouseExited

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        
        //Enable buttons
        jAcceptBtn.setEnabled(true);
        
    }//GEN-LAST:event_tableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.maple.resources.ButtonBadges jAcceptBtn;
    private javax.swing.JComboBox<String> jManagerDropdown;
    private javax.swing.JPanel jPanel1;
    private com.maple.resources.Card jPrivateEvent;
    private com.maple.resources.Card jPublicEvent;
    private com.maple.resources.ButtonBadges jRefreshTableBtn;
    private javax.swing.JLabel jRoute;
    private javax.swing.JScrollPane jScrollPane2;
    private com.maple.resources.Table table;
    // End of variables declaration//GEN-END:variables
}
