package biblio.document;

import biblio.Abonne.Abonne;
import biblio.document.DocumentsState.DocumentLibre;
import biblio.document.exception.EmpruntException;
import biblio.document.exception.ReservationException;
import biblio.document.exception.RetourException;
import music.LecteurMusic;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Document implements IDocument{

    private DocumentState state;
    public static final int timeReservation = 15000;
    private long timeReservationLong;

    public Document(){
        state = new DocumentLibre();
    }

    private Timer timer;
    @Override
    public void reservation(Abonne ab) throws ReservationException {
        if(ab.isBanned()) throw new ReservationException("impossible de reserver le document car vous êtes banni");
        long timeStay = timeReservationLong - System.currentTimeMillis();
        if(timer != null && timeStay < 60 * 1000 && timeStay > 0){
            new LecteurMusic(timeStay).lancerMusicIndien();
            throw new ReservationException("ce document est deja reservé par un autre membre mais il reste moins de " + (timeStay / 1000) + "s donc patientez avec cette musique");
        }
        state = state.reservation(ab);
        TimerTask task = new TimerTask() {
            public void run()
            {
                try {
                    System.out.println("reservation finie");
                    retour(false);
                    timer = null;
                    timeReservationLong = 0;
                } catch (RetourException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        timer = new Timer("timer reservation");
        timeReservationLong = System.currentTimeMillis() + timeReservation;
        timer.schedule(task, timeReservation);
    }

    @Override
    public void emprunt(Abonne ab) throws EmpruntException {
        if(ab.isBanned()) throw new EmpruntException("l'emprunt du document est impossible car l'abonne est banni");
        state = state.emprunt(ab);
        if(timer != null)
        {
            timer.cancel();
        }
    }

    @Override
    public void retour(boolean estAbime) throws RetourException {
        state = state.retour(estAbime);
    }
}
