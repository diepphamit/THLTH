package bai1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class serverb2 extends Thread {

	public static void main(String[] args) throws Exception {

		ServerSocket serverSocket = new ServerSocket(1234);
		System.out.print("Server is start............");
		Socket socket = serverSocket.accept();
		DataInputStream inputStream = new DataInputStream(socket.getInputStream());
		DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
		while (true) {
			String snhan = inputStream.readUTF();
			String stra = xulychuoi(snhan);
			outputStream.writeUTF(stra);
		}
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
