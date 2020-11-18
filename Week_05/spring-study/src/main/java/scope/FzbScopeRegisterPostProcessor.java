package scope;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;


@Component
public  class FzbScopeRegisterPostProcessor implements BeanFactoryPostProcessor {


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println(this+"工作。。。");
        FzbScope fzbScope = (FzbScope) beanFactory.getBean("fzbScope");
        beanFactory.registerScope("fzbScope",fzbScope);//注册scope
    }
}