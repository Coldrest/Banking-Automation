/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaodevi;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import static javaodevi.mudurduzenleme.jTable1;
import static javaodevi.mudurduzenleme.txttcduzen;
import static javaodevi.mudurduzenleme.txtsifreduzen;
import static javaodevi.mudurkredi.jTable2;
import static javaodevi.mudurkredi.txthesapno;

/**
 *
 * @author Enes
 */
public class Mudur extends Kullanıcı implements Girisint {

    @Override
    public void giris(String username, String password) {
        String sqlquery = "SELECT * FROM mudurtablosu WHERE mudurtc='" + username + "' AND mudursifre='" + password + "'";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\DESKTOP-C3A52CP:1433;databaseName=javaproje;encrypt=true;trustServerCertificate=true;", "enes", "0359");
            PreparedStatement pst = conn.prepareStatement(sqlquery);
            ResultSet rs = pst.executeQuery();
            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "TC veya Şifre Hatalı");
                new mudurgiris().show();
            } else {
                JOptionPane.showMessageDialog(null, "Giriş Başarılı");
                new mudurekran().show();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void tablo_update() {
        int c;
        String sqlquery = "SELECT * FROM bankacitablosu";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\DESKTOP-C3A52CP:1433;databaseName=javaproje;encrypt=true;trustServerCertificate=true;", "enes", "0359");
            Statement stm = conn.createStatement();
            PreparedStatement pst = conn.prepareStatement(sqlquery);
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rss = rs.getMetaData();
            c = rss.getColumnCount();
            DefaultTableModel df = (DefaultTableModel) jTable1.getModel();
            df.setRowCount(0);
            while (rs.next()) {
                Vector v2 = new Vector();

                for (int a = 1; a <= c; a++) {
                    v2.add(rs.getString("bankacitc"));
                    v2.add(rs.getString("bankacisifre"));
                }
                df.addRow(v2);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    

    public void ekleme(String tcduzen, String sifreduzen) {
        String sqlquery = "INSERT INTO bankacitablosu VALUES('" + tcduzen + "','" + sifreduzen + "')";

        try {

            if (txttcduzen.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Lütfen TC Girin");
            } else if (txtsifreduzen.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Lütfen Şifre Girin");
            } else {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\DESKTOP-C3A52CP:1433;databaseName=javaproje;encrypt=true;trustServerCertificate=true;", "enes", "0359");
                Statement stm = conn.createStatement();
                stm.executeUpdate(sqlquery);
                JOptionPane.showMessageDialog(null, "Kayıt Edildi");

                txttcduzen.setText("");
                txtsifreduzen.setText("");
                txttcduzen.requestFocus();
                conn.close();

            }
            tablo_update();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void duzenleme(String tcduzen, String sifreduzen, String id1) {

        String sqlquery = "UPDATE bankacitablosu SET bankacitc='"+tcduzen+"',bankacisifre='"+sifreduzen+"' WHERE bankacitc='"+id1+"'";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\DESKTOP-C3A52CP:1433;databaseName=javaproje;encrypt=true;trustServerCertificate=true;", "enes", "0359");
            Statement stm = conn.createStatement();
            stm.executeUpdate(sqlquery);
            JOptionPane.showMessageDialog(null, "Kayıt Düzenlendi");
            tablo_update();
            txttcduzen.setText("");
            txtsifreduzen.setText("");
            txttcduzen.requestFocus();
            conn.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void silme(String id){
        String sqlquery = "DELETE FROM bankacitablosu WHERE bankacitc='"+id+"'";
        
        try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\DESKTOP-C3A52CP:1433;databaseName=javaproje;encrypt=true;trustServerCertificate=true;", "enes", "0359" );
        Statement stm=conn.createStatement();
        stm.executeUpdate(sqlquery);
        JOptionPane.showMessageDialog(null, "Kayıt Silindi");
        tablo_update();
        txttcduzen.setText("");
        txtsifreduzen.setText("");
        txttcduzen.requestFocus();
        conn.close();
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void tablo_update1(){
        int c;  
        String sqlquery= "SELECT * FROM müsterihesap WHERE müsterikredidurum = 1";
    try{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\DESKTOP-C3A52CP:1433;databaseName=javaproje;encrypt=true;trustServerCertificate=true;", "enes", "0359" );
        Statement stm=conn.createStatement();
        PreparedStatement pst = conn.prepareStatement(sqlquery);
        ResultSet rs = pst.executeQuery();
        ResultSetMetaData rss= rs.getMetaData();
        c = rss.getColumnCount();
        DefaultTableModel df = (DefaultTableModel)jTable2.getModel();
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
                v2.add(rs.getString("müsterikredidurum"));
                v2.add(rs.getString("müsterikredisonuc"));
            }
            df.addRow(v2);
        }
        
        
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void kredikabul(String username , String a){
        String sqlquery = "SELECT * FROM müsterihesap WHERE müsterihesap='"+username+"'";
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\DESKTOP-C3A52CP:1433;databaseName=javaproje;encrypt=true;trustServerCertificate=true;", "enes", "0359" );
            PreparedStatement pst = conn.prepareStatement(sqlquery);
            ResultSet rs = pst.executeQuery();
            if(!rs.next()){
                JOptionPane.showMessageDialog(null, "HesapNo Hatalı");
            }else{
                String sqlquery2 = "UPDATE müsterihesap SET müsterikredisonuc='"+a+"'WHERE müsterihesap='"+username+"'";
                PreparedStatement pst1 = conn.prepareStatement(sqlquery2);
                pst1.execute();

                JOptionPane.showMessageDialog(null, "Kabul Edildi");

            }
            conn.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
       tablo_update1();
    }
    public void kredired(String username , String a){
        String sqlquery = "SELECT * FROM müsterihesap WHERE müsterihesap='"+username+"'";
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\DESKTOP-C3A52CP:1433;databaseName=javaproje;encrypt=true;trustServerCertificate=true;", "enes", "0359" );
            PreparedStatement pst = conn.prepareStatement(sqlquery);
            ResultSet rs = pst.executeQuery();
            if(!rs.next()){
                JOptionPane.showMessageDialog(null, "HesapNo Hatalı");
            }else{
                String sqlquery2 = "UPDATE müsterihesap SET müsterikredisonuc='"+a+"'WHERE müsterihesap='"+username+"'";
                PreparedStatement pst1 = conn.prepareStatement(sqlquery2);
                pst1.execute();

                JOptionPane.showMessageDialog(null, "Red Edildi");

            }
            conn.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
       tablo_update1();
    }

}
