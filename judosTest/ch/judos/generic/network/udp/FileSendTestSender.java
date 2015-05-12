package ch.judos.generic.network.udp;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.InetSocketAddress;
import java.net.SocketException;

import ch.judos.generic.network.udp.Udp4Forwarded;
import ch.judos.generic.network.udp.UdpLib;

public class FileSendTestSender {
	public static void main(String[] args) throws SocketException, FileNotFoundException {
		new FileSendTestSender();
	}

	public FileSendTestSender() throws SocketException, FileNotFoundException {
		super();
		Udp4Forwarded u = UdpLib.createForwarded(60000);

		u.sendFileTo(new File("test.abc"), "tescht", new InetSocketAddress(
			"84.227.98.109", 60000), null);

	}
}
