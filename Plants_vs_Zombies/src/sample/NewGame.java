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
import javafx.scene.input.*;
import javafx.scene.layout.*;
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
    private Button peashooterBtn;
    @FXML
    private Button sunflowerBtn;
    @FXML
    private Button walnutBtn;
    @FXML
    private Button cherrybombBtn;
    @FXML
    private ImageView r4c5;
    @FXML
    private ImageView r1c1;
    @FXML
    private ImageView r1c2;
    @FXML
    private ImageView r1c3;
    @FXML
    private ImageView r1c4;
    @FXML
    private ImageView r1c5;
    @FXML
    private ImageView r2c1;
    @FXML
    private ImageView r2c2;
    @FXML
    private ImageView r2c3;
    @FXML
    private ImageView r2c4;
    @FXML
    private ImageView r2c5;
    @FXML
    private ImageView r3c1;
    @FXML
    private ImageView r3c2;
    @FXML
    private ImageView r3c3;
    @FXML
    private ImageView r3c4;
    @FXML
    private ImageView r3c5;
    @FXML
    private ImageView r4c1;
    @FXML
    private ImageView r4c2;
    @FXML
    private ImageView r4c3;
    @FXML
    private ImageView r4c4;
    @FXML
    private ImageView r5c1;
    @FXML
    private ImageView r5c2;
    @FXML
    private ImageView r5c3;
    @FXML
    private ImageView r5c4;
    @FXML
    private ImageView r5c5;

    public TranslateTransition moveIt;
    public Timeline animation;
    public static int zombieHits = 0;

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
            if(normalZombie.getLayoutX() >= peaBullet.getLayoutX()-7 && normalZombie.getLayoutX() <= peaBullet.getLayoutX()+7)
            {
                System.out.println("COLLISION");
                peaBullet.setLayoutX(388);
                peaBullet.setLayoutY(566);
                animation.stop();
                zombieHits++;
                if(zombieHits == 3)
                {
                    normalZombie.setVisible(false);
                    normalZombie.setLayoutX(-10000);
                    normalZombie.setLayoutY(-10000);
                }
                shootPea();
            }
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
    private void dragDetect(MouseEvent event)
    {
        System.out.println("DRAGGING");
        Button dragged = (Button) event.getSource();
        String draggedId = dragged.getId();

        String gifName = "";
        if(draggedId.equals("peashooterBtn"))
            gifName = "pea_shooter.gif";
        else if (draggedId.equals("sunflowerBtn"))
            gifName = "sun_flower.gif";
        else if (draggedId.equals("walnutBtn"))
            gifName = "walnut_full_life.gif";
        else if (draggedId.equals("cherrybombBtn"))
            gifName = "beetroot.gif";

        Dragboard db = peashooterBtn.startDragAndDrop(TransferMode.ANY);
        ClipboardContent cb = new ClipboardContent();
        cb.putImage(new Image(getClass().getResource("../resources/img/" + gifName).toExternalForm()));
        db.setContent(cb);
        event.consume();
    }

    @FXML
    private void dragOver(DragEvent event)
    {
        if(event.getDragboard().hasImage())
            event.acceptTransferModes(TransferMode.ANY);
    }

    @FXML
    private void dragDrop(DragEvent event)
    {
        Image tempImg = event.getDragboard().getImage();
        ImageView putHere = (ImageView) event.getSource();
        putHere.setImage(tempImg);
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