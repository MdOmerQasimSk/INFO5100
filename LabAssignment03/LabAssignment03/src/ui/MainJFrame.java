/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import model.DoctorCatalogue;
import model.HospitalCatalogue;
import model.PatientCatalogue;
import model.PersonCatalogue;
import ui.CommunityAdmin.CommunityAdminBaseJPanel;
import ui.DoctorView.DoctorLoginJPanel;
import ui.ExistingPerson.ExistingPersonBaseJPanel;
import ui.HospitalAdmin.HospitalAdminBaseJPanel;
import ui.Person.PersonBaseJPanel;
import ui.SystemAdmin.AdminBaseJPanel;

/**
 *
 * @author omerq
 */
public class MainJFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainJFrame
     */
    
    DoctorCatalogue doctorCatalogue;
    HospitalCatalogue hospitalCatalogue;
    PatientCatalogue patientCatalogue;
    PersonCatalogue personCatalogue;
    
    public MainJFrame() {
        initComponents();
        doctorCatalogue = new DoctorCatalogue();
        hospitalCatalogue = new HospitalCatalogue();
        patientCatalogue = new PatientCatalogue();
        personCatalogue = new PersonCatalogue();
        
        //Landing Page
        HickoryLandingJPanel hickoryLandingJPanel = new HickoryLandingJPanel();
        jSplitPane.setRightComponent(hickoryLandingJPanel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane = new javax.swing.JSplitPane();
        jLeftPanel = new javax.swing.JPanel();
        jSystemAdminBtn = new javax.swing.JButton();
        jHospitalAdminBtn = new javax.swing.JButton();
        jCommunityAdminBtn = new javax.swing.JButton();
        jBookApptBtn = new javax.swing.JButton();
        jDoctorViewBtn = new javax.swing.JButton();
        jHomeBtn = new javax.swing.JButton();
        jExistingUserAppointment = new javax.swing.JButton();
        jRightPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1250, 800));

        jSplitPane.setMaximumSize(new java.awt.Dimension(1105, 850));

        jLeftPanel.setMaximumSize(new java.awt.Dimension(143, 800));
        jLeftPanel.setMinimumSize(new java.awt.Dimension(143, 800));
        jLeftPanel.setPreferredSize(new java.awt.Dimension(143, 800));

        jSystemAdminBtn.setText("System Admin");
        jSystemAdminBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSystemAdminBtnActionPerformed(evt);
            }
        });

        jHospitalAdminBtn.setText("Hospital Admin");
        jHospitalAdminBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jHospitalAdminBtnActionPerformed(evt);
            }
        });

        jCommunityAdminBtn.setText("Community Admin");
        jCommunityAdminBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCommunityAdminBtnActionPerformed(evt);
            }
        });

        jBookApptBtn.setText("Book Appointment");
        jBookApptBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBookApptBtnActionPerformed(evt);
            }
        });

        jDoctorViewBtn.setText("Doctor View");
        jDoctorViewBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDoctorViewBtnActionPerformed(evt);
            }
        });

        jHomeBtn.setText("Hickory");
        jHomeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jHomeBtnActionPerformed(evt);
            }
        });

        jExistingUserAppointment.setText("Already Registered?");
        jExistingUserAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jExistingUserAppointmentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jLeftPanelLayout = new javax.swing.GroupLayout(jLeftPanel);
        jLeftPanel.setLayout(jLeftPanelLayout);
        jLeftPanelLayout.setHorizontalGroup(
            jLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLeftPanelLayout.createSequentialGroup()
                .addGroup(jLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLeftPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jCommunityAdminBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDoctorViewBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jHospitalAdminBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSystemAdminBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jLeftPanelLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jHomeBtn))
                    .addGroup(jLeftPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jExistingUserAppointment))
                    .addGroup(jLeftPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jBookApptBtn)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLeftPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jBookApptBtn, jCommunityAdminBtn, jDoctorViewBtn, jExistingUserAppointment, jHospitalAdminBtn, jSystemAdminBtn});

        jLeftPanelLayout.setVerticalGroup(
            jLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLeftPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jHomeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jSystemAdminBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jHospitalAdminBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCommunityAdminBtn)
                .addGap(227, 227, 227)
                .addComponent(jBookApptBtn)
                .addGap(18, 18, 18)
                .addComponent(jExistingUserAppointment)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 208, Short.MAX_VALUE)
                .addComponent(jDoctorViewBtn)
                .addGap(69, 69, 69))
        );

        jSplitPane.setLeftComponent(jLeftPanel);

        jRightPanel.setMaximumSize(new java.awt.Dimension(1100, 800));
        jRightPanel.setMinimumSize(new java.awt.Dimension(1100, 800));
        jRightPanel.setPreferredSize(new java.awt.Dimension(1100, 800));

        javax.swing.GroupLayout jRightPanelLayout = new javax.swing.GroupLayout(jRightPanel);
        jRightPanel.setLayout(jRightPanelLayout);
        jRightPanelLayout.setHorizontalGroup(
            jRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1100, Short.MAX_VALUE)
        );
        jRightPanelLayout.setVerticalGroup(
            jRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 850, Short.MAX_VALUE)
        );

        jSplitPane.setRightComponent(jRightPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jSystemAdminBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSystemAdminBtnActionPerformed
        // TODO add your handling code here:
        AdminBaseJPanel adminBaseJPanel = new AdminBaseJPanel(doctorCatalogue, hospitalCatalogue, patientCatalogue, personCatalogue);
        jSplitPane.setRightComponent(adminBaseJPanel);
    }//GEN-LAST:event_jSystemAdminBtnActionPerformed

    private void jDoctorViewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDoctorViewBtnActionPerformed
        // TODO add your handling code here:
        DoctorLoginJPanel doctorLoginJPanel = new DoctorLoginJPanel(doctorCatalogue,patientCatalogue);
        jSplitPane.setRightComponent(doctorLoginJPanel);
    }//GEN-LAST:event_jDoctorViewBtnActionPerformed

    private void jHomeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHomeBtnActionPerformed
        // TODO add your handling code here:
        HickoryLandingJPanel hickoryLandingJPanel = new HickoryLandingJPanel();
        jSplitPane.setRightComponent(hickoryLandingJPanel);
    }//GEN-LAST:event_jHomeBtnActionPerformed

    private void jCommunityAdminBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCommunityAdminBtnActionPerformed
        // TODO add your handling code here:
        CommunityAdminBaseJPanel communityAdminBaseJPanel = new CommunityAdminBaseJPanel(personCatalogue, hospitalCatalogue);
        jSplitPane.setRightComponent(communityAdminBaseJPanel);
    }//GEN-LAST:event_jCommunityAdminBtnActionPerformed

    private void jHospitalAdminBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHospitalAdminBtnActionPerformed
        // TODO add your handling code here:
        HospitalAdminBaseJPanel hospitalAdminBaseJPanel = new HospitalAdminBaseJPanel(hospitalCatalogue);
        jSplitPane.setRightComponent(hospitalAdminBaseJPanel);
    }//GEN-LAST:event_jHospitalAdminBtnActionPerformed

    private void jBookApptBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBookApptBtnActionPerformed
        // TODO add your handling code here:
        PersonBaseJPanel personBaseJPanel = new PersonBaseJPanel(hospitalCatalogue, doctorCatalogue, personCatalogue, patientCatalogue);
        jSplitPane.setRightComponent(personBaseJPanel);
    }//GEN-LAST:event_jBookApptBtnActionPerformed

    private void jExistingUserAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jExistingUserAppointmentActionPerformed
        // TODO add your handling code here:
        ExistingPersonBaseJPanel personBaseJPanel = new ExistingPersonBaseJPanel(hospitalCatalogue, doctorCatalogue, personCatalogue, patientCatalogue);
        jSplitPane.setRightComponent(personBaseJPanel);
    }//GEN-LAST:event_jExistingUserAppointmentActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBookApptBtn;
    private javax.swing.JButton jCommunityAdminBtn;
    private javax.swing.JButton jDoctorViewBtn;
    private javax.swing.JButton jExistingUserAppointment;
    private javax.swing.JButton jHomeBtn;
    private javax.swing.JButton jHospitalAdminBtn;
    private javax.swing.JPanel jLeftPanel;
    private javax.swing.JPanel jRightPanel;
    private javax.swing.JSplitPane jSplitPane;
    private javax.swing.JButton jSystemAdminBtn;
    // End of variables declaration//GEN-END:variables
}
