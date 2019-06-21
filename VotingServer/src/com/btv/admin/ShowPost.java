package com.btv.admin;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.btv.conn.ConnectionClass;

public class ShowPost extends javax.swing.JDialog {

    /**
     * Creates new form Login
     */
    static Connection conn;
    static Statement stm;

    public ShowPost(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        fillTable();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        but_add = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add Department");
        setIconImages(null);
        getContentPane().setLayout(null);

        but_add.setText("Exit");
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
        but_add.setBounds(520, 250, 91, 30);

        model = new javax.swing.table.DefaultTableModel();
        jTable1.setModel(model);
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(30, 20, 580, 210);

        setSize(new java.awt.Dimension(663, 369));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void but_addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_but_addMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_but_addMouseClicked

    private void but_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_addActionPerformed
        dispose();
    }//GEN-LAST:event_but_addActionPerformed

    private void fillTable() {
        model = new DefaultTableModel();
        model.addColumn("Sr. No.");
        model.addColumn("Department");
        model.addColumn("Post");
        model.addColumn("Candidate-1");
        model.addColumn("Candidate-2");
        model.addColumn("Candidate-3");

        ResultSet departments = new ConnectionClass().getPostData();
        try {
            while (departments.next()) {
                Vector row = new Vector();
                row.add(departments.getString(1));
                row.add(departments.getString(2));
                row.add(departments.getString(3));
                row.add(departments.getString(4));
                row.add(departments.getString(5));
                row.add(departments.getString(6));
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShowPost.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTable1.setModel(model);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    ShowPost dialog = new ShowPost(new javax.swing.JFrame(), true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });

                    dialog.setVisible(true);
                } catch (HeadlessException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton but_add;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.table.DefaultTableModel model;
    // End of variables declaration//GEN-END:variables

}
