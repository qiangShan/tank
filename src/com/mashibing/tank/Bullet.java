package com.mashibing.tank;

import com.mashibing.factory.BaseBullet;
import com.mashibing.factory.BaseTank;

import java.awt.*;

public class Bullet extends BaseBullet {

    private static final int SPEED=10;
    public static int WIDTH=ResourceMgr.bulletD.getWidth();
    public static int HEIGHT=ResourceMgr.bulletD.getHeight();
    private int x,y;
    private Dir dir;
    private TankFrame tankFrame=null;
    private Group group=Group.BAD;
    Rectangle rectangleBullet =new Rectangle();
    private boolean living =true;

    public Bullet(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group=group;
        this.tankFrame=tankFrame;
        rectangleBullet.x=this.x;
        rectangleBullet.y=this.y;
        rectangleBullet.width=WIDTH;
        rectangleBullet.height=HEIGHT;

        tankFrame.bullets.add(this);
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void paint(Graphics graphics){
        if(!living){
            tankFrame.bullets.remove(this);
        }
        switch (dir){
            case LEFT:
                graphics.drawImage(ResourceMgr.bulletL,x,y,null);
                break;
            case UP:
                graphics.drawImage(ResourceMgr.bulletU,x,y,null);
                break;
            case RIGHT:
                graphics.drawImage(ResourceMgr.bulletR,x,y,null);
                break;
            case DOWN:
                graphics.drawImage(ResourceMgr.bulletD,x,y,null);
                break;
        }
        move();
    }


    private void move() {

        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
                break;
        }

        rectangleBullet.x=this.x;
        rectangleBullet.y=this.y;

        if(x<0 || y<0 || x>TankFrame.GAME_WIDTH || y>TankFrame.GAME_HEIGHT)
            living =false;
    }

    public void collidWith(BaseTank tank) {
        if(this.group==tank.getGroup()) return;

        if(rectangleBullet.intersects(tank.rectangleTank)){
            tank.die();
            this.die();
            int eX=tank.getX()+Tank.WIDTH/2-Explode.WIDTH/2;
            int eY=tank.getY()+Tank.HEIGHT/2-Explode.HEIGHT/2;
            tankFrame.explodes.add(tankFrame.gf.createExplode(eX,eY,tankFrame) );
        }
    }

    private void die() {
        this.living=false;
    }
}
