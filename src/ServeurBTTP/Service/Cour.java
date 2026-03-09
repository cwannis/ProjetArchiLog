package ServeurBTTP.Service;

public class Cour {

    private static int nbCour = 0;
    private int id;
    private int nbPlaceTotale;
    private int nbPlaceReserve;

    public Cour(int nbPlaceTotale) {
        this.nbPlaceTotale = nbPlaceTotale;
        id = nbCour++;
    }

    public synchronized boolean addReserve(int nbPlaceaReserve) {
        if(nbPlaceReserve + nbPlaceaReserve <= this.nbPlaceTotale)
        {
            nbPlaceTotale = nbPlaceReserve + nbPlaceaReserve;
            return true;
        } return false;
    }

    public int getNbPlaceTotale() {
        return nbPlaceTotale;
    }

    public int getNbPlaceReserve() {
        return nbPlaceReserve;
    }

    public int getNbPlaceRestante()
    {
        return nbPlaceTotale - nbPlaceReserve;
    }

    public int getId() {
        return id;
    }
}
