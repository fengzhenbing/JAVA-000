import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @description: CyclicBarrier await让主线程等待
 * @author: fzb
 * @create: 2020-11-11 10:31
 */
public class GetValue8 {
    private static int value;

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(2);

        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " -- 执行业务");
            value = Fibo.fibo(32);
           try {
                barrier.await();
               System.out.println(Thread.currentThread().getName() + " --  被唤醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        thread.start();

        try {
            System.out.println(Thread.currentThread().getName() + " -- await 等待");
            barrier.await();
            System.out.println(Thread.currentThread().getName() + " --  被唤醒");
            System.out.println(value);
        } catch (BrokenBarrierException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
