package com.hyf.tank.mode;

import com.hyf.tank.abstracts.AbstractGameObject;
import com.hyf.tank.chainofresponsibility.Collider;
import com.hyf.tank.chainofresponsibility.ColliderChain;
import com.hyf.tank.util.PropertiesUtil;

import java.awt.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author hyf
 * @version [版本号, 2020/5/23]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class GameMode implements Serializable {

    private List<AbstractGameObject> abstractGameObjects;

    private Player myTank;

    private Collider colliderChain;

    public GameMode() {
        initGameObject();
    }

    private void initGameObject() {
        myTank = new Player(100, 100, Dir.R, Group.GOOD);
        int initTankCount = Integer.parseInt(PropertiesUtil.get("initTankCount"));
        abstractGameObjects = new LinkedList<>();
        for (int i = 0; i < initTankCount; i++) {
            abstractGameObjects.add(new Tank(100 + (50 * i), 200, Dir.R, Group.BAD));
        }
        abstractGameObjects.add(new Wall(400, 300, 200, 50));

        //初始化碰撞器
        colliderChain = new ColliderChain();
    }

    public void paint(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.drawString("objects:"+abstractGameObjects.size(),10,50);
        myTank.paint(graphics);
        for (int i = 0; i < abstractGameObjects.size(); i++) {
            AbstractGameObject gameObject = abstractGameObjects.get(i);
            for (int j = 0; j < abstractGameObjects.size(); j++) {
                AbstractGameObject gameObject1 = abstractGameObjects.get(j);
                colliderChain.collider(gameObject, gameObject1, abstractGameObjects);
            }
            if (gameObject.isLive()) {
                gameObject.paint(graphics);
            }
        }


    }

    public void add(AbstractGameObject object) {
        abstractGameObjects.add(object);
    }

    public List<AbstractGameObject> getAbstractGameObjects() {
        return abstractGameObjects;
    }


    public Player getMyTank() {
        return myTank;
    }


    public Collider getColliderChain() {
        return colliderChain;
    }

}
