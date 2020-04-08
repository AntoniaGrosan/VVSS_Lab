import Domain.Student;
import Repository.StudentRepo;
import Service.ServiceStudent;
import Validator.StudentValidator;
import Validator.ValidationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentTest {

    private Student s1, s2, s3, s4, s5, s6;
    private ServiceStudent studentService;


    @Before
    public void setUp() {
        s1 = new Student("22", "Ana", 933, "ana@ana.com", "Ioan");
        s2 = new Student("25", "Mihai007", 932, "mihai@mahai.com", "Mirabela");
        s3 = new Student("a", "Andrei", 933, "andre@andre.com", "Mihaela");
        s4 = new Student("23", "George", 933, "geroge@george", "Ovidiu");
        s5 = new Student("24", "Maria", 9000, "mary@mary.com", "Marinela");
        s6 = new Student("26", "Ioana", 933, "ioa@ioa.com", "Danutzu$100");
        StudentRepo repoStudent = new StudentRepo(new StudentValidator(), "src/main/resources/studenti.xml");
        studentService = new ServiceStudent(repoStudent);
    }

    @Test
    public void addStudent1() {
        Student s = studentService.add(s1);
        Assert.assertEquals(s.getID(), "22");
    }

    @Test
    public void addStudent2() {
        try {
            studentService.add(s2);
        } catch (ValidationException e) {
            Assert.assertEquals("\nNume invalid", e.getMessage());
        }
    }

    @Test
    public void addStudent3() {
        try {
            studentService.add(s3);
        } catch (ValidationException e) {
            Assert.assertEquals("\nID invalid", e.getMessage());
        }
    }

    @Test
    public void addStudent4() {
        try {
            studentService.add(s4);
        } catch (ValidationException e) {
            Assert.assertEquals("\nEmail invalid", e.getMessage());
        }
    }

    @Test
    public void addStudent5() {
        try {
            studentService.add(s5);
        } catch (ValidationException e) {
            Assert.assertEquals("\nGrupa invalida", e.getMessage());
        }
    }

    @Test
    public void addStudent6() {
        try {
            studentService.add(s6);
        } catch (ValidationException e) {
            Assert.assertEquals("\nNume profesor invalid", e.getMessage());
        }
    }
}
