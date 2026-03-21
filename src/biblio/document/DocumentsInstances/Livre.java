package biblio.document.DocumentsInstances;


import biblio.Abonne.Abonne;
import biblio.document.Document;
import biblio.document.exception.EmpruntException;
import biblio.document.exception.ReservationException;
import biblio.document.exception.RetourException;

public class Livre extends Document {

    private String id;
    private String titre;
    private int nbPages;

    public Livre(String id, String titre, int nbPages) {
        super(titre);
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
        super.reservation(ab);
    }

    @Override
    public void emprunt(Abonne ab) throws EmpruntException {
        super.emprunt(ab);
    }

    @Override
    public void retour(boolean estAbime) throws RetourException {
        super.retour(estAbime);
    }
}
