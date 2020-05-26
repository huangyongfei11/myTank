package com.hyf.tank.mode;

import com.hyf.tank.abstracts.AbstractGameObject;
import com.hyf.tank.strategy.TankFireStrategy;
import com.hyf.tank.util.PropertiesUtil;
import com.hyf.tank.util.ResourceMgr;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author hyf
 * @version [版本号, 2020/5/20]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class Player extends AbstractGameObject {

    private static final int SPEED = 5;

    private int x, y;

    private Dir dir;

    private boolean live;

    private boolean left, right, up, down;

    private boolean moving;

    private Group group;

    private TankFireStrategy fireStrategy;

    public Player() {
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Player(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        initTankFireStrategy();
    }


    public void initTankFireStrategy(){
        try {
            Class  strategyClass = Class.forName("com.hyf.tank.strategy." + PropertiesUtil.get("tankFireStrategy"));
            strategyClass.getDeclaredConstructors();
            fireStrategy = (TankFireStrategy)strategyClass.newInstance() ;
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    @Override
    public void paint(Graphics graphics) {
        switch (dir) {
            case L:
                graphics.drawImage(ResourceMgr.goodTankL,x,y,null);
                break;
            case R:
                graphics.drawImage(ResourceMgr.goodTankR,x,y,null);
                break;
            case U:
                graphics.drawImage(ResourceMgr.goodTankU,x,y,null);
                break;
            case D:
                graphics.drawImage(ResourceMgr.goodTankD,x,y,null);
        }

        move();
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                left = true;
                break;
            case KeyEvent.VK_RIGHT:
                right = true;
                break;
            case KeyEvent.VK_UP:
                up = true;
                break;
            case KeyEvent.VK_DOWN:
                down = true;
                break;
        }
        setDir();
    }

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                left = false;
                break;
            case KeyEvent.VK_RIGHT:
                right = false;
                break;
            case KeyEvent.VK_UP:
                up = false;
                break;
            case KeyEvent.VK_DOWN:
                down = false;
                break;
            case KeyEvent.VK_CONTROL:
                fire();
                break;
        }
        setDir();
    }

    public void fire(){
        fireStrategy.fire(this);
    }
    public void setDir() {
        if (!left && !right && !up && !down) {
            moving = false;
        }else {
            moving = true;
        }
        if (left && !right && !up && !down) {
            dir = Dir.L;
        }
        if (!left && right && !up && !down) {
            dir = Dir.R;
        }
        if (!left && !right && up && !down) {
            dir = Dir.U;
        }
        if (!left && !right && !up && down) {
            dir = Dir.D;
        }
    }

    public void move() {
        if(!moving){return;}
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

    @Override
    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }
}
