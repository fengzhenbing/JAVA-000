import java.util.concurrent.locks.LockSupport;

/**
 * @description:    suspend让主线程等待
 * @author: fzb
 * @create: 2020-11-11 10:31
 */
public class GetValue4 {
    private static int value;

    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);//保证主线程先suspend
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " -- 执行业务");
            value = Fibo.fibo(32);
            System.out.println(Thread.currentThread().getName() + " -- resume 唤醒通过suspend waiting的主线程");
            mainThread.resume();
        });
        thread.start();

        System.out.println(Thread.currentThread().getName() + " --  suspend");
        mainThread.suspend();
        System.out.println(Thread.currentThread().getName() + " --  被唤醒");
        System.out.println(value);

    }
}
