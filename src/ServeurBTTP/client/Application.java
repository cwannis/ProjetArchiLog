package ServeurBTTP.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

class Application {
	private final static int PORT = 1234;
	private final static String HOST = "localhost"; 

	public static void main(String[] args) {
		Socket socket = null;		
		try {
			Client c = new Client(PORT, HOST);

			BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));

			while (true) {
				System.out.println(c.readline());
				System.out.print("> ");
				c.send(clavier.readLine());
				System.out.println("donne envoye en attente de la reponse du server ...");
				String reponse = c.readline();
				System.out.println("Server reponse : " + reponse);
			}
		}
		catch (IOException e) { System.err.println(e); }
		// Refermer dans tous les cas la socket
		try { if (socket != null) socket.close(); } 
		catch (IOException e2) { ; }		
	}
}
