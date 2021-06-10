package main;

import controller.*;

public class Start {
    public static void main(String[] args) {
        Server server = Server.getInstance();
        Client client = Client.getInstance();
        server.addClient(client);
        client.start();
    }
}