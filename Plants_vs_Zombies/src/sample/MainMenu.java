package sample;

import java.io.File;
import java.net.URL;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class MainMenu implements Initializable
{
    @FXML
    private AnchorPane mainMenu;

    MediaPlayer mediaPlayer;

    @FXML
    private void startNewGame()
    {
        mediaPlayer.stop();
        Timeline openLevel1 = new Timeline(new KeyFrame(Duration.millis(100) , event ->
        {
            System.out.println("Opening Level 1");
        }));
        openLevel1.setCycleCount(1);
        openLevel1.setOnFinished(e ->
        {
            loadNewGame();
        });
        openLevel1.play();
    }

    @FXML
    private void showLevels()
    {
        mediaPlayer.stop();
        Timeline openLevel1 = new Timeline(new KeyFrame(Duration.millis(100) , event ->
        {
            System.out.println("Loading Levels");
        }));
        openLevel1.setCycleCount(1);
        openLevel1.setOnFinished(e ->
        {
            loadLevels();
        });
        openLevel1.play();
    }

    private void loadNewGame()
    {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("Level1.fxml"));
            Stage newGameStage = new Stage();
            newGameStage.setTitle("Level 1");
            newGameStage.setScene(new Scene(root, 1300, 650));
            newGameStage.show();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private void loadLevels()
    {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("LevelStart.fxml"));
            Stage newGameStage = new Stage();
            newGameStage.setTitle("Choose Levels");
            newGameStage.setScene(new Scene(root, 1300, 650));
            newGameStage.show();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @FXML
    private void exitGame()
    {
        System.exit(0);
    }

    private void playMusic(String musicFile)
    {
        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
        playMusic("src/resources/audio/menu.wav");
    }
}