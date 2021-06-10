package network.call;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

import shape.Shape;

/**
 * Implementation of SourceInterface to be used for remote communication. extending UnicastRemoteObject ensures that every remote object shall be unique, therefore notifications can be directed to this particular instance.
 */
public class ServerController
	extends UnicastRemoteObject implements ServerInterface {
	private final Logger logger = Logger.getLogger(this.getClass().getName());

	private CopyOnWriteArrayList<ClientInterface> registerdClients =
		new CopyOnWriteArrayList<ClientInterface>();

	protected ServerController() throws RemoteException {
	}

	@Override
	public void registerClient(ClientInterface client) throws RemoteException {
		logger.finest("registering client");
		if(!registerdClients.contains(client)){
			registerdClients.add(client);
			logger.info("client registered");
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
    public void sendShape(Shape shape) throws RemoteException {
        for (var client: registerdClients) {
            client.addShape(shape);
        }
    }

	@Override
    public void sendRepaint() throws RemoteException {
        for (var client: registerdClients) {
            client.repaint();
        }
    }

	@Override
    public void sendContent(String content) throws RemoteException {
        for (var client: registerdClients) {
            client.addContent(content);
            client.repaint();
        }
    }

    public static void start() {
        try {
            var obj = new ServerController();
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
