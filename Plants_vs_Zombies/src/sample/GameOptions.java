package sample;

import java.awt.*;
import java.net.URL;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.util.ResourceBundle;

public class GameOptions implements Initializable
{
    @FXML
    private Button resumeBtn;

    @FXML
    public void exitGame()
    {
        System.exit(0);
    }

    @FXML
    public void resumeGame()
    {
    }

    @Override
    public void initialize(URL url , ResourceBundle rb)
    {
        //TODO
    }
}