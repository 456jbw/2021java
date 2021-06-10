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

public abstract class UDPServer implements Runnable {

	public static final long MAGIC = 0x826532;
	private static final int TIMEOUT = 10000;

	final int bufferSize = 512;
	private SocketAddress addr;

	private final Logger logger = Logger.getLogger(this.getClass().getName());

	DatagramChannel chan;

	UDPServer(int port) throws IOException {
		this(new InetSocketAddress(port));
	}

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

	static boolean checkMagic(ByteBuffer buf) {
		return MAGIC == buf.getLong();
	}

	public abstract void respond(SocketAddress addr, ByteBuffer request)
			throws IOException;
}
