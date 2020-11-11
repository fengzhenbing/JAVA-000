import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.function.Supplier;

/**
 * @description:   CompletableFuture.supplyAsync，让主线程futureTask.get() 等待直到拿到结果
 * @author: fzb
 * @create: 2020-11-11 10:31
 */
public class GetValue12 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return Fibo.fibo(32);
            }
        });

        Integer value = future.get();
        System.out.println(value);


    }
}
