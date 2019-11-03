package sample;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ResourceBundle;

public class NewGame implements Initializable
{
    @FXML
    private AnchorPane gameScreen;
    @FXML
    private ImageView normalZombie;
    @FXML
    private ImageView peaBullet;
    @FXML
    private ImageView fallingSun;
    @FXML
    private ImageView lawnMower2;
    @FXML
    private Button gameOpts;

    public TranslateTransition moveIt;

    @FXML
    public void showOptions() throws IOException
    {
        gameScreen.setOpacity(0.65);

        Parent root = FXMLLoader.load(getClass().getResource("GameOptions.fxml"));
        Stage newGameStage = new Stage();
        newGameStage.setTitle("Plants vs Zombies");
        newGameStage.setScene(new Scene(root, 400, 400));
        newGameStage.show();
    }

    public void exitGame()
    {
        System.exit(0);
    }

    private void fadeIn()
    {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(500));
        fade.setNode(gameScreen);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    private void moveZombie()
    {
        moveIt = new TranslateTransition(Duration.millis(30000) , normalZombie);
        moveIt.setByX(-1000);
        moveIt.play();
    }

    private void shootPea()
    {
        moveIt = new TranslateTransition(Duration.millis(6000) , peaBullet);
        moveIt.setByX(1000);
        moveIt.setCycleCount(10000);
        moveIt.play();
    }

    private void giveSun()
    {
        moveIt = new TranslateTransition(Duration.millis(7500) , fallingSun);
        moveIt.setByY(260);
        moveIt.play();
    }

    private void useLawnMower()
    {
        playMusic("C:\\Users\\Ojas\\IdeaProjects\\Plants_vs_Zombies\\audio\\lamborghini.wav");

        moveIt = new TranslateTransition(Duration.millis(5000) , lawnMower2);
        moveIt.setByX(1100);
        moveIt.play();
    }

    private void playMusic(String musicFile)
    {
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
        gameScreen.setOpacity(0);
        fadeIn();

        moveZombie();
        shootPea();
        giveSun();
        useLawnMower();
    }
}