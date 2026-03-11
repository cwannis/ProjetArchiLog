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
        System.out.println(timeReservationLong);
        long timeStay = timeReservationLong - System.currentTimeMillis();
        System.out.println(timeStay);
        if(timer != null && timeStay < 60 * 1000 && timeStay > 0){
            new LecteurMusic(timeStay).lancerMusicIndien();
            throw new ReservationException("ce document est deja reserver par un autre memebre mais il reste moin de " + (timeStay / 1000) + "s donc patientez avec cette musique");
        }
        state = state.reservation(ab);
        TimerTask task = new TimerTask() {
            public void run()
            {
                try {
                    System.out.println("reservation fini");
                    retour();
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
        state = state.emprunt(ab);
        if(timer != null)
        {
            timer.cancel();
        }
    }

    @Override
    public void retour() throws RetourException {
        state = state.retour();
    }
}
