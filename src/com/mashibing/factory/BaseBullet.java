package com.mashibing.factory;

import java.awt.*;

public abstract class BaseBullet {

    public abstract void paint(Graphics graphics);

    public abstract void collidWith(BaseTank baseTank);
}
