package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FXMLPage1Controller {
    public void gotoOffline(ActionEvent event) throws IOException
    {
        Parent pagegoto = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene tableViewScene = new Scene(pagegoto);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    public void gotoAnhVietAPI(ActionEvent event) throws IOException
    {
        Parent pagegoto = FXMLLoader.load(getClass().getResource("APIAnhViet.fxml"));
        Scene tableViewScene = new Scene(pagegoto);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }


    public void gotoVietAnhAPI(ActionEvent event) throws IOException
    {
        Parent pagegoto = FXMLLoader.load(getClass().getResource("APIVietAnh.fxml"));
        Scene tableViewScene = new Scene(pagegoto);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
    public void gotoSearch(ActionEvent event) throws IOException
    {
        Parent pagegoto = FXMLLoader.load(getClass().getResource("search.fxml"));
        Scene tableViewScene = new Scene(pagegoto);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

}


