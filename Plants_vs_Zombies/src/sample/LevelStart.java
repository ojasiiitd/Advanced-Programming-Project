package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LevelStart implements Initializable
{
    @FXML
    private Button lvl1;
    @FXML
    private Button lvl2;
    @FXML
    private Button lvl3;
    @FXML
    private Button lvl4;
    @FXML
    private Button lvl5;

    private void loadNewGame(String openFile)
    {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource(openFile + ".fxml"));
            Stage newGameStage = new Stage();
            newGameStage.setTitle(openFile);
            newGameStage.setScene(new Scene(root, 1300, 650));
            newGameStage.show();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
        lvl1.setOnMouseClicked(e ->
        {
            loadNewGame("Level1");
        });

        lvl2.setOnMouseClicked(e ->
        {
            loadNewGame("Level2");
        });

        lvl3.setOnMouseClicked(e ->
        {
            loadNewGame("Level3");
        });

        lvl4.setOnMouseClicked(e ->
        {
            loadNewGame("Level4");
        });

        lvl5.setOnMouseClicked(e ->
        {
            loadNewGame("Level5");
        });
    }
}
