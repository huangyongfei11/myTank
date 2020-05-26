package com.hyf.tank.chainofresponsibility;

import com.hyf.tank.abstracts.AbstractGameObject;

import java.io.Serializable;
import java.util.List;

/**
 * 游戏实物之间的碰撞规则
 */
public interface Collider extends Serializable {

    boolean collider(AbstractGameObject gameObject1, AbstractGameObject gameObject2, List<AbstractGameObject> abstractGameObjects);
}
