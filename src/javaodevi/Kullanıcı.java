/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaodevi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Enes
 */
public class Kullanıcı implements Girisint {

    @Override
    public void giris(String username, String password) {
        String sqlquery = "";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\DESKTOP-C3A52CP:1433;databaseName=javaproje;encrypt=true;trustServerCertificate=true;", "enes", "0359");
            PreparedStatement pst = conn.prepareStatement(sqlquery);
            ResultSet rs = pst.executeQuery();
            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "TC veya Şifre Hatalı");
            } else {
                JOptionPane.showMessageDialog(null, "Giriş Başarılı");
                //new mudurekran().show(); 
                
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void paracekme(String username,String miktar1){
        int intmiktar1 = Integer.parseInt(miktar1);
        String sqlquery = "";
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\DESKTOP-C3A52CP:1433;databaseName=javaproje;encrypt=true;trustServerCertificate=true;", "enes", "0359" );
            PreparedStatement pst = conn.prepareStatement(sqlquery);
            ResultSet rs = pst.executeQuery();
            if(!rs.next()){
                JOptionPane.showMessageDialog(null, "HesapNo Hatalı");
            }else{
                String bakiye = rs.getString("müsteribakiye");
                int toplambakiye = Integer.parseInt(bakiye)- intmiktar1 ;
                   if(intmiktar1 > Integer.parseInt(bakiye)){
                    JOptionPane.showMessageDialog(null, "Hesabınızdaki Miktar Yetersiz");
                    } 
                   else {
                       String sqlquery2 = "";
                PreparedStatement pst1 = conn.prepareStatement(sqlquery2);
                pst1.execute();

                JOptionPane.showMessageDialog(null, "Para Çekildi");
                }
                

            }
            conn.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void parayatirma(String username, String miktar1){
        int intmiktar1 = Integer.parseInt(miktar1);
        String sqlquery = "";
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\DESKTOP-C3A52CP:1433;databaseName=javaproje;encrypt=true;trustServerCertificate=true;", "enes", "0359" );
            PreparedStatement pst = conn.prepareStatement(sqlquery);
            ResultSet rs = pst.executeQuery();
            if(!rs.next()){
                JOptionPane.showMessageDialog(null, "HesapNo Hatalı");
            }else{
                String bakiye = rs.getString("müsteribakiye");
                int toplambakiye = Integer.parseInt(bakiye)+ intmiktar1 ;
                String sqlquery2 = "";
                PreparedStatement pst1 = conn.prepareStatement(sqlquery2);
                pst1.execute();

                JOptionPane.showMessageDialog(null, "Para Yatırıldı");

            }
            conn.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
