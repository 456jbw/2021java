package network.discovery;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.net.SocketAddress;
import java.net.InetSocketAddress;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.function.BiConsumer;

/**
 * peer prober
 *
 * broadcasts probe request packet on all interfaces
 * to retrive peer list
 */
public class DiscoveryProber implements Runnable {
	// probe request header
	public static final long PING_MESSAGE = 0xbebebe;

	private static final int PROBE_INTERVAL = 1000;

	private final Logger logger = Logger.getLogger(this.getClass().getName());

	private final DiscoverServer serv;

	/** construct prober
	 *
	 * @param port port to use
	 * @param cb callback function for new peer response
	 */
	public DiscoveryProber(
			int port,
			BiConsumer<String, InetSocketAddress> cb
			) throws IOException {
		this.serv = new DiscoverServer(port, cb);
	}

	/** construct prober
	 *
	 * @param addr address to listen on
	 * @param cb callback function for new peer response
	 */
	public DiscoveryProber(
			InetSocketAddress addr,
			BiConsumer<String, InetSocketAddress> cb
			) throws IOException {
		this.serv = new DiscoverServer(addr, cb);
	}

	/**
	 * UDP server to handle incoming probe response packet
	 */
	public class DiscoverServer extends UDPServer {
		private final BiConsumer<String, InetSocketAddress> callback;
		public DiscoverServer(
				int port,
				BiConsumer<String, InetSocketAddress> cb
				) throws IOException {
			super(port);
			this.callback = cb;
		}

		public DiscoverServer(
				InetSocketAddress addr,
				BiConsumer<String, InetSocketAddress> cb
				) throws IOException {
			super(addr);
			this.callback = cb;
		}

		@Override
		public void respond(SocketAddress addr, ByteBuffer request)
			throws IOException {
			if (!(checkMagic(request) &&
						request.getLong() == RespondServer.PONG_MESSAGE)) {
				return;
						}
			var port = request.getShort();

			var namebuf = new byte[super.bufferSize];
			request.get(namebuf, 0, request.remaining());
			var name = new String(namebuf);

			try {
				var inetaddr = (InetSocketAddress)addr;
				var respaddr = new InetSocketAddress(
						inetaddr.getAddress(),
						port);
				logger.info("received probe reponse from "
						+ name + "@" + respaddr);
				this.callback.accept(name, respaddr);
			} catch (ClassCastException ignored) {
			}
		}
	}

	@Override
	public void run() {
		try {
			var serv_thread = new Thread(serv);
			serv_thread.start();

			serv.chan.socket().setBroadcast(true);
			var addr = new InetSocketAddress(
					"255.255.255.255",
					RespondServer.PORT);
			var probe = ByteBuffer.allocate(512)
				.order(ByteOrder.BIG_ENDIAN)
				.putLong(UDPServer.MAGIC)
				.putLong(PING_MESSAGE);

			for (;;) {
				probe.flip();
				serv.chan.send(probe, addr);
				logger.finest("probe send");
				try {
					Thread.sleep(PROBE_INTERVAL);
				} catch (InterruptedException e) {
					serv_thread.interrupt();
					return;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		// listen on random port
		var thread = new Thread(new DiscoveryProber(
					null,
					(a,b)->System.out.println("get " + a + b)
					));
		thread.start();
		System.in.read();
		thread.interrupt();
	}
}
