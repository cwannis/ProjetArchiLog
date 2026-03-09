package biblio;

import biblio.Abonne.Abonne;
import biblio.document.Document;

import java.util.ArrayList;

public class Bibliotheque {

    private ArrayList<Document> documents;
    private ArrayList<Abonne> abonnes;

    public Bibliotheque() {
        ArrayList<Document> doc = new ArrayList<>();
        ArrayList<Abonne> ab = new ArrayList<>();
    }
    public Bibliotheque(ArrayList<Document> documents, ArrayList<Abonne> ab) {
        this();
        this.documents = documents;
        this.abonnes = ab;
    }

    public void addDocument(Document document) {
        documents.add(document);
    }

    public void addAbonne(Abonne ab) {
        abonnes.add(ab);
    }
}