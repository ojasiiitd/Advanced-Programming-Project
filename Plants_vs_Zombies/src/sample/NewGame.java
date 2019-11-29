package sample;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.Time;
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
    private Button peashooterBtn;
    @FXML
    private Button sunflowerBtn;
    @FXML
    private Button walnutBtn;
    @FXML
    private Button cherrybombBtn;
    @FXML
    private GridPane grid;

    public TranslateTransition moveIt;
    public Timeline animation;

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

    @FXML
    public void removeSun()
    {
        fallingSun.setVisible(false);
    }

    public void exitGame()
    {
        System.exit(0);
    }

    private void fadeIn()
    {
        System.out.println("FADEIN");
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(500));
        fade.setNode(gameScreen);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    private void moveZombie()
    {
        KeyFrame kf = new KeyFrame(Duration.millis(30) , event ->
        {
            normalZombie.setLayoutX(normalZombie.getLayoutX() - 1);
        });
        animation = new Timeline(kf);
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }

    private void shootPea()
    {
        peaBullet.setVisible(true);
        KeyFrame kf = new KeyFrame(Duration.millis(5) , event ->
        {
            peaBullet.setLayoutX(peaBullet.getLayoutX() + 1);
        });
        animation = new Timeline(kf);
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }

    private void giveSun()
    {
        moveIt = new TranslateTransition(Duration.millis(7500) , fallingSun);
        moveIt.setByY(260);
        moveIt.play();
    }

    private void useLawnMower()
    {
        playMusic("src/resources/audio/lamborghini.wav");

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

    @FXML
    private void dragDrop()
    {
        System.out.println("DRAGGING");

        Image imag = new Image(getClass().getResource("../resources/img/pea_shooter.gif").toExternalForm());
        ImageView peashooter = new ImageView();
        peashooter.setImage(imag);

        peashooterBtn.setOnMouseDragged(e ->
        {
            System.out.println("MOUSE DRAGGING");

            System.out.println(e.getSceneX());
            System.out.println(e.getSceneY());
        });
        peashooterBtn.setOnMouseReleased(e ->
        {
            int col = (int)(e.getSceneY() - grid.getLayoutY()) , row = (int)(e.getSceneX() - grid.getLayoutX());

            grid.add(peashooter , 3 , 1);
        });
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