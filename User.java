package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String name;
    private int max_score;
    private ArrayList<Plants> unlocked_plants = new ArrayList<>();
    private int level_reached;
    private CurrentGame currentGame;

    public User(String name,int max_score, int level_reached, ArrayList<Plants> unlocked_plants,CurrentGame currentGame) {
        this.unlocked_plants = unlocked_plants;
        this.currentGame = currentGame;
        this.level_reached=level_reached;
        this.currentGame=currentGame;
        this.unlocked_plants=unlocked_plants;
    }

    public ArrayList<Plants> getUnlocked_plants() {
        return unlocked_plants;
    }

    public void setUnlocked_plants(ArrayList<Plants> unlocked_plants) {
        this.unlocked_plants = unlocked_plants;
    }

    public int getLevel_reached() {
        return level_reached;
    }

    public void setLevel_reached(int level_reached) {
        this.level_reached = level_reached;
    }

    public int getMax_score() {
        return max_score;
    }

    public void setMax_score(int max_score) {
        this.max_score = max_score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void newGame()
    {

    }
    public void LoadGame()
    {

    }
}
