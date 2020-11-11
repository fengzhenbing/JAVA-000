import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: ReentrantLock lock
 * @author: fzb
 * @create: 2020-11-11 10:31
 */
public class GetValue5 {
    private static int value;

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        Thread thread = new Thread(() -> {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " -- 执行业务");
                value = Fibo.fibo(32);
             } finally {
                System.out.println(Thread.currentThread().getName() + " -- 释放锁");
                lock.unlock();
            }

        });
        thread.start();

        try {
            try {
                Thread.sleep(1000);//保证子线程先获取到锁
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " --  lock");
            lock.lock();
            System.out.println(value);
        } finally {
            lock.unlock();
        }

    }
}
