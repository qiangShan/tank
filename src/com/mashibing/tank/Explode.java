package com.mashibing.tank;

import java.awt.*;
import java.util.Random;

public class Explode {
    private int x,y;
    public static int WIDTH=ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT=ResourceMgr.explodes[0].getHeight();
    private TankFrame tankFrame=null;
    //private boolean living=true;
    private int step=0;



    public Explode(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame=tankFrame;
    }



    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void paint(Graphics graphics){
        graphics.drawImage(ResourceMgr.explodes[step++],x,y,null);
        if(step>=ResourceMgr.explodes.length){
            tankFrame.explodes.remove(this);
        }
    }


}
