package bai1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;

public class Server extends Thread {

	public static void main(String[] args) throws UnknownHostException, Exception {

		ServerSocket serverSocket = new ServerSocket(8881);
		System.out.print("Server is start............");
		Socket socket = serverSocket.accept();
		DataInputStream inputStream = new DataInputStream(socket.getInputStream());
		DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
		while (true) {
			String snhan = inputStream.readUTF();
			String hoa = doiChuHoa(snhan);
			String thuong = doiChuThuong(snhan);
			int dem = demSoTu(snhan);
			outputStream.writeUTF("Chu hoa: "+hoa+" Chu thuong: "+thuong+" So ky tu: "+dem);	
		}
	}

	public static String doiChuHoa(String s) {
		return s.toUpperCase();
	}

	public static String doiChuThuong(String s) {
		return s.toLowerCase();
	}

	public static int demSoTu(String s1) {
		int dem = 0;
		String s = ' ' + s1;
		for (int i = 0; i < s.length() - 1; i++) {
			if (s.charAt(i) == ' ' && s.charAt(i + 1) != ' ')
				dem++;
		}
		return dem;
	}
}
