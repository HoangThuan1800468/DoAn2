/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo_doan2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author lyhoa
 */
public class Them_Tai_Khoan extends javax.swing.JFrame {

    Connect a = new Connect();
    Connection conn = a.getConnection();
    public Them_Tai_Khoan() {
        initComponents();
    }

    public void TaoTaiKhoan(){
        String mk1 = textbox_TaoTaiKhoan_MatKhau.getText();
        String mk2 = textbox_TaoTaiKhoan_NhapLaiMatKhau1.getText();
        if (mk1.equals(mk2) != true){
            JOptionPane.showMessageDialog(null,"Nhập lại mật khẩu không khớp!","Thông báo",JOptionPane.ERROR_MESSAGE);
        }
        else{
            String chucvu = txt_chucvuhientai.getSelectedItem().toString();
            String id = txt_idgv.getText();
            String tengiaovien = textbox_TaoTaiKhoan_TenGiaoVien.getText();
            String matkhau = textbox_TaoTaiKhoan_MatKhau.getText();
            try {
                if (id.equals("") && tengiaovien.equals("") && matkhau.equals("") && chucvu.equals("---Chọn---")){
                    JOptionPane.showMessageDialog(null, "Chưa nhập thông tin!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }else if (id.equals("")) {
                    JOptionPane.showMessageDialog(null, "Chưa nhập ID!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }else if (tengiaovien.equals("")) {
                    JOptionPane.showMessageDialog(null, "Chưa nhập tên giáo viên!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }else if (matkhau.equals("")) {
                    JOptionPane.showMessageDialog(null, "Chưa nhập mật khẩu!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }else if (chucvu.equals("---Chọn---")) {
                    JOptionPane.showMessageDialog(null, "Chưa chọn chức vụ!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }else {
                    

                    Statement dd = conn.createStatement();
                    dd.executeUpdate("insert into taikhoan values"
                            + " (" + id + ", N'" + tengiaovien + "' , N'" + chucvu + "' , '" + matkhau + "', 'CKH' )");
                    JOptionPane.showMessageDialog(null, "Tài khoản đã được lưu vào hệ thống chờ kiểm duyệt!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    Them_Tai_Khoan.this.setVisible(false);
                    new Quan_Ly_Tai_Khoan_Admin().setVisible(true);
                }

            } catch (Exception ex) {
                System.out.print(ex.toString());
            }            
        }      
    }
    
//    public int BatLoiKyTu(String s){
//        Pattern p = Pattern.compile("[!@#$%&*()_+=|<>?{}\\\\[\\\\]~-]");
//        Matcher m = p.matcher(s);
//        boolean b = m.find();
//        if (b == true) {
//            JOptionPane.showMessageDialog(null, "Không được nhập ký tự đặc biệt!", "Thông báo", JOptionPane.ERROR_MESSAGE);
//        }
//        return 0;
//    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        button_TaoTaiKhoan = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        textbox_TaoTaiKhoan_MatKhau = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        textbox_TaoTaiKhoan_NhapLaiMatKhau1 = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        textbox_TaoTaiKhoan_TenGiaoVien = new javax.swing.JTextField();
        txt_chucvuhientai = new javax.swing.JComboBox<>();
        txt_idgv = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Phần mềm quản lý cơ sở vật chất");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        button_TaoTaiKhoan.setBackground(java.awt.Color.lightGray);
        button_TaoTaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-add-user-male-24.png"))); // NOI18N
        button_TaoTaiKhoan.setText("Tạo Tài Khoản");
        button_TaoTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button_TaoTaiKhoanMouseClicked(evt);
            }
        });
        button_TaoTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_TaoTaiKhoanActionPerformed(evt);
            }
        });

        jLabel6.setText("Mật khẩu");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-add-user-male-52.png"))); // NOI18N
        jLabel7.setText("TẠO TÀI KHOẢN");

        jLabel2.setText("ID Giáo viên");

        jLabel4.setText("Tên giáo viên");

        jLabel5.setText("Nhập lại mật khẩu");

        jLabel8.setText("Chức vụ");

        txt_chucvuhientai.setBackground(java.awt.Color.white);
        txt_chucvuhientai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Chọn---", "Giáo viên", "Quản lý" }));
        txt_chucvuhientai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_chucvuhientaiActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-go-back-24.png"))); // NOI18N
        jLabel1.setText("Trở về");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel8))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(textbox_TaoTaiKhoan_NhapLaiMatKhau1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(txt_chucvuhientai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(button_TaoTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(textbox_TaoTaiKhoan_TenGiaoVien)
                                    .addComponent(textbox_TaoTaiKhoan_MatKhau)
                                    .addComponent(txt_idgv, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_idgv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textbox_TaoTaiKhoan_TenGiaoVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textbox_TaoTaiKhoan_MatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textbox_TaoTaiKhoan_NhapLaiMatKhau1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_chucvuhientai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_TaoTaiKhoan)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void button_TaoTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_TaoTaiKhoanMouseClicked
        // TODO add your handling code here:
        TaoTaiKhoan();
        a.BatLoiKyTu(textbox_TaoTaiKhoan_TenGiaoVien.getText());
        a.BatLoiKyTu(txt_idgv.getText());
        a.BatLoiKyTu(textbox_TaoTaiKhoan_MatKhau.getText());
    }//GEN-LAST:event_button_TaoTaiKhoanMouseClicked

    private void button_TaoTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_TaoTaiKhoanActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_button_TaoTaiKhoanActionPerformed

    private void txt_chucvuhientaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_chucvuhientaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_chucvuhientaiActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        Them_Tai_Khoan.this.setVisible(false);
        new Quan_Ly_Tai_Khoan_Admin().setVisible(true);
    }//GEN-LAST:event_jLabel1MouseClicked

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
            java.util.logging.Logger.getLogger(Them_Tai_Khoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Them_Tai_Khoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Them_Tai_Khoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Them_Tai_Khoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Them_Tai_Khoan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_TaoTaiKhoan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPasswordField textbox_TaoTaiKhoan_MatKhau;
    private javax.swing.JPasswordField textbox_TaoTaiKhoan_NhapLaiMatKhau1;
    private javax.swing.JTextField textbox_TaoTaiKhoan_TenGiaoVien;
    private javax.swing.JComboBox<String> txt_chucvuhientai;
    private javax.swing.JTextField txt_idgv;
    // End of variables declaration//GEN-END:variables
}
