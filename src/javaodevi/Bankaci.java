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
import java.sql.Statement;
import java.util.Vector;
import static javaodevi.bankaciduzenleme.jTable3;
import static javaodevi.bankacihesapacma.jTable4;
import static javaodevi.bankacihesapacma.txttcduzen;
import static javaodevi.bankacihesapacma.txtsifreduzen;
import static javaodevi.bankacihesapacma.txtsoyisimduzen;
import static javaodevi.bankacihesapacma.txttcduzen;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static javaodevi.bankaciduzenleme.txttcduzen1;
import static javaodevi.bankaciduzenleme.txtisimduzen1;
import static javaodevi.bankaciduzenleme.txtsoyisimduzen1;
import static javaodevi.bankaciduzenleme.txtsifreduzen1;
import static javaodevi.bankacihesapacma.txtisimduzen;

/**
 *
 * @author Enes
 */
public class Bankaci extends Kullanıcı implements Girisint {
    @Override
    public void giris(String username, String password){
        String sqlquery = "SELECT * FROM bankacitablosu WHERE bankacitc='"+username+"' AND bankacisifre='"+password+"'";
        try{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\DESKTOP-C3A52CP:1433;databaseName=javaproje;encrypt=true;trustServerCertificate=true;", "enes", "0359" );
        PreparedStatement pst = conn.prepareStatement(sqlquery);
        ResultSet rs = pst.executeQuery();
        if(!rs.next()){
            JOptionPane.showMessageDialog(null, "TC veya Şifre Hatalı");
            new bankacigiris().show();
        }else{
            JOptionPane.showMessageDialog(null, "Giriş Başarılı");
            new bankaciekran().show();
            
        }
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void tablo_update()
    {
        int c;  
        String sqlquery= "SELECT * FROM müsteritablosu";
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
                v2.add(rs.getString("müsteritc"));
                v2.add(rs.getString("müsteriad"));
                v2.add(rs.getString("müsterisoyad"));
                v2.add(rs.getString("müsterisifre"));
            }
            df.addRow(v2);
        }
        
        
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    
    }
    public void musteriekle(String tcduzen,String isimduzen,String soyisimduzen,String sifreduzen){
        String sqlquery= "INSERT INTO müsteritablosu VALUES('"+tcduzen+"','"+isimduzen+"','"+soyisimduzen+"','"+sifreduzen+"')";
        

        try{
            if(txttcduzen1.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Lütfen TC Girin");
            }else if(txtisimduzen1.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Lütfen İsim Girin");
           }else if(txtsoyisimduzen1.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Lütfen Soyisim Girin");
           }else if(txtsifreduzen1.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Lütfen Şifre Girin");
           }else{
               JOptionPane.showMessageDialog(null, "Kayıt Edildi");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\DESKTOP-C3A52CP:1433;databaseName=javaproje;encrypt=true;trustServerCertificate=true;", "enes", "0359" );
            Statement stm=conn.createStatement();
            stm.executeUpdate(sqlquery);
            txttcduzen1.setText("");
            txtisimduzen1.setText("");
            txtsoyisimduzen1.setText("");
            txtsifreduzen1.setText("");
            txttcduzen1.requestFocus();
            conn.close();
           }
            tablo_update();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void musteriduzenle(String id1){
        String sqlquery = "UPDATE müsteritablosu SET müsteritc='"+txttcduzen1.getText()+"',müsteriad='"+txtisimduzen1.getText()+"',müsterisoyad='"+txtsoyisimduzen1.getText()+"',müsterisifre='"+txtsifreduzen1.getText()+"'WHERE müsteritc='"+id1+"'";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\DESKTOP-C3A52CP:1433;databaseName=javaproje;encrypt=true;trustServerCertificate=true;", "enes", "0359" );
            Statement stm=conn.createStatement();
            stm.executeUpdate(sqlquery);
            JOptionPane.showMessageDialog(null, "Kayıt Düzenlendi");
            tablo_update();
            txttcduzen1.setText("");
            txtisimduzen1.setText("");
            txtsoyisimduzen1.setText("");
            txtsifreduzen1.setText("");
            txttcduzen1.requestFocus();
            conn.close();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void musterisilme(String id){
        String sqlquery = "DELETE FROM müsteritablosu WHERE müsteritc='"+id+"'";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\DESKTOP-C3A52CP:1433;databaseName=javaproje;encrypt=true;trustServerCertificate=true;", "enes", "0359" );
            Statement stm=conn.createStatement();
            stm.executeUpdate(sqlquery);
            JOptionPane.showMessageDialog(null, "Kayıt Silindi");
            tablo_update();
            txttcduzen1.setText("");
            txtisimduzen1.setText("");
            txtsoyisimduzen1.setText("");
            txtsifreduzen1.setText("");
            txttcduzen1.requestFocus();
            conn.close();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void tablo_update1()
    {
        int c;  
        String sqlquery= "SELECT * FROM müsterihesap";
    try{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\DESKTOP-C3A52CP:1433;databaseName=javaproje;encrypt=true;trustServerCertificate=true;", "enes", "0359" );
        Statement stm=conn.createStatement();
        PreparedStatement pst = conn.prepareStatement(sqlquery);
        ResultSet rs = pst.executeQuery();
        ResultSetMetaData rss= rs.getMetaData();
        c = rss.getColumnCount();
        DefaultTableModel df = (DefaultTableModel)jTable4.getModel();
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
    public void hesapekle(String tcduzen,String isimduzen,String soyisimduzen,String sifreduzen){
        String sqlquery= "INSERT INTO müsterihesap(müsteritc,müsteriisim,müsterisoyisim,müsterisifre,müsteribakiye) VALUES('"+tcduzen+"','"+isimduzen+"','"+soyisimduzen+"','"+sifreduzen+"','"+0+"')";
        String sql1="SELECT FROM müsteritablosu WHERE müsteritc='"+tcduzen+"'";
        try{
            if(txttcduzen.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Lütfen TC Girin");
            }if(txtisimduzen.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Lütfen İsim Girin");
            }if(txtsoyisimduzen.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Lütfen Soyisim Girin");
            }else if(txtsifreduzen.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Lütfen Şifre Girin");
            }else{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\DESKTOP-C3A52CP:1433;databaseName=javaproje;encrypt=true;trustServerCertificate=true;", "enes", "0359" );
            Statement stm=conn.createStatement();
            stm.executeUpdate(sqlquery);
            JOptionPane.showMessageDialog(null, "Kayıt Edildi");
            txttcduzen.setText("");
            txtisimduzen.setText("");
            txtsoyisimduzen.setText("");
            txtsifreduzen.setText("");
            txttcduzen.requestFocus();
            tablo_update1();
            conn.close();
             }
            

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void hesapsil(String id){
        String sqlquery = "DELETE FROM müsterihesap WHERE müsterihesap='"+id+"'";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\DESKTOP-C3A52CP:1433;databaseName=javaproje;encrypt=true;trustServerCertificate=true;", "enes", "0359" );
            Statement stm=conn.createStatement();
            stm.executeUpdate(sqlquery);
            JOptionPane.showMessageDialog(null, "Kayıt Silindi");
            tablo_update1();
            txttcduzen.setText("");
            txtisimduzen.setText("");
            txtsoyisimduzen.setText("");
            txtsifreduzen.setText("");
            txttcduzen.requestFocus();
            conn.close();

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
}
