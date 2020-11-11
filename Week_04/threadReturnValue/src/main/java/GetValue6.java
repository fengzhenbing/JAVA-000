import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: ReentrantLock  condition await让主线程等待
 * @author: fzb
 * @create: 2020-11-11 10:31
 */
public class GetValue6 {
    private static int value;

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
      //  Thread mainThread = Thread.currentThread();

        Thread thread = new Thread(() -> {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " -- 执行业务");
                value = Fibo.fibo(32);
               // System.out.println(mainThread.getName() + " -- "+ mainThread.getState());
                condition.signal();
               //  System.out.println(mainThread.getName() + " -- "+ mainThread.getState());
                System.out.println(Thread.currentThread().getName() + " -- signal 唤醒通过await waiting主线程");
            } finally {
                lock.unlock();
            }

        });
        thread.start();

        try {
            lock.lock();
           // System.out.println(mainThread.getName() + " -- "+ mainThread.getState());
            System.out.println(Thread.currentThread().getName() + " -- await 释放锁，加入到等待队列");
            condition.await();
            System.out.println(Thread.currentThread().getName() + " --  被唤醒");
            System.out.println(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }


    }
}
