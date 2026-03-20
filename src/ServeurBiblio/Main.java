package ServeurBiblio;

import BiblioServices.EmpruntService;
import BiblioServices.ReservationService;
import BiblioServices.RetourService;
import Properties.PropertiesReader;
import ServeurBTTP.serveur.Serveur;
import biblio.Abonne.Abonne;
import biblio.Bibliotheque;
import biblio.document.Dvd;
import biblio.document.Livre;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        PropertiesReader reader = PropertiesReader.getInstance();
        System.out.println(reader.getProperties().getProperty("server.timeout"));
        Bibliotheque bibliotheque = Bibliotheque.getInstance();
        bibliotheque.addAbonne(new Abonne("1", "test", 18));
        bibliotheque.addDocument(new Dvd("1", "testDvd", true));
        bibliotheque.addAbonne(new Abonne("2", "test2", 15));
        bibliotheque.addDocument(new Livre("2", "testLivre", 200));
        try {
            new Thread(new Serveur(2000, ReservationService.class)).start();
            new Thread(new Serveur(2001, EmpruntService.class)).start();
            new Thread(new Serveur(2002, RetourService.class)).start();
        } catch (IOException e) {
            System.out.println("Erreur lors du lancement du serveur -> " + e.getMessage());
        }
    }

}
