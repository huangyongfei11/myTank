package com.hyf.tank.chainofresponsibility;

import com.hyf.tank.abstracts.AbstractGameObject;
import com.hyf.tank.util.PropertiesUtil;

import java.util.ArrayList;
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
public class ColliderChain implements Collider {

    private static List<Collider> colliders;
    static {
        colliders = new ArrayList<>();
        try {
            String[] colliderNames = PropertiesUtil.get("colliderStrategy").split(",");
            for (String colliderName :colliderNames){
                Class clazz = Class.forName("com.hyf.tank.chainofresponsibility." + colliderName);
                colliders.add((Collider)clazz.newInstance());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean collider(AbstractGameObject gameObject1, AbstractGameObject gameObject2, List<AbstractGameObject> abstractGameObjects) {
        for(Collider collider : colliders){
            if(!collider.collider(gameObject1,gameObject2,abstractGameObjects)){
                break;
            }
        }
        return true;
    }
}
