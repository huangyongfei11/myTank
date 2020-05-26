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
public class Tank extends AbstractGameObject {

    private static final int SPEED = 5;

    private int x, y;

    private Dir dir;

    private boolean moving = true;

    private boolean live;

    private Group group;

    static final int WIDTH = ResourceMgr.goodTankU.getWidth();

    static final int HEIGHT = ResourceMgr.goodTankU.getHeight();

    private Rectangle rectangleTank ;

    private int oldX, oldY;

    public Tank(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.live = true;
        this.dir = dir;
        this.group = group;
        this.oldX = x;
        this.oldY = y;
        rectangleTank = new Rectangle(x,y,Tank.WIDTH,Tank.HEIGHT);
    }

    @Override
    public void paint(Graphics graphics) {
        if(!this.isLive()){
            return;
        }
        switch (dir) {
            case L:
                graphics.drawImage(ResourceMgr.badTankL, x, y, null);
                break;
            case R:
                graphics.drawImage(ResourceMgr.badTankR, x, y, null);
                break;
            case U:
                graphics.drawImage(ResourceMgr.badTankU, x, y, null);
                break;
            case D:
                graphics.drawImage(ResourceMgr.badTankD, x, y, null);
        }
        oldY = y;
        oldX = x;
        move();
        rectangleTank.x = x;
        rectangleTank.y = y;
        checkBoundary();
        if (Dir.RANDOM_INSTANCE.nextInt(100) > 90) {
            dir = Dir.getDir();
            fire();
        }

    }

    private void checkBoundary() {
        if (x < WIDTH || y < HEIGHT || x + WIDTH > TankFrame.GAME_WIDTH || y + HEIGHT > TankFrame.GAME_HEIGHT) {
            back();
        }
    }

    public void back() {
        x = oldX;
        y = oldY;
    }

    private void fire() {
        int bY = y + ResourceMgr.goodTankU.getHeight() / 2 - ResourceMgr.bulletU.getHeight() / 2;
        int bX = x + ResourceMgr.goodTankU.getWidth() / 2 - ResourceMgr.bulletU.getWidth() / 2;
        TankFrame.INSTANCE.getGameMode().add(new Bullet(bX, bY, dir, group));
    }
    public void die(){
        this.setLive(false);
        TankFrame.INSTANCE.getGameMode().add(new Explode(x,y));
    }


    private void move() {
        if (!moving) {
            return;
        }
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
    }

    public Tank() {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Rectangle getRectangleTank() {
        return rectangleTank;
    }
}
