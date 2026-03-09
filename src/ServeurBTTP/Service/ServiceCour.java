package ServeurBTTP.Service;

import ServeurBTTP.serveur.Service;

import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

public class ServiceCour extends Service {

    private static Vector<Cour> cours;

    static {
        cours = new Vector<>();
        cours.add(new Cour(20));
        cours.add(new Cour(30));
        cours.add(new Cour(40));
        cours.add(new Cour(10));
    }
    private Socket client;

    public ServiceCour(Socket client) {
        super(client);
        this.client = super.getSocketclient();
    }

    private Cour getCour(int id) {
        for (Cour c : cours) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void run() {
        while (true) {
            try {
                StringBuilder sb = new StringBuilder();
                for (Cour c : cours) {
                    sb.append("cour n " + c.getId() + " => " + c.getNbPlaceRestante() + " places restante \n");
                }
                String s = sb.toString();
                super.sendLine(s);
                String data = super.readLine();
                String[] datas = data.split(" ");
                int nbCour = Integer.parseInt(datas[0]);
                int nbPlace = Integer.parseInt(datas[1]);

                Cour c = getCour(nbCour);
                if (c != null) {
                    if (c.addReserve(nbPlace)) {
                        super.sendLine("RESERVED there are " + nbPlace + " places in cour " + nbCour);
                    } else {
                        super.sendLine("no  there are " + nbPlace + " places in cour " + nbCour);
                    }
                } else {
                    super.sendLine("No such cour found");
                }


            } catch (IOException e) {
                try {
                    client.close();
                    return;
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

    }
}
