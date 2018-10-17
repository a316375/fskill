package xyxgame.fskill;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import xyxgame.fskill.Hero.DJ.DJHero;

public class HeroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                DJHero djHero=new DJHero();
                intent.putExtra("hero",djHero);
                setResult(1,intent);
                finish();
            }
        });


    }
}
