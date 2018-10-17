package xyxgame.fskill.game.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import xyxgame.fskill.game.Gcard;
import xyxgame.fskill.game.Glog;

public class CardViewModel extends ViewModel {

    MutableLiveData<List<Gcard>> gcards;
    int removePoint=-1;

    public int getRemovePoint() {
        return removePoint;
    }

    public void setRemovePoint(int removePoint) {
        this.removePoint = removePoint;
    }

    public MutableLiveData<List<Gcard>> getGcards() {
        if (gcards==null)gcards=new MutableLiveData<List<Gcard>>();
        return gcards;
    }

    public void setGcards(MutableLiveData<List<Gcard>>gcards) {
        this.gcards = gcards;
    }

    public void clearSelet(){
        for (int i = 0; i < gcards.getValue().size(); i++) {
            gcards.getValue().get(i).setSelet(false);
        }
    }

    public void remove(int point){
        gcards.getValue().remove(point);
    }
}
