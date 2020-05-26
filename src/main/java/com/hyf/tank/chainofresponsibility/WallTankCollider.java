package com.hyf.tank.chainofresponsibility;

import com.hyf.tank.abstracts.AbstractGameObject;
import com.hyf.tank.mode.Tank;
import com.hyf.tank.mode.Wall;

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
public class WallTankCollider implements Collider {
    @Override
    public boolean collider(AbstractGameObject gameObject1, AbstractGameObject gameObject2, List<AbstractGameObject> abstractGameObjects) {

        if(gameObject1 instanceof Wall && gameObject2 instanceof Tank){
            Wall wall = (Wall) gameObject1;
            Tank tank = (Tank) gameObject2;
            if(wall.getRectangle().intersects(tank.getRectangleTank())){
                tank.back();
            }
        }else  if (gameObject2 instanceof Wall && gameObject1 instanceof Tank){
            collider(gameObject2,gameObject1,abstractGameObjects);
        }
        return true;
    }
}
