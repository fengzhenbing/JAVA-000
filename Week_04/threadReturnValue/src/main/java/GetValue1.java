/**
 * @description: 共享变量 , thread.join让主线程等待
 * @author: fzb
 * @create: 2020-11-11 10:31
 */
public class GetValue1 {
    private static int value;

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            value = Fibo.fibo(32);
        });
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(value);
    }
}
