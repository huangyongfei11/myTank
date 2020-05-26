package com.hyf.tank.mode;

import com.hyf.tank.abstracts.AbstractGameObject;
import com.hyf.tank.util.ResourceMgr;

import java.awt.*;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author hyf
 * @version [版本号, 2020/5/21]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class Explode extends AbstractGameObject {

    private int x,y;

    private int width, height;
    private int step = 0;
    private boolean live = true;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;

        this.width = ResourceMgr.explodes[0].getWidth();
        this.height = ResourceMgr.explodes[0].getHeight();

        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }

    @Override
    public boolean isLive() {
        return live;
    }

    public void setLive(boolean over) {
        this.live = over;
    }

    @Override
    public void paint(Graphics g) {

        if(!live) {return;}

        g.drawImage(ResourceMgr.explodes[step], x, y, null);
        step++;

        if (step >= ResourceMgr.explodes.length) {
            this.die();
        }


    }

    private void die() {
        this.live = false;
    }
}
