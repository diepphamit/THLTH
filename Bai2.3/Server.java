package minhleo.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JOptionPane;

public class Server {
	DatagramSocket server;
	DatagramPacket receivePacket;
	byte[] receiveData;

	public Server(int port) {
		try {
			server = new DatagramSocket(port);
			while (true) {
				receiveData = new byte[1024];
				receivePacket = new DatagramPacket(receiveData, receiveData.length);
				server.receive(receivePacket);
				ClientConnection cc = new ClientConnection(server, receivePacket);
				cc.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		int port = Integer.parseInt(JOptionPane.showInputDialog("Nhập cổng: ", "12345"));
		new Server(port);
	}

	static class ClientConnection extends Thread {
		DatagramSocket server;
		int connectionNumber;
		DatagramPacket sendPacket;
		DatagramPacket receivePacket;
		byte[] sendData;
		byte[] receiveData;
		private static Hashtable<Integer,InetAddress> connectionList = new Hashtable<Integer,InetAddress>();
		InetAddress address;
		int port;

		public ClientConnection(DatagramSocket server, DatagramPacket receivePacket) {
			this.connectionNumber = connectionList.size();
			this.server= server;
			this.receivePacket = receivePacket;
		}

		public void run() {
			try {
				receiveData = new byte[1024];
				receiveData = receivePacket.getData();
				String message = new String(receiveData).trim();
				address = receivePacket.getAddress();
				port = receivePacket.getPort();
				if(!connectionList.containsKey(port)){
					connectionList.put(port,address);
				}
				sendToAll(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * Phát thông điệp đến tất cả client đang kết nối
		 * 
		 * @param message
		 *            nội dung
		 */
		public void sendToAll(String message) {
			synchronized (connectionList) {
				Enumeration<Integer> keys;
				keys = connectionList.keys();
				while(keys.hasMoreElements()){
					port = (Integer) keys.nextElement();
					address = connectionList.get(port);
					sendData = new byte[1024];
					sendData = (message).getBytes();
					sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
					try {
						server.send(sendPacket);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}