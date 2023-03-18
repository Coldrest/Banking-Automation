/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaodevi;

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
import static javaodevi.musterigiris.txtmüsteritc;
import static javaodevi.musterihesapgörüntüle.jTable3;
import static javaodevi.musterikredi.jTable1;
import static javaodevi.musterikredi.txthesap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Enes
 */
public class Musteri extends Kullanıcı implements Girisint {
    public void tablo_update()
    {
        int c;  
        String sqlquery= "SELECT * FROM müsterihesap WHERE müsteritc='"+txtmüsteritc.getText()+"'";
    try{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\DESKTOP-C3A52CP:1433;databaseName=javaproje;encrypt=true;trustServerCertificate=true;", "enes", "0359" );
        Statement stm=conn.createStatement();
        PreparedStatement pst = conn.prepareStatement(sqlquery);
        ResultSet rs = pst.executeQuery();
        ResultSetMetaData rss= rs.getMetaData();
        c = rss.getColumnCount();
        DefaultTableModel df = (DefaultTableModel)jTable3.getModel();
        df.setRowCount(0);
        while(rs.next())
        {
            Vector v2 = new Vector();
            
            for(int a=1;a<=c; a++)
            {
                v2.add(rs.getString("müsterihesap"));
                v2.add(rs.getString("müsteritc"));
                v2.add(rs.getString("müsteriisim"));
                v2.add(rs.getString("müsterisoyisim"));
                v2.add(rs.getString("müsterisifre"));
                v2.add(rs.getString("müsteribakiye"));
            }
            df.addRow(v2);
        }
        
        
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    
    }
    
    @Override
    public void giris(String username, String password){
        String sqlquery = "SELECT * FROM müsteritablosu WHERE müsteritc='"+username+"' AND müsterisifre='"+password+"'";
        try{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\DESKTOP-C3A52CP:1433;databaseName=javaproje;encrypt=true;trustServerCertificate=true;", "enes", "0359" );
        PreparedStatement pst = conn.prepareStatement(sqlquery);
        ResultSet rs = pst.executeQuery();
        if(!rs.next()){
            JOptionPane.showMessageDialog(null, "TC veya Şifre Hatalı");
            new musterigiris().show();
        }else{
            JOptionPane.showMessageDialog(null, "Giriş Başarılı");
            
            
            new musteriekran().show();
            
        }
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    @Override
    public void parayatirma(String username, String miktar1){
        int intmiktar1 = Integer.parseInt(miktar1);
        String sqlquery = "SELECT * FROM müsterihesap WHERE müsterihesap='"+username+"'";
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
                String sqlquery2 = "UPDATE müsterihesap SET müsteribakiye='"+toplambakiye+"'WHERE müsterihesap='"+username+"'";
                PreparedStatement pst1 = conn.prepareStatement(sqlquery2);
                pst1.execute();

                JOptionPane.showMessageDialog(null, "Para Yatırıldı");

            }
            conn.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    @Override
    public void paracekme(String username,String miktar1){
        int intmiktar1 = Integer.parseInt(miktar1);
        String sqlquery = "SELECT * FROM müsterihesap WHERE müsterihesap='"+username+"'";
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
                       String sqlquery2 = "UPDATE müsterihesap SET müsteribakiye='"+toplambakiye+"'WHERE müsterihesap='"+username+"'";
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
    public void tablo_update1()
    {
        int c;  
        String sqlquery= "SELECT * FROM müsterihesap WHERE müsteritc='"+txtmüsteritc.getText()+"' AND müsterikredidurum='1'";
    try{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\DESKTOP-C3A52CP:1433;databaseName=javaproje;encrypt=true;trustServerCertificate=true;", "enes", "0359" );
        Statement stm=conn.createStatement();
        PreparedStatement pst = conn.prepareStatement(sqlquery);
        ResultSet rs = pst.executeQuery();
        ResultSetMetaData rss= rs.getMetaData();
        c = rss.getColumnCount();
        DefaultTableModel df = (DefaultTableModel)jTable1.getModel();
        df.setRowCount(0);
        while(rs.next())
        {
            
            Vector v2 = new Vector();
            
            for(int a=1;a<=c; a++)
            {
                v2.add(rs.getString("müsterihesap"));
                v2.add(rs.getString("müsteritc"));
                v2.add(rs.getString("müsterikredisonuc"));
                
            }
            df.addRow(v2);
        }
        
        
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    
    }
    public void müsterikrd(String username1) throws ClassNotFoundException, SQLException{
        String sqlquery = "SELECT * FROM müsterihesap WHERE müsterihesap='"+username1+"' ";
              
        try{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\DESKTOP-C3A52CP:1433;databaseName=javaproje;encrypt=true;trustServerCertificate=true;", "enes", "0359" );
        PreparedStatement pst = conn.prepareStatement(sqlquery);
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            String sqlquery2="UPDATE müsterihesap SET müsterikredidurum = (case müsterikredidurum "
                    + "                                                      when '0' then '1'"
                    + "                                                          end)WHERE müsterihesap='"+txthesap.getText()+"' ";
            PreparedStatement pst1 = conn.prepareStatement(sqlquery2);
            pst1.execute();
            JOptionPane.showMessageDialog(null, "Başvuru Yapıldı");
            tablo_update1();
        if(rs.next())  
        {
            String sqlquery3="UPDATE müsterihesap SET müsterikredidurum = (case müsterikredidurum " 
                    + "                                                          when '1' then '1'" 
                    + "                                                end)WHERE müsterihesap='"+txthesap.getText()+"'" ;
            PreparedStatement pst2 = conn.prepareStatement(sqlquery3);
            pst2.execute();
            JOptionPane.showMessageDialog(null, "Zaten Başvuru Yapmışsınız");
            tablo_update1();
        }
            
        } else{
            JOptionPane.showMessageDialog(null, "Hesap No Hatalı");
        }
        }catch(SQLException e){
            
            JOptionPane.showMessageDialog(null, "Zaten Başvuru Yapmışsınız");
            
           }
        }
    }
    

