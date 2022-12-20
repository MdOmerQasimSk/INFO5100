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
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mohammedomerqasimshaik
 */

public class BusinessAdminDashboard extends javax.swing.JPanel {
    
    WorkRequestController workRequestController;
    ArrayList<User> userData;
    
    int businessAdminId;
    int hotelClick = 0;
    int cateringClick = 0;
    int travelClick = 0;
    
    public BusinessAdminDashboard(ArrayList<User> userData) throws SQLException {
        initComponents();
        workRequestController = new WorkRequestController();
        this.userData = userData;
        businessAdminId = userData.get(0).getID();
        populateCardData();
        populateTableData("ALL");
        table.fixTable(jScrollPane1); //apply table theme
    }

    //Table Data
    private void populateTableData(String type) throws SQLException {
        
        DefaultTableModel dtmodel = (DefaultTableModel) table.getModel();
        dtmodel.setRowCount(0);
        ArrayList<Hotel> hotelFilteredList = new ArrayList<>(); 
        ArrayList<Catering> cateringFilteredList = new ArrayList<>();
        ArrayList<TravelAgent> travelAgentFilteredList = new ArrayList<>();
        
        if(type.equalsIgnoreCase("HOTEL")){
            hotelFilteredList = workRequestController.getHotelEnterpriseData(businessAdminId, "ACCEPTED"); 
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
            cateringFilteredList = workRequestController.getCateringEnterpriseData(businessAdminId, "ACCEPTED");
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
            travelAgentFilteredList = workRequestController.getTravelAgentEnterpriseData(businessAdminId, "ACCEPTED"); 
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
            hotelFilteredList = workRequestController.getHotelEnterpriseData(businessAdminId, "ACCEPTED"); //TODO: Pass toId from USER table
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
            cateringFilteredList = workRequestController.getCateringEnterpriseData(businessAdminId, "ACCEPTED");
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
            travelAgentFilteredList = workRequestController.getTravelAgentEnterpriseData(businessAdminId, "ACCEPTED"); 
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
    
    //Card Data
    private void populateCardData() throws SQLException{
        
        //Load Icons
        jHotelCard.setIcon(new ImageIcon(getClass().getResource("/com/maple/icons/hotel.png")));
        jCateringCard.setIcon(new ImageIcon(getClass().getResource("/com/maple/icons/catering.png")));
        jTravelAgentCard.setIcon(new ImageIcon(getClass().getResource("/com/maple/icons/travelAgent.png")));
        
        //Set Description
        jHotelCard.setDescription("Hotels Affiliated");
        jCateringCard.setDescription("Caterers Affiliated");
        jTravelAgentCard.setDescription("Travel Agents Affiliated");
        
        //Assign Request Values
        jHotelCard.setValues("# " + workRequestController.getHotelEnterpriseData(businessAdminId, "ACCEPTED").size());
        jCateringCard.setValues("# " + workRequestController.getCateringEnterpriseData(businessAdminId, "ACCEPTED").size()); 
        jTravelAgentCard.setValues("# " + workRequestController.getTravelAgentEnterpriseData(businessAdminId, "ACCEPTED").size()); 
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jHotelCard = new com.maple.resources.Card();
        jCateringCard = new com.maple.resources.Card();
        jTravelAgentCard = new com.maple.resources.Card();
        jTablePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new com.maple.resources.Table();
        jLocation = new javax.swing.JLabel();
        jRefreshTableBtn = new com.maple.resources.ButtonBadges();

        setBackground(new java.awt.Color(245, 241, 241));
        setMaximumSize(new java.awt.Dimension(1196, 720));
        setMinimumSize(new java.awt.Dimension(1196, 720));
        setPreferredSize(new java.awt.Dimension(1196, 720));
        setRequestFocusEnabled(false);

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

        jTablePanel.setBackground(new java.awt.Color(255, 255, 255));

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
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(3).setResizable(false);
            table.getColumnModel().getColumn(4).setResizable(false);
            table.getColumnModel().getColumn(5).setResizable(false);
            table.getColumnModel().getColumn(6).setResizable(false);
        }

        javax.swing.GroupLayout jTablePanelLayout = new javax.swing.GroupLayout(jTablePanel);
        jTablePanel.setLayout(jTablePanelLayout);
        jTablePanelLayout.setHorizontalGroup(
            jTablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jTablePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLocation)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jRefreshTableBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jHotelCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(jCateringCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(jTravelAgentCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(223, 223, 223))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLocation)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jHotelCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCateringCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTravelAgentCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jRefreshTableBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jRefreshTableBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRefreshTableBtnActionPerformed
        try {
            populateTableData("ALL");
        } catch (SQLException ex) {}
        //Reset card colors
        jHotelCard.setBackground(Color.white);
        jCateringCard.setBackground(Color.white);
        jTravelAgentCard.setBackground(Color.white);
        //Reset card click counter
        hotelClick = 0;
        cateringClick = 0;
        travelClick = 0;
    }//GEN-LAST:event_jRefreshTableBtnActionPerformed

    private void jHotelCardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jHotelCardMouseClicked
        //Apply CSS
        jHotelCard.setBackground(Color.CYAN);
        jCateringCard.setBackground(Color.white);
        jTravelAgentCard.setBackground(Color.white);
        
        try {
            //Refresh Table Data
            populateTableData("HOTEL");
        } catch (SQLException ex) {
            
        }
        
        hotelClick = 1;
        cateringClick = 0;
        travelClick = 0;
    }//GEN-LAST:event_jHotelCardMouseClicked

