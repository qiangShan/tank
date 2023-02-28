package com.mashibing.factory;

import com.mashibing.tank.ResourceMgr;
import com.mashibing.tank.TankFrame;

import java.awt.*;

public class RectExcplode extends BaseExplode{

    private int x,y;
    public static int WIDTH= ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT=ResourceMgr.explodes[0].getHeight();
    private TankFrame tankFrame=null;
    private int step=0;



    public RectExcplode(int x, int y, TankFrame tankFrame) {
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

    @Override
    public void paint(Graphics graphics){
        //graphics.drawImage(ResourceMgr.explodes[step++],x,y,null);
        Color color=graphics.getColor();
        graphics.setColor(Color.RED);
        graphics.fillRect(x,y,10*step,10*step);
        step++;
        if(step>=8){
            tankFrame.explodes.remove(this);
        }
        graphics.setColor(color);
    }
}
