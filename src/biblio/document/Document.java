package biblio.document;

import biblio.Abonne.Abonne;
import biblio.document.DocumentsState.DocumentLibre;
import biblio.document.exception.EmpruntException;
import biblio.document.exception.ReservationException;
import biblio.document.exception.RetourException;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Document implements IDocument{

    private DocumentState state;
    public static final int timeReservation = 15000;

    public Document(){
        state = new DocumentLibre();
    }

    private Timer timer;
    @Override
    public void reservation(Abonne ab) throws ReservationException {
        state = state.reservation(ab);
        TimerTask task = new TimerTask() {
            public void run()
            {
                try {
                    System.out.println("reservation fini");
                    retour();
                    timer = null;
                } catch (RetourException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        timer = new Timer("timer reservation");
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
