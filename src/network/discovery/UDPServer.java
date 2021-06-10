package network.discovery;

import java.io.IOException;
import java.nio.channels.DatagramChannel;
import java.net.SocketAddress;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.BufferUnderflowException;
import java.nio.channels.ClosedByInterruptException;
import java.util.logging.Logger;

/**
 * @author wyc
 * UDP server base class w/ simple magic support
 */
public abstract class UDPServer implements Runnable {
	// packet header
	public static final long MAGIC = 0x826532;
	// socket timeout
	private static final int TIMEOUT = 10000;

	final int bufferSize = 512;
	// listen address
	private SocketAddress addr;

	private final Logger logger = Logger.getLogger(this.getClass().getName());

	DatagramChannel chan;

	/**
	 * construct server w/ listen address 0.0.0.0
	 *
	 * @param port port to listen
	 */
	UDPServer(int port) throws IOException {
		this(new InetSocketAddress(port));
	}

	/**
	 * construct new server
	 *
	 * @param addr address to listen
	 */
	UDPServer(SocketAddress addr) throws IOException {
		this.addr = addr;
		chan = DatagramChannel.open();
	}

	@Override
	public void run() {
		try {
			chan.bind(addr);
			chan.socket().setSoTimeout(TIMEOUT);
			logger.info("Listen on " + chan.getLocalAddress());

			while (true) {
				var rxbuf = ByteBuffer.allocate(bufferSize)
					.order(ByteOrder.BIG_ENDIAN);
				try {
					var rxaddr = chan.receive(rxbuf);
					logger.finest("received from" + rxaddr);
					rxbuf.flip();
					this.respond(rxaddr, rxbuf);
				} catch (SocketTimeoutException
						| BufferUnderflowException ignored) {
				} catch (ClosedByInterruptException e) {
					logger.info("server exiting");
					return;
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * check magic bytes in packet header
	 * and advance buffer's position
	 *
	 * @param buf packet
	 * @return whether packet starts with specific magic
	 */
	protected static boolean checkMagic(ByteBuffer buf) {
		return MAGIC == buf.getLong();
	}

	/**
	 * callback function
	 * invoked when new UDP packet arrived
	 *
	 * @param addr packet address
	 * @param request packet content
	 */
	public abstract void respond(SocketAddress addr, ByteBuffer request)
			throws IOException;
}
