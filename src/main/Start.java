package main;

import controller.*;
import network.call.ServerController;
import network.call.ClientController;

public class Start {
    public static void main(String[] args) throws Exception {
		ServerController.start();
        Client client = Client.getInstance();
		var clientwrapper = new ClientController(client);
		Server.getInstance().registerClient(clientwrapper);
        client.start();
    }
}
