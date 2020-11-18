package scope;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author fzb
 * @date 2020.08.07 15:48
 */
@Component
@Scope("fzbScope")
public class FzbAttribute implements InitializingBean, DisposableBean {

   private Object object;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("fzbScope.initialized()");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("fzbScope.destroy()");
    }


}
