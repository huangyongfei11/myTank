package com.hyf.tank.mode;

import java.util.Random;

/**
 * tank炮筒的方向
 */
public enum Dir {
    L,R,U,D;

    public static final Random RANDOM_INSTANCE = new Random();

    public static Dir getDir(){
        return values()[RANDOM_INSTANCE.nextInt(4)];
    }
}
