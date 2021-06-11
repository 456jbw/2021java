package main;
import controller.*;
import network.call.ServerController;
import network.call.ClientController;

public class Start {
    public static void main(String[] args) throws Exception {
        Client client = Client.getInstance();
        client.show();
    }
}
