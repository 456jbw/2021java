package network.discovery;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.io.IOException;
import java.net.SocketAddress;
import java.util.logging.Logger;

/**
 * UDP server that handles peer discovery probe packet.
 * 
 * RespondServer sends probe response packet,
 * including advertised name and port,
 * to the source of probe request packet.
 */
public class RespondServer extends UDPServer {
	// port to listen
	public static final int PORT = 54333;
	// header of probe response
	public static final long PONG_MESSAGE = 0xadadad;

	private final Logger logger = Logger.getLogger(this.getClass().getName());

	public final String advertisedName;
	public final short advertisedPort;

	// probe response packet to send
	private ByteBuffer advertisement = ByteBuffer.allocate(super.bufferSize);

	/**
	 * construct RespondServer
	 *
	 * @param name name to advertise
	 * @param port port to advertise
	 */
	public RespondServer(String name, short port) throws IOException {
		super(PORT);
		advertisedName = name;
		advertisedPort = port;

		advertisement.order(ByteOrder.BIG_ENDIAN)
			.putLong(UDPServer.MAGIC)
			.putLong(PONG_MESSAGE)
			.putShort(port)
			.put(name.getBytes())
			.flip();
	}

	@Override
	public void respond(SocketAddress addr, ByteBuffer request)
		throws IOException {
		if (!(checkMagic(request) &&
					request.getLong() == DiscoveryProber.PING_MESSAGE)) {
			return;
		}
		logger.fine("received probe from" + addr);
		chan.send(advertisement, addr);
		advertisement.flip();
	}

	public static void main(String[] args) throws IOException {
		var thread = new Thread(
				new RespondServer("RespondServer", (short)1234));
		thread.start();
		System.in.read();
		thread.interrupt();
	}
}
