package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ResourceBundle;

public class APIVietAnhController {
    @FXML
    private Button Button;
    @FXML
    private TextField searchBar;
    @FXML
    private TextArea textArea;



    @FXML
    void handleAction(ActionEvent event) throws IOException{
        String text = searchBar.getText();
        String translateText = APITranslator.translate("vi", "en", text);
        textArea.setText(translateText);
    }

    @FXML
    void sound(ActionEvent event) throws IOException{
        TextToSpeech tts = new TextToSpeech();
        String text = searchBar.getText();
        String translateText = APITranslator.translate("vi", "en", text);
        tts.speak(translateText,2.0f,false, true);
    }

    public void back(ActionEvent event) throws IOException
    {
        Parent pagegoto = FXMLLoader.load(getClass().getResource("Page1.fxml"));
        Scene tableViewScene = new Scene(pagegoto);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

}
