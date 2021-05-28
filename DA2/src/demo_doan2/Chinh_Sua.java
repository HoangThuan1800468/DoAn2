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
public class Chinh_Sua extends javax.swing.JFrame {

    Connect a = new Connect();
    Connection conn = a.getConnection();
    public Chinh_Sua(String tbtk) {
        initComponents();
        TaiDuLieuCN(tbtk);
    }

    private Chinh_Sua() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void TaiDuLieuCN(String tbtk){        
        try{            
            Statement dd = conn.createStatement();
            ResultSet rs = dd.executeQuery("select idgiaovien, tengiaovien,chucvu, matkhau from taikhoan where idgiaovien like '" + tbtk + "'");
            while (rs.next()) {
                String id = rs.getString(1);
                String te = rs.getString(2);
                String cv = rs.getString(3);
                String mk = rs.getString(4);

                textbox_CNTaiKhoan_IDGiaoVien.setText(id);
                textbox_CNTaiKhoan_TenGiaoVien.setText(te);
                txt_chucvuhientai.setSelectedItem(cv);
                textbox_CNTaiKhoan_MatKhau.setText(mk);
            }    
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Không thành công!","Thông báo",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void CNTaiKhoan(){
        String id = textbox_CNTaiKhoan_IDGiaoVien.getText();
        String mk1 = textbox_CNTaiKhoan_MatKhau.getText();
        String mk2 = textbox_CNTaiKhoan_NhapLaiMatKhau.getText();
        if (mk1.equals(mk2) != true){
            JOptionPane.showMessageDialog(null,"Nhập lại mật khẩu không khớp!","Thông báo",JOptionPane.ERROR_MESSAGE);
        }
        else{
            String chucvu = txt_chucvuhientai.getSelectedItem().toString();
            String tengiaovien = textbox_CNTaiKhoan_TenGiaoVien.getText();
            try{
                if (chucvu.equals("---Chọn---")) {
                    JOptionPane.showMessageDialog(null, "Chưa chọn chức vụ!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                } else if (tengiaovien.equals("")) {
                    JOptionPane.showMessageDialog(null, "Chưa nhập tên giáo viên!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                } else if (mk1.equals("")) {
                    JOptionPane.showMessageDialog(null, "Chưa nhập mật khẩu!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                } else{
                    PreparedStatement dd = conn.prepareStatement("update taikhoan set idgiaovien=?, tengiaovien=?, chucvu=?, matkhau=? where idgiaovien like '" + id + "'");
                    dd.setString(1, textbox_CNTaiKhoan_IDGiaoVien.getText());
                    dd.setString(2, textbox_CNTaiKhoan_TenGiaoVien.getText());
                    dd.setString(3, txt_chucvuhientai.getSelectedItem().toString());
                    dd.setString(4, textbox_CNTaiKhoan_MatKhau.getText());
                    dd.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Đã chỉnh sửa!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    Chinh_Sua.this.setVisible(false);
                    new Quan_Ly_Tai_Khoan_Admin().setVisible(true);
                }
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Không thành công!","Thông báo",JOptionPane.ERROR_MESSAGE);
            }
        }       
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        textbox_CNTaiKhoan_MatKhau = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        textbox_CNTaiKhoan_IDGiaoVien = new javax.swing.JTextField();
        button_CNTaiKhoan = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        textbox_CNTaiKhoan_TenGiaoVien = new javax.swing.JTextField();
        txt_chucvuhientai = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        textbox_CNTaiKhoan_NhapLaiMatKhau = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Phần mềm quản lý cơ sở vật chất");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel6.setText("Mật khẩu");

        textbox_CNTaiKhoan_MatKhau.setText("111111111");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-edit-24.png"))); // NOI18N
        jLabel7.setText("CHỈNH SỬA TÀI KHOẢN");

        jLabel2.setText("ID Giáo viên");

        jLabel4.setText("Tên giáo viên");

        textbox_CNTaiKhoan_IDGiaoVien.setEnabled(false);

        button_CNTaiKhoan.setBackground(java.awt.Color.lightGray);
        button_CNTaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-edit-24.png"))); // NOI18N
        button_CNTaiKhoan.setText("Chỉnh sửa");
        button_CNTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button_CNTaiKhoanMouseClicked(evt);
            }
        });
        button_CNTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_CNTaiKhoanActionPerformed(evt);
            }
        });

        jLabel8.setText("Nhập lại mật khẩu");

        jLabel9.setText("Chức vụ");

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

        textbox_CNTaiKhoan_NhapLaiMatKhau.setText("111111111");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(82, 82, 82))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(textbox_CNTaiKhoan_IDGiaoVien, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                                    .addComponent(textbox_CNTaiKhoan_MatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                                    .addComponent(textbox_CNTaiKhoan_TenGiaoVien, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                                    .addComponent(textbox_CNTaiKhoan_NhapLaiMatKhau))
                                .addComponent(txt_chucvuhientai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(button_CNTaiKhoan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel6)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textbox_CNTaiKhoan_IDGiaoVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textbox_CNTaiKhoan_TenGiaoVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textbox_CNTaiKhoan_MatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textbox_CNTaiKhoan_NhapLaiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_chucvuhientai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button_CNTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void button_CNTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_CNTaiKhoanMouseClicked
        // TODO add your handling code here:
        CNTaiKhoan();
    }//GEN-LAST:event_button_CNTaiKhoanMouseClicked

    private void button_CNTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_CNTaiKhoanActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_button_CNTaiKhoanActionPerformed

    private void txt_chucvuhientaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_chucvuhientaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_chucvuhientaiActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        Chinh_Sua.this.setVisible(false);
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
            java.util.logging.Logger.getLogger(Chinh_Sua.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Chinh_Sua.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Chinh_Sua.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Chinh_Sua.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Chinh_Sua().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_CNTaiKhoan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField textbox_CNTaiKhoan_IDGiaoVien;
    private javax.swing.JPasswordField textbox_CNTaiKhoan_MatKhau;
    private javax.swing.JPasswordField textbox_CNTaiKhoan_NhapLaiMatKhau;
    private javax.swing.JTextField textbox_CNTaiKhoan_TenGiaoVien;
    private javax.swing.JComboBox<String> txt_chucvuhientai;
    // End of variables declaration//GEN-END:variables
}
