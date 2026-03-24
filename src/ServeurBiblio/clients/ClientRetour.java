package ServeurBiblio.clients;

import ServeurBTTP.client.Client;

import java.io.IOException;
import java.util.Scanner;

public class ClientRetour {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Client client = new Client(2002, "bttp://127.0.0.1");
        while(true) {
            System.out.println(client.read());
            client.send(sc.nextLine());
        }
    }
}
