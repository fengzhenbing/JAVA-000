/**
 * @description: 加synchronized锁   wait让主线程等待
 * @author: fzb
 * @create: 2020-11-11 10:31
 */
public class GetValue2 {
    private static int value;

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);//保证主线程先拿到锁
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (GetValue2.class){
                System.out.println(Thread.currentThread().getName()+" -- 3 拿到锁执行业务");
                value = Fibo.fibo(32);
                System.out.println(Thread.currentThread().getName()+" -- 4 notify 唤醒在waitSet里面的线程，让其到 entrySet里去竞争锁");
                GetValue2.class.notify();// 4 唤醒在waitSet里面的线程，让其到 entrySet里去竞争锁
                System.out.println(Thread.currentThread().getName()+" -- 5 退出锁");
            }
        });
        thread.start();

        try {
            System.out.println(Thread.currentThread().getName()+" --  1先拿到锁");
            synchronized (GetValue2.class){//1先拿到锁
                System.out.println(Thread.currentThread().getName()+" -- 2 wait 释放锁，进入monitor的waitSet");
                GetValue2.class.wait();// 2释放锁，进入monitor的waitSet
                System.out.println(Thread.currentThread().getName()+" -- 6 竞争到锁");
            }
            System.out.println(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
