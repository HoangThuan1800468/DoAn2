/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package demo_doan2;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lyhoa
 */
public class Them_Vao_Danh_Sach_Bao_Tri extends javax.swing.JFrame {

    /** Creates new form Them_Vao_Danh_Sach_Bao_Tri */
    public Them_Vao_Danh_Sach_Bao_Tri() {
        initComponents();
        loadCSVCcanbaotri();
    }
    public void loadCSVCcanbaotri(){
        DefaultTableModel DanhSachCSVC = (DefaultTableModel)table_DanhSachCSVC.getModel();
        try{
            Connect a = new Connect();
            Connection conn = a.getConnection();
            if (conn!=null){
                Statement dd = conn.createStatement();            
                 ResultSet  kq = dd.executeQuery("select macsvc,ngaybaotri from baotrivasuachua");
                 while(kq.next()){
                    String macsvc = kq.getString(1);
                    String ngaybaotri=kq.getString(2);
                    String str[] = ngaybaotri.toString().split("-");
                    String ngaybaotri1=str[2]+"-"+str[1]+"-"+str[0];
                    Statement da = conn.createStatement(); 
                    ResultSet  csvc = da.executeQuery("select tencsvc,tinhtrangcsvc,ngaynhancsvc,maphong from cosovatchat where macsvc like '"+macsvc+"' ");
                    while(csvc.next()){
                      String tencsvc=csvc.getString(1);
                      String tinhtrangcsvc =csvc.getString(2);
                      String ngaynhancsvc =csvc.getString(3);
                      String maphong =csvc.getString(4);
                      String str1[] = ngaynhancsvc.toString().split("-");
                      String ngaynhancsvc1=str1[2]+"-"+str1[1]+"-"+str1[0];
                       DanhSachCSVC.addRow(new Object[]{tencsvc, macsvc,tinhtrangcsvc,maphong,ngaynhancsvc1,ngaybaotri1});
                 }
                   
                }
                
            }
                      
        }catch(Exception ex){
            System.out.print(ex.toString());
        }
    }
    public void timkiem(String doituong){
      DefaultTableModel model = (DefaultTableModel)table_DanhSachCSVCTimKiem.getModel();
       model.setRowCount(0);
       if (doituong.equals("")){
           try{
            Connect a = new Connect();
            Connection conn = a.getConnection();
            if (conn!=null){
                Statement dd = conn.createStatement();            
                 ResultSet  kq = dd.executeQuery("select tencsvc,macsvc,tinhtrangcsvc,maphong,ngaynhancsvc from cosovatchat where xoa = 0 and  tinhtrangcsvc not like N'Bảo trì' and tinhtrangcsvc not like N'Đang bảo trì' ");
                 while(kq.next()){
                    String tencsvc= kq.getString(1);
                    String macsvc = kq.getString(2);
                    String tinhtrangcsvc=kq.getString(3);
                    String maphong=kq.getString(4);
                    String ngaynhancsvc= kq.getString(5);
                    String str1[] = ngaynhancsvc.toString().split("-");
                    String ngaynhancsvc1=str1[2]+"-"+str1[1]+"-"+str1[0];
                    model.addRow(new Object[]{tencsvc, macsvc,tinhtrangcsvc,maphong,ngaynhancsvc1});
                }
                
            }
                      
        }catch(Exception ex){
            System.out.print(ex.toString());
        }
       }else{
          try{
            Connect a = new Connect();
            Connection conn = a.getConnection();
            if (conn!=null){
                Statement dd = conn.createStatement();            
                 ResultSet  kq = dd.executeQuery("select tencsvc,macsvc,tinhtrangcsvc,maphong,ngaynhancsvc from cosovatchat where  (macsvc like '"+doituong+"' or tencsvc like N'%"+doituong+"%') and (tinhtrangcsvc not like N'Bảo trì' and tinhtrangcsvc not like N'Đang bảo trì')");
                 while(kq.next()){
                    String tencsvc= kq.getString(1);
                    String macsvc = kq.getString(2);
                    String tinhtrangcsvc=kq.getString(3);
                    String maphong=kq.getString(4);
                    String ngaynhancsvc= kq.getString(5);
                    String str1[] = ngaynhancsvc.toString().split("-");
                    String ngaynhancsvc1=str1[2]+"-"+str1[1]+"-"+str1[0];
                    model.addRow(new Object[]{tencsvc, macsvc,tinhtrangcsvc,maphong,ngaynhancsvc1});
                }
                
            }
                      
        }catch(Exception ex){
            System.out.print(ex.toString());
        }
           
       }
    }
    public String ngaynhan(String macsvcthongtin) {
        String ngaynhan = "";
       try{
            Connect a = new Connect();
            Connection conn = a.getConnection();
            Statement dd = conn.createStatement();            
            ResultSet  kq = dd.executeQuery("select ngaynhancsvc from cosovatchat where macsvc like '"+macsvcthongtin+"'");
                while(kq.next()){
                   ngaynhan=kq.getString(1);
                }         
        }catch(Exception ex){
            System.out.print(ngaynhan);
            
        }
       return ngaynhan;
    }
    public void them(){
        int Row = table_DanhSachCSVCTimKiem.getSelectedRow();
        String macsvcthongtin = (String) table_DanhSachCSVCTimKiem.getValueAt(Row,1);
        DefaultTableModel DanhSachCSVC = (DefaultTableModel)table_DanhSachCSVC.getModel();
        DefaultTableModel DanhSachCSVCTimKiem = (DefaultTableModel)table_DanhSachCSVCTimKiem.getModel();
        String ngaybaotri = JOptionPane.showInputDialog(this,"Nhập ngày bảo trì cho csvc! \n"
                                                           + " Theo định dạng: day-month-year ");
        String ngaynhan=ngaynhan(macsvcthongtin);
        try{
        String str[] = ngaybaotri.toString().split("-");
        String ngaybaotri2=str[2]+"-"+str[1]+"-"+str[0];
        int nam=Integer.parseInt(str[2]);
        Date ngaybaotri1 = new SimpleDateFormat("yyyy-MM-dd").parse(ngaybaotri2);
        Date ngaynhan1 = new SimpleDateFormat("yyyy-MM-dd").parse(ngaynhan);
       
        if(ngaybaotri.equals("")==true||ngaybaotri1.equals("")==true){
            JOptionPane.showMessageDialog(null,"Vui lòng nhập ngày bảo trì cho cơ sở vật chất!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        }else if(nam>=3000||nam<2020){
            JOptionPane.showMessageDialog(null,"Vui lòng nhập năm sau 2020 và trước 2999!","Thông báo",JOptionPane.ERROR_MESSAGE);
        }
        else if(ngaybaotri1.before(ngaynhan1)==true){
            JOptionPane.showMessageDialog(null,"Vui lòng nhập ngày bảo trì sau ngày nhận csvc!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        }
        else if(ngaybaotri1.before(ngaynhan1)==false){
            
            try{
            Connect a = new Connect();
            Connection conn = a.getConnection();
            if (conn!=null){
                Statement dd = conn.createStatement();            
                 ResultSet  kq = dd.executeQuery("select tencsvc,macsvc,maphong,ngaynhancsvc from cosovatchat where macsvc like '"+macsvcthongtin+"'");
                 while(kq.next()){
                    String tencsvc= kq.getString(1);
                    String macsvc = kq.getString(2);
                    String tinhtrangcsvc="Bảo trì";
                    String maphong=kq.getString(3);
                    String ngaynhancsvc= kq.getString(4);
                    
                    if (macsvc.equals("")==false){
                    String str2[] = ngaynhancsvc.toString().split("-");
                    String ngaynhancsvc1=str2[2]+"-"+str2[1]+"-"+str2[0];
                    DanhSachCSVC.addRow(new Object[]{tencsvc, macsvc,tinhtrangcsvc,maphong,ngaynhancsvc1,ngaybaotri});
                    Statement da = conn.createStatement(); 
                    da.executeUpdate("insert into baotrivasuachua (macsvc,ngaybaotri) values ('"+macsvc+"','"+ngaybaotri2+"')");
                    da.executeUpdate("update cosovatchat set tinhtrangcsvc = N'Bảo trì' where macsvc like '"+macsvcthongtin+"'  ");
                    }else{
                        System.out.print("đã có");
                    }
                }
                
            }
                      
        }catch(Exception ex){
            System.out.print(ex.toString());

        }
        DanhSachCSVCTimKiem.removeRow(Row);
        }
        }catch(Exception ex){
            System.out.print(ex);
           JOptionPane.showMessageDialog(null,"Vui lòng nhập ngày bảo trì đúng định dạng cho cơ sở vật chất!","Thông báo",JOptionPane.INFORMATION_MESSAGE);

        }
        
        
       
    }
    public void bo(){
        int Row = table_DanhSachCSVC.getSelectedRow();
        String macsvcthongtin = (String) table_DanhSachCSVC.getValueAt(Row,1);
        DefaultTableModel DanhSachCSVC = (DefaultTableModel)table_DanhSachCSVC.getModel();
        DefaultTableModel DanhSachCSVCTimKiem = (DefaultTableModel)table_DanhSachCSVCTimKiem.getModel();
        DanhSachCSVC.removeRow(Row);
        try{
            Connect a = new Connect();
            Connection conn = a.getConnection();
            if (conn!=null){
                Statement dd = conn.createStatement();            
                 ResultSet  kq = dd.executeQuery("select tencsvc,macsvc,tinhtrangcsvc,maphong,ngaynhancsvc from cosovatchat where tencsvc like N'"+macsvcthongtin+"' or macsvc like '"+macsvcthongtin+"' ");
                 while(kq.next()){
                    String tencsvc= kq.getString(1);
                    String macsvc = kq.getString(2);
                    String tinhtrangcsvc="Đã bảo trì";
                    String maphong=kq.getString(4);
                    String ngaynhancsvc=kq.getString(5);
                    if (macsvc.equals("")==false){
                    String str[] = ngaynhancsvc.toString().split("-");
                    String ngaynhancsvc1=str[2]+"-"+str[1]+"-"+str[0];
                    DanhSachCSVCTimKiem.addRow(new Object[]{tencsvc, macsvc,tinhtrangcsvc,maphong,ngaynhancsvc1});
                    Statement da = conn.createStatement(); 
                    da.executeUpdate("delete from baotrivasuachua where macsvc like '"+macsvc+"'");
                    da.executeUpdate("update cosovatchat set tinhtrangcsvc = N'Đã bảo trì' where macsvc like '"+macsvcthongtin+"'  ");
                    }else{
                        System.out.print("đã có");
                    }
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

        jDialog1 = new javax.swing.JDialog();
        btn_Them = new javax.swing.JButton();
        btn_Bo = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txt_TimKiem = new javax.swing.JTextField();
        button_TimKiem = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_DanhSachCSVCTimKiem = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        table_DanhSachCSVC = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Phần mềm quản lý cơ sở vật chất");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        btn_Them.setBackground(java.awt.Color.lightGray);
        btn_Them.setText(">>>");
        btn_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemActionPerformed(evt);
            }
        });

        btn_Bo.setBackground(java.awt.Color.lightGray);
        btn_Bo.setText("<<<");
        btn_Bo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_BoActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm cơ sở vật chất"));

        button_TimKiem.setBackground(java.awt.Color.lightGray);
        button_TimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-search-26.png"))); // NOI18N
        button_TimKiem.setText("Tìm kiếm");
        button_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_TimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_TimKiem)
                    .addComponent(button_TimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(button_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        table_DanhSachCSVCTimKiem.setBackground(java.awt.Color.white);
        table_DanhSachCSVCTimKiem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên csvc", "Mã csvc", "Tình trạng", "Mã phòng", "Ngày nhận"
            }
        ));
        jScrollPane2.setViewportView(table_DanhSachCSVCTimKiem);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Danh sách bảo trì");

        table_DanhSachCSVC.setBackground(java.awt.Color.white);
        table_DanhSachCSVC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên csvc", "Mã csvc", "Tình trạng", "Mã phòng", "Ngày nhận", "Ngày bảo trì"
            }
        ));
        jScrollPane4.setViewportView(table_DanhSachCSVC);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Danh sách Cơ Sở Vật Chất");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-maintenance-60.png"))); // NOI18N
        jLabel2.setText("THÊM VÀO DANH SÁCH BẢO TRÌ");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btn_Bo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_Them, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(479, 479, 479))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE))
                .addGap(15, 15, 15))
            .addGroup(layout.createSequentialGroup()
                .addGap(480, 480, 480)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(90, 90, 90)
                                .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_Bo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(174, 174, 174)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void button_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_TimKiemActionPerformed
        // TODO add your handling code here:
        String doituong = txt_TimKiem.getText();
        timkiem(doituong);
    }//GEN-LAST:event_button_TimKiemActionPerformed

    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
        // TODO add your handling code here:
        try{
            them();
        }catch(Exception ex){
            System.out.println(ex);
         JOptionPane.showMessageDialog(null,"Vui lòng chọn cơ sở vật chất của phòng!", "Thông báo",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btn_ThemActionPerformed

    private void btn_BoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BoActionPerformed
        // TODO add your handling code here:
        try{
            bo();
        }catch(Exception ex){
         JOptionPane.showMessageDialog(null,"Vui lòng chọn cơ sở vật chất của phòng!", "Thông báo",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btn_BoActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        Them_Vao_Danh_Sach_Bao_Tri.this.setVisible(false);
         new Danh_Sach_Bao_Tri().setVisible(true);
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
            java.util.logging.Logger.getLogger(Them_Vao_Danh_Sach_Bao_Tri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Them_Vao_Danh_Sach_Bao_Tri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Them_Vao_Danh_Sach_Bao_Tri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Them_Vao_Danh_Sach_Bao_Tri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Them_Vao_Danh_Sach_Bao_Tri().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Bo;
    private javax.swing.JButton btn_Them;
    private javax.swing.JButton button_TimKiem;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable table_DanhSachCSVC;
    private javax.swing.JTable table_DanhSachCSVCTimKiem;
    private javax.swing.JTextField txt_TimKiem;
    // End of variables declaration//GEN-END:variables

}
