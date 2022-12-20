/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.ExistingPerson;

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
import model.Hospital;
import model.HospitalCatalogue;
import model.Patient;
import model.PatientCatalogue;
import model.Person;
import model.PersonCatalogue;

/**
 *
 * @author Avinash Reddy
 */
public class ExistingPersonBaseJPanel extends javax.swing.JPanel {

    /**
     * Creates new form PersonBaseJPanel
     */
    private HospitalCatalogue hospitalCatalogue;
    private DoctorCatalogue doctorCatalogue;
    private PersonCatalogue personCatalogue;
    private PatientCatalogue patientCatalogue;
    
    private int personId = 1000; //Starting perId
    private int defaultId = 1000;
    
    public ExistingPersonBaseJPanel(HospitalCatalogue hospitalCatalogue, DoctorCatalogue doctorCatalogue,PersonCatalogue personCatalogue, PatientCatalogue patientCatalogue) {
        initComponents();
        this.hospitalCatalogue = hospitalCatalogue;
        this.doctorCatalogue = doctorCatalogue;
        this.personCatalogue = personCatalogue;
        this.patientCatalogue = patientCatalogue;
        
       //Populate View Tables - Hospital
        populateDoctorTableAfterReset();
        
        //Person ID
        jPersonId.setEnabled(false); //non-editable 
        jPersonId.setText(String.valueOf(generatePersonId()));
        
        //non-editable
        jDoctorHospital.setEnabled(false); 
        jDoctorName.setEnabled(false);
        jDoctorCity.setEnabled(false);
        jDoctorCommunity.setEnabled(false);
        
        //DropDown logic
        populateDropDownValues();
    }
    
    private void populateDoctorTableAfterReset() {
       DefaultTableModel dtmodel = (DefaultTableModel) jDoctorTable.getModel();
        dtmodel.setRowCount(0);
        
        ArrayList<Doctor> filteredList = new ArrayList<>();
        
        doctorCatalogue.getDoctorList().stream()
                .filter(x->x.getCity().equalsIgnoreCase("Select")).forEach(action->filteredList.add(action));
        
        for(Doctor doc: filteredList){
           Object[] obj = new Object[5];
            obj[0] = doc;
            obj[1] = doc.getName();
            obj[2] = doc.getHospital();
            obj[3] = doc.getCity();
            obj[4] = doc.getCommunity();
             
            dtmodel.addRow(obj);
        }
    }

    //ID
    private int generatePersonId(){
        if(!personCatalogue.getPersonList().isEmpty()){
            personId = personCatalogue.getPersonList().get(personCatalogue.getPersonList().size()-1).getPersonId()+1;
            return personId;
        }   
        return defaultId;
    }
    
    private void populateDoctorTable() {
       DefaultTableModel dtmodel = (DefaultTableModel) jDoctorTable.getModel();
        dtmodel.setRowCount(0);
        
        for(Doctor doc: doctorCatalogue.getDoctorList()){
            Object[] obj = new Object[5];
            obj[0] = doc;
            obj[1] = doc.getName();
            obj[2] = doc.getHospital();
            obj[3] = doc.getCity();
            obj[4] = doc.getCommunity();
            
            dtmodel.addRow(obj);
        }
    }
    //Drop-down logic
    private void populateDropDownValues(){
        //Existing User 
        ArrayList<String> userList = new ArrayList<>();
        userList.add("-- Select --");
        this.personCatalogue.getPersonList().stream().forEach(action->{
            userList.add(action.getPersonName());
        });
        Set<String> uniqueUsers = new HashSet<String>(userList);
        jExistingUser.setModel(new DefaultComboBoxModel<>(uniqueUsers.toArray(new String[0])));
        
        //City 
        ArrayList<String> cityList = new ArrayList<>();
        cityList.add("-- Select --");
        this.hospitalCatalogue.getHospitalList().stream().forEach(action->{
            cityList.add(action.getCity());
        });
        Set<String> uniqueCities = new HashSet<String>(cityList);
        jHospitalCity.setModel(new DefaultComboBoxModel<>(uniqueCities.toArray(new String[0])));
        
        //Community
        List<String> communityList = new ArrayList<>();
        communityList.add("-- Select --");
        this.hospitalCatalogue.getHospitalList().stream().forEach(action->{
            communityList.add(action.getCommunity());
        });
        Set<String> uniqueCommunities = new HashSet<String>(communityList);
        jHospitalCommunity.setModel(new DefaultComboBoxModel<>(uniqueCommunities.toArray(new String[0])));
        
        //Hospital
        List<String> hospitalList = new ArrayList<>();
        hospitalList.add("-- Select --");
        this.hospitalCatalogue.getHospitalList().stream().forEach(action->{
            hospitalList.add(action.getHospitalName());
        });
        jHospital.setModel(new DefaultComboBoxModel<>(hospitalList.toArray(new String[0])));
        
    }
        
