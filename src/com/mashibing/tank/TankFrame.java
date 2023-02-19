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

    GameModel gameModel=new GameModel();

    public static final int GAME_WIDTH=1080,GAME_HEIGHT=960;

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

        gameModel.paint(graphics);

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
                    gameModel.getMainTank().fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }
        private void setMainTankDir() {
            if(!bL && !bU && !bR && !bD) {
                gameModel.getMainTank().setMoving(false);
            }else{
                gameModel.getMainTank().setMoving(true);
                if(bL) gameModel.getMainTank().setDir(Dir.LEFT);
                if(bU) gameModel.getMainTank().setDir(Dir.UP);
                if(bR) gameModel.getMainTank().setDir(Dir.RIGHT);
                if(bD) gameModel.getMainTank().setDir(Dir.DOWN);
            }
        }
    }
}
