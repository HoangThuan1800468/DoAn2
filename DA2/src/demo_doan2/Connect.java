/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo_doan2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;



public class Connect {
    public Connection getConnection(){
        Connection conn = null;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=quanlycosovatchat;user=sa;password=12345");
            if (conn != null){
                System.out.println("Ket noi thanh cong!");
            }
        }catch(Exception ex){
            System.out.print(ex.toString());
        } 
       return conn;
    }
    
    public static int BatLoiKyTu(String s){
        Pattern p = Pattern.compile("[!@#$%&*()_+=|<>?{}\\\\[\\\\]~-]");
        Matcher m = p.matcher(s);
        boolean b = m.find();
        if (b == true) {
            JOptionPane.showMessageDialog(null, "Không được nhập ký tự đặc biệt!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }
    
    
    
}
