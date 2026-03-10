package biblio.document;

import biblio.Abonne.Abonne;
import biblio.document.DocumentsState.DocumentLibre;
import biblio.document.exception.EmpruntException;
import biblio.document.exception.ReservationException;
import biblio.document.exception.RetourException;

public abstract class Document implements IDocument{

    private DocumentState state;

    public Document(){
        state = new DocumentLibre();
    }

    @Override
    public void reservation(Abonne ab) throws ReservationException {
        state = state.reservation(ab);
    }

    @Override
    public void emprunt(Abonne ab) throws EmpruntException {
        state = state.emprunt(ab);
    }

    @Override
    public void retour() throws RetourException {
        state = state.retour();
    }
}
