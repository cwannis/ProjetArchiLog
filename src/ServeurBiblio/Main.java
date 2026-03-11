package ServeurBiblio;

import BiblioServices.EmpruntService;
import BiblioServices.ReservationService;
import BiblioServices.RetourService;
import ServeurBTTP.serveur.Serveur;
import biblio.Abonne.Abonne;
import biblio.Bibliotheque;
import biblio.document.Dvd;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Bibliotheque bibliotheque = Bibliotheque.getInstance();
        bibliotheque.addAbonne(new Abonne("1", "test", 18));
        bibliotheque.addDocument(new Dvd("1", "testDvd", true));
        try {
            new Thread(new Serveur(2000, ReservationService.class)).start();
            new Thread(new Serveur(2001, EmpruntService.class)).start();
            new Thread(new Serveur(2002, RetourService.class)).start();
        } catch (IOException e) {
            System.out.println("Erreur lors du lancement du serveur -> " + e.getMessage());
        }
    }

}
