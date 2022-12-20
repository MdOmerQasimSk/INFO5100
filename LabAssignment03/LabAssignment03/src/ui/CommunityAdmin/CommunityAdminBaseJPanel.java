/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.CommunityAdmin;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Hospital;
import model.HospitalCatalogue;
import model.Person;
import model.PersonCatalogue;

/**
 *
 * @author omerq
 */
public class CommunityAdminBaseJPanel extends javax.swing.JPanel {

    /**
     * Creates new form CommunityAdminBaseJPanel
     */
    
    HospitalCatalogue hospitalCatalogue;
    PersonCatalogue personCatalogue;
    
    public CommunityAdminBaseJPanel(PersonCatalogue personCatalogue, HospitalCatalogue hospitalCatalogue) {
        initComponents();
        this.hospitalCatalogue = hospitalCatalogue;
        this.personCatalogue = personCatalogue;
        
        //Disable ID fields
        jPersonID.setEnabled(false);
        jHospitalID.setEnabled(false);
        
        //Disable fields
        disablePersonFields();
        disableHospitalFields();
        
        //Clear fields
        clearPersonFields();
        clearHospitalFields();
        
        //Disable Buttons
        jPersonUpdateBtn.setEnabled(false);
        jPersonDeleteBtn.setEnabled(false);
        jHospitalUpdateBtn.setEnabled(false);
        jHospitalDeleteBtn.setEnabled(false);
        
        //Populate Dropdown values
        populateDropDown();
        
        //Populate table
        populateHospitalTable();
        populatePersonTable();
        
    }
    
    private void disablePersonFields(){
        jPersonName.setEnabled(false);
        jPersonTelNum.setEnabled(false);
        jPersonEmail.setEnabled(false);
        jPersonAddress.setEnabled(false);
    }
    
    private void disableHospitalFields(){
        jHospitalName.setEnabled(false);
        jHospitalTelNum.setEnabled(false);
        jHospitalEmail.setEnabled(false);
        jHospitalAddress.setEnabled(false);
    }
    
    private void enablePersonFields(){
        jPersonName.setEnabled(true);
        jPersonTelNum.setEnabled(true);
        jPersonEmail.setEnabled(true);
        jPersonAddress.setEnabled(true);
    }
    
    private void enableHospitalFields(){
        jHospitalName.setEnabled(true);
        jHospitalTelNum.setEnabled(true);
        jHospitalEmail.setEnabled(true);
        jHospitalAddress.setEnabled(true);
    }
    
    private void clearPersonFields(){
        jPersonID.setText("");
        jPersonName.setText("");
        jPersonTelNum.setText("");
        jPersonEmail.setText("");
        jPersonAddress.setText("");
        
        jPersonNameValidation.setText(" ");
        jPersonTelNumValidation.setText(" ");
        jPersonEmailValidation.setText(" ");
        jPersonAddressValidation.setText(" ");
    }
    
    private void clearHospitalFields(){
        jHospitalID.setText("");
        jHospitalName.setText("");
        jHospitalTelNum.setText("");
        jHospitalEmail.setText("");
        jHospitalAddress.setText("");
        
        jHospitalNameValidation.setText(" ");
        jHospitalTelNumValidation.setText(" ");
        jHospitalEmailValidation.setText(" ");
        jHospitalAddressValidation.setText(" ");
    }
    
    private void populateDropDown(){
        //City 
        ArrayList<String> cityList = new ArrayList<>();
        cityList.add("-- Select --");
        this.hospitalCatalogue.getHospitalList().stream().forEach(action->{
            cityList.add(action.getCity());
        });
        Set<String> uniqueCities = new HashSet<String>(cityList);
        jCommunityAdminCity.setModel(new DefaultComboBoxModel<>(uniqueCities.toArray(new String[0])));
        
        //Community
        List<String> communityList = new ArrayList<>();
        communityList.add("-- Select --");
        this.hospitalCatalogue.getHospitalList().stream().forEach(action->{
            communityList.add(action.getCommunity());
        });
        Set<String> uniqueCommunities = new HashSet<String>(communityList);
        jCommunityAdminCommunity.setModel(new DefaultComboBoxModel<>(uniqueCommunities.toArray(new String[0])));
    }
    
    private void populatePersonTable() {
       DefaultTableModel dtmodel = (DefaultTableModel) jPersonTable.getModel();
        dtmodel.setRowCount(0);
        
        String adminCity = jCommunityAdminCity.getSelectedItem().toString();
        String adminCommunity = jCommunityAdminCommunity.getSelectedItem().toString();
        ArrayList<Person> filteredList = new ArrayList<>();
        
        personCatalogue.getPersonList().stream()
                .filter(person->person.getCity().equalsIgnoreCase(adminCity)
                        &&person.getCommunity().equalsIgnoreCase(adminCommunity))
                .forEach(personData->filteredList.add(personData));
        
        for(Person per: filteredList){
            Object[] obj = new Object[5];
            obj[0] = per;
            obj[1] = per.getPersonName();
            obj[2] = per.getPersonTelNum();
            obj[3] = per.getPersonEmail();
            obj[4] = per.getPersonAddress();
            dtmodel.addRow(obj);
        }
    }
    
    private void populateHospitalTable() {
       DefaultTableModel dtmodel = (DefaultTableModel) jHospitalTable.getModel();
        dtmodel.setRowCount(0);
        
        String adminCity = jCommunityAdminCity.getSelectedItem().toString();
        String adminCommunity = jCommunityAdminCommunity.getSelectedItem().toString();
        ArrayList<Hospital> filteredList = new ArrayList<>();
        
        hospitalCatalogue.getHospitalList().stream()
                .filter(hosp->hosp.getCity().equalsIgnoreCase(adminCity)
                        &&hosp.getCommunity().equalsIgnoreCase(adminCommunity))
                .forEach(hospData->filteredList.add(hospData));
        
        for(Hospital hosp: filteredList){
            Object[] obj = new Object[5];
            obj[0] = hosp;
            obj[1] = hosp.getHospitalName();
            obj[2] = hosp.getContactNumber();
            obj[3] = hosp.getEmail();
            obj[4] = hosp.getAddress();
            dtmodel.addRow(obj);
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

        jTitle = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jCommunityAdminCity = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jCommunityAdminCommunity = new javax.swing.JComboBox<>();
        jLoginBtn = new javax.swing.JButton();
        jResetBtn = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPersonTable = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jHospitalTable = new javax.swing.JTable();
        jTitle2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jPersonID = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jPersonName = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jPersonTelNum = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jPersonEmail = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jPersonAddress = new javax.swing.JTextField();
        jPersonUpdateBtn = new javax.swing.JButton();
        jPersonDeleteBtn = new javax.swing.JButton();
        jPersonNameValidation = new javax.swing.JLabel();
        jPersonTelNumValidation = new javax.swing.JLabel();
        jPersonEmailValidation = new javax.swing.JLabel();
        jPersonAddressValidation = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jHospitalID = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jHospitalName = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jHospitalTelNum = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jHospitalEmail = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jHospitalAddress = new javax.swing.JTextField();
        jHospitalUpdateBtn = new javax.swing.JButton();
        jHospitalDeleteBtn = new javax.swing.JButton();
        jHospitalNameValidation = new javax.swing.JLabel();
        jHospitalTelNumValidation = new javax.swing.JLabel();
        jHospitalEmailValidation = new javax.swing.JLabel();
        jHospitalAddressValidation = new javax.swing.JLabel();
        jTitle3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jCommunityAdminName = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(1100, 800));
        setMinimumSize(new java.awt.Dimension(1100, 800));
        setPreferredSize(new java.awt.Dimension(1100, 800));

        jTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jTitle.setText("COMMUNITY ADMIN");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Admin Login"));

        jCommunityAdminCity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select --" }));
        jCommunityAdminCity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCommunityAdminCityActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel31.setText("City");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel30.setText("Community");

        jCommunityAdminCommunity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select --" }));
        jCommunityAdminCommunity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCommunityAdminCommunityActionPerformed(evt);
            }
        });

        jLoginBtn.setText("Login");
        jLoginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLoginBtnActionPerformed(evt);
            }
        });

        jResetBtn.setText("Logout");
        jResetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jResetBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel31)
                .addGap(15, 15, 15)
                .addComponent(jCommunityAdminCity, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jLabel30)
                .addGap(15, 15, 15)
                .addComponent(jCommunityAdminCommunity, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLoginBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jResetBtn)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLoginBtn, jResetBtn});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCommunityAdminCommunity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLoginBtn)
                        .addComponent(jResetBtn))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCommunityAdminCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );

        jPersonTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Person ID", "Name", "Telephone", "Email", "Address"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jPersonTable.getTableHeader().setReorderingAllowed(false);
        jPersonTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPersonTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jPersonTable);

        jHospitalTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Hospital ID", "Hospital Name", "Telephone", "Email", "Address"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jHospitalTable.getTableHeader().setReorderingAllowed(false);
        jHospitalTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jHospitalTableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jHospitalTable);

        jTitle2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jTitle2.setText("HOSPITAL DATA");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Person Information"));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel32.setText("Person ID");

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel33.setText("Name");

        jPersonName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPersonNameKeyReleased(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel34.setText("Telephone");

        jPersonTelNum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPersonTelNumKeyReleased(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel35.setText("Email");

        jPersonEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPersonEmailActionPerformed(evt);
            }
        });
        jPersonEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPersonEmailKeyReleased(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel36.setText("Address");

        jPersonAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPersonAddressActionPerformed(evt);
            }
        });
        jPersonAddress.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPersonAddressKeyReleased(evt);
            }
        });

        jPersonUpdateBtn.setText("Update");
        jPersonUpdateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPersonUpdateBtnActionPerformed(evt);
            }
        });

        jPersonDeleteBtn.setText("Delete");
        jPersonDeleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPersonDeleteBtnActionPerformed(evt);
            }
        });

        jPersonNameValidation.setText("   ");

        jPersonTelNumValidation.setText("   ");

        jPersonEmailValidation.setText("   ");

        jPersonAddressValidation.setText("   ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel34)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addComponent(jPersonTelNum, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jPersonTelNumValidation, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel33)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPersonName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPersonNameValidation, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel32)
                                    .addGap(18, 18, 18)
                                    .addComponent(jPersonID, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jPersonAddressValidation, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel35)
                                        .addGap(38, 38, 38)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jPersonEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jPersonEmailValidation, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel36)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPersonAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jPersonUpdateBtn)
                        .addGap(18, 18, 18)
                        .addComponent(jPersonDeleteBtn)))
                .addGap(18, 18, 18))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPersonID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPersonName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jPersonNameValidation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPersonTelNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jPersonTelNumValidation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPersonEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jPersonEmailValidation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPersonAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(jPersonAddressValidation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPersonUpdateBtn)
                    .addComponent(jPersonDeleteBtn))
                .addGap(18, 18, 18))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Hospital Information"));

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel37.setText("Hospital ID");

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel38.setText("Name");

        jHospitalName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jHospitalNameKeyReleased(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel39.setText("Telephone");

        jHospitalTelNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jHospitalTelNumActionPerformed(evt);
            }
        });
        jHospitalTelNum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jHospitalTelNumKeyReleased(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel40.setText("Email");

        jHospitalEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jHospitalEmailActionPerformed(evt);
            }
        });
        jHospitalEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jHospitalEmailKeyReleased(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel41.setText("Address");

        jHospitalAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jHospitalAddressActionPerformed(evt);
            }
        });
        jHospitalAddress.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jHospitalAddressKeyReleased(evt);
            }
        });

        jHospitalUpdateBtn.setText("Update");
        jHospitalUpdateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jHospitalUpdateBtnActionPerformed(evt);
            }
        });

        jHospitalDeleteBtn.setText("Delete");
        jHospitalDeleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jHospitalDeleteBtnActionPerformed(evt);
            }
        });

        jHospitalNameValidation.setText("   ");

        jHospitalTelNumValidation.setText("   ");

        jHospitalEmailValidation.setText("   ");

        jHospitalAddressValidation.setText("   ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel38)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jHospitalName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jHospitalNameValidation, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel37)
                                    .addGap(18, 18, 18)
                                    .addComponent(jHospitalID, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel40)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel39)
                                .addGap(24, 24, 24)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jHospitalEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jHospitalTelNum, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jHospitalTelNumValidation, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jHospitalEmailValidation, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel41)
                                .addGap(36, 36, 36)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jHospitalAddressValidation, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jHospitalAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jHospitalUpdateBtn)
                        .addGap(18, 18, 18)
                        .addComponent(jHospitalDeleteBtn)))
                .addGap(13, 13, 13))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jHospitalID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jHospitalName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jHospitalNameValidation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jHospitalTelNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jHospitalTelNumValidation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jHospitalEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jHospitalEmailValidation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jHospitalAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(jHospitalAddressValidation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jHospitalUpdateBtn)
                    .addComponent(jHospitalDeleteBtn))
                .addGap(18, 18, 18))
        );

        jTitle3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jTitle3.setText("PEOPLE DATA");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Logged In As"));

        jCommunityAdminName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jCommunityAdminName.setText(" ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jCommunityAdminName, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jCommunityAdminName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(450, 450, 450)
                        .addComponent(jTitle)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTitle3))
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTitle2)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(45, 45, 45))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTitle2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTitle3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(87, 87, 87))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jCommunityAdminCityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCommunityAdminCityActionPerformed
        // TODO add your handling code here:
        //Populate Community dynamically
        ArrayList<String> filteredCommunityList = new ArrayList<>();
        filteredCommunityList.add("-- Select --");
        if(!jCommunityAdminCity.getSelectedItem().toString().equals("-- Select --")){
            hospitalCatalogue.getHospitalList().stream()
                .filter(hosp -> hosp.getCity().equals(jCommunityAdminCity.getSelectedItem().toString()))
                .forEach(action -> filteredCommunityList.add(action.getCommunity()));
            jCommunityAdminCommunity.setModel(new DefaultComboBoxModel<>(filteredCommunityList.toArray(new String[0]))); 
        }
        
        
        
    }//GEN-LAST:event_jCommunityAdminCityActionPerformed

    private void jCommunityAdminCommunityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCommunityAdminCommunityActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jCommunityAdminCommunityActionPerformed

    private void jPersonTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPersonTableMouseClicked
        // TODO add your handling code here:
        
        // disable Add button
        jPersonUpdateBtn.setEnabled(true);
        jPersonDeleteBtn.setEnabled(true);

        //Clear previous selected data
        clearPersonFields();

        //Select row data
        int selectedRow = jPersonTable.getSelectedRow();

        DefaultTableModel dtmodel = (DefaultTableModel) jPersonTable.getModel();
        Person selectedRowObj = (Person) dtmodel.getValueAt(selectedRow, 0);

        //Display Person rowData
        jPersonID.setText(String.valueOf(selectedRowObj.getPersonId()));
        jPersonName.setText(selectedRowObj.getPersonName());
        jPersonAddress.setText(selectedRowObj.getPersonAddress());
        jPersonTelNum.setText(selectedRowObj.getPersonTelNum());
        jPersonEmail.setText(selectedRowObj.getPersonEmail());

        //Enable fields
        enablePersonFields();
    }//GEN-LAST:event_jPersonTableMouseClicked

    private void jHospitalTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jHospitalTableMouseClicked
        // TODO add your handling code here:
        // disable Add button
        jHospitalUpdateBtn.setEnabled(true);
        jHospitalDeleteBtn.setEnabled(true);

        //Clear previous selected data
        clearHospitalFields();

        //Select row data
        int selectedRow = jHospitalTable.getSelectedRow();
        DefaultTableModel dtmodel = (DefaultTableModel) jHospitalTable.getModel();
        Hospital selectedRowObj = (Hospital) dtmodel.getValueAt(selectedRow, 0);

        //Display Hospital rowData
        jHospitalID.setText(String.valueOf(selectedRowObj.getHospitalID()));
        jHospitalName.setText(selectedRowObj.getHospitalName());
        jHospitalAddress.setText(selectedRowObj.getAddress());
        jHospitalTelNum.setText(selectedRowObj.getContactNumber());
        jHospitalEmail.setText(selectedRowObj.getEmail());

        //Enable fields
        enableHospitalFields();
    }//GEN-LAST:event_jHospitalTableMouseClicked

    private void jPersonEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPersonEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPersonEmailActionPerformed

    private void jPersonAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPersonAddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPersonAddressActionPerformed

    private void jHospitalEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHospitalEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jHospitalEmailActionPerformed

    private void jHospitalAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHospitalAddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jHospitalAddressActionPerformed

    private void jHospitalTelNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHospitalTelNumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jHospitalTelNumActionPerformed

    private void jResetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jResetBtnActionPerformed
        // TODO add your handling code here:
        jCommunityAdminName.setText(" ");
        clearPersonFields();
        clearHospitalFields();
        jCommunityAdminCity.setSelectedIndex(0);
        jCommunityAdminCommunity.setSelectedIndex(0);
        disablePersonFields();
        disableHospitalFields();
        jPersonUpdateBtn.setEnabled(false);
        jPersonDeleteBtn.setEnabled(false);
        jHospitalUpdateBtn.setEnabled(false);
        jHospitalDeleteBtn.setEnabled(false);
        populatePersonTable();
        populateHospitalTable();
    }//GEN-LAST:event_jResetBtnActionPerformed

    private void jLoginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLoginBtnActionPerformed
        // TODO add your handling code here:
        if(!jCommunityAdminCommunity.getSelectedItem().toString().equals("-- Select --")){
            jCommunityAdminName.setText(jCommunityAdminCommunity.getSelectedItem().toString());    
        } else {
            jCommunityAdminName.setText(" ");
        }
        
       String adminCity = jCommunityAdminCity.getSelectedItem().toString();
       String adminCommunity = jCommunityAdminCommunity.getSelectedItem().toString();
       
       if(adminCity.equals("-- Select --") || adminCommunity.equals("-- Select --")){
           JOptionPane.showMessageDialog(this, "Please select valid options");
           return;
       }
       populateHospitalTable();
       populatePersonTable(); 
       //Clear deleted data
        clearPersonFields();
        disablePersonFields();
        clearHospitalFields();
        disableHospitalFields();
        jHospitalUpdateBtn.setEnabled(false);
        jHospitalDeleteBtn.setEnabled(false);
        jPersonUpdateBtn.setEnabled(false);
        jPersonDeleteBtn.setEnabled(false);
        
    }//GEN-LAST:event_jLoginBtnActionPerformed

    private void jPersonDeleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPersonDeleteBtnActionPerformed
        // TODO add your handling code here:
        int selectedRow = jPersonTable.getSelectedRow();      
        if(selectedRow<0){
            JOptionPane.showMessageDialog(this, "Please select any row to delete");
            return;
        }  
        DefaultTableModel dtmodel = (DefaultTableModel) jPersonTable.getModel();
        Person selectedRowObj = (Person) dtmodel.getValueAt(selectedRow, 0);
        personCatalogue.removePerson(selectedRowObj);
        JOptionPane.showMessageDialog(this, "Person profile removed!");
        
        //refresh table data
        this.populatePersonTable();  
        
        //Clear deleted data
        clearPersonFields();
        disablePersonFields();
        
        jPersonUpdateBtn.setEnabled(false);
        jPersonDeleteBtn.setEnabled(false);
    }//GEN-LAST:event_jPersonDeleteBtnActionPerformed

    private void jHospitalDeleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHospitalDeleteBtnActionPerformed
        // TODO add your handling code here:
        int selectedRow = jHospitalTable.getSelectedRow();      
        if(selectedRow<0){
            JOptionPane.showMessageDialog(this, "Please select any row to delete");
            return;
        }  
        DefaultTableModel dtmodel = (DefaultTableModel) jHospitalTable.getModel();
        Hospital selectedRowObj = (Hospital) dtmodel.getValueAt(selectedRow, 0);
        hospitalCatalogue.removeHospital(selectedRowObj);
        JOptionPane.showMessageDialog(this, "Hospital profile removed!");
        
        //refresh table data
        this.populateHospitalTable();  
        
        //Clear deleted data
        clearHospitalFields();
        disableHospitalFields();
        jHospitalUpdateBtn.setEnabled(false);
        jHospitalDeleteBtn.setEnabled(false);
    }//GEN-LAST:event_jHospitalDeleteBtnActionPerformed

    private boolean validatePersonName(){
        boolean isValid;
        String validName = jPersonName.getText();
        Pattern pattern = Pattern.compile("[A-Za-z ]+");
        Matcher matcher = pattern.matcher(validName);
        isValid = matcher.matches();
        return isValid;
    }
    
    private boolean validatePersonTelNum(){
        boolean isValid;
        String validTelNum = jPersonTelNum.getText();
        Pattern pattern = Pattern.compile("[1-9]{1}[0-9]{9}");
        Matcher matcher = pattern.matcher(validTelNum);
        isValid = matcher.matches();
        return isValid;
    }
    
    private boolean validatePersonEmail(){
        boolean isValid;
        String validEmail = jPersonEmail.getText();
        Pattern pattern = Pattern.compile("[A-Za-z0-9]+[.]{0,1}[A-Za-z0-9]+[@](gmail.com|northeastern.edu)");
        Matcher matcher = pattern.matcher(validEmail);
        isValid = matcher.matches();
        return isValid;
    }
    
    private boolean validatePersonAddress(){
        boolean isValid;
        String validName = jPersonAddress.getText();
        Pattern pattern = Pattern.compile("[A-Za-z0-9. ]+");
        Matcher matcher = pattern.matcher(validName);
        isValid = matcher.matches();
        return isValid;
    }
    
    private boolean validateHospitalName(){
        boolean isValid;
        String validName = jHospitalName.getText();
        Pattern pattern = Pattern.compile("[A-Za-z ]+");
        Matcher matcher = pattern.matcher(validName);
        isValid = matcher.matches();
        return isValid;
    }
    
    private boolean validateHospitalTelNum(){
        boolean isValid;
        String validTelNum = jHospitalTelNum.getText();
        Pattern pattern = Pattern.compile("[1-9]{1}[0-9]{9}");
        Matcher matcher = pattern.matcher(validTelNum);
        isValid = matcher.matches();
        return isValid;
    }
    
    private boolean validateHospitalEmail(){
        boolean isValid;
        String validEmail = jHospitalEmail.getText();
        Pattern pattern = Pattern.compile("[A-Za-z0-9]+[.]{0,1}[A-Za-z0-9]+[@](gmail.com|northeastern.edu)");
        Matcher matcher = pattern.matcher(validEmail);
        isValid = matcher.matches();
        return isValid;
    }
    
    private boolean validateHospitalAddress(){
        boolean isValid;
        String validName = jHospitalAddress.getText();
        Pattern pattern = Pattern.compile("[A-Za-z0-9. ]+");
        Matcher matcher = pattern.matcher(validName);
        isValid = matcher.matches();
        return isValid;
    }
    
    private void jPersonUpdateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPersonUpdateBtnActionPerformed
        // TODO add your handling code here:
        int selectedRow = jPersonTable.getSelectedRow();      
        if(selectedRow<0){
            JOptionPane.showMessageDialog(this, "Please select any row to update");
            return;
        }  
        
