package xyxgame.fskill.Hero.DJ;

import xyxgame.fskill.Hero.Date;
import xyxgame.fskill.Hero.Hero;

public class JZYHero extends Hero {
    public JZYHero() {
        setLife(6);
    }

    @Override
    public Date getDate() {
        return new Date() {


            @Override
            public String skills() {
                return "老不死";
            }

            @Override
            public String name() {
                return "姜子牙";
            }
        };
    }
}
