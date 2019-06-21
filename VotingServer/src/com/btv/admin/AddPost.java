package com.btv.admin;

import java.sql.Connection;
import java.sql.Statement;
import javax.swing.JOptionPane;
import org.btv.conn.ConnectionClass;
import java.sql.ResultSet;

public class AddPost extends javax.swing.JDialog {

    /**
     * Creates new form Login
     */
    static Connection conn;
    static Statement stm;

    public AddPost(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        initComponents();
        try {
            combo_deptname.removeAll();
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
        txt_postname = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        combo_deptname = new javax.swing.JComboBox();
        jLabel26 = new javax.swing.JLabel();
        txt_postname1 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txt_postname2 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txt_postname3 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Login Form");
        setIconImages(null);
        getContentPane().setLayout(null);

        but_add.setText("ADD");
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
        but_add.setBounds(430, 30, 91, 30);

        txt_postname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_postnameActionPerformed(evt);
            }
        });
        getContentPane().add(txt_postname);
        txt_postname.setBounds(150, 80, 260, 30);

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("Post Name");
        getContentPane().add(jLabel24);
        jLabel24.setBounds(30, 80, 110, 30);

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("Select Department ");
        getContentPane().add(jLabel25);
        jLabel25.setBounds(30, 30, 120, 30);

        combo_deptname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_deptnameActionPerformed(evt);
            }
        });
        getContentPane().add(combo_deptname);
        combo_deptname.setBounds(150, 30, 260, 30);

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setText("Candidate 1");
        getContentPane().add(jLabel26);
        jLabel26.setBounds(30, 120, 110, 30);

        txt_postname1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_postname1ActionPerformed(evt);
            }
        });
        getContentPane().add(txt_postname1);
        txt_postname1.setBounds(150, 120, 260, 30);

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setText("Candidate 2");
        getContentPane().add(jLabel27);
        jLabel27.setBounds(30, 160, 110, 30);

        txt_postname2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_postname2ActionPerformed(evt);
            }
        });
        getContentPane().add(txt_postname2);
        txt_postname2.setBounds(150, 160, 260, 30);

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel28.setText("Candidate 3");
        getContentPane().add(jLabel28);
        jLabel28.setBounds(30, 200, 110, 30);

        txt_postname3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_postname3ActionPerformed(evt);
            }
        });
        getContentPane().add(txt_postname3);
        txt_postname3.setBounds(150, 200, 260, 30);

        setSize(new java.awt.Dimension(608, 444));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void but_addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_but_addMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_but_addMouseClicked

    private void but_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_addActionPerformed
        String response = "";
        String deptname = combo_deptname.getSelectedItem().toString();
        String post = txt_postname.getText().toString().trim();
        String post1 = txt_postname1.getText().toString().trim();
        String post2 = txt_postname2.getText().toString().trim();
        String post3 = txt_postname3.getText().toString().trim();
        if (post.equals("")) {
            JOptionPane.showMessageDialog(this, "Fill Post ", "Message", JOptionPane.ERROR_MESSAGE);

        } else {
            if (post1.equals("") || post2.equals("") || post3.equals("")) {
                JOptionPane.showMessageDialog(this, "Fill Candidate Name ", "Message", JOptionPane.ERROR_MESSAGE);
            } else {
                if (post1.equals(post2) || post2.equals(post3) || post3.equals(post1)) {
                    JOptionPane.showMessageDialog(this, "Candidate Name Repeat", "Message", JOptionPane.ERROR_MESSAGE);
                } else {
                    response = new ConnectionClass().addPost(deptname, post, post1, post2, post3);

                    if (response.contains("Allready")) {
                        JOptionPane.showMessageDialog(this, "" + response, "Message", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "" + response, "Message", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }

    }//GEN-LAST:event_but_addActionPerformed

    private void txt_postnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_postnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_postnameActionPerformed

    private void combo_deptnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_deptnameActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_combo_deptnameActionPerformed

    private void txt_postname1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_postname1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_postname1ActionPerformed

    private void txt_postname2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_postname2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_postname2ActionPerformed

    private void txt_postname3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_postname3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_postname3ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    AddPost dialog = new AddPost(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JTextField txt_postname;
    private javax.swing.JTextField txt_postname1;
    private javax.swing.JTextField txt_postname2;
    private javax.swing.JTextField txt_postname3;
    // End of variables declaration//GEN-END:variables

}
