import bean.ISchool;
import bean.Klass;
import bean.School;
import bean.Student;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: fzb
 * @create: 2020-11-18 14:44
 */
@Configuration
@EnableConfigurationProperties(DemoPropeties.class)
@ConditionalOnProperty(
        name = {"demo.enabled"}
        //,
        // matchIfMissing = true
)
public class DemoAutoConfiguration {

    @Bean("studentTony")
    public Student student(){
        return new Student(10,"tony");
    }

    @Bean("klass")
    public Klass klass(Student student){
        Klass klass = new Klass();
        List<Student> studentList = new ArrayList<Student>();
        studentList.add(student);
        klass.setStudents(studentList);
        return klass;
    }

    @Bean("school")
    public School school(Klass klass ,Student student){
        School school = new School();
        school.setClass1(klass);
        school.setStudent(student);
        return school;
    }

}
