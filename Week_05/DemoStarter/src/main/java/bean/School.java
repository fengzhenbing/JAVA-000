package bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

@Data
@Setter
@Getter
@ToString
public class School implements ISchool {
    
    // Resource 
     Klass class1;
    

    Student student;
    
    @Override
    public void ding(){
    
        System.out.println("Class1 have " + this.class1.getStudents().size() + " students and one is " + this.student);
        
    }
    
}
