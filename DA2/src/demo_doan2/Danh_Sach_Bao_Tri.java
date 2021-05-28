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
public class Danh_Sach_Bao_Tri extends javax.swing.JFrame {

    /**
     * Creates new form Danh_Sach_Bao_Tri
     */
    public Danh_Sach_Bao_Tri() {
        initComponents();
        loadCSVCcanbaotri();
        
    
    }
    public void nhapmota(){
        try{
        int Row = table_DanhSachCSVC1.getSelectedRow();
        String macsvcthongtin = (String) table_DanhSachCSVC1.getValueAt(Row,1);
        String mota = JOptionPane.showInputDialog(this,"Nhập mô tả cho csvc!");
        if (mota.equals("")){
            JOptionPane.showMessageDialog(null,"Chưa nhập mô tả cho csvc!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        }else{
            Connect a = new Connect();
            Connection conn = a.getConnection();
            if (conn!=null){
                Statement da = conn.createStatement(); 
                da.executeUpdate(" update cosovatchat set mota = N'"+mota+"' where macsvc like '"+macsvcthongtin+"'    ");
                
            }
            JOptionPane.showMessageDialog(null,"Đã thêm mô tả cho csvc!","Thông báo",JOptionPane.INFORMATION_MESSAGE);          
            }
        
        }catch(Exception ex){
            System.out.print(ex.toString());
            JOptionPane.showMessageDialog(null,"Chưa chọn csvc!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void hienthimota(){
        try{
        int Row = table_DanhSachCSVC1.getSelectedRow();
        String macsvcthongtin = (String) table_DanhSachCSVC1.getValueAt(Row,1);
        
            Connect a = new Connect();
            Connection conn = a.getConnection();
            if (conn!=null){
                Statement da = conn.createStatement(); 
                ResultSet  kq = da.executeQuery("select mota from cosovatchat where macsvc like '"+macsvcthongtin+"' ");
                while(kq.next()){
                    String mota = kq.getString(1);
                    txt_mota.setText(mota);
                }
            }

        }catch(Exception ex){
            System.out.print(ex.toString());
        }
    }
    public void loadCSVCcanbaotri(){
        DefaultTableModel DanhSachCSVC = (DefaultTableModel)table_DanhSachCSVC1.getModel();
        try{
            Connect a = new Connect();
            Connection conn = a.getConnection();
            if (conn!=null){
                Statement dd = conn.createStatement();            
                 ResultSet  kq = dd.executeQuery("select macsvc,ngaybaotri from baotrivasuachua");
                 while(kq.next()){
                    String macsvc = kq.getString(1);
                    String ngaybaotri =kq.getString(2);
                    String str[] = ngaybaotri.toString().split("-");
                    String ngaybaotri1=str[2]+"-"+str[1]+"-"+str[0];
                    Statement da = conn.createStatement();   
                    ResultSet  csvc = da.executeQuery("select tencsvc,tinhtrangcsvc,maphong from cosovatchat where (macsvc like '"+macsvc+"')  ");
                    while(csvc.next()){
                      String tencsvc=csvc.getString(1);
                      String tinhtrangcsvc =csvc.getString(2);
                      String maphong =csvc.getString(3);
                      DanhSachCSVC.addRow(new Object[]{tencsvc, macsvc,tinhtrangcsvc,maphong,ngaybaotri1});
                      }
                   
                }
                
            }
                      
        }catch(Exception ex){
            System.out.print(ex.toString());
        }
    }
    
    
    public void bo(){
        int Row = table_DanhSachCSVC1.getSelectedRow();
        String macsvcthongtin = (String) table_DanhSachCSVC1.getValueAt(Row,1);
        DefaultTableModel DanhSachCSVC = (DefaultTableModel)table_DanhSachCSVC1.getModel();
        
        
        try{
            Connect a = new Connect();
            Connection conn = a.getConnection();
            if (conn!=null){
                Statement da = conn.createStatement(); 
                da.executeUpdate("update cosovatchat set tinhtrangcsvc = N'Đã bảo trì' where macsvc like '"+macsvcthongtin+"'  ");
                da.executeUpdate("delete from baotrivasuachua where macsvc like '"+macsvcthongtin+"'");
                DanhSachCSVC.removeRow(Row);
                DanhSachCSVC.setRowCount(0);
                loadCSVCcanbaotri();
                JOptionPane.showMessageDialog(null,"Đã cập nhật tình trạng thành đã bảo trì và xóa khỏi danh sách bảo trì!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
            }
                      
        }catch(Exception ex){
            System.out.print(ex.toString());
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

        label_TroVeTrangChu = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        button_ThemVaoDanhSach = new javax.swing.JButton();
        button_XoaKhoiDanhSach = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_DanhSachCSVC1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        button_themmota = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_mota = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Phần mềm quản lý cơ sở vật chất");
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

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 25)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-maintenance-60.png"))); // NOI18N
        jLabel2.setText("QUẢN LÝ BẢO TRÌ CƠ SỞ VẬT CHẤT");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel1.setText("Danh sách cơ sở vật chất cần bảo trì & sửa chữa");

        button_ThemVaoDanhSach.setBackground(java.awt.Color.lightGray);
        button_ThemVaoDanhSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-add-list-24.png"))); // NOI18N
        button_ThemVaoDanhSach.setText("Thêm mới vào danh sách");
        button_ThemVaoDanhSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ThemVaoDanhSachActionPerformed(evt);
            }
        });

        button_XoaKhoiDanhSach.setBackground(java.awt.Color.lightGray);
        button_XoaKhoiDanhSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-update-24.png"))); // NOI18N
        button_XoaKhoiDanhSach.setText("Đã bảo trì và xóa khỏi danh sách ");
        button_XoaKhoiDanhSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_XoaKhoiDanhSachActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Chức năng");

        table_DanhSachCSVC1.setBackground(java.awt.Color.white);
        table_DanhSachCSVC1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên", "Mã csvc", "Tình trạng", "Mã phòng", "Ngày bảo trì"
            }
        ));
        table_DanhSachCSVC1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_DanhSachCSVC1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(table_DanhSachCSVC1);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Chọn 1 cơ sở vật chất trong danh sách");

        button_themmota.setBackground(java.awt.Color.lightGray);
        button_themmota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-add-column-24.png"))); // NOI18N
        button_themmota.setText("Thêm mô tả cho csvc");
        button_themmota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_themmotaActionPerformed(evt);
            }
        });

        txt_mota.setColumns(20);
        txt_mota.setRows(5);
        txt_mota.setBorder(javax.swing.BorderFactory.createTitledBorder("Mô tả csvc"));
        txt_mota.setEnabled(false);
        jScrollPane1.setViewportView(txt_mota);

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
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(355, 355, 355))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 672, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane1)
                                    .addComponent(jLabel3)
                                    .addComponent(button_ThemVaoDanhSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(button_themmota, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(button_XoaKhoiDanhSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(251, 251, 251)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_ThemVaoDanhSach, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_themmota, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_XoaKhoiDanhSach, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(label_TroVeTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void label_TroVeTrangChuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_TroVeTrangChuMouseClicked
        // TODO add your handling code here:
        Danh_Sach_Bao_Tri.this.setVisible(false);
        new Menu_Quan_Ly().setVisible(true);
    }//GEN-LAST:event_label_TroVeTrangChuMouseClicked

    private void button_ThemVaoDanhSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ThemVaoDanhSachActionPerformed
        // TODO add your handling code here:
         new Them_Vao_Danh_Sach_Bao_Tri().setVisible(true);
         Danh_Sach_Bao_Tri.this.setVisible(false);
    }//GEN-LAST:event_button_ThemVaoDanhSachActionPerformed

    private void button_XoaKhoiDanhSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_XoaKhoiDanhSachActionPerformed
        // TODO add your handling code here:
            //    JOptionPane.showMessageDialog(null,"Đã xóa!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
            
            try{
             bo();
        }catch(Exception ex){
            System.out.println(ex);
         JOptionPane.showMessageDialog(null,"Vui lòng chọn cơ sở vật chất!", "Thông báo",JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_button_XoaKhoiDanhSachActionPerformed

    private void table_DanhSachCSVC1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_DanhSachCSVC1MouseClicked
        // TODO add your handling code here:
        hienthimota();
    }//GEN-LAST:event_table_DanhSachCSVC1MouseClicked

    private void button_themmotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_themmotaActionPerformed
        // TODO add your handling code here:
        nhapmota();
    }//GEN-LAST:event_button_themmotaActionPerformed

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
            java.util.logging.Logger.getLogger(Danh_Sach_Bao_Tri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Danh_Sach_Bao_Tri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Danh_Sach_Bao_Tri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Danh_Sach_Bao_Tri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Danh_Sach_Bao_Tri().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_ThemVaoDanhSach;
    private javax.swing.JButton button_XoaKhoiDanhSach;
    private javax.swing.JButton button_themmota;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel label_TroVeTrangChu;
    private javax.swing.JTable table_DanhSachCSVC1;
    private javax.swing.JTextArea txt_mota;
    // End of variables declaration//GEN-END:variables
}
