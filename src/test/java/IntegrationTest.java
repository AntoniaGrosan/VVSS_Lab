import Domain.Nota;
import Domain.Student;
import Domain.Tema;
import Repository.NoteRepo;
import Repository.StudentRepo;
import Repository.TemeRepo;
import Service.ServiceNote;
import Service.ServiceStudent;
import Service.ServiceTeme;
import Validator.NotaValidator;
import Validator.StudentValidator;
import Validator.TemeValidator;
import Validator.ValidationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.AbstractMap;
import java.util.Map;

public class IntegrationTest {
    private Nota g;
    private ServiceNote serviceNote;
    private TemeRepo repoTeme;
    private ServiceTeme temeService;
    private Student s;
    private ServiceStudent studentService;

    @Before
    public void setUp() {
        s = new Student("22", "Ana", 933, "ana@ana.com", "Ioan");
        Tema a = new Tema(15,"First Assignment",6,18);
        Map.Entry<String, Integer> nid = new AbstractMap.SimpleEntry<String, Integer>("1",1);
        g = new Nota(nid,s,a,10,3);
        NoteRepo repoNote = new NoteRepo(new NotaValidator());
        serviceNote = new ServiceNote(repoNote);
        repoTeme = new TemeRepo(new TemeValidator(),"src/main/resources/teme.xml");
        temeService = new ServiceTeme(repoTeme);
        StudentRepo repoStudent = new StudentRepo(new StudentValidator(), "src/main/resources/studenti.xml");
        studentService = new ServiceStudent(repoStudent);
    }

    @Test
    public void addStudent() {
        Student s1 = studentService.add(s);
        Assert.assertEquals(s1.getID(), "22");
    }

    @Test
    public void addAssignment(){
        Tema a1 = new Tema(15,"First Assignment",6,18);
        try {
            temeService.add(a1);
        } catch (ValidationException e) {
            Assert.assertEquals("\nDeadline invalid", e.getMessage());
        }
    }

    @Test
    public void addGrade() {
        try {
            Nota n1 = serviceNote.add(g,"Catalog.xml");
        } catch (ValidationException e) {
            Assert.assertEquals("Deadline gresit.\n", e.getMessage());
        }
    }

    @Test
    public void addAll() {
        addStudent();
        addAssignment();
        addGrade();
    }




}
