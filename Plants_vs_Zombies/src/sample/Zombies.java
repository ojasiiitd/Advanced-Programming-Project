package sample;

import javafx.scene.image.ImageView;

import java.io.Serializable;

public class Zombies implements Serializable {

    private int health , level , attack_value , defence_value;
    private float speed;
    private double xposition , yposition;

    public ImageView img;
    public boolean hittingPlant;
    public Zombies(ImageView img , int health , int attack_value , float speed)
    {
        this.img = img;
        this.health=health;
        this.attack_value=attack_value;
        this.speed=speed;
        this.hittingPlant = false;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getDefence_value() {
        return defence_value;
    }

    public void setDefence_value(int defence_value) {
        this.defence_value = defence_value;
    }

    public int getAttack_value() {
        return attack_value;
    }

    public void setAttack_value(int attack_value) {
        this.attack_value = attack_value;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getYposition() {
        return yposition;
    }

    public void setYposition(double yposition) {
        this.yposition = yposition;
    }

    public double getXposition() {
        return xposition;
    }

    public void setXposition(double xposition) {
        this.xposition = xposition;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
