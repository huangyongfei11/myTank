package com.hyf.tank.mode;

import com.hyf.tank.abstracts.AbstractGameObject;
import com.hyf.tank.frame.TankFrame;
import com.hyf.tank.util.ResourceMgr;

import java.awt.*;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author hyf
 * @version [版本号, 2020/5/20]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class Bullet extends AbstractGameObject {

    private int x;

    private int y;

    private Dir dir;

    private Group group;

    private static final int WIDTH = ResourceMgr.bulletU.getWidth();

    private static final int HEIGH = ResourceMgr.bulletU.getHeight();

    private static final int SPEED = 8;

    private boolean live = true;

    private Rectangle rectangle;

    public Bullet(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        rectangle = new Rectangle(x,y,WIDTH,HEIGH);
    }

    @Override
    public void paint(Graphics graphics) {
        switch (dir) {
            case L:
                graphics.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case R:
                graphics.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case U:
                graphics.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case D:
                graphics.drawImage(ResourceMgr.bulletD, x, y, null);
        }
        move();
        rectangle.x = x;
        rectangle.y = y;
    }


    private void move() {
        switch (dir) {
            case L:
                x -= SPEED;
                break;
            case R:
                x += SPEED;
                break;
            case U:
                y -= SPEED;
                break;
            case D:
                y += SPEED;
        }

        boundsCheck();
    }

    private void boundsCheck(){
        if(x<0 || y<30 ||x > TankFrame.GAME_WIDTH || y>TankFrame.GAME_HEIGHT){
           live = false;
        }
    }


    public void die(){
        this.setLive(false);
    }

    @Override
    public boolean isLive() {
        return live;
    }

    private void setLive(boolean live) {
        this.live = live;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Group getGroup() {
        return group;
    }

}
