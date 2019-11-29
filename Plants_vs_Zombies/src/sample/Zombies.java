package sample;

import java.io.Serializable;

public class Zombies implements Serializable {

    private int xposition,yposition,health,level,attack_value,defence_value;
    private float speed;
    public Zombies(int health,int attack_value,int defence_value,int level,float speed)
    {
        this.health=health;
        this.level=level;
        this.attack_value=attack_value;
        this.defence_value=defence_value;
        this.speed=speed;
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

    public int getYposition() {
        return yposition;
    }

    public void setYposition(int yposition) {
        this.yposition = yposition;
    }

    public int getXposition() {
        return xposition;
    }

    public void setXposition(int xposition) {
        this.xposition = xposition;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
