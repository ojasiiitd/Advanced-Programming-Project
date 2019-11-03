package sample;

import java.io.File;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import javafx.animation.FadeTransition;
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
    AnchorPane mainMenu;

    MediaPlayer mediaPlayer;

    @FXML
    public void startNewGame()
    {
        fadeOut();
    }

    private void fadeOut()
    {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(500));
        fade.setNode(mainMenu);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.setOnFinished(new EventHandler<ActionEvent>() {
                               @Override
                               public void handle(ActionEvent event) {
                                   loadNewGame();
                               }
                           });
        fade.play();
        mediaPlayer.stop();
    }

    private void loadNewGame()
    {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("NewGame.fxml"));
            Stage newGameStage = new Stage();
            newGameStage.setTitle("Plants vs Zombies");
            newGameStage.setScene(new Scene(root, 1300, 650));
            newGameStage.show();
        }
        catch (Exception ex) {}
    }

    public void exitGame()
    {
        System.exit(0);
    }

    private void playMusic(String musicFile)
    {
        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
        playMusic("C:\\Users\\Ojas\\IdeaProjects\\Plants_vs_Zombies\\audio\\menu.wav");
    }
}