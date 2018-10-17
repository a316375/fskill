package xyxgame.fskill.Hero.DJ;

import xyxgame.fskill.Hero.Date;
import xyxgame.fskill.Hero.Hero;

public class ZWHero extends Hero {
    public ZWHero() {
        setLife(3);
    }

    @Override
    public Date getDate() {

        return new Date() {


            @Override
            public String skills() {
                return "暴虐";
            }

            @Override
            public String name() {
                return "纣王";
            }
        };
    }
}
