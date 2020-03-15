import Domain.Student;
import Repository.StudentRepo;

import Service.ServiceStudent;
import Validator.StudentValidator;
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
        s2 = new Student("22", "Mihai", 932, "iona@ioana.com","Mirabela");
        repo = new StudentRepo(new StudentValidator(),"C:\\Facultate\\An III\\SSVV\\src\\main\\resources\\studenti.xml");
        service = new ServiceStudent(repo);
    }

    @Test
    public void addStudentTest1(){
        service.add(s1);
        Assert.assertEquals("Ana", service.find("22").getNume());
    }

    @Test
    public  void addStudentTest2() {
        int length = repo.size();
        service.add(s2);
        Assert.assertEquals(length, repo.size());
    }
}
