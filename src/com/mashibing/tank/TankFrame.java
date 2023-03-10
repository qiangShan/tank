package com.mashibing.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class TankFrame extends Frame {

    Tank myTank=new Tank(200,500,Dir.DOWN,Group.GOOD,this);
    List<Bullet> bullets=new ArrayList<Bullet>();
    List<Tank> tanks=new ArrayList<Tank>();
    public static final int GAME_WIDTH=1080,GAME_HEIGHT=960;
    List<Explode> explodes=new ArrayList<Explode>();
    public TankFrame(){
        setSize(GAME_WIDTH,GAME_HEIGHT);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);

        this.addKeyListener(new MyKeyListener());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    Image offScreenImage=null;
    @Override
    public void update(Graphics graphics){
        if(offScreenImage == null){
            offScreenImage=this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics gOffScreen=offScreenImage.getGraphics();
        Color color=gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOffScreen.setColor(color);
        paint(gOffScreen);
        graphics.drawImage(offScreenImage,0,0,null);
    }

    @Override
    public void paint(Graphics graphics){
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

    class MyKeyListener extends KeyAdapter{

        boolean bL=false;
        boolean bU=false;
        boolean bR=false;
        boolean bD=false;

        @Override
        public void keyPressed(KeyEvent e) {
           int key=e.getKeyCode();
           switch (key){
               case KeyEvent.VK_LEFT:
                    bL=true;
                   break;
               case KeyEvent.VK_UP:
                   bU=true;
                   break;
               case KeyEvent.VK_RIGHT:
                   bR=true;
                   break;
               case KeyEvent.VK_DOWN:
                   bD=true;
                   break;
               default:
                   break;
           }
           setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key=e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL=false;
                    break;
                case KeyEvent.VK_UP:
                    bU=false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR=false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD=false;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }
        private void setMainTankDir() {
            if(!bL && !bU && !bR && !bD) {
                myTank.setMoving(false);
            }else{
                myTank.setMoving(true);
                if(bL) myTank.setDir(Dir.LEFT);
                if(bU) myTank.setDir(Dir.UP);
                if(bR) myTank.setDir(Dir.RIGHT);
                if(bD) myTank.setDir(Dir.DOWN);
            }
        }
    }
}
