package ServeurBiblio;

import BiblioServices.EmpruntService;
import BiblioServices.ReservationService;
import ServeurBTTP.serveur.Serveur;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            Serveur servReserv = new Serveur(2000, ReservationService.class);
            Serveur servEmprunt = new Serveur(2001, EmpruntService.class);
            Serveur servRetour = new Serveur(2002, ReservationService.class);
        } catch (IOException e) {
            System.out.println("Erreur lors du lancement du serveur -> " + e.getMessage());
        }
    }

}
