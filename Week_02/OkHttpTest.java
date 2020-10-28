import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * @Description
 * @Author fzb
 * @date 2020.10.28 18:25
 */
public class OkHttpTest {
    OkHttpClient client = new OkHttpClient();

    /**
     * 同步调用
     * @param url
     * @return
     */
    public String  syncGet(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if(response.isSuccessful()){
                final String res = response.body().string();
                System.out.println(res);
                return res;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 异步调用
     * @param url
     */
    public void  asyncGet(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            //失败回调
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println(e.getMessage());
            }

            //成功回调
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful()){
                    final String res = response.body().string();
                    System.out.println(res);
                }

            }
        });
    }

    public static void main(String[] args){
       String res = new OkHttpTest().syncGet("http://localhost:8801");
       // new OkHttpTest().asyncGet("http://www.baidu.com");
    }
}
