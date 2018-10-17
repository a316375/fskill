package xyxgame.fskill.game.Adatper;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import xyxgame.fskill.R;
import xyxgame.fskill.game.Glog;

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.Hoder> {
    List<Glog> glogs = new ArrayList<>();

    @NonNull
    @Override
    public Hoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.log, viewGroup, false);
        return new Hoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Hoder hoder, int i) {
        hoder.textView.setText(glogs.get(i).getString());

    }

    public void setGlogs(List<Glog> glogs) {
        this.glogs = glogs;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return glogs.size();
    }

    class Hoder extends RecyclerView.ViewHolder {
        TextView textView;

        public Hoder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.log_text);
        }
    }
}
