package xyxgame.fskill.game;

import android.arch.lifecycle.ViewModel;

public class Gcard   {
    String name;
    int number;
    int color;
    boolean isSelet;

    public Gcard(String name, int number, int color, boolean isSelet) {
        this.name = name;
        this.number = number;
        this.color = color;
        this.isSelet = isSelet;
    }

    public boolean isSelet() {
        return isSelet;
    }

    public void setSelet(boolean selet) {
        isSelet = selet;
    }


    @Override
    public String toString() {
        return "Gcard{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", color=" + color +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Number getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
