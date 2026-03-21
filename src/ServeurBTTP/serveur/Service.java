package ServeurBTTP.serveur;

import ServeurBTTP.codage.Codage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;

public abstract class Service implements Runnable {

    private static int TIMEOUT = 1000*60*10;
    private Socket socketclient;
    private BufferedReader sin;
    private PrintWriter sout;
    private boolean timeOut = false;

    public Service(Socket client) {
        socketclient = client;
        try {
            sout = new PrintWriter(client.getOutputStream(), true);
            sin = new BufferedReader(new InputStreamReader(client.getInputStream()));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Socket getSocketclient() {
        return socketclient;
    }

    public void setTimeOut(boolean timeOut) {
        this.timeOut = timeOut;
    }

    public String read() throws IOException {
        try {
            if (timeOut) socketclient.setSoTimeout(TIMEOUT);
            String s = sin.readLine();
            if (s == null) {
                socketclient.close();
                return null;
            } else if (s.isEmpty()) {
                socketclient.close();
                return null;
            }
            s = Codage.decodage(s);
            return s;
        }catch (SocketTimeoutException e)
        {
            sout.println("Timeout: connexion eteinte car aucune reponse apré: " + "s");
            socketclient.close();
            return null;
        }catch (IOException e) {
            System.err.println(e);
            return null;
        }
    }

    public static void setTIMEOUT(int TIMEOUT) {
        Service.TIMEOUT = TIMEOUT;
    }

    public void send(String line) {
        line = Codage.codage(line);
        sout.println(line);
    }
}