    private void jHotelCardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jHotelCardMouseEntered
        // TODO add your handling code here:
        jHotelCard.setBackground(Color.CYAN);
    }//GEN-LAST:event_jHotelCardMouseEntered

    private void jHotelCardMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jHotelCardMouseExited
        // TODO add your handling code here:
        if(hotelClick==1 && cateringClick==0 && travelClick==0){
            jHotelCard.setBackground(Color.CYAN);
            return;
        }
            jHotelCard.setBackground(Color.white);
            hotelClick = 0;
    }//GEN-LAST:event_jHotelCardMouseExited

    private void jCateringCardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCateringCardMouseClicked
        // Apply CSS
        jHotelCard.setBackground(Color.white);
        jCateringCard.setBackground(Color.CYAN);
        jTravelAgentCard.setBackground(Color.white);
        
        try {
            //Refresh Table Data
            populateTableData("CATERING");
        } catch (SQLException ex) {
        }
        
        hotelClick = 0;
        cateringClick = 1;
        travelClick = 0;
    }//GEN-LAST:event_jCateringCardMouseClicked

    private void jCateringCardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCateringCardMouseEntered
        // TODO add your handling code here:
        jCateringCard.setBackground(Color.CYAN);
    }//GEN-LAST:event_jCateringCardMouseEntered

    private void jCateringCardMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCateringCardMouseExited
        // TODO add your handling code here:
        if(cateringClick==1 && hotelClick==0 && travelClick==0){
            jCateringCard.setBackground(Color.CYAN);
            return;
        }
        jCateringCard.setBackground(Color.white);
        cateringClick = 0;
    }//GEN-LAST:event_jCateringCardMouseExited

    private void jTravelAgentCardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTravelAgentCardMouseClicked
        //Apply CSS
        jHotelCard.setBackground(Color.white);
        jCateringCard.setBackground(Color.white);
        jTravelAgentCard.setBackground(Color.CYAN);
        
        try {
            //Refresh Table Data
            populateTableData("TRAVEL");
        } catch (SQLException ex) {
        }
        
        hotelClick = 0;
        cateringClick = 0;
        travelClick = 1;
    }//GEN-LAST:event_jTravelAgentCardMouseClicked

    private void jTravelAgentCardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTravelAgentCardMouseEntered
        // TODO add your handling code here:
        jTravelAgentCard.setBackground(Color.CYAN);
    }//GEN-LAST:event_jTravelAgentCardMouseEntered

    private void jTravelAgentCardMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTravelAgentCardMouseExited
        // TODO add your handling code here:
        if(travelClick==1 && cateringClick==0 && hotelClick==0){
            jTravelAgentCard.setBackground(Color.CYAN);
            return;
        }
        jTravelAgentCard.setBackground(Color.white);
        travelClick = 0;
    }//GEN-LAST:event_jTravelAgentCardMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.maple.resources.Card jCateringCard;
    private com.maple.resources.Card jHotelCard;
    private javax.swing.JLabel jLocation;
    private com.maple.resources.ButtonBadges jRefreshTableBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jTablePanel;
    private com.maple.resources.Card jTravelAgentCard;
    private com.maple.resources.Table table;
    // End of variables declaration//GEN-END:variables


}
