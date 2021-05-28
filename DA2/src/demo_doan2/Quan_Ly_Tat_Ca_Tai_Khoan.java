/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo_doan2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author lyhoa
 */
public class Quan_Ly_Tat_Ca_Tai_Khoan extends javax.swing.JFrame {

    Connect a = new Connect();
    Connection conn = a.getConnection();
    public Quan_Ly_Tat_Ca_Tai_Khoan() {
        initComponents();
    }

    
    public void XacNhan(){
        String tkht = textbox_TKHT.getText(), mkht = textbox_MKHT.getText();
        if(tkht.equals("admin")){
            Quan_Ly_Tat_Ca_Tai_Khoan.this.setVisible(false);
            new Quan_Ly_Tai_Khoan_Admin().setVisible(true);
        }else{
        try{
            Statement dd = conn.createStatement();
            PreparedStatement pr = conn.prepareStatement("select tengiaovien, matkhau from taikhoan where idgiaovien = ? and matkhau = ? and chucvu = N'Quản lý'");
            pr.setString(1, tkht);
            pr.setString(2, mkht);
            ResultSet rs = pr.executeQuery();
            //System.out.print(tkht.equals(HTHT));
            if(rs.next()){
                Quan_Ly_Tat_Ca_Tai_Khoan.this.setVisible(false);
                new Quan_Ly_Tai_Khoan_Admin().setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null,"Sai thông tin!","Thông báo",JOptionPane.ERROR_MESSAGE);
            }
           
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,"Thất bại!","Thông báo",JOptionPane.ERROR_MESSAGE);
        }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        textbox_TKHT = new javax.swing.JTextField();
        button_XacNhan = new javax.swing.JButton();
        textbox_MKHT = new javax.swing.JPasswordField();
        label_TroVeTrangChu = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Phần mềm quản lý cơ sở vật chất");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\lyhoa\\OneDrive\\Desktop\\New folder\\Demo_DoAn2\\src\\demo_doan2\\image\\icons8-admin-settings-male-60.png")); // NOI18N
        jLabel1.setText("Đăng nhập bằng tài khoản Quản lý");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\lyhoa\\OneDrive\\Desktop\\New folder\\Demo_DoAn2\\src\\demo_doan2\\image\\icons8-test-account-242.png")); // NOI18N
        jLabel2.setText("ID:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\lyhoa\\OneDrive\\Desktop\\New folder\\Demo_DoAn2\\src\\demo_doan2\\image\\icons8-key-2-24.png")); // NOI18N
        jLabel3.setText("Mật khẩu");

        button_XacNhan.setBackground(java.awt.Color.lightGray);
        button_XacNhan.setIcon(new javax.swing.ImageIcon("C:\\Users\\lyhoa\\OneDrive\\Desktop\\New folder\\Demo_DoAn2\\src\\demo_doan2\\image\\icons8-login-24.png")); // NOI18N
        button_XacNhan.setText("Xác nhận");
        button_XacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_XacNhanActionPerformed(evt);
            }
        });

        label_TroVeTrangChu.setIcon(new javax.swing.ImageIcon("C:\\Users\\lyhoa\\OneDrive\\Desktop\\New folder\\Demo_DoAn2\\src\\demo_doan2\\image\\icons8-go-back-24.png")); // NOI18N
        label_TroVeTrangChu.setText("Trở về");
        label_TroVeTrangChu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_TroVeTrangChuMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(43, 43, 43))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(130, 130, 130)
                                .addComponent(button_XacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(textbox_TKHT)
                            .addComponent(textbox_MKHT)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label_TroVeTrangChu)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(textbox_TKHT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textbox_MKHT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button_XacNhan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label_TroVeTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void button_XacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_XacNhanActionPerformed
        XacNhan(); 
    }//GEN-LAST:event_button_XacNhanActionPerformed

    private void label_TroVeTrangChuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_TroVeTrangChuMouseClicked
        // TODO add your handling code here:
        Quan_Ly_Tat_Ca_Tai_Khoan.this.setVisible(false);
        new Tai_Khoan().setVisible(true);
    }//GEN-LAST:event_label_TroVeTrangChuMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
if (JOptionPane.showConfirmDialog(this, "Thoát phần mềm?", "Xác nhận", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
               System.exit(0);
            }    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(Quan_Ly_Tat_Ca_Tai_Khoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Quan_Ly_Tat_Ca_Tai_Khoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Quan_Ly_Tat_Ca_Tai_Khoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Quan_Ly_Tat_Ca_Tai_Khoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Quan_Ly_Tat_Ca_Tai_Khoan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_XacNhan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel label_TroVeTrangChu;
    private javax.swing.JPasswordField textbox_MKHT;
    private javax.swing.JTextField textbox_TKHT;
    // End of variables declaration//GEN-END:variables
}
