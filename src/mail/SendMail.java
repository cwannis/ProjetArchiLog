package mail;

import biblio.Abonne.Abonne;
import biblio.document.IDocument;

import java.util.ArrayList;

public class SendMail {

    public static void sendMessage(String subject, String text, String destinataire) {
        new Thread(new SendMailThread(subject, text, destinataire)).start();
    }

    public static void notifierAbbones(ArrayList<Abonne> abonnes, IDocument doc) {
        for (Abonne ab : abonnes) {
            sendMessage("Document libre", "le document " + doc.getTitre() + " que vous vouliez reserver est maintenan disponible", "jean-francois.brette@u-paris.fr");
        }
    }

    public static void main(String[] args) {
        sendMessage("test", "test", "test@test.com");
    }

}


