package com.mashibing.tank;

import java.awt.*;
import java.util.Random;

public class Tank extends GameObject{
    private int x,y;
    private Dir dir=Dir.DOWN;
    private static final int SPEED=3;
    public static int WIDTH=ResourceMgr.goodTankU.getWidth();
    public static int HEIGHT=ResourceMgr.goodTankU.getHeight();
    private boolean moving=true;
    private boolean living=true;
    private Random random=new Random();
    private Group group=Group.BAD;
    Rectangle rectangleTank =new Rectangle();
    GameModel gm;

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Tank(int x, int y, Dir dir, Group group, GameModel gm) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group=group;
        this.gm=gm;

        rectangleTank.x=this.x;
        rectangleTank.y=this.y;
        rectangleTank.width=WIDTH;
        rectangleTank.height=HEIGHT;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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
        if(!living)
            gm.remove(this);
        switch (dir){
            case LEFT:
                graphics.drawImage( this.group==Group.GOOD? ResourceMgr.goodTankL:ResourceMgr.badTankL,x,y,null);
                break;
            case UP:
                graphics.drawImage( this.group==Group.GOOD? ResourceMgr.goodTankU:ResourceMgr.badTankU,x,y,null);
                break;
            case RIGHT:
                graphics.drawImage( this.group==Group.GOOD? ResourceMgr.goodTankR:ResourceMgr.badTankR,x,y,null);
                break;
            case DOWN:
                graphics.drawImage( this.group==Group.GOOD? ResourceMgr.goodTankD:ResourceMgr.badTankD,x,y,null);
                break;
            default:
                break;
        }
        move();
    }

    private void move() {
        if(!moving) return;

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

        if(this.group==Group.BAD && random.nextInt(100)>95)
            this.fire();

        if(this.group==Group.BAD && random.nextInt(100)>95)
            randomDir();
        //间距碰撞
        boundsCheck();
        //update rect
        rectangleTank.x=this.x;
        rectangleTank.y=this.y;

    }

    private void boundsCheck() {
        if(this.x<2) x=2;
        if(this.y<28) y=28;
        if(this.x>TankFrame.GAME_WIDTH-Tank.WIDTH-2) x=TankFrame.GAME_WIDTH-Tank.WIDTH-2;
        if(this.y>TankFrame.GAME_HEIGHT-Tank.HEIGHT-2) y=TankFrame.GAME_HEIGHT-Tank.HEIGHT-2;
    }

    private void randomDir() {
        this.dir=Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        int bX=this.x+Tank.WIDTH/2-Bullet.WIDTH/2;
        int bY=this.y+Tank.HEIGHT/2-Bullet.HEIGHT/2;
        gm.add(new Bullet(bX,bY,this.dir,this.group,this.gm));
    }

    public void die() {
        this.living=false;
    }
}
