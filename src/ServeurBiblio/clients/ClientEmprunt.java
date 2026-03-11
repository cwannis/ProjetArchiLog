package ServeurBiblio.clients;

import ServeurBTTP.client.Client;

import java.io.IOException;
import java.util.Scanner;

public class ClientEmprunt  {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Client client = new Client(2001, "127.0.0.1");
        while(true) {
            System.out.println(client.readline());
            client.send(sc.nextLine());
        }
    }
}
