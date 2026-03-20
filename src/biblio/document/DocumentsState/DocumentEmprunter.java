package biblio.document.DocumentsState;

import Properties.PropertiesReader;
import biblio.Abonne.Abonne;
import biblio.Bibliotheque;
import biblio.document.DocumentState;
import biblio.document.exception.EmpruntException;
import biblio.document.exception.ReservationException;
import biblio.document.exception.RetourException;
import mail.SendMail;

import java.util.ArrayList;
import java.util.Properties;

public class DocumentEmprunter extends DocumentState {

    private Abonne abonneEmprunter;
    private ArrayList<Abonne> abonnesWantMail;

    public DocumentEmprunter(Abonne abonne) {
        abonnesWantMail = null;
        abonneEmprunter = abonne;
    }
    public DocumentEmprunter(Abonne abonne, ArrayList<Abonne> wantMail) throws RetourException {
        this(abonne);
        abonnesWantMail = wantMail;
    }

    public DocumentState retour(boolean estAbime) throws RetourException {
        if(estAbime)
        {
            PropertiesReader reader = PropertiesReader.getInstance();
            Properties props = reader.getProperties();
            abonneEmprunter.ban(Integer.parseInt(props.getProperty("client.timeBanForEndomaged")));
        }
        if(abonnesWantMail != null)
        {
            for(Abonne a : abonnesWantMail) {
                SendMail.sendMessage("Document libre", "le document que vous vouliez reserver est maintenan disponible", "jean-francois.brette@u-paris.fr");
            }
        }
        return new DocumentLibre();
    }

    @Override
    public DocumentState emprunt(Abonne ab) throws EmpruntException {
        if(abonneEmprunter.getId().equals(ab.getId())) throw new EmpruntException("Vous possedez deja ce document");
        throw new EmpruntException("ce document est deja emprunté par un autre abonné");
    }

    @Override
    public DocumentState reservation(Abonne ab) throws ReservationException
    {
        if(abonnesWantMail == null)
        {
            abonnesWantMail = new ArrayList<>();
        }
        abonnesWantMail.add(ab);
        throw new ReservationException("ce document ne peut pas etre reservé il est deja emprunter par un autre abonne");
    }

}
