package com.mashibing.factory;
/**
 * 抽象工厂
 * */
public abstract class AbstarctFactory {

    abstract Tank createTank();
    abstract Bullet createBullet();
    abstract Explosion createExplosion();
}
