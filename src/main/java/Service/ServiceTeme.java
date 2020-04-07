package Service;

import Domain.Tema;
import Repository.TemeRepo;

public class ServiceTeme {
    private TemeRepo rep;
    public ServiceTeme(TemeRepo rep){this.rep=rep;}

    /***
     * Adauga tema
     * @param s
     * @return tema adaugata
     */
    public Tema add(Tema s){
        return rep.save(s);
    }

    /***
     * sterge tema
     * @param id
     * @return tema stearsa
     */
    public Tema del(Integer id){
        return rep.delete(id);
    }

    /***
     * Modifica tema
     * @param s
     * @return tema modificata
     */
    public Tema mod(Tema s){
        return rep.update(s);
    }

    /***
     * Cauta tema dupa id
     * @param id
     * @return tema gasita
     */
    public Tema find(Integer id){
        return rep.findOne(id);
    }

    /***
     * @return temele
     */
    public Iterable<Tema> all(){
        return rep.findAll();
    }
}

