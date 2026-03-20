package BiblioServices;

import ServeurBTTP.serveur.Service;
import biblio.Bibliotheque;

import java.net.Socket;

public class RetourService extends Service {

    public RetourService(Socket client) {
        super(client);
    }

    @Override
    public void run() {
        String err = "";
        Bibliotheque bibliotheque = Bibliotheque.getInstance();
        while (true) {
            try {
                super.sendLine(err + "\n" + "Entrer [idDoc] que vous voulez rendre");
                err = "";
                String r = super.readLine();
                String[] arguments = r.split(" ");
                for (String argument : arguments) argument = argument.trim();
                if (arguments.length != 1) {
                    err = "Veuillez saisir les arguments comme demande";
                    continue;
                }
                if (bibliotheque.asDocumentId(arguments[0])) {
                    String rr;
                    super.sendLine("Le Document est il abimé repondez avec y/n");
                    while(true){
                        rr = super.readLine();
                        if(rr.equals("y") || rr.equals("n")){
                            break;
                        } sendLine("veillez repondre avec y/n");
                    }
                    err = bibliotheque.retourDocuments(arguments[0], rr.equals("y"));
                } else {
                    err = "Erreur de saisie l'id du document n'est pas reconnu";
                    continue;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
