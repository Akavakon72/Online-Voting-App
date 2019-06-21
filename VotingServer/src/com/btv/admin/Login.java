package com.btv.admin;

import com.bt.VotingServer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Login extends javax.swing.JDialog {

    /**
     * Creates new form Login
     */
    static Connection conn;
    static Statement stm;

    public Login(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        initComponents();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txt_password = new javax.swing.JPasswordField();
        txtuser_name = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        but_login = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Login Form");
        setIconImages(null);
        getContentPane().setLayout(null);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logins.png"))); // NOI18N
        getContentPane().add(jLabel8);
        jLabel8.setBounds(0, 0, 120, 220);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(null);

        jLabel4.setText(" UnAuthorized Person is not allowed.");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(70, 130, 220, 14);
        jPanel1.add(txt_password);
        txt_password.setBounds(130, 80, 168, 20);
        jPanel1.add(txtuser_name);
        txtuser_name.setBounds(130, 40, 168, 20);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("User Name");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(50, 40, 61, 14);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Password");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(50, 80, 54, 14);

        jLabel7.setForeground(new java.awt.Color(204, 0, 0));
        jLabel7.setText("NOTE:");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(30, 130, 40, 14);

        but_login.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/i16x16/reload.png"))); // NOI18N
        but_login.setText("Log-In");
        but_login.setToolTipText("");
        but_login.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        but_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_loginActionPerformed(evt);
            }
        });
        jPanel1.add(but_login);
        but_login.setBounds(90, 160, 85, 25);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/i16x16/exit.png"))); // NOI18N
        jButton1.setText("Exit");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(180, 160, 90, 25);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(120, 0, 350, 220);

        setSize(new java.awt.Dimension(486, 259));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void but_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_loginActionPerformed

        String username = "admin";
        String password = "admin";
        if (txtuser_name.getText().equalsIgnoreCase(username) && txt_password.getText().equals(password)) {

            JOptionPane.showMessageDialog(this, "Login Successfully");
            new MDI_Form().setVisible(true);
            this.dispose();
        } else {

            JOptionPane.showMessageDialog(this, "Invalid Username or Password", "Error", JOptionPane.ERROR_MESSAGE);

            txtuser_name.setText("");
            txt_password.setText("");
            txtuser_name.requestFocus();
        }

    }//GEN-LAST:event_but_loginActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        this.dispose();

    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                Login dialog = new Login(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });

                dialog.setVisible(true);

            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton but_login;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txtuser_name;
    // End of variables declaration//GEN-END:variables

}
