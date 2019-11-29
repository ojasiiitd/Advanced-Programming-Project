package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class CurrentGame implements Serializable
{
    private int suns,score,level;
    private ArrayList<Plants> plants_list = new ArrayList<>();
    private ArrayList<Zombies> zombies_list = new ArrayList<>();
    public CurrentGame()
    { }

    public ArrayList<Zombies> getZombies_list() {
        return zombies_list;
    }

    public void setZombies_list(ArrayList<Zombies> zombies_list) {
        this.zombies_list = zombies_list;
    }

    public ArrayList<Plants> getPlants_list() {
        return plants_list;
    }

    public void setPlants_list(ArrayList<Plants> plants_list) {
        this.plants_list = plants_list;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getSuns() {
        return suns;
    }

    public void setSuns(int suns) {
        this.suns = suns;
    }
    public void savegame() {

    }
    public void restart()
    {

    }

}
