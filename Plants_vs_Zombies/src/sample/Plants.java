package sample;

import java.io.Serializable;

public abstract class Plants implements Serializable, Currency {
    private int cost;
    private int Health;
    private String name;
    private int AttackValue;
    private int DefenceValue;
    private int x_pos;
    private int y_pos;
    private int wait_time;
    private int level;


    public Plants(int AttackValue ,int DefenceValue ,int wait_time,int cost) {

        this.AttackValue=AttackValue;
        this.DefenceValue=DefenceValue;
        this.wait_time=wait_time;
        this.cost=cost;
    }


    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getHealth() {
        return Health;
    }

    public void setHealth(int Health) {
        this.Health = Health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttackValue() {
        return AttackValue;
    }

    public void setAttackValue(int attackValue) {
        AttackValue = attackValue;
    }

    public int getDefenceValue() {
        return DefenceValue;
    }



    public void setDefenceValue(int defenceValue) {
        DefenceValue = defenceValue;
    }

    public int getX_pos() {
        return x_pos;
    }

    public void setX_pos(int x_pos) {
        this.x_pos = x_pos;
    }

    public int getY_pos() {
        return y_pos;
    }

    public void setY_pos(int y_pos) {
        this.y_pos = y_pos;
    }

    public int getWait_time() {
        return wait_time;
    }

    public void setWait_time(int wait_time) {
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

     public void ability(Zombies z) {



    }
}
