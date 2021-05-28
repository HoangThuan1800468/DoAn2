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

/**
 *
 * @author lyhoa
 */
public class Kiem_Ke_CSVC extends javax.swing.JFrame {

    
    public Kiem_Ke_CSVC() {
        initComponents();
        TaiDuLieuKiemKe();
        TaiDuLieuKiemKe2();
    }
    public void TaiDuLieuKiemKe(){
        DefaultTableModel tbn = (DefaultTableModel)table_DanhSachCacDotChuaKiemKe.getModel();
       try{
            Connect a = new Connect();
            Connection conn = a.getConnection();
            if (conn!=null){
                Statement dd = conn.createStatement();            
                 ResultSet  kq = dd.executeQuery("select makiemke,maphong,tengiaovien,ngaykiemke from kiemke where tinhtrangkiemduyet = 0");
                 while(kq.next()){
                    String makiemke= kq.getString(1);
                    String maphong = kq.getString(2);
                    String tengiaovien=kq.getString(3);
                    String ngaykiemke=kq.getString(4);
                    
                    String str[] = ngaykiemke.toString().split("-");
                    String ngaykiemke1=str[2]+"-"+str[1]+"-"+str[0];
                    
                    tbn.addRow(new Object[]{makiemke, maphong,tengiaovien,ngaykiemke1});
                }
                dd.close();
                conn.close();
            }
                      
        }catch(Exception ex){
            System.out.print(ex.toString());
        }
    }
    public void TaiDuLieuKiemKe2(){
         DefaultTableModel tbn2 = (DefaultTableModel)table_DanhSachCacDoDaKiemKe.getModel();
         try{
            Connect a = new Connect();
            Connection conn = a.getConnection();
            if (conn!=null){
                Statement dd = conn.createStatement();            
                 ResultSet  kq = dd.executeQuery("select makiemke,maphong,tengiaovien,ngaykiemke from kiemke where tinhtrangkiemduyet = 1");
                 while(kq.next()){
                    String makiemke= kq.getString(1);
                    String maphong = kq.getString(2);
                    String tengiaovien=kq.getString(3);
                    String ngaykiemke=kq.getString(4);
                    
                    String str[] = ngaykiemke.toString().split("-");
                    String ngaykiemke1=str[2]+"-"+str[1]+"-"+str[0];
                    
                    tbn2.addRow(new Object[]{makiemke, maphong,tengiaovien,ngaykiemke1});
                }
                dd.close();
                conn.close();
            }
                      
        }catch(Exception ex){
            System.out.print(ex.toString());
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_DanhSachCacDotChuaKiemKe = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        button_TaoBienBanKiemKeMoi = new javax.swing.JButton();
        button_KiemDuyetThongTinKiemKe = new javax.swing.JButton();
        label_TroVeTrangChu = new javax.swing.JLabel();
        button_XoaThongTinKiemKe = new javax.swing.JButton();
        button_ChinhSuaThongTinKiemKe = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_DanhSachCacDoDaKiemKe = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        button_thongtinbienbandakiemduyet = new javax.swing.JButton();
        button_XoaThongTinKiemKe2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Phần mềm quản lý cơ sở vật chất");
        setLocation(new java.awt.Point(600, 300));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-warehouse-50.png"))); // NOI18N
        jLabel1.setText("KIỂM KÊ CƠ SỞ VẬT CHẤT");

        table_DanhSachCacDotChuaKiemKe.setBackground(java.awt.Color.white);
        table_DanhSachCacDotChuaKiemKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Phòng", "Người kiểm kê", "Ngày kiểm kê"
            }
        ));
        jScrollPane1.setViewportView(table_DanhSachCacDotChuaKiemKe);

        jLabel2.setText("Danh sách biên bản kiểm kê chưa kiểm duyệt");

        button_TaoBienBanKiemKeMoi.setBackground(java.awt.Color.lightGray);
        button_TaoBienBanKiemKeMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-add-list-24.png"))); // NOI18N
        button_TaoBienBanKiemKeMoi.setText("Tạo biên bản kiểm kê mới");
        button_TaoBienBanKiemKeMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_TaoBienBanKiemKeMoiActionPerformed(evt);
            }
        });

        button_KiemDuyetThongTinKiemKe.setBackground(java.awt.Color.lightGray);
        button_KiemDuyetThongTinKiemKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-check-all-24.png"))); // NOI18N
        button_KiemDuyetThongTinKiemKe.setText("Kiểm duyệt biên bản");
        button_KiemDuyetThongTinKiemKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_KiemDuyetThongTinKiemKeActionPerformed(evt);
            }
        });

        label_TroVeTrangChu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-go-back-24.png"))); // NOI18N
        label_TroVeTrangChu.setText("Trở về");
        label_TroVeTrangChu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_TroVeTrangChuMouseClicked(evt);
            }
        });

        button_XoaThongTinKiemKe.setBackground(java.awt.Color.lightGray);
        button_XoaThongTinKiemKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-delete-bin-24.png"))); // NOI18N
        button_XoaThongTinKiemKe.setText("Xóa biên bản kiểm kê chưa kiểm duyệt");
        button_XoaThongTinKiemKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_XoaThongTinKiemKeActionPerformed(evt);
            }
        });

        button_ChinhSuaThongTinKiemKe.setBackground(java.awt.Color.lightGray);
        button_ChinhSuaThongTinKiemKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-edit-24.png"))); // NOI18N
        button_ChinhSuaThongTinKiemKe.setText("Chỉnh sửa biên bản chưa kiểm duyệt");
        button_ChinhSuaThongTinKiemKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ChinhSuaThongTinKiemKeActionPerformed(evt);
            }
        });

        jLabel3.setText("Thao tác");

        table_DanhSachCacDoDaKiemKe.setBackground(java.awt.Color.white);
        table_DanhSachCacDoDaKiemKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Phòng", "Người kiểm kê", "Ngày kiểm kê"
            }
        ));
        jScrollPane2.setViewportView(table_DanhSachCacDoDaKiemKe);

        jLabel4.setText("Danh sách biên bản kiểm kê đã kiểm duyệt");

        jLabel5.setText("----------------------------------------------------------------");

        button_thongtinbienbandakiemduyet.setBackground(java.awt.Color.lightGray);
        button_thongtinbienbandakiemduyet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-information-24.png"))); // NOI18N
        button_thongtinbienbandakiemduyet.setText("Thông tin biên bản đã kiểm duyệt");
        button_thongtinbienbandakiemduyet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_thongtinbienbandakiemduyetActionPerformed(evt);
            }
        });

        button_XoaThongTinKiemKe2.setBackground(java.awt.Color.lightGray);
        button_XoaThongTinKiemKe2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-delete-bin-24.png"))); // NOI18N
        button_XoaThongTinKiemKe2.setText("Xóa biên bản đã kiểm duyệt");
        button_XoaThongTinKiemKe2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_XoaThongTinKiemKe2ActionPerformed(evt);
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
                        .addComponent(jLabel2)
                        .addGap(465, 465, 465))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(button_TaoBienBanKiemKeMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(button_KiemDuyetThongTinKiemKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(button_ChinhSuaThongTinKiemKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(button_XoaThongTinKiemKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(button_thongtinbienbandakiemduyet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(button_XoaThongTinKiemKe2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(14, 14, 14))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_TroVeTrangChu)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(321, 321, 321)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(button_TaoBienBanKiemKeMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(button_KiemDuyetThongTinKiemKe, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(button_ChinhSuaThongTinKiemKe, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button_XoaThongTinKiemKe, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(button_thongtinbienbandakiemduyet, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_XoaThongTinKiemKe2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_TroVeTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void label_TroVeTrangChuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_TroVeTrangChuMouseClicked
        // TODO add your handling code here:
        Kiem_Ke_CSVC.this.setVisible(false);
        new Menu_Quan_Ly().setVisible(true);
    }//GEN-LAST:event_label_TroVeTrangChuMouseClicked

    private void button_TaoBienBanKiemKeMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_TaoBienBanKiemKeMoiActionPerformed
        // TODO add your handling code here:
        Kiem_Ke_CSVC.this.setVisible(false);
       new Tao_Bien_Ban_Kiem_Ke_Moi().setVisible(true);
    }//GEN-LAST:event_button_TaoBienBanKiemKeMoiActionPerformed

    private void button_ChinhSuaThongTinKiemKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ChinhSuaThongTinKiemKeActionPerformed
        // TODO add your handling code here:
        try{
        int Row = table_DanhSachCacDotChuaKiemKe.getSelectedRow();
        String makiemke = (String) table_DanhSachCacDotChuaKiemKe.getValueAt(Row,0);
        System.out.print(makiemke);
        Kiem_Ke_CSVC.this.setVisible(false);
        new Chinh_Sua_Thong_Tin_Kiem_Ke(makiemke).setVisible(true);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Chưa chọn biên bản kiểm kê!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_button_ChinhSuaThongTinKiemKeActionPerformed

    private void button_KiemDuyetThongTinKiemKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_KiemDuyetThongTinKiemKeActionPerformed
        // TODO add your handling code here:
        try{
        int Row = table_DanhSachCacDotChuaKiemKe.getSelectedRow();
        String makkchuaduyet = (String) table_DanhSachCacDotChuaKiemKe.getValueAt(Row,0);
        Kiem_Ke_CSVC.this.setVisible(false);
        new Kiem_Duyet_Thong_Tin_Kiem_Ke(makkchuaduyet).setVisible(true);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Chưa chọn biên bản kiểm kê chưa kiểm duyệt!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_button_KiemDuyetThongTinKiemKeActionPerformed

    private void button_XoaThongTinKiemKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_XoaThongTinKiemKeActionPerformed
        // TODO add your handling code here:
         DefaultTableModel tbn = (DefaultTableModel)table_DanhSachCacDotChuaKiemKe.getModel();
        try{
            int Row = table_DanhSachCacDotChuaKiemKe.getSelectedRow();
            String makkchuaduyet = (String) table_DanhSachCacDotChuaKiemKe.getValueAt(Row,0);   
            String maphong = (String) table_DanhSachCacDotChuaKiemKe.getValueAt(Row,1);
            
            if (JOptionPane.showConfirmDialog(this, "Xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                Connect a = new Connect();
                Connection conn = a.getConnection();
                Statement dd = conn.createStatement();
                dd.executeUpdate("delete from kiemke_csvcphong where makiemke like '"+makkchuaduyet+"'");
                dd.executeUpdate("delete from kiemke where makiemke like '"+makkchuaduyet+"'");
                dd.executeUpdate("update phonghoc set tinhtrangkiemkecuaphong = 0 where maphong like '"+maphong+"'");
                tbn.setRowCount(0);
                TaiDuLieuKiemKe();
                JOptionPane.showMessageDialog(null,"Đã xóa!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
            }
            
        }catch (Exception ex){
            System.out.print(ex);
            JOptionPane.showMessageDialog(null,"Không thành công!","Thông báo",JOptionPane.ERROR_MESSAGE);
        }
        
        
    }//GEN-LAST:event_button_XoaThongTinKiemKeActionPerformed

    private void button_thongtinbienbandakiemduyetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_thongtinbienbandakiemduyetActionPerformed
        // TODO add your handling code here:
        try{
        int Row = table_DanhSachCacDoDaKiemKe.getSelectedRow();
        String makkchuaduyet = (String) table_DanhSachCacDoDaKiemKe.getValueAt(Row,0);
        Kiem_Ke_CSVC.this.setVisible(false);
        new Thong_Tin_Bien_Ban_Kiem_Ke_DaKD(makkchuaduyet).setVisible(true);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Chưa chọn biên bản kiểm kê đã kiểm duyệt!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_button_thongtinbienbandakiemduyetActionPerformed

    private void button_XoaThongTinKiemKe2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_XoaThongTinKiemKe2ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tbn = (DefaultTableModel)table_DanhSachCacDoDaKiemKe.getModel();
        try{
            int Row = table_DanhSachCacDoDaKiemKe.getSelectedRow();
            String makkchuaduyet = (String) table_DanhSachCacDoDaKiemKe.getValueAt(Row,0);            
            
            if (JOptionPane.showConfirmDialog(this, "Xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                Connect a = new Connect();
                Connection conn = a.getConnection();
                Statement dd = conn.createStatement();
                dd.executeUpdate("delete from kiemke_csvcphong where makiemke like '"+makkchuaduyet+"'");
                dd.executeUpdate("delete from kiemke where makiemke like '"+makkchuaduyet+"'");
                tbn.setRowCount(0);
                TaiDuLieuKiemKe2();
                JOptionPane.showMessageDialog(null,"Đã xóa!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
            }
            
        }catch (Exception ex){
            System.out.print(ex);
            JOptionPane.showMessageDialog(null,"Không thành công!","Thông báo",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_button_XoaThongTinKiemKe2ActionPerformed

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
            java.util.logging.Logger.getLogger(Kiem_Ke_CSVC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Kiem_Ke_CSVC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Kiem_Ke_CSVC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Kiem_Ke_CSVC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Kiem_Ke_CSVC().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_ChinhSuaThongTinKiemKe;
    private javax.swing.JButton button_KiemDuyetThongTinKiemKe;
    private javax.swing.JButton button_TaoBienBanKiemKeMoi;
    private javax.swing.JButton button_XoaThongTinKiemKe;
    private javax.swing.JButton button_XoaThongTinKiemKe2;
    private javax.swing.JButton button_thongtinbienbandakiemduyet;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel label_TroVeTrangChu;
    private javax.swing.JTable table_DanhSachCacDoDaKiemKe;
    private javax.swing.JTable table_DanhSachCacDotChuaKiemKe;
    // End of variables declaration//GEN-END:variables
}
