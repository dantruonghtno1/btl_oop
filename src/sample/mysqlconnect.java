package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author amir
 */
public class mysqlconnect{

    //Connection conn = null;
    /*public static Connection ConnectDb(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/tutorial","root","");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }

    public static ObservableList<Word> getDataWord(){
        Connection conn = ConnectDb();
        ObservableList<Word> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM `tbl_edict`");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){

                int value1 = Integer.parseInt(rs.getString("idx"));
                String value2 = rs.getString("word");
                String value3 = rs.getString("detail");
                value3 =value3.substring(14,value3.length());
                value3 =value3.substring(0,value3.length()-20);
                String[] arrOfStr = value3.split("<br />",6);
                String res = "";
                for (String a : arrOfStr) res = res +"\n"+ a;
                value3 = res;
                value3 = value3.substring(3, value3.length());
                list.add(new Word(value1,value2,value3));
            }
        } catch (Exception e) {
        }
        return list;
    }
    public mysqlconnect() {

    }*/
    //Connection conn = null;
    //Connection conn = null;
    public static Connection ConnectDb(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/official","root","");
            //JOptionPane.showMessageDialog(null, "Connection Established");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }

    }

    public static ObservableList<Word> getDataWord(){
        Connection conn = ConnectDb();
        ObservableList<Word> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM `tbl_edict`");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                String value = rs.getString("detail");
                value = value.substring(16, value.length());
                value = value.substring(0, value.length() - 20);
                String[] words1 = value.split("<br />");
                value = "";
                for (String word : words1) {
                    value = value + '\n' + word;

                }
                list.add(new Word(Integer.parseInt(rs.getString("idx")), rs.getString("word"), value));
            }
        } catch (Exception e) {
        }
        return list;
    }
    public void insertFromMySQL(Dictionary Dict) {
        Connection conn = ConnectDb();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM `tbl_edict`");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String value = rs.getString("detail");
                value = value.substring(16, value.length());
                value = value.substring(0, value.length() - 20);
                String[] words1 = value.split("<br />");
                value = "";
                for (String word : words1) {
                    value = value + '\n' + word;

                }

                Word temp = new Word(Integer.parseInt(rs.getString("idx")), rs.getString("word"), value);
                Dict.getDictionary(temp);
            }
        } catch (Exception e) {
        }

    }
}