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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lyhoa
 */
public class Kiem_Duyet_Thong_Tin_Kiem_Ke extends javax.swing.JFrame {

    /**
     * Creates new form Kiem_Duyet_Thong_Tin_Kiem_Ke
     */
    String makiemke;
    public Kiem_Duyet_Thong_Tin_Kiem_Ke(String makkchuaduyet) {
        initComponents();
        loadphong();
        TaiDuLieu(makkchuaduyet);
        loaddata(makkchuaduyet);
        makiemke =makkchuaduyet;
    }

    private Kiem_Duyet_Thong_Tin_Kiem_Ke() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void loadphong(){
        
        try{
        Connect a = new Connect();
        Connection conn = a.getConnection();
         if (conn!=null){
             Statement dd = conn.createStatement();
             ResultSet  kq = dd.executeQuery("select tenphong from phonghoc ");  
             while(kq.next()){
                   txt_PhongBanDuocKiemKe.addItem(kq.getString(1));    
             }
         }
        conn.close();
        }catch(Exception ex){
            System.out.print(ex.toString());
        }
       
    }
    public void TaiDuLieu(String makkchuaduyet){
        DefaultTableModel DanhSachCSVC = (DefaultTableModel)table_DanhSachCSVCCuaPhongBanDuocKiemKe.getModel();
        DanhSachCSVC.setRowCount(0);
        try{
            Connect a = new Connect();
            Connection conn = a.getConnection();
            if (conn!=null){
                Statement dd = conn.createStatement();            
                 ResultSet  kq = dd.executeQuery("Select macsvc, tencsvc, soluong, tinhtrangcsvc from kiemke_csvcphong where makiemke like '"+makkchuaduyet+"' ");
                 while(kq.next()){
                    String macsvc = kq.getString(1);
                    String tencsvc =kq.getString(2);
                    String soluong =kq.getString(3);
                    String tinhtrangcsvc =kq.getString(4);
                    DanhSachCSVC.addRow(new Object[]{macsvc, tencsvc,soluong,tinhtrangcsvc});
                }
                
            }
                      
        }catch(Exception ex){
            System.out.print(ex.toString());
        }
    }
    public void loaddata(String makkchuaduyet){

        try{
            Connect a = new Connect();
            Connection conn = a.getConnection();
            if (conn!=null){
                Statement dd = conn.createStatement();            
                 ResultSet  kq = dd.executeQuery("Select makiemke,tengiaovien,chucvu,maphong,tinhtrangphong,ngaykiemke from kiemke where makiemke like '"+makkchuaduyet+"' ");
                 while(kq.next()){
                    String makiemke = kq.getString(1);
                    String tengiaovien =kq.getString(2);
                    String chucvu =kq.getString(3);
                    String maphong =kq.getString(4);
                    String tinhtrangphong =kq.getString(5);
                    String ngaykiemke =kq.getString(6);
                    
                    Statement da = conn.createStatement();            
                    ResultSet  ka = da.executeQuery("Select tenphong from phonghoc where maphong like'"+maphong+"'");
                    while(ka.next()){
                       String tenphong = ka.getString(1);
                       txt_PhongBanDuocKiemKe.setSelectedItem(tenphong);
                    }
                    String str[] = ngaykiemke.toString().split("-");
                    String ngaykiemke1=str[2]+"-"+str[1]+"-"+str[0];
                    txt_tengiaovien.setText(tengiaovien);
                    txt_chucvuhientai.setSelectedItem(chucvu);
                    txt_ngaykiemke.setText(ngaykiemke1);
                    txt_MaKiemKe.setText(makiemke);
                    txt_Maphong.setText(maphong);
                    txt_tinhtrangphong.setText(tinhtrangphong);
                    
                }
                
            }
                      
        }catch(Exception ex){
            System.out.print(ex.toString());
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label_TroVeTrangChu = new javax.swing.JLabel();
        button_XacNhanThongtinKiemKe = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txt_MaKiemKe = new javax.swing.JTextField();
        txt_chucvuhientai = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_Maphong = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_DanhSachCSVCCuaPhongBanDuocKiemKe = new javax.swing.JTable();
        txt_ngaykiemke = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_PhongBanDuocKiemKe = new javax.swing.JComboBox<>();
        txt_tengiaovien = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_tinhtrangphong = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Phần mềm quản lý cơ sở vật chất");
        setLocation(new java.awt.Point(500, 200));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        label_TroVeTrangChu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-go-back-24.png"))); // NOI18N
        label_TroVeTrangChu.setText("Trở về");
        label_TroVeTrangChu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_TroVeTrangChuMouseClicked(evt);
            }
        });

        button_XacNhanThongtinKiemKe.setBackground(java.awt.Color.lightGray);
        button_XacNhanThongtinKiemKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-check-all-24.png"))); // NOI18N
        button_XacNhanThongtinKiemKe.setText("Xác nhận thông tin kiểm kê");
        button_XacNhanThongtinKiemKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_XacNhanThongtinKiemKeActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setText("XÁC NHẬN THÔNG TIN KIỂM KÊ CƠ SỞ VẬT CHẤT");

        txt_MaKiemKe.setEnabled(false);

        txt_chucvuhientai.setBackground(java.awt.Color.white);
        txt_chucvuhientai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Chọn---", "Giáo viên", "Quản lý", "Thanh tra", "Bảo vệ" }));
        txt_chucvuhientai.setEnabled(false);
        txt_chucvuhientai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_chucvuhientaiActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel7.setText("Mã kiểm kê:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel11.setText("Mã phòng:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel8.setText("Phòng học thực hiện kiểm kê:");

        txt_Maphong.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel9.setText("Danh sách cơ sở vật chất của phòng học");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel2.setText("Tên giáo viên thực hiện:");

        table_DanhSachCSVCCuaPhongBanDuocKiemKe.setBackground(java.awt.Color.white);
        table_DanhSachCSVCCuaPhongBanDuocKiemKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Tên csvc", "SL", "Tình trạng khi kiểm kê"
            }
        ));
        jScrollPane2.setViewportView(table_DanhSachCSVCCuaPhongBanDuocKiemKe);

        txt_ngaykiemke.setText("yyyy-mm-dd");
        txt_ngaykiemke.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel3.setText("Chức vụ hiện tại:");

        txt_PhongBanDuocKiemKe.setBackground(java.awt.Color.white);
        txt_PhongBanDuocKiemKe.setEnabled(false);
        txt_PhongBanDuocKiemKe.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txt_PhongBanDuocKiemKeItemStateChanged(evt);
            }
        });

        txt_tengiaovien.setEnabled(false);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel10.setText("Tình trạng phòng:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel5.setText("Ngày kiểm kê:");

        txt_tinhtrangphong.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(156, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(132, 132, 132))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(label_TroVeTrangChu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button_XacNhanThongtinKiemKe, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(102, 102, 102)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(jLabel5)
                                .addComponent(jLabel7)
                                .addComponent(jLabel8)
                                .addComponent(jLabel10)
                                .addComponent(jLabel11))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_tengiaovien)
                                .addComponent(txt_ngaykiemke)
                                .addComponent(txt_chucvuhientai, 0, 370, Short.MAX_VALUE)
                                .addComponent(txt_tinhtrangphong)
                                .addComponent(txt_Maphong)
                                .addComponent(txt_MaKiemKe)
                                .addComponent(txt_PhongBanDuocKiemKe, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 782, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(38, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 669, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_XacNhanThongtinKiemKe, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_TroVeTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(89, 89, 89)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txt_tengiaovien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(6, 6, 6)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txt_chucvuhientai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                        .addComponent(txt_ngaykiemke, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(txt_MaKiemKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txt_PhongBanDuocKiemKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(2, 2, 2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(txt_Maphong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(txt_tinhtrangphong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel9)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(90, 90, 90)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void label_TroVeTrangChuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_TroVeTrangChuMouseClicked
        // TODO add your handling code here:
        Kiem_Duyet_Thong_Tin_Kiem_Ke.this.setVisible(false);
        new Kiem_Ke_CSVC().setVisible(true);
    }//GEN-LAST:event_label_TroVeTrangChuMouseClicked

    private void button_XacNhanThongtinKiemKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_XacNhanThongtinKiemKeActionPerformed
        // TODO add your handling code here:
        try{
            String maphong = txt_Maphong.getText();
            Connect a = new Connect();
            Connection conn = a.getConnection();
            Statement dd = conn.createStatement();            
            dd.executeUpdate("update kiemke set tinhtrangkiemduyet = 1 where makiemke like '"+makiemke+"'");
            dd.executeUpdate("update phonghoc set tinhtrangkiemkecuaphong = 0, tinhtrangphong= N'Đã kiểm kê' where maphong like '"+maphong+"'");
            JOptionPane.showMessageDialog(null,"Thông tin kiểm kê CSVC đã được kiểm duyệt!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
            Kiem_Duyet_Thong_Tin_Kiem_Ke.this.setVisible(false);
            new Kiem_Ke_CSVC().setVisible(true);
        }catch(Exception ex){
            System.out.print(ex.toString());
            JOptionPane.showMessageDialog(null,"Lỗi!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_button_XacNhanThongtinKiemKeActionPerformed

    private void txt_chucvuhientaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_chucvuhientaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_chucvuhientaiActionPerformed

    private void txt_PhongBanDuocKiemKeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txt_PhongBanDuocKiemKeItemStateChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txt_PhongBanDuocKiemKeItemStateChanged

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
            java.util.logging.Logger.getLogger(Kiem_Duyet_Thong_Tin_Kiem_Ke.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Kiem_Duyet_Thong_Tin_Kiem_Ke.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Kiem_Duyet_Thong_Tin_Kiem_Ke.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Kiem_Duyet_Thong_Tin_Kiem_Ke.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Kiem_Duyet_Thong_Tin_Kiem_Ke().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_XacNhanThongtinKiemKe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel label_TroVeTrangChu;
    private javax.swing.JTable table_DanhSachCSVCCuaPhongBanDuocKiemKe;
    private javax.swing.JTextField txt_MaKiemKe;
    private javax.swing.JTextField txt_Maphong;
    private javax.swing.JComboBox<String> txt_PhongBanDuocKiemKe;
    private javax.swing.JComboBox<String> txt_chucvuhientai;
    private javax.swing.JTextField txt_ngaykiemke;
    private javax.swing.JTextField txt_tengiaovien;
    private javax.swing.JTextField txt_tinhtrangphong;
    // End of variables declaration//GEN-END:variables

    
}
