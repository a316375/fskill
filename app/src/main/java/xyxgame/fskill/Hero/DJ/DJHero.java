package xyxgame.fskill.Hero.DJ;


import xyxgame.fskill.Hero.Date;
import xyxgame.fskill.Hero.Hero;

public class DJHero extends Hero {

    public DJHero() {
        setLife(5);
    }

    //基础信息
    @Override
    public Date getDate() {
        Date date=new Date() {


            @Override
            public String skills() {
                return "九尾狐";
            }

            @Override
            public String name() {
                return "妲己";
            }
        };
        return date;
    }




}
