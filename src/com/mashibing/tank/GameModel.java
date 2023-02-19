package com.mashibing.tank;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {

    Tank myTank=new Tank(200,500,Dir.DOWN,Group.GOOD,this);
    java.util.List<Bullet> bullets=new ArrayList<Bullet>();
    java.util.List<Tank> tanks=new ArrayList<Tank>();
    List<Explode> explodes=new ArrayList<Explode>();

    public GameModel(){
        //初始化敌方坦克
        for(int i=0;i<5;i++){
            tanks.add(new Tank(50+i*80,200,Dir.DOWN,Group.BAD,this));
        }
    }
    public void paint(Graphics graphics) {

        Color color=graphics.getColor();
        graphics.setColor(Color.WHITE);
        graphics.drawString("子弹的数量:"+bullets.size(),10,60);
        graphics.drawString("敌人的数量:"+tanks.size(),10,80);
        graphics.drawString("爆炸数量:"+explodes.size(),10,100);
        graphics.setColor(color);
        myTank.paint(graphics);
        for(int i=0;i<bullets.size();i++){
            bullets.get(i).paint(graphics);
        }

        for(int i=0;i<tanks.size();i++){
            tanks.get(i).paint(graphics);
        }

        for(int i=0;i<bullets.size();i++){
            for(int j=0;j<tanks.size();j++){
                bullets.get(i).collidWith(tanks.get(j));
            }
        }

        for(int i=0;i<explodes.size();i++){
            explodes.get(i).paint(graphics);
        }


        /*
         Iterator<Bullet> iterator = bullets.iterator();
         for(bullets.iterator();iterator.hasNext();){
         Bullet bullet=iterator.next();
         bullet.paint(graphics);
         if(!bullet.live){
            iterator.remove();
            }
         }
         */
    }

    public Tank getMainTank(){
        return myTank;
    }
}
