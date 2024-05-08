package javaz.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPsender {
	public static void main(String[] args) throws IOException {
		System.out.println("** SENDER started **");
		
		DatagramSocket socket = new DatagramSocket();
		byte[] buf = new String("Hello receiver!").getBytes();
		
		InetAddress addr = InetAddress.getByName("localhost");
		DatagramPacket packet = new DatagramPacket(buf, buf.length, addr, 5000);
		socket.send(packet);
		socket.close();

	}
}
