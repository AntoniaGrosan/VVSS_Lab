import Domain.Nota;
import Domain.Student;
import Domain.Tema;
import Repository.NoteRepo;
import Repository.StudentRepo;
import Service.ServiceNote;
import Service.ServiceStudent;
import Validator.NotaValidator;
import Validator.StudentValidator;
import Validator.ValidationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.AbstractMap;
import java.util.Map;

public class NotaTest {
    private Nota g;
    private ServiceNote serviceNote;

    @Before
    public void setUp() {
        Student s = new Student("22", "Ana", 933, "ana@ana.com", "Ioan");
        Tema a = new Tema(15,"First Assignment",2,5);
        Map.Entry<String, Integer> nid = new AbstractMap.SimpleEntry<String, Integer>("2",2);
        g = new Nota(nid,s,a,10,1);
        NoteRepo repoNote = new NoteRepo(new NotaValidator());
        serviceNote = new ServiceNote(repoNote);
    }

    @Test
    public void addGrade1() {
        Nota n1 = serviceNote.add(g,"Catalog.xml");
        Assert.assertEquals(n1,null);
    }

}
