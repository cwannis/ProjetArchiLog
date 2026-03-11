package biblio.Abonne;

import java.util.Timer;
import java.util.TimerTask;

public class Abonne {

    private String id;
    private String nom;
    private int age;
    private boolean isBanned;

    public Abonne(String id, String nom, int age) {
        this.id = id;
        this.nom = nom;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getAge() {
        return age;
    }

    public void ban(long duration) {
        this.isBanned = true;
        TimerTask task = new TimerTask() {
            public void run() {
                isBanned = false;
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, duration);
    }

    public boolean isBanned() {
        return isBanned;
    }
}
