package com.mashibing.tank;

public class BulletTankCollider implements Collider{
    @Override
    public boolean collide(GameObject o1, GameObject o2) {

        if(o1 instanceof Bullet && o2 instanceof Tank){
            Bullet bullet=(Bullet) o1;
            Tank tank=(Tank) o2;
            //TODO copy code from method collidWith
            if(bullet.collidWith(tank)){
                return false;
            }
        }else if(o1 instanceof Tank && o2 instanceof Bullet){
            return collide(o2,o1);
        }
        return true;
    }
}
