import Domain.Student;
import Domain.Teme;
import Repository.StudentRepo;

import Repository.TemeRepo;
import Service.ServiceStudent;
import Service.ServiceTeme;
import Validator.StudentValidator;
import Validator.TemeValidator;
import Validator.ValidationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentTest {

    private Student s1,s2;
    private Teme a1,a2;
    private StudentRepo repoStudent;
    private TemeRepo repoTeme;
    private ServiceStudent studentService;
    private ServiceTeme temeService;
    @Before
    public void setUp(){
        s1 = new Student("22","Ana",933,"ana@ana.com","Ioan");
        s2 = new Student("25", "Mihai007", 932, "mihai@mahai.com","Mirabela");
        repoStudent = new StudentRepo(new StudentValidator(),"C:\\Facultate\\An III\\SSVV\\src\\main\\resources\\studenti.xml");
        studentService = new ServiceStudent(repoStudent);
    }

    @Test
    public void addStudent1(){
        Student s = studentService.add(s1);
        Assert.assertEquals(s.getID(), "22");
    }

    @Test
    public  void addStudent2() {
        try {
            studentService.add(s2);
        } catch (ValidationException e)
        {
            Assert.assertEquals("\nNume invalid", e.getMessage());
        }

    }
}
