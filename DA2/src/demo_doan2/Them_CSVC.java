/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo_doan2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lyhoa
 */
public class Them_CSVC extends javax.swing.JFrame {
    
   
    public Them_CSVC() {
        initComponents();
        loadphong();
        loadmaphong1();
    }
     public void loadphong(){
        
        try{
        Connect a = new Connect();
        Connection conn = a.getConnection();
         if (conn!=null){
             Statement dd = conn.createStatement();  
             ResultSet  kq = dd.executeQuery("select tenphong from phonghoc ");  
             while(kq.next()){
                   textbox_PhongLuuTru.addItem(kq.getString(1));
                   
             }
         }
        conn.close();
        }catch(Exception ex){
            System.out.print(ex.toString());
        }
       
    }
     public void loadmaphong1(){
        
        try{
        Connect a = new Connect();
        Connection conn = a.getConnection();
         if (conn!=null){
             Statement dd = conn.createStatement();  
             ResultSet  kq = dd.executeQuery("select maphong from phonghoc");  
             while(kq.next()){
                   textbox_Phong.addItem(kq.getString(1));
                   
             }
         }
        conn.close();
        }catch(Exception ex){
            System.out.print(ex.toString());
        }
    }
     public String loadmaphong(String tenphong){
        String maphong = "";
        try{
        Connect a = new Connect();
        Connection conn = a.getConnection();
         if (conn!=null){
             Statement dd = conn.createStatement();  
             ResultSet  kq = dd.executeQuery("select maphong from phonghoc where tenphong like N'"+tenphong+"'");  
             while(kq.next()){
                   maphong= kq.getString(1);
                   
             }
         }
        conn.close();
        }catch(Exception ex){
            System.out.print(ex.toString());
        }
       return maphong;
    }
     public String loadtenphong(String maphong){
        String tenphong = "";
        try{
        Connect a = new Connect();
        Connection conn = a.getConnection();
         if (conn!=null){
             Statement dd = conn.createStatement();  
             ResultSet  kq = dd.executeQuery("select tenphong from phonghoc where maphong like '"+maphong+"'");  
             while(kq.next()){
                   tenphong= kq.getString(1);
                   
             }
         }
        conn.close();
        }catch(Exception ex){
            System.out.print(ex.toString());
        }
       return tenphong;
    }
//    addItem(kq.getString(1));
     public boolean txma(String macsvc){
         boolean txma=false;
          try{
        Connect a = new Connect();
        Connection conn = a.getConnection();
        String ma="";
        
             Statement dd = conn.createStatement();  
             ResultSet  kq = dd.executeQuery("select macsvc from cosovatchat where macsvc like '"+macsvc+"'");  
             while(kq.next()){
                    ma= kq.getString(1);
         }
             if(ma.equals("")){
                 txma=true;
             }else{
                 txma=false;
                 
             }
        }catch(Exception ex){
            System.out.print(ex.toString());
            txma=false;
            JOptionPane.showMessageDialog(null,"Mã bị lỗi!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        }
         return txma;
     }
   public void Them(){
       try{
           if (textloi(txt_nam1.getText())==false||textloi(txt_nam2.getText())==false){
               JOptionPane.showMessageDialog(null,"Vui lòng nhập năm đúng định dạng số!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
           }else{
               int nam1=Integer.parseInt(txt_nam1.getText());
               int nam2=Integer.parseInt(txt_nam2.getText());
            String ngaynhan1 = txt_nam1.getText().trim()+"-"+txt_thang1.getSelectedItem().toString().trim()+"-"+txt_ngay1.getSelectedItem().toString().trim();
            String hansudung1 = txt_nam2.getText().trim()+"-"+txt_thang2.getSelectedItem().toString().trim()+"-"+txt_ngay2.getSelectedItem().toString().trim();
            java.util.Date ngaynhan = new SimpleDateFormat("yyyy-MM-dd").parse(ngaynhan1);
            java.util.Date hansudung = new SimpleDateFormat("yyyy-MM-dd").parse(hansudung1);
            if(ngaynhan.equals("")==true||hansudung.equals("")==true){
                JOptionPane.showMessageDialog(null,"Vui lòng nhập ngày nhận và hạn sử dụng cho cơ sở vật chất!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
            }else if(hansudung.before(ngaynhan)==true){
                JOptionPane.showMessageDialog(null,"Vui lòng nhập hạn sử dụng sau ngày nhận csvc!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
            }else if(nam1>=3000||nam1<2020||nam2>=3000||nam2<2020){
                JOptionPane.showMessageDialog(null,"Vui lòng nhập năm sau 2020 và trước 2999!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
            }
            else{
        
                    String tencsvc =textbox_TenCSVC.getText();
                    String MaCSVC =textbox_MaCSVC.getText();
                    int numbersoluong = (Integer) textbox_SoLuong.getValue(); 
                    String DonViTinh =textbox_DonViTinh.getSelectedItem().toString();
                    String GiaTriCSVC =textbox_GiaTriCSVC.getText();
                    String TinhTrang =textbox_TinhTrang.getSelectedItem().toString();
                    String NgayNhan =ngaynhan1;
                    String HanSuDung =hansudung1;
                    String Phongluutru =textbox_PhongLuuTru.getSelectedItem().toString();
                    String HinhThucThanhToan =textbox_HinhThucThanhToan.getSelectedItem().toString();
                    String LoaiCSVC =textbox_LoaiCSVC.getSelectedItem().toString();
                    String NguonVonTrichXuat =textbox_NguonVonTrichXuat.getSelectedItem().toString();
                    String NguoiDaiDienGiaoDich =textbox_NguoiDaiDienGiaoDich.getText();
                    String Phong = textbox_Phong.getSelectedItem().toString();
                    if(tencsvc.equals("")||DonViTinh.equals("---Chọn---")
                            ||GiaTriCSVC.equals("")||TinhTrang.equals("")||NgayNhan.equals("")
                            ||HanSuDung.equals("")||HinhThucThanhToan.equals("---Chọn---")||Phongluutru.equals("")
                            ||LoaiCSVC.equals("---Chọn---")||NguonVonTrichXuat.equals("---Chọn---")||NguoiDaiDienGiaoDich.equals("")||Phong.equals(""))
                    {
                        JOptionPane.showMessageDialog(null,"Nhập thiếu thông tin nhập lại!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                    }else if(textloi(GiaTriCSVC) == false){
                        JOptionPane.showMessageDialog(null,"Nhập sai giá csvc nhập lại!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                    }else if(numbersoluong<0){
                        JOptionPane.showMessageDialog(null,"Số lượng phải lớn hơn 0!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                         
                       int numberGiaTriCSVC = Integer.parseInt(GiaTriCSVC);  
                       
                        try{
                         Connect a = new Connect();
                         Connection conn = a.getConnection();
                         
                          if(txma(MaCSVC)==false){
                              JOptionPane.showMessageDialog(null,"Mã bị trùng!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                          }else{
                                 
                            Statement dd = conn.createStatement();
                            dd.executeUpdate("insert into cosovatchat(tencsvc,macsvc,soluong,giatri,donvi,tinhtrangcsvc,loaicsvc,hinhthucthanhtoan,phongluutru,nguontien,nguoigiaodich,maphong,ngaynhancsvc,hansudungcsvc,xoa)  "
                            + "values  (N'"+tencsvc+"','"+MaCSVC+"' , "+numbersoluong+", "+numberGiaTriCSVC+",N'"+DonViTinh+"',N'"+TinhTrang+"',N'"+LoaiCSVC+"', N'"+HinhThucThanhToan+"' , N'"+Phongluutru+"' ,N'"+NguonVonTrichXuat+"', N'"+NguoiDaiDienGiaoDich+"' ,N'"+Phong+"','"+NgayNhan+"','"+HanSuDung+"',0)");
                            JOptionPane.showMessageDialog(null,"Đã cập thêm mới csvc!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                            Them_CSVC.this.setVisible(false);
                            new Danh_Sach_CSVC().setVisible(true);
                                 
                         
                          }
                       }catch(Exception ex){
                         System.out.print(ex.toString());
                         JOptionPane.showMessageDialog(null,"Vui lòng nhập năm theo đúng định dạng!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                       }                     
                      }
                    }
           }
       }catch(Exception ex){
           JOptionPane.showMessageDialog(null,"Vui lòng nhập năm theo đúng định dạng!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
       }
    }
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
    
    
   
    
    
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        textbox_TenCSVC = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        textbox_NguoiDaiDienGiaoDich = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        textbox_GiaTriCSVC = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        textbox_MaCSVC = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        label_TroVeTrangTruoc = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btn_Them = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        textbox_TinhTrang = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        textbox_PhongLuuTru = new javax.swing.JComboBox<>();
        textbox_SoLuong = new javax.swing.JSpinner();
        textbox_LoaiCSVC = new javax.swing.JComboBox<>();
        textbox_DonViTinh = new javax.swing.JComboBox<>();
        textbox_NguonVonTrichXuat = new javax.swing.JComboBox<>();
        textbox_HinhThucThanhToan = new javax.swing.JComboBox<>();
        textbox_Phong = new javax.swing.JComboBox<>();
        txt_ngay1 = new javax.swing.JComboBox<>();
        txt_thang1 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txt_nam1 = new javax.swing.JTextField();
        txt_thang2 = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txt_nam2 = new javax.swing.JTextField();
        txt_ngay2 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Phần mềm quản lý cơ sở vật chất");
        setLocation(new java.awt.Point(600, 300));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setText("Hình thức thanh toán:");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Tên CSVC:");

        jLabel15.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel15.setText("Nguồn tiền:");

        jLabel13.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel13.setText("Loại CSVC:");

        jLabel16.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel16.setText("Người đại diện giao dịch:");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Tổng giá trị:");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Mã CSVC:");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Tình trạng:");

        jLabel14.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel14.setText("Mã phòng:");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Đơn vị tính:");

        label_TroVeTrangTruoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-go-back-24.png"))); // NOI18N
        label_TroVeTrangTruoc.setText("Trở về");
        label_TroVeTrangTruoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_TroVeTrangTruocMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Số lượng:");

        jLabel11.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel11.setText("HSD:");

        btn_Them.setBackground(java.awt.Color.lightGray);
        btn_Them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-add-list-24.png"))); // NOI18N
        btn_Them.setText("Thêm");
        btn_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setText("Ngày nhận:");

        textbox_TinhTrang.setBackground(java.awt.Color.white);
        textbox_TinhTrang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tốt", "Hỏng", "Bảo trì", "Bỏ", "Đang bảo trì", "Đã bảo trì" }));

        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setText("Tên phòng:");

        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-product-48.png"))); // NOI18N
        jLabel1.setText("THÊM CƠ SỞ VẬT CHẤT");

        textbox_PhongLuuTru.setBackground(java.awt.Color.white);
        textbox_PhongLuuTru.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                textbox_PhongLuuTruItemStateChanged(evt);
            }
        });

        textbox_SoLuong.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        textbox_LoaiCSVC.setBackground(java.awt.Color.white);
        textbox_LoaiCSVC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Chọn---", "Bàn", "Tủ", "Thiết bị điện", "Giấy tờ", "Dụng cụ thực hành", "Mô hình" }));

        textbox_DonViTinh.setBackground(java.awt.Color.white);
        textbox_DonViTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Chọn---", "Cái", "Lọ", "Chai", "Hộp" }));

        textbox_NguonVonTrichXuat.setBackground(java.awt.Color.white);
        textbox_NguonVonTrichXuat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Chọn---", "Quỹ trường", "Quỹ khuyến học", "Quỹ bộ Giáo Dục", "Quỹ phụ huynh" }));

        textbox_HinhThucThanhToan.setBackground(java.awt.Color.white);
        textbox_HinhThucThanhToan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Chọn---", "Tiền mặt", "Chuyển khoản", "Ghi nợ", "Trả góp" }));

        textbox_Phong.setBackground(java.awt.Color.white);
        textbox_Phong.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                textbox_PhongItemStateChanged(evt);
            }
        });

        txt_ngay1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        txt_thang1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        jLabel12.setText("/");

        jLabel17.setText("/");

        txt_nam1.setText("2020");

        txt_thang2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        jLabel20.setText("/");

        jLabel21.setText("/");

        txt_nam2.setText("2020");

        txt_ngay2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(99, 99, 99)
                                .addComponent(textbox_PhongLuuTru, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(100, 100, 100)
                                .addComponent(textbox_Phong, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel10))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textbox_HinhThucThanhToan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textbox_NguoiDaiDienGiaoDich)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(288, 288, 288)
                                        .addComponent(jLabel14))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(204, 204, 204)
                                        .addComponent(jLabel15))
                                    .addComponent(jLabel9))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_ngay1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_thang1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_nam1))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel13))
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(0, 47, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(textbox_GiaTriCSVC)
                                            .addComponent(textbox_TinhTrang, javax.swing.GroupLayout.Alignment.TRAILING, 0, 174, Short.MAX_VALUE)
                                            .addComponent(textbox_TenCSVC, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(textbox_LoaiCSVC, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel4)
                                                    .addComponent(jLabel7)
                                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(textbox_MaCSVC, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                                                    .addComponent(textbox_SoLuong)
                                                    .addComponent(textbox_DonViTinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(82, 82, 82)
                                                .addComponent(textbox_NguonVonTrichXuat, 0, 1, Short.MAX_VALUE)))))))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(label_TroVeTrangTruoc)
                                .addGap(141, 141, 141)
                                .addComponent(btn_Them, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(70, 70, 70)
                                .addComponent(txt_ngay2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_thang2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_nam2)))
                        .addGap(6, 6, 6))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label_TroVeTrangTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(textbox_TenCSVC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(textbox_MaCSVC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(textbox_TinhTrang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(textbox_SoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel7)
                            .addComponent(textbox_LoaiCSVC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textbox_DonViTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(textbox_GiaTriCSVC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(textbox_NguonVonTrichXuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel14)
                            .addComponent(textbox_PhongLuuTru, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textbox_Phong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txt_ngay1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_thang1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel17)
                            .addComponent(txt_nam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_ngay2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_thang2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel20)
                                .addComponent(jLabel21)
                                .addComponent(txt_nam2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(textbox_HinhThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(textbox_NguoiDaiDienGiaoDich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void label_TroVeTrangTruocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_TroVeTrangTruocMouseClicked
        // TODO add your handling code here:
        Them_CSVC.this.setVisible(false);
        new Danh_Sach_CSVC().setVisible(true);
    }//GEN-LAST:event_label_TroVeTrangTruocMouseClicked

    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
        // TODO add your handling code here:
        Them();
    }//GEN-LAST:event_btn_ThemActionPerformed

    private void textbox_PhongLuuTruItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_textbox_PhongLuuTruItemStateChanged
        // TODO add your handling code here:
        String tenphong = textbox_PhongLuuTru.getSelectedItem().toString();
        textbox_Phong.setSelectedItem(loadmaphong(tenphong));
        
    }//GEN-LAST:event_textbox_PhongLuuTruItemStateChanged

    private void textbox_PhongItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_textbox_PhongItemStateChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_textbox_PhongItemStateChanged

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
            java.util.logging.Logger.getLogger(Them_CSVC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Them_CSVC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Them_CSVC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Them_CSVC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Them_CSVC().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Them;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel label_TroVeTrangTruoc;
    private javax.swing.JComboBox<String> textbox_DonViTinh;
    private javax.swing.JTextField textbox_GiaTriCSVC;
    private javax.swing.JComboBox<String> textbox_HinhThucThanhToan;
    private javax.swing.JComboBox<String> textbox_LoaiCSVC;
    private javax.swing.JTextField textbox_MaCSVC;
    private javax.swing.JTextField textbox_NguoiDaiDienGiaoDich;
    private javax.swing.JComboBox<String> textbox_NguonVonTrichXuat;
    private javax.swing.JComboBox<String> textbox_Phong;
    private javax.swing.JComboBox<String> textbox_PhongLuuTru;
    private javax.swing.JSpinner textbox_SoLuong;
    private javax.swing.JTextField textbox_TenCSVC;
    private javax.swing.JComboBox<String> textbox_TinhTrang;
    private javax.swing.JTextField txt_nam1;
    private javax.swing.JTextField txt_nam2;
    private javax.swing.JComboBox<String> txt_ngay1;
    private javax.swing.JComboBox<String> txt_ngay2;
    private javax.swing.JComboBox<String> txt_thang1;
    private javax.swing.JComboBox<String> txt_thang2;
    // End of variables declaration//GEN-END:variables
}
