package com.hyf.tank.netty;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author hyf
 * @version [版本号, 2020/5/25]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class CallableTest {

    static  ExecutorService service = new ThreadPoolExecutor(5, 5,
            60L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>());

    public static void main(String[] args) throws ExecutionException, InterruptedException {
         Future future = service.submit(new FutureTaskTest());
         future.get();
         System.out.println("=============");

    }
}
