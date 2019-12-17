package bai1;

import java.awt.GridLayout;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;

public class clientb2 implements ActionListener {
	public static Panel pn1, pn2, pn3, pn;
	public static TextField tfnhap = new TextField(5);
	public static TextField tfkq = new TextField();
	public static Label lbnhap = new Label();
	public static Label lbkq = new Label();
	public static Button bt1, bt2;
	public static String s = "";
	Socket socket;
	DataOutputStream outputStream;
	DataInputStream inputStream;

	public clientb2() throws UnknownHostException, IOException {
		GUI();
	}

	void GUI() throws UnknownHostException, IOException {

		JFrame fr = new JFrame("Client");
		fr.setSize(400, 200);
		fr.setLayout(new GridLayout());
		lbnhap.setText("Nhap vao chuoi can tinh toan");
		lbkq.setText("Ket qua");
		pn = new Panel(new GridLayout(5, 1));
		pn1 = new Panel(new GridBagLayout());
		bt1 = new Button("send");
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
		socket = new Socket("127.0.0.1", 1234);
		outputStream = new DataOutputStream(socket.getOutputStream());
		inputStream = new DataInputStream(socket.getInputStream());
	}

	public static void main(String[] args) throws Exception {
		clientb2 t = new clientb2();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == bt1) {
			try {
				String s = tfnhap.getText();
				outputStream.writeUTF(s);
				String nhan = inputStream.readUTF();
				tfkq.setText(nhan);

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
