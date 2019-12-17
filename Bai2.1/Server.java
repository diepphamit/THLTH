package Bài1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {

	public static void main(String[] args) throws UnknownHostException, Exception {

		   byte senddata[];
		   byte receivedata[]=new byte[1024];		
		   DatagramSocket server=new DatagramSocket(8892);		   
		   DatagramPacket receive=new DatagramPacket(receivedata, receivedata.length);
		   server.receive(receive);
		   InetAddress inet=receive.getAddress();
		   int port=receive.getPort();
		   String nhan=new String(receive.getData());
		   senddata=new byte[nhan.length()];
		   String chuoihoa=doiChuHoa(nhan);
		   senddata=chuoihoa.getBytes();
		   DatagramPacket send=new DatagramPacket(senddata, senddata.length, inet, port);
		   server.send(send);
		   String chuoithuong=doiChuThuong(nhan);
		   senddata=chuoithuong.getBytes();
		   send=new DatagramPacket(senddata, senddata.length, inet, port);
		   server.send(send);
		   int dem=demSoTu(nhan);
		   String soTu="So tu la:"+dem;
		   System.out.println(dem);
		   senddata=soTu.getBytes();
		   send=new DatagramPacket(senddata, senddata.length, inet, port);
		   server.send(send);
	
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
