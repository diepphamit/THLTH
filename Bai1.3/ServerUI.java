package server;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import client.ClientThread;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class ServerUI implements ActionListener {

	private JFrame frame;
	private JTextField txtMess;
	DataInputStream din;
	DataOutputStream dos;
	ServerSocket serverSocket;
	JTextArea ta;
	JButton btnSend;
	public Hashtable<String, ServerThread> clients = new Hashtable<>();
	

	/**
	 * Launch the application.
	 * @throws IOException 
	 */
//	public static void main(String[] args) throws IOException {
//		ServerUI window = new ServerUI();
//		window.frame.setVisible(true);
//		window.connect();
//	
//	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public ServerUI() throws IOException {
		initialize();
		connect();
	}
	
	

	private void connect() throws IOException {
		
		serverSocket=new ServerSocket(1000);
		ta.append("Server have already! "+"\n");
		while(true){
			Socket socket=serverSocket.accept();
			
			ServerThread thread=new ServerThread(socket,this);
			new Thread(thread).start();
		}
	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		
		txtMess = new JTextField();
		txtMess.setColumns(10);
		
		btnSend = new JButton("Send");
		btnSend.addActionListener(this);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(29, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtMess, GroupLayout.PREFERRED_SIZE, 292, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnSend, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 384, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(27)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtMess, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSend))
					.addContainerGap())
		);
		
		ta = new JTextArea();
		scrollPane.setViewportView(ta);
		frame.getContentPane().setLayout(groupLayout);
	}
	
	public void updateUI(String mess){
		ta.append(mess+"\n");
	}
	public void sendAll(String mess, String from) throws IOException{
		Enumeration<String> e = clients.keys();
		while(e.hasMoreElements()){
			String name = e.nextElement();
			if(!name.equals(from)){
				ServerThread thread = clients.get(name);
				thread.sendMessage("MESSAGE "+from+" "+mess);
			}
		}
 	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnSend){

		}
		
	}
}
