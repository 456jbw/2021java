package main;
import controller.*;
import network.call.ServerController;
import network.call.ClientController;

public class Start {
    public static void main(String[] args) throws Exception {
		String host = (args.length < 1) ? null : args[0];
		if (host == null) {
			ServerController.start();
		}
        Client client = Client.getInstance();
		var clientwrapper = new ClientController(client);
		Server.getInstance(host).registerClient(clientwrapper);
        client.show();
    }
}
