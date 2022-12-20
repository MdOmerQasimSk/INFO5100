/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.maple.frontend.businessAdminScreen;

import com.maple.backend.controller.WorkRequestController;
import com.maple.backend.model.Catering;
import com.maple.backend.model.Hotel;
import com.maple.backend.model.TravelAgent;
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
public class BusinessAdminRequest extends javax.swing.JPanel {

    /**
     * Creates new form BusinessAdminRequest
     */
    WorkRequestController workRequestController;
    ArrayList<User> userData;
    
    int businessAdminId;
    int hotelClick = 0;
    int cateringClick = 0;
    int travelClick = 0;
    
    public BusinessAdminRequest(ArrayList<User> userData) throws SQLException {
        initComponents();
        workRequestController = new WorkRequestController();
        this.userData = userData;
        businessAdminId = userData.get(0).getID();
        populateCardData();
        populateTableData("ALL");
        table.fixTable(jScrollPane2);
        jAcceptBtn.setEnabled(false);
        jRejectBtn.setEnabled(false);
    }
    
    //Card Data
    public void populateCardData() throws SQLException{
        
        //Load Icons
        jHotelCard.setIcon(new ImageIcon(getClass().getResource("/com/maple/icons/hotel.png")));
        jCateringCard.setIcon(new ImageIcon(getClass().getResource("/com/maple/icons/catering.png")));
        jTravelAgentCard.setIcon(new ImageIcon(getClass().getResource("/com/maple/icons/travelAgent.png")));
        
        //Set Description
        jHotelCard.setDescription("Hotel Requests");
        jCateringCard.setDescription("Catering Requests");
        jTravelAgentCard.setDescription("Travel Agents Requests");
        
        //Assign Request Values
        jHotelCard.setValues("# " + workRequestController.getHotelEnterpriseData(businessAdminId, "PENDING").size());
        jCateringCard.setValues("# " + workRequestController.getCateringEnterpriseData(businessAdminId, "PENDING").size()); 
        jTravelAgentCard.setValues("# " + workRequestController.getTravelAgentEnterpriseData(businessAdminId, "PENDING").size()); 
        
    }
    
    //Table Data
    private void populateTableData(String type) throws SQLException {
        
        DefaultTableModel dtmodel = (DefaultTableModel) table.getModel();
        dtmodel.setRowCount(0);
        ArrayList<Hotel> hotelFilteredList = new ArrayList<>(); 
        ArrayList<Catering> cateringFilteredList = new ArrayList<>();
        ArrayList<TravelAgent> travelAgentFilteredList = new ArrayList<>();
        
        if(type.equalsIgnoreCase("HOTEL")){
            hotelFilteredList = workRequestController.getHotelEnterpriseData(businessAdminId, "PENDING");
            for(Hotel ht: hotelFilteredList){
                Object[] obj = new Object[7];
                obj[0] = ht;
                obj[1] = "HOTEL";
                obj[2] = ht.getHotelAddress();
                obj[3] = ht.getHotelArea();
                obj[4] = ht.getCapacity();
                obj[5] = ht.getEmail();
                obj[6] = ht.getPhone();
                dtmodel.addRow(obj);
            }
        } else if(type.equalsIgnoreCase("CATERING")){
            cateringFilteredList = workRequestController.getCateringEnterpriseData(businessAdminId, "PENDING");
            for(Catering ct: cateringFilteredList){
                Object[] obj = new Object[7];
                obj[0] = ct;
                obj[1] = "CATERING";
                obj[2] = ct.getCateringAddress();
                obj[3] = ct.getCateringArea();
                obj[4] = ct.getCapacity();
                obj[5] = ct.getEmail();
                obj[6] = ct.getPhone();
                dtmodel.addRow(obj);
            }
        } else if(type.equalsIgnoreCase("TRAVEL")){
            travelAgentFilteredList = workRequestController.getTravelAgentEnterpriseData(businessAdminId, "PENDING"); 
            for(TravelAgent ta: travelAgentFilteredList){
                Object[] obj = new Object[7];
                obj[0] = ta;
                obj[1] = "TRAVEL";
                obj[2] = ta.getTravelAgentAddress();
                obj[3] = ta.getTravelAgentArea();
                obj[4] = ta.getCapacity();
                obj[5] = ta.getEmail();
                obj[6] = ta.getPhone();
                dtmodel.addRow(obj);
            }
        } else {
            //Load hotel data
            hotelFilteredList = workRequestController.getHotelEnterpriseData(businessAdminId, "PENDING"); //TODO: Pass toId from USER table
            for(Hotel ht: hotelFilteredList){
                Object[] obj = new Object[7];
                obj[0] = ht;
                obj[1] = "HOTEL";
                obj[2] = ht.getHotelAddress();
                obj[3] = ht.getHotelArea();
                obj[4] = ht.getCapacity();
                obj[5] = ht.getEmail();
                obj[6] = ht.getPhone();
                dtmodel.addRow(obj);
            }
            //Load catering data
            cateringFilteredList = workRequestController.getCateringEnterpriseData(businessAdminId, "PENDING");
            for(Catering ct: cateringFilteredList){
                Object[] obj = new Object[7];
                obj[0] = ct;
                obj[1] = "CATERING";
                obj[2] = ct.getCateringAddress();
                obj[3] = ct.getCateringArea();
                obj[4] = ct.getCapacity();
                obj[5] = ct.getEmail();
                obj[6] = ct.getPhone();
                dtmodel.addRow(obj);
            }
            //Load travelAgent data
            travelAgentFilteredList = workRequestController.getTravelAgentEnterpriseData(businessAdminId, "PENDING"); 
            for(TravelAgent ta: travelAgentFilteredList){
                Object[] obj = new Object[7];
                obj[0] = ta;
                obj[1] = "TRAVEL";
                obj[2] = ta.getTravelAgentAddress();
                obj[3] = ta.getTravelAgentArea();
                obj[4] = ta.getCapacity();
                obj[5] = ta.getEmail();
                obj[6] = ta.getPhone();
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
        jHotelCard = new com.maple.resources.Card();
        jCateringCard = new com.maple.resources.Card();
        jTravelAgentCard = new com.maple.resources.Card();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new com.maple.resources.Table();
        jRefreshTableBtn = new com.maple.resources.ButtonBadges();
        jRejectBtn = new com.maple.resources.ButtonBadges();
        jAcceptBtn = new com.maple.resources.ButtonBadges();

        setBackground(new java.awt.Color(245, 241, 241));
        setMaximumSize(new java.awt.Dimension(1196, 720));
        setMinimumSize(new java.awt.Dimension(1196, 720));
        setPreferredSize(new java.awt.Dimension(1196, 720));

        jRoute.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jRoute.setForeground(new java.awt.Color(4, 72, 210));
        jRoute.setText("Admin / Requests");

        jHotelCard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jHotelCardMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jHotelCardMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jHotelCardMouseExited(evt);
            }
        });

