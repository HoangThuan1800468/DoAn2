/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo_doan2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author lyhoa
 */
public class Dang_Nhap extends javax.swing.JFrame {

    Connect a = new Connect();
    Connection conn = a.getConnection();
    public static String getid;
    public Dang_Nhap() {
        initComponents();
    
    }

    public void DangNhap(){
        getid = textbox_IDGiaoVien.getText(); 
        String mkht = textbox_MatKhau.getText();
         
        try{
            PreparedStatement pr = conn.prepareStatement("select idgiaovien, matkhau from taikhoan where idgiaovien = ? and matkhau = ? and kichhoat like 'DKH'");
            pr.setString(1, getid);
            pr.setString(2, mkht);
            ResultSet rs = pr.executeQuery();            
            if(rs.next()){
                Dang_Nhap.this.setVisible(false);
                new Menu_Quan_Ly().setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null,"Sai thông tin!","Thông báo",JOptionPane.ERROR_MESSAGE);
            }
           
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,"Thất bại!","Thông báo",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        textbox_IDGiaoVien = new javax.swing.JTextField();
        button_DangNhap = new javax.swing.JButton();
        textbox_MatKhau = new javax.swing.JPasswordField();
        label_QuenMatKhau = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Phần mềm quản lý cơ sở vật chất");
        setLocation(new java.awt.Point(700, 400));
        setName("dangnhap"); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-admin-settings-male-60.png"))); // NOI18N
        jLabel1.setText("PHẦN MỀM QUẢN LÝ CƠ SỞ VẬT CHẤT");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-collaborator-male-24.png"))); // NOI18N
        jLabel2.setText("ID Giáo viên");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-key-2-24.png"))); // NOI18N
        jLabel4.setText("Mật khẩu");

        button_DangNhap.setBackground(java.awt.Color.lightGray);
        button_DangNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-login-24.png"))); // NOI18N
        button_DangNhap.setText("Đăng Nhập");
        button_DangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_DangNhapActionPerformed(evt);
            }
        });

        label_QuenMatKhau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-password-reset-24.png"))); // NOI18N
        label_QuenMatKhau.setText("Quên mật khẩu?");
        label_QuenMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_QuenMatKhauMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(button_DangNhap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textbox_IDGiaoVien)
                            .addComponent(textbox_MatKhau, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(label_QuenMatKhau))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textbox_IDGiaoVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textbox_MatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(button_DangNhap)
                .addGap(18, 18, 18)
                .addComponent(label_QuenMatKhau)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void label_QuenMatKhauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_QuenMatKhauMouseClicked
        // TODO add your handling code here:
        Dang_Nhap.this.setVisible(false);
        new Quen_Mat_Khau().setVisible(true);
    }//GEN-LAST:event_label_QuenMatKhauMouseClicked

    private void button_DangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_DangNhapActionPerformed
        // TODO add your handling code here:
       DangNhap();
    }//GEN-LAST:event_button_DangNhapActionPerformed

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
            java.util.logging.Logger.getLogger(Dang_Nhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dang_Nhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dang_Nhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dang_Nhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dang_Nhap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_DangNhap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel label_QuenMatKhau;
    private javax.swing.JTextField textbox_IDGiaoVien;
    private javax.swing.JPasswordField textbox_MatKhau;
    // End of variables declaration//GEN-END:variables
}
