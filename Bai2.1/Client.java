package Bài1;

import java.awt.Button;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JFrame;

public class Client implements ActionListener {
	public static Panel pn1, pn2, pn3, pn;
	public static TextField tfnhap = new TextField(5);
	public static TextField tfkq = new TextField();
	public static Label lbnhap = new Label();
	public static Label lbkq = new Label();
	public static Button bt1, bt2;
	public static String s = "";
	DatagramSocket moi;
	InetAddress inet;
	byte senddata[];
	byte receivedata[];

	public Client() throws UnknownHostException, IOException {
		GUI();
	}

	void GUI() throws UnknownHostException, IOException {

		JFrame fr = new JFrame("Client");
		fr.setSize(400, 200);
		fr.setLayout(new GridLayout());
		lbnhap.setText("Nập vào chuỗi cần xử lý");
		lbkq.setText("Kết quả");
		pn = new Panel(new GridLayout(5, 1));
		pn1 = new Panel(new GridBagLayout());
		bt1 = new Button("Chuyển");
		bt2 = new Button("Exit");
		pn.add(lbnhap);
		pn.add(tfnhap);
		pn.add(lbkq);
		pn.add(tfkq);
		pn1.add(bt1);
		pn1.add(bt2);
		pn.add(pn1);
		fr.add(pn);
		bt1.addActionListener(this);
		bt2.addActionListener(this);
		fr.show();
		inet = InetAddress.getByName("localhost");
		moi = new DatagramSocket();
	}

	public static void main(String[] args) throws Exception {
		Client t = new Client();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == bt1) {
			try {
				String kq="";
				String s = tfnhap.getText();
				senddata = new byte[s.length()];
				receivedata = new byte[s.length()];
				senddata = s.getBytes();
				DatagramPacket send = new DatagramPacket(senddata, senddata.length, inet, 8892);
				moi.send(send);
				for(int i=0;i<3;i++){
					DatagramPacket receive = new DatagramPacket(receivedata, receivedata.length);
					moi.receive(receive);
					kq+=new String(receive.getData())+" *** ";
				}	
				tfkq.setText(kq);

			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		if (e.getSource() == bt2)
			System.exit(0);
	}
}
