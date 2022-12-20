/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.SystemAdmin;

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
import model.Doctor;
import model.DoctorCatalogue;
import model.HospitalCatalogue;
import model.Patient;
import model.PatientCatalogue;
import model.Person;
import model.PersonCatalogue;

/**
 *
 * @author omerq
 */
public class AdminBaseJPanel extends javax.swing.JPanel {

    /**
     * Creates new form AdminBaseJPanel
     */
    
    DoctorCatalogue doctorCatalogue;
    HospitalCatalogue hospitalCatalogue;
    PatientCatalogue patientCatalogue;
    PersonCatalogue personCatalogue;
   
    private int doctorId = 1000; //Starting docId
    private int patientId = 1000; //Starting patientId
    private int personId = 1000; //Starting personId
    private int defaultId = 1000;
    
    public AdminBaseJPanel(DoctorCatalogue doctorCatalogue, HospitalCatalogue hospitalCatalogue, PatientCatalogue patientCatalogue, PersonCatalogue personCatalogue) {
        initComponents();
        this.doctorCatalogue = doctorCatalogue; 
        this.hospitalCatalogue = hospitalCatalogue;
        this.patientCatalogue = patientCatalogue;
        this.personCatalogue = personCatalogue;
        
        //Populate View Tables - Doctor/Patient
        populateDoctorTable();
        populatePatientTable();
        populatePersonTable();
        
        //Doctor ID
        jDoctorID.setEnabled(false); //non-editable - Manage Doctor View
        jDoctorID.setText(String.valueOf(generateDoctorId()));
        jDocID.setEnabled(false); //non-editable- Manage Patient View
        
        //Patient ID
        jPatientID.setEnabled(false);
        jPatientID.setText(String.valueOf(generatePatientId()));
        
        //Person ID
        jPersonId.setEnabled(false);
        jPersonId.setText(String.valueOf(generatePersonId()));
        
        jUpdateBtn.setEnabled(false);
        jDeleteBtn.setEnabled(false);
        jPatUpdateBtn.setEnabled(false);
        jPatDeleteBtn.setEnabled(false);
        jConsultedDoctor.setEnabled(false);
        jPersonUpdateBtn.setEnabled(false);
        jPersonDeleteBtn.setEnabled(false);
        
        //DropDown logic
        populateDropDownValues();
    }
    
    //ID
    private int generateDoctorId(){
        if(!doctorCatalogue.getDoctorList().isEmpty()){
            doctorId = doctorCatalogue.getDoctorList().get(doctorCatalogue.getDoctorList().size()-1).getDoctorId()+1;
            return doctorId;
        }   
        return defaultId;
    }
    
    private int generatePatientId(){
        if(!patientCatalogue.getPatientList().isEmpty()){
            patientId = patientCatalogue.getPatientList().get(patientCatalogue.getPatientList().size()-1).getPatientId()+1;
            return patientId;
        }   
        return defaultId;
    }
    
    private int generatePersonId(){
        if(!personCatalogue.getPersonList().isEmpty()){
            personId = personCatalogue.getPersonList().get(personCatalogue.getPersonList().size()-1).getPersonId()+1;
            return personId;
        }   
        return defaultId;
    }
    
    //Table Data
    private void populateDoctorTable() {
       DefaultTableModel dtmodel = (DefaultTableModel) jDoctorTable.getModel();
        dtmodel.setRowCount(0);
        
        for(Doctor doc: doctorCatalogue.getDoctorList()){
            Object[] obj = new Object[7];
            obj[0] = doc;
            obj[1] = doc.getName();
            obj[2] = doc.getCity();
            obj[3] = doc.getCommunity();
            obj[4] = doc.getHospital();
            obj[5] = doc.getPhoneNumber();
            obj[6] = doc.getEmail();
            dtmodel.addRow(obj);
        }
    }
    
    private void populatePatientTable() {
       DefaultTableModel dtmodel = (DefaultTableModel) jPatientTable.getModel();
        dtmodel.setRowCount(0);
        
        for(Patient pat: patientCatalogue.getPatientList()){
            Object[] obj = new Object[7];
            obj[0] = pat;
            obj[1] = pat.getPatientName();
            obj[2] = pat.getCity();
            obj[3] = pat.getCommunity();
            obj[4] = pat.getHospital();
            obj[5] = pat.getPatientTelNum();
            obj[6] = pat.getConsultedDoctor();
            dtmodel.addRow(obj);
        }
    }
    
    private void populatePersonTable() {
       DefaultTableModel dtmodel = (DefaultTableModel) jPersonTable.getModel();
        dtmodel.setRowCount(0);
        
        for(Person per: personCatalogue.getPersonList()){
            Object[] obj = new Object[7];
            obj[0] = per;
            obj[1] = per.getPersonName();
            obj[2] = per.getCity();
            obj[3] = per.getCommunity();
            obj[4] = per.getPersonAddress();
            obj[5] = per.getPersonTelNum();
            obj[6] = per.getPersonEmail();
            dtmodel.addRow(obj);
        }
    }
    
    //Drop-down logic
    private void populateDropDownValues(){
        //City 
        ArrayList<String> cityList = new ArrayList<>();
        cityList.add("-- Select --");
        this.hospitalCatalogue.getHospitalList().stream().forEach(action->{
            cityList.add(action.getCity());
        });
        Set<String> uniqueCities = new HashSet<String>(cityList);
        jDoctorCity.setModel(new DefaultComboBoxModel<>(uniqueCities.toArray(new String[0])));
        jPatientCity.setModel(new DefaultComboBoxModel<>(uniqueCities.toArray(new String[0])));
        jPersonCity.setModel(new DefaultComboBoxModel<>(uniqueCities.toArray(new String[0])));
        
        //Community
        List<String> communityList = new ArrayList<>();
        communityList.add("-- Select --");
        this.hospitalCatalogue.getHospitalList().stream().forEach(action->{
            communityList.add(action.getCommunity());
        });
        Set<String> uniqueCommunities = new HashSet<String>(communityList);
        jDoctorCommunity.setModel(new DefaultComboBoxModel<>(uniqueCommunities.toArray(new String[0])));
        jPatientCommunity.setModel(new DefaultComboBoxModel<>(uniqueCommunities.toArray(new String[0])));
        jPersonCommunity.setModel(new DefaultComboBoxModel<>(uniqueCommunities.toArray(new String[0])));
        
        //Hospital
        List<String> hospitalList = new ArrayList<>();
        hospitalList.add("-- Select --");
        this.hospitalCatalogue.getHospitalList().stream().forEach(action->{
            hospitalList.add(action.getHospitalName());
        });
        jDoctorHospital.setModel(new DefaultComboBoxModel<>(hospitalList.toArray(new String[0])));
        jPatientHospital.setModel(new DefaultComboBoxModel<>(hospitalList.toArray(new String[0])));
        
        //Advanced Search - Manage Doctor View
        List<String> searchList = new ArrayList<>();
        String[] searchOptions = {"-- Select --", "ID" , "City", "Community", "Hospital"};
        for(String str: searchOptions){
           searchList.add(str);
        }
        jAdvSearchOption.setModel(new DefaultComboBoxModel<>(searchList.toArray(new String[0])));
        
        //Advanced Search - Manage Patient View
        List<String> searchPatientList = new ArrayList<>();
        String[] searchPatientOptions = {"-- Select --", "ID" , "City", "Community", "Hospital", "Doctor"};
        for(String str: searchPatientOptions){
           searchPatientList.add(str);
        }
        jPatAdvSearchOption.setModel(new DefaultComboBoxModel<>(searchPatientList.toArray(new String[0])));
        
        //Advanced Search - Manage Person View
        List<String> searchPersonList = new ArrayList<>();
        String[] searchPersonOptions = {"-- Select --", "ID" , "City", "Community"};
        for(String str: searchPersonOptions){
           searchPersonList.add(str);
        }
        jPersonAdvSearchOption.setModel(new DefaultComboBoxModel<>(searchPersonList.toArray(new String[0])));
    }
    
    private void populateDropDownAfterReset(){
        //Community
        List<String> communityList = new ArrayList<>();
        communityList.add("-- Select --");
        this.hospitalCatalogue.getHospitalList().stream().forEach(action->{
            communityList.add(action.getCommunity());
        });   
        jDoctorCommunity.setModel(new DefaultComboBoxModel<>(communityList.toArray(new String[0])));
        jPatientCommunity.setModel(new DefaultComboBoxModel<>(communityList.toArray(new String[0])));
        jPersonCommunity.setModel(new DefaultComboBoxModel<>(communityList.toArray(new String[0])));
        
        //Hospital
        List<String> hospitalList = new ArrayList<>();
        hospitalList.add("-- Select --");
        this.hospitalCatalogue.getHospitalList().stream().forEach(action->{
            hospitalList.add(action.getHospitalName());
        }); 
        jDoctorHospital.setModel(new DefaultComboBoxModel<>(hospitalList.toArray(new String[0])));
        jPatientHospital.setModel(new DefaultComboBoxModel<>(hospitalList.toArray(new String[0])));
        
        //DoctorNames
        List<String> doctorList = new ArrayList<>();
        doctorList.add("-- Select --");
        this.doctorCatalogue.getDoctorList().stream().forEach(action->{
            doctorList.add(action.getName());
        });  
        jConsultedDoctor.setModel(new DefaultComboBoxModel<>(doctorList.toArray(new String[0])));
    }
    
    //Clear fields logic
    private void clearFields(){
        //populate dropdown
        populateDropDownAfterReset();
        
        //Clear fields
        jDoctorID.setText("");
        jDoctorName.setText("");
        jDoctorAge.setText("");
        jDoctorGender.setSelectedIndex(0);
        jDoctorCity.setSelectedIndex(0);
        jDoctorCommunity.setSelectedIndex(0);
        jDoctorHospital.setSelectedIndex(0);
        jDoctorTelNum.setText("");
        jDoctorEmail.setText("");
        
        //Clear validation messages
        jDocNameValidation.setText(" ");
        jDocAgeValidation.setText(" ");
        jDocGenderValidation.setText(" ");
        jDocTelNumValidation.setText(" ");
        jDocEmailValidation.setText(" ");
        jDocCityValidation.setText(" ");
        jDocCommunityValidation.setText(" ");
        jDocHospitalValidation.setText(" ");
        jAdvSearchValidation.setText(" ");
        
        //Refresh doctorId
        jDoctorID.setText(String.valueOf(generateDoctorId()));
    }
    
    private void clearPatientFields(){
        //populate dropdown
        populateDropDownAfterReset(); 
        
        //Clear fields
        jPatientID.setText("");
        jPatientName.setText("");
        jPatientAge.setText("");
        jPatientCity.setSelectedIndex(0);
        jPatientCommunity.setSelectedIndex(0);
        jPatientHospital.setSelectedIndex(0);
        jPatientTelNum.setText("");
        jPatientEmail.setText("");
        
        jDocID.setText("");
        jConsultedDoctor.setSelectedIndex(0);
        
        //Clear validation messages
        jPatNameValidation.setText(" ");
        jPatAgeValidation.setText(" ");
        jPatTelNumValidation.setText(" ");
        jPatEmailValidation.setText(" ");
        jPatCityValidation.setText(" ");
        jPatCommunityValidation.setText(" ");
        jPatHospitalValidation.setText(" ");
        jPatAdvSearchValidation.setText(" ");
        jPatDocNameValidation.setText(" ");
        
        //Refresh doctorId
        jPatientID.setText(String.valueOf(generatePatientId()));
    }
    
    private void clearPersonFields(){
        //populate dropdown
        populateDropDownAfterReset(); 
        
        //Clear fields
        jPersonId.setText("");
        jPersonName.setText("");
        jPersonAge.setText("");
        jPersonCity.setSelectedIndex(0);
        jPersonCommunity.setSelectedIndex(0);
        jPersonAddress.setText("");
        jPersonTelNum.setText("");
        jPersonEmail.setText("");
        
        //Clear validation messages
        jPersonNameValidation.setText(" ");
        jPersonAgeValidation.setText(" ");
        jPersonTelNumValidation.setText(" ");
        jPersonEmailValidation.setText(" ");
        jPersonCityValidation.setText(" ");
        jPersonCommunityValidation.setText(" ");
        jPersonAddressValidation.setText(" ");
        jPersonAdvSearchValidation.setText(" ");
        
        //Refresh doctorId
        jPersonId.setText(String.valueOf(generatePersonId()));
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
        jTabbedPane = new javax.swing.JTabbedPane();
        jManageDoctorsPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jDoctorTable = new javax.swing.JTable();
        jAdvancedSearchPanel = new javax.swing.JPanel();
        jAdvSearchOption = new javax.swing.JComboBox<>();
        jAdvSearch = new javax.swing.JTextField();
        jAdvSearchBtn = new javax.swing.JButton();
        jResetBtn = new javax.swing.JButton();
        jAdvSearchValidation = new javax.swing.JLabel();
        jActionPanel = new javax.swing.JPanel();
        jUpdateBtn = new javax.swing.JButton();
        jDeleteBtn = new javax.swing.JButton();
        jAddBtn = new javax.swing.JButton();
        jDoctorInfoPanel = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jEmail = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTelNum = new javax.swing.JTextField();
        jTelNumValidation = new javax.swing.JLabel();
        jEmailValidation = new javax.swing.JLabel();
        jPInfoPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jDoctorName = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jDoctorGender = new javax.swing.JComboBox<>();
        jDocNameValidation = new javax.swing.JLabel();
        jDocAgeValidation = new javax.swing.JLabel();
        jDocGenderValidation = new javax.swing.JLabel();
        jDocCommunityValidation = new javax.swing.JLabel();
        jDoctorAge = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jDoctorID = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jDoctorCommunity = new javax.swing.JComboBox<>();
        jDoctorCity = new javax.swing.JComboBox<>();
        jDoctorHospital = new javax.swing.JComboBox<>();
        jDocCityValidation = new javax.swing.JLabel();
        jDocHospitalValidation = new javax.swing.JLabel();
        jPInfoPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jDoctorEmail = new javax.swing.JTextField();
        jDocEmailValidation = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jDoctorTelNum = new javax.swing.JTextField();
        jDocTelNumValidation = new javax.swing.JLabel();
        jManagePatientsPanel = new javax.swing.JPanel();
        jAdvancedSearchPanel2 = new javax.swing.JPanel();
        jPatAdvSearchOption = new javax.swing.JComboBox<>();
        jPatAdvSearch = new javax.swing.JTextField();
        jPatAdvSearchBtn = new javax.swing.JButton();
        jPatResetBtn = new javax.swing.JButton();
        jPatAdvSearchValidation = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPatientTable = new javax.swing.JTable();
        jPInfoPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPatientEmail = new javax.swing.JTextField();
        jPatEmailValidation = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPatientTelNum = new javax.swing.JTextField();
        jPatTelNumValidation = new javax.swing.JLabel();
        jPInfoPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPatientName = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPatNameValidation = new javax.swing.JLabel();
        jPatAgeValidation = new javax.swing.JLabel();
        jPatCommunityValidation = new javax.swing.JLabel();
        jPatientAge = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPatientID = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jPatientCommunity = new javax.swing.JComboBox<>();
        jPatientCity = new javax.swing.JComboBox<>();
        jPatientHospital = new javax.swing.JComboBox<>();
        jPatCityValidation = new javax.swing.JLabel();
        jPatHospitalValidation = new javax.swing.JLabel();
        jActionPanel1 = new javax.swing.JPanel();
        jPatUpdateBtn = new javax.swing.JButton();
        jPatDeleteBtn = new javax.swing.JButton();
        jPatAddBtn = new javax.swing.JButton();
        jPInfoPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jDocID = new javax.swing.JTextField();
        jConsultedDoctor = new javax.swing.JComboBox<>();
        jPatDocNameValidation = new javax.swing.JLabel();
        jManagePeoplePanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPersonTable = new javax.swing.JTable();
        jPInfoPanel5 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPersonName = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jPersonNameValidation = new javax.swing.JLabel();
        jPersonAgeValidation = new javax.swing.JLabel();
        jPersonCommunityValidation = new javax.swing.JLabel();
        jPersonAge = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jPersonId = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jPersonCommunity = new javax.swing.JComboBox<>();
        jPersonCity = new javax.swing.JComboBox<>();
        jPersonCityValidation = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jPersonAddress = new javax.swing.JTextField();
        jPersonAddressValidation = new javax.swing.JLabel();
        jPInfoPanel6 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jPersonEmail = new javax.swing.JTextField();
        jPersonEmailValidation = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jPersonTelNum = new javax.swing.JTextField();
        jPersonTelNumValidation = new javax.swing.JLabel();
        jActionPanel2 = new javax.swing.JPanel();
        jPersonUpdateBtn = new javax.swing.JButton();
        jPersonDeleteBtn = new javax.swing.JButton();
        jPersonAddBtn = new javax.swing.JButton();
        jAdvancedSearchPanel3 = new javax.swing.JPanel();
        jPersonAdvSearchOption = new javax.swing.JComboBox<>();
        jPersonAdvSearch = new javax.swing.JTextField();
        jPersonAdvSearchBtn = new javax.swing.JButton();
        jPersonResetBtn = new javax.swing.JButton();
        jPersonAdvSearchValidation = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(1100, 800));
        setMinimumSize(new java.awt.Dimension(1100, 800));
        setPreferredSize(new java.awt.Dimension(1100, 800));

        jTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jTitle.setText("SYSTEM ADMIN");

        jManageDoctorsPanel.setMaximumSize(new java.awt.Dimension(1100, 800));
        jManageDoctorsPanel.setMinimumSize(new java.awt.Dimension(1100, 800));
        jManageDoctorsPanel.setPreferredSize(new java.awt.Dimension(1100, 800));

        jDoctorTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
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
                "Doctor ID", "Doctor Name", "City", "Community", "Hospital", "Telephone", "Email ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jDoctorTable.getTableHeader().setReorderingAllowed(false);
        jDoctorTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jDoctorTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jDoctorTable);

        jAdvancedSearchPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Advanced Search"));
        jAdvancedSearchPanel.setPreferredSize(new java.awt.Dimension(492, 100));

        jAdvSearchOption.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select --", "Emp ID", "Name", "Team", "Level", "Title", "Start Date", "Telephone", "Email" }));
        jAdvSearchOption.setMaximumSize(new java.awt.Dimension(91, 22));
        jAdvSearchOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAdvSearchOptionActionPerformed(evt);
            }
        });

        jAdvSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAdvSearchActionPerformed(evt);
            }
        });

        jAdvSearchBtn.setText("Search");
        jAdvSearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAdvSearchBtnActionPerformed(evt);
            }
        });

        jResetBtn.setText("Reset");
        jResetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jResetBtnActionPerformed(evt);
            }
        });

        jAdvSearchValidation.setText("   ");

        javax.swing.GroupLayout jAdvancedSearchPanelLayout = new javax.swing.GroupLayout(jAdvancedSearchPanel);
        jAdvancedSearchPanel.setLayout(jAdvancedSearchPanelLayout);
        jAdvancedSearchPanelLayout.setHorizontalGroup(
            jAdvancedSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jAdvancedSearchPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jAdvSearchOption, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jAdvancedSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jAdvSearchValidation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jAdvSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jAdvSearchBtn)
                .addGap(10, 10, 10)
                .addComponent(jResetBtn)
                .addGap(20, 20, 20))
        );
        jAdvancedSearchPanelLayout.setVerticalGroup(
            jAdvancedSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jAdvancedSearchPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jAdvancedSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jAdvSearchOption, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jAdvSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jAdvSearchBtn)
                    .addComponent(jResetBtn))
                .addGap(5, 5, 5)
                .addComponent(jAdvSearchValidation)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jActionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Action"));

        jUpdateBtn.setText("Update");
        jUpdateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jUpdateBtnActionPerformed(evt);
            }
        });

        jDeleteBtn.setText("Delete");
        jDeleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDeleteBtnActionPerformed(evt);
            }
        });

        jAddBtn.setText("Add");
        jAddBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAddBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jActionPanelLayout = new javax.swing.GroupLayout(jActionPanel);
        jActionPanel.setLayout(jActionPanelLayout);
        jActionPanelLayout.setHorizontalGroup(
            jActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jActionPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jAddBtn)
                .addGap(12, 12, 12)
                .addComponent(jUpdateBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jDeleteBtn)
                .addGap(10, 10, 10))
        );

        jActionPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jAddBtn, jDeleteBtn, jUpdateBtn});

        jActionPanelLayout.setVerticalGroup(
            jActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jActionPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jAddBtn)
                    .addComponent(jUpdateBtn)
                    .addComponent(jDeleteBtn))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jActionPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jAddBtn, jDeleteBtn, jUpdateBtn});

        jDoctorInfoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Contact Information"));
        jDoctorInfoPanel.setPreferredSize(new java.awt.Dimension(508, 105));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setText("Telephone");

        jEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jEmailKeyReleased(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setText("Email");

        jTelNum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTelNumKeyReleased(evt);
            }
        });

        jTelNumValidation.setText("   ");

        jEmailValidation.setText("   ");

        javax.swing.GroupLayout jDoctorInfoPanelLayout = new javax.swing.GroupLayout(jDoctorInfoPanel);
        jDoctorInfoPanel.setLayout(jDoctorInfoPanelLayout);
        jDoctorInfoPanelLayout.setHorizontalGroup(
            jDoctorInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDoctorInfoPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jDoctorInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDoctorInfoPanelLayout.createSequentialGroup()
                        .addComponent(jTelNum, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel17))
                    .addComponent(jTelNumValidation, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jDoctorInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jEmailValidation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        jDoctorInfoPanelLayout.setVerticalGroup(
            jDoctorInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDoctorInfoPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jDoctorInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jDoctorInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTelNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addGroup(jDoctorInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTelNumValidation)
                    .addComponent(jEmailValidation))
                .addGap(14, 14, 14))
        );

        jPInfoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Doctor Information"));
        jPInfoPanel.setPreferredSize(new java.awt.Dimension(508, 276));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Name");

        jDoctorName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jDoctorNameKeyReleased(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Gender");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("Age");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setText("Community");

        jDoctorGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select --", "Male", "Female", "Others" }));
        jDoctorGender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDoctorGenderActionPerformed(evt);
            }
        });

        jDocNameValidation.setText("   ");

        jDocAgeValidation.setText("   ");

        jDocGenderValidation.setText("   ");

        jDocCommunityValidation.setText("   ");

        jDoctorAge.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jDoctorAgeKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("DoctorId");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel24.setText("City");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel25.setText("Hospital");

        jDoctorCommunity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select --", "Male", "Female", "Others" }));
        jDoctorCommunity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDoctorCommunityActionPerformed(evt);
            }
        });

        jDoctorCity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select --" }));
        jDoctorCity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDoctorCityActionPerformed(evt);
            }
        });

        jDoctorHospital.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select --", "Male", "Female", "Others" }));
        jDoctorHospital.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDoctorHospitalActionPerformed(evt);
            }
        });

        jDocCityValidation.setText("   ");

        jDocHospitalValidation.setText("   ");

        javax.swing.GroupLayout jPInfoPanelLayout = new javax.swing.GroupLayout(jPInfoPanel);
        jPInfoPanel.setLayout(jPInfoPanelLayout);
        jPInfoPanelLayout.setHorizontalGroup(
            jPInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPInfoPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPInfoPanelLayout.createSequentialGroup()
                        .addGroup(jPInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPInfoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(45, 45, 45)
                                .addGroup(jPInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jDocAgeValidation, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jDoctorID, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jDoctorAge, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPInfoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(167, 167, 167))
                            .addGroup(jPInfoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addGap(173, 173, 173)))
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPInfoPanelLayout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(47, 47, 47)
                        .addGroup(jPInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDoctorHospital, 0, 148, Short.MAX_VALUE)
                            .addComponent(jDoctorCity, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDocCityValidation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDocHospitalValidation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jLabel15)
                    .addComponent(jLabel4))
                .addGroup(jPInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPInfoPanelLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDoctorGender, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDoctorName)
                            .addComponent(jDocNameValidation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDocGenderValidation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDocCommunityValidation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPInfoPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDoctorCommunity, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPInfoPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jDoctorID, jDoctorName});

        jPInfoPanelLayout.setVerticalGroup(
            jPInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPInfoPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPInfoPanelLayout.createSequentialGroup()
                        .addGroup(jPInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPInfoPanelLayout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addGroup(jPInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPInfoPanelLayout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(jDoctorAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(5, 5, 5)
                        .addComponent(jDocAgeValidation))
                    .addGroup(jPInfoPanelLayout.createSequentialGroup()
                        .addGroup(jPInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jDoctorName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDoctorID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addComponent(jDocNameValidation)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDoctorGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jDocGenderValidation)))
                .addGap(10, 10, 10)
                .addGroup(jPInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPInfoPanelLayout.createSequentialGroup()
                        .addGroup(jPInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDoctorCommunity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDoctorCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jDocCommunityValidation)
                            .addComponent(jDocCityValidation)))
                    .addGroup(jPInfoPanelLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(jPInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDoctorHospital, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jDocHospitalValidation)
                .addGap(115, 115, 115))
        );

        jPInfoPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jDoctorID, jDoctorName});

        jPInfoPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Contact Information"));
        jPInfoPanel1.setPreferredSize(new java.awt.Dimension(508, 276));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Email");

        jDoctorEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jDoctorEmailKeyReleased(evt);
            }
        });

        jDocEmailValidation.setText("   ");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Telephone");

        jDoctorTelNum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jDoctorTelNumKeyReleased(evt);
            }
        });

        jDocTelNumValidation.setText("   ");

        javax.swing.GroupLayout jPInfoPanel1Layout = new javax.swing.GroupLayout(jPInfoPanel1);
        jPInfoPanel1.setLayout(jPInfoPanel1Layout);
        jPInfoPanel1Layout.setHorizontalGroup(
            jPInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPInfoPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jDoctorTelNum, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jDocTelNumValidation, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPInfoPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(167, 167, 167)))
                .addGap(20, 20, 20)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDocEmailValidation, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDoctorEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPInfoPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jDoctorEmail, jDoctorTelNum});

        jPInfoPanel1Layout.setVerticalGroup(
            jPInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPInfoPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jDoctorEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDoctorTelNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jDocEmailValidation)
                    .addComponent(jDocTelNumValidation))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jPInfoPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jDoctorEmail, jDoctorTelNum});

        javax.swing.GroupLayout jManageDoctorsPanelLayout = new javax.swing.GroupLayout(jManageDoctorsPanel);
        jManageDoctorsPanel.setLayout(jManageDoctorsPanelLayout);
        jManageDoctorsPanelLayout.setHorizontalGroup(
            jManageDoctorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jManageDoctorsPanelLayout.createSequentialGroup()
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jDoctorInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(634, 634, 634))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jManageDoctorsPanelLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jManageDoctorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jManageDoctorsPanelLayout.createSequentialGroup()
                        .addGroup(jManageDoctorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jManageDoctorsPanelLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jAdvancedSearchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1))
                        .addGap(45, 45, 45))
                    .addGroup(jManageDoctorsPanelLayout.createSequentialGroup()
                        .addComponent(jPInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jManageDoctorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jManageDoctorsPanelLayout.createSequentialGroup()
                                .addGap(133, 133, 133)
                                .addComponent(jActionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jManageDoctorsPanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jPInfoPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(44, 44, 44))))
        );
        jManageDoctorsPanelLayout.setVerticalGroup(
            jManageDoctorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jManageDoctorsPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jAdvancedSearchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jManageDoctorsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jManageDoctorsPanelLayout.createSequentialGroup()
                        .addComponent(jPInfoPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jActionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1969, 1969, 1969)
                .addComponent(jDoctorInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Manage Doctors", jManageDoctorsPanel);

        jManagePatientsPanel.setMaximumSize(new java.awt.Dimension(864, 464));
        jManagePatientsPanel.setMinimumSize(new java.awt.Dimension(864, 464));

        jAdvancedSearchPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Advanced Search"));
        jAdvancedSearchPanel2.setPreferredSize(new java.awt.Dimension(492, 100));

        jPatAdvSearchOption.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select --" }));
        jPatAdvSearchOption.setMaximumSize(new java.awt.Dimension(91, 22));
        jPatAdvSearchOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPatAdvSearchOptionActionPerformed(evt);
            }
        });

        jPatAdvSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPatAdvSearchActionPerformed(evt);
            }
        });

        jPatAdvSearchBtn.setText("Search");
        jPatAdvSearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPatAdvSearchBtnActionPerformed(evt);
            }
        });

        jPatResetBtn.setText("Reset");
        jPatResetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPatResetBtnActionPerformed(evt);
            }
        });

        jPatAdvSearchValidation.setText("   ");

        javax.swing.GroupLayout jAdvancedSearchPanel2Layout = new javax.swing.GroupLayout(jAdvancedSearchPanel2);
        jAdvancedSearchPanel2.setLayout(jAdvancedSearchPanel2Layout);
        jAdvancedSearchPanel2Layout.setHorizontalGroup(
            jAdvancedSearchPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jAdvancedSearchPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPatAdvSearchOption, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jAdvancedSearchPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPatAdvSearchValidation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPatAdvSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addComponent(jPatAdvSearchBtn)
                .addGap(10, 10, 10)
                .addComponent(jPatResetBtn)
                .addGap(20, 20, 20))
        );
        jAdvancedSearchPanel2Layout.setVerticalGroup(
            jAdvancedSearchPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jAdvancedSearchPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jAdvancedSearchPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPatAdvSearchOption, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPatAdvSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPatAdvSearchBtn)
                    .addComponent(jPatResetBtn))
                .addGap(5, 5, 5)
                .addComponent(jPatAdvSearchValidation)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jPatientTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Patient ID", "Name", "City", "Community", "Hospital", "Telephone", "Doctor Assigned"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jPatientTable.getTableHeader().setReorderingAllowed(false);
        jPatientTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPatientTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jPatientTable);
        if (jPatientTable.getColumnModel().getColumnCount() > 0) {
            jPatientTable.getColumnModel().getColumn(4).setHeaderValue("Hospital");
            jPatientTable.getColumnModel().getColumn(6).setHeaderValue("Doctor Assigned");
        }

        jPInfoPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Contact Information"));
        jPInfoPanel2.setPreferredSize(new java.awt.Dimension(508, 276));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Email");

        jPatientEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPatientEmailKeyReleased(evt);
            }
        });

        jPatEmailValidation.setText("   ");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Telephone");

        jPatientTelNum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPatientTelNumKeyReleased(evt);
            }
        });

        jPatTelNumValidation.setText("   ");

        javax.swing.GroupLayout jPInfoPanel2Layout = new javax.swing.GroupLayout(jPInfoPanel2);
        jPInfoPanel2.setLayout(jPInfoPanel2Layout);
        jPInfoPanel2Layout.setHorizontalGroup(
            jPInfoPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPInfoPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPInfoPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPInfoPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPatientTelNum, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPatTelNumValidation, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPInfoPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(167, 167, 167)))
                .addGroup(jPInfoPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPInfoPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addComponent(jPatientEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPatEmailValidation, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        jPInfoPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPatientEmail, jPatientTelNum});

        jPInfoPanel2Layout.setVerticalGroup(
            jPInfoPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPInfoPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPInfoPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPatientEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPatientTelNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPInfoPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPatEmailValidation)
                    .addComponent(jPatTelNumValidation))
                .addGap(18, 18, 18))
        );

        jPInfoPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Patient Information"));
        jPInfoPanel3.setPreferredSize(new java.awt.Dimension(508, 276));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Name");

        jPatientName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPatientNameKeyReleased(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setText("Age");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel22.setText("Community");

        jPatNameValidation.setText("   ");

        jPatAgeValidation.setText("   ");

        jPatCommunityValidation.setText("   ");

        jPatientAge.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPatientAgeKeyReleased(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("PatientId");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel26.setText("City");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel27.setText("Hospital");

        jPatientCommunity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select --" }));
        jPatientCommunity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPatientCommunityActionPerformed(evt);
            }
        });

        jPatientCity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select --" }));
        jPatientCity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPatientCityActionPerformed(evt);
            }
        });

        jPatientHospital.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select --" }));
        jPatientHospital.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPatientHospitalActionPerformed(evt);
            }
        });

        jPatCityValidation.setText("   ");

        jPatHospitalValidation.setText("   ");

        javax.swing.GroupLayout jPInfoPanel3Layout = new javax.swing.GroupLayout(jPInfoPanel3);
        jPInfoPanel3.setLayout(jPInfoPanel3Layout);
        jPInfoPanel3Layout.setHorizontalGroup(
            jPInfoPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPInfoPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPInfoPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPInfoPanel3Layout.createSequentialGroup()
                        .addGroup(jPInfoPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPInfoPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPInfoPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jPatientAge, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel26)
                                .addGap(225, 225, 225))
                            .addGroup(jPInfoPanel3Layout.createSequentialGroup()
                                .addGroup(jPInfoPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPatAgeValidation, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPatientID, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16)
                                .addComponent(jLabel10))))
                    .addGroup(jPInfoPanel3Layout.createSequentialGroup()
                        .addGroup(jPInfoPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPatCommunityValidation, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPInfoPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPatientCommunity, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel27)
                        .addGap(5, 5, 5)
                        .addGroup(jPInfoPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPInfoPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPatientHospital, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPatHospitalValidation, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPatientCity, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPatientName)
                            .addComponent(jPatNameValidation, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPatCityValidation, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))))
        );

        jPInfoPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPatientID, jPatientName});

        jPInfoPanel3Layout.setVerticalGroup(
            jPInfoPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPInfoPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPInfoPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPInfoPanel3Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPInfoPanel3Layout.createSequentialGroup()
                        .addGroup(jPInfoPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPatientID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPInfoPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPInfoPanel3Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(jPatAgeValidation))
                            .addGroup(jPInfoPanel3Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPInfoPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPatientAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPInfoPanel3Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jPatNameValidation)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPatientCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPatCityValidation)))))
                .addGap(3, 3, 3)
                .addGroup(jPInfoPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPInfoPanel3Layout.createSequentialGroup()
                        .addGroup(jPInfoPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPatientCommunity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addComponent(jPatCommunityValidation))
                    .addGroup(jPInfoPanel3Layout.createSequentialGroup()
                        .addGroup(jPInfoPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPatientHospital, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addComponent(jPatHospitalValidation)))
                .addGap(18, 18, 18))
        );

        jActionPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Action"));

        jPatUpdateBtn.setText("Update");
        jPatUpdateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPatUpdateBtnActionPerformed(evt);
            }
        });

        jPatDeleteBtn.setText("Delete");
        jPatDeleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPatDeleteBtnActionPerformed(evt);
            }
        });

        jPatAddBtn.setText("Add");
        jPatAddBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPatAddBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jActionPanel1Layout = new javax.swing.GroupLayout(jActionPanel1);
        jActionPanel1.setLayout(jActionPanel1Layout);
        jActionPanel1Layout.setHorizontalGroup(
            jActionPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jActionPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPatAddBtn)
                .addGap(10, 10, 10)
                .addComponent(jPatUpdateBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPatDeleteBtn)
                .addGap(16, 16, 16))
        );

        jActionPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPatAddBtn, jPatUpdateBtn});

        jActionPanel1Layout.setVerticalGroup(
            jActionPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jActionPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jActionPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPatUpdateBtn)
                    .addComponent(jPatDeleteBtn)
                    .addComponent(jPatAddBtn))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPInfoPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Doctor Information"));
        jPInfoPanel4.setPreferredSize(new java.awt.Dimension(508, 276));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Name");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("DoctorId");

        jDocID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDocIDActionPerformed(evt);
            }
        });

        jConsultedDoctor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select --" }));
        jConsultedDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jConsultedDoctorActionPerformed(evt);
            }
        });

        jPatDocNameValidation.setText("   ");

        javax.swing.GroupLayout jPInfoPanel4Layout = new javax.swing.GroupLayout(jPInfoPanel4);
        jPInfoPanel4.setLayout(jPInfoPanel4Layout);
        jPInfoPanel4Layout.setHorizontalGroup(
            jPInfoPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPInfoPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPInfoPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPatDocNameValidation, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPInfoPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jDocID, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jConsultedDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPInfoPanel4Layout.setVerticalGroup(
            jPInfoPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPInfoPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPInfoPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPInfoPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jDocID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jConsultedDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(2, 2, 2)
                .addComponent(jPatDocNameValidation)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jManagePatientsPanelLayout = new javax.swing.GroupLayout(jManagePatientsPanel);
        jManagePatientsPanel.setLayout(jManagePatientsPanelLayout);
        jManagePatientsPanelLayout.setHorizontalGroup(
            jManagePatientsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jManagePatientsPanelLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jManagePatientsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jAdvancedSearchPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jManagePatientsPanelLayout.createSequentialGroup()
                        .addGroup(jManagePatientsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPInfoPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPInfoPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jManagePatientsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPInfoPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jManagePatientsPanelLayout.createSequentialGroup()
                                .addGap(115, 115, 115)
                                .addComponent(jActionPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(48, 48, 48))
            .addGroup(jManagePatientsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jManagePatientsPanelLayout.createSequentialGroup()
                    .addGap(44, 44, 44)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(56, Short.MAX_VALUE)))
        );
        jManagePatientsPanelLayout.setVerticalGroup(
            jManagePatientsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jManagePatientsPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jAdvancedSearchPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(200, 200, 200)
                .addGroup(jManagePatientsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jManagePatientsPanelLayout.createSequentialGroup()
                        .addComponent(jPInfoPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jActionPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPInfoPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jPInfoPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(130, 130, 130))
            .addGroup(jManagePatientsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jManagePatientsPanelLayout.createSequentialGroup()
                    .addGap(148, 148, 148)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(512, Short.MAX_VALUE)))
        );

        jTabbedPane.addTab("Manage Patients", jManagePatientsPanel);

        jManagePeoplePanel.setMaximumSize(new java.awt.Dimension(864, 464));
        jManagePeoplePanel.setMinimumSize(new java.awt.Dimension(864, 464));

        jPersonTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Person ID", "Name", "City", "Community", "Address", "Telephone", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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

        jPInfoPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Person Information"));
        jPInfoPanel5.setPreferredSize(new java.awt.Dimension(508, 276));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Name");

        jPersonName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPersonNameKeyReleased(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel29.setText("Age");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel30.setText("Community");

        jPersonNameValidation.setText("   ");

        jPersonAgeValidation.setText("   ");

        jPersonCommunityValidation.setText("   ");

        jPersonAge.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPersonAgeKeyReleased(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setText("PersonId");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel31.setText("City");

        jPersonCommunity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select --" }));
        jPersonCommunity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPersonCommunityActionPerformed(evt);
            }
        });

        jPersonCity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select --" }));
        jPersonCity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPersonCityActionPerformed(evt);
            }
        });

        jPersonCityValidation.setText("   ");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel32.setText("Address");

        jPersonAddress.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPersonAddressKeyReleased(evt);
            }
        });

        jPersonAddressValidation.setText("   ");

        javax.swing.GroupLayout jPInfoPanel5Layout = new javax.swing.GroupLayout(jPInfoPanel5);
        jPInfoPanel5.setLayout(jPInfoPanel5Layout);
        jPInfoPanel5Layout.setHorizontalGroup(
            jPInfoPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPInfoPanel5Layout.createSequentialGroup()
                .addGroup(jPInfoPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPInfoPanel5Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPersonNameValidation, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPInfoPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPInfoPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPInfoPanel5Layout.createSequentialGroup()
                                .addGroup(jPInfoPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel29)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPInfoPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPInfoPanel5Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jPersonAge, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel31)
                                        .addGap(39, 39, 39)
                                        .addComponent(jPersonCity, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPInfoPanel5Layout.createSequentialGroup()
                                        .addComponent(jPersonId, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(16, 16, 16)
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPersonName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPInfoPanel5Layout.createSequentialGroup()
                                        .addComponent(jPersonAgeValidation, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPersonCityValidation, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPInfoPanel5Layout.createSequentialGroup()
                                .addGroup(jPInfoPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPersonCommunityValidation, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPInfoPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel30)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jPersonCommunity, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel32)
                                .addGap(17, 17, 17)
                                .addGroup(jPInfoPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPersonAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPersonAddressValidation, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(18, 18, 18))
        );
        jPInfoPanel5Layout.setVerticalGroup(
            jPInfoPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPInfoPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPInfoPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPInfoPanel5Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPInfoPanel5Layout.createSequentialGroup()
                        .addGroup(jPInfoPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jPersonName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPersonId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPInfoPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPInfoPanel5Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPInfoPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPersonAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPersonCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPInfoPanel5Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jPersonNameValidation)
                                .addGap(39, 39, 39)
                                .addGroup(jPInfoPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jPersonCityValidation)
                                    .addComponent(jPersonAgeValidation))))))
                .addGap(5, 5, 5)
                .addGroup(jPInfoPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPersonCommunity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPersonAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPInfoPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPersonCommunityValidation)
                    .addComponent(jPersonAddressValidation))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPInfoPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Contact Information"));
        jPInfoPanel6.setPreferredSize(new java.awt.Dimension(508, 276));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel33.setText("Email");

        jPersonEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPersonEmailKeyReleased(evt);
            }
        });

        jPersonEmailValidation.setText("   ");

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel34.setText("Telephone");

        jPersonTelNum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPersonTelNumKeyReleased(evt);
            }
        });

        jPersonTelNumValidation.setText("   ");

        javax.swing.GroupLayout jPInfoPanel6Layout = new javax.swing.GroupLayout(jPInfoPanel6);
        jPInfoPanel6.setLayout(jPInfoPanel6Layout);
        jPInfoPanel6Layout.setHorizontalGroup(
            jPInfoPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPInfoPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPInfoPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPInfoPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPersonTelNum, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPersonTelNumValidation, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPInfoPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addGap(167, 167, 167)))
                .addGroup(jPInfoPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPInfoPanel6Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addComponent(jPersonEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPersonEmailValidation, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
        jPInfoPanel6Layout.setVerticalGroup(
            jPInfoPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPInfoPanel6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPInfoPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPersonEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPersonTelNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPInfoPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPersonEmailValidation)
                    .addComponent(jPersonTelNumValidation))
                .addGap(18, 18, 18))
        );

        jActionPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Action"));

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

        jPersonAddBtn.setText("Add");
        jPersonAddBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPersonAddBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jActionPanel2Layout = new javax.swing.GroupLayout(jActionPanel2);
        jActionPanel2.setLayout(jActionPanel2Layout);
        jActionPanel2Layout.setHorizontalGroup(
            jActionPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jActionPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPersonAddBtn)
                .addGap(10, 10, 10)
                .addComponent(jPersonUpdateBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPersonDeleteBtn)
                .addGap(16, 16, 16))
        );
        jActionPanel2Layout.setVerticalGroup(
            jActionPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jActionPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jActionPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPersonUpdateBtn)
                    .addComponent(jPersonDeleteBtn)
                    .addComponent(jPersonAddBtn))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jAdvancedSearchPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Advanced Search"));
        jAdvancedSearchPanel3.setPreferredSize(new java.awt.Dimension(492, 100));

        jPersonAdvSearchOption.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Select --" }));
        jPersonAdvSearchOption.setMaximumSize(new java.awt.Dimension(91, 22));
        jPersonAdvSearchOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPersonAdvSearchOptionActionPerformed(evt);
            }
        });

        jPersonAdvSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPersonAdvSearchActionPerformed(evt);
            }
        });

        jPersonAdvSearchBtn.setText("Search");
        jPersonAdvSearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPersonAdvSearchBtnActionPerformed(evt);
            }
        });

        jPersonResetBtn.setText("Reset");
        jPersonResetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPersonResetBtnActionPerformed(evt);
            }
        });

        jPersonAdvSearchValidation.setText("   ");

        javax.swing.GroupLayout jAdvancedSearchPanel3Layout = new javax.swing.GroupLayout(jAdvancedSearchPanel3);
        jAdvancedSearchPanel3.setLayout(jAdvancedSearchPanel3Layout);
        jAdvancedSearchPanel3Layout.setHorizontalGroup(
            jAdvancedSearchPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jAdvancedSearchPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPersonAdvSearchOption, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jAdvancedSearchPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPersonAdvSearchValidation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPersonAdvSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addComponent(jPersonAdvSearchBtn)
                .addGap(10, 10, 10)
                .addComponent(jPersonResetBtn)
                .addGap(20, 20, 20))
        );
        jAdvancedSearchPanel3Layout.setVerticalGroup(
            jAdvancedSearchPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jAdvancedSearchPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jAdvancedSearchPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPersonAdvSearchOption, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPersonAdvSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPersonAdvSearchBtn)
                    .addComponent(jPersonResetBtn))
                .addGap(5, 5, 5)
                .addComponent(jPersonAdvSearchValidation)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jManagePeoplePanelLayout = new javax.swing.GroupLayout(jManagePeoplePanel);
        jManagePeoplePanel.setLayout(jManagePeoplePanelLayout);
        jManagePeoplePanelLayout.setHorizontalGroup(
            jManagePeoplePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jManagePeoplePanelLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jManagePeoplePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jAdvancedSearchPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jManagePeoplePanelLayout.createSequentialGroup()
                        .addComponent(jPInfoPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jManagePeoplePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jManagePeoplePanelLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jPInfoPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jManagePeoplePanelLayout.createSequentialGroup()
                                .addGap(121, 121, 121)
                                .addComponent(jActionPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jManagePeoplePanelLayout.setVerticalGroup(
            jManagePeoplePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jManagePeoplePanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jAdvancedSearchPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jManagePeoplePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPInfoPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jManagePeoplePanelLayout.createSequentialGroup()
                        .addComponent(jPInfoPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jActionPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(280, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Manage People", jManagePeoplePanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(461, 461, 461)
                .addComponent(jTitle)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jAdvSearchOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAdvSearchOptionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jAdvSearchOptionActionPerformed

    private void jAdvSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAdvSearchActionPerformed
        // TODO add your handling code here:
        //Validation based on selected dropdown value
    }//GEN-LAST:event_jAdvSearchActionPerformed

    private void jAdvSearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAdvSearchBtnActionPerformed
        // TODO add your handling code here:
        String searchType = jAdvSearchOption.getSelectedItem().toString();
        String searchValue = jAdvSearch.getText(); //city,community,hospital
        //Validation
        if(!validateSearchOption(searchType, searchValue)){
            return;
        }
        if(searchType.equals("-- Select --")){
            JOptionPane.showMessageDialog(this, "Please select valid search option");
            return;
        }
        //Initialize Table 
        DefaultTableModel dtmodel = (DefaultTableModel) jDoctorTable.getModel();
        dtmodel.setRowCount(0);
        //Populate rowData
        ArrayList<Doctor> tempList = doctorCatalogue.searchByOption(searchType, searchValue, doctorCatalogue.getDoctorList()); //model-class call
        if(tempList.isEmpty()){
            JOptionPane.showMessageDialog(this, "No data found!");
//            this.populateEmployeeTable();  
        } else {
            //Iterate and show data
            for(Doctor doc: tempList){
                Object[] obj = new Object[7];
                obj[0] = doc;
                obj[1] = doc.getName();
                obj[2] = doc.getCity();
                obj[3] = doc.getCommunity();
                obj[4] = doc.getHospital();
                obj[5] = doc.getPhoneNumber();
                obj[6] = doc.getEmail();
                dtmodel.addRow(obj);
            }
            JOptionPane.showMessageDialog(this, "Found " + tempList.size() + " entries!");
        }  
    }//GEN-LAST:event_jAdvSearchBtnActionPerformed

    private boolean validateSearchOption(String searchType, String searchValue){
        if(searchType.equalsIgnoreCase("ID") && !validateDoctorId(searchValue)){
            jAdvSearchValidation.setText("Invalid Doctor ID");
            jAdvSearchValidation.setForeground(Color.red);
            return false;
        } else if(searchType.equalsIgnoreCase("City") && !validateDoctorCity(searchValue)){
            jAdvSearchValidation.setText("Invalid City");
            jAdvSearchValidation.setForeground(Color.red);
            return false;
        } else if(searchType.equalsIgnoreCase("Community") && !validateDoctorCommunity(searchValue)){
            jAdvSearchValidation.setText("Invalid Community");
            jAdvSearchValidation.setForeground(Color.red);
            return false;
        } else if(searchType.equalsIgnoreCase("Hospital") && !validateDoctorHospital(searchValue)){
            jAdvSearchValidation.setText("Invalid Hospital");
            jAdvSearchValidation.setForeground(Color.red);
            return false;
        } else {
            jAdvSearchValidation.setText(" ");
            return true;
        }
    }
    
    private boolean validatePatientSearchOption(String searchType, String searchValue){
        if(searchType.equalsIgnoreCase("ID") && !validateDoctorId(searchValue)){
            jPatAdvSearchValidation.setText("Invalid Patient ID");
            jPatAdvSearchValidation.setForeground(Color.red);
            return false;
        } else if(searchType.equalsIgnoreCase("City") && !validateDoctorCity(searchValue)){
            jPatAdvSearchValidation.setText("Invalid City");
            jPatAdvSearchValidation.setForeground(Color.red);
            return false;
        } else if(searchType.equalsIgnoreCase("Community") && !validateDoctorCommunity(searchValue)){
            jPatAdvSearchValidation.setText("Invalid Community");
            jPatAdvSearchValidation.setForeground(Color.red);
            return false;
        } else if(searchType.equalsIgnoreCase("Hospital") && !validateDoctorHospital(searchValue)){
            jPatAdvSearchValidation.setText("Invalid Hospital");
            jPatAdvSearchValidation.setForeground(Color.red);
            return false;
        } else if(searchType.equalsIgnoreCase("Doctor") && !validateDoctorHospital(searchValue)){
            jPatAdvSearchValidation.setText("Invalid Doctor Name");
            jPatAdvSearchValidation.setForeground(Color.red);
            return false;
        } else {
            jPatAdvSearchValidation.setText(" ");
            return true;
        }
    }
    
    private boolean validatePersonSearchOption(String searchType, String searchValue){
        if(searchType.equalsIgnoreCase("ID") && !validatePersonId(searchValue)){
            jPersonAdvSearchValidation.setText("Invalid Person ID");
            jPersonAdvSearchValidation.setForeground(Color.red);
            return false;
        } else if(searchType.equalsIgnoreCase("City") && !validatePersonCity(searchValue)){
            jPersonAdvSearchValidation.setText("Invalid City");
            jPersonAdvSearchValidation.setForeground(Color.red);
            return false;
        } else if(searchType.equalsIgnoreCase("Community") && !validatePersonCommunity(searchValue)){
            jPersonAdvSearchValidation.setText("Invalid Community");
            jPersonAdvSearchValidation.setForeground(Color.red);
            return false;
        } else {
            jPersonAdvSearchValidation.setText(" ");
            return true;
        }
    }
    
    private boolean validateDoctorId(String validDocId){
        Pattern pattern = Pattern.compile("[1-9]{1}[0-9]{3}");
        Matcher matcher = pattern.matcher(validDocId);
        return matcher.matches();
    }

    private boolean validatePersonId(String validPersonId){
        Pattern pattern = Pattern.compile("[1-9]{1}[0-9]{3}");
        Matcher matcher = pattern.matcher(validPersonId);
        return matcher.matches();
    }
    
    private boolean validateDoctorCity(String validDocCity){
        Pattern pattern = Pattern.compile("[A-Za-z ]+");
        Matcher matcher = pattern.matcher(validDocCity);
        return matcher.matches();
    }
    
    private boolean validatePersonCity(String validPersonCity){
        Pattern pattern = Pattern.compile("[A-Za-z ]+");
        Matcher matcher = pattern.matcher(validPersonCity);
        return matcher.matches();
    }
    
    private boolean validateDoctorCommunity(String validDocCommunity){
        Pattern pattern = Pattern.compile("[A-Za-z ]+");
        Matcher matcher = pattern.matcher(validDocCommunity);
        return matcher.matches();
    }
    
    private boolean validatePersonCommunity(String validPersonCommunity){
        Pattern pattern = Pattern.compile("[A-Za-z ]+");
        Matcher matcher = pattern.matcher(validPersonCommunity);
        return matcher.matches();
    }
    
    private boolean validateDoctorHospital(String validDocHospital){
        Pattern pattern = Pattern.compile("[A-Za-z ]+");
        Matcher matcher = pattern.matcher(validDocHospital);
        return matcher.matches();
    }
    
    private void jResetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jResetBtnActionPerformed
        // Toggle buttons
        jAddBtn.setEnabled(true);
        jUpdateBtn.setEnabled(false);
        jDeleteBtn.setEnabled(false);
        clearFields();
        populateDoctorTable();
        jAdvSearchOption.setSelectedIndex(0);
        jAdvSearch.setText("");
    }//GEN-LAST:event_jResetBtnActionPerformed

    private void jUpdateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jUpdateBtnActionPerformed

        int selectedRow = jDoctorTable.getSelectedRow();      
        if(selectedRow<0 && jDoctorID.getText().isBlank()){
            JOptionPane.showMessageDialog(this, "Please select any row to update");
            return;
        }  
        
        //valildations
        boolean checkIsValid = validateName() && validateAge() && validateTelNum() && validateEmail() 
                                && !validateCity() && !validateCommunity() && !validateHospital() && !validateGender();

        if(!checkIsValid){
            JOptionPane.showMessageDialog(this, "Please enter required fields!");
            return;
        }
        
        //Display selectedRow
        Doctor updatedDoctor = new Doctor();
        updatedDoctor.setDoctorId(Integer.parseInt(jDoctorID.getText()));
        updatedDoctor.setName(jDoctorName.getText());
        updatedDoctor.setAge(Integer.parseInt(jDoctorAge.getText()));
        updatedDoctor.setGender(jDoctorGender.getSelectedItem().toString());
        updatedDoctor.setCity(jDoctorCity.getSelectedItem().toString());
        updatedDoctor.setCommunity(jDoctorCommunity.getSelectedItem().toString());
        updatedDoctor.setHospital(jDoctorHospital.getSelectedItem().toString());
        updatedDoctor.setPhoneNumber(jDoctorTelNum.getText());
        updatedDoctor.setEmail(jDoctorEmail.getText());
        
        //update
        doctorCatalogue.updateDoctor(updatedDoctor, doctorCatalogue.getDoctorList());
        JOptionPane.showMessageDialog(this, "Doctor profile updated!");
        this.populateDoctorTable();   
       
        //Clear deleted data
        clearFields();
        
        //refresh doctorId
        jDoctorID.setText(String.valueOf(generateDoctorId()));
        
        //Toggle buttons
        jAddBtn.setEnabled(true);
        jUpdateBtn.setEnabled(false);
        jDeleteBtn.setEnabled(false);
    }//GEN-LAST:event_jUpdateBtnActionPerformed

    private void jDeleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDeleteBtnActionPerformed
        // TODO add your handling code here:
        int selectedRow = jDoctorTable.getSelectedRow();      
        if(selectedRow<0){
            JOptionPane.showMessageDialog(this, "Please select any row to delete");
            return;
        }  
        DefaultTableModel dtmodel = (DefaultTableModel) jDoctorTable.getModel();
        Doctor selectedRowObj = (Doctor) dtmodel.getValueAt(selectedRow, 0);
        doctorCatalogue.removeDoctor(selectedRowObj);
        JOptionPane.showMessageDialog(this, "Doctor profile removed!");
        
        //refresh table data
        this.populateDoctorTable();  
        
        //Clear deleted data
        clearFields();
        
        //refresh doctorId
        jDoctorID.setText(String.valueOf(generateDoctorId()));
        
        //Toggle buttons
        jAddBtn.setEnabled(true);
        jUpdateBtn.setEnabled(false);
        jDeleteBtn.setEnabled(false);
    }//GEN-LAST:event_jDeleteBtnActionPerformed

    private void jEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jEmailKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jEmailKeyReleased

    private void jTelNumKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTelNumKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTelNumKeyReleased

    private boolean validateTelNum(){
        boolean isValid;
        String validTelNum = jDoctorTelNum.getText();
        Pattern pattern = Pattern.compile("[1-9]{1}[0-9]{9}");
        Matcher matcher = pattern.matcher(validTelNum);
        isValid = matcher.matches();
        return isValid;
    }
    
    private boolean validatePatientTelNum(){
        boolean isValid;
        String validTelNum = jPatientTelNum.getText();
        Pattern pattern = Pattern.compile("[1-9]{1}[0-9]{9}");
        Matcher matcher = pattern.matcher(validTelNum);
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
    
    private void jDoctorAgeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDoctorAgeKeyReleased
        // TODO add your handling code here:  
        if(!validateAge()){
            jDocAgeValidation.setText("Minimum Age 25 Required");
            jDocAgeValidation.setForeground(Color.red);
        } else {
            jDocAgeValidation.setText(" ");
        }  
    }//GEN-LAST:event_jDoctorAgeKeyReleased

    
    private boolean validateAge(){
        boolean isValid;
        String validAge = jDoctorAge.getText();
        Pattern pattern = Pattern.compile("[1-9]{1}[0-9]{0,1}"); //Only accept > than 25
        Matcher matcher = pattern.matcher(validAge);
        isValid = matcher.matches();
        if(isValid && Integer.parseInt(validAge) >= 25) {
            return true;
        }
        return false;
    }
    
    private boolean validatePatientAge(){
        boolean isValid;
        String validAge = jPatientAge.getText();
        Pattern pattern = Pattern.compile("[1-9]{1}[0-9]{0,1}"); //Only accept > than 1
        Matcher matcher = pattern.matcher(validAge);
        isValid = matcher.matches();
        if(isValid && Integer.parseInt(validAge) >= 1) {
            return true;
        }
        return false;
    }
    
    private boolean validatePersonAge(){
        boolean isValid;
        String validAge = jPersonAge.getText();
        Pattern pattern = Pattern.compile("[1-9]{1}[0-9]{0,1}"); //Only accept > than 1
        Matcher matcher = pattern.matcher(validAge);
        isValid = matcher.matches();
        if(isValid && Integer.parseInt(validAge) >= 1) {
            return true;
        }
        return false;
    }
    
    
    private void jDoctorGenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDoctorGenderActionPerformed
        // TODO add your handling code here:
       if(validateGender()){
            jDocGenderValidation.setText("Gender Required");
            jDocGenderValidation.setForeground(Color.red);
        } else {
            jDocGenderValidation.setText(" ");
        }
    }//GEN-LAST:event_jDoctorGenderActionPerformed

    private boolean validateGender(){
        String selectedGender = jDoctorGender.getSelectedItem().toString();
        return selectedGender.equals("-- Select --");
    }

    
    private void jDoctorNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDoctorNameKeyReleased
        // TODO add your handling code here:
       if(!validateName()){
            jDocNameValidation.setText("Invalid Name");
            jDocNameValidation.setForeground(Color.red);
        } else {
            jDocNameValidation.setText(" ");
        } 
    }//GEN-LAST:event_jDoctorNameKeyReleased

    private boolean validateName(){
        boolean isValid;
        String validName = jDoctorName.getText();
        Pattern pattern = Pattern.compile("[A-Za-z ]+");
        Matcher matcher = pattern.matcher(validName);
        isValid = matcher.matches();
        return isValid;
    }
    
    private boolean validatePersonName(){
        boolean isValid;
        String validName = jPersonName.getText();
        Pattern pattern = Pattern.compile("[A-Za-z ]+");
        Matcher matcher = pattern.matcher(validName);
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
    
    
    private void jAddBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAddBtnActionPerformed
        // TODO add your handling code here:
        //check validations
        boolean checkIsValid = validateName() && validateAge() && validateTelNum() && validateEmail() 
                                && !validateCity() && !validateCommunity() && !validateHospital() && !validateGender();
        if(!checkIsValid){
            JOptionPane.showMessageDialog(this, "Please provide the required details!");
            return;
        }
            
        //validations passed
        Doctor doctor = new Doctor();
        //Generate DoctorId
        doctor.setDoctorId(generateDoctorId());
        //Set variables
        doctor.setName(jDoctorName.getText());
        doctor.setAge(Integer.parseInt(jDoctorAge.getText()));
        doctor.setGender(jDoctorGender.getSelectedItem().toString());
        doctor.setCity(jDoctorCity.getSelectedItem().toString());
        doctor.setCommunity(jDoctorCommunity.getSelectedItem().toString());
        doctor.setHospital(jDoctorHospital.getSelectedItem().toString());
        doctor.setPhoneNumber(jDoctorTelNum.getText());
        doctor.setEmail(jDoctorEmail.getText());
        
        //Add to list
        doctorCatalogue.addDoctor(doctor);
        JOptionPane.showMessageDialog(this, "Doctor profile " + "with docId - " + doctorId + " saved successfully!");
        
        //Clear fields after save
        clearFields();
        
        //refresh table after save
        populateDoctorTable();
    }//GEN-LAST:event_jAddBtnActionPerformed

    private boolean validateEmail(){
        boolean isValid;
        String validEmail = jDoctorEmail.getText();
        Pattern pattern = Pattern.compile("[A-Za-z0-9]+[.]{0,1}[A-Za-z0-9]+[@](gmail.com|northeastern.edu)");
        Matcher matcher = pattern.matcher(validEmail);
        isValid = matcher.matches();
        return isValid;
    }
    
    private boolean validatePatientEmail(){
        boolean isValid;
        String validEmail = jPatientEmail.getText();
        Pattern pattern = Pattern.compile("[A-Za-z0-9]+[.]{0,1}[A-Za-z0-9]+[@](gmail.com|northeastern.edu)");
        Matcher matcher = pattern.matcher(validEmail);
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
    
    private void jDoctorCommunityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDoctorCommunityActionPerformed
        // TODO add your handling code here:
        //Validation
        if(validateCommunity()){
            jDocCommunityValidation.setText("Community Required");
            jDocCommunityValidation.setForeground(Color.red);
        } else {
            jDocCommunityValidation.setText(" ");
        }
        
        jDoctorHospital.setEnabled(true);
        //Populate Hospital dynamically
        ArrayList<String> filteredHospitalList = new ArrayList<>();
        filteredHospitalList.add("-- Select --");
        if(!jDoctorCommunity.getSelectedItem().toString().equals("-- Select --")){
            hospitalCatalogue.getHospitalList().stream()
                .filter(hosp -> hosp.getCommunity().equals(jDoctorCommunity.getSelectedItem().toString()))
                .forEach(action -> filteredHospitalList.add(action.getHospitalName()));
            jDoctorHospital.setModel(new DefaultComboBoxModel<>(filteredHospitalList.toArray(new String[0])));
        }
    }//GEN-LAST:event_jDoctorCommunityActionPerformed

    private boolean validateCommunity(){
        String selectedGender = jDoctorCommunity.getSelectedItem().toString();
        return selectedGender.equals("-- Select --");
    }
    
    private boolean validatePatientCommunity(){
        String selectedGender = jPatientCommunity.getSelectedItem().toString();
        return selectedGender.equals("-- Select --");
    }
    
    private boolean validatePersonCommunity(){
        String selectedGender = jPersonCommunity.getSelectedItem().toString();
        return selectedGender.equals("-- Select --");
    }
    
    private void jDoctorCityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDoctorCityActionPerformed
        // TODO add your handling code here:
        //Validation
        if(validateCity()){
            jDocCityValidation.setText("City Required");
            jDocCityValidation.setForeground(Color.red);
        } else {
            jDocCityValidation.setText(" ");
        } 
        
        //Populate Community & Hospital dynamically
        ArrayList<String> filteredCommunityList = new ArrayList<>();
        filteredCommunityList.add("-- Select --");
        if(!jDoctorCity.getSelectedItem().toString().equals("-- Select --")){
            hospitalCatalogue.getHospitalList().stream()
                .filter(hosp -> hosp.getCity().equals(jDoctorCity.getSelectedItem().toString()))
                .forEach(action -> filteredCommunityList.add(action.getCommunity()));
            jDoctorCommunity.setModel(new DefaultComboBoxModel<>(filteredCommunityList.toArray(new String[0]))); 
        }
        
        ArrayList<String> filteredHospitalList = new ArrayList<>();
        filteredHospitalList.add("-- Select --");
        if(!jDoctorCity.getSelectedItem().toString().equals("-- Select --")){
            hospitalCatalogue.getHospitalList().stream()
                .filter(hosp -> hosp.getCity().equals(jDoctorCity.getSelectedItem().toString()))
                .forEach(action -> filteredHospitalList.add(action.getHospitalName()));
            jDoctorHospital.setModel(new DefaultComboBoxModel<>(filteredHospitalList.toArray(new String[0])));
        }
        jDoctorHospital.setEnabled(false);
    }//GEN-LAST:event_jDoctorCityActionPerformed

    private boolean validateCity(){
        String selectedGender = jDoctorCity.getSelectedItem().toString();
        return selectedGender.equals("-- Select --");
    }
    
    private boolean validatePatientCity(){
        String selectedGender = jPatientCity.getSelectedItem().toString();
        return selectedGender.equals("-- Select --");
    }
    
    private boolean validatePersonCity(){
        String selectedGender = jPersonCity.getSelectedItem().toString();
        return selectedGender.equals("-- Select --");
    }
    
    private void jDoctorHospitalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDoctorHospitalActionPerformed
        // TODO add your handling code here:
        if(validateHospital()){
            jDocHospitalValidation.setText("Hospital Required");
            jDocHospitalValidation.setForeground(Color.red);
        } else {
            jDocHospitalValidation.setText(" ");
        } 
    }//GEN-LAST:event_jDoctorHospitalActionPerformed

    private boolean validateHospital(){
        String selectedGender = jDoctorHospital.getSelectedItem().toString();
        return selectedGender.equals("-- Select --");
    }

    private boolean validatePatientHospital(){
        String selectedGender = jPatientHospital.getSelectedItem().toString();
        return selectedGender.equals("-- Select --");
    }
    
    private void jDoctorTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDoctorTableMouseClicked
        // disable Add button
        jAddBtn.setEnabled(false);
        jUpdateBtn.setEnabled(true);
        jDeleteBtn.setEnabled(true);
        
        //Clear previous selected data
        clearFields();
        
        //Select row data
        int selectedRow = jDoctorTable.getSelectedRow();
        DefaultTableModel dtmodel = (DefaultTableModel) jDoctorTable.getModel();
        Doctor selectedRowObj = (Doctor) dtmodel.getValueAt(selectedRow, 0);
        
        //Display rowData
        jDoctorID.setText(String.valueOf(selectedRowObj.getDoctorId()));
        jDoctorName.setText(selectedRowObj.getName());
        jDoctorAge.setText(String.valueOf(selectedRowObj.getAge()));
        jDoctorGender.setSelectedItem(selectedRowObj.getGender());
        jDoctorCity.setSelectedItem(selectedRowObj.getCity());
        jDoctorCommunity.setSelectedItem(selectedRowObj.getCommunity());
        jDoctorHospital.setSelectedItem(selectedRowObj.getHospital());
        jDoctorTelNum.setText(selectedRowObj.getPhoneNumber());
        jDoctorEmail.setText(selectedRowObj.getEmail()); 
    }//GEN-LAST:event_jDoctorTableMouseClicked

    private void jDoctorEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDoctorEmailKeyReleased
        // TODO add your handling code here:
        if(!validateEmail()){
            jDocEmailValidation.setText("Invalid Email");
            jDocEmailValidation.setForeground(Color.red);
        } else {
            jDocEmailValidation.setText(" ");
        }
    }//GEN-LAST:event_jDoctorEmailKeyReleased

    private void jDoctorTelNumKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDoctorTelNumKeyReleased
        // TODO add your handling code here:
        if(!validateTelNum()){
            jDocTelNumValidation.setText("Invalid Phone Number");
            jDocTelNumValidation.setForeground(Color.red);
        } else {
            jDocTelNumValidation.setText(" ");
        }     
    }//GEN-LAST:event_jDoctorTelNumKeyReleased

    private void jPatAdvSearchOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPatAdvSearchOptionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPatAdvSearchOptionActionPerformed

    private void jPatAdvSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPatAdvSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPatAdvSearchActionPerformed

    private void jPatAdvSearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPatAdvSearchBtnActionPerformed
        // TODO add your handling code here:
        String searchType = jPatAdvSearchOption.getSelectedItem().toString();
        String searchValue = jPatAdvSearch.getText(); //Id,city,community,hospital,doctor
        //Validation
        if(!validatePatientSearchOption(searchType, searchValue)){
            return;
        }
        if(searchType.equals("-- Select --")){
            JOptionPane.showMessageDialog(this, "Please select valid search option");
            return;
        }
        //Initialize Table 
        DefaultTableModel dtmodel = (DefaultTableModel) jPatientTable.getModel();
        dtmodel.setRowCount(0);
        //Populate rowData
        ArrayList<Patient> tempList = patientCatalogue.searchByOption(searchType, searchValue, patientCatalogue.getPatientList()); //model-class call
        if(tempList.isEmpty()){
            JOptionPane.showMessageDialog(this, "No data found!");
//            this.populateEmployeeTable();  
        } else {
            //Iterate and show data
            for(Patient pat: tempList){
                Object[] obj = new Object[7];
                obj[0] = pat;
                obj[1] = pat.getPatientName();
                obj[2] = pat.getPatientAge();
                obj[3] = pat.getCity();
                obj[4] = pat.getCommunity();
                obj[4] = pat.getHospital();
                obj[5] = pat.getPatientTelNum();
                obj[6] = pat.getConsultedDoctor();
                dtmodel.addRow(obj);
            }
            JOptionPane.showMessageDialog(this, "Found " + tempList.size() + " entries!");
        }
    }//GEN-LAST:event_jPatAdvSearchBtnActionPerformed

    private void jPatResetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPatResetBtnActionPerformed

        // Toggle buttons
        jPatAddBtn.setEnabled(true);
        jPatUpdateBtn.setEnabled(false);
        jPatDeleteBtn.setEnabled(false);
        jConsultedDoctor.setEnabled(false);
        clearPatientFields();
        populatePatientTable();
        jPatAdvSearchOption.setSelectedIndex(0);
        jPatAdvSearch.setText("");
    }//GEN-LAST:event_jPatResetBtnActionPerformed

    private void jPatientTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPatientTableMouseClicked
        // TODO add your handling code here:
         // disable Add button
        jPatAddBtn.setEnabled(false);
        jPatUpdateBtn.setEnabled(true);
        jPatDeleteBtn.setEnabled(true);
        
        //Clear previous selected data
        clearPatientFields();
        
        //Select row data
        int selectedRow = jPatientTable.getSelectedRow();
        DefaultTableModel dtmodel = (DefaultTableModel) jPatientTable.getModel();
        Patient selectedRowObj = (Patient) dtmodel.getValueAt(selectedRow, 0);
        
        //Display Patient rowData
        jPatientID.setText(String.valueOf(selectedRowObj.getPatientId()));
        jPatientName.setText(selectedRowObj.getPatientName());
        jPatientAge.setText(String.valueOf(selectedRowObj.getPatientAge()));
        jPatientCity.setSelectedItem(selectedRowObj.getCity());
        jPatientCommunity.setSelectedItem(selectedRowObj.getCommunity());
        jPatientHospital.setSelectedItem(selectedRowObj.getHospital());
        jPatientTelNum.setText(selectedRowObj.getPatientTelNum());
        jPatientEmail.setText(selectedRowObj.getPatientEmail());
        
        //Display corresponding Doctor rowData
        if(!jConsultedDoctor.getSelectedItem().toString().equals("-- Select --")){
            jDocID.setText(String.valueOf(findDoctorId(selectedRowObj.getConsultedDoctor())));
        }
        jConsultedDoctor.setSelectedItem(selectedRowObj.getConsultedDoctor());
    }//GEN-LAST:event_jPatientTableMouseClicked

    private int findDoctorId(String docName){
//        if(!docName.equals("-- Select --")){
        ArrayList<String> doctorID = new ArrayList<>();
        this.doctorCatalogue.getDoctorList().stream()
                .filter(doc -> doc.getName().equals(docName))
                .forEach(action-> doctorID.add(0, String.valueOf(action.getDoctorId()))); //ERRRORRRRR
        return doctorID.isEmpty()?0:Integer.parseInt(doctorID.get(0));
    }
    
    private void jPatientEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPatientEmailKeyReleased
        // TODO add your handling code here:
        if(!validatePatientEmail()){
            jPatEmailValidation.setText("Invalid Email");
            jPatEmailValidation.setForeground(Color.red);
        } else {
            jPatEmailValidation.setText(" ");
        }
        
    }//GEN-LAST:event_jPatientEmailKeyReleased

    private void jPatientTelNumKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPatientTelNumKeyReleased
        // TODO add your handling code here:
         if(!validatePatientTelNum()){
            jPatTelNumValidation.setText("Invalid Phone Number");
            jPatTelNumValidation.setForeground(Color.red);
        } else {
            jPatTelNumValidation.setText(" ");
        }
    }//GEN-LAST:event_jPatientTelNumKeyReleased

    private void jPatientNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPatientNameKeyReleased
        // TODO add your handling code here:
        if(!validatePatientName()){
            jPatNameValidation.setText("Invalid Name");
            jPatNameValidation.setForeground(Color.red);
        } else {
            jPatNameValidation.setText(" ");
        }
    }//GEN-LAST:event_jPatientNameKeyReleased

    private boolean validatePatientName(){
        boolean isValid;
        String validName = jPatientName.getText();
        Pattern pattern = Pattern.compile("[A-Za-z ]+");
        Matcher matcher = pattern.matcher(validName);
        isValid = matcher.matches();
        return isValid;
    }

    
    private void jPatientAgeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPatientAgeKeyReleased
        // TODO add your handling code here:
        if(!validatePatientAge()){
            jPatAgeValidation.setText("Invalid Age");
            jPatAgeValidation.setForeground(Color.red);
        } else {
            jPatAgeValidation.setText(" ");
        }  
    }//GEN-LAST:event_jPatientAgeKeyReleased

    private void jPatientCommunityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPatientCommunityActionPerformed
        // TODO add your handling code here:
        if(validatePatientCommunity()){
            jPatCommunityValidation.setText("Community Required");
            jPatCommunityValidation.setForeground(Color.red);
        } else {
            jPatCommunityValidation.setText(" ");
        }
        
        ArrayList<String> filteredHospitalList = new ArrayList<>();
        filteredHospitalList.add("-- Select --");
        if(!jPatientCommunity.getSelectedItem().toString().equals("-- Select --")){
            hospitalCatalogue.getHospitalList().stream()
                .filter(hosp -> hosp.getCommunity().equals(jPatientCommunity.getSelectedItem().toString()))
                .forEach(action -> filteredHospitalList.add(action.getHospitalName()));
            jPatientHospital.setModel(new DefaultComboBoxModel<>(filteredHospitalList.toArray(new String[0])));
        }
        jPatientHospital.setEnabled(true);
        ArrayList<String> emptyDoctorList = new ArrayList<>();
        emptyDoctorList.add("-- Select --");
        jConsultedDoctor.setModel(new DefaultComboBoxModel<>(emptyDoctorList.toArray(new String[0])));
        jDocID.setText(" ");
    }//GEN-LAST:event_jPatientCommunityActionPerformed

    private void jPatientCityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPatientCityActionPerformed
        // TODO add your handling code here:
        if(validatePatientCity()){
            jPatCityValidation.setText("City Required");
            jPatCityValidation.setForeground(Color.red);
        } else {
            jPatCityValidation.setText(" ");
        }
        
        //Populate Community & Hospital dynamically
        ArrayList<String> filteredCommunityList = new ArrayList<>();
        filteredCommunityList.add("-- Select --");
        if(!jPatientCity.getSelectedItem().toString().equals("-- Select --")){
            hospitalCatalogue.getHospitalList().stream()
                .filter(hosp -> hosp.getCity().equals(jPatientCity.getSelectedItem().toString()))
                .forEach(action -> filteredCommunityList.add(action.getCommunity()));
            jPatientCommunity.setModel(new DefaultComboBoxModel<>(filteredCommunityList.toArray(new String[0]))); 
        }
        
        ArrayList<String> filteredHospitalList = new ArrayList<>();
        filteredHospitalList.add("-- Select --");
        if(!jPatientCity.getSelectedItem().toString().equals("-- Select --")){
            hospitalCatalogue.getHospitalList().stream()
                .filter(hosp -> hosp.getCity().equals(jPatientCity.getSelectedItem().toString()))
                .forEach(action -> filteredHospitalList.add(action.getHospitalName()));
            jPatientHospital.setModel(new DefaultComboBoxModel<>(filteredHospitalList.toArray(new String[0])));
        }
        jPatientHospital.setEnabled(false);
        ArrayList<String> emptyDoctorList = new ArrayList<>();
        emptyDoctorList.add("-- Select --");
        jConsultedDoctor.setModel(new DefaultComboBoxModel<>(emptyDoctorList.toArray(new String[0])));
        jDocID.setText(" ");
    }//GEN-LAST:event_jPatientCityActionPerformed

    private void jPatientHospitalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPatientHospitalActionPerformed
        // TODO add your handling code here:
        if(validatePatientHospital()){
            jPatHospitalValidation.setText("Hospital Required");
            jPatHospitalValidation.setForeground(Color.red);
        } else {
            jPatHospitalValidation.setText(" ");
        }
        if(!jPatientHospital.getSelectedItem().toString().equals("-- Select --")){
            jConsultedDoctor.setEnabled(true);    
        }
        ArrayList<String> filteredDoctorList = new ArrayList<>();
        filteredDoctorList.add("-- Select --");
        if(!jPatientHospital.getSelectedItem().toString().equals("-- Select --")){
            doctorCatalogue.getDoctorList().stream()
                .filter(doc -> doc.getHospital().equals(jPatientHospital.getSelectedItem().toString()))
                .forEach(action -> filteredDoctorList.add(action.getName()));
            if(!filteredDoctorList.isEmpty()){
                jConsultedDoctor.setModel(new DefaultComboBoxModel<>(filteredDoctorList.toArray(new String[0])));
                jDocID.setText(String.valueOf(findDoctorId(jConsultedDoctor.getSelectedItem().toString())));
            } else {
                ArrayList<String> emptyDoctorList = new ArrayList<>();
                emptyDoctorList.add("-- Select --");
                jConsultedDoctor.setModel(new DefaultComboBoxModel<>(emptyDoctorList.toArray(new String[0])));
            }
        }
    }//GEN-LAST:event_jPatientHospitalActionPerformed

    private void jPatUpdateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPatUpdateBtnActionPerformed
        // TODO add your handling code here:
        int selectedRow = jPatientTable.getSelectedRow();      
        if(selectedRow<0){
            JOptionPane.showMessageDialog(this, "Please select any row to update");
            return;
        }  
        
        //valildations
        boolean checkIsValid = validatePatientName() && validatePatientAge() && validatePatientTelNum() && validatePatientEmail() 
                                && !validatePatientCity() && !validatePatientCommunity() && !validatePatientHospital() && !validateDocName();

        if(!checkIsValid){
            JOptionPane.showMessageDialog(this, "Please enter required fields!");
            return;
        }
        
        //Display selectedRow
        Patient updatedPatient = new Patient();
        updatedPatient.setPatientId(Integer.parseInt(jPatientID.getText()));
        updatedPatient.setPatientName(jPatientName.getText());
        updatedPatient.setPatientAge(Integer.parseInt(jPatientAge.getText()));
        updatedPatient.setCity(jPatientCity.getSelectedItem().toString());
        updatedPatient.setCommunity(jPatientCommunity.getSelectedItem().toString());
        updatedPatient.setHospital(jPatientHospital.getSelectedItem().toString());
        updatedPatient.setPatientTelNum(jPatientTelNum.getText());
        updatedPatient.setPatientEmail(jPatientEmail.getText());
        updatedPatient.setConsultedDoctor(jConsultedDoctor.getSelectedItem().toString());
        
        //update
        patientCatalogue.updatePatient(updatedPatient, patientCatalogue.getPatientList());
        JOptionPane.showMessageDialog(this, "Patient profile updated!");
        this.populatePatientTable();   
       
        //Clear deleted data
        clearPatientFields();
        
        //refresh doctorId
        jPatientID.setText(String.valueOf(generatePatientId()));
        
        //Toggle buttons
        jPatAddBtn.setEnabled(true);
        jPatUpdateBtn.setEnabled(false);
        jPatDeleteBtn.setEnabled(false);
        jConsultedDoctor.setEnabled(false);
    }//GEN-LAST:event_jPatUpdateBtnActionPerformed

    private void jPatDeleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPatDeleteBtnActionPerformed
        // TODO add your handling code here:
        int selectedRow = jPatientTable.getSelectedRow();      
        if(selectedRow<0){
            JOptionPane.showMessageDialog(this, "Please select any row to delete");
            return;
        }  
        DefaultTableModel dtmodel = (DefaultTableModel) jPatientTable.getModel();
        Patient selectedRowObj = (Patient) dtmodel.getValueAt(selectedRow, 0);
        patientCatalogue.removePatient(selectedRowObj);
        JOptionPane.showMessageDialog(this, "Patient profile removed!");
        
        //refresh table data
        this.populatePatientTable();  
        
        //Clear deleted data
        clearPatientFields();
        
        //refresh doctorId
        jPatientID.setText(String.valueOf(generatePatientId()));
        
        //Toggle buttons
        jPatAddBtn.setEnabled(true);
        jPatUpdateBtn.setEnabled(false);
        jPatDeleteBtn.setEnabled(false);
        jConsultedDoctor.setEnabled(false);
    }//GEN-LAST:event_jPatDeleteBtnActionPerformed

    private void jDocIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDocIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDocIDActionPerformed

    private void jPatAddBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPatAddBtnActionPerformed
        // TODO add your handling code here:
        //check validations
        boolean checkIsValid = validatePatientName() && validatePatientAge() && validatePatientTelNum() && validatePatientEmail() 
                                && !validatePatientCity() && !validatePatientCommunity() && !validatePatientHospital() && !validateDocName();
        if(!checkIsValid){
            JOptionPane.showMessageDialog(this, "Please provide the required details!");
            return;
        }
            
        //validations passed
        Patient patient = new Patient();
        //Generate DoctorId
        patient.setPatientId(generatePatientId());
        //Set variables
        patient.setPatientName(jPatientName.getText());
        patient.setPatientAge(Integer.parseInt(jPatientAge.getText()));
        patient.setCity(jPatientCity.getSelectedItem().toString());
        patient.setCommunity(jPatientCommunity.getSelectedItem().toString());
        patient.setHospital(jPatientHospital.getSelectedItem().toString());
        patient.setPatientTelNum(jPatientTelNum.getText());
        patient.setPatientEmail(jPatientEmail.getText());
        patient.setConsultedDoctor(jConsultedDoctor.getSelectedItem().toString());
        
        //Add to list
        patientCatalogue.addPatient(patient);
        JOptionPane.showMessageDialog(this, "Patient profile " + "with patientId - " + patientId + " saved successfully!");
        
        //Clear fields after save
        clearPatientFields();
        
        //refresh table after save
        populatePatientTable();
        
        jConsultedDoctor.setEnabled(false);
    }//GEN-LAST:event_jPatAddBtnActionPerformed

    private void jConsultedDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jConsultedDoctorActionPerformed
        // TODO add your handling code here:
        if(validateDocName()){
            jPatDocNameValidation.setText("Invalid Doctor");
            jPatDocNameValidation.setForeground(Color.red);
        } else {
            jPatDocNameValidation.setText(" ");
        }
        jDocID.setText(String.valueOf(findDoctorId(jConsultedDoctor.getSelectedItem().toString())));
        
    }//GEN-LAST:event_jConsultedDoctorActionPerformed

    private void jPersonTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPersonTableMouseClicked
        // TODO add your handling code here:
         // disable Add button
        jPersonAddBtn.setEnabled(false);
        jPersonUpdateBtn.setEnabled(true);
        jPersonDeleteBtn.setEnabled(true);
        
        //Clear previous selected data
        clearPersonFields();
        
        //Select row data
        int selectedRow = jPersonTable.getSelectedRow();
        DefaultTableModel dtmodel = (DefaultTableModel) jPersonTable.getModel();
        Person selectedRowObj = (Person) dtmodel.getValueAt(selectedRow, 0);
        
        //Display Person rowData
        jPersonId.setText(String.valueOf(selectedRowObj.getPersonId()));
        jPersonName.setText(selectedRowObj.getPersonName());
        jPersonAge.setText(String.valueOf(selectedRowObj.getPersonAge()));
        jPersonCity.setSelectedItem(selectedRowObj.getCity());
        jPersonCommunity.setSelectedItem(selectedRowObj.getCommunity());
        jPersonAddress.setText(selectedRowObj.getPersonAddress());
        jPersonTelNum.setText(selectedRowObj.getPersonTelNum());
        jPersonEmail.setText(selectedRowObj.getPersonEmail());
        
    }//GEN-LAST:event_jPersonTableMouseClicked

    private void jPersonNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPersonNameKeyReleased
        // TODO add your handling code here:
        if(!validatePersonName()){
            jPersonNameValidation.setText("Invalid Name");
            jPersonNameValidation.setForeground(Color.red);
        } else {
            jPersonNameValidation.setText(" ");
        }
    }//GEN-LAST:event_jPersonNameKeyReleased

    private void jPersonAgeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPersonAgeKeyReleased
        // TODO add your handling code here:
        if(!validatePersonAge()){
            jPersonAgeValidation.setText("Invalid Age");
            jPersonAgeValidation.setForeground(Color.red);
        } else {
            jPersonAgeValidation.setText(" ");
        } 
    }//GEN-LAST:event_jPersonAgeKeyReleased

    private void jPersonCommunityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPersonCommunityActionPerformed
        // TODO add your handling code here:
        if(validatePersonCommunity()){
            jPersonCommunityValidation.setText("Community Required");
            jPersonCommunityValidation.setForeground(Color.red);
        } else {
            jPersonCommunityValidation.setText(" ");
        }
    }//GEN-LAST:event_jPersonCommunityActionPerformed

    private void jPersonCityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPersonCityActionPerformed
        // TODO add your handling code here:
        if(validatePersonCity()){
            jPersonCityValidation.setText("City Required");
            jPersonCityValidation.setForeground(Color.red);
        } else {
            jPersonCityValidation.setText(" ");
        }
        
        //Populate Community dynamically
        ArrayList<String> filteredCommunityList = new ArrayList<>();
        filteredCommunityList.add("-- Select --");
        if(!jPersonCity.getSelectedItem().toString().equals("-- Select --")){
            hospitalCatalogue.getHospitalList().stream()
                .filter(hosp -> hosp.getCity().equals(jPersonCity.getSelectedItem().toString()))
                .forEach(action -> filteredCommunityList.add(action.getCommunity()));
            jPersonCommunity.setModel(new DefaultComboBoxModel<>(filteredCommunityList.toArray(new String[0]))); 
        }
    }//GEN-LAST:event_jPersonCityActionPerformed

    private void jPersonAddressKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPersonAddressKeyReleased
        // TODO add your handling code here:
        if(!validatePersonAddress()){
            jPersonAddressValidation.setText("Invalid Address");
            jPersonAddressValidation.setForeground(Color.red);
        } else {
            jPersonAddressValidation.setText(" ");
        }
    }//GEN-LAST:event_jPersonAddressKeyReleased

    private void jPersonEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPersonEmailKeyReleased
        // TODO add your handling code here:
        if(!validatePersonEmail()){
            jPersonEmailValidation.setText("Invalid Email");
            jPersonEmailValidation.setForeground(Color.red);
        } else {
            jPersonEmailValidation.setText(" ");
        }
    }//GEN-LAST:event_jPersonEmailKeyReleased

    private void jPersonTelNumKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPersonTelNumKeyReleased
        // TODO add your handling code here:
         if(!validatePersonTelNum()){
            jPersonTelNumValidation.setText("Invalid Phone Number");
            jPersonTelNumValidation.setForeground(Color.red);
        } else {
            jPersonTelNumValidation.setText(" ");
        }
    }//GEN-LAST:event_jPersonTelNumKeyReleased

    private void jPersonUpdateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPersonUpdateBtnActionPerformed
        // TODO add your handling code here:
        int selectedRow = jPersonTable.getSelectedRow();      
        if(selectedRow<0){
            JOptionPane.showMessageDialog(this, "Please select any row to update");
            return;
        }  
        
        //valildations
        boolean checkIsValid = validatePersonName() && validatePersonAge() && validatePersonTelNum() && validatePersonEmail()  && validatePersonAddress() 
                                && !validatePersonCity() && !validatePersonCommunity();

        if(!checkIsValid){
            JOptionPane.showMessageDialog(this, "Please enter required fields!");
            return;
        }
        
        //Display selectedRow
        Person updatedPerson = new Person();
        updatedPerson.setPersonId(Integer.parseInt(jPersonId.getText()));
        updatedPerson.setPersonName(jPersonName.getText());
        updatedPerson.setPersonAge(Integer.parseInt(jPersonAge.getText()));
        updatedPerson.setCity(jPersonCity.getSelectedItem().toString());
        updatedPerson.setCommunity(jPersonCommunity.getSelectedItem().toString());
        updatedPerson.setPersonAddress(jPersonAddress.getText());
        updatedPerson.setPersonTelNum(jPersonTelNum.getText());
        updatedPerson.setPersonEmail(jPersonEmail.getText());
        
        //update
        personCatalogue.updatePerson(updatedPerson, personCatalogue.getPersonList());
        JOptionPane.showMessageDialog(this, "Person profile updated!");
        this.populatePersonTable();   
       
        //Clear deleted data
        clearPersonFields();
        
        //refresh personId
        jPersonId.setText(String.valueOf(generatePersonId()));
        
        //Toggle buttons
        jPersonAddBtn.setEnabled(true);
        jPersonUpdateBtn.setEnabled(false);
        jPersonDeleteBtn.setEnabled(false);
        
    }//GEN-LAST:event_jPersonUpdateBtnActionPerformed

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
        
        //refresh doctorId
        jPersonId.setText(String.valueOf(generatePersonId()));
        
        //Toggle buttons
        jPersonAddBtn.setEnabled(true);
        jPersonUpdateBtn.setEnabled(false);
        jPersonDeleteBtn.setEnabled(false);
        
    }//GEN-LAST:event_jPersonDeleteBtnActionPerformed

    private void jPersonAddBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPersonAddBtnActionPerformed
        // TODO add your handling code here:
        //check validations
        boolean checkIsValid = validatePersonName() && validatePersonAge() && validatePersonTelNum() && validatePersonEmail()&& validatePersonAddress() 
                                && !validatePersonCity() && !validatePersonCommunity();
        if(!checkIsValid){
            JOptionPane.showMessageDialog(this, "Please provide the required details!");
            return;
        }
            
        //validations passed
        Person person = new Person();
        //Generate PersonId
        person.setPersonId(generatePersonId());
        //Set variables
        person.setPersonName(jPersonName.getText());
        person.setPersonAge(Integer.parseInt(jPersonAge.getText()));
        person.setCity(jPersonCity.getSelectedItem().toString());
        person.setCommunity(jPersonCommunity.getSelectedItem().toString());
        person.setPersonAddress(jPersonAddress.getText());
        person.setPersonTelNum(jPersonTelNum.getText());
        person.setPersonEmail(jPersonEmail.getText());
        
        //Add to list
        personCatalogue.addPerson(person);
        JOptionPane.showMessageDialog(this, "Person profile " + "with personId - " + personId + " saved successfully!");
        
        //Clear fields after save
        clearPersonFields();
        
        //refresh table after save
        populatePersonTable();
        
        
    }//GEN-LAST:event_jPersonAddBtnActionPerformed

    private void jPersonAdvSearchOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPersonAdvSearchOptionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPersonAdvSearchOptionActionPerformed

    private void jPersonAdvSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPersonAdvSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPersonAdvSearchActionPerformed

    private void jPersonAdvSearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPersonAdvSearchBtnActionPerformed
        // TODO add your handling code here:
        String searchType = jPersonAdvSearchOption.getSelectedItem().toString();
        String searchValue = jPersonAdvSearch.getText(); //Id,city,community
        //Validation
        if(!validatePersonSearchOption(searchType, searchValue)){
            return;
        }
        if(searchType.equals("-- Select --")){
            JOptionPane.showMessageDialog(this, "Please select valid search option");
            return;
        }
        //Initialize Table 
        DefaultTableModel dtmodel = (DefaultTableModel) jPersonTable.getModel();
        dtmodel.setRowCount(0);
        //Populate rowData
        ArrayList<Person> tempList = personCatalogue.searchByOption(searchType, searchValue, personCatalogue.getPersonList()); //model-class call
        if(tempList.isEmpty()){
            JOptionPane.showMessageDialog(this, "No data found!");
//            this.populateEmployeeTable();  
        } else {
            //Iterate and show data
            for(Person per: tempList){
                Object[] obj = new Object[7];
                obj[0] = per;
                obj[1] = per.getPersonName();
                obj[2] = per.getCity();
                obj[3] = per.getCommunity();
                obj[4] = per.getPersonAddress();
                obj[5] = per.getPersonTelNum();
                obj[6] = per.getPersonEmail();
                dtmodel.addRow(obj);
            }
            JOptionPane.showMessageDialog(this, "Found " + tempList.size() + " entries!");
        }
    }//GEN-LAST:event_jPersonAdvSearchBtnActionPerformed

    private void jPersonResetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPersonResetBtnActionPerformed
        // TODO add your handling code here:
        // Toggle buttons
        jPersonAddBtn.setEnabled(true);
        jPersonUpdateBtn.setEnabled(false);
        jPersonDeleteBtn.setEnabled(false);
        clearPersonFields();
        populatePersonTable();
        jPersonAdvSearchOption.setSelectedIndex(0);
        jPersonAdvSearch.setText("");
    }//GEN-LAST:event_jPersonResetBtnActionPerformed

     private boolean validateDocName(){
        String selectedGender = jConsultedDoctor.getSelectedItem().toString();
        return selectedGender.equals("-- Select --");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jActionPanel;
    private javax.swing.JPanel jActionPanel1;
    private javax.swing.JPanel jActionPanel2;
    private javax.swing.JButton jAddBtn;
    private javax.swing.JTextField jAdvSearch;
    private javax.swing.JButton jAdvSearchBtn;
    private javax.swing.JComboBox<String> jAdvSearchOption;
    private javax.swing.JLabel jAdvSearchValidation;
    private javax.swing.JPanel jAdvancedSearchPanel;
    private javax.swing.JPanel jAdvancedSearchPanel2;
    private javax.swing.JPanel jAdvancedSearchPanel3;
    private javax.swing.JComboBox<String> jConsultedDoctor;
    private javax.swing.JButton jDeleteBtn;
    private javax.swing.JLabel jDocAgeValidation;
    private javax.swing.JLabel jDocCityValidation;
    private javax.swing.JLabel jDocCommunityValidation;
    private javax.swing.JLabel jDocEmailValidation;
    private javax.swing.JLabel jDocGenderValidation;
    private javax.swing.JLabel jDocHospitalValidation;
    private javax.swing.JTextField jDocID;
    private javax.swing.JLabel jDocNameValidation;
    private javax.swing.JLabel jDocTelNumValidation;
    private javax.swing.JTextField jDoctorAge;
    private javax.swing.JComboBox<String> jDoctorCity;
    private javax.swing.JComboBox<String> jDoctorCommunity;
    private javax.swing.JTextField jDoctorEmail;
    private javax.swing.JComboBox<String> jDoctorGender;
    private javax.swing.JComboBox<String> jDoctorHospital;
    private javax.swing.JTextField jDoctorID;
    private javax.swing.JPanel jDoctorInfoPanel;
    private javax.swing.JTextField jDoctorName;
    private javax.swing.JTable jDoctorTable;
    private javax.swing.JTextField jDoctorTelNum;
    private javax.swing.JTextField jEmail;
    private javax.swing.JLabel jEmailValidation;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jManageDoctorsPanel;
    private javax.swing.JPanel jManagePatientsPanel;
    private javax.swing.JPanel jManagePeoplePanel;
    private javax.swing.JPanel jPInfoPanel;
    private javax.swing.JPanel jPInfoPanel1;
    private javax.swing.JPanel jPInfoPanel2;
    private javax.swing.JPanel jPInfoPanel3;
    private javax.swing.JPanel jPInfoPanel4;
    private javax.swing.JPanel jPInfoPanel5;
    private javax.swing.JPanel jPInfoPanel6;
    private javax.swing.JButton jPatAddBtn;
    private javax.swing.JTextField jPatAdvSearch;
    private javax.swing.JButton jPatAdvSearchBtn;
    private javax.swing.JComboBox<String> jPatAdvSearchOption;
    private javax.swing.JLabel jPatAdvSearchValidation;
    private javax.swing.JLabel jPatAgeValidation;
    private javax.swing.JLabel jPatCityValidation;
    private javax.swing.JLabel jPatCommunityValidation;
    private javax.swing.JButton jPatDeleteBtn;
    private javax.swing.JLabel jPatDocNameValidation;
    private javax.swing.JLabel jPatEmailValidation;
    private javax.swing.JLabel jPatHospitalValidation;
    private javax.swing.JLabel jPatNameValidation;
    private javax.swing.JButton jPatResetBtn;
    private javax.swing.JLabel jPatTelNumValidation;
    private javax.swing.JButton jPatUpdateBtn;
    private javax.swing.JTextField jPatientAge;
    private javax.swing.JComboBox<String> jPatientCity;
    private javax.swing.JComboBox<String> jPatientCommunity;
    private javax.swing.JTextField jPatientEmail;
    private javax.swing.JComboBox<String> jPatientHospital;
    private javax.swing.JTextField jPatientID;
    private javax.swing.JTextField jPatientName;
    private javax.swing.JTable jPatientTable;
    private javax.swing.JTextField jPatientTelNum;
    private javax.swing.JButton jPersonAddBtn;
    private javax.swing.JTextField jPersonAddress;
    private javax.swing.JLabel jPersonAddressValidation;
    private javax.swing.JTextField jPersonAdvSearch;
    private javax.swing.JButton jPersonAdvSearchBtn;
    private javax.swing.JComboBox<String> jPersonAdvSearchOption;
    private javax.swing.JLabel jPersonAdvSearchValidation;
    private javax.swing.JTextField jPersonAge;
    private javax.swing.JLabel jPersonAgeValidation;
    private javax.swing.JComboBox<String> jPersonCity;
    private javax.swing.JLabel jPersonCityValidation;
    private javax.swing.JComboBox<String> jPersonCommunity;
    private javax.swing.JLabel jPersonCommunityValidation;
    private javax.swing.JButton jPersonDeleteBtn;
    private javax.swing.JTextField jPersonEmail;
    private javax.swing.JLabel jPersonEmailValidation;
    private javax.swing.JTextField jPersonId;
    private javax.swing.JTextField jPersonName;
    private javax.swing.JLabel jPersonNameValidation;
    private javax.swing.JButton jPersonResetBtn;
    private javax.swing.JTable jPersonTable;
    private javax.swing.JTextField jPersonTelNum;
    private javax.swing.JLabel jPersonTelNumValidation;
    private javax.swing.JButton jPersonUpdateBtn;
    private javax.swing.JButton jResetBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JTextField jTelNum;
    private javax.swing.JLabel jTelNumValidation;
    private javax.swing.JLabel jTitle;
    private javax.swing.JButton jUpdateBtn;
    // End of variables declaration//GEN-END:variables
}