//        //valildations
        boolean checkIsValid = validatePersonName() && validatePersonTelNum() && validatePersonEmail()  && validatePersonAddress();

        if(!checkIsValid){
            JOptionPane.showMessageDialog(this, "Please enter required fields!");
            return;
        }
        
        ArrayList<String> tempList = new ArrayList<>();
        personCatalogue.getPersonList().stream().forEach(action->{
            if(action.getPersonId()==Integer.parseInt(jPersonID.getText())){
               tempList.add(String.valueOf(action.getPersonAge()));
            }
        });
        
        //Display selectedRow
        Person updatedPerson = new Person();
        updatedPerson.setPersonId(Integer.parseInt(jPersonID.getText()));
        updatedPerson.setPersonName(jPersonName.getText());
        updatedPerson.setPersonAge(Integer.parseInt(tempList.get(0)));
        updatedPerson.setCity(jCommunityAdminCity.getSelectedItem().toString());
        updatedPerson.setCommunity(jCommunityAdminCommunity.getSelectedItem().toString());
        updatedPerson.setPersonAddress(jPersonAddress.getText());
        updatedPerson.setPersonTelNum(jPersonTelNum.getText());
        updatedPerson.setPersonEmail(jPersonEmail.getText());
        
        //update
        personCatalogue.updatePerson(updatedPerson, personCatalogue.getPersonList());
        JOptionPane.showMessageDialog(this, "Person profile updated!");
        this.populatePersonTable();   
       
        //Clear deleted data
        clearPersonFields();
        disablePersonFields();
        
        jPersonUpdateBtn.setEnabled(false);
        jPersonDeleteBtn.setEnabled(false);
    }//GEN-LAST:event_jPersonUpdateBtnActionPerformed

    private void jPersonNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPersonNameKeyReleased
        // TODO add your handling code here:
        if(!validatePersonName()){
            jPersonNameValidation.setText("Invalid Name");
            jPersonNameValidation.setForeground(Color.red);
        } else {
            jPersonNameValidation.setText(" ");
        } 
    }//GEN-LAST:event_jPersonNameKeyReleased

    private void jPersonTelNumKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPersonTelNumKeyReleased
        // TODO add your handling code here:
        if(!validatePersonTelNum()){
            jPersonTelNumValidation.setText("Invalid Phone Number");
            jPersonTelNumValidation.setForeground(Color.red);
        } else {
            jPersonTelNumValidation.setText(" ");
        }  
    }//GEN-LAST:event_jPersonTelNumKeyReleased

    private void jPersonEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPersonEmailKeyReleased
        // TODO add your handling code here:
        if(!validatePersonEmail()){
            jPersonEmailValidation.setText("Invalid Email");
            jPersonEmailValidation.setForeground(Color.red);
        } else {
            jPersonEmailValidation.setText(" ");
        } 
    }//GEN-LAST:event_jPersonEmailKeyReleased

    private void jPersonAddressKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPersonAddressKeyReleased
        // TODO add your handling code here:
        if(!validatePersonAddress()){
            jPersonAddressValidation.setText("Invalid Address");
            jPersonAddressValidation.setForeground(Color.red);
        } else {
            jPersonAddressValidation.setText(" ");
        } 
    }//GEN-LAST:event_jPersonAddressKeyReleased

    private void jHospitalUpdateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHospitalUpdateBtnActionPerformed
        // TODO add your handling code here:
        int selectedRow = jHospitalTable.getSelectedRow();      
        if(selectedRow<0){
            JOptionPane.showMessageDialog(this, "Please select any row to update");
            return;
        }  
        
