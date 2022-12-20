/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.maple.frontend.eventManagerScreen;
import com.maple.backend.model.Catering;
import com.maple.backend.model.Event;
import com.maple.backend.model.Hotel;
import com.maple.backend.model.TravelAgent;
import com.maple.backend.model.User;
import com.maple.backend.model.WorkRequest;
import com.maple.backend.service.EnterpriseService;
import com.maple.backend.controller.WorkRequestController;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author DKapoor
 */
public class EventManagerRequests extends javax.swing.JPanel {

    /**
     * Creates new form EventManagerRequests
     */
    ArrayList<User> userData;
    Event e;
    EnterpriseService enterpriseService;
    WorkRequestController workRequestController;
    public EventManagerRequests(ArrayList<User> userData, Event e) throws SQLException {
        initComponents();
        this.userData = userData;
        this.e = e;
        enterpriseService = new EnterpriseService();
        workRequestController = new WorkRequestController();
        
        accoPanel.setVisible(false);
        caterPanel.setVisible(false);
        travelPanel.setVisible(false);
        
        accoStatus.setVisible(false);
        CaterStatus.setVisible(false);
        TravelStatus.setVisible(false);
        
        if (e.getAccomodationNeeded().equalsIgnoreCase("yes")){
            accoPanel.setVisible(true);
            populateAccoDropDown();
        }
        if (e.getCateringNeeded().equalsIgnoreCase("yes")){
            caterPanel.setVisible(true);
            populateCaterDropDown();
        }
        if (e.getTravelNeeded().equalsIgnoreCase("yes")){
            travelPanel.setVisible(true);
            populateTravelDropDown();
        }
        
    }
    
    public void populateAccoDropDown() throws SQLException{
        
        ArrayList<WorkRequest> workRequest = workRequestController.getAllWorkRequestData();
        int event_id = e.getEventID();
        int event_manager_id = e.getEventManagerID();
        
        for (WorkRequest wr: workRequest){
            if(wr.getEventID() == event_id && wr.getEventManagerID()== event_manager_id){
                if(wr.getType().equalsIgnoreCase("eventmanager_hoteladmin")){
                    selectAcco.setVisible(false);
            jLabel4.setVisible(false);
            buttonAcco.setVisible(false);
            accoStatus.setVisible(true);
            accoStatus.setText(wr.getStatus());
            
            ArrayList<Hotel> hList = enterpriseService.getAllHotelDataService();
            for (Hotel h: hList){
                if(Integer.parseInt(h.getHotelAdmin()) == (wr.getToID())){
                    AccoName.setText(h.getHotelName());
                    AccoAddress.setText(h.getHotelAddress());
                }
                String path = h.getPhoto().replace("\\", "\\\\");
                ImageIcon icon = new ImageIcon(path);    
                accoPhoto.setIcon(icon);
            }
            
            if(accoStatus.getText().equalsIgnoreCase("rejected")){
                accoStatus.setForeground(Color.red);
                
                selectAcco.setVisible(true);
                jLabel4.setVisible(true);
                buttonAcco.setVisible(true);
            }
            else if(accoStatus.getText().equalsIgnoreCase("accepted")){
                accoStatus.setForeground(Color.green);
            }
            else if(accoStatus.getText().equalsIgnoreCase("pending")){
                accoStatus.setForeground(Color.orange);
            }
                }
            }
        }
        
        ArrayList<Hotel> hotelList = enterpriseService.getAllHotelDataService();
        ArrayList<String> hList = new ArrayList<>();
        
        hotelList.stream().forEach(hotel -> hList.add(hotel.getHotelName()));
        selectAcco.setModel(new DefaultComboBoxModel<>(hList.toArray(new String[0])));
    }
    
    public void populateCaterDropDown() throws SQLException{
        
        ArrayList<WorkRequest> workRequest = workRequestController.getAllWorkRequestData();
        int event_id = e.getEventID();
        int event_manager_id = e.getEventManagerID();
        
        for (WorkRequest wr: workRequest){
            if(wr.getEventID() == event_id && wr.getEventManagerID()== event_manager_id){
                if(wr.getType().equalsIgnoreCase("eventmanager_cateringadmin")){
                    selectCater.setVisible(false);
            jLabel5.setVisible(false);
            buttonCater.setVisible(false);
            CaterStatus.setVisible(true);
            
            CaterStatus.setText(wr.getStatus());
            if(CaterStatus.getText().equalsIgnoreCase("rejected")){
                CaterStatus.setForeground(Color.red);
                
                selectCater.setVisible(true);
                jLabel5.setVisible(true);
                buttonCater.setVisible(true);
                
            }
            else if(CaterStatus.getText().equalsIgnoreCase("accepted")){
                CaterStatus.setForeground(Color.green);
            }
            else if(CaterStatus.getText().equalsIgnoreCase("pending")){
                CaterStatus.setForeground(Color.orange);
            }
            ArrayList<Catering> cList = enterpriseService.getAllCateringDataService();
            for (Catering h: cList){
                if(Integer.parseInt(h.getCateringAdmin()) == (wr.getToID())){
                    CaterName.setText(h.getCateringName());
                    CaterAddress.setText(h.getCateringAddress());
                }
                String path = h.getPhoto().replace("\\", "\\\\");
                   ImageIcon icon = new ImageIcon(path);    
                caterPhoto.setIcon(icon);
            }
                }
            }
        }
        
        ArrayList<Catering> cList = enterpriseService.getAllCateringDataService();
        ArrayList<String> cater = new ArrayList<>();
        
        cList.stream().forEach(c -> cater.add(c.getCateringName()));
        selectCater.setModel(new DefaultComboBoxModel<>(cater.toArray(new String[0])));
    }
    
