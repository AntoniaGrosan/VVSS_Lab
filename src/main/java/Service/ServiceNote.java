package Service;


import Domain.Nota;
import Repository.NoteRepo;

import java.util.Map;

public class ServiceNote {
    private NoteRepo rep;
    public ServiceNote(NoteRepo rep){this.rep=rep;}
    /**
     * Adauga Nota
     * Returneaza Nota adaugata*/
    public Nota add(Nota s,String fd){
        return rep.save(s,fd);
    }

    public Nota find(Map.Entry<String, Integer> id){
        return rep.findOne(id);
    }

    public Iterable<Nota> all(){
        return rep.findAll();
    }
}

