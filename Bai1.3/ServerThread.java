package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.StringTokenizer;

public class ServerThread implements Runnable {
	Socket socket;
	DataOutputStream dos;
	DataInputStream din;
	ServerUI main;
	boolean isRunning = false;

	public ServerThread(Socket socket, ServerUI main) throws IOException {
		this.socket = socket;
		this.main = main;
		dos = new DataOutputStream(socket.getOutputStream());
		din = new DataInputStream(socket.getInputStream());
		isRunning = true;

	}

	@Override
	public void run() {
		while (isRunning) {
			try {
				String mess = din.readUTF();
				StringTokenizer st = new StringTokenizer(mess);
				String CMD = st.nextToken();
				String from = st.nextToken();
				switch (CMD) {
				case "LOG_IN":
					if (!main.clients.containsKey(from)) {
						main.clients.put(from, this);
						main.updateUI(from + " joined chat room!");
						sendMessage("SUCCESS");
					} else {
						sendMessage("ERROR");
					}
					break;
				case "MESSAGE":

					String msg = "";
					while (st.hasMoreTokens()) {
						msg = msg + " " + st.nextToken();
					}

					main.sendAll(msg, from);
					break;
				case "LOG_OUT":
					main.clients.remove(from);
					updateUI(from + " left chat room!");
					isRunning = false;
					socket.close();
					break;

				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void updateUI(String mess) {
		main.updateUI(mess);
	}

	public void sendMessage(String mess) throws IOException {
		dos.writeUTF(mess);

	}

}
