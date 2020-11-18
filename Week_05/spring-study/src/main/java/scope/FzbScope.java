package scope;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.context.support.SimpleThreadScope;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;


@Component
public class FzbScope implements Scope , BeanFactoryAware {

    private static final Log logger = LogFactory.getLog(SimpleThreadScope.class);

    private  BeanFactory beanFactory;

    private final  Map<String, Object> fzbScope = new ConcurrentHashMap<>(256);

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Objects.requireNonNull(name);
        Object  scopedObject = this.fzbScope.get(name);
        if (scopedObject == null) {
            synchronized (this.fzbScope){
                scopedObject = this.fzbScope.get(name);
                if(scopedObject == null){
                    scopedObject = objectFactory.getObject();
                    fzbScope.put(name, scopedObject);
                }
            }
        }
        return scopedObject;
    }

    @Override
    public Object remove(String name) {
        synchronized (this.fzbScope){
            return fzbScope.remove(name);
        }
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        logger.info("FzbScope"+name+" destruction");
        callback.run();
    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return this.hashCode()+"";
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }


}
