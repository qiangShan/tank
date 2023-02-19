package com.mashibing.strategy;

import com.mashibing.tank.Bullet;
import com.mashibing.tank.Tank;

public class DefaultFireStrategy implements FireStrategy{

    @Override
    public void fire(Tank tank) {
        int bX=tank.x+Tank.WIDTH/2- Bullet.WIDTH/2;
        int bY=tank.y+Tank.HEIGHT/2-Bullet.HEIGHT/2;
        new Bullet(bX,bY,tank.dir,tank.group,tank.tankFrame);
    }
}