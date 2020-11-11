import java.util.concurrent.locks.LockSupport;

/**
 * @description: LockSupport  park让主线程等待
 * @author: fzb
 * @create: 2020-11-11 10:31
 */
public class GetValue3 {
    private static int value;

    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();

        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " -- 执行业务");
            value = Fibo.fibo(32);
            System.out.println(Thread.currentThread().getName() + " -- unpark 唤醒通过park waiting的主线程");
            LockSupport.unpark(mainThread);
        });
        thread.start();

        System.out.println(Thread.currentThread().getName() + " --  park");
        LockSupport.park();
        System.out.println(Thread.currentThread().getName() + " --  被唤醒");
        System.out.println(value);

    }
}
