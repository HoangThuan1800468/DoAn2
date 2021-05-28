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
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
//Lê Hải Dâng
//DangHT
/**
 *
 * @author lyhoa
 */
public class Quan_Ly_Tai_Khoan_Admin extends javax.swing.JFrame {

    Connect a = new Connect();
    Connection conn = a.getConnection();
    DefaultTableModel tbn = new DefaultTableModel();
    public Quan_Ly_Tai_Khoan_Admin() {
        initComponents();
        TaiKhoan();
    }

    public void TaiKhoan(){     /// TẢI DỮ LIỆU LÊN BẢNG
        try {
            int so;
            Vector dong, cot;
            cot = new Vector();           
            Statement dd = conn.createStatement();
            ResultSet rs = dd.executeQuery("Select idgiaovien, tengiaovien, kichhoat from taikhoan");
            ResultSetMetaData meta = rs.getMetaData();
            so = meta.getColumnCount();
            for (int i = 1; i <= so; i++){
                cot.add(meta.getColumnName(i));
            }
            tbn.setColumnIdentifiers(cot);
            
            while (rs.next()){
                dong = new Vector();
                for (int i = 1; i <= so; i++){
                    dong.addElement(rs.getString(i));
                }
                tbn.addRow(dong);
                table_TaiKhoan.setModel(tbn);
            }
        } catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Không thành công!","Thông báo",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void KichHoat(){   ///KÍCH HOẠT TÀI KHOẢN
        try{
            int Row = table_TaiKhoan.getSelectedRow();
            String macsvcthongtin = (String) table_TaiKhoan.getValueAt(Row,0);
            Statement dd = conn.createStatement();
            ResultSet rs = dd.executeQuery("Select kichhoat from taikhoan where idgiaovien like '"+macsvcthongtin+"'   ");
            String kh = "";
            while (rs.next()){
                    kh = rs.getString(1); 
                if (kh.equals("DKH")) {
                    JOptionPane.showMessageDialog(null, "Tài khoản đã được kích hoạt!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    PreparedStatement pr = conn.prepareStatement("update taikhoan set kichhoat=? where idgiaovien=?");
                    pr.setString(2, table_TaiKhoan.getValueAt(table_TaiKhoan.getSelectedRow(), 0).toString());
                    if (JOptionPane.showConfirmDialog(this, "Kích hoạt?", "Xác nhận", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        pr.setString(1, "DKH");
                        pr.executeUpdate();
                        tbn.setRowCount(0);
                        TaiKhoan();
                        JOptionPane.showMessageDialog(null, "Đã kích hoạt!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                }
           
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Chưa chọn tài khoản!","Thông báo",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void Xoa(){    ////XÓA TÀI KHOẢN
        try{
            PreparedStatement pr = conn.prepareStatement("delete from taikhoan where idgiaovien=?");
            pr.setString(1, table_TaiKhoan.getValueAt(table_TaiKhoan.getSelectedRow(), 0).toString());
            if (JOptionPane.showConfirmDialog(this, "Xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                pr.executeUpdate();
                tbn.setRowCount(0);
                TaiKhoan();
                JOptionPane.showMessageDialog(null,"Đã xóa!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
            }
            
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,"Chưa chọn tài khoản!","Thông báo",JOptionPane.ERROR_MESSAGE);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_TaiKhoan = new javax.swing.JTable();
        button_KichHoatTaiKhoan = new javax.swing.JButton();
        button_Xoa = new javax.swing.JButton();
        button_ChinhSua = new javax.swing.JButton();
        button_ThemTaiKhoan = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        label_TroVeTrangChu = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Phần mềm quản lý cơ sở vật chất");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel1.setText("Danh sách tài khoản:");

        table_TaiKhoan.setBackground(java.awt.Color.white);
        table_TaiKhoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(table_TaiKhoan);

        button_KichHoatTaiKhoan.setBackground(java.awt.Color.lightGray);
        button_KichHoatTaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-check-all-24.png"))); // NOI18N
        button_KichHoatTaiKhoan.setText("Kích hoạt tài khoản");
        button_KichHoatTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_KichHoatTaiKhoanActionPerformed(evt);
            }
        });

        button_Xoa.setBackground(java.awt.Color.lightGray);
        button_Xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-delete-bin-24.png"))); // NOI18N
        button_Xoa.setText("Xóa");
        button_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_XoaActionPerformed(evt);
            }
        });

        button_ChinhSua.setBackground(java.awt.Color.lightGray);
        button_ChinhSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-edit-24.png"))); // NOI18N
        button_ChinhSua.setText("Chỉnh sửa");
        button_ChinhSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ChinhSuaActionPerformed(evt);
            }
        });

        button_ThemTaiKhoan.setBackground(java.awt.Color.lightGray);
        button_ThemTaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-add-column-24.png"))); // NOI18N
        button_ThemTaiKhoan.setText("Thêm tài khoản");
        button_ThemTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ThemTaiKhoanActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 25)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-account-48.png"))); // NOI18N
        jLabel2.setText("QUẢN LÝ TÀI KHOẢN");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel3.setText("Chức năng");

        label_TroVeTrangChu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-go-back-24.png"))); // NOI18N
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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(button_ChinhSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(button_ThemTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                                .addComponent(button_Xoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(button_KichHoatTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel3)))
                    .addComponent(label_TroVeTrangChu)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(100, 100, 100)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(button_KichHoatTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(button_ChinhSua, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(button_ThemTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_TroVeTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void button_KichHoatTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_KichHoatTaiKhoanActionPerformed

        KichHoat();
    }//GEN-LAST:event_button_KichHoatTaiKhoanActionPerformed

    private void button_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_XoaActionPerformed
        // TODO add your handling code here:
        Xoa();
    }//GEN-LAST:event_button_XoaActionPerformed

    private void button_ChinhSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ChinhSuaActionPerformed
        // TODO add your handling code here:
        try{
        int Row = table_TaiKhoan.getSelectedRow();
        String tbtk = (String) table_TaiKhoan.getValueAt(Row,0);
        Quan_Ly_Tai_Khoan_Admin.this.setVisible(false);
        new Chinh_Sua(tbtk).setVisible(true);
        }
        catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Chưa chọn tài khoản!","Thông báo",JOptionPane.ERROR_MESSAGE);
                }
  
    }//GEN-LAST:event_button_ChinhSuaActionPerformed

    private void button_ThemTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ThemTaiKhoanActionPerformed
        // TODO add your handling code here:
        Quan_Ly_Tai_Khoan_Admin.this.setVisible(false);
        new Them_Tai_Khoan().setVisible(true);
    }//GEN-LAST:event_button_ThemTaiKhoanActionPerformed

    private void label_TroVeTrangChuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_TroVeTrangChuMouseClicked
        // TODO add your handling code here:
        Quan_Ly_Tai_Khoan_Admin.this.setVisible(false); new Tai_Khoan().setVisible(true);
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
            java.util.logging.Logger.getLogger(Quan_Ly_Tai_Khoan_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Quan_Ly_Tai_Khoan_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Quan_Ly_Tai_Khoan_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Quan_Ly_Tai_Khoan_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Quan_Ly_Tai_Khoan_Admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_ChinhSua;
    private javax.swing.JButton button_KichHoatTaiKhoan;
    private javax.swing.JButton button_ThemTaiKhoan;
    private javax.swing.JButton button_Xoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_TroVeTrangChu;
    private javax.swing.JTable table_TaiKhoan;
    // End of variables declaration//GEN-END:variables
}
