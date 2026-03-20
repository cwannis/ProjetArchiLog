package biblio.document.DocumentsState;

import biblio.Abonne.Abonne;
import biblio.document.DocumentState;
import biblio.document.IDocument;
import biblio.document.exception.EmpruntException;
import biblio.document.exception.ReservationException;
import biblio.document.exception.RetourException;
import mail.SendMail;

import java.util.ArrayList;

public class DocumentReserver extends DocumentState {

    private Abonne abboReserve;
    private ArrayList<Abonne> abonnesWantMail;

    public DocumentReserver(Abonne ab) {
        abboReserve = ab;
        abonnesWantMail = null;
    }

    @Override
    public DocumentState retour(IDocument doc, boolean estAbime) throws RetourException {
        if(abonnesWantMail != null)
        {
            SendMail.notifierAbbones(abonnesWantMail, doc);
        }
        return new DocumentLibre();
    }

    @Override
    public DocumentState emprunt(IDocument doc, Abonne ab) throws EmpruntException {
        if(abboReserve.getId().equals(ab.getId()))
        {
            if(abonnesWantMail != null)
            {
                return new DocumentEmprunter(ab, abonnesWantMail);
            }
            return new DocumentEmprunter(ab);
        }
        throw new EmpruntException("ce document est deja reserver par un autre abbonne");
    }

    public DocumentState reservation(IDocument doc, Abonne ab) throws ReservationException {
        if(abboReserve.getId().equals(ab.getId())) throw new ReservationException("Vous ne pouvez pas reservez ce document car vous l'avez deja reservé");
        if(abonnesWantMail == null) abonnesWantMail = new ArrayList<>();
        abonnesWantMail.add(ab);
        throw new ReservationException("ce document ne peut pas etre reserver car un autre abonne la deja reservé");
    }


}
