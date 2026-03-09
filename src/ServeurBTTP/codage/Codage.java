package ServeurBTTP.codage;

public class Codage {

    public static final String TOKENFORBACK;

    static {
        TOKENFORBACK = "##";
    }

    public static String codage(String s){
        s = s.replaceAll("\n",TOKENFORBACK);
        return s;
    }

    public static String decodage(String s){
        s = s.replaceAll(TOKENFORBACK,"\n");
        return s;
    }

}
