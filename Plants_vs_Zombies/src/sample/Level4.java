package sample;

import java.io.File;
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

public class Level4 implements Initializable
{
    @FXML
    private AnchorPane pausePane;
    @FXML
    private AnchorPane gameScreen;
    @FXML
    private Button peashooterBtn;
    @FXML
    private Button sunflowerBtn;
    @FXML
    private Button walnutBtn;
    @FXML
    private Button cherrybombBtn;
    @FXML
    private Label sunLabel;
    @FXML
    private Button exitBtn;
    @FXML
    private Button resumeBtn;

    private CurrentGame game;
    private boolean zombiesEnded;

    private Timeline sunTimeline;
    private Timeline sunfallTimeline;
    private Timeline peashooterTimeline;
    private Timeline addZombieTimeline;
    private Timeline checkPlantsTimeline;
    private Timeline winnerTimeline;

    private ArrayList<ImageView> allPeas;

    private void stopAll()
    {
        for(Plants i : game.getPlants_list())
        {
            try
            {
                i.plantActionTimeline.stop();
            }
            catch (Exception ex) {}
        }
        for(Zombies i : game.getZombies_list()) {
            try {
                i.zombieTimeline.stop();
            }
            catch (Exception ex) {}
        }
        try {
            sunTimeline.stop();
            sunfallTimeline.stop();
            peashooterTimeline.stop();
            addZombieTimeline.stop();
            checkPlantsTimeline.stop();
            winnerTimeline.stop();
        }
        catch (Exception ex) {}
    }

