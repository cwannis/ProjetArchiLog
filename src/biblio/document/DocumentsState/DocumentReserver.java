package biblio.document.DocumentsState;

import biblio.Abonne.Abonne;
import biblio.document.DocumentState;
import biblio.document.exception.EmpruntException;
import biblio.document.exception.ReservationException;
import biblio.document.exception.RetourException;
import music.LecteurMusic;

import java.util.TimerTask;

public class DocumentReserver extends DocumentState {

    private Abonne abboReserve;

    public DocumentReserver(Abonne ab) {

        abboReserve = ab;
    }

    @Override
    public DocumentState retour(boolean estAbime) throws RetourException {
        return new DocumentLibre();
    }

    @Override
    public DocumentState emprunt(Abonne ab) throws EmpruntException {
        if(abboReserve.getId().equals(ab.getId())) return new DocumentEmprunter(ab);
        throw new EmpruntException("ce document est deja reserver par un autre abbonne");
    }

    public DocumentState reservation(Abonne ab) throws ReservationException {
        if(abboReserve.getId().equals(ab.getId())) throw new ReservationException("Vous ne pouvez pas reservez ce document car vous l'avez deja reservé");
        throw new ReservationException("ce document ne peut pas etre reserver car un autre abonne la deja reservé");
    }


}
