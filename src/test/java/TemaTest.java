import Domain.Tema;
import Repository.TemeRepo;
import Service.ServiceTeme;
import Validator.TemeValidator;
import Validator.ValidationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TemaTest {
    private TemeRepo repoTeme;
    private ServiceTeme temeService;

    @Before
    public void setUp(){
        repoTeme = new TemeRepo(new TemeValidator(),"src/main/resources/teme.xml");
        temeService = new ServiceTeme(repoTeme);
    }

    @Test
    public void addAssignment1(){
        Tema a1 = new Tema(15,"First Assignment",6,18);
        try {
            temeService.add(a1);
        } catch (ValidationException e) {
            Assert.assertEquals("\nDeadline invalid", e.getMessage());
        }
    }

    @Test
    public void addAssignment2() {
        Tema a2 = new Tema(16,"Second Assignment",0,5);
        try {
            temeService.add(a2);
        } catch (ValidationException e) {
            Assert.assertEquals("\nWeek in which assignment was given is invalid", e.getMessage());
        }
    }

    @Test
    public void addAssignment3(){
        Tema a3 = new Tema(null, "3rd assign", 1,2);
        try {
            temeService.add(a3);
        } catch (ValidationException e) {
            Assert.assertEquals("\nID invalid", e.getMessage());
        }
    }

    @Test
    public void addAssignment4(){
        Tema a4 = new Tema(20, "4th assign", 10,2);
        try {
            temeService.add(a4);
        } catch (ValidationException e) {
            Assert.assertEquals("\nWeeks not correct", e.getMessage());
        }
    }

    @Test
    public void addAssignment5(){
        Tema a5 = new Tema(19, "", 1,2);
        try {
            temeService.add(a5);
        } catch (ValidationException e) {
            Assert.assertEquals("\nDescription can't be null or empty string", e.getMessage());
        }
    }

    @Test
    public void addAssignment6(){
        Tema a6 = new Tema(20, "6th assign", 1,2);
        try {
            temeService.add(a6);
        } catch (ValidationException e) {
            Assert.assertEquals("\nDescription can't be null or empty string", e.getMessage());
        }
        Tema found = temeService.find(20);
        Assert.assertEquals(a6.getID(), found.getID());
        Assert.assertEquals(a6.getDeadline(), found.getDeadline());
        Assert.assertEquals(a6.getSaptPrimire(), found.getSaptPrimire());
        Assert.assertEquals(a6.getDescriere(), found.getDescriere());
    }
}
