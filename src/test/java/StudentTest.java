import Domain.Student;
import Repository.StudentRepo;

import Service.ServiceStudent;
import Validator.StudentValidator;
import Validator.ValidationException;
import Validator.Validator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentTest {

    private Student s1,s2;
    private StudentRepo repo;
    private ServiceStudent service;
    @Before
    public void setUp(){
        s1 = new Student("22","Ana",933,"ana@ana.com","Ioan");
        s2 = new Student("25", "Mihai007", 932, "mihai@mahai.com","Mirabela");
        repo = new StudentRepo(new StudentValidator(),"C:\\Facultate\\An III\\SSVV\\src\\main\\resources\\studenti.xml");
        service = new ServiceStudent(repo);
    }

    @Test
    public void addStudent1(){
        Student s = service.add(s1);
        Assert.assertEquals(s.getID(), "22");
    }

    @Test
    public  void addStudent2() {
        try {
            service.add(s2);
        } catch (ValidationException e)
        {
            Assert.assertEquals("\nNume invalid", e.getMessage());
        }

    }
}
