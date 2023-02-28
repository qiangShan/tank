package com.mashibing.factory;

import com.mashibing.tank.Group;

import java.awt.*;

public abstract class BaseTank {
    public Group group=Group.BAD;
    public Rectangle rectangleTank =new Rectangle();

    public abstract void paint(Graphics graphics);

    public  Group getGroup(){
        return this.group;
    }

    public abstract void die();

    public abstract int getX();

    public abstract int getY();
}
