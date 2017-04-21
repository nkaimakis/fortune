package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import data.ServerListener;

public class Server {
	int serverPort;
	public String ipa;
	public MySQLDriver mysql = null;
	public ServerSocket ss = null;
	public Vector<ServerListener> serverListeners = null;

	public Server(int port) {
		this.serverListeners = new Vector<ServerListener>();
		this.serverPort = port;
		try {
			this.ss = new ServerSocket(this.serverPort);
			System.out.println("Server started!");
		} catch (IOException e) {
			System.out.println("Server construction exception: " + e.getMessage());
		}

		this.mysql = new MySQLDriver();

		try {
			while (true) {
				Socket s = null;
				s = ss.accept();
				System.out.println("Connection from " + s.getInetAddress());
				ServerListener sl = new ServerListener(s, this);
				this.serverListeners.add(sl);
			}
		} catch (IOException ioe) {
			System.out.println("ioe in PlayerServer: " + ioe.getMessage());
		} finally {
			try {
				this.ss.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Error closing server socket in server: " + e.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		int port = 6969;
		new Server(port);

	}
}
