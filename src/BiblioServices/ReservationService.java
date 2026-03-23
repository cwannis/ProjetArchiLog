package BiblioServices;

import ServeurBTTP.serveur.Service;
import biblio.Bibliotheque;

import java.net.Socket;

public class ReservationService extends Service {

    public ReservationService(Socket client) {
        super(client);
    }

    @Override
    public void run() {
        Bibliotheque bibliotheque = Bibliotheque.getInstance();
        String err = "";
        while (true) {
            try {
                super.send(err + "\n" + "Entrer [idDoc-idAbonne] que vous voulez reserver");
                err = "";
                String r = super.read();
                String[] arguments = r.split("-");
                for(String argument : arguments) argument = argument.trim();
                if(arguments.length != 2){
                    err = "Veuillez saisir les arguments comme demande";
                    continue;
                }
                if(bibliotheque.asDocumentId(arguments[0]) && bibliotheque.asAbonne(arguments[1])) {
                    String rep = "";
                    super.send("Si le document n'est pas disponible voulez vous une alerte l'orsque que ce dernier ce libére ? repondez avec Y/n");
                    while(true)
                    {
                        rep = super.read();
                        if(rep.equals("y") || rep.equals("n")) break;
                        super.send("veillez repondre avec y pour yes ou n pour no");
                    }
                    if(rep.equals("y")) bibliotheque.addAlert(arguments[1], arguments[0]);
                    err = bibliotheque.reservationDocuments(arguments[0], arguments[1]);
                } else
                {
                    err = "Erreur de saisie l'id du document ou du user n'est pas reconnu";
                    continue;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
