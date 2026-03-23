package biblio;

import biblio.Abonne.Abonne;
import biblio.document.Document;
import biblio.document.IDocument;
import biblio.document.exception.EmpruntException;
import biblio.document.exception.ReservationException;
import biblio.document.exception.RetourException;
import mail.SendMail;

import java.util.ArrayList;
import java.util.HashMap;

public class Bibliotheque {

    private static Bibliotheque instance = new Bibliotheque();

    public static Bibliotheque getInstance() {
        return instance;
    }

    private HashMap<String, IDocument> documents;
    private HashMap<String, Abonne> abonnes;


    public Bibliotheque() {
        documents = new HashMap<>();
        abonnes = new HashMap<>();
    }

    public boolean asDocumentId(String id)
    {
        return documents.containsKey(id);
    }

    public boolean asAbonne(String id)
    {
        return abonnes.containsKey(id);
    }

    public Bibliotheque(ArrayList<IDocument> docs, ArrayList<Abonne> ab) {
        this();
        for(IDocument d : docs) addDocument(d);
        for(Abonne a : ab) addAbonne(a);
    }

    public void addDocument(IDocument document) {
        documents.put(document.idDoc(), document);
    }

    public void addAbonne(Abonne ab) {
        abonnes.put(ab.getId(), ab);
    }

    public String empreinterDocuments(String idDocument, String idAbonne) {
        IDocument d = documents.get(idDocument);
        synchronized (d){
            try {
                d.emprunt(abonnes.get(idAbonne));
                return "document emprunté avec succès";
            }catch(EmpruntException e) {
                return e.getMessage();
            }
        }
    }

    public String retourDocuments(String idDocument, boolean estAbime) {
        IDocument d = documents.get(idDocument);
        synchronized (d) {
            try {
                documents.get(idDocument).retour(estAbime);
                return "document retourné avec succès";
            } catch (RetourException e) {
                return e.getMessage();
            }
        }
    }

    public String reservationDocuments(String idDocument, String idAbonne) {
        IDocument d = documents.get(idDocument);
        synchronized (d) {
            try {
                d.reservation(abonnes.get(idAbonne));
                return "document reservé avec succès";
            } catch (ReservationException e) {
                return e.getMessage();
            }
        }
    }

    public void addAlert(String idAbonne, String idDocument) {
        IDocument d = documents.get(idDocument);
        d.addAlert(abonnes.get(idAbonne));
    }
}