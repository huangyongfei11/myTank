package com.hyf.tank.strategy;

import com.hyf.tank.mode.Player;

import java.io.Serializable;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author hyf
 * @version [版本号, 2020/5/21]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface TankFireStrategy extends Serializable {

    /**
     * 开火
     */
    void fire(Player player);
}
