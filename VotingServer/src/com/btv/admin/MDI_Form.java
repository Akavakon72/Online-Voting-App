package com.btv.admin;

import com.bt.VotingServer;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Eblog
 */
public class MDI_Form extends javax.swing.JFrame {

    VotingServer vt;
    Thread thread;

    public MDI_Form() {

        initComponents();

        // Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        startserverButton = new javax.swing.JButton();
        stopserverButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menu_dept = new javax.swing.JMenu();
        menu_dept_add = new javax.swing.JMenuItem();
        dept_delete = new javax.swing.JMenuItem();
        menu_post = new javax.swing.JMenu();
        menu_post_ad = new javax.swing.JMenuItem();
        menu_post_delete = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        showresult_Menu = new javax.swing.JMenu();
        exit_Menu = new javax.swing.JMenu();

        jMenuItem3.setText("jMenuItem3");

        jMenuItem6.setText("jMenuItem6");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Voting Server");
        setMinimumSize(new java.awt.Dimension(800, 600));
        setType(java.awt.Window.Type.POPUP);
        getContentPane().setLayout(null);
        getContentPane().add(jDesktopPane1);
        jDesktopPane1.setBounds(710, 50, 0, 0);

        startserverButton.setBackground(new java.awt.Color(0, 255, 0));
        startserverButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        startserverButton.setText("START SERVER");
        startserverButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startserverButtonActionPerformed(evt);
            }
        });
        getContentPane().add(startserverButton);
        startserverButton.setBounds(400, 20, 120, 40);

        stopserverButton.setBackground(new java.awt.Color(255, 51, 51));
        stopserverButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        stopserverButton.setText("STOP SERVER");
        stopserverButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopserverButtonActionPerformed(evt);
            }
        });
        getContentPane().add(stopserverButton);
        stopserverButton.setBounds(540, 20, 120, 40);

        menu_dept.setText("Department");
        menu_dept.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        menu_dept_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/i16x16/addnew.png"))); // NOI18N
        menu_dept_add.setText("Add");
        menu_dept_add.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menu_dept_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_dept_addActionPerformed(evt);
            }
        });
        menu_dept.add(menu_dept_add);

        dept_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/i16x16/delete.png"))); // NOI18N
        dept_delete.setText("Delete");
        dept_delete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dept_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dept_deleteActionPerformed(evt);
            }
        });
        menu_dept.add(dept_delete);

        jMenuBar1.add(menu_dept);

        menu_post.setText("Post");
        menu_post.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        menu_post_ad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/i16x16/addnew.png"))); // NOI18N
        menu_post_ad.setText("Add");
        menu_post_ad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menu_post_ad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_post_adActionPerformed(evt);
            }
        });
        menu_post.add(menu_post_ad);

        menu_post_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/i16x16/delete.png"))); // NOI18N
        menu_post_delete.setText("Delete");
        menu_post_delete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menu_post_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_post_deleteActionPerformed(evt);
            }
        });
        menu_post.add(menu_post_delete);

        jMenuBar1.add(menu_post);

        jMenu1.setText("ShowPost");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        showresult_Menu.setText("Show Result");
        showresult_Menu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        showresult_Menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showresult_MenuMouseClicked(evt);
            }
        });
        showresult_Menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showresult_MenuActionPerformed(evt);
            }
        });
        jMenuBar1.add(showresult_Menu);

        exit_Menu.setText("Exit");
        exit_Menu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exit_Menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exit_MenuMouseClicked(evt);
            }
        });
        exit_Menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exit_MenuActionPerformed(evt);
            }
        });
        jMenuBar1.add(exit_Menu);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menu_dept_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_dept_addActionPerformed

        new AddDepartment(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_menu_dept_addActionPerformed

    private void menu_post_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_post_deleteActionPerformed

        new DeletePost(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_menu_post_deleteActionPerformed

    private void menu_post_adActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_post_adActionPerformed

        new AddPost(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_menu_post_adActionPerformed

    private void dept_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dept_deleteActionPerformed
        new DeleteDepartment(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_dept_deleteActionPerformed

    private void exit_MenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exit_MenuActionPerformed

    }//GEN-LAST:event_exit_MenuActionPerformed

    private void exit_MenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exit_MenuMouseClicked
        this.dispose();
    }//GEN-LAST:event_exit_MenuMouseClicked

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed

    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked

        new ShowPost(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_jMenu1MouseClicked

    private void showresult_MenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showresult_MenuMouseClicked
        new ShowResult(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_showresult_MenuMouseClicked

    private void showresult_MenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showresult_MenuActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_showresult_MenuActionPerformed

    private void startserverButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startserverButtonActionPerformed

        vt = new VotingServer();
        vt.start();

        startserverButton.setEnabled(false);
        stopserverButton.setEnabled(true);

    }//GEN-LAST:event_startserverButtonActionPerformed

    private void stopserverButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopserverButtonActionPerformed

        vt.setIsRunning(false);
        vt.destroy();
        System.out.println("Server Stop");

        stopserverButton.setEnabled(false);
        startserverButton.setEnabled(true);

    }//GEN-LAST:event_stopserverButtonActionPerformed

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
            java.util.logging.Logger.getLogger(MDI_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MDI_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MDI_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MDI_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MDI_Form().setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("e");
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem dept_delete;
    private javax.swing.JMenu exit_Menu;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenu menu_dept;
    private javax.swing.JMenuItem menu_dept_add;
    private javax.swing.JMenu menu_post;
    private javax.swing.JMenuItem menu_post_ad;
    private javax.swing.JMenuItem menu_post_delete;
    private javax.swing.JMenu showresult_Menu;
    private javax.swing.JButton startserverButton;
    private javax.swing.JButton stopserverButton;
    // End of variables declaration//GEN-END:variables
}
