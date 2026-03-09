package biblio.document;


import biblio.Abonne.Abonne;
import biblio.document.exception.EmpruntException;
import biblio.document.exception.ReservationException;
import biblio.document.exception.RetourException;

public class Livre implements Document {

    private String id;
    private String titre;
    private int nbPages;

    public Livre(String id, String titre, int nbPages) {
        this.id = id;
        this.titre = titre;
        this.nbPages = nbPages;
    }

    @Override
    public String idDoc() {
        return id;
    }

    @Override
    public void reservation(Abonne ab) throws ReservationException {

    }

    @Override
    public void emprunt(Abonne ab) throws EmpruntException {

    }

    @Override
    public void retour() throws RetourException {

    }
}
