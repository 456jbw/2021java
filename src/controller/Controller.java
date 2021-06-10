package controller;

import shape.Shape;

import java.io.IOException;
import java.rmi.RemoteException;

import network.call.ClientController;
import network.call.ServerController;
import network.discovery.RespondServer;

public class Controller {
	private static Controller controller;
	private Thread thread;
	public void addShape(Shape shape) {
		try {
			Server.getInstance().sendShape(shape);
			Server.getInstance().sendRepaint();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void addContent(String content) {
		try {
			Server.getInstance().sendContent(content);
			Server.getInstance().sendRepaint();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	void repaint() {
		try {
			Server.getInstance().sendRepaint();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void Wait(String name) throws IOException {
		thread = new Thread(new RespondServer(name, (short)1099));
		thread.start();
		ServerController.start();
		var clientwrapper = new ClientController(Client.getInstance());
		Server.getInstance().registerClient(clientwrapper);
	}
	
	public void Start(){
		thread.interrupt();
        Client client = Client.getInstance();
        client.start();
	}

	public static Controller getInstance() {
		if (controller == null){
			controller = new Controller();
		}
		return controller;
	}
}
