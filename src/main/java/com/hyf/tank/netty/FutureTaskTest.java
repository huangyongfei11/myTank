package com.hyf.tank.netty;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author hyf
 * @version [版本号, 2020/5/25]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class FutureTaskTest implements Callable {

    public int num;

    @Override
    public Object call() throws Exception {
        System.out.println("11111111");
        Thread.sleep(5000);
        this.num = 123;

        return this.num;
    }

    public static void main(String[] args) throws Exception {
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new FutureTaskTest());
        Thread thread = new Thread(futureTask);
        thread.start();

        futureTask.get();
        System.out.println("2121212121");


    }
}
