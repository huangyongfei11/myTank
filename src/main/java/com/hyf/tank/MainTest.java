package com.hyf.tank;

import com.hyf.tank.frame.TankFrame;
import com.hyf.tank.mode.Audio;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author hyf
 * @version [版本号, 2020/5/20]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class MainTest {

    public static void main(String[] args) {

        TankFrame tankFrame = TankFrame.INSTANCE;
        tankFrame.setVisible(true);

        new Thread(() -> new Audio("audio/war1.wav").loop()).start();
        for (;;){
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tankFrame.repaint();
        }

    }
}
