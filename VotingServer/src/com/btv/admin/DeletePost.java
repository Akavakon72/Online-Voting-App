package com.btv.admin;

import java.awt.Dimension;
import java.sql.Connection;
import java.sql.Statement;
import javax.swing.JOptionPane;
import org.btv.conn.ConnectionClass;
import java.sql.ResultSet;

public class DeletePost extends javax.swing.JDialog {

    /**
     * Creates new form Login
     */
    static Connection conn;
    static Statement stm;

    public DeletePost(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        initComponents();
        try {
            combo_deptname.removeAllItems();
            ResultSet departments = new ConnectionClass().getDepartments();
            while (departments.next()) {
                combo_deptname.addItem(departments.getString("dept"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        but_add = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        combo_postname = new javax.swing.JComboBox();
        combo_deptname = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Login Form");
        setIconImages(null);
        getContentPane().setLayout(null);

        but_add.setText("DELETE");
        but_add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                but_addMouseClicked(evt);
            }
        });
        but_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_addActionPerformed(evt);
            }
        });
        getContentPane().add(but_add);
        but_add.setBounds(430, 90, 91, 30);

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("Select Post");
        getContentPane().add(jLabel24);
        jLabel24.setBounds(30, 90, 110, 30);

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("Select Department ");
        getContentPane().add(jLabel25);
        jLabel25.setBounds(30, 30, 120, 30);

        combo_postname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_postnameActionPerformed(evt);
            }
        });
        getContentPane().add(combo_postname);
        combo_postname.setBounds(150, 90, 260, 30);

        combo_deptname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_deptnameActionPerformed(evt);
            }
        });
        getContentPane().add(combo_deptname);
        combo_deptname.setBounds(150, 30, 260, 30);

        setSize(new java.awt.Dimension(608, 300));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void but_addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_but_addMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_but_addMouseClicked

    private void but_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_addActionPerformed
        boolean response = false;

        try {
            if (combo_deptname.getSelectedItem().toString().trim() == null
                    || combo_postname.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "No Post For Department", "Message", JOptionPane.ERROR_MESSAGE);
                response = false;
            } else {
                String deptname = "" + combo_deptname.getSelectedItem().toString().trim();
                String post = "" + combo_postname.getSelectedItem().toString().trim();

                response = new ConnectionClass().deletePost(deptname, post);
                if (response) {
                    JOptionPane.showMessageDialog(this, "Post Deleted Successfully", "Message", JOptionPane.INFORMATION_MESSAGE);

                    combo_postname.removeAllItems();
                    ResultSet departments = new ConnectionClass().getPost(deptname);
                    while (departments.next()) {
                        combo_postname.addItem(departments.getString("post"));
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "Delete Post Failed", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_but_addActionPerformed

    private void combo_postnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_postnameActionPerformed
    }//GEN-LAST:event_combo_postnameActionPerformed

    private void combo_deptnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_deptnameActionPerformed
        try {
            String deptname = combo_deptname.getSelectedItem().toString();
            combo_postname.removeAllItems();
            ResultSet departments = new ConnectionClass().getPost(deptname);
            while (departments.next()) {
                combo_postname.addItem(departments.getString("post"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_combo_deptnameActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    DeletePost dialog = new DeletePost(new javax.swing.JFrame(), true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton but_add;
    private javax.swing.JComboBox combo_deptname;
    private javax.swing.JComboBox combo_postname;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    // End of variables declaration//GEN-END:variables
}
