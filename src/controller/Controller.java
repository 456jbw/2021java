package controller;

import shape.Shape;
import views.Drawboard;
import views.ReceiveView;

import java.io.IOException;
import java.rmi.RemoteException;
import javax.swing.JTextField;

import network.call.ClientController;
import network.call.ServerController;
import network.discovery.DiscoveryProber;
import network.discovery.RespondServer;

public class Controller {
	private static Controller controller;
	private Thread serverThread;
	private Thread searchThread;
	public void addShape(Shape shape) {
		try {
			Server.getInstance().sendShape(shape);
			Server.getInstance().sendRepaint();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public static Controller getInstance() {
		if (controller == null){
			controller = new Controller();
		}
		return controller;
	}
	
	public void addContent(String content) {
		String name = Client.getInstance().getName() + ":";
		try {
			Server.getInstance().sendContent(name+content);
			Server.getInstance().sendRepaint();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		((JTextField)Drawboard.getInstance().getComponent(1)).setText("");
	}

	void repaint() {
		try {
			Server.getInstance().sendRepaint();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void Wait(String name) throws IOException {
		serverThread = new Thread(new RespondServer(name, (short)1099));
		serverThread.start();
		ServerController.start();
		// assert server has already started
		ServerController.mayGetController()
			.setCallback(ReceiveView.getInstance()::addClient);

		Client.getInstance().setName(name);
		Client.getInstance().setState(1);
		var clientwrapper = new ClientController(Client.getInstance());
		Server.getInstance().registerClient(clientwrapper, name);
	}
	
	public void Start(){
		if (serverThread != null){
			serverThread.interrupt();
		}
		try {
			Server.getInstance().sendStart();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void Find() throws IOException {
		searchThread = new Thread(
				new DiscoveryProber(
					null,
					Client.getInstance()::addServer));
		searchThread.start();
		Client.getInstance().setState(3);
	}

}
