
package demo_doan2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Quan_Ly_Phong_Ban extends javax.swing.JFrame {
    
    public Quan_Ly_Phong_Ban() {
        initComponents();
        loadData();       
    }
    
    
    public void loadData(){
        
        DefaultTableModel model = (DefaultTableModel)table_DanhSachPhong.getModel();
        try{
            Connect a = new Connect();
            Connection conn = a.getConnection();
            if (conn!=null){
                Statement dd = conn.createStatement();            
                 ResultSet  kq = dd.executeQuery("select tenphong,maphong,tinhtrangphong from phonghoc");
                 while(kq.next()){
                    String tenphong= kq.getString(1);
                    String maphong = kq.getString(2);
                    String tinhtrangphong=kq.getString(3);
                    model.addRow(new Object[]{tenphong, maphong,tinhtrangphong});
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

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_DanhSachPhong = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_DanhSachCoSoVatChat = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        button_XoáCVC = new javax.swing.JButton();
        button_ThemPhong = new javax.swing.JButton();
        button_XoaPhong = new javax.swing.JButton();
        button_CapNhatThongTinPhong = new javax.swing.JButton();
        button_DiChuyenCSVC = new javax.swing.JButton();
        button_ThongTinCSVC = new javax.swing.JButton();
        label_TroVeTrangChu = new javax.swing.JLabel();
        button_ThongTinPhong = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Phần mềm quản lý cơ sở vật chất");
        setLocation(new java.awt.Point(500, 250));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 25)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-room-50.png"))); // NOI18N
        jLabel2.setText("QUẢN LÝ CƠ SỞ VẬT CHẤT PHÒNG HỌC");

        jLabel1.setText("Danh sách phòng");

        table_DanhSachPhong.setBackground(java.awt.Color.white);
        table_DanhSachPhong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên phòng", "Mã phòng", "Tình trạng "
            }
        ));
        table_DanhSachPhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_DanhSachPhongMouseClicked(evt);
            }
        });
        table_DanhSachPhong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                table_DanhSachPhongKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(table_DanhSachPhong);

        table_DanhSachCoSoVatChat.setBackground(java.awt.Color.white);
        table_DanhSachCoSoVatChat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên CSVC", "Mã CSVC", "Số lượng", "Tình trạng"
            }
        ));
        jScrollPane2.setViewportView(table_DanhSachCoSoVatChat);

        jLabel3.setText("Danh sách cở sở vật chất của phòng");

        button_XoáCVC.setBackground(java.awt.Color.lightGray);
        button_XoáCVC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-information-24.png"))); // NOI18N
        button_XoáCVC.setText("Xóa CSVC");
        button_XoáCVC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_XoáCVCActionPerformed(evt);
            }
        });

        button_ThemPhong.setBackground(java.awt.Color.lightGray);
        button_ThemPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-add-list-24.png"))); // NOI18N
        button_ThemPhong.setText("Thêm phòng");
        button_ThemPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ThemPhongActionPerformed(evt);
            }
        });

        button_XoaPhong.setBackground(java.awt.Color.lightGray);
        button_XoaPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-delete-bin-24.png"))); // NOI18N
        button_XoaPhong.setText("Xóa phòng");
        button_XoaPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_XoaPhongActionPerformed(evt);
            }
        });

        button_CapNhatThongTinPhong.setBackground(java.awt.Color.lightGray);
        button_CapNhatThongTinPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-update-24.png"))); // NOI18N
        button_CapNhatThongTinPhong.setText("Cập nhật thông tin phòng");
        button_CapNhatThongTinPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_CapNhatThongTinPhongActionPerformed(evt);
            }
        });

        button_DiChuyenCSVC.setBackground(java.awt.Color.lightGray);
        button_DiChuyenCSVC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-update-left-rotation-24.png"))); // NOI18N
        button_DiChuyenCSVC.setText("Di chuyển CSVC");
        button_DiChuyenCSVC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_DiChuyenCSVCActionPerformed(evt);
            }
        });

        button_ThongTinCSVC.setBackground(java.awt.Color.lightGray);
        button_ThongTinCSVC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-information-24.png"))); // NOI18N
        button_ThongTinCSVC.setText("Thông tin CSVC");
        button_ThongTinCSVC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ThongTinCSVCActionPerformed(evt);
            }
        });

        label_TroVeTrangChu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-go-back-24.png"))); // NOI18N
        label_TroVeTrangChu.setText(" Trở về ");
        label_TroVeTrangChu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_TroVeTrangChuMouseClicked(evt);
            }
        });

        button_ThongTinPhong.setBackground(java.awt.Color.lightGray);
        button_ThongTinPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-information-24.png"))); // NOI18N
        button_ThongTinPhong.setText("Thông tin phòng");
        button_ThongTinPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ThongTinPhongActionPerformed(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label_TroVeTrangChu)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(button_CapNhatThongTinPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(button_ThemPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(button_XoaPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(button_ThongTinPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(button_ThongTinCSVC, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(button_XoáCVC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(button_DiChuyenCSVC, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(256, 256, 256)
                        .addComponent(jLabel2)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_XoáCVC, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_XoaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_ThemPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_ThongTinCSVC, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_CapNhatThongTinPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_ThongTinPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_DiChuyenCSVC, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(label_TroVeTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void label_TroVeTrangChuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_TroVeTrangChuMouseClicked
        // TODO add your handling code here:
        Quan_Ly_Phong_Ban.this.setVisible(false);
        new Menu_Quan_Ly().setVisible(true);
    }//GEN-LAST:event_label_TroVeTrangChuMouseClicked

    private void button_ThemPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ThemPhongActionPerformed
        // TODO add your handling code here:
         Quan_Ly_Phong_Ban.this.setVisible(false);
        new Them_Phong().setVisible(true);
    }//GEN-LAST:event_button_ThemPhongActionPerformed

    private void button_ThongTinPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ThongTinPhongActionPerformed
      try{
        int Row = table_DanhSachPhong.getSelectedRow();
        String maphonghoc = (String) table_DanhSachPhong.getValueAt(Row,1);
        new Thong_Tin_Phong(maphonghoc).setVisible(true);
      }catch(Exception ex){
          JOptionPane.showMessageDialog(null,"Vui lòng chọn phòng!", "Thông báo",JOptionPane.INFORMATION_MESSAGE);
      }
    }//GEN-LAST:event_button_ThongTinPhongActionPerformed

    private void button_CapNhatThongTinPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_CapNhatThongTinPhongActionPerformed

        try{
         int Row = table_DanhSachPhong.getSelectedRow();
        String maphonghoccapnhat = (String) table_DanhSachPhong.getValueAt(Row,1);
        new Cap_Nhat_Thong_Tin_Phong(maphonghoccapnhat).setVisible(true);
        Quan_Ly_Phong_Ban.this.setVisible(false);
        }catch(Exception ex){
          JOptionPane.showMessageDialog(null,"Vui lòng chọn phòng!", "Thông báo",JOptionPane.INFORMATION_MESSAGE);
      }
    }//GEN-LAST:event_button_CapNhatThongTinPhongActionPerformed

    private void button_XoaPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_XoaPhongActionPerformed
        try{
        int Row = table_DanhSachPhong.getSelectedRow();
        String maphongxoa = (String) table_DanhSachPhong.getValueAt(Row,1);
        try{
        Connect a = new Connect();
        Connection conn = a.getConnection();
         if (conn!=null){
             Statement dd = conn.createStatement();  
             dd.executeUpdate("update cosovatchat set maphong = '0000' where maphong like '"+maphongxoa+"' ");  
             dd.executeUpdate("delete  from phonghoc where maphong like '"+maphongxoa+"'");
             
             
         }
        conn.close();
        }catch(Exception ex){
            System.out.print(ex.toString());
        }
        JOptionPane.showMessageDialog(null,"XÓA THÀNH CÔNG.", "Thông báo",JOptionPane.INFORMATION_MESSAGE);
        Quan_Ly_Phong_Ban.this.setVisible(false);
        new Quan_Ly_Phong_Ban().setVisible(true);
        }catch(Exception ex){
          JOptionPane.showMessageDialog(null,"Vui lòng chọn phòng!", "Thông báo",JOptionPane.INFORMATION_MESSAGE);
      }
    }//GEN-LAST:event_button_XoaPhongActionPerformed

    private void button_XoáCVCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_XoáCVCActionPerformed
        // TODO add your handling code here:
       
        try{
             int Row = table_DanhSachCoSoVatChat.getSelectedRow();
             String macsvcxoa = (String) table_DanhSachCoSoVatChat.getValueAt(Row,1);
             try{
                  Connect a = new Connect();
                 Connection conn = a.getConnection();
                 if (conn!=null){
                 Statement dd = conn.createStatement();  
                 dd.executeUpdate("update cosovatchat set  maphong= '0000' where macsvc like N'"+macsvcxoa+"' ");       
                 }
             }catch(Exception ex){
                 System.out.print(ex.toString());
             }
             JOptionPane.showMessageDialog(null,"Đã xóa!", "Thông báo",JOptionPane.INFORMATION_MESSAGE);
             Quan_Ly_Phong_Ban.this.setVisible(false);
             new Quan_Ly_Phong_Ban().setVisible(true);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Vui lòng chọn phòng và cơ sở vật chất của phòng!", "Thông báo",JOptionPane.INFORMATION_MESSAGE);
        }
        
    }//GEN-LAST:event_button_XoáCVCActionPerformed

    private void button_ThongTinCSVCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ThongTinCSVCActionPerformed
        // TODO add your handling code here:
        try{
         int Row = table_DanhSachCoSoVatChat.getSelectedRow();
        String macsvcthongtin = (String) table_DanhSachCoSoVatChat.getValueAt(Row,1);
        Quan_Ly_Phong_Ban.this.setVisible(false);
        new Thong_Tin_CSVC_Phong(macsvcthongtin).setVisible(true);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Vui lòng chọn phòng và cơ sở vật chất của phòng!", "Thông báo",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_button_ThongTinCSVCActionPerformed

    private void button_DiChuyenCSVCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_DiChuyenCSVCActionPerformed
        // TODO add your handling code here:
        try{
        int Row = table_DanhSachCoSoVatChat.getSelectedRow();
        String macsvcdichuyen = (String) table_DanhSachCoSoVatChat.getValueAt(Row,1);
        try{
                 Connect a = new Connect();
                 Connection conn = a.getConnection();
                 if (conn!=null){
                 Statement dd = conn.createStatement();  
                 ResultSet  kq = dd.executeQuery(" select tinhtrangcsvc from cosovatchat where macsvc = '"+macsvcdichuyen+"'   ");
                 while(kq.next()){
                     String tinhtrang = kq.getString(1);
                     if (tinhtrang.equals("Đang bảo trì")||tinhtrang.equals("Bảo trì")){
                         JOptionPane.showMessageDialog(null,"Cơ sở vật chất đang trong tình trạng bảo trì không thể di chuyển!", "Thông báo",JOptionPane.INFORMATION_MESSAGE);
                     }else{
                         new Di_Chuyen_CSVC(macsvcdichuyen).setVisible(true);
                         Quan_Ly_Phong_Ban.this.setVisible(false);
                         DefaultTableModel model = (DefaultTableModel)table_DanhSachCoSoVatChat.getModel();
                         
                     }
                 }
                 }
             }catch(Exception ex){
                 System.out.print(ex.toString());
             }
        
         }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Vui lòng chọn phòng và cơ sở vật chất của phòng!", "Thông báo",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_button_DiChuyenCSVCActionPerformed

    private void table_DanhSachPhongKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_table_DanhSachPhongKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_table_DanhSachPhongKeyReleased

    private void table_DanhSachPhongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_DanhSachPhongMouseClicked

        
        int Row = table_DanhSachPhong.getSelectedRow();
        
        String maphongchon = (String) table_DanhSachPhong.getValueAt(Row,1);
       loadtabledanhsachcsvc(maphongchon);
        
           
    }//GEN-LAST:event_table_DanhSachPhongMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
if (JOptionPane.showConfirmDialog(this, "Thoát phần mềm?", "Xác nhận", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
               System.exit(0);
            }    }//GEN-LAST:event_formWindowClosing
public void loadtabledanhsachcsvc(String maphongchon){
     DefaultTableModel model = (DefaultTableModel)table_DanhSachCoSoVatChat.getModel();
            model.setRowCount(0);
        try{
            Connect a = new Connect();
            Connection conn = a.getConnection();
            if (conn!=null){
                Statement dd = conn.createStatement();            
                 ResultSet  kq = dd.executeQuery("select tencsvc,macsvc,soluong,tinhtrangcsvc from cosovatchat where xoa = 0 and maphong = '"+maphongchon+"'");
                 while(kq.next()){
                    String tencsvc= kq.getString(1);
                    String macsvc= kq.getString(2);
                    int soluong=kq.getInt(3);
                    String tinhtrangcsvc = kq.getString(4);
                    model.addRow(new Object[]{tencsvc, macsvc,soluong,tinhtrangcsvc});
                }
                dd.close();
                conn.close();
            }
                      
        }catch(Exception ex){
            System.out.print(ex.toString());
        }
}
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
            java.util.logging.Logger.getLogger(Quan_Ly_Phong_Ban.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Quan_Ly_Phong_Ban.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Quan_Ly_Phong_Ban.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Quan_Ly_Phong_Ban.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Quan_Ly_Phong_Ban().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_CapNhatThongTinPhong;
    private javax.swing.JButton button_DiChuyenCSVC;
    private javax.swing.JButton button_ThemPhong;
    private javax.swing.JButton button_ThongTinCSVC;
    private javax.swing.JButton button_ThongTinPhong;
    private javax.swing.JButton button_XoaPhong;
    private javax.swing.JButton button_XoáCVC;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel label_TroVeTrangChu;
    private javax.swing.JTable table_DanhSachCoSoVatChat;
    private javax.swing.JTable table_DanhSachPhong;
    // End of variables declaration//GEN-END:variables
}
