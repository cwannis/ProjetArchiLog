package biblio.document.DocumentsState;

import biblio.Abonne.Abonne;
import biblio.document.DocumentState;
import biblio.document.IDocument;
import biblio.document.exception.EmpruntException;
import biblio.document.exception.ReservationException;
import biblio.document.exception.RetourException;

public class DocumentLibre extends DocumentState{

    @Override
    public DocumentState reservation(IDocument doc, Abonne ab) throws ReservationException {
        return new DocumentReserver(ab);
    }

    @Override
    public DocumentState emprunt(IDocument doc, Abonne ab) throws EmpruntException {
        return new DocumentEmprunter(ab);
    }

    @Override
    public DocumentState retour(IDocument doc, boolean estAbime) throws RetourException {
        throw new RetourException("ce document ne peut pas etre retourner car il est deja en stock");
    }

}
