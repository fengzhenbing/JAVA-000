import bean.Klass;
import bean.School;
import bean.Student;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.GenericApplicationContext;
import scope.FzbAttribute;
import service.LearnService;

/**
 * @description: 注解类的方式 加载bean
 * @author: fzb
 * @create: 2020-11-18 13:52
 */
public class AnnotationApplictionContext implements BeanFactoryAware{
    private static DefaultListableBeanFactory beanFactory;

    public static void main(String[] args) {
        // testAnnotationConfigApplicationContext();
       // testAnnotationConfigApplicationContext2();
         testAnnotationConfigApplicationContextWithScope();
       // testGenericApplicationContext();

     }

    /**
     * 通过指定目录扫描
     */
    public static void testAnnotationConfigApplicationContext(){
        ApplicationContext context = new AnnotationConfigApplicationContext("service");
        LearnService learnService = (LearnService) context.getBean("learnServiceImpl");
        learnService.learn();
    }

    /**
     * 通过指定类扫描
     */
    public static void testAnnotationConfigApplicationContext2(){
        ApplicationContext context = new AnnotationConfigApplicationContext(AnnotationApplictionContext.class);
        Student student = (Student) context.getBean("student");
       //获取到bean定义
        BeanDefinition beanDefinition =beanFactory.getBeanDefinition("student");

        student.sayHello(" fzb ");
    }

    /**
     * 测试自定义 scope
     */
    public static void testAnnotationConfigApplicationContextWithScope(){
        //AnnotationConfigApplicationContext
        ApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext("scope");
        FzbAttribute attribute = (FzbAttribute) annotationConfigApplicationContext.getBean("fzbAttribute");
        attribute.setObject("ddd");
        System.out.println(attribute.getObject());
    }

    public static void testGenericApplicationContext(){
        GenericApplicationContext context = new GenericApplicationContext();
        AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(context);
        reader.register(AnnotationApplictionContext.class);
        context.refresh();
        Student student = (Student) context.getBean("student");
        student.sayHello("fzb");
    }


    @Bean(value = "student",initMethod = "init")
    Student getStudent(){
        return new Student(100,"test");
    };

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }
}
