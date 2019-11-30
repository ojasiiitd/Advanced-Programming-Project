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
import javafx.scene.control.Label;
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

public class NewGame implements Initializable, Runnable
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
    @FXML
    private Label sunLabel;

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

    private void moveZombie(Zombies z)
    {
        KeyFrame kf = new KeyFrame(Duration.millis(z.getSpeed()) , event ->
        {
            z.img.setLayoutX(z.img.getLayoutX() - 1);

            PeaShooter hitting = null;
            for(Plants i : game.getPlants_list())
            {
                PeaShooter p;

                try {
                    p = (PeaShooter) i;
                } catch (Exception ex) {
                    continue;
                }

                if (z.img.getLayoutX() >= p.pea.getLayoutX() - 7 && z.img.getLayoutX() <= p.pea.getLayoutX() + 7) {
                    hitting = p;
                    break;
                }
            }

            if(hitting != null) {
                System.out.println("COLLISION");
                z.setHealth(z.getHealth() - 50);
                hitting.pea.setLayoutX(hitting.pea.getLayoutX());
                hitting.pea.setLayoutY(hitting.pea.getLayoutY());
                if (z.getHealth() <= 0)
                {
                    z.img.setVisible(false);
                    z.img.setLayoutX(-10000);
                    z.img.setLayoutY(-10000);
                }
                shootPea(hitting);
            }
        });
        animation = new Timeline(kf);
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
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
        Image temp = new Image(getClass().getResource("../resources/img/sun.gif").toExternalForm());
        ImageView newSun = new ImageView();
        newSun.setImage(temp);
        newSun.setLayoutX(getRandomNumberInRange(380 , 1220));
        newSun.setLayoutY(getRandomNumberInRange(50 , 80));
        gameScreen.getChildren().add(newSun);
        newSun.setOnMouseClicked(e ->
        {
            Integer curSuns = Integer.parseInt(sunLabel.getText());
            curSuns += 25;

            sunLabel.setText(curSuns.toString());
            gameScreen.getChildren().remove(newSun);
        });

        KeyFrame kf = new KeyFrame(Duration.millis(15) , event ->
        {
            newSun.setLayoutY(newSun.getLayoutY() + 1);
        });
        animation = new Timeline(kf);
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
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
        Image zombie1 = new Image(getClass().getResource("../resources/img/zombie_normal.gif").toExternalForm());
        Image zombie2 = new Image(getClass().getResource("../resources/img/zombie_football.gif").toExternalForm());
        Image zombie3 = new Image(getClass().getResource("../resources/img/zombie_football.gif").toExternalForm());
        ImageView zombieArr[] = new ImageView[3];

        for(int i=0 ; i<3 ; i++)
            zombieArr[i] = new ImageView();

        zombieArr[0].setId("zombie1");
        zombieArr[0].setImage(zombie1);

        zombieArr[1].setId("zombie2");
        zombieArr[1].setImage(zombie2);

        zombieArr[2].setId("zombie3");
        zombieArr[2].setImage(zombie3);

        int yCoors[] = {142 , 244 , 354 , 452 , 559};

        for(int i=0 ; i<5 ; i++)
        {
            System.out.println("CREATING ZOMBIE");

            try
            {
                Zombies newZombie = new Zombies(zombieArr[getRandomNumberInRange(0, 2)], getRandomNumberInRange(100, 300), getRandomNumberInRange(10, 20), getRandomNumberInRange(40 , 60));
                newZombie.setXposition(getRandomNumberInRange(1230, 1250));
                newZombie.img.setLayoutX(newZombie.getXposition());
                newZombie.setYposition(yCoors[getRandomNumberInRange(0 , 4)]);
                newZombie.img.setLayoutY(newZombie.getYposition());
                newZombie.img.setFitHeight(100);
                newZombie.img.setFitWidth(80);
                moveZombie(newZombie);
                gameScreen.getChildren().add(newZombie.img);
                game.getZombies_list().add(newZombie);
            }
            catch (Exception ex) { }
        }
    }

    @Override
    public void run()
    {
        while(true)
        {
            giveSun();
            for(int i=0 ; i<10000 ; i++)
        }
    }

    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
        gameScreen.setOpacity(0);
        fadeIn();

        game = new CurrentGame();

        addZombies();

        NewGame forSuns = new NewGame();
        Thread t1 = new Thread(forSuns);
        t1.start();

//        useLawnMower();
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