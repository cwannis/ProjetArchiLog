package biblio.document.DocumentsState;

import biblio.Abonne.Abonne;
import biblio.document.DocumentState;
import biblio.document.exception.EmpruntException;
import biblio.document.exception.RetourException;

public class DocumentEmprunter extends DocumentState {

    private Abonne abonneEmprunter;

    public DocumentEmprunter(Abonne abonne) {
        abonneEmprunter = abonne;
    }

    public DocumentState retour() throws RetourException {
        return new DocumentLibre();
    }

    @Override
    public DocumentState emprunt(Abonne ab) throws EmpruntException {
        if(abonneEmprunter.getId().equals(ab.getId())) throw new EmpruntException("Vous possede deja ce document");
        throw new EmpruntException("ce document est deja emprunter par un autre abonne");
    }

}
