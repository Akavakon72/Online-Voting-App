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

public class AddDepartment extends javax.swing.JDialog {

    /**
     * Creates new form Login
     */
    static Connection conn;
    static Statement stm;

    public AddDepartment(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        fillTable();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        but_add = new javax.swing.JButton();
        txt_deptname = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add Department");
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
        but_add.setBounds(410, 30, 91, 30);
        getContentPane().add(txt_deptname);
        txt_deptname.setBounds(140, 30, 258, 30);

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("Department Name");
        getContentPane().add(jLabel24);
        jLabel24.setBounds(20, 30, 110, 30);

        model = new javax.swing.table.DefaultTableModel();
        jTable1.setModel(model);
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 90, 480, 210);

        setSize(new java.awt.Dimension(540, 369));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void but_addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_but_addMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_but_addMouseClicked

    private void but_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_addActionPerformed
        boolean response = false;
        String deptname = txt_deptname.getText().toString();
        if (!deptname.equals("")) {
            addRow(deptname);
            response = new ConnectionClass().addDepartment("" + deptname);
        }
        if (response) {
            JOptionPane.showMessageDialog(this, "Department Added Successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Add Department  Failed", "Message", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_but_addActionPerformed

    private void fillTable() {
        model = new DefaultTableModel();
        model.addColumn("Sr. No.");
        model.addColumn("Department");

        ResultSet departments = new ConnectionClass().getDepartments();
        try {
            while (departments.next()) {
                Vector row = new Vector();
                row.add(departments.getString(1));
                row.add(departments.getString(2));
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddDepartment.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTable1.setModel(model);
    }

    private void addRow(String dept) {
        int rowCount = model.getRowCount();
        if (rowCount < 1) {
            model.addRow(new Object[]{1, dept});
        } else {
            String toString = model.getValueAt(rowCount - 1, 0).toString();
            int lastRowPlusOne = Integer.parseInt(toString) + 1;
            model.addRow(new Object[]{lastRowPlusOne, dept});
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    AddDepartment dialog = new AddDepartment(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel24;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.table.DefaultTableModel model;
    private javax.swing.JTextField txt_deptname;
    // End of variables declaration//GEN-END:variables

}
