import java.util.concurrent.*;

/**
 * @description:   提交FutureTask，让主线程futureTask.get() 等待直到拿到结果
 * @author: fzb
 * @create: 2020-11-11 10:31
 */
public class GetValue11 {

    public static void main(String[] args) {
         FutureTask<Integer> futureTask = new FutureTask(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return Fibo.fibo(32);
            }
        });


         new Thread(futureTask).start();

        try {
            Integer value = futureTask.get();
            System.out.println(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
