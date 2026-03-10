package BiblioServices;

import ServeurBTTP.serveur.Service;

import java.net.Socket;

public class EmpruntService extends Service {

    public EmpruntService(Socket client) {
        super(client);
    }

    @Override
    public void run() {

    }
}
