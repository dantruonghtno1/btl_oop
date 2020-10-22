package sample;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.w3c.dom.xpath.XPathResult;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML
    private TableColumn<Word, String> col_english;

    @FXML
    private TableColumn<Word, Integer> col_id;

    @FXML
    private TableColumn<Word, String> col_vietnamese;

    @FXML
    private TableView<Word> table;

    @FXML
    private TextField txt_eng;

    @FXML
    private TextField txt_viet;

    @FXML
    private TextField txt_id;

    @FXML
    private TextField filterField;

    ObservableList<Word>listData;
    ObservableList<Word>listSearch;
    int index = -1;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UpdateTable();
        search_user();
    }

    @FXML
    void getSelected (MouseEvent event) {
        index = table.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return ;
        }
        txt_id.setText(col_id.getCellData(index).toString());
        txt_eng.setText(col_english.getCellData(index).toString());
        txt_viet.setText(col_vietnamese.getCellData(index).toString());

    }

    public  void Edit() {
        try {
            conn = mysqlconnect.ConnectDb();
            String value1 = txt_id.getText();
            String value2 = txt_eng.getText();
            String value3 = "<C><F><I><N><Q>@"+txt_viet.getText()+"</Q></N></I></F></C>";

            String sql = "update tbl_edict set idx='"+value1+"',word='"+value2+"',detail='"+value3+"' where idx='"+value1+"'";
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Update");
            UpdateTable();
            search_user();
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }

    public void UpdateTable() {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_english.setCellValueFactory(new PropertyValueFactory<>("eng"));
        col_vietnamese.setCellValueFactory(new PropertyValueFactory<>("viet"));

        listData = mysqlconnect.getDataWord();

        table.setItems(listData);
    }

    public void Delete() {
        conn = mysqlconnect.ConnectDb();
        String sql = "delete from tbl_edict where idx = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1,txt_id.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Delete");
            UpdateTable();
            search_user();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    public void add_word (){
        conn = mysqlconnect.ConnectDb();
        String sql = "insert into tbl_edict (idx,word,detail) values(?,?,?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id.getText());
            pst.setString(2, txt_eng.getText());
            pst.setString(3, txt_viet.getText());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Users Add succes");
             UpdateTable();
             search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    void search_user() {
        col_id.setCellValueFactory(new PropertyValueFactory<Word,Integer>("id"));
        col_english.setCellValueFactory(new PropertyValueFactory<Word,String>("eng"));
        col_vietnamese.setCellValueFactory(new PropertyValueFactory<Word,String>("viet"));

        listSearch = mysqlconnect.getDataWord();
        table.setItems(listSearch);
        FilteredList<Word> filteredData = new FilteredList<>(listSearch, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(word -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (word.getEng().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (word.getEng().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                else
                    return false;
            });
        });
        SortedList<Word> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
    }
    public void back(ActionEvent event) throws IOException
    {
        Parent pagegoto = FXMLLoader.load(getClass().getResource("Page1.fxml"));
        Scene tableViewScene = new Scene(pagegoto);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

}
