package com.hyf.tank.abstracts;

import java.awt.*;
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
public abstract class AbstractGameObject implements Serializable {

    public abstract void paint(Graphics graphics);

    public abstract boolean isLive();
}
