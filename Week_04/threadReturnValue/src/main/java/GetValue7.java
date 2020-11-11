import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: CountDownLatch await让主线程等待
 * @author: fzb
 * @create: 2020-11-11 10:31
 */
public class GetValue7 {
    private static int value;

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);


        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " -- 执行业务");
            value = Fibo.fibo(32);
            latch.countDown();
            System.out.println(Thread.currentThread().getName() + " -- countDown 唤醒通过await waiting的主线程");

        });
        thread.start();

        try {
            System.out.println(Thread.currentThread().getName() + " -- await 等待");
            latch.await();
            System.out.println(Thread.currentThread().getName() + " --  被唤醒");
            System.out.println(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
