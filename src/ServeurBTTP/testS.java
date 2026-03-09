package ServeurBTTP;

import java.io.IOException;
import ServeurBTTP.Service.ServiceCour;
import ServeurBTTP.serveur.Serveur;

class testS {
	private final static int PORT = 1234;

	public static void main(String[] args) {

		try {
			new Thread(new Serveur(PORT, ServiceCour.class)).start();
			System.out.println("Serveur demarre sur le port " + PORT);
		} 
		catch (IOException e) {
			System.err.println("Pb lors de la cr�ation du ServeurBTTP.serveur : " +  e);
		}
	}
}
