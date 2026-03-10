package biblio.document.DocumentsState;

import biblio.Abonne.Abonne;
import biblio.document.DocumentState;
import biblio.document.exception.EmpruntException;
import biblio.document.exception.ReservationException;

public class DocumentLibre extends DocumentState{

    public DocumentState reservation(Abonne ab) throws ReservationException {
        return new DocumentReserver(ab);
    }
    public DocumentState emprunt(Abonne ab) throws EmpruntException {
        return new DocumentEmprunter();
    }

}
