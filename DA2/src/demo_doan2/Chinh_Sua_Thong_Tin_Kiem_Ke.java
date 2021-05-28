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
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lyhoa
 */
public class Chinh_Sua_Thong_Tin_Kiem_Ke extends javax.swing.JFrame {

    String makiemke;
    String maphong;
    public Chinh_Sua_Thong_Tin_Kiem_Ke(String makkchuaduyet) {
        initComponents();
        loadphong();
        
        makiemke =makkchuaduyet;
        loadidgv();
        TaiDuLieu(makkchuaduyet);
        loaddata(makkchuaduyet);
    }

    
    
    private Chinh_Sua_Thong_Tin_Kiem_Ke() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void loadidgv(){
         try{
        Connect a = new Connect();
        Connection conn = a.getConnection();
         if (conn!=null){
             Statement dd = conn.createStatement();
             ResultSet  kq = dd.executeQuery("select idgiaovien from taikhoan where kichhoat like N'DKH' ");  
             while(kq.next()){
                   txt_idgv.addItem(kq.getString(1));    
             }
         }
        conn.close();
        }catch(Exception ex){
            System.out.print(ex.toString());
        }
     }
     
     public void hientengv(String id){
         try{
        Connect a = new Connect();
        Connection conn = a.getConnection();
         if (conn!=null){
             Statement dd = conn.createStatement();
             ResultSet  kq = dd.executeQuery("select tengiaovien,chucvu from taikhoan where kichhoat like N'DKH' and idgiaovien like '"+id+"' ");  
             while(kq.next()){
                   txt_tengiaovien.setText(kq.getString(1));    
                   txt_chucvuhientai.setSelectedItem(kq.getString(2));
             }
         }
        conn.close();
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
                 ResultSet  kq = dd.executeQuery("Select makiemke,tengiaovien,chucvu,maphong,tinhtrangphong,ngaykiemke,idgiaovien from kiemke where makiemke like '"+makkchuaduyet+"' ");
                 while(kq.next()){
                    String makiemke = kq.getString(1);
                    String tengiaovien =kq.getString(2);
                    String chucvu =kq.getString(3);
                    String maphong =kq.getString(4);
                    String tinhtrangphong =kq.getString(5);
                    String ngaykiemke =kq.getString(6);
                    String id = kq.getString(7);
                    Statement da = conn.createStatement();            
                    ResultSet  ka = da.executeQuery("Select tenphong from phonghoc where maphong like'"+maphong+"'");
                    while(ka.next()){
                       String tenphong = ka.getString(1);
                       txt_PhongBanDuocKiemKe.setSelectedItem(tenphong);
                    }
                    txt_tengiaovien.setText(tengiaovien);
                    txt_chucvuhientai.setSelectedItem(chucvu);
                    txt_MaKiemKe.setText(makiemke);
                    txt_Maphong.setText(maphong);
                    txt_tinhtrangphong.setSelectedItem(tinhtrangphong);
                    txt_idgv.setSelectedItem(id);
                    
                    String str[] = ngaykiemke.toString().split("-");
                    txt_ngay1.setSelectedItem(str[2]);
                    txt_thang1.setSelectedItem(str[1]);
                    txt_nam1.setText(str[0]);
                }
                
            }
                      
        }catch(Exception ex){
            System.out.print(ex.toString());
        }
    }
    public void loadphong(){
        
        try{
        Connect a = new Connect();
        Connection conn = a.getConnection();
         if (conn!=null){
             Statement dd = conn.createStatement();
             ResultSet  kq = dd.executeQuery("select tenphong from  phonghoc");  
             while(kq.next()){
                   txt_PhongBanDuocKiemKe.addItem(kq.getString(1));    
             }
         }
        conn.close();
        }catch(Exception ex){
            System.out.print(ex.toString());
        }
       
    }
     
     
     
     public void TaiDuLieu(String makiemke){
        DefaultTableModel DanhSachCSVC = (DefaultTableModel)table_DanhSachCSVCCuaPhongBanDuocKiemKe.getModel();
        DanhSachCSVC.setRowCount(0);
        try{
            Connect a = new Connect();
            Connection conn = a.getConnection();
            if (conn!=null){
                Statement dd = conn.createStatement();            
                 ResultSet  kq = dd.executeQuery("Select macsvc, tencsvc, soluong, tinhtrangcsvc from kiemke_csvcphong where makiemke like '"+makiemke+"'");
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
     public boolean ktma(String makiemke){
         boolean tex =false;
         try{
             String chuoi = "";
             Connect a = new Connect();
            Connection conn = a.getConnection();
            if (conn!=null){
                Statement dd = conn.createStatement();            
                 ResultSet  kq = dd.executeQuery("Select makiemke from kiemke where makiemke like '"+makiemke+"'");
                 while(kq.next()){
                    chuoi = kq.getString(1);
                }
            }
            if(chuoi.equals("")==true){
                tex=true;
                return tex;
            }else{
                tex=false;
                return tex;
            }
             
         }catch(Exception ex){
             return tex;
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
     public boolean testloi (String a){
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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        label_TroVeTrangChu = new javax.swing.JLabel();
        button_XacNhanThongtinKiemKe = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txt_tengiaovien = new javax.swing.JTextField();
        txt_idgv = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_MaKiemKe = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_DanhSachCSVCCuaPhongBanDuocKiemKe = new javax.swing.JTable();
        txt_PhongBanDuocKiemKe = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txt_chucvuhientai = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        txt_Maphong = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_tinhtrangphong = new javax.swing.JComboBox<>();
        txt_nam1 = new javax.swing.JTextField();
        txt_ngay1 = new javax.swing.JComboBox<>();
        txt_thang1 = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Phần mềm quản lý cơ sở vật chất");
        setLocation(new java.awt.Point(500, 200));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-warehouse-50.png"))); // NOI18N
        jLabel1.setText("CHỈNH SỬA THÔNG TIN KIỂM KÊ CƠ SỞ VẬT CHẤT");

        label_TroVeTrangChu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-go-back-24.png"))); // NOI18N
        label_TroVeTrangChu.setText("Trở về ");
        label_TroVeTrangChu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_TroVeTrangChuMouseClicked(evt);
            }
        });

        button_XacNhanThongtinKiemKe.setBackground(java.awt.Color.lightGray);
        button_XacNhanThongtinKiemKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-update-24.png"))); // NOI18N
        button_XacNhanThongtinKiemKe.setText("Xác nhận chỉnh sửa");
        button_XacNhanThongtinKiemKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_XacNhanThongtinKiemKeActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel2.setText("Tên giáo viên thực hiện:");

        txt_tengiaovien.setEnabled(false);

        txt_idgv.setBackground(java.awt.Color.white);
        txt_idgv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Chọn---" }));
        txt_idgv.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txt_idgvItemStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel3.setText("Chức vụ hiện tại:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel5.setText("Ngày kiểm kê:");

        txt_MaKiemKe.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel7.setText("Mã kiểm kê:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel8.setText("Phòng học thực hiện kiểm kê:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel9.setText("Danh sách cơ sở vật chất của phòng học");

        table_DanhSachCSVCCuaPhongBanDuocKiemKe.setBackground(java.awt.Color.white);
        table_DanhSachCSVCCuaPhongBanDuocKiemKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Tên csvc", "SL", "Tình trạng khi kiểm kê"
            }
        ));
        jScrollPane2.setViewportView(table_DanhSachCSVCCuaPhongBanDuocKiemKe);

        txt_PhongBanDuocKiemKe.setBackground(java.awt.Color.white);
        txt_PhongBanDuocKiemKe.setEnabled(false);
        txt_PhongBanDuocKiemKe.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txt_PhongBanDuocKiemKeItemStateChanged(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel10.setText("Tình trạng phòng:");

        txt_chucvuhientai.setBackground(java.awt.Color.white);
        txt_chucvuhientai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Chọn---", "Giáo viên", "Quản lý", "Thanh tra", "Bảo vệ" }));
        txt_chucvuhientai.setEnabled(false);
        txt_chucvuhientai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_chucvuhientaiActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel11.setText("Mã phòng:");

        txt_Maphong.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel4.setText("ID giáo viên:");

        txt_tinhtrangphong.setBackground(java.awt.Color.white);
        txt_tinhtrangphong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tốt", "Xuống cấp", "Hỏng" }));

        txt_nam1.setText("2020");

        txt_ngay1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        txt_thang1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jLabel18.setText("/");

        jLabel19.setText("/");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_TroVeTrangChu)
                    .addComponent(jLabel9)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txt_chucvuhientai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txt_MaKiemKe)
                                            .addComponent(txt_tengiaovien)
                                            .addComponent(txt_idgv, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txt_ngay1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel18)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_thang1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel19)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_nam1))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_tinhtrangphong, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_Maphong, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txt_PhongBanDuocKiemKe, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(button_XacNhanThongtinKiemKe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(177, 177, 177))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_tengiaovien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_idgv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_chucvuhientai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_ngay1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_thang1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel18)
                                .addComponent(jLabel19)
                                .addComponent(txt_nam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txt_MaKiemKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txt_PhongBanDuocKiemKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_Maphong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txt_tinhtrangphong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(button_XacNhanThongtinKiemKe, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(label_TroVeTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void label_TroVeTrangChuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_TroVeTrangChuMouseClicked
        // TODO add your handling code here:
        Chinh_Sua_Thong_Tin_Kiem_Ke.this.setVisible(false);
        new Kiem_Ke_CSVC().setVisible(true);
    }//GEN-LAST:event_label_TroVeTrangChuMouseClicked

    private void button_XacNhanThongtinKiemKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_XacNhanThongtinKiemKeActionPerformed
        // TODO add your handling code here:
       String chucvu =txt_chucvuhientai.getSelectedItem().toString();
       String id =txt_idgv.getSelectedItem().toString();
       String tengiaovien = txt_tengiaovien.getText();
       String makiemke=txt_MaKiemKe.getText();
       String maphong = txt_Maphong.getText();
       String tinhtrangphong = txt_tinhtrangphong.getSelectedItem().toString();
       String nam = txt_nam1.getText();
       
       try{
        int nam1 = Integer.parseInt(nam);
       if(chucvu.equals("---Chọn---")){
           JOptionPane.showMessageDialog(null,"Chưa chọn chức vụ!","Thông báo",JOptionPane.ERROR_MESSAGE);
       }else if(tengiaovien.equals("")){
           JOptionPane.showMessageDialog(null,"Chưa nhập tên giáo viên!","Thông báo",JOptionPane.ERROR_MESSAGE);
       }else if(tinhtrangphong.equals("---Chọn---")){
           JOptionPane.showMessageDialog(null,"Chưa chọn tình trạng phòng!","Thông báo",JOptionPane.ERROR_MESSAGE);
       }else if(id.equals("---Chọn---")){
           JOptionPane.showMessageDialog(null,"Chưa chọn người đại diện kiểm kê!","Thông báo",JOptionPane.ERROR_MESSAGE);
           txt_chucvuhientai.setSelectedItem("---Chọn---");
           txt_tengiaovien.setText("");
       }else if(ktmaxuatfile(makiemke)==false){
           JOptionPane.showMessageDialog(null,"Đã xuất biên bản không thể cập nhật thông tin!","Thông báo",JOptionPane.ERROR_MESSAGE);
       }else if(nam.equals("")){
           JOptionPane.showMessageDialog(null,"Vui lòng nhập năm!","Thông báo",JOptionPane.ERROR_MESSAGE);
       }else if(testloi(nam)==false){
           JOptionPane.showMessageDialog(null,"Vui lòng nhập năm đúng ký tự số!","Thông báo",JOptionPane.ERROR_MESSAGE);
       }else if(nam1>=3000||nam1<2020){
           JOptionPane.showMessageDialog(null,"Vui lòng nhập năm sau 2020 và trước 2999!","Thông báo",JOptionPane.ERROR_MESSAGE);
       }else{
        try{
            String ngaykiemke1 = txt_nam1.getText().trim()+"-"+txt_thang1.getSelectedItem().toString().trim()+"-"+txt_ngay1.getSelectedItem().toString().trim();
            java.util.Date ngaykiemke = new SimpleDateFormat("yyyy-MM-dd").parse(ngaykiemke1);
            String NgayKiemKe = ngaykiemke1;
            Connect a = new Connect();
            Connection conn = a.getConnection();
            Statement dd = conn.createStatement();            
            dd.executeUpdate("update kiemke set "
            + "tengiaovien = N'"+tengiaovien+"' , idgiaovien = '"+id+"' , chucvu = N'"+chucvu+"' , maphong = '"+maphong+"' , tinhtrangphong = N'"+tinhtrangphong+"'  "
            + "where makiemke like '"+makiemke+"'");
            dd.executeUpdate("update phonghoc set tinhtrangphong = N'"+tinhtrangphong+"' where maphong like '"+maphong+"' ");
            JOptionPane.showMessageDialog(null,"Thông tin kiểm kê CSVC đã được chỉnh sửa!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
            Chinh_Sua_Thong_Tin_Kiem_Ke.this.setVisible(false);
            new Kiem_Ke_CSVC().setVisible(true);
        }catch(Exception ex){
            System.out.print(ex.toString());
            JOptionPane.showMessageDialog(null,"Lỗi!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        }
       }
       }catch(Exception ex){
           System.out.print(ex.toString());
           JOptionPane.showMessageDialog(null,"Nhập năm không đúng định dạng!","Thông báo",JOptionPane.ERROR_MESSAGE);
       }
        
    }//GEN-LAST:event_button_XacNhanThongtinKiemKeActionPerformed

    private void txt_idgvItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txt_idgvItemStateChanged
        // TODO add your handling code here:
        String id = txt_idgv.getSelectedItem().toString();
        hientengv(id);

    }//GEN-LAST:event_txt_idgvItemStateChanged

    private void txt_PhongBanDuocKiemKeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txt_PhongBanDuocKiemKeItemStateChanged
        // TODO add your handling code here:
       
    }//GEN-LAST:event_txt_PhongBanDuocKiemKeItemStateChanged

    private void txt_chucvuhientaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_chucvuhientaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_chucvuhientaiActionPerformed

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
            java.util.logging.Logger.getLogger(Chinh_Sua_Thong_Tin_Kiem_Ke.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Chinh_Sua_Thong_Tin_Kiem_Ke.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Chinh_Sua_Thong_Tin_Kiem_Ke.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Chinh_Sua_Thong_Tin_Kiem_Ke.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Chinh_Sua_Thong_Tin_Kiem_Ke().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_XacNhanThongtinKiemKe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
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
    private javax.swing.JComboBox<String> txt_idgv;
    private javax.swing.JTextField txt_nam1;
    private javax.swing.JComboBox<String> txt_ngay1;
    private javax.swing.JTextField txt_tengiaovien;
    private javax.swing.JComboBox<String> txt_thang1;
    private javax.swing.JComboBox<String> txt_tinhtrangphong;
    // End of variables declaration//GEN-END:variables
}
