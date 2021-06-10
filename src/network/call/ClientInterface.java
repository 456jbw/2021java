package network.call;

import java.rmi.Remote;
import java.rmi.RemoteException;

import shape.Shape;

/**
 * @author wyc
 *
 * RMI interface of paint client
 *
 * this interface provides window refresh callbacks for server's update call
 */
public interface ClientInterface extends Remote {
	/**
	 * simple ping handler for check server's availability
	 */
	default String pong(String x) throws RemoteException {
		return "pong";
	}

    void addShape(final Shape shape) throws RemoteException;
    void addContent(final String content) throws RemoteException;
    void repaint() throws RemoteException;
}
