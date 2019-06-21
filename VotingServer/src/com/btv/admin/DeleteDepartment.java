package com.btv.admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.btv.conn.ConnectionClass;

public class DeleteDepartment extends javax.swing.JDialog {

    /**
     * Creates new form Login
     */
    static Connection conn;
    static Statement stm;

    public DeleteDepartment(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        try {
            combo_deptname.removeAll();
            ResultSet departments = new ConnectionClass().getDepartments();
            while (departments.next()) {
                combo_deptname.addItem(departments.getString("dept"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        but_delete = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        combo_deptname = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Delete Post");
        setIconImages(null);
        getContentPane().setLayout(null);

        but_delete.setText("DELETE");
        but_delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                but_deleteMouseClicked(evt);
            }
        });
        but_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_deleteActionPerformed(evt);
            }
        });
        getContentPane().add(but_delete);
        but_delete.setBounds(410, 30, 91, 30);

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("Department Name");
        getContentPane().add(jLabel24);
        jLabel24.setBounds(20, 30, 110, 30);

        getContentPane().add(combo_deptname);
        combo_deptname.setBounds(140, 30, 260, 30);

        setSize(new java.awt.Dimension(588, 238));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void but_deleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_but_deleteMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_but_deleteMouseClicked

    private void but_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_deleteActionPerformed
        try {
            boolean response = false;
            String deptname = combo_deptname.getSelectedItem().toString();
            if (!deptname.equals("")) {
                response = new ConnectionClass().deleteDepartment(deptname);
            }
            if (response) {
                JOptionPane.showMessageDialog(this, "Department Delete Successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Delete Department  Failed", "Message", JOptionPane.ERROR_MESSAGE);
            }
            combo_deptname.removeAllItems();
            ResultSet departments = new ConnectionClass().getDepartments();
            while (departments.next()) {
                combo_deptname.addItem(departments.getString("dept"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DeleteDepartment.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_but_deleteActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    DeleteDepartment dialog = new DeleteDepartment(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton but_delete;
    private javax.swing.JComboBox combo_deptname;
    private javax.swing.JLabel jLabel24;
    // End of variables declaration//GEN-END:variables

}
