package biblio;

import biblio.Abonne.Abonne;
import biblio.document.Document;
import biblio.document.IDocument;
import biblio.document.exception.EmpruntException;
import biblio.document.exception.ReservationException;
import biblio.document.exception.RetourException;

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
                return "document emprunter avec sucee";
            }catch(EmpruntException e) {
                return e.getMessage();
            }
        }
    }

    public String retourDocuments(String idDocument) {
        IDocument d = documents.get(idDocument);
        synchronized (d) {
            try {
                documents.get(idDocument).retour();
                return "document retourner avec sucee";
            } catch (RetourException e) {
                return e.getMessage();
            }
        }
    }

    public synchronized String reservationDocuments(String idDocument, String idAbonne) {
        IDocument d = documents.get(idDocument);
        synchronized (d) {
            try {
                d.reservation(abonnes.get(idAbonne));
                return "document reserver avec sucee";
            } catch (ReservationException e) {
                return e.getMessage();
            }
        }
    }

}