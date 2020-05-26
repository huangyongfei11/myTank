package com.hyf.tank.chainofresponsibility;

import com.hyf.tank.abstracts.AbstractGameObject;
import com.hyf.tank.mode.Bullet;
import com.hyf.tank.mode.Wall;

import java.awt.*;
import java.util.List;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author hyf
 * @version [版本号, 2020/5/22]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class BulletWallCollider implements Collider {

    @Override
    public boolean collider(AbstractGameObject gameObject1, AbstractGameObject gameObject2, List<AbstractGameObject> abstractGameObjects) {

        if(gameObject1 instanceof Bullet && gameObject2 instanceof Wall){
            Bullet bullet = (Bullet) gameObject1;
            Wall wall = (Wall)gameObject2;
            if(bullet.getRectangle().intersects(wall.getRectangle())){
                bullet.die();
                abstractGameObjects.remove(bullet);
                return false;
            }
        }else if(gameObject1 instanceof Wall && gameObject2 instanceof Bullet){
            collider(gameObject2,gameObject1,abstractGameObjects);
        }
        return true;
    }
}
