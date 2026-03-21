package ServeurBiblio.clients;

import ServeurBTTP.client.Client;

import java.io.IOException;
import java.util.Scanner;

public class clientReservation {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Client client = new Client(2000, "127.0.0.1");
        while(true) {
            System.out.println(client.read());
            client.send(sc.nextLine());
        }
    }
}
