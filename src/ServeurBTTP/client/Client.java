package ServeurBTTP.client;

import ServeurBTTP.codage.Codage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private final int PORT;
    private final String HOST;
    private final Socket socket;
    private BufferedReader sin;
    private PrintWriter sout;

    public Client(int PORT, String HOST) throws IOException {
        this.PORT = PORT;
        this.HOST = HOST;
        socket = new Socket(HOST, PORT);
        sin = new BufferedReader (new InputStreamReader(socket.getInputStream()));
        sout = new PrintWriter (socket.getOutputStream(), true);
    }

    public String readline()
    {
        try{
            String s = sin.readLine();
            if (s == null) {
                socket.close();
                return null;
            } else if (s.isEmpty()) {
                socket.close();
                return null;
            }
            s = Codage.decodage(s);
            return s;
        }catch (IOException e)
        {
            sout.println("une erreur s'est produite lors de l'attente du message du server : " + e);
            return null;
        }
    }

    public void send(String s)
    {
        s = Codage.codage(s);
        sout.println(s);
    }
}
