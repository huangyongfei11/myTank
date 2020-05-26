package com.hyf.tank.mode;

import com.hyf.tank.abstracts.AbstractGameObject;

import java.awt.*;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author hyf
 * @version [版本号, 2020/5/22]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class Wall extends AbstractGameObject {

    private int x,y;

    private int w ,h;

    private Rectangle rectangle ;

    public Wall(int x,int y,int w,int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        rectangle = new Rectangle(x,y,w,h);
    }


    @Override
    public void paint(Graphics graphics) {
        graphics.setColor(Color.GRAY);
        graphics.fillRect(x,y,w,h);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    @Override
    public boolean isLive() {
        return true;
    }
}
