package xyxgame.fskill;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

import xyxgame.fskill.Hero.BeAttackListenser;
import xyxgame.fskill.Hero.DJ.JZYHero;
import xyxgame.fskill.Hero.DJ.ZWHero;
import xyxgame.fskill.Hero.Hero;
import xyxgame.fskill.game.Adatper.CardAdaper;
import xyxgame.fskill.game.Gcard;
import xyxgame.fskill.game.Glog;
import xyxgame.fskill.game.Adatper.LogAdapter;
import xyxgame.fskill.game.ViewModel.CardViewModel;
import xyxgame.fskill.game.ViewModel.LogViewModel;

public class GameActivity extends AppCompatActivity {

    public static CardViewModel cardViewModel;
    private Button button, play, play1, play2;
    private LogViewModel logViewModel;
    private RadioButton bt1, bt2;
    private Hero hero1;
    private Hero hero2;
    private Hero hero;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play = (Button) findViewById(R.id.play_0);
        play1 = (Button) findViewById(R.id.play_1);
        play2 = (Button) findViewById(R.id.play_2);
        bt1 = (RadioButton) findViewById(R.id.bt1);
        bt2 = (RadioButton) findViewById(R.id.bt2);
        //下面2列无用代码,负责设置选择对象的布尔值
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt2.setChecked(false);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt1.setChecked(false);
            }
        });


        Log.e("|---", "onCreate: ");
        final RecyclerView logRecycview = (RecyclerView) findViewById(R.id.rv_log);
        final RecyclerView carRecycleView = (RecyclerView) findViewById(R.id.rv_card);

        logRecycview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        carRecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        final CardAdaper cardAdapter = new CardAdaper();
        carRecycleView.setAdapter(cardAdapter);

        final LogAdapter logAdapter = new LogAdapter();
        logRecycview.setAdapter(logAdapter);

        cardViewModel = ViewModelProviders.of(this).get(CardViewModel.class);
        cardViewModel.getGcards().observe(this, new Observer<List<Gcard>>() {
            @Override
            public void onChanged(@Nullable List<Gcard> gcards) {
                cardAdapter.setCards(gcards);
            }
        });

        carRecycleView.addItemDecoration(new RecyclerView.ItemDecoration() {

            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);

                int point = parent.getChildAdapterPosition(view);
                if (cardViewModel.getGcards().getValue().get(point).isSelet()) {//如果标记是被选中的话
                    doAnimation(view, 1);//由于是被刷新了所以-1不会生效
                }
                // Log.e("----", "getItemOffsets: "+point );
                if (parent.getChildAdapterPosition(view) != (cardViewModel.getGcards().getValue().size())) {
                    if (cardViewModel.getGcards().getValue().size() > 6) {
                        if (point != 0) outRect.left = -50;
                    }
                }
            }
        });

        logViewModel = ViewModelProviders.of(this).get(LogViewModel.class);
        logViewModel.getStringLiveData().observe(this, new Observer<List<Glog>>() {
            @Override
            public void onChanged(@Nullable List<Glog> list) {
                logAdapter.setGlogs(list);
                logRecycview.scrollToPosition(logAdapter.getItemCount() - 1);
            }
        });

        ((Button) findViewById(R.id.ok)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cardViewModel.getGcards().getValue() != null && cardViewModel.getGcards().getValue().size() > 0 && cardViewModel.getRemovePoint() != -1) {
                    //log版的更新
                    List<Glog> value = logViewModel.getStringLiveData().getValue();
                    String heroname=bt1.isChecked()?bt1.getText().toString():bt2.getText().toString();

                    hero1.SetBeAttackListener(new BeAttackListenser() {

                        @Override
                        public void downlife() {
                            hero1.setLife(hero1.getLife()-1);
                            Log.e("----", hero1.getDate().name()+"downlife: 被"+"攻击了,剩余"+hero1.getLife() );
                            play1.setText(hero1.getDate().name() + "\n技能:" + hero1.getDate().skills()+"\n血量:" + hero1.getLife());
                        }
                    });
                    hero2.SetBeAttackListener(new BeAttackListenser() {

                        @Override
                        public void downlife() {
                            hero2.setLife(hero2.getLife()-1);
                            Log.e("----", hero2.getDate().name()+"downlife: 被"+"攻击了,剩余"+hero2.getLife() );
                            play2.setText(hero2.getDate().name() + "\n技能:" + hero2.getDate().skills()+"\n血量:" + hero2.getLife());
                        }
                    });

                    if (bt1.isChecked()) {
                        hero1.BeAttack();
                    } else {
                        hero2.BeAttack();
                    }
                    value.add(new Glog("你对"+heroname+"使用了一个" + cardViewModel.getGcards().getValue().get(cardViewModel.getRemovePoint()).getName()));
                    logViewModel.getStringLiveData().postValue(value);
                    //card后台数据的更新
                    cardViewModel.getGcards().getValue().remove(cardViewModel.getRemovePoint());
                    cardAdapter.setCards(cardViewModel.getGcards().getValue());
                    cardViewModel.setRemovePoint(-1);//标记为未选择卡牌,-1代表清除选中状态
                }
            }
        });

        ((Button) findViewById(R.id.cancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cardViewModel.getRemovePoint() != -1) {
                    cardViewModel.getGcards().getValue().get(cardViewModel.getRemovePoint()).setSelet(false);
                    cardAdapter.notifyDataSetChanged();
                }
                cardViewModel.setRemovePoint(-1);
            }
        });


        button = findViewById(R.id.start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(GameActivity.this, HeroActivity.class), 0);
            }
        });

        findViewById(R.id.over).setVisibility(View.GONE);

    }

    //返回来的时候
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        button.setVisibility(View.GONE);
        List<Gcard> gcards = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            gcards.add(new Gcard("杀" + i, 5, 1, false));
        }
        cardViewModel.getGcards().postValue(gcards);//放进后台数据

        final List<Glog> glogstring = new ArrayList<>();
        glogstring.add(new Glog("游戏开始了...."));
        logViewModel.getStringLiveData().postValue(glogstring);

        //获取返回来选中的英雄
        hero = (Hero) data.getSerializableExtra("hero");
        play.setText(hero.getDate().name() + "\n技能:" + hero.getDate().skills()+"\n血量:" + hero.getLife());

        //创建一个角色1
        hero1 = new ZWHero();
        play1.setText(hero1.getDate().name() + "\n技能:" + hero1.getDate().skills()+"\n血量:" + hero1.getLife());
        bt1.setText(hero1.getDate().name());
        //创建一个角色2
        hero2 = new JZYHero();
        play2.setText(hero2.getDate().name() + "\n技能:" + hero2.getDate().skills()+"\n血量:" + hero2.getLife());
        bt2.setText(hero2.getDate().name());


    }

    private void doAnimation(View view, int i) {
        Animation animation;
        if (i == 1)
            animation = new TranslateAnimation(0, 0, 0, -30);
        else animation = new TranslateAnimation(0, 0, -30, 0);
        animation.setDuration(100);
        animation.setRepeatCount(0);//动画的重复次数
        animation.setFillAfter(true);//设置为true，动画转化结束后被应用
        view.startAnimation(animation);
    }

}
