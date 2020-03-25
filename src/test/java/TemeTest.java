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

public class TemeTest {

    private Student s1,s2;
    private Teme a1,a2;
    private StudentRepo repoStudent;
    private TemeRepo repoTeme;
    private ServiceStudent studentService;
    private ServiceTeme temeService;
    @Before
    public void setUp(){
        a1 = new Teme(15,"First Assignment",6,3);
        a2 = new Teme(16,"Second Assignment",0,5);
        repoTeme = new TemeRepo(new TemeValidator(),"C:\\Facultate\\An III\\SSVV\\src\\main\\resources\\teme.xml");
        temeService = new ServiceTeme(repoTeme);
    }

    @Test
    public void addAssignment1(){
        try {
            temeService.add(a1);
        } catch (ValidationException e) {
            Assert.assertEquals("\nDeadline invalid", e.getMessage());
        }
    }

    @Test
    public void addAssignment2() {
        try {
            temeService.add(a2);
        } catch (ValidationException e) {
            Assert.assertEquals("\nSaptamana in care tema a fost primita este invalida", e.getMessage());
        }
    }
}
