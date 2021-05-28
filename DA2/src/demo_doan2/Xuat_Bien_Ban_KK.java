/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo_doan2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lyhoa
 */
public class Xuat_Bien_Ban_KK extends javax.swing.JFrame {

    /**
     * Creates new form Xuat_Bien_Ban_KK
     */
    public Xuat_Bien_Ban_KK() {
        initComponents();
        TaiDuLieuKiemKe();
    }

    public void TaiDuLieuKiemKe(){
        DefaultTableModel tbn = (DefaultTableModel)table_DanhSachCacDotChuaKiemKe.getModel();
       try{
            Connect a = new Connect();
            Connection conn = a.getConnection();
            if (conn!=null){
                Statement dd = conn.createStatement();            
                 ResultSet  kq = dd.executeQuery("select makiemke,maphong,tinhtrangphong,tengiaovien,ngaykiemke from kiemke where tinhtrangkiemduyet like 0 ");
                 while(kq.next()){
                    String makiemke= kq.getString(1);
                    String maphong = kq.getString(2);
                    String tinhtrangphong=kq.getString(3);
                    String tengiaovien=kq.getString(4);
                    String ngaykiemke = kq.getString(5);
                    String str[] = ngaykiemke.toString().split("-");
                    String ngaykiemke1=str[2]+"-"+str[1]+"-"+str[0];
                    tbn.addRow(new Object[]{makiemke, maphong,tinhtrangphong,tengiaovien,ngaykiemke1});
                }
                dd.close();
                conn.close();
            }
                      
        }catch(Exception ex){
            System.out.print(ex.toString());
        }
    }
    public boolean ktmaxuatfile(String makiemke){
        boolean kt=false;
        String mavb="";
        try{
            Connect a = new Connect();
            Connection conn = a.getConnection();
            
                Statement dd = conn.createStatement();            
                 ResultSet  kq = dd.executeQuery("select mavanban from xuatvanban where mavanban like '"+makiemke+"' ");
                 while(kq.next()){
                     mavb= kq.getString(1);
                }
                 
                if (mavb.equals("")==true){
                    kt=true;
                }else{
                    kt=false;
                }
            
                      
        }catch(Exception ex){
            kt=false;
            System.out.print(ex.toString());
        }
        return kt;
    }
    public void xuatfile(String makiemke){
        String tengiaovien,chucvu,maphong,tinhtrangphong,ngaykiemke,id;
        
        try{
            Connect a = new Connect();
            Connection conn = a.getConnection();
            

            Statement dd = conn.createStatement();            
                 ResultSet  kq = dd.executeQuery("select tengiaovien,chucvu,maphong,tinhtrangphong,ngaykiemke,idgiaovien from kiemke where tinhtrangkiemduyet like 0 and makiemke like'"+makiemke+"'");
                 while(kq.next()){
                     tengiaovien=kq.getString(1);
                     chucvu=kq.getString(2);
                     maphong=kq.getString(3);
                     tinhtrangphong=kq.getString(4);
                     ngaykiemke=kq.getString(5);
                     id=kq.getString(6);
                     Statement de = conn.createStatement();  
                    de.executeUpdate("insert into xuatvanban (loaivanban,mavanban,ngaybaotri,nguoidaidien,idgiaovien) values (N'Biên bản kiểm kê','"+makiemke+"' , '"+ngaykiemke+"' , N'"+tengiaovien+"' , '"+id+"' )");
                 Statement da = conn.createStatement();
                 da.executeUpdate("insert into xuatvanban_kiemke (mavanban,tengiaovien,chucvu,maphong,tinhtrangphong,ngaykiemke) values ('"+makiemke+"' , N'"+tengiaovien+"' , N'"+chucvu+"' , '"+maphong+"' , N'"+tinhtrangphong+"' , '"+ngaykiemke+"'      )");
                 }
                 Statement db = conn.createStatement();
                 ResultSet  kb = db.executeQuery("select macsvc,soluong,tinhtrangcsvc,tencsvc from kiemke_csvcphong where makiemke like '"+makiemke+"'");
                 while(kb.next()){
                     String macsvc = kb.getString(1);
                     int soluong =kb.getInt(2);
                     String tinhtrangcsvc=kb.getString(3);
                     String tencsvc = kb.getString(4);
                     Statement dc = conn.createStatement();
                      dc.executeUpdate("insert into xuatvanban_kiemke_csvc(mavanban,macsvc,soluong,tinhtrangcsvc,tencsvc) values ('"+makiemke+"' , '"+macsvc+"' , '"+soluong+"' , N'"+tinhtrangcsvc+"' , N'"+tencsvc+"'       )");
                 }
                 
                 
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_DanhSachCacDotChuaKiemKe = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        label_trove = new javax.swing.JLabel();
        btn_xoa = new javax.swing.JButton();
        btn_xemthongtin = new javax.swing.JButton();
        btn_xuatfile = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Phần mềm quản lý cơ sở vật chất");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-warehouse-50.png"))); // NOI18N
        jLabel4.setText("THÔNG TIN XUẤT BIÊN BẢN KIỂM KÊ CSVC PHÒNG HỌC");

        table_DanhSachCacDotChuaKiemKe.setBackground(java.awt.Color.white);
        table_DanhSachCacDotChuaKiemKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã kiểm kê", "Phòng kiểm kê", "Tình trạng phòng", "Người đại diện", "Ngày kiểm kê"
            }
        ));
        table_DanhSachCacDotChuaKiemKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_DanhSachCacDotChuaKiemKeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_DanhSachCacDotChuaKiemKe);

        jLabel8.setText("Chọn biên bản kiểm kê chưa kiểm duyệt:");

        label_trove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-go-back-24.png"))); // NOI18N
        label_trove.setText("Trở về");
        label_trove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_troveMouseClicked(evt);
            }
        });

        btn_xoa.setBackground(java.awt.Color.lightGray);
        btn_xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-delete-bin-24.png"))); // NOI18N
        btn_xoa.setText("Xóa biên bản");
        btn_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaActionPerformed(evt);
            }
        });

        btn_xemthongtin.setBackground(java.awt.Color.lightGray);
        btn_xemthongtin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-information-24.png"))); // NOI18N
        btn_xemthongtin.setText("Xem thông tin");
        btn_xemthongtin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xemthongtinActionPerformed(evt);
            }
        });

        btn_xuatfile.setBackground(java.awt.Color.lightGray);
        btn_xuatfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-file-24.png"))); // NOI18N
        btn_xuatfile.setText("Xuất file");
        btn_xuatfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xuatfileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 663, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_xoa, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                            .addComponent(btn_xemthongtin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_xuatfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(label_trove))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_xemthongtin, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_xuatfile, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_trove, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void table_DanhSachCacDotChuaKiemKeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_DanhSachCacDotChuaKiemKeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_table_DanhSachCacDotChuaKiemKeMouseClicked

    private void label_troveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_troveMouseClicked
        // TODO add your handling code here:
        Xuat_Bien_Ban_KK.this.setVisible(false);
        new Xuat_File().setVisible(true);

    }//GEN-LAST:event_label_troveMouseClicked

    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaActionPerformed
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
        
        
    }//GEN-LAST:event_btn_xoaActionPerformed

    private void btn_xemthongtinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xemthongtinActionPerformed
        // TODO add your handling code here:
        try{
        int Row = table_DanhSachCacDotChuaKiemKe.getSelectedRow();
        String makiemke = (String) table_DanhSachCacDotChuaKiemKe.getValueAt(Row,0);
        System.out.print(makiemke);
        Xuat_Bien_Ban_KK.this.setVisible(false);
        new Xem_Thong_Tin_KK(makiemke).setVisible(true);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Chưa chọn biên bản kiểm kê!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btn_xemthongtinActionPerformed

    private void btn_xuatfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xuatfileActionPerformed
        // TODO add your handling code here:
        
        try{
        int Row = table_DanhSachCacDotChuaKiemKe.getSelectedRow();
        String makiemke = (String) table_DanhSachCacDotChuaKiemKe.getValueAt(Row,0);
        if(ktmaxuatfile(makiemke)==false){
            JOptionPane.showMessageDialog(null,"Biên bản này đã được xuất file vui lòng kiểm tra lại!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        }else{
        xuatfile(makiemke);
        Xuat_Bien_Ban_KK.this.setVisible(false);
        new Bien_Ban_Kiem_Ke(makiemke).setVisible(true);
        }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Chưa chọn biên bản kiểm kê!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btn_xuatfileActionPerformed

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
            java.util.logging.Logger.getLogger(Xuat_Bien_Ban_KK.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Xuat_Bien_Ban_KK.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Xuat_Bien_Ban_KK.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Xuat_Bien_Ban_KK.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Xuat_Bien_Ban_KK().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_xemthongtin;
    private javax.swing.JButton btn_xoa;
    private javax.swing.JButton btn_xuatfile;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_trove;
    private javax.swing.JTable table_DanhSachCacDotChuaKiemKe;
    // End of variables declaration//GEN-END:variables
}
