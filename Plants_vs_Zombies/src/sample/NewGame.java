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

import java.util.Random;
import java.util.ResourceBundle;

public class NewGame implements Initializable
{
    @FXML
    private AnchorPane gameScreen;
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

    CurrentGame game;

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
//        KeyFrame kf = new KeyFrame(Duration.millis(30) , event ->
//        {
//            normalZombie.setLayoutX(normalZombie.getLayoutX() - 1);
//            if(normalZombie.getLayoutX() >= peaBullet.getLayoutX()-7 && normalZombie.getLayoutX() <= peaBullet.getLayoutX()+7)
//            {
//                System.out.println("COLLISION");
//                game.getZombies_list().get(0).setHealth(game.getZombies_list().get(0).getHealth() - 50);
//                peaBullet.setLayoutX(388);
//                peaBullet.setLayoutY(566);
//                animation.stop();
//                if(game.getZombies_list().get(0).getHealth() <= 0)
//                {
//                    normalZombie.setVisible(false);
//                    normalZombie.setLayoutX(-10000);
//                    normalZombie.setLayoutY(-10000);
//                }
//                shootPea(p);
//            }
//        });
//        animation = new Timeline(kf);
//        animation.setCycleCount(Timeline.INDEFINITE);
//        animation.play();
    }

    private void shootPea(PeaShooter p)
    {
        p.pea.setLayoutX(p.getX_pos());
        p.pea.setLayoutY(p.getY_pos());
        gameScreen.getChildren().add(p.pea);

        KeyFrame kf = new KeyFrame(Duration.millis(5) , event ->
        {
            p.pea.setLayoutX(p.pea.getLayoutX() + 1);
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

        String gifName;
        ImageView dragImage = new ImageView();

        if(draggedId.equals("peashooterBtn"))
        {
            gifName = "pea_shooter.gif";
            dragImage.setImage(new Image(getClass().getResource("../resources/img/" + gifName).toExternalForm()));
            dragImage.setId("peaShooter");
            ImageView pea = new ImageView();
            pea.setImage(new Image(getClass().getResource("../resources/img/Pea.png").toExternalForm()));
            pea.setId("peaBullet");
            game.getPlants_list().add(new PeaShooter(dragImage , pea));
        }
        else if (draggedId.equals("sunflowerBtn"))
        {
            gifName = "sun_flower.gif";
            dragImage.setImage(new Image(getClass().getResource("../resources/img/" + gifName).toExternalForm()));
            dragImage.setId("sunflower");
            game.getPlants_list().add(new SunFlower(dragImage));
        }
        else if (draggedId.equals("walnutBtn"))
        {
            gifName = "walnut_full_life.gif";
            dragImage.setImage(new Image(getClass().getResource("../resources/img/" + gifName).toExternalForm()));
            dragImage.setId("walnut");
            game.getPlants_list().add(new Wallnut(dragImage));
        }
        else if (draggedId.equals("cherrybombBtn"))
        {
            gifName = "beetroot.gif";
            dragImage.setImage(new Image(getClass().getResource("../resources/img/" + gifName).toExternalForm()));
            dragImage.setId("cherryBomb");
            game.getPlants_list().add(new CherryBomb(dragImage));
        }

        Dragboard db = peashooterBtn.startDragAndDrop(TransferMode.ANY);
        ClipboardContent cb = new ClipboardContent();
        cb.putImage(dragImage.getImage());
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

        Plants curPlant = game.getPlants_list().get(game.getPlants_list().size()-1);
        curPlant.setX_pos(event.getSceneX());
        curPlant.setY_pos(event.getSceneY());

        try
        {
            PeaShooter p = (PeaShooter) curPlant;
            shootPea(p);
        }
        catch (Exception ex)
        {}
    }

    public void addZombies()
    {
        Image zombie1 = new Image(getClass().getResource("../resources/img/zombie1.gif").toExternalForm());
        Image zombie2 = new Image(getClass().getResource("../resources/img/zombie2.gif").toExternalForm());
        Image zombie3 = new Image(getClass().getResource("../resources/img/zombie3.gif").toExternalForm());
        ImageView zombieArr[] = new ImageView[3];

        for(int i=0 ; i<3 ; i++)
            zombieArr[i] = new ImageView();

        zombieArr[0].setId("zombie1");
        zombieArr[0].setImage(zombie1);

        zombieArr[1].setId("zombie2");
        zombieArr[1].setImage(zombie2);

        zombieArr[2].setId("zombie3");
        zombieArr[2].setImage(zombie3);

        for(int i=0 ; i<5 ; i++)
        {
            Zombies newZombie = new Zombies(zombieArr[getRandomNumberInRange(0 , 2)] , getRandomNumberInRange(100,300) , getRandomNumberInRange(10 , 20) , getRandomNumberInRange(20 , 40));
            game.getZombies_list().add(newZombie);
        }
    }

    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
        gameScreen.setOpacity(0);
        fadeIn();

        game = new CurrentGame();

        addZombies();
//        moveZombie();
        giveSun();
        useLawnMower();
    }

    private static int getRandomNumberInRange(int min, int max)
    {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

}