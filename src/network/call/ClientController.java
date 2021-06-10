package network.call;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Logger;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import shape.Shape;
import controller.Client;

/**
 * @author wyc
 */
public class ClientController
	extends UnicastRemoteObject implements ClientInterface {

	private final Logger logger = Logger.getLogger(this.getClass().getName());
	private final Client client;

	public ClientController(Client client) throws RemoteException {
		this.client = client;
	}

	@Override
    public void addShape(final Shape shape) {
		client.addShape(shape);
	}

	@Override
    public void addContent(final String content) {
		client.addContent(content);
	}

	@Override
    public void repaint() {
		client.repaint();
	}

	@Override
	public String pong(String x) {
		logger.info("got pong call with " + x);
		return "pong";
	}

    public static void main(String args[]) {
		String host = (args.length < 1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            var stub = (ServerInterface) registry.lookup("server");
			stub.registerClient(new ClientController(null));
			stub.requireBroadcast("hello");
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
