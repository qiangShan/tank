package com.mashibing.tank;

public class TankTankCollider implements Collider{
    @Override
    public boolean collide(GameObject o1, GameObject o2) {

        if(o1 instanceof Tank && o2 instanceof Tank){
            Tank tank1=(Tank) o1;
            Tank tank2=(Tank) o2;
            if(tank1.getRectangleTank().intersects(tank2.getRectangleTank())){
                tank1.stop();
            }
        }
        return true;
    }
}