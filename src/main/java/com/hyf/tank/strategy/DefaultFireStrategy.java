package com.hyf.tank.strategy;

import com.hyf.tank.frame.TankFrame;
import com.hyf.tank.mode.Bullet;
import com.hyf.tank.mode.GameMode;
import com.hyf.tank.mode.Player;
import com.hyf.tank.util.ResourceMgr;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author hyf
 * @version [版本号, 2020/5/21]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class DefaultFireStrategy implements TankFireStrategy {

    @Override
    public void fire(Player player) {
        int bX = player.getX() + ResourceMgr.goodTankU.getWidth()/2 - ResourceMgr.bulletU.getWidth()/2;
        int bY = player.getY() + ResourceMgr.goodTankU.getHeight()/2 - ResourceMgr.bulletU.getHeight()/2;
        TankFrame.INSTANCE.getGameMode().add(new Bullet(bX,bY,player.getDir(),player.getGroup()));
    }
}
