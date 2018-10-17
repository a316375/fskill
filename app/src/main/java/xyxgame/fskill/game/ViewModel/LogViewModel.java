package xyxgame.fskill.game.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import xyxgame.fskill.game.Glog;

public class LogViewModel extends ViewModel {
    MutableLiveData<List<Glog>> stringLiveData;

    public MutableLiveData<List<Glog>> getStringLiveData() {
        if (stringLiveData==null){stringLiveData=new MutableLiveData<>();        }
        return stringLiveData;
    }

}
