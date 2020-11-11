import java.util.concurrent.*;

/**
 * @description:  线程池提交Callable，让主线程future.get() 等待直到拿到结果
 * @author: fzb
 * @create: 2020-11-11 10:31
 */
public class GetValue10 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return Fibo.fibo(32);
            }
        });

        try {
            Integer value = future.get();
            System.out.println(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executorService.shutdownNow();

    }
}
