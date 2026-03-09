package biblio.Abonne;

public class Abonne {

    private String id;
    private String nom;
    private int age;

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
}
