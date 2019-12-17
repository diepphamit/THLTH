package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.StringTokenizer;

import javax.swing.JDialog;

public class ClientThread implements Runnable{
	Socket socket;
	DataOutputStream dos;
	DataInputStream din;
	Client main;
	boolean isRunning = false;
	public ClientThread(Socket socket, Client main) throws IOException {
		this.socket=socket;
		this.main = main;
		dos=new DataOutputStream(socket.getOutputStream());
		din=new DataInputStream(socket.getInputStream());
		isRunning = true;
		
	}

	@Override
	public void run() {
		while(isRunning){
			try {
				String mess=din.readUTF();
				StringTokenizer st = new StringTokenizer(mess);
				String CMD = st.nextToken();
				String from= st.nextToken();
				switch(CMD){
				   case "MESSAGE":
					   String msg ="";
					   while(st.hasMoreTokens()){
						   msg = msg +" "+ st.nextToken();
					   }
					   main.updateUI(from+": "+msg);
					   break; 
				
				}
				System.out.println(mess);
				
			} catch (IOException e) {
				break;
			}
		}
	
	} 
	public void sendMessage(String mess) throws IOException{
		dos.writeUTF(mess);
	}
	public void updateUI(String mess){
		main.updateUI(mess);
	}
	
	public void stop(){
		isRunning = false;
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
