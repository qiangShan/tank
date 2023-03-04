package com.mashibing.tank;

import java.awt.*;

public class Bullet extends GameObject{

    private static final int SPEED=10;
    public static int WIDTH=ResourceMgr.bulletD.getWidth();
    public static int HEIGHT=ResourceMgr.bulletD.getHeight();
    private int x,y;
    private Dir dir;
    private GameModel gm =null;
    private Group group=Group.BAD;
    Rectangle rectangleBullet =new Rectangle();
    private boolean living =true;

    public Bullet(int x, int y, Dir dir, Group group, GameModel gm) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group=group;
        this.gm = gm;

        rectangleBullet.x=this.x;
        rectangleBullet.y=this.y;
        rectangleBullet.width=WIDTH;
        rectangleBullet.height=HEIGHT;

        gm.add(this);
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public void paint(Graphics graphics){
        if(!living){
            gm.remove(this);
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

    public void collidWith(Tank tank) {
        if(this.group==tank.getGroup()) return;

        if(rectangleBullet.intersects(tank.rectangleTank)){
            tank.die();
            this.die();
            int eX=tank.getX()+Tank.WIDTH/2-Explode.WIDTH/2;
            int eY=tank.getY()+Tank.HEIGHT/2-Explode.HEIGHT/2;
            gm.add(new Explode(eX,eY, gm));
        }
    }

    private void die() {
        this.living=false;
    }
}
