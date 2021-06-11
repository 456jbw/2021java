package network.call;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;
import java.util.function.Consumer;

import shape.Shape;

/**
 * @author wyc
 *
 * paint server
 */
public class ServerController
	extends UnicastRemoteObject implements ServerInterface {
	private final Logger logger = Logger.getLogger(this.getClass().getName());

	private CopyOnWriteArrayList<ClientInterface> registerdClients =
		new CopyOnWriteArrayList<ClientInterface>();

	// callback function when a new client joins
	private Consumer<String> callback = (a)->{};

	public void setCallback(Consumer<String> cb) {
		callback = cb;
	}

	protected ServerController() throws RemoteException {
	}

	@Override
	public void registerClient(ClientInterface client) throws RemoteException {
		registerClient(client, "");
	}

	@Override
	public void registerClient(ClientInterface client, String name)
		throws RemoteException {
		logger.finest("registering client");
		if(!registerdClients.contains(client)){
			registerdClients.add(client);
			logger.info("client registered");
			callback.accept(name);
		}
	}

	@Override
	public void unregisterClient(ClientInterface client)
		throws RemoteException {
		logger.finest("unregistering client");
		if(registerdClients.contains(client)){		
			registerdClients.remove(client);
			logger.info("client successfully unregistered.");
		}
	}

	@Override
	public void requireBroadcast(String x) throws RemoteException {
		logger.fine("broadcast handler called");
		for (var i : registerdClients) {
			logger.fine("calling pong");
			i.pong(x);
		}
	}

	/**
	 * 发送消息
	 */
	@Override
	public void sendShape(Shape shape) {
		for (var client: registerdClients) {
			try {
				client.addShape(shape);
			} catch (RemoteException ignored) {
			}
		}
	}

	@Override
	public void sendRepaint() {
		for (var client: registerdClients) {
			try {
				client.repaint();
			} catch (RemoteException ignored) {
			}
		}
	}

	@Override
	public void sendContent(String content) {
		for (var client: registerdClients) {
			try {
				client.addContent(content);
				client.repaint();
			} catch (RemoteException ignored) {
			}
		}
	}

	private static ServerController obj;

	/**
	 * get running server
	 *
	 * @return running server, or null if server not started
	 */
	public static ServerController mayGetController() {
		return obj;
	}

	public static void start() {
		if (obj != null) {
			return;
		}

		try {
			obj = new ServerController();
			var stub = (ServerInterface)obj;

			// Bind the remote object's stub in the registry
			Registry registry = LocateRegistry.createRegistry(1099);
			registry.bind("server", stub);

			obj.logger.info("Server ready");
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		try {
			var obj = new ServerController();
			var stub = (ServerInterface)obj;

			// Bind the remote object's stub in the registry
			Registry registry = LocateRegistry.createRegistry(1099);
			registry.bind("server", stub);

			System.out.println("Server ready");
			/*
			   for (int i = 0; i < 10; i++) {
			   Thread.sleep(1000);
			   }*/
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}
}
