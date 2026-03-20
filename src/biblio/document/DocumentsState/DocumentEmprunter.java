package biblio.document.DocumentsState;

import Properties.PropertiesReader;
import biblio.Abonne.Abonne;
import biblio.document.DocumentState;
import biblio.document.exception.EmpruntException;
import biblio.document.exception.RetourException;

import java.util.Properties;

public class DocumentEmprunter extends DocumentState {

    private Abonne abonneEmprunter;

    public DocumentEmprunter(Abonne abonne) {
        abonneEmprunter = abonne;
    }

    public DocumentState retour(boolean estAbime) throws RetourException {
        if(estAbime)
        {
            PropertiesReader reader = PropertiesReader.getInstance();
            Properties props = reader.getProperties();
            abonneEmprunter.ban(Integer.parseInt(props.getProperty("client.timeBanForEndomaged")));
        }
        return new DocumentLibre();
    }

    @Override
    public DocumentState emprunt(Abonne ab) throws EmpruntException {
        if(abonneEmprunter.getId().equals(ab.getId())) throw new EmpruntException("Vous possedez deja ce document");
        throw new EmpruntException("ce document est deja emprunté par un autre abonné");
    }

}