    @FXML
    private void showOptions()
    {
        pausePane.toFront();
        pausePane.setVisible(true);
        pausePane.setDisable(false);

        for(Plants i : game.getPlants_list())
        {
            try
            {
                i.plantActionTimeline.pause();
            }
            catch (Exception ex) {}
        }
        for(Zombies i : game.getZombies_list()) {
            try {
                i.zombieTimeline.pause();
            }
            catch (Exception ex) {}
        }
        try {
            sunTimeline.pause();
            sunfallTimeline.pause();
            peashooterTimeline.pause();
            addZombieTimeline.pause();
            checkPlantsTimeline.pause();
            winnerTimeline.pause();
        }
        catch (Exception ex) {}

        exitBtn.setOnMouseClicked(e ->
        {
            closeScreen();
        });

        resumeBtn.setOnMouseClicked(e ->
        {
            pausePane.setVisible(false);
            pausePane.setDisable(true);

            for(Plants i : game.getPlants_list())
            {
                try
                {
                    i.plantActionTimeline.play();
                }
                catch (Exception ex) {}
            }
            for(Zombies i : game.getZombies_list()) {
                try {
                    i.zombieTimeline.play();
                }
                catch (Exception ex) {}
            }

            try {
                sunTimeline.play();
                sunfallTimeline.play();
                peashooterTimeline.play();
                addZombieTimeline.play();
                checkPlantsTimeline.play();
                winnerTimeline.play();
            }
            catch (Exception ex) {}
        });
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

    private void playMusic(String musicFile)
    {
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
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
        p.plantActionTimeline = new Timeline(kf);
        p.plantActionTimeline.setCycleCount(Timeline.INDEFINITE);
        p.plantActionTimeline.play();
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

    private void sunflowerCycle(SunFlower s)
    {
        KeyFrame kf = new KeyFrame(Duration.seconds(5) , event ->
        {
            ImageView bornSun = new ImageView();
            bornSun.setImage(new Image(getClass().getResource("../resources/img/sun.gif").toExternalForm()));
            bornSun.setLayoutX(s.getX_pos());
            bornSun.setLayoutY(s.getY_pos());
            bornSun.setOnMouseClicked(e ->
            {
                Integer curSuns = Integer.parseInt(sunLabel.getText());
                curSuns += 25;
                game.setSuns(curSuns);
                sunLabel.setText(curSuns.toString());
                gameScreen.getChildren().remove(bornSun);
            });
            gameScreen.getChildren().add(bornSun);
        });
        s.plantActionTimeline = new Timeline(kf);
        s.plantActionTimeline.setCycleCount(Timeline.INDEFINITE);
        s.plantActionTimeline.play();
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
            game.setSuns(curSuns);
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

    private void checkPlants()
    {
        KeyFrame kf = new KeyFrame(Duration.millis(20) , event ->
        {
            int curSuns = game.getSuns();
            if(curSuns >= 50)
                sunflowerBtn.setDisable(false);
            if(curSuns >= 100)
                peashooterBtn.setDisable(false);

            if(curSuns < 100)
                peashooterBtn.setDisable(true);
            if(curSuns < 50)
                sunflowerBtn.setDisable(true);
        });
        checkPlantsTimeline = new Timeline(kf);
        checkPlantsTimeline.setCycleCount(Timeline.INDEFINITE);
        checkPlantsTimeline.play();
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
            game.setSuns(game.getSuns() - 100);
            sunLabel.setText(((Integer)game.getSuns()).toString());
            game.getPlants_list().add(new PeaShooter(dragImage));
        }
        else if (draggedId.equals("sunflowerBtn"))
        {
            gifName = "sun_flower.gif";
            dragImage.setImage(new Image(getClass().getResource("../resources/img/" + gifName).toExternalForm()));
            dragImage.setId("sunflower");
            game.setSuns(game.getSuns() - 50);
            sunLabel.setText(((Integer)game.getSuns()).toString());
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
        curPlant.location = putHere;
        curPlant.setX_pos(event.getSceneX());
        curPlant.setY_pos(event.getSceneY());

        try
        {
            PeaShooter p = (PeaShooter) curPlant;
            peashooterCycle(p);
        }
        catch (Exception ex) {}

        try
        {
            SunFlower s = (SunFlower) curPlant;
            sunflowerCycle(s);
        }
        catch (Exception ex) {}
    }

    private void moveZombie(Zombies z)
    {
        KeyFrame kf = new KeyFrame(Duration.millis(z.getSpeed()) , event ->
        {
            if(! z.hittingPlant)
                z.img.setLayoutX(z.img.getLayoutX() - 1);

            //hitting peas
            ImageView hitting = null;
            for(ImageView i : allPeas)
            {
                if (z.img.getLayoutX() >= (i.getLayoutX()-7) && z.img.getLayoutX() <= (i.getLayoutX()+7) && (z.img.getLayoutY()) <= i.getLayoutY() && (z.img.getLayoutY() + 95) >= i.getLayoutY())
                {
                    hitting = i;
                    break;
                }
            }

            if(hitting != null)
            {
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
                    game.getZombies_list().remove(z);
                }
            }

            //hitting plants
            Plants plantHit = null;
            for(Plants i : game.getPlants_list())
            {
                if (z.img.getLayoutX() <= (i.getX_pos() + 5) && z.img.getLayoutX() >= (i.getX_pos() - 5) && (z.img.getLayoutY() - 5) <= i.getY_pos()  && (z.img.getLayoutY() + 95) >= i.getY_pos())
                {
                    plantHit = i;
                    z.hittingPlant = true;
                    break;
                }
            }
            if(plantHit != null)
            {
                if(plantHit.getClass() == new LawnMover(new ImageView()).getClass())
                {
                    System.out.println("LAWNMOWERRRR");
                    moveLawnmoverover((LawnMover) plantHit);
                }

                else
                {
                    System.out.println(plantHit.getClass());
                    playMusic("src/resources/audio/chomp.wav");
                    System.out.println("PLANT HIT");

                    plantHit.setDefenceValue(plantHit.getDefenceValue() - z.getAttack_value());
                    if (plantHit.getDefenceValue() <= 0) {
                        System.out.println("KILLED");
                        z.hittingPlant = false;
                        plantHit.plantActionTimeline.stop();
                        plantHit.location.setImage(null);

                        game.getPlants_list().remove(plantHit);
                    }
                }
            }
        });
        z.zombieTimeline = new Timeline(kf);
        z.zombieTimeline.setCycleCount(Timeline.INDEFINITE);
        z.zombieTimeline.play();
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

            Zombies nextZombie = new Zombies(newZombie , getRandomNumberInRange(250 , 350) , getRandomNumberInRange(1 , 2) , getRandomNumberInRange(50 , 60));
            moveZombie(nextZombie);
            game.getZombies_list().add(nextZombie);
        }
    }

    public void startZombies()
    {
        KeyFrame kf = new KeyFrame(Duration.seconds(8) , event ->
        {
            playMusic("src/resources/audio/zombies_coming.wav");
            addZombies();
        });
        addZombieTimeline = new Timeline(kf);
        addZombieTimeline.setCycleCount(3);
        addZombieTimeline.setOnFinished(event ->
        {
            zombiesEnded = true;
            checkWinner();
        });
        addZombieTimeline.play();
    }

    public void createLawnmovers()
    {
        int arrY[] = {104 , 214 , 324 , 434 , 544};
        for(int i=0 ; i<5 ; i++)
        {
            ImageView lawnMower = new ImageView();
            lawnMower.setImage(new Image(getClass().getResource("../resources/img/lawn_mower.gif").toExternalForm()));
            lawnMower.setLayoutX(286);
            lawnMower.setLayoutY(arrY[i]);
            gameScreen.getChildren().add(lawnMower);
            LawnMover newLM = new LawnMover(lawnMower);
            newLM.setX_pos(lawnMower.getLayoutX());
            newLM.setY_pos(lawnMower.getLayoutY());
            game.getPlants_list().add(newLM);
        }
    }

    public void moveLawnmoverover(LawnMover lm)
    {
//        playMusic("src/resources/audio/lamborghini.wav");

        KeyFrame kf = new KeyFrame(Duration.millis(10) , event ->
        {
            lm.image.setLayoutX(lm.image.getLayoutX() + 1);

            for(Zombies i : game.getZombies_list())
            {
                if(lm.getX_pos()+7 >= i.img.getLayoutX() && lm.getX_pos()-7 <= i.img.getLayoutX() && i.img.getLayoutY()-5 <= lm.getY_pos() && i.img.getLayoutY()+105 >= lm.getY_pos()) {
                    i.zombieTimeline.stop();
                    i.img.setVisible(false);
                    i.img.setLayoutX(-10000);
                    i.img.setLayoutY(-10000);
                    gameScreen.getChildren().remove(i.img);
                    game.getZombies_list().remove(i);
                }
            }
//
        });
        lm.plantActionTimeline = new Timeline(kf);
        lm.plantActionTimeline.setCycleCount(Timeline.INDEFINITE);
        lm.plantActionTimeline.play();
    }

    public void checkWinner()
    {
        KeyFrame kf = new KeyFrame(Duration.seconds(1) , event ->
        {
            if(zombiesEnded == true && game.getZombies_list().size() == 0)
            {
                System.out.println("YOU WON!!");
                nextLevel();
            }
        });
        winnerTimeline = new Timeline(kf);
        winnerTimeline.setCycleCount(Timeline.INDEFINITE);
        winnerTimeline.play();
    }

    public void nextLevel()
    {
        closeScreen();
        loadlevel5();
    }

    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
        zombiesEnded = false;

        game = new CurrentGame();
        allPeas = new ArrayList<>();

        pausePane.setVisible(false);
        pausePane.setDisable(true);

        createLawnmovers();
        startZombies();
        startSuns();
        checkPlants();

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

    private void loadlevel5()
    {
        Timeline openLevel5 = new Timeline(new KeyFrame(Duration.millis(100) , event ->
        {
            System.out.println("Opening Level 4");
        }));
        openLevel5.setCycleCount(1);
        openLevel5.setOnFinished(e ->
        {
            try
            {
                Parent root = FXMLLoader.load(getClass().getResource("Level4.fxml"));
                Stage newGameStage = new Stage();
                newGameStage.setTitle("Level 4");
                newGameStage.setScene(new Scene(root, 1300, 650));
                newGameStage.show();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
            closeScreen();
        });
        openLevel5.play();
    }

    private void closeScreen()
    {
        stopAll();
        Stage curStage = (Stage) gameScreen.getScene().getWindow();
        curStage.close();
    }
}