/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo_doan2;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lyhoa
 */
public class Di_Chuyen_CSVC extends javax.swing.JFrame {

    /**
     * Creates new form Di_Chuyen_CSVC
     */
    
    public Di_Chuyen_CSVC(String macsvcdichuyen) {
        initComponents();
        loadcsvc(macsvcdichuyen);
        loadData();
        
    }

    
    Di_Chuyen_CSVC() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void loadData(){
        
        DefaultTableModel model = (DefaultTableModel)table_DanhSachPhongHoc.getModel();
        try{
            Connect a = new Connect();
            Connection conn = a.getConnection();
            if (conn!=null){
                Statement dd = conn.createStatement();            
                 ResultSet  kq = dd.executeQuery("select tenphong,maphong from phonghoc where tinhtrangkiemkecuaphong like 0 and maphong not like '"+maphong+"'");
                 while(kq.next()){
                    String tenphong= kq.getString(1);
                    String maphong = kq.getString(2);
                    model.addRow(new Object[]{tenphong, maphong});
                }
                dd.close();
                conn.close();
            }
                      
        }catch(Exception ex){
            System.out.print(ex.toString());
        }
    }
    String tencsvc,macsvc,tinhtrang,loai,donvi,hinhthucthanhtoan,phongluutru,nguontien,nguoigiaodich,maphong;
    Date ngaynhan,hansudung;
    int soluong,giatri;
    public void loadcsvc(String macsvcthongtin){
        try{
        Connect a = new Connect();
        Connection conn = a.getConnection();
         if (conn!=null){
             Statement dd = conn.createStatement();  
             ResultSet  kq = dd.executeQuery("select * from cosovatchat where macsvc = '"+macsvcthongtin+"' ");  
             while(kq.next()){
                    tencsvc= kq.getString(1);
                     macsvc = kq.getString(2);
                     tinhtrang=kq.getString(3);
                     ngaynhan=kq.getDate(4);
                     loai=kq.getString(5);
                     soluong=kq.getInt(6);
                     donvi=kq.getString(7);
                     giatri=kq.getInt(8);
                     hansudung=kq.getDate(9);
                     hinhthucthanhtoan=kq.getString(10);
                     phongluutru=kq.getString(11);
                     nguontien=kq.getString(12);
                     nguoigiaodich=kq.getString(13);
                     maphong=kq.getString(14);
                    
                     String str[] = ngaynhan.toString().split("-");
                     String str1[] = hansudung.toString().split("-");
                     
                     String ngaynhan1=str[2]+"-"+str[1]+"-"+str[0];
                     String hansudung1=str1[2]+"-"+str1[1]+"-"+str1[0];
                     
                    txt_ThongTinCSVC.setText("Tên CSVC: "+tencsvc+"\n"
                            + "Mã CSVC: "+macsvc+"\n"
                            + "Tình trạng: "+tinhtrang+"\n"
                            + "Loại: "+loai+"\n"
                            + "Số lượng: "+soluong+"\n"
                            + "Đơn vị: "+donvi+"\n"
                            + "Giá trị: "+giatri+"\n"
                            + "Mã Phòng: "+maphong+"\n"
                            + "Ngày nhận; "+ngaynhan1+"\n"
                            + "Hạn sử dụng: "+hansudung1+"");
                    
             }
         }
        conn.close();
        }catch(Exception ex){
            System.out.print(ex.toString());
        }
    }
    public boolean xemtinhtrangphong(){
        boolean tx = false;
        try{
            Connect a = new Connect();
            Connection conn = a.getConnection();
            Statement dd = conn.createStatement();   
            ResultSet  kq = dd.executeQuery("select tinhtrangkiemkecuaphong from phonghoc where maphong like '"+maphong+"'");
            while(kq.next()){
                 int ma = kq.getInt(1);
                 if (ma==0){
                     tx=true;
                 }else{
                     tx=false;
                 }
            }
        }catch(Exception ex){
            tx=false;
            System.out.println(ex);
        }
        return tx;
    }
    public void dichuyencsvc(String macsvcdichyen, String maphongchuyen, int soluongchuyen){
        if (soluongchuyen>soluong||txt_SoLuong.equals("")){
            JOptionPane.showMessageDialog(null,"Nhập lại số lượng!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        }else if (xemtinhtrangphong()==false){
            JOptionPane.showMessageDialog(null,"Phòng đang kiểm kê không thể di chuyển csvc!","Thông báo",JOptionPane.INFORMATION_MESSAGE);

        }
        else{
            if(soluongchuyen==soluong){
               try{
               Connect a = new Connect();
               Connection conn = a.getConnection();
               if (conn!=null){
                Statement dd = conn.createStatement();            
                 dd.executeUpdate("update cosovatchat set  maphong= '"+maphongchuyen+"' where macsvc like '"+macsvcdichyen+"' ");
                 
                 dd.close();
                 conn.close();
                }       
               JOptionPane.showMessageDialog(null,"Đã chuyển CSVC thành công!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                 Di_Chuyen_CSVC.this.setVisible(false);
                 new Quan_Ly_Phong_Ban().setVisible(true);
               }catch(Exception ex){
                  System.out.print(ex.toString());
                  
                }
            }else{
                
                String macsvcchuyen = JOptionPane.showInputDialog(this,"Nhập mã cho csvc chuyển!");
                try{
                     Connect a = new Connect();
                     Connection conn = a.getConnection();
                     if (conn!=null){
                         Statement dd = conn.createStatement();  
                         int soluongcon=soluong-soluongchuyen;
                         if(macsvcchuyen.equals("")==true){
                             JOptionPane.showMessageDialog(null,"Vui lòng nhập lại mã cho cơ sở vật chất muốn chuyển!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                         }else if (thumacsvc(macsvcchuyen)==false){
                             JOptionPane.showMessageDialog(null,"Mã đã trùng vui lòng nhập lại mã cho cơ sở vật chất muốn chuyển!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                         }else if (thumacsvc(macsvcchuyen)==true){
                             dd.executeUpdate("update cosovatchat set  soluong= "+soluongcon+" where macsvc like '"+macsvc+"'");
                             dd.executeUpdate( "insert into cosovatchat (tencsvc,macsvc,tinhtrangcsvc,ngaynhancsvc,loaicsvc,soluong,donvi,giatri,hansudungcsvc,hinhthucthanhtoan,phongluutru,nguontien,nguoigiaodich,maphong,xoa) values \n" +
                             "(N'"+tencsvc+"','"+macsvcchuyen+"',N'"+tinhtrang+"','"+ngaynhan+"',N'"+loai+"',"+soluongchuyen+",N'"+donvi+"',"+giatri+",'"+hansudung+"',N'"+hinhthucthanhtoan+"',N'"+phongluutru+"',N'"+nguontien+"',N'"+nguoigiaodich+"','"+maphongchuyen+"',0)");
                              JOptionPane.showMessageDialog(null,"Đã chuyển CSVC thành công!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                    Di_Chuyen_CSVC.this.setVisible(false);
                    new Quan_Ly_Phong_Ban().setVisible(true);
                         }
                         
                     }
                     
                }catch(Exception ex){
                     System.out.print(ex.toString());
                     JOptionPane.showMessageDialog(null,"Đã chuyển CSVC không thành công!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                     
                }
           
                 }
            
           }
    }
    public boolean thumacsvc(String mathu){
        boolean thuma = false;
        int count = 0 ;
         try{
               Connect a = new Connect();
               Connection conn = a.getConnection();
               if (conn!=null){
                Statement dd = conn.createStatement();            
                 ResultSet  kq = dd.executeQuery("select macsvc from cosovatchat where macsvc like '"+mathu+"'");
                 while(kq.next()){
                 String ma = kq.getString(1);
                 if (ma.equals("")==false){
                     count++;
                 }
                 else {
                     count = count;
                 }
                 
                 
                 }
                }        
               }catch(Exception ex){
                  System.out.print(ex.toString());
                  
                }
         if(count>0){
             thuma = false;
         }else if (count == 0){
             thuma=true;
         }
         return thuma;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_ThongTinCSVC = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_DanhSachPhongHoc = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        button_ChuyenDi = new javax.swing.JButton();
        txt_SoLuong = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        label_TroVeTrangTruoc = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Phần mềm quản lý cơ sở vật chất");
        setLocation(new java.awt.Point(500, 250));
        setName("f_dichuyencosovatchat"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 25)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-update-left-rotation-24.png"))); // NOI18N
        jLabel2.setText("DI CHUYỂN CƠ SỞ VẬT CHẤT");

        txt_ThongTinCSVC.setColumns(20);
        txt_ThongTinCSVC.setRows(5);
        txt_ThongTinCSVC.setEnabled(false);
        jScrollPane1.setViewportView(txt_ThongTinCSVC);

        jLabel1.setText("Thông tin CSVC:");

        table_DanhSachPhongHoc.setBackground(java.awt.Color.white);
        table_DanhSachPhongHoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên phòng", "Mã phòng"
            }
        ));
        jScrollPane2.setViewportView(table_DanhSachPhongHoc);

        jLabel3.setText("Danh sách phòng học");

        button_ChuyenDi.setBackground(java.awt.Color.lightGray);
        button_ChuyenDi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-update-left-rotation-24.png"))); // NOI18N
        button_ChuyenDi.setText("Chuyển đi");
        button_ChuyenDi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ChuyenDiActionPerformed(evt);
            }
        });

        jLabel4.setText("Nhập số lượng chuyển:");

        label_TroVeTrangTruoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-go-back-24.png"))); // NOI18N
        label_TroVeTrangTruoc.setText("Trở về");
        label_TroVeTrangTruoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_TroVeTrangTruocMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(192, 192, 192))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label_TroVeTrangTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(172, 172, 172)
                                .addComponent(jLabel3)
                                .addGap(0, 196, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_SoLuong)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 11, Short.MAX_VALUE))
                            .addComponent(button_ChuyenDi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_SoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_ChuyenDi, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(label_TroVeTrangTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public boolean textloi (String a){
         boolean inputFinish = false;
         int number;
         try
         {
           number = Integer.parseInt(a);  
           inputFinish = true;
         }
          catch (Exception ignore)
          {
              inputFinish = false;
          }
        
        return inputFinish;
    }
    private void button_ChuyenDiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ChuyenDiActionPerformed
        // TODO add your handling code here:
        try{
        int Row = table_DanhSachPhongHoc.getSelectedRow();
        String maphongchuyen = (String) table_DanhSachPhongHoc.getValueAt(Row,1);
        
        if (textloi(txt_SoLuong.getText())==true){
        int soluongchuyen = Integer.parseInt(txt_SoLuong.getText());
        
        dichuyencsvc(macsvc, maphongchuyen,soluongchuyen);
        
        }else{
            JOptionPane.showMessageDialog(null,"Vui lòng nhập lại số lượng cần chuyển!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        }
        }catch(Exception ex){
            System.out.print(ex.toString());
            JOptionPane.showMessageDialog(null,"Vui lòng chọn phòng cần chuyển!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_button_ChuyenDiActionPerformed

    private void label_TroVeTrangTruocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_TroVeTrangTruocMouseClicked
        // TODO add your handling code here:
        Di_Chuyen_CSVC.this.setVisible(false);
        new Quan_Ly_Phong_Ban().setVisible(true);
    }//GEN-LAST:event_label_TroVeTrangTruocMouseClicked

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
            java.util.logging.Logger.getLogger(Di_Chuyen_CSVC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Di_Chuyen_CSVC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Di_Chuyen_CSVC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Di_Chuyen_CSVC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Di_Chuyen_CSVC().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_ChuyenDi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel label_TroVeTrangTruoc;
    private javax.swing.JTable table_DanhSachPhongHoc;
    private javax.swing.JTextField txt_SoLuong;
    private javax.swing.JTextArea txt_ThongTinCSVC;
    // End of variables declaration//GEN-END:variables
}
