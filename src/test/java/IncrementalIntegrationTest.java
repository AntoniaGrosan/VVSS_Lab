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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.AbstractMap;
import java.util.Map;

public class IncrementalIntegrationTest {
    private ServiceNote serviceNote;
    private ServiceStudent serviceStudent;
    private ServiceTeme serviceTeme;

    private NoteRepo noteRepo;
    private StudentRepo studentRepo;
    private TemeRepo temeRepo;

    private Nota nota;
    private Student student;
    private Tema tema;

    @Before
    public void setUp() {
        NoteRepo noteRepo =  new NoteRepo(new NotaValidator());
        StudentRepo studentRepo = new StudentRepo(new StudentValidator(), "src/main/resources/studenti.xml");
        TemeRepo temeRepo = new TemeRepo(new TemeValidator(),"src/main/resources/teme.xml");

        serviceNote = new ServiceNote(noteRepo);
        serviceStudent = new ServiceStudent(studentRepo);
        serviceTeme = new ServiceTeme(temeRepo);

        student = new Student("22", "Ana", 933, "ana@ana.com", "Ioan");
        tema = new Tema(15,"First Assignment",6,8);
        Map.Entry<String, Integer> nid = new AbstractMap.SimpleEntry<String, Integer>("1",1);
        nota = new Nota(nid, student, tema,10,3);
    }

    @Test
    public void addStudent() {
        Student s1 = serviceStudent.add(student);
        Assert.assertEquals(serviceStudent.find(s1.getID()).getMail(), student.getMail());
    }

    @Test
    public void addStudentAndAssignment(){
        Tema t1 = serviceTeme.add(tema);
        Student s1 = serviceStudent.add(student);
        Assert.assertEquals(serviceStudent.find(s1.getID()).getMail(), student.getMail());
        Assert.assertEquals(serviceTeme.find(t1.getID()).getDescriere(), tema.getDescriere());
    }

    @Test
    public void addStudentAssignmentAndGrade() {
        Tema t1 = serviceTeme.add(tema);
        Student s1 = serviceStudent.add(student);
        Assert.assertEquals(serviceStudent.find(s1.getID()).getMail(), student.getMail());
        Assert.assertEquals(serviceTeme.find(t1.getID()).getDescriere(), tema.getDescriere());
        Nota n1 = serviceNote.add(nota,"Catalog.xml");
        Assert.assertEquals(serviceNote.find(n1.getID()).getDl(), nota.getDl());
    }
}