         private void populateDropDownAfterReset(){
        //Existing user
        List<String> userList = new ArrayList<>();
        userList.add("-- Select --");
        this.personCatalogue.getPersonList().stream().forEach(action->{
            userList.add(action.getPersonName());
        });   
        jExistingUser.setModel(new DefaultComboBoxModel<>(userList.toArray(new String[0])));
        
        //City
        List<String> cityList = new ArrayList<>();
        cityList.add("-- Select --");
        this.hospitalCatalogue.getHospitalList().stream().forEach(action->{
            cityList.add(action.getCity());
        }); 
        Set<String> uniqueCities = new HashSet<String>(cityList);
        
        jHospitalCity.setModel(new DefaultComboBoxModel<>(uniqueCities.toArray(new String[0])));

        //Community
        List<String> communityList = new ArrayList<>();
        communityList.add("-- Select --");
        this.hospitalCatalogue.getHospitalList().stream().forEach(action->{
            communityList.add(action.getCommunity());
        });   
        Set<String> uniqueCommunities = new HashSet<String>(cityList);
        
        jHospitalCommunity.setModel(new DefaultComboBoxModel<>(uniqueCommunities.toArray(new String[0])));
       
        
        //Hospital
        List<String> hospitalList = new ArrayList<>();
        hospitalList.add("-- Select --");
        this.hospitalCatalogue.getHospitalList().stream().forEach(action->{
            hospitalList.add(action.getHospitalName());
        }); 
        jHospital.setModel(new DefaultComboBoxModel<>(hospitalList.toArray(new String[0])));
       

    }
         
      private void clearFields() {
        //populate dropdown
        populateDropDownAfterReset();
        
        //Clear fields
        jExistingUser.setSelectedIndex(0);
        jPersonAge.setText("");
        jPersonGender.setSelectedIndex(0);
        jPersonAddress.setText("");
        jPersonNumber.setText("");
        jPersonEmail.setText("");
//        jPersonComments.setText("");
        
        jDoctorName.setText("");
        jDoctorHospital.setText("");
        jDoctorCommunity.setText("");
        jDoctorCity.setText("");
        
        //Clear validation messages
        jExistingUserNameValidation.setText(" ");
        jPersonAgeValid.setText(" ");
        jPersonGenderValid.setText(" ");
        jPersonAddressValid.setText(" ");
        jPersonNumberValid.setText(" ");
        jPersonEmailValid.setText(" ");
        
        //Refresh personId
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

        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPersonAge = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPersonNumber = new javax.swing.JTextField();
        jPersonAddress = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPersonEmail = new javax.swing.JTextField();
        jPersonGender = new javax.swing.JComboBox<>();
        jPersonAgeValid = new javax.swing.JLabel();
        jPersonAddressValid = new javax.swing.JLabel();
        jExistingUserNameValidation = new javax.swing.JLabel();
        jPersonEmailValid = new javax.swing.JLabel();
        jPersonGenderValid = new javax.swing.JLabel();
        jPersonNumberValid = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPersonIdValidation = new javax.swing.JLabel();
        jPersonId = new javax.swing.JTextField();
        jExistingUser = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jDoctorHospital = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jDoctorName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jDoctorCity = new javax.swing.JTextField();
        jDoctorCommunity = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jDoctorTable = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jHospitalCity = new javax.swing.JComboBox<>();
        jHospitalCommunity = new javax.swing.JComboBox<>();
        jHospital = new javax.swing.JComboBox<>();
        jResetBtn = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jSearchBtn = new javax.swing.JButton();
        jHospitalCityValidation = new javax.swing.JLabel();
        jHospitalCommunityValidation = new javax.swing.JLabel();
        jHospitalNameValidation = new javax.swing.JLabel();
        jTitle3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jBookAppointment = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(1100, 800));
        setMinimumSize(new java.awt.Dimension(1100, 800));
        setPreferredSize(new java.awt.Dimension(1100, 800));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("EXISTING USER");
        jLabel1.setToolTipText("");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Patient Details"));

