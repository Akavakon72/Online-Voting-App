package com.btv.admin;

import com.bean.ResultBean;
import encdec.EncDecString;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.btv.conn.ConnectionClass;

public class ShowResult extends javax.swing.JDialog {

    /**
     * Creates new form Login
     */
    static Connection conn;
    static Statement stm;

    public ShowResult(java.awt.Frame parent, boolean modal) {
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
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Voting Result");
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
        but_add.setBounds(520, 280, 91, 30);

        model = new javax.swing.table.DefaultTableModel();
        jTable1.setModel(model);
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(30, 60, 580, 210);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("Result");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(250, 20, 150, 28);

        setSize(new java.awt.Dimension(663, 401));
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
        model.addColumn("Candidate");
        model.addColumn("Vote");
//        model.addColumn("Candidate-3");
        List<ResultBean> voteResult = new ConnectionClass().getVoteResult();
        try {
            int i = 1;
//            while (departments.next()) {
            for (ResultBean resultBean : voteResult) {

                Vector row = new Vector();
                row.add(i);
                row.add(resultBean.getDpt());
                row.add(resultBean.getPost());
                String candiname = new EncDecString().Decrypt(resultBean.getName());
                row.add(candiname);
                row.add(resultBean.getVote());
                model.addRow(row);

                i++;
            }
        } catch (Exception ex) {
            Logger.getLogger(ShowResult.class.getName()).log(Level.SEVERE, null, ex);
        }

        jTable1.setModel(model);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    ShowResult dialog = new ShowResult(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.table.DefaultTableModel model;
    // End of variables declaration//GEN-END:variables

}
