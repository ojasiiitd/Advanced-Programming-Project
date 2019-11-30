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
import java.util.ArrayList;
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
    @FXML
    private Label sunLabel;

    public TranslateTransition moveIt;
    public Timeline animation;
    public static int zombieHits = 0;

    private CurrentGame game;

    private Timeline zombieTimeline;
    private Timeline peaTimeline;
    private Timeline sunTimeline;
    private Timeline sunfallTimeline;
    private Timeline peashooterTimeline;
    private Timeline addZombieTimeline;

    private ArrayList<ImageView> allPeas;

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

            ImageView hitting = null;
            for(ImageView i : allPeas)
            {
                if (z.img.getLayoutX() >= (i.getLayoutX()-7) && z.img.getLayoutX() <= (i.getLayoutX()+7) && (z.img.getLayoutY()) <= i.getLayoutY() && (z.img.getLayoutY() + 95) >= i.getLayoutY())
                {
                        hitting = i;
                        break;
                }
            }

            if(hitting != null) {
                System.out.println("COLLISION");
                z.setHealth(z.getHealth() - 50);
                hitting.setVisible(false);
                hitting.setLayoutX(10000);
                hitting.setLayoutY(10000);
                allPeas.remove(hitting);
                if (z.getHealth() <= 0)
                {
                    z.img.setVisible(false);
                    z.img.setLayoutX(-10000);
                    z.img.setLayoutY(-10000);
                }
            }
        });
        zombieTimeline = new Timeline(kf);
        zombieTimeline.setCycleCount(Timeline.INDEFINITE);
        zombieTimeline.play();
    }

    private void shootPea(PeaShooter p)
    {
        ImageView pea = new ImageView();
        pea.setImage(new Image(getClass().getResource("../resources/img/Pea.png").toExternalForm()));
        pea.setLayoutX(p.getX_pos());
        pea.setLayoutY(p.getY_pos());

        allPeas.add(pea);
        gameScreen.getChildren().add(pea);

        KeyFrame kf = new KeyFrame(Duration.millis(5) , event ->
        {
            pea.setLayoutX(pea.getLayoutX() + 1);
        });
        peaTimeline = new Timeline(kf);
        peaTimeline.setCycleCount(Timeline.INDEFINITE);
        peaTimeline.play();
    }

    private void peashooterCycle(PeaShooter p)
    {
        KeyFrame kf = new KeyFrame(Duration.seconds(2) , event ->
        {
            shootPea(p);
        });
        peashooterTimeline = new Timeline(kf);
        peashooterTimeline.setCycleCount(Timeline.INDEFINITE);
        peashooterTimeline.play();
    }

    private void giveSun()
    {
        ImageView newSun = new ImageView();
        newSun.setImage(new Image(getClass().getResource("../resources/img/sun.gif").toExternalForm()));
        newSun.setLayoutX(getRandomNumberInRange(380 , 1220));
        newSun.setLayoutY(getRandomNumberInRange(0 , 20));
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
        sunfallTimeline = new Timeline(kf);
        sunfallTimeline.setCycleCount(Timeline.INDEFINITE);
        sunfallTimeline.play();
    }

    private void startSuns()
    {
        KeyFrame kf = new KeyFrame(Duration.seconds(5) , event -> {
            giveSun();
        });
        sunTimeline = new Timeline(kf);
        sunTimeline.setCycleCount(Timeline.INDEFINITE);
        sunTimeline.play();
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
            game.getPlants_list().add(new PeaShooter(dragImage));
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
            peashooterCycle(p);
        }
        catch (Exception ex) {}
    }

    public void addZombies()
    {
        Image zombie1 = new Image(getClass().getResource("../resources/img/zombie_normal.gif").toExternalForm());
        Image zombie2 = new Image(getClass().getResource("../resources/img/zombie_football.gif").toExternalForm());
        Image zombie3 = new Image(getClass().getResource("../resources/img/zombie_normal.gif").toExternalForm());
        Image zombieImg[] = {zombie1 , zombie2 , zombie3};

        int yCoors[] = {80 , 180 , 280 , 380 , 480};

        for(int i=0 ; i<2 ; i++)
        {
            ImageView newZombie = new ImageView();
            newZombie.setImage(zombieImg[getRandomNumberInRange(0 , 2)]);
            newZombie.setLayoutX(getRandomNumberInRange(1240 , 1250));
            newZombie.setLayoutY(yCoors[getRandomNumberInRange(0 , 4)]);
            gameScreen.getChildren().add(newZombie);

            Zombies nextZombie = new Zombies(newZombie , getRandomNumberInRange(250 , 350) , getRandomNumberInRange(10 , 20) , getRandomNumberInRange(50 , 60));
            moveZombie(nextZombie);
            game.getZombies_list().add(nextZombie);
        }
    }

    public void startZombies()
    {
        KeyFrame kf = new KeyFrame(Duration.seconds(5) , event ->
        {
            addZombies();
        });
        addZombieTimeline = new Timeline(kf);
        addZombieTimeline.setCycleCount(Timeline.INDEFINITE);
        addZombieTimeline.play();
    }

    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
        gameScreen.setOpacity(0);
        fadeIn();

        game = new CurrentGame();
        allPeas = new ArrayList<>();

        startZombies();
        startSuns();

        walnutBtn.setDisable(true);
        cherrybombBtn.setDisable(true);
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