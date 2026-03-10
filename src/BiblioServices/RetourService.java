package BiblioServices;

import ServeurBTTP.serveur.Service;

import java.net.Socket;

public class RetourService extends Service {

    public RetourService(Socket client) {
        super(client);
    }

    @Override
    public void run() {

    }
}
