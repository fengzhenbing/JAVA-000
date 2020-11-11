/**
 * @description: fobo数列计算
 * @author: fzb
 * @create: 2020-11-11 10:33
 */
public class Fibo {

    public static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
