package org.fzb.rpcfx.client;

import com.alibaba.fastjson.JSON;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import net.bytebuddy.matcher.ElementMatchers;
import okhttp3.MediaType;
import org.fzb.rpcfx.api.Filter;
import org.fzb.rpcfx.api.RpcfxRequest;
import org.fzb.rpcfx.api.RpcfxResponse;
import org.fzb.rpcfx.api.ServiceProviderDesc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * BuddyProxy
 *
 * @author fengzhenbing
 */
public class BuddyProxy implements RpcfxProxy{

    private static final NetClient defaultNetClient = new OkHttpClient();

    @Override
    public <T> T create(Class<T> serviceClass, ServiceProviderDesc serviceProviderDesc, Filter... filters) {
        try {
            return (T) new ByteBuddy().subclass(Object.class)
                    .implement(serviceClass)
                    .method(ElementMatchers.isDeclaredBy(serviceClass))
                    .intercept(InvocationHandlerAdapter.of(new BuddyInvocationHandler(serviceClass,serviceProviderDesc, filters)))
                    .make()
                    .load(getClass().getClassLoader())
                    .getLoaded()
                     .newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static class BuddyInvocationHandler  implements InvocationHandler {

        public static final MediaType JSONTYPE = MediaType.get("application/json; charset=utf-8");

        private final Class<?> serviceClass;
        private final ServiceProviderDesc serviceProviderDesc;
        private final Filter[] filters;

        public <T> BuddyInvocationHandler(Class<T> serviceClass, ServiceProviderDesc serviceProviderDesc, Filter... filters) {
            this.serviceClass = serviceClass;
            this.serviceProviderDesc = serviceProviderDesc;
            this.filters = filters;
        }

        // 可以尝试，自己去写对象序列化，二进制还是文本的，，，rpcfx是xml自定义序列化、反序列化，json: code.google.com/p/rpcfx
        // int byte char float double long bool
        // [], data class

        @Override
        public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {

            // 加filter地方之二
            // mock == true, new Student("hubao");

            RpcfxRequest request = new RpcfxRequest();
            request.setServiceInterfaceClass(this.serviceClass.getName());
            request.setServiceImplClass(serviceProviderDesc.getServiceImplClass());
            request.setMethod(method.getName());
            request.setParams(params);
            request.setUrl(serviceProviderDesc.httpUrl());

            if (null!=filters) {
                for (Filter filter : filters) {
                    if (!filter.filter(request)) {
                        return null;
                    }
                }
            }


            RpcfxResponse response = defaultNetClient.sendRequest(request);

            // 加filter地方之三
            // Student.setTeacher("cuijing");

            // 这里判断response.status，处理异常
            // 考虑封装一个全局的RpcfxException

            return JSON.parse(response.getResult().toString());
        }


    }
}
