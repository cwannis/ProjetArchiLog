package BiblioServices;

import ServeurBTTP.serveur.Service;
import biblio.Bibliotheque;

import java.net.Socket;

public class EmpruntService extends Service {

    public EmpruntService(Socket client) {
        super(client);
    }

    @Override
    public void run() {
        Bibliotheque bibliotheque = Bibliotheque.getInstance();
        String err = "";
        while (true) {
            try {
                super.send(err + "\n" + "Entrez [idDoc-idAbonne] que vous voulez emprunter");
                err = "";
                String r = super.read();
                String[] arguments = r.split("-");
                for(String argument : arguments) argument = argument.trim();
                if(arguments.length != 2){
                    err = "Veuillez saisir les arguments comme demandé";
                    continue;
                }
                if(bibliotheque.asDocumentId(arguments[0]) && bibliotheque.asAbonne(arguments[1])) {
                    super.send("Si le document n'est pas disponible voulez vous une alerte l'orsque que ce dernier ce libére ? repondez avec Y/n");
                    String rep = "";
                    while(true)
                    {
                        rep = super.read();
                        if(rep.equals("y") || rep.equals("n")) break;
                        super.send("veillez repondre avec y pour yes ou n pour no");
                    }
                    if(rep.equals("y")) bibliotheque.addAlert(arguments[1], arguments[0]);
                    err = bibliotheque.empreinterDocuments(arguments[0], arguments[1]);
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
