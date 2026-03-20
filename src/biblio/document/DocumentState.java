package biblio.document;

import biblio.Abonne.Abonne;
import biblio.document.exception.EmpruntException;
import biblio.document.exception.ReservationException;
import biblio.document.exception.RetourException;

public abstract class DocumentState {

    public DocumentState reservation(IDocument doc, Abonne ab) throws ReservationException {
        throw new ReservationException("ce document ne peut pas etre resevé");
    }

    public DocumentState emprunt(IDocument doc, Abonne ab) throws EmpruntException {
        throw new EmpruntException("ce document ne peut pas etre emprunté");
    }

    public DocumentState retour(IDocument doc, boolean estAbime) throws RetourException {
        throw new RetourException("ce document ne peut pas etre retourner");
    }

}
