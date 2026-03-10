package biblio.document.DocumentsState;

import biblio.Abonne.Abonne;
import biblio.document.DocumentState;
import biblio.document.exception.EmpruntException;
import biblio.document.exception.RetourException;

public class DocumentEmprunter extends DocumentState {

    public DocumentState retour() throws RetourException {
        return new DocumentLibre();
    }

    @Override
    public DocumentState emprunt(Abonne ab) throws EmpruntException {
        throw new EmpruntException("ce document est deja emprunter par un autre abonne");
    }

}
