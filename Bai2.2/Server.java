package Bài2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Server {
	public static void main(String[] args) throws UnknownHostException, Exception {

		byte senddata[];
		byte receivedata[] = new byte[1024];
		DatagramSocket server = new DatagramSocket(8892);
		DatagramPacket receive = new DatagramPacket(receivedata, receivedata.length);
		server.receive(receive);
		InetAddress inet = receive.getAddress();
		int port = receive.getPort();
		String nhan = new String(receive.getData());		
		String ketqua = xulychuoi(nhan);
		senddata = new byte[ketqua.length()];
		senddata = ketqua.getBytes();
		DatagramPacket send = new DatagramPacket(senddata, senddata.length, inet, port);
		server.send(send);
	}

	public static String xulychuoi(String s) {
		float ketqua = 0;
		int i = 0;
		float giatritam = 0;
		while (i < s.length()) {
			while ((s.charAt(i) != '+') && ((s.charAt(i) != '-')) && (s.charAt(i) != '*') && (s.charAt(i) != '/')) {
				ketqua = ketqua * 10 + Integer.parseInt(String.valueOf(s.charAt(i)));
				i++;
			}
			if (s.charAt(i) == '+') {
				i++;
				while ((i < s.length() && (s.charAt(i) != '+') && ((s.charAt(i) != '-')) && (s.charAt(i) != '*')
						&& (s.charAt(i) != '/'))) {
					giatritam = giatritam * 10 + Integer.parseInt(String.valueOf(s.charAt(i)));
					i++;
				}
				ketqua += giatritam;
				giatritam = 0;
			}
			if ((i < s.length()) && (s.charAt(i) == '-')) {
				i++;
				while ((i < s.length() && (s.charAt(i) != '+') && ((s.charAt(i) != '-')) && (s.charAt(i) != '*')
						&& (s.charAt(i) != '/'))) {
					giatritam = giatritam * 10 + Integer.parseInt(String.valueOf(s.charAt(i)));
					i++;
				}
				ketqua -= giatritam;
				giatritam = 0;
			}
			if ((i < s.length()) && (s.charAt(i) == '*')) {
				i++;
				while ((i < s.length() && (s.charAt(i) != '+') && ((s.charAt(i) != '-')) && (s.charAt(i) != '*')
						&& (s.charAt(i) != '/'))) {
					giatritam = giatritam * 10 + Integer.parseInt(String.valueOf(s.charAt(i)));
					i++;
				}
				ketqua *= giatritam;
				giatritam = 0;
			}
			if ((i < s.length()) && (s.charAt(i) == '/')) {
				i++;
				while ((i < s.length() && (s.charAt(i) != '+') && ((s.charAt(i) != '-')) && (s.charAt(i) != '*')
						&& (s.charAt(i) != '/'))) {
					giatritam = giatritam * 10 + Integer.parseInt(String.valueOf(s.charAt(i)));
					i++;
				}
				ketqua /= giatritam;
				giatritam = 0;
			}

		}
		System.out.print(ketqua);
		return String.valueOf(ketqua);
	}
}
