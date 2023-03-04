package com.mashibing.tank;

public class BulletTankCollider implements Collider{
    @Override
    public void collide(GameObject o1, GameObject o2) {

        if(o1 instanceof Bullet && o2 instanceof Tank){
            Bullet bullet=(Bullet) o1;
            Tank tank=(Tank) o2;
            bullet.collidWith(tank);
        }else if(o1 instanceof Tank && o2 instanceof Bullet){
            collide(o2,o1);
        }else {
            return;
        }
    }
}
