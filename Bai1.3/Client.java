package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Client implements ActionListener {

	private JFrame frame;
	private JTextField txtNickName;
	private JTextField txtMess;
	ClientThread thread;
	private JButton btnSend,btnLogin ;
	private JTextArea ta;
	private JMenuBar menuBar;
	private JMenu mnAccount;
	private JMenuItem mntmLogOut;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client window = new Client();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Client() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNickName = new JLabel("Nick name: ");
		
		txtNickName = new JTextField();
		txtNickName.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		
		txtMess = new JTextField();
		txtMess.setColumns(10);
		
		btnSend= new JButton("Send");
		btnSend.setEnabled(false);
		btnSend.addActionListener(this);
			
		
		btnLogin = new JButton("Log in");
		btnLogin.addActionListener(this);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNickName)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtNickName, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnLogin))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtMess, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnSend)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE, false)
						.addComponent(lblNickName)
						.addComponent(txtNickName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLogin))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtMess, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSend))
					.addGap(8))
		);
		
		ta = new JTextArea();
		scrollPane.setViewportView(ta);
		frame.getContentPane().setLayout(groupLayout);
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		mnAccount = new JMenu("Account");
		menuBar.add(mnAccount);
		
		mntmLogOut = new JMenuItem("Log out");
		mntmLogOut.addActionListener(this);
		mnAccount.add(mntmLogOut);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnLogin){
			try {
				Socket socket =new Socket("localhost",1000);
				DataOutputStream os = new DataOutputStream(socket.getOutputStream());
				os.writeUTF("LOG_IN "+txtNickName.getText());
				DataInputStream in = new DataInputStream(socket.getInputStream());
				String response = in.readUTF();
				if(response.equals("SUCCESS")){
					ta.setText("");
					thread = new ClientThread(socket, this);
					new Thread(thread).start();
					btnSend.setEnabled(true);
					txtNickName.setEnabled(false);
					btnLogin.setEnabled(false);
				}else{
				   updateUI("ERROR : tai khoan da ton tai");
				
				}
				

				
			} catch (UnknownHostException e1) {
				
				
			} catch (IOException e1) {
				
			}
		
		}
		if(e.getSource()==btnSend){
			try{
				thread.sendMessage("MESSAGE "+txtNickName.getText()+ " "+txtMess.getText());
				updateUI("I :"+txtMess.getText());
				txtMess.setText("");
			} catch (IOException e1) {
				
			}
		}
		
		if(e.getSource()==mntmLogOut){
			try {
				thread.sendMessage("LOG_OUT "+txtNickName.getText());
				thread.stop();
				btnSend.setEnabled(false);
				txtNickName.setEnabled(true);
				btnLogin.setEnabled(true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	public void updateUI(String mess){
		ta.append(mess+"\n");
	}
}
