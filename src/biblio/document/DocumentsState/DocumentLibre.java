package biblio.document.DocumentsState;

import biblio.Abonne.Abonne;
import biblio.document.DocumentState;
import biblio.document.exception.EmpruntException;
import biblio.document.exception.ReservationException;
import biblio.document.exception.RetourException;

public class DocumentLibre extends DocumentState{

    public DocumentState reservation(Abonne ab) throws ReservationException {
        return new DocumentReserver(ab);
    }
    public DocumentState emprunt(Abonne ab) throws EmpruntException {
        return new DocumentEmprunter();
    }
    public DocumentState retour() throws RetourException {
        throw new RetourException("ce document ne peut pas etre retourner car il est deja en stock");
    }

}
