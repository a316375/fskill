package xyxgame.fskill.Hero;

import java.io.Serializable;

/**
 * 英雄的基类
 */
public abstract class Hero implements Serializable {
   public Date date;

   public int life;

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    ;
    BeAttackListenser beAttackListenser;

    public void SetBeAttackListener(BeAttackListenser beAttackListenser) {
        this.beAttackListenser = beAttackListenser;
    }

    public void BeAttack() {
         beAttackListenser.downlife();
    }
}
