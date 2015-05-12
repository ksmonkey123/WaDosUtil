package ch.judos.generic.network.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * @since 17.07.2013
 * @author Julian Schelker
 */
public class Client {

	/**
	 * @param args
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket s = new Socket(InetAddress.getByName("localhost"), 60000);
		BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s
			.getOutputStream()));

		System.out.println("connected to the server :" + s.getInetAddress() + " : "
			+ s.getPort());
		System.out.println("using local port: " + s.getLocalPort());

		Scanner scanner = new Scanner(System.in);

		while (true) {
			String msg = scanner.nextLine();
			if (msg.equals("exit"))
				break;
			out.write(msg);
			out.newLine();
			out.flush();
			String answer = in.readLine();
			System.out.println("answer from server: " + answer);
		}

		s.close();
		scanner.close();
	}

}
