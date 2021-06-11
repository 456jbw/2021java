package network.call;

import java.rmi.Remote;
import java.rmi.RemoteException;

import shape.Shape;

/**
 * @author wyc
 *
 * RMI interface of paint server
 *
 * since the server is headless,
 * it only receives user input from remote and local clients,
 * perform drawing logic,
 * and then make calls to clients' interfaces to refresh screen
 */
public interface ServerInterface extends Remote {
	/**
	 * simple ping handler for check server's availability
	 */
	default String ping(String x) throws RemoteException {
		return "pong";
	}
	
	/**
	 * Registers a client
	 * perform handshake with server
	 *
	 * @param client client to register
	 */
	void registerClient(ClientInterface client) throws RemoteException;
	/**
	 * Registers a client
	 * perform handshake with server
	 *
	 * @param client client to register
	 * @param name client name
	 */
	void registerClient(ClientInterface client, String name)
			throws RemoteException;
	/**
	 * Unregisters a client
	 *
	 * @param client client to unregister
	 */
	void unregisterClient(ClientInterface client) throws RemoteException;

	void requireBroadcast(String x) throws RemoteException;
    void sendShape(Shape shape) throws RemoteException;
    void sendRepaint() throws RemoteException;
    void sendContent(String content) throws RemoteException;
}