        jCateringCard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCateringCardMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jCateringCardMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jCateringCardMouseExited(evt);
            }
        });

        jTravelAgentCard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTravelAgentCardMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTravelAgentCardMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTravelAgentCardMouseExited(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Name", "Enterprise", "Address", "Area", "Capacity", "Email", "Phone"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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
            table.getColumnModel().getColumn(5).setResizable(false);
            table.getColumnModel().getColumn(6).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2))
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

        jRejectBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/maple/icons/cross.png"))); // NOI18N
        jRejectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRejectBtnActionPerformed(evt);
            }
        });

        jAcceptBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/maple/icons/tick.png"))); // NOI18N
        jAcceptBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAcceptBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jRefreshTableBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jHotelCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(40, 40, 40)
                            .addComponent(jCateringCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(40, 40, 40)
                            .addComponent(jTravelAgentCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jRoute)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(119, 119, 119))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jAcceptBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jRejectBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(220, 220, 220))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jRoute)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jHotelCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCateringCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTravelAgentCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jRefreshTableBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jRejectBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jAcceptBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jHotelCardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jHotelCardMouseClicked
        
        try {
            //Disable buttons
            jAcceptBtn.setEnabled(false);
            jRejectBtn.setEnabled(false);
            
            //Apply CSS
            jHotelCard.setBackground(Color.CYAN);
            jCateringCard.setBackground(Color.white);
            jTravelAgentCard.setBackground(Color.white);
            
            //Refresh Table Data
            populateTableData("HOTEL");
            
            hotelClick = 1;
            cateringClick = 0;
            travelClick = 0;
        } catch (SQLException ex) {
            Logger.getLogger(BusinessAdminRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jHotelCardMouseClicked

    private void jRejectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRejectBtnActionPerformed
        
        try {
            
            //Select row data
            int selectedRow = table.getSelectedRow();
            DefaultTableModel dtmodel = (DefaultTableModel) table.getModel();
            Object enterpriseName = (Object) dtmodel.getValueAt(selectedRow, 0);
            Object enterpriseType = (Object) dtmodel.getValueAt(selectedRow, 1);
            
            //update STATUS in WORK_REQUEST & HOTEL & USER table
            workRequestController.updateStatus(enterpriseName.toString(), enterpriseType.toString(), "REJECTED");
            
            //refresh data in card and table
            populateCardData();
            populateTableData("ALL");
            
            //Disable buttons
            jAcceptBtn.setEnabled(false);
            jRejectBtn.setEnabled(false);
            
        } catch (SQLException ex) {
            Logger.getLogger(BusinessAdminRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jRejectBtnActionPerformed

    private void jAcceptBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAcceptBtnActionPerformed

        try {
            
            //Select row data
            int selectedRow = table.getSelectedRow();
            DefaultTableModel dtmodel = (DefaultTableModel) table.getModel();
            Object enterpriseName = (Object) dtmodel.getValueAt(selectedRow, 0); //hotelName
            Object enterpriseType = (Object) dtmodel.getValueAt(selectedRow, 1); //type - HOTEL/TA/Catering
            
            //update STATUS in WORK_REQUEST & HCT & USER table
            workRequestController.updateStatus(enterpriseName.toString(), enterpriseType.toString(), "ACCEPTED");
            
            //refresh data in card and table
            populateCardData();
            populateTableData("ALL");
            //Reset card colors
            jHotelCard.setBackground(Color.white);
            jCateringCard.setBackground(Color.white);
            jTravelAgentCard.setBackground(Color.white);
            //Reset card click counter
            hotelClick = 0;
            cateringClick = 0;
            travelClick = 0;
            
            //Disable buttons
            jAcceptBtn.setEnabled(false);
            jRejectBtn.setEnabled(false);
            
        } catch (SQLException ex) {
            Logger.getLogger(BusinessAdminRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jAcceptBtnActionPerformed

    private void jRefreshTableBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRefreshTableBtnActionPerformed
 
        try {
            //Disable buttons
            jAcceptBtn.setEnabled(false);
            jRejectBtn.setEnabled(false);
            
            populateTableData("ALL");
            
            //Reset card colors
            jHotelCard.setBackground(Color.white);
            jCateringCard.setBackground(Color.white);
            jTravelAgentCard.setBackground(Color.white);
            
            //Reset card click counter
            hotelClick = 0;
            cateringClick = 0;
            travelClick = 0;
        } catch (SQLException ex) {
            Logger.getLogger(BusinessAdminRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jRefreshTableBtnActionPerformed

    private void jHotelCardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jHotelCardMouseEntered
        jHotelCard.setBackground(Color.CYAN);
    }//GEN-LAST:event_jHotelCardMouseEntered

    private void jHotelCardMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jHotelCardMouseExited
        if(hotelClick==1 && cateringClick==0 && travelClick==0){
            jHotelCard.setBackground(Color.CYAN);
//            jHotelCard.setBackground(new Color(245,241,241));
            return;
        }
            jHotelCard.setBackground(Color.white);
            hotelClick = 0;
    }//GEN-LAST:event_jHotelCardMouseExited

    private void jCateringCardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCateringCardMouseClicked
        try {
            //Disable buttons
            jAcceptBtn.setEnabled(false);
            jRejectBtn.setEnabled(false);
            
            // Apply CSS
            jHotelCard.setBackground(Color.white);
            jCateringCard.setBackground(Color.CYAN);
            jTravelAgentCard.setBackground(Color.white);
            
            //Refresh Table Data
            populateTableData("CATERING");
            
            hotelClick = 0;
            cateringClick = 1;
            travelClick = 0;
        } catch (SQLException ex) {
            Logger.getLogger(BusinessAdminRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jCateringCardMouseClicked

    private void jCateringCardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCateringCardMouseEntered
        jCateringCard.setBackground(Color.CYAN);
    }//GEN-LAST:event_jCateringCardMouseEntered

    private void jCateringCardMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCateringCardMouseExited
        if(cateringClick==1 && hotelClick==0 && travelClick==0){
            jCateringCard.setBackground(Color.CYAN);
            return;
        }
        jCateringCard.setBackground(Color.white);
        cateringClick = 0;
    }//GEN-LAST:event_jCateringCardMouseExited

    private void jTravelAgentCardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTravelAgentCardMouseClicked
        try {
            //Disable buttons
            jAcceptBtn.setEnabled(false);
            jRejectBtn.setEnabled(false);
            
            //Apply CSS
            jHotelCard.setBackground(Color.white);
            jCateringCard.setBackground(Color.white);
            jTravelAgentCard.setBackground(Color.CYAN);
            
            //Refresh Table Data
            populateTableData("TRAVEL");
            
            hotelClick = 0;
            cateringClick = 0;
            travelClick = 1;
        } catch (SQLException ex) {
            Logger.getLogger(BusinessAdminRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTravelAgentCardMouseClicked

    private void jTravelAgentCardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTravelAgentCardMouseEntered
        jTravelAgentCard.setBackground(Color.CYAN);
    }//GEN-LAST:event_jTravelAgentCardMouseEntered

    private void jTravelAgentCardMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTravelAgentCardMouseExited
        if(travelClick==1 && cateringClick==0 && hotelClick==0){
            jTravelAgentCard.setBackground(Color.CYAN);
            return;
        }
        jTravelAgentCard.setBackground(Color.white);
        travelClick = 0;
    }//GEN-LAST:event_jTravelAgentCardMouseExited

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        
        //Enable buttons
        jAcceptBtn.setEnabled(true);
        jRejectBtn.setEnabled(true);
        
    }//GEN-LAST:event_tableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.maple.resources.ButtonBadges jAcceptBtn;
    private com.maple.resources.Card jCateringCard;
    private com.maple.resources.Card jHotelCard;
    private javax.swing.JPanel jPanel1;
    private com.maple.resources.ButtonBadges jRefreshTableBtn;
    private com.maple.resources.ButtonBadges jRejectBtn;
    private javax.swing.JLabel jRoute;
    private javax.swing.JScrollPane jScrollPane2;
    private com.maple.resources.Card jTravelAgentCard;
    private com.maple.resources.Table table;
    // End of variables declaration//GEN-END:variables
}
