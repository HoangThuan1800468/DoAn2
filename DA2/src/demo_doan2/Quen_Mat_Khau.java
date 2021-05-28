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
public class Quen_Mat_Khau extends javax.swing.JFrame {

    

    public Quen_Mat_Khau() {
        initComponents();
    }

    public void DoiMatKhau(){
        String qtk = textbox_QuenTaiKhoan_IDGiaoVien.getText();
        String qmk = textbox_QuenTaiKhoan_MatKhau.getText();
        String qmk2 = textbox_QuenTaiKhoan_NhapLaiMatKhau.getText();
        
        if(qtk.equals("")){
            JOptionPane.showMessageDialog(null, "Chưa nhập ID!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }else if(qmk.equals("")){
            JOptionPane.showMessageDialog(null, "Chưa nhập mật khẩu!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }else if(qmk2.equals("")){
            JOptionPane.showMessageDialog(null, "Chưa nhập mật khẩu lần 2!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
        else{
            try {
                Connect a = new Connect();
                Connection conn = a.getConnection();
                Statement dd = conn.createStatement();  
                ResultSet  kq = dd.executeQuery("select idgiaovien,kichhoat from taikhoan where idgiaovien= '"+qtk+"'");
                String id = "";
                String kichhoat="";
                while (kq.next()) {
                     id = kq.getString(1);
                     kichhoat = kq.getString(2);
                }
                if(id.equals("")){
                        JOptionPane.showMessageDialog(null, "ID không có trong CSDL!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
                else if (textbox_QuenTaiKhoan_MatKhau.getText().equals(textbox_QuenTaiKhoan_NhapLaiMatKhau.getText()) == false) {
                    JOptionPane.showMessageDialog(null, "Mật khẩu nhập lại không trùng khớp!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }else{
                        Statement da = conn.createStatement();  
                        da.executeUpdate("update taikhoan set matkhau = '"+qmk+"' ,kichhoat = 'CKH' where idgiaovien like '" +qtk+"'");                        
                        JOptionPane.showMessageDialog(null, "Mật khẩu đã đổi, hiện không thể đăng nhập bằng tài khoản này! "
                                + "Liên hệ với quản trị viên để kich hoạt tài khoản!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        } 
                    
            } catch (Exception ex) {
                
                    JOptionPane.showMessageDialog(null, "Chưa nhập ID hoặc sai ID!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                
            }  
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        textbox_QuenTaiKhoan_NhapLaiMatKhau = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        textbox_QuenTaiKhoan_IDGiaoVien = new javax.swing.JTextField();
        button_TaoLaiMatKhau = new javax.swing.JButton();
        textbox_QuenTaiKhoan_MatKhau = new javax.swing.JPasswordField();
        label_TroVeTrangDangNhap = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Phần mềm quản lý cơ sở vật chất");
        setLocation(new java.awt.Point(700, 400));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-key-2-24.png"))); // NOI18N
        jLabel6.setText("Nhập lại mật khẩu");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-test-account-24.png"))); // NOI18N
        jLabel2.setText("ID Giáo viên");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-key-2-24.png"))); // NOI18N
        jLabel4.setText("Mật khẩu");

        button_TaoLaiMatKhau.setBackground(java.awt.Color.lightGray);
        button_TaoLaiMatKhau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-password-reset-24.png"))); // NOI18N
        button_TaoLaiMatKhau.setText("Đổi Mật Khẩu");
        button_TaoLaiMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button_TaoLaiMatKhauMouseClicked(evt);
            }
        });
        button_TaoLaiMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_TaoLaiMatKhauActionPerformed(evt);
            }
        });

        label_TroVeTrangDangNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-go-back-24.png"))); // NOI18N
        label_TroVeTrangDangNhap.setText("Trở về");
        label_TroVeTrangDangNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_TroVeTrangDangNhapMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-account-48.png"))); // NOI18N
        jLabel1.setText("QUÊN TÀI KHOẢN");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textbox_QuenTaiKhoan_MatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(38, 38, 38)
                        .addComponent(textbox_QuenTaiKhoan_IDGiaoVien, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(button_TaoLaiMatKhau, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                            .addComponent(textbox_QuenTaiKhoan_NhapLaiMatKhau, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(label_TroVeTrangDangNhap, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(0, 4, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textbox_QuenTaiKhoan_IDGiaoVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textbox_QuenTaiKhoan_MatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textbox_QuenTaiKhoan_NhapLaiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button_TaoLaiMatKhau)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_TroVeTrangDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void button_TaoLaiMatKhauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_TaoLaiMatKhauMouseClicked
        // TODO add your handling code here:
        DoiMatKhau();
    }//GEN-LAST:event_button_TaoLaiMatKhauMouseClicked

    private void label_TroVeTrangDangNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_TroVeTrangDangNhapMouseClicked
        // TODO add your handling code here:
        Quen_Mat_Khau.this.setVisible(false);
        new Dang_Nhap().setVisible(true);
    }//GEN-LAST:event_label_TroVeTrangDangNhapMouseClicked

    private void button_TaoLaiMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_TaoLaiMatKhauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_TaoLaiMatKhauActionPerformed

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
            java.util.logging.Logger.getLogger(Quen_Mat_Khau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Quen_Mat_Khau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Quen_Mat_Khau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Quen_Mat_Khau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Quen_Mat_Khau().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_TaoLaiMatKhau;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel label_TroVeTrangDangNhap;
    private javax.swing.JTextField textbox_QuenTaiKhoan_IDGiaoVien;
    private javax.swing.JPasswordField textbox_QuenTaiKhoan_MatKhau;
    private javax.swing.JPasswordField textbox_QuenTaiKhoan_NhapLaiMatKhau;
    // End of variables declaration//GEN-END:variables
}
