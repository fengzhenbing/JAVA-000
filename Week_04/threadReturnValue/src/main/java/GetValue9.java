import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * @description: Semaphore acquire让主线程等待
 * @author: fzb
 * @create: 2020-11-11 10:31
 */
public class GetValue9 {
    private static int value;

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);

        semaphore.acquire();
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " -- 执行业务");
            value = Fibo.fibo(32);
            semaphore.release();
        });
        thread.start();


        System.out.println(Thread.currentThread().getName() + " -- await 等待");
        semaphore.acquire();
        System.out.println(Thread.currentThread().getName() + " --  被唤醒");
        System.out.println(value);
        semaphore.release();


    }
}