//        //valildations
        boolean checkIsValid = validateHospitalName() && validateHospitalTelNum() && validateHospitalEmail()  && validateHospitalAddress();

        if(!checkIsValid){
            JOptionPane.showMessageDialog(this, "Please enter required fields!");
            return;
        }
        
        //Display selectedRow
        Hospital updatedHospital = new Hospital();
        updatedHospital.setHospitalID(Integer.parseInt(jHospitalID.getText()));
        updatedHospital.setHospitalName(jHospitalName.getText());
        updatedHospital.setCity(jCommunityAdminCity.getSelectedItem().toString());
        updatedHospital.setCommunity(jCommunityAdminCommunity.getSelectedItem().toString());
        updatedHospital.setAddress(jHospitalAddress.getText());
        updatedHospital.setContactNumber(jHospitalTelNum.getText());
        updatedHospital.setEmail(jHospitalEmail.getText());
        
        //update
        hospitalCatalogue.updateHospital(updatedHospital, hospitalCatalogue.getHospitalList());
        JOptionPane.showMessageDialog(this, "Hospital profile updated!");
        this.populateHospitalTable();   
       
        //Clear deleted data
        clearHospitalFields();
        disableHospitalFields();
        
        
        jHospitalUpdateBtn.setEnabled(false);
        jHospitalDeleteBtn.setEnabled(false);
    }//GEN-LAST:event_jHospitalUpdateBtnActionPerformed

    private void jHospitalNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jHospitalNameKeyReleased
        // TODO add your handling code here:
        if(!validateHospitalName()){
            jHospitalNameValidation.setText("Invalid Name");
            jHospitalNameValidation.setForeground(Color.red);
        } else {
            jHospitalNameValidation.setText(" ");
        } 
    }//GEN-LAST:event_jHospitalNameKeyReleased

    private void jHospitalTelNumKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jHospitalTelNumKeyReleased
        // TODO add your handling code here:
        if(!validateHospitalTelNum()){
            jHospitalTelNumValidation.setText("Invalid Phone Number");
            jHospitalTelNumValidation.setForeground(Color.red);
        } else {
            jHospitalTelNumValidation.setText(" ");
        }  
    }//GEN-LAST:event_jHospitalTelNumKeyReleased

    private void jHospitalEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jHospitalEmailKeyReleased
        // TODO add your handling code here:
        if(!validateHospitalEmail()){
            jHospitalEmailValidation.setText("Invalid Email");
            jHospitalEmailValidation.setForeground(Color.red);
        } else {
            jHospitalEmailValidation.setText(" ");
        } 
    }//GEN-LAST:event_jHospitalEmailKeyReleased

    private void jHospitalAddressKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jHospitalAddressKeyReleased
        // TODO add your handling code here:
        if(!validateHospitalAddress()){
            jHospitalAddressValidation.setText("Invalid Address");
            jHospitalAddressValidation.setForeground(Color.red);
        } else {
            jHospitalAddressValidation.setText(" ");
        } 
    }//GEN-LAST:event_jHospitalAddressKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jCommunityAdminCity;
    private javax.swing.JComboBox<String> jCommunityAdminCommunity;
    private javax.swing.JLabel jCommunityAdminName;
    private javax.swing.JTextField jHospitalAddress;
    private javax.swing.JLabel jHospitalAddressValidation;
    private javax.swing.JButton jHospitalDeleteBtn;
    private javax.swing.JTextField jHospitalEmail;
    private javax.swing.JLabel jHospitalEmailValidation;
    private javax.swing.JTextField jHospitalID;
    private javax.swing.JTextField jHospitalName;
    private javax.swing.JLabel jHospitalNameValidation;
    private javax.swing.JTable jHospitalTable;
    private javax.swing.JTextField jHospitalTelNum;
    private javax.swing.JLabel jHospitalTelNumValidation;
    private javax.swing.JButton jHospitalUpdateBtn;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JButton jLoginBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField jPersonAddress;
    private javax.swing.JLabel jPersonAddressValidation;
    private javax.swing.JButton jPersonDeleteBtn;
    private javax.swing.JTextField jPersonEmail;
    private javax.swing.JLabel jPersonEmailValidation;
    private javax.swing.JTextField jPersonID;
    private javax.swing.JTextField jPersonName;
    private javax.swing.JLabel jPersonNameValidation;
    private javax.swing.JTable jPersonTable;
    private javax.swing.JTextField jPersonTelNum;
    private javax.swing.JLabel jPersonTelNumValidation;
    private javax.swing.JButton jPersonUpdateBtn;
    private javax.swing.JButton jResetBtn;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel jTitle;
    private javax.swing.JLabel jTitle2;
    private javax.swing.JLabel jTitle3;
    // End of variables declaration//GEN-END:variables
}
