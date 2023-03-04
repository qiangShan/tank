package com.mashibing.tank;

import java.awt.*;

public class Explode extends GameObject{
    private int x,y;
    public static int WIDTH=ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT=ResourceMgr.explodes[0].getHeight();
    private GameModel gm =null;
    //private boolean living=true;
    private int step=0;



    public Explode(int x, int y, GameModel gm) {
        this.x = x;
        this.y = y;
        this.gm = gm;
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

    @Override
    public void paint(Graphics graphics){
        graphics.drawImage(ResourceMgr.explodes[step++],x,y,null);
        if(step>=ResourceMgr.explodes.length){
            gm.remove(this);
        }
    }


}
