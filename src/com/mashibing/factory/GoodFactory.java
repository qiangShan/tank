package com.mashibing.factory;

public class GoodFactory extends AbstarctFactory{
    @Override
    Tank createTank() {
        return new BadTank();
    }

    @Override
    Bullet createBullet() {
        return new GoodBullet();
    }

    @Override
    Explosion createExplosion() {
        return new GoodExplosion();
    }
}
