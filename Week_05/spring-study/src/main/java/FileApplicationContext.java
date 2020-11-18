import bean.Klass;
import bean.School;
import bean.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * @description: 文件 配置的bean spring 容器加载
 * @author: fzb
 * @create: 2020-11-18 12:40
 */
public class FileApplicationContext {
    public static void main(String[] args) {
       // testClassPathXmlApplicationContext();
        // testFileSystemXmlApplicationContext();
        testFileSystemXmlApplicationContext();
    }

    /**
     * ClassPathXmlApplicationContext
     */
    public static void testClassPathXmlApplicationContext(){
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        testBean(context);
    }

    /**
     * FileSystemXmlApplicationContext
     */
    public static void testFileSystemXmlApplicationContext(){
        ApplicationContext context  = new FileSystemXmlApplicationContext("/spring-study/src/main/resources/application.xml");
        testBean(context);
    }

    /**
     * GenericXmlApplicationContext
     */
    public static void testGenericXmlApplicationContext(){
        ApplicationContext context = new GenericXmlApplicationContext("application.xml");
        testBean(context);
    }

    private static void testBean(ApplicationContext context) {
        Student student = (Student) context.getBean("student");
        student.sayHello(" fzb ");
        Klass klass = (Klass) context.getBean("klass");
        System.out.println(klass.getStudents());
        School school = (School) context.getBean("school");
        school.ding();
    }
}
