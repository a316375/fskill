package xyxgame.fskill.game;

import android.arch.lifecycle.ViewModel;

public class Glog   {
    String string;

    @Override
    public String toString() {
        return "Glog{" +
                "string='" + string + '\'' +
                '}';
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Glog(String string) {

        this.string = string;
    }
}
