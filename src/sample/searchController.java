package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class searchController  {
    @FXML
    AnchorPane root;

    @FXML
    TextField wordSearch, wordName;

    @FXML
    ListView<String> wordRcm;



    @FXML
    TextArea wordMeaning;

    @FXML
    Button search;

    Dictionary lists = new Dictionary();
    Dictionary lists1 = new Dictionary();
    public void initialize() {
        getRecommendList();

        wordSearch.setOnAction(e -> {
            try {
                String wordTarget = wordSearch.getText();
                boolean isCorrect = false;
                if (wordTarget != null && !wordTarget.contentEquals("")) {
                    for (Word w: lists1.words) {
                        if (w.getEng().equals(wordTarget)) {
                            wordName.setText(wordTarget);
                            wordMeaning.setText(w.getViet());
                            isCorrect = true;
                            break;
                        }
                    }
                }
                if (! isCorrect) {
                    //wordName.setText("Wrong Word!!");
                    wordMeaning.setText("Wrong Word!!");
                }
            } catch (Exception ex) {
                System.out.println("wordSearch. onAction. " + ex);
            }
        });

        wordSearch.textProperty().addListener((obs, oldText, newText) -> {
            try {
                // System.out.println("text changed from "+ oldText + " to " + newText);
                actionListview(newText);
            } catch (Exception ex) {
                System.out.println("wordSearch. changeWord. " + ex);
            }
        });

        wordRcm.setOnMouseClicked(e -> {
            String wordTarget = wordRcm.getSelectionModel().getSelectedItem();
            if (wordTarget == null) {
                return;
            }
            for (Word w: lists1.words) {
                if (w.getEng().equals(wordTarget)) {
                    wordName.setText(wordTarget);
                    wordMeaning.setText(w.getViet());
                    break;
                }
            }
        });

        search.setOnAction(e ->{
            try {
                String wordTarget = wordSearch.getText();
                boolean isCorrect = false;
                if (wordTarget != null && !wordTarget.contentEquals("")) {
                    for (Word w: lists1.words) {
                        if (w.getEng().equals(wordTarget)) {
                            wordName.setText(wordTarget);
                            wordMeaning.setText(w.getViet());
                            isCorrect = true;
                            break;
                        }
                    }
                }
                else {
                    wordRcm.getItems().clear();
                    wordName.setText("");
                    wordMeaning.setText(null);
                    isCorrect = true;
                }
                if (! isCorrect) {
                    //wordName.setText("Wrong Word!!");
                    wordMeaning.setText("Wrong Word!!");
                }
            } catch (Exception ex) {
                System.out.println("wordSearch. onAction. " + ex);
            }
        });
    }

    public void getRecommendList() {
        mysqlconnect rs = new mysqlconnect();
        rs.insertFromMySQL(lists);

    }

    public void actionListview(String word) {
        lists1.words.clear();
        for(int i=0;i<lists.words.size();++i) {
            if(lists.words.get(i).getEng().startsWith(word)) {
                lists1.words.add(lists.words.get(i));
            }
        }
        wordRcm.getItems().clear();
        for(int i=0;i<lists1.words.size();++i) {
            wordRcm.getItems().add(lists1.words.get(i).getEng());
        }

    }

    @FXML
    void sound(ActionEvent event) {
        TextToSpeech tts = new TextToSpeech();
        String text = wordName.getText();
        tts.speak(text,2.0f,false, true);
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