        jLabel3.setText("Name");

        jLabel4.setText("Age");

        jPersonAge.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPersonAgeKeyReleased(evt);
            }
        });

        jLabel8.setText("Gender");

        jLabel12.setText("Address");

        jPersonNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPersonNumberKeyReleased(evt);
            }
        });

        jLabel10.setText("Contact Number");

        jLabel11.setText("Email ID");

        jPersonEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPersonEmailKeyReleased(evt);
            }
        });

        jPersonGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select--", "Male", "Female", "Others" }));
        jPersonGender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPersonGenderActionPerformed(evt);
            }
        });

        jPersonAgeValid.setText("   ");

        jPersonAddressValid.setText("   ");

        jExistingUserNameValidation.setText("   ");

        jPersonEmailValid.setText("   ");

        jPersonGenderValid.setText("   ");

        jPersonNumberValid.setText("   ");

        jLabel2.setText("Person ID");

        jExistingUser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select--" }));
        jExistingUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jExistingUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel8)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPersonNumberValid, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPersonNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jPersonIdValidation, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPersonId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                                        .addComponent(jPersonGenderValid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPersonGender, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(46, 46, 46)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3))
                                .addGap(35, 35, 35)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPersonAge, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jPersonAgeValid, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jExistingUserNameValidation, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jExistingUser, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jPersonAddressValid, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jPersonAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPersonEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPersonEmailValid, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPersonEmail, jPersonId, jPersonNumber});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(42, 42, 42))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jPersonId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jExistingUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPersonIdValidation, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jExistingUserNameValidation))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPersonAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPersonAgeValid))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jPersonGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPersonGenderValid)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jPersonAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPersonAddressValid))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel12)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jPersonNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPersonNumberValid)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jPersonEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPersonEmailValid)))))))
                .addGap(15, 15, 15))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Doctor Details"));

        jLabel5.setText("Hospital Name");

        jLabel6.setText("Doctor Name");

        jLabel7.setText("City");

        jLabel9.setText("Community");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9))
                .addGap(42, 42, 42)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDoctorName, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                    .addComponent(jDoctorHospital)
                    .addComponent(jDoctorCity)
                    .addComponent(jDoctorCommunity))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jDoctorName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jDoctorHospital, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jDoctorCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jDoctorCommunity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jDoctorTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Doctor ID", "Doctor Name", "Hospital ", "City", "Community"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jDoctorTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jDoctorTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jDoctorTable);
        if (jDoctorTable.getColumnModel().getColumnCount() > 0) {
            jDoctorTable.getColumnModel().getColumn(0).setResizable(false);
            jDoctorTable.getColumnModel().getColumn(1).setResizable(false);
            jDoctorTable.getColumnModel().getColumn(2).setResizable(false);
            jDoctorTable.getColumnModel().getColumn(3).setResizable(false);
            jDoctorTable.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setText("Fill the form to book an appointment");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Search"));

        jHospitalCity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select--" }));
        jHospitalCity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jHospitalCityActionPerformed(evt);
            }
        });

        jHospitalCommunity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select--" }));
        jHospitalCommunity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jHospitalCommunityActionPerformed(evt);
            }
        });

        jHospital.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select--" }));
        jHospital.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jHospitalActionPerformed(evt);
            }
        });

        jResetBtn.setText("Reset");
        jResetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jResetBtnActionPerformed(evt);
            }
        });

        jLabel20.setText("Hospital");

        jLabel21.setText("City");

        jLabel22.setText("Community");

        jSearchBtn.setText("Search");
        jSearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSearchBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jHospitalCityValidation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jHospitalCity, 0, 129, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jHospitalCommunityValidation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jHospitalCommunity, 0, 129, Short.MAX_VALUE))
                        .addGap(20, 20, 20)
                        .addComponent(jLabel20)
                        .addGap(18, 18, 18)
                        .addComponent(jHospital, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jHospitalNameValidation, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jResetBtn)
                .addGap(27, 27, 27)
                .addComponent(jSearchBtn)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jHospitalCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jHospitalCommunity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jHospital, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jResetBtn)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22)
                    .addComponent(jSearchBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jHospitalCityValidation, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jHospitalCommunityValidation, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
                    .addComponent(jHospitalNameValidation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );

        jTitle3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jTitle3.setText("DOCTOR DATA");

        jBookAppointment.setText("Book An Appointment");
        jBookAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBookAppointmentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 995, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTitle3, javax.swing.GroupLayout.Alignment.LEADING)))
                                .addGap(0, 60, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(48, 48, 48))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jBookAppointment)
                                        .addGap(124, 124, 124))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(450, 450, 450)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jTitle3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBookAppointment)))
                .addContainerGap(79, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

   
    

    private void jPersonAgeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPersonAgeKeyReleased
        // TODO add your handling code here:
        if(!validateAge()){
            jPersonAgeValid.setText("Minimum Age 18 Required");
            jPersonAgeValid.setForeground(Color.red);
        } else {
            jPersonAgeValid.setText(" ");
        }
    }//GEN-LAST:event_jPersonAgeKeyReleased

    private boolean validateAge(){
        boolean isValid;
        String validAge = jPersonAge.getText();
        Pattern pattern = Pattern.compile("[1-9]{1}[0-9]{0,1}"); //Only accept > than 17
        Matcher matcher = pattern.matcher(validAge);
        isValid = matcher.matches();
        if(isValid && Integer.parseInt(validAge) >= 18) {
            return true;
        }
        return false;
    }
    private void jPersonNumberKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPersonNumberKeyReleased
        // TODO add your handling code here:
        if(!validateNumber()){
            jPersonNumberValid.setText("Invalid Phone Number");
            jPersonNumberValid.setForeground(Color.red);
        } else {
            jPersonNumberValid.setText(" ");
        }
    }//GEN-LAST:event_jPersonNumberKeyReleased

    private boolean validateNumber(){
        boolean isValid;
        String validNum = jPersonNumber.getText();
        Pattern pattern = Pattern.compile("[1-9]{1}[0-9]{9}");
        Matcher matcher = pattern.matcher(validNum);
        isValid = matcher.matches();
        return isValid;
    }
    private void jPersonEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPersonEmailKeyReleased
        // TODO add your handling code here:
        if(!validateEmail()){
            jPersonEmailValid.setText("Invalid Email");
            jPersonEmailValid.setForeground(Color.red);
        } else {
            jPersonEmailValid.setText(" ");
        }
    }//GEN-LAST:event_jPersonEmailKeyReleased

     private boolean validateEmail(){
        boolean isValid;
        String validEmail = jPersonEmail.getText();
        Pattern pattern = Pattern.compile("[A-Za-z0-9]+[.]{0,1}[A-Za-z0-9]+[@](gmail.com|northeastern.edu)");
        Matcher matcher = pattern.matcher(validEmail);
        isValid = matcher.matches();
        return isValid;
    }
    private void jPersonGenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPersonGenderActionPerformed
        // TODO add your handling code here:
        if(validateGender()){
            jPersonGenderValid.setText("Gender Required");
            jPersonGenderValid.setForeground(Color.red);
        } else {
            jPersonGenderValid.setText(" ");
        }
    }//GEN-LAST:event_jPersonGenderActionPerformed

    private boolean validateGender(){
        String selectedGender = jPersonGender.getSelectedItem().toString();
        return selectedGender.equals("-- Select --");
    }

    private void jDoctorTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDoctorTableMouseClicked
        //Clear previous selected data
         clearFields();

        //Select row data
        int selectedRow = jDoctorTable.getSelectedRow();
        DefaultTableModel dtmodel = (DefaultTableModel) jDoctorTable.getModel();
        Doctor selectedRowObj = (Doctor) dtmodel.getValueAt(selectedRow, 0);

        //Display rowData
        jDoctorName.setText(selectedRowObj.getName());
        jDoctorHospital.setText(selectedRowObj.getHospital());
        jDoctorCity.setText(selectedRowObj.getCity());
        jDoctorCommunity.setText(selectedRowObj.getCommunity());

    }//GEN-LAST:event_jDoctorTableMouseClicked

    private void jResetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jResetBtnActionPerformed
        // Toggle buttons
        clearFields();
        populateDoctorTableAfterReset();
        //jSearchBtn.setSelectedIndex(0);
        
    }//GEN-LAST:event_jResetBtnActionPerformed

    private void jHospitalCityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHospitalCityActionPerformed
        // TODO add your handling code here:
        //Validation
        if(validateCity()){
            jHospitalCityValidation.setText("City Required");
            jHospitalCityValidation.setForeground(Color.red);
        } else {
            jHospitalCityValidation.setText(" ");
        } 
        
        //Populate Community & Hospital dynamically
        ArrayList<String> filteredCommunityList = new ArrayList<>();
        if(!jHospitalCity.getSelectedItem().toString().equals("-- Select --")){
            hospitalCatalogue.getHospitalList().stream()
                .filter(hosp -> hosp.getCity().equals(jHospitalCity.getSelectedItem().toString()))
                .forEach(action -> filteredCommunityList.add(action.getCommunity()));
            jHospitalCommunity.setModel(new DefaultComboBoxModel<>(filteredCommunityList.toArray(new String[0]))); 
        }
        
        ArrayList<String> filteredHospitalList = new ArrayList<>();
        if(!jHospitalCity.getSelectedItem().toString().equals("-- Select --")){
            hospitalCatalogue.getHospitalList().stream()
                .filter(hosp -> hosp.getCity().equals(jHospitalCity.getSelectedItem().toString()))
                .forEach(action -> filteredHospitalList.add(action.getHospitalName()));
            jHospital.setModel(new DefaultComboBoxModel<>(filteredHospitalList.toArray(new String[0])));
        }
        jHospital.setEnabled(false);
    }//GEN-LAST:event_jHospitalCityActionPerformed

    private boolean validateCity(){
        String selectedValue = jHospitalCity.getSelectedItem().toString();
        return selectedValue.equals("-- Select --");
    }
    
    private void jHospitalCommunityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHospitalCommunityActionPerformed
        // TODO add your handling code here:
        //Validation
        if(validateCommunity()){
            jHospitalCommunityValidation.setText("Community Required");
            jHospitalCommunityValidation.setForeground(Color.red);
        } else {
            jHospitalCommunityValidation.setText(" ");
        }
        
        jHospital.setEnabled(true);
        //Populate Hospital dynamically
        ArrayList<String> filteredHospitalList = new ArrayList<>();
        if(!jHospitalCommunity.getSelectedItem().toString().equals("-- Select --")){
            hospitalCatalogue.getHospitalList().stream()
                .filter(hosp -> hosp.getCommunity().equals(jHospitalCommunity.getSelectedItem().toString()))
                .forEach(action -> filteredHospitalList.add(action.getHospitalName()));
            jHospital.setModel(new DefaultComboBoxModel<>(filteredHospitalList.toArray(new String[0])));
        }
    }//GEN-LAST:event_jHospitalCommunityActionPerformed

     private boolean validateCommunity(){
        String selectedValue = jHospitalCommunity.getSelectedItem().toString();
        return selectedValue.equals("-- Select --");
    }
     
    private void jHospitalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHospitalActionPerformed
        // TODO add your handling code here:
        
        if(validateHospital()){
            jHospitalNameValidation.setText("Hospital Required");
            jHospitalNameValidation.setForeground(Color.red);
        } else {
            jHospitalNameValidation.setText(" ");
        }
    }//GEN-LAST:event_jHospitalActionPerformed

    private void jBookAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBookAppointmentActionPerformed
        // TODO add your handling code here:
        int selectedRow = jDoctorTable.getSelectedRow();      
        if(selectedRow<0){
            JOptionPane.showMessageDialog(this, "Please select a doctor from the table to book an appointment.");
            return;
        }  
        
        //check validations
        boolean checkIsValid = !validateExistingUser() && validateAge() && validateNumber() && validateEmail() && !validateGender();
                                
        if(!checkIsValid){
            JOptionPane.showMessageDialog(this, "Please provide the required details!");
            return;
        }
            
        //validations passed
        Patient patient = new Patient();
        
        //Set variables
        patient.setPatientId(Integer.parseInt(jPersonId.getText()));
        patient.setPatientName(jExistingUser.getSelectedItem().toString());
        patient.setPatientAge(Integer.parseInt(jPersonAge.getText()));
        patient.setPatientTelNum(jPersonNumber.getText());
        patient.setPatientEmail(jPersonEmail.getText());
        
        patient.setConsultedDoctor(jDoctorName.getText());
        patient.setCity(jDoctorCity.getText());
        patient.setCommunity(jDoctorCommunity.getText());
        patient.setHospital(jDoctorHospital.getText());
        //Add to list
        patientCatalogue.addPatient(patient);
        JOptionPane.showMessageDialog(this, "Booked an Appointment successfully!");
        
        //Clear fields after save
        clearFields();
        
        //refresh table after save
        //populatePersonTable();
    }//GEN-LAST:event_jBookAppointmentActionPerformed

    private void jExistingUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jExistingUserActionPerformed
        // TODO add your handling code here:
        if(validateExistingUser()){
            jExistingUserNameValidation.setText("City Required");
            jExistingUserNameValidation.setForeground(Color.red);
        } else {
            jExistingUserNameValidation.setText(" ");
        }
    }//GEN-LAST:event_jExistingUserActionPerformed

    private void jSearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSearchBtnActionPerformed
        // TODO add your handling code here:
        String selectedCommunity = jHospitalCommunity.getSelectedItem().toString();
        String selectedCity = jHospitalCity.getSelectedItem().toString();
        
        DefaultTableModel dtmodel = (DefaultTableModel) jDoctorTable.getModel();
        dtmodel.setRowCount(0);
        
        ArrayList<Doctor> filteredList = new ArrayList<>();
        
        doctorCatalogue.getDoctorList().stream()
                .filter(hosp->hosp.getCity().equalsIgnoreCase(selectedCity)
                        &&hosp.getCommunity().equalsIgnoreCase(selectedCommunity))
                .forEach(hospData->filteredList.add(hospData));
        
        for(Doctor hosp: filteredList){
            Object[] obj = new Object[5];
            obj[0] = hosp;
            obj[1] = hosp.getName();
            obj[2] = hosp.getHospital();
            obj[3] = hosp.getCity();
            obj[4] = hosp.getCommunity();
            dtmodel.addRow(obj);
        }
    }//GEN-LAST:event_jSearchBtnActionPerformed

        private boolean validateExistingUser() {
        String selectedValue = jExistingUser.getSelectedItem().toString();
        return selectedValue.equals("-- Select --");
    }
    private boolean validateHospital(){
        String selectedValue = jHospital.getSelectedItem().toString();
        return selectedValue.equals("-- Select --");
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBookAppointment;
    private javax.swing.JTextField jDoctorCity;
    private javax.swing.JTextField jDoctorCommunity;
    private javax.swing.JTextField jDoctorHospital;
    private javax.swing.JTextField jDoctorName;
    private javax.swing.JTable jDoctorTable;
    private javax.swing.JComboBox<String> jExistingUser;
    private javax.swing.JLabel jExistingUserNameValidation;
    private javax.swing.JComboBox<String> jHospital;
    private javax.swing.JComboBox<String> jHospitalCity;
    private javax.swing.JLabel jHospitalCityValidation;
    private javax.swing.JComboBox<String> jHospitalCommunity;
    private javax.swing.JLabel jHospitalCommunityValidation;
    private javax.swing.JLabel jHospitalNameValidation;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField jPersonAddress;
    private javax.swing.JLabel jPersonAddressValid;
    private javax.swing.JTextField jPersonAge;
    private javax.swing.JLabel jPersonAgeValid;
    private javax.swing.JTextField jPersonEmail;
    private javax.swing.JLabel jPersonEmailValid;
    private javax.swing.JComboBox<String> jPersonGender;
    private javax.swing.JLabel jPersonGenderValid;
    private javax.swing.JTextField jPersonId;
    private javax.swing.JLabel jPersonIdValidation;
    private javax.swing.JTextField jPersonNumber;
    private javax.swing.JLabel jPersonNumberValid;
    private javax.swing.JButton jResetBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jSearchBtn;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel jTitle3;
    // End of variables declaration//GEN-END:variables

    
}
