package Validator;

import Domain.Tema;

public class TemeValidator implements Validator<Tema> {
    @Override
    public String validate(Tema t) {
        String m = new String();
        m = "";
        if (t.getID() == null || t.getID().equals("") || t.getID() < 1)
            m = m + "\nID invalid";
        if(t.getSaptPrimire() > 15 || t.getSaptPrimire() < 1)
            m = m + "\nWeek in which assignment was given is invalid";
        if (t.getDeadline() > 15 || t.getDeadline() < 1)
            m = m + "\nDeadline invalid";
        if ( t.getDeadline() < t.getSaptPrimire())
            m += "\nWeeks not correct";
        if(t.getDescriere().equals("") || t.getDescriere() == null)
            m = m + "\nDescription can't be null or empty string";
        return m;
    }
}
