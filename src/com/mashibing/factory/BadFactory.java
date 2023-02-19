package com.mashibing.factory;

public class BadFactory extends AbstarctFactory{
    @Override
    Tank createTank() {
        return new BadTank();
    }

    @Override
    Bullet createBullet() {
        return new BadBullet();
    }

    @Override
    Explosion createExplosion() {
        return new BadExplosion();
    }
}
