package Domain;

import java.time.LocalDateTime;

public class Tema implements hasID<Integer>{
    private Integer nr;
    private String descriere;
    private Integer deadline;
    private Integer saptPrimire;

    public Tema(Integer nr, String descriere, Integer saptPrimire, Integer deadline){
        this.deadline=deadline;
        this.descriere=descriere;
        this.nr=nr;
        this.saptPrimire=saptPrimire;
    }

    public Integer getID() {
        return nr;
    }

    public void setID(Integer id) {
        this.nr = id;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String nume) {
        this.descriere = nume;
    }

    public Integer getDeadline(){
        return deadline;
    }

    public void setDeadline(Integer deadline){
        this.deadline=deadline;
    }

    public Integer getSaptPrimire(){
        return saptPrimire;
    }

    public void setSaptPrimire(Integer s){
        this.saptPrimire=s;
    }

    public String toString(){
        return nr.toString()+' '+descriere+' '+saptPrimire.toString()+' '+deadline.toString();
    }
}
