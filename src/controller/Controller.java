package controller;

import shape.Shape;
import java.rmi.RemoteException;

public class Controller{
	private static Controller controller;
	public void addShape(Shape shape){
		try {
			Server.getInstance().sendShape(shape);
			Server.getInstance().sendRepaint();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	public void addContent(String content){
		try {
			Server.getInstance().sendContent(content);
			Server.getInstance().sendRepaint();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	void repaint(){
		try {
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
}
