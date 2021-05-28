/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo_doan2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lyhoa
 */
public class Xuat_Van_Ban_Bao_Tri extends javax.swing.JFrame {

    /**
     * Creates new form Xuat_Van_Ban_Bao_Tri
     */
    public Xuat_Van_Ban_Bao_Tri() {
        initComponents();
        loadngay();
        loadtengv();
    }

    public void loadtengv(){
        try{
        Connect a = new Connect();
        Connection conn = a.getConnection();
         if (conn!=null){
             Statement dd = conn.createStatement();  
             ResultSet  kq = dd.executeQuery("select tengiaovien from taikhoan  where  kichhoat = 'DKH' ");  
             while(kq.next()){
                   txt_nguoilapbienban.addItem(kq.getString(1));   
             }
         }
        conn.close();
        }catch(Exception ex){
            System.out.print(ex.toString());
        }
    }
    public void hienid (String ten){
        try{
        Connect a = new Connect();
        Connection conn = a.getConnection();
         if (conn!=null){
             Statement dd = conn.createStatement();  
             ResultSet  kq = dd.executeQuery("select idgiaovien from taikhoan  where  kichhoat = 'DKH' and tengiaovien like N'"+ten+"' ");  
             while(kq.next()){
                   txt_idgiaovien.setText(kq.getString(1));   
             }
         }
        conn.close();
        }catch(Exception ex){
            System.out.print(ex.toString());
        }
    }
    public void loadngay(){
        
        try{
        Connect a = new Connect();
        Connection conn = a.getConnection();
         if (conn!=null){
             Statement dd = conn.createStatement();  
             ResultSet  kq = dd.executeQuery("select distinct ngaybaotri from baotrivasuachua  where  macsvc in (select macsvc from cosovatchat where tinhtrangcsvc = N'Bảo trì') ");  
             while(kq.next()){
                 String ngay = kq.getString(1);
                 String str[] = ngay.toString().split("-");
                 String ngay1=str[2]+"-"+str[1]+"-"+str[0];
                   cb_ngay.addItem(ngay1);   
             }
         }
        conn.close();
        }catch(Exception ex){
            System.out.print(ex.toString());
        }
       
    }
    public void loadcsvc(String ngay){
        DefaultTableModel model = (DefaultTableModel)tb_danhsachbaotri.getModel();
         try{
            Connect a = new Connect();
            Connection conn = a.getConnection();
            if (conn!=null){
                Statement dd = conn.createStatement();            
                 ResultSet  kq = dd.executeQuery("select macsvc from baotrivasuachua where ngaybaotri like '"+ngay+"' and macsvc in (select macsvc from cosovatchat where tinhtrangcsvc = N'Bảo trì')  ");
                 while(kq.next()){
                    String macsvc = kq.getString(1);
                    Statement da = conn.createStatement(); 
                    ResultSet  csvc = da.executeQuery("select tencsvc,tinhtrangcsvc,soluong,mota from cosovatchat where macsvc like '"+macsvc+"'");
                    while(csvc.next()){
                      String tencsvc=csvc.getString(1);
                      String tinhtrangcsvc =csvc.getString(2);
                      int soluong =csvc.getInt(3);
                      String mota =csvc.getString(4);
                      model.addRow(new Object[]{tencsvc, macsvc,soluong,tinhtrangcsvc,mota});
                 }
                   
                }
                
            }
                      
        }catch(Exception ex){
            System.out.print(ex.toString());
        }
        
    } 
    
    public void taobienban(String ngay,String mabienban,String nguoilapbienban,String idgiaovien,String bensuachua,String nguoidaidien,String sdt,String diachi,String ngayhoanthanh){
         int rows = tb_danhsachbaotri.getRowCount();
        try{
        Connect a = new Connect();
        Connection conn = a.getConnection();
             Statement dd = conn.createStatement();
             dd.executeUpdate("insert into xuatvanban (loaivanban,mavanban,idgiaovien,ngaybaotri,bensuachua,nguoidaidien,sdt,diachi,ngayhoanthanh)"
             + " values (N'Biên bản bảo trì','"+mabienban+"','"+idgiaovien+"','"+ngay+"',N'"+bensuachua+"',N'"+nguoidaidien+"','"+sdt+"',N'"+diachi+"','"+ngayhoanthanh+"')");
             
              Statement da = conn.createStatement();
              ResultSet  kq = da.executeQuery("select macsvc from baotrivasuachua where ngaybaotri like '"+ngay+"' and macsvc in (select macsvc from cosovatchat where tinhtrangcsvc = N'Bảo trì')  ");
                    while(kq.next()){
                    String macsvc = kq.getString(1);
                    Statement db = conn.createStatement();
                    db.executeUpdate("update cosovatchat set tinhtrangcsvc = N'Đang bảo trì', mabaotri = '"+mabienban.trim()+"' where macsvc like '"+macsvc+"'");
                    Statement dc = conn.createStatement();
                    dc.executeUpdate("update baotrivasuachua set mabaotri = '"+mabienban+"' where macsvc like '"+macsvc+"'");
                    Statement dg = conn.createStatement();
                    ResultSet  hkt = dg.executeQuery("select mota from cosovatchat where macsvc like '"+macsvc+"'   ");
                    while(hkt.next()){
                        String motatinhtrang = hkt.getString(1);
                        Statement de = conn.createStatement();
                        de.executeUpdate("insert into xuatvanban_csvc (macsvc,mavanban,mota) values ('"+macsvc+"','"+mabienban+"',N'"+motatinhtrang+"') "); 
                    }
                    
                    
                    
                }
                JOptionPane.showMessageDialog(null,"Đã thêm biên bản bảo trì!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                Xuat_Van_Ban_Bao_Tri.this.setVisible(false);
                new Bien_Ban_Bao_Tri(mabienban).setVisible(true); 
                 
        }catch(Exception ex){
            System.out.print(ex.toString());
        }
        
        
    }
    
    public boolean batloisdt(String sdt){
        boolean loi = false;
        int number;
        int dodai=sdt.length();
        try{
            if(dodai==10){
            number = Integer.parseInt(sdt);
            
            loi = true;
            }else{
                loi=false;
            }
        }catch(Exception ex){
            loi = false;
        }
        return loi;
    }
    public boolean batloiid(String idgiaovien){
        boolean loi=false;
        try{
            Connect a = new Connect();
            Connection conn = a.getConnection();
            if (conn!=null){
                Statement dd = conn.createStatement();            
                 ResultSet  kq = dd.executeQuery("select idgiaovien from taikhoan Where idgiaovien like '"+idgiaovien+"'");
                 while(kq.next()){
                    String ID = kq.getString(1);
                   if (ID.equals("")==true){
                       loi = false;
                   }else{
                       loi=true;
                   }
                }
                
            }
        }catch(Exception ex){
            loi=false;
        }
            return loi;
    }
    public boolean batloimabienban(String mabienban){
        
        try{
            boolean loi = true;
            Connect a = new Connect();
            Connection conn = a.getConnection();
            if (conn!=null){
                Statement dc = conn.createStatement();            
                 ResultSet  ka = dc.executeQuery("select mavanban from xuatvanban,kiemke where makiemke like '"+mabienban+"' or mavanban like '"+mabienban+"' ");
                 while(ka.next()){
                    String ma = ka.getString(1);
                    if(ma.equals("")){
                        loi = true;
                    }else {
                        loi = false;
                    }
                }
            }
            return loi;
        }catch(Exception ex){
            boolean loi = false;
            System.out.println(ex);
            loi = false;
            return loi;
        }
            
    }
    public boolean batloingay(String ngayhoanthanh,String ngaybaotri){
        boolean loi=false;
        
        try{
            java.util.Date ngayhoanthanh1 = new SimpleDateFormat("yyyy-MM-dd").parse(ngayhoanthanh);
            java.util.Date ngaybaotri1 = new SimpleDateFormat("yyyy-MM-dd").parse(ngaybaotri);
                   if (ngaybaotri1.before(ngayhoanthanh1)==true){
                       loi = true;
                   }else{
                       loi=false;
                   }  
        }catch(Exception ex){
            loi=false;
        }
            return loi;
    }
    public boolean textngay(String ngay){
        boolean loi=false;
        try{
            java.util.Date ngayhoanthanh = new SimpleDateFormat("yyyy-MM-dd").parse(ngay);
            loi = true;
            return loi;   
        }catch(Exception ex){
            loi=false;
            return loi;
        }
           
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        txt_mabienban = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_bensuachua = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_sdt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_danhsachbaotri = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        cb_ngay = new javax.swing.JComboBox<>();
        label_trove = new javax.swing.JLabel();
        btn_Xac_Nhan = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_diachi = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt_nguoidaidien = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txt_idgiaovien = new javax.swing.JTextField();
        txt_nguoilapbienban = new javax.swing.JComboBox<>();
        txt_nam1 = new javax.swing.JTextField();
        txt_ngay1 = new javax.swing.JComboBox<>();
        txt_thang1 = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Phần mềm quản lý cơ sở vật chất");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-maintenance-60.png"))); // NOI18N
        jLabel4.setText("THÔNG TIN XUẤT BIÊN BẢN BẢO TRÌ CSVC");

        jLabel2.setText("Mã biên bản: ");

        jLabel3.setText("Bên sửa chữa:");

        jLabel6.setText("Sđt:");

        jLabel7.setText("Địa chỉ:");

        tb_danhsachbaotri.setBackground(java.awt.Color.white);
        tb_danhsachbaotri.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên csvc", "Mã csvc", "Số lượng", "Tình trạng", "Mô tả"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tb_danhsachbaotri.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_danhsachbaotriMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_danhsachbaotri);

        jLabel8.setText("Chọn danh sách bảo trì của ngày: ");

        cb_ngay.setBackground(java.awt.Color.white);
        cb_ngay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Chọn---" }));
        cb_ngay.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_ngayItemStateChanged(evt);
            }
        });
        cb_ngay.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                cb_ngayInputMethodTextChanged(evt);
            }
        });

        label_trove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-go-back-24.png"))); // NOI18N
        label_trove.setText("Trở về");
        label_trove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_troveMouseClicked(evt);
            }
        });

        btn_Xac_Nhan.setBackground(java.awt.Color.lightGray);
        btn_Xac_Nhan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/demo_doan2/image/icons8-check-all-24.png"))); // NOI18N
        btn_Xac_Nhan.setText("Xác nhận");
        btn_Xac_Nhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Xac_NhanActionPerformed(evt);
            }
        });

        txt_diachi.setColumns(20);
        txt_diachi.setRows(5);
        jScrollPane2.setViewportView(txt_diachi);

        jLabel10.setText("Người lập biên bản:");

        jLabel11.setText("ID giáo viên:");

        jLabel13.setText("Người đại diện:");

        jLabel14.setText("Ngày hoàn thành:");

        txt_idgiaovien.setEnabled(false);

        txt_nguoilapbienban.setBackground(java.awt.Color.white);
        txt_nguoilapbienban.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---chọn---" }));
        txt_nguoilapbienban.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txt_nguoilapbienbanItemStateChanged(evt);
            }
        });

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(cb_ngay, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 805, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_trove))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel2))
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_nguoilapbienban, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_idgiaovien)
                                    .addComponent(txt_mabienban)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_nguoidaidien, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txt_sdt, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                                    .addComponent(txt_bensuachua)
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
                            .addComponent(btn_Xac_Nhan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(362, 362, 362))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cb_ngay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_mabienban, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txt_nguoilapbienban, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_idgiaovien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_bensuachua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_nguoidaidien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_ngay1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_thang1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel18)
                                .addComponent(jLabel19)
                                .addComponent(txt_nam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addComponent(btn_Xac_Nhan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_trove, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void label_troveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_troveMouseClicked
        // TODO add your handling code here:
         Xuat_Van_Ban_Bao_Tri.this.setVisible(false);
        new Xuat_File().setVisible(true);
        
    }//GEN-LAST:event_label_troveMouseClicked

    private void btn_Xac_NhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Xac_NhanActionPerformed
        // TODO add your handling code here:
        if(cb_ngay.getSelectedItem().toString().equals("---Chọn---")){
            JOptionPane.showMessageDialog(null,"Không có dữ liệu!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        }else{
        String mabienban = txt_mabienban.getText();
        String nguoilapbienban = txt_nguoilapbienban.getSelectedItem().toString();
        String idgiaovien = txt_idgiaovien.getText();
        String bensuachua = txt_bensuachua.getText();
        String nguoidaidien = txt_nguoidaidien.getText();
        String sdt = txt_sdt.getText();
        String diachi = txt_diachi.getText();
        
        try{
            String ngayhoanthanh1 = txt_nam1.getText().trim()+"-"+txt_thang1.getSelectedItem().toString().trim()+"-"+txt_ngay1.getSelectedItem().toString().trim();
            java.util.Date ngayhoanthanh2 = new SimpleDateFormat("yyyy-MM-dd").parse(ngayhoanthanh1);
            String ngayhoanthanh = ngayhoanthanh1;
            String ngay = cb_ngay.getSelectedItem().toString();
            String str[] = ngay.toString().split("-");
            String ngay1=str[2]+"-"+str[1]+"-"+str[0];
            int nam1 = Integer.parseInt(txt_nam1.getText().trim());
            if(cb_ngay.getSelectedItem().toString().equals("---Chọn---")){
            JOptionPane.showMessageDialog(null,"Không có dữ liệu!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
            }
            else if(mabienban.equals("")||nguoilapbienban.equals("---Chọn---")||idgiaovien.equals("")||bensuachua.equals("")||nguoidaidien.equals("")||sdt.equals("")||diachi.equals("")){
                JOptionPane.showMessageDialog(null,"Vui lòng nhập đầy đủ và chính xác thông tin!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
            }else if(batloisdt(sdt)==false){
               JOptionPane.showMessageDialog(null,"Nhập đúng số điện thoại!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
            } else if (batloiid(idgiaovien)==false){
               JOptionPane.showMessageDialog(null,"Nhập đúng ID giáo viên!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
            }else if (batloimabienban(mabienban)==false){
               JOptionPane.showMessageDialog(this,"Mã biên bản bị trùng!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
            }else if(textngay(ngayhoanthanh)==false){
                JOptionPane.showMessageDialog(null,"Nhập năm đúng định dạng!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
            }else if(nam1>=3000||nam1<2020){
                JOptionPane.showMessageDialog(null,"Vui lòng nhập năm sau 2020 và trước 2999!","Thông báo",JOptionPane.ERROR_MESSAGE);
            }
            else if (batloingay(ngayhoanthanh,ngay1)==false){
               JOptionPane.showMessageDialog(null,"Nhập ngày hoàn thành sau ngày bảo trì!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
            }else{
                taobienban(ngay1,mabienban,nguoilapbienban,idgiaovien,bensuachua,nguoidaidien,sdt,diachi,ngayhoanthanh);
                
            }
        
        }catch(Exception ex){
            System.out.print(ex.toString());
            JOptionPane.showMessageDialog(null,"Nhập năm không đúng định dạng!","Thông báo",JOptionPane.ERROR_MESSAGE);
        }
        }
    }//GEN-LAST:event_btn_Xac_NhanActionPerformed

    private void cb_ngayInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_cb_ngayInputMethodTextChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cb_ngayInputMethodTextChanged

    private void cb_ngayItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_ngayItemStateChanged
        // TODO add your handling code here:
         String ngay = cb_ngay.getSelectedItem().toString();
         String str[] = ngay.toString().split("-");
         String ngay1=str[2]+"-"+str[1]+"-"+str[0];
         DefaultTableModel model = (DefaultTableModel)tb_danhsachbaotri.getModel();
         model.setRowCount(0);
         loadcsvc(ngay1);
         
    }//GEN-LAST:event_cb_ngayItemStateChanged

    private void tb_danhsachbaotriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_danhsachbaotriMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_danhsachbaotriMouseClicked

    private void txt_nguoilapbienbanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txt_nguoilapbienbanItemStateChanged
        // TODO add your handling code here:
        String ten = txt_nguoilapbienban.getSelectedItem().toString();
        hienid(ten);
    }//GEN-LAST:event_txt_nguoilapbienbanItemStateChanged

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
            java.util.logging.Logger.getLogger(Xuat_Van_Ban_Bao_Tri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Xuat_Van_Ban_Bao_Tri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Xuat_Van_Ban_Bao_Tri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Xuat_Van_Ban_Bao_Tri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Xuat_Van_Ban_Bao_Tri().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Xac_Nhan;
    private javax.swing.JComboBox<String> cb_ngay;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel label_trove;
    private javax.swing.JTable tb_danhsachbaotri;
    private javax.swing.JTextField txt_bensuachua;
    private javax.swing.JTextArea txt_diachi;
    private javax.swing.JTextField txt_idgiaovien;
    private javax.swing.JTextField txt_mabienban;
    private javax.swing.JTextField txt_nam1;
    private javax.swing.JComboBox<String> txt_ngay1;
    private javax.swing.JTextField txt_nguoidaidien;
    private javax.swing.JComboBox<String> txt_nguoilapbienban;
    private javax.swing.JTextField txt_sdt;
    private javax.swing.JComboBox<String> txt_thang1;
    // End of variables declaration//GEN-END:variables
}
