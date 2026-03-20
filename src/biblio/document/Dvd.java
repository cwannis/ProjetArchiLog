package biblio.document;

import biblio.Abonne.Abonne;
import biblio.document.exception.EmpruntException;
import biblio.document.exception.ReservationException;
import biblio.document.exception.RetourException;

public class Dvd extends Document{

    private String id;
    private String titre;
    private boolean p16;

    public Dvd(String id, String titre, boolean p16) {
        super(titre);
        this.id = id;
        this.titre = titre;
        this.p16 = p16;
    }

    @Override
    public String idDoc() {
        return id;
    }

    @Override
    public void reservation(Abonne ab) throws ReservationException {
        if(!ageIsGood(ab)) throw new ReservationException("Pour reserver ce biblio.document il faut au moin 16 ans");
        super.reservation(ab);
    }

    @Override
    public void retour(boolean estAbime) throws RetourException {
        super.retour(estAbime);
    }

    @Override
    public void emprunt(Abonne ab) throws EmpruntException {
        if(!ageIsGood(ab)) throw new EmpruntException("Pour reserver ce biblio.document il faut au moin 16 ans");
        super.emprunt(ab);
    }

    private boolean ageIsGood(Abonne ab)
    {
        return !p16 || ab.getAge() >= 16;
    }
}
