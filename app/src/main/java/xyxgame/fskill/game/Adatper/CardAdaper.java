package xyxgame.fskill.game.Adatper;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import xyxgame.fskill.R;
import xyxgame.fskill.game.Gcard;

import static xyxgame.fskill.GameActivity.cardViewModel;

public class CardAdaper extends RecyclerView.Adapter<CardAdaper.Hoder> {
    List<Gcard> cards = new ArrayList<>();

    @NonNull
    @Override
    public Hoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card, viewGroup, false);
        return new Hoder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final Hoder hoder, final int i) {
        hoder.card_name.setText(cards.get(i).getName());
        hoder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("------", "onClick: "+i);
                cardViewModel.clearSelet();
                cardViewModel.getGcards().getValue().get(i).setSelet(true);
                //cards.get(i).setSelet(!cards.get(i).isSelet());
                cardViewModel.setRemovePoint(i);
                notifyDataSetChanged();

            }
        });
        ;
    }

    public void setCards(List<Gcard> cards) {
        this.cards = cards;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    class Hoder extends RecyclerView.ViewHolder {
        TextView card_name;

        public Hoder(@NonNull View itemView) {
            super(itemView);
            card_name = itemView.findViewById(R.id.card_name);


        }
    }
}
