package cn.yongjie;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


public class FutureTest {
    public static void main(String[] args) {


        /*
        synchronism
         */
//        Long start = System.currentTimeMillis();
//
//        Thread th1 = new ColdDishThread();
//
//        try {
//            th1.start();
//            th1.join();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        Thread t2 = new BumThread();
//
//        try {
//            t2.start();
//            t2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        long end = System.currentTimeMillis();
//        System.out.println("准备完毕时间："+(end-start));


        long start = System.currentTimeMillis();


        // 等凉菜
        Callable ca1 = new Callable(){

            public String call() throws Exception {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "凉菜准备完毕";
            }
        };
        FutureTask<String> ft1 = new FutureTask<String>(ca1);
        new Thread(ft1).start();

        // 等包子 -- 必须要等待返回的结果，所以要调用join方法
        Callable ca2 = new Callable(){

            public Object call() throws Exception {
                try {
                    Thread.sleep(1000*3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "包子准备完毕";
            }
        };
        FutureTask<String> ft2 = new FutureTask<String>(ca2);
        new Thread(ft2).start();

        try {
            System.out.println(ft1.get());
            System.out.println(ft2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("准备完毕时间："+(end-start));

    }
}

class BumThread extends Thread{

    @Override
    public void run() {

        try{
            Thread.sleep(3000);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

class ColdDishThread extends Thread {

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println("凉菜准备完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