    public void populateTravelDropDown() throws SQLException{
        
        ArrayList<WorkRequest> workRequest = workRequestController.getAllWorkRequestData();
        int event_id = e.getEventID();
        int event_manager_id = e.getEventManagerID();
        
        for (WorkRequest wr: workRequest){
            if(wr.getEventID() == event_id && wr.getEventManagerID()== event_manager_id){
                if(wr.getType().equalsIgnoreCase("eventmanager_travelagentadmin")){
                    selectTravel.setVisible(false);
            jLabel6.setVisible(false);
            buttonTravel.setVisible(false);
            selectTravel.setVisible(false);
            TravelStatus.setVisible(true);
            
            TravelStatus.setText(wr.getStatus());
            if(TravelStatus.getText().equalsIgnoreCase("rejected")){
                TravelStatus.setForeground(Color.red);
                jLabel6.setVisible(true);
            buttonTravel.setVisible(true);
            selectTravel.setVisible(true);
            }
            else if(TravelStatus.getText().equalsIgnoreCase("accepted")){
                TravelStatus.setForeground(Color.green);
            }
            else if(TravelStatus.getText().equalsIgnoreCase("pending")){
                TravelStatus.setForeground(Color.ORANGE);
            }
            ArrayList<TravelAgent> cList = enterpriseService.getAllTravelDataService();
            for (TravelAgent h: cList){
//                System.out.println("ashdbshbd" + h.getTravelAgentAdmin()+ wr.getToID());
                if(Integer.parseInt(h.getTravelAgentAdmin()) == (wr.getToID())){
//                                 System.out.println("skjdfbhdbf");
                    TAName.setText(h.getTravelAgentName());
                    TAAddress.setText(h.getTravelAgentAddress());
                }
                String path = h.getPhoto().replace("\\", "\\\\");
                   ImageIcon icon = new ImageIcon(path);    
                travelPhoto.setIcon(icon);
            }
                } 
            }
        }
////        ArrayList<Catering> cList = enterpriseService.getAllCateringDataService();
//            for (Catering h: cList){
//                if(Integer.parseInt(h.getCateringAdmin()) == (wr.getToID())){
//                    CaterName.setText(h.getCateringName());
//                    CaterAddress.setText(h.getCateringAddress());
//                }
//                String path = h.getPhoto().replace("\\", "\\\\");
//                   ImageIcon icon = new ImageIcon(path);    
//                caterPhoto.setIcon(icon);
////            }
        ArrayList<TravelAgent> tList = enterpriseService.getAllTravelDataService();
        ArrayList<String> travelList = new ArrayList<>();
        
        tList.stream().forEach(t -> travelList.add(t.getTravelAgentName()));
        selectTravel.setModel(new DefaultComboBoxModel<>(travelList.toArray(new String[0])));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLocation2 = new javax.swing.JLabel();
        accoPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        selectAcco = new javax.swing.JComboBox<>();
        buttonAcco = new com.maple.resources.Button();
        AccoName = new com.maple.resources.TextField();
        AccoAddress = new com.maple.resources.TextField();
        accoStatus = new javax.swing.JLabel();
        accoPhoto = new com.maple.resources.ImageAvatar();
        caterPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        selectCater = new javax.swing.JComboBox<>();
        buttonCater = new com.maple.resources.Button();
        CaterStatus = new javax.swing.JLabel();
        CaterName = new com.maple.resources.TextField();
        CaterAddress = new com.maple.resources.TextField();
        caterPhoto = new com.maple.resources.ImageAvatar();
        travelPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        selectTravel = new javax.swing.JComboBox<>();
        buttonTravel = new com.maple.resources.Button();
        TravelStatus = new javax.swing.JLabel();
        travelPhoto = new com.maple.resources.ImageAvatar();
        TAName = new com.maple.resources.TextField();
        TAAddress = new com.maple.resources.TextField();

        setBackground(new java.awt.Color(245, 241, 241));
        setMaximumSize(new java.awt.Dimension(1196, 720));
        setMinimumSize(new java.awt.Dimension(1196, 720));
        setPreferredSize(new java.awt.Dimension(1196, 720));

        jLocation2.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLocation2.setForeground(new java.awt.Color(4, 72, 210));
        jLocation2.setText("Event Manager / Request");

        accoPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel1.setText("Accomodation");

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel4.setText("Select a hotel");

        selectAcco.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        buttonAcco.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        buttonAcco.setText("REQUEST");
        buttonAcco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAccoActionPerformed(evt);
            }
        });

        AccoName.setEditable(false);
        AccoName.setLabelText("Hotel Name");

        AccoAddress.setEditable(false);
        AccoAddress.setLabelText("Hotel Address");

        accoStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        accoStatus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout accoPanelLayout = new javax.swing.GroupLayout(accoPanel);
        accoPanel.setLayout(accoPanelLayout);
        accoPanelLayout.setHorizontalGroup(
            accoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accoPanelLayout.createSequentialGroup()
                .addGroup(accoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(accoPanelLayout.createSequentialGroup()
                        .addGroup(accoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(accoPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1))
                            .addGroup(accoPanelLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel4)
                                .addGap(31, 31, 31)
                                .addComponent(selectAcco, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(55, 55, 55))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, accoPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonAcco, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(96, 96, 96)))
                .addGroup(accoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(accoPanelLayout.createSequentialGroup()
                        .addGroup(accoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(AccoAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(AccoName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(53, 53, 53)
                        .addComponent(accoPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(184, 184, 184))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, accoPanelLayout.createSequentialGroup()
                        .addComponent(accoStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        accoPanelLayout.setVerticalGroup(
            accoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, accoPanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(accoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(accoPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(accoPanelLayout.createSequentialGroup()
                        .addComponent(AccoName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(accoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AccoAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonAcco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(25, 25, 25))
            .addGroup(accoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(accoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(accoStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(accoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(selectAcco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        caterPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel2.setText("Catering");

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel5.setText("Select a caterer");

        selectCater.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        buttonCater.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        buttonCater.setText("REQUEST");
        buttonCater.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCaterActionPerformed(evt);
            }
        });

        CaterStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CaterStatus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        CaterName.setEditable(false);
        CaterName.setLabelText("Cater Name");

        CaterAddress.setEditable(false);
        CaterAddress.setLabelText("Cater Address");

        javax.swing.GroupLayout caterPanelLayout = new javax.swing.GroupLayout(caterPanel);
        caterPanel.setLayout(caterPanelLayout);
        caterPanelLayout.setHorizontalGroup(
            caterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(caterPanelLayout.createSequentialGroup()
                .addGroup(caterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(caterPanelLayout.createSequentialGroup()
                        .addGroup(caterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(caterPanelLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel5)
                                .addGap(31, 31, 31)
                                .addComponent(selectCater, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, caterPanelLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buttonCater, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(90, 90, 90)))
                        .addGroup(caterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CaterName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CaterAddress, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(80, 80, 80)
                        .addComponent(caterPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))
                    .addGroup(caterPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(CaterStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        caterPanelLayout.setVerticalGroup(
            caterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(caterPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(caterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(caterPanelLayout.createSequentialGroup()
                        .addComponent(CaterStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(caterPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addGroup(caterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, caterPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(caterPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))
                            .addGroup(caterPanelLayout.createSequentialGroup()
                                .addGroup(caterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(caterPanelLayout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addGroup(caterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel5)
                                            .addComponent(selectCater, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(caterPanelLayout.createSequentialGroup()
                                        .addComponent(CaterName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(caterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(CaterAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(buttonCater, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );

        travelPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel3.setText("Travel");

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel6.setText("Select an agent");

        selectTravel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        buttonTravel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        buttonTravel.setText("REQUEST");
        buttonTravel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTravelActionPerformed(evt);
            }
        });

        TravelStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TravelStatus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        TAName.setEditable(false);
        TAName.setLabelText("Travel Agent Name");

        TAAddress.setEditable(false);
        TAAddress.setLabelText("Travel Agent Address");

        javax.swing.GroupLayout travelPanelLayout = new javax.swing.GroupLayout(travelPanel);
        travelPanel.setLayout(travelPanelLayout);
        travelPanelLayout.setHorizontalGroup(
            travelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(travelPanelLayout.createSequentialGroup()
                .addGroup(travelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(travelPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3))
                    .addGroup(travelPanelLayout.createSequentialGroup()
                        .addGroup(travelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(travelPanelLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel6)
                                .addGap(31, 31, 31)
                                .addComponent(selectTravel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, travelPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(buttonTravel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(84, 84, 84)))
                        .addGroup(travelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TAName, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(TAAddress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(travelPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(TravelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        travelPanelLayout.setVerticalGroup(
            travelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(travelPanelLayout.createSequentialGroup()
                .addGroup(travelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(travelPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(travelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(travelPanelLayout.createSequentialGroup()
                                .addComponent(TAName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(TAAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(travelPanelLayout.createSequentialGroup()
                                .addGroup(travelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TravelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(travelPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(29, 29, 29)
                                        .addGroup(travelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel6)
                                            .addComponent(selectTravel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(32, 32, 32)
                                        .addComponent(buttonTravel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(17, 17, 17))))
                    .addGroup(travelPanelLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(travelPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLocation2)
                    .addComponent(accoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(caterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(travelPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(283, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLocation2)
                .addGap(18, 18, 18)
                .addComponent(accoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(caterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(travelPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCaterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCaterActionPerformed
        String catering = selectCater.getSelectedItem().toString();
        
        ArrayList<Catering> cList;
        try {
            cList = enterpriseService.getAllCateringDataService();
                    for (Catering c : cList){
            if(c.getCateringName().equalsIgnoreCase(catering)){
//                int hotel_id = h.getHotelID();
                String ca_id = c.getCateringAdmin();
                WorkRequest wr = new WorkRequest();
                int event_manager_id = e.getEventManagerID();
                int event_id = e.getEventID();
            wr.setID(workRequestController.getAllWorkRequestData().size()+1);
            wr.setType("EVENTMANAGER_CATERINGADMIN");
            wr.setFromID(event_manager_id);
            wr.setToID(Integer.parseInt(ca_id)); 
            wr.setStatus("PENDING");
            wr.setEventID(event_id);
            wr.setEventManagerID(event_manager_id);
            workRequestController.createWorkRequest(wr);
            
            JOptionPane.showMessageDialog(this, "Request sent to catering admin!");
            selectCater.setVisible(false);
            jLabel5.setVisible(false);
            buttonCater.setVisible(false);
            CaterStatus.setVisible(true);
            
            CaterStatus.setText("PENDING");
            CaterStatus.setForeground(Color.ORANGE);
            CaterName.setText(c.getCateringName());
            CaterAddress.setText(c.getCateringAddress());
            String path = c.getPhoto().replace("\\", "\\\\");
                   ImageIcon icon = new ImageIcon(path);    
                caterPhoto.setIcon(icon);
            
            }
        }
        }catch (SQLException ex) {
            Logger.getLogger(EventManagerRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonCaterActionPerformed

    private void buttonTravelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTravelActionPerformed
        String travel = selectTravel.getSelectedItem().toString();
        
        ArrayList<TravelAgent> tList;
        try {
            tList = enterpriseService.getAllTravelDataService();
                    for (TravelAgent t : tList){
            if(t.getTravelAgentName().equalsIgnoreCase(travel)){
//                int hotel_id = h.getHotelID();
                String ta_id = t.getTravelAgentAdmin();
                WorkRequest wr = new WorkRequest();
                int event_manager_id = e.getEventManagerID();
                int event_id = e.getEventID();
            wr.setID(workRequestController.getAllWorkRequestData().size()+1);
            wr.setType("EVENTMANAGER_TRAVELAGENTADMIN");
            wr.setFromID(event_manager_id);
            wr.setToID(Integer.parseInt(ta_id)); 
            wr.setStatus("PENDING");
            wr.setEventID(event_id);
            wr.setEventManagerID(event_manager_id);
            workRequestController.createWorkRequest(wr);
            
            JOptionPane.showMessageDialog(this, "Request sent to travel agent admin!");
            selectTravel.setVisible(false);
            jLabel6.setVisible(false);
            buttonTravel.setVisible(false);
//            TravelLabel.setVisible(true);
            TravelStatus.setVisible(true);
            
            TravelStatus.setText("PENDING");
            TravelStatus.setForeground(Color.ORANGE);
            
//            ArrayList<TravelAgent> cList = enterpriseService.getAllTravelDataService();
//            for (TravelAgent h: cList){
//                if(Integer.parseInt(h.getTravelAgentAdmin()) == (wr.getToID())){
                    TAName.setText(t.getTravelAgentName());
                    TAAddress.setText(t.getTravelAgentAddress());
//                }
                String path = t.getPhoto().replace("\\", "\\\\");
                   ImageIcon icon = new ImageIcon(path);    
                travelPhoto.setIcon(icon);
//            }
            }
        }
        }catch (SQLException ex) {
            Logger.getLogger(EventManagerRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
        
    }//GEN-LAST:event_buttonTravelActionPerformed

    private void buttonAccoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAccoActionPerformed
        String hotel = selectAcco.getSelectedItem().toString();

        ArrayList<Hotel> hotelList;
        try {
            hotelList = enterpriseService.getAllHotelDataService();
            for (Hotel h : hotelList){
                if(h.getHotelName().equalsIgnoreCase(hotel)){
                    //                int hotel_id = h.getHotelID();
                    String ha_id = h.getHotelAdmin();
                    WorkRequest wr = new WorkRequest();
                    int event_manager_id = e.getEventManagerID();
                    int event_id = e.getEventID();
                    wr.setID(workRequestController.getAllWorkRequestData().size()+1);
                    wr.setType("EVENTMANAGER_HOTELADMIN");
                    wr.setFromID(event_manager_id);
                    wr.setToID(Integer.parseInt(ha_id));
                    wr.setStatus("PENDING");
                    wr.setEventID(event_id);
                    wr.setEventManagerID(event_manager_id);
                    workRequestController.createWorkRequest(wr);

                    JOptionPane.showMessageDialog(this, "Request sent to hotel admin!");
                    selectAcco.setVisible(false);
                    jLabel4.setVisible(false);
                    buttonAcco.setVisible(false);
                    
//                    AccoLabel.setVisible(true);
                    accoStatus.setVisible(true);

//                    AccoName.setVisible(true);
//                    AccoAddress.setVisible(true);
//                    accoPhoto.setVisible(true);
                    String path = h.getPhoto().replace("\\", "\\\\");
                   ImageIcon icon = new ImageIcon(path);    
                accoPhoto.setIcon(icon);
                    accoStatus.setText("PENDING");
                    accoStatus.setForeground(Color.orange);
//                    accoStatus.set

                    AccoName.setText(h.getHotelName());
                    AccoAddress.setText(h.getHotelAddress());
                    
                    
//                    accoPhoto.setIcon();
                }
            }
        }catch (SQLException ex) {
            Logger.getLogger(EventManagerRequests.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_buttonAccoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.maple.resources.TextField AccoAddress;
    private com.maple.resources.TextField AccoName;
    private com.maple.resources.TextField CaterAddress;
    private com.maple.resources.TextField CaterName;
    private javax.swing.JLabel CaterStatus;
    private com.maple.resources.TextField TAAddress;
    private com.maple.resources.TextField TAName;
    private javax.swing.JLabel TravelStatus;
    private javax.swing.JPanel accoPanel;
    private com.maple.resources.ImageAvatar accoPhoto;
    private javax.swing.JLabel accoStatus;
    private com.maple.resources.Button buttonAcco;
    private com.maple.resources.Button buttonCater;
    private com.maple.resources.Button buttonTravel;
    private javax.swing.JPanel caterPanel;
    private com.maple.resources.ImageAvatar caterPhoto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLocation2;
    private javax.swing.JComboBox<String> selectAcco;
    private javax.swing.JComboBox<String> selectCater;
    private javax.swing.JComboBox<String> selectTravel;
    private javax.swing.JPanel travelPanel;
    private com.maple.resources.ImageAvatar travelPhoto;
    // End of variables declaration//GEN-END:variables
}
