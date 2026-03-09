package biblio;

import biblio.Abonne.Abonne;
import biblio.document.Document;

import java.util.ArrayList;
import java.util.HashMap;

public class Bibliotheque {

    private HashMap<String, Document> documents;
    private HashMap<String, Abonne> abonnes;

    public Bibliotheque() {
        HashMap<String, Document> doc = new HashMap<>();
        HashMap<String, Abonne> ab = new HashMap<>();
    }

    public Bibliotheque(ArrayList<Document> docs, ArrayList<Abonne> ab) {
        this();
        for(Document d : docs) addDocument(d);
        for(Abonne a : ab) addAbonne(a);
    }

    public void addDocument(Document document) {
        documents.put(document.idDoc(), document);
    }

    public void addAbonne(Abonne ab) {
        abonnes.put(ab.getId(), ab);
    }

    public synchronized String empreinterDocuments(String idDocument, String idAbonne) {
        try {
            documents.get(idDocument).emprunt(abonnes.get(idAbonne));
            return "document emprunter avec sucee";
        }catch(Exception e) {
            return e.getMessage();
        }
    }

}