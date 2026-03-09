package ServeurBTTP.serveur;

import java.io.*;
import java.net.*;

public class Serveur implements Runnable {

	private ServerSocket listen_socket;
	private Class<? extends Service> service_class;

	public Serveur(int port, Class<? extends Service> service) throws IOException {
        try {
            Class.forName("ServeurBTTP.serveur.ServerConfig");
        } catch (ClassNotFoundException e) {
			System.out.println("erreur avec le fichier de configuration");
            throw new RuntimeException(e);
        }
        listen_socket = new ServerSocket(port);
		service_class = service;
	}

	public void run() {
		try {
			while(true) {
				System.out.println("J'attends un ServeurBTTP.client...");
				Socket client_socket = listen_socket.accept(); // Appel bloquant !
				System.out.println("Ca y est ! J'ai un ServeurBTTP.client.");
				
				System.out.print("Adresse IP locale : "+client_socket.getLocalAddress());
				System.out.println(" Port local : "+client_socket.getLocalPort());
				System.out.print("Adresse IP distante (ServeurBTTP.client) : "+client_socket.getInetAddress());
				System.out.println(" Port distant : "+client_socket.getPort());
				new Thread(service_class.getConstructor(Socket.class).newInstance(client_socket)).start();
			}
		}
		catch (IOException e) { 
			try {this.listen_socket.close();} catch (IOException e1) {}
			System.err.println("Pb sur le port d'�coute :"+e);
		} catch (Exception e)
		{
			System.err.println("erreur l'ors de l'initiation du service :" + e);
		}
	}

	protected void finalize() throws Throwable {
		try {this.listen_socket.close();} catch (IOException e1) {}
	}
}
