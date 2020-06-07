package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import clientHandler.ClientHandler;

public class MySerialServer implements Server {
	//Data
	private int port;
	private volatile boolean stop;
	
	//Constructor
	public MySerialServer(int port) {
		this.stop = false;
		this.port = port;
	}
	
	//Private Methods
	private void runServer(ClientHandler c) {
		try {
		ServerSocket server = new ServerSocket(this.port); // Setting a socket for the server to listen on
		server.setSoTimeout(3000); 
		while(!this.stop) {
			try {
				Socket aClient = server.accept();// Waiting for a client to connect
				try {
					c.handleClient(aClient.getInputStream(), aClient.getOutputStream());
					aClient.close();
				} catch(IOException e) {System.out.println("exit(3) = can't read from client");} 
			}catch(SocketTimeoutException e) { System.out.println("exit(2) = timeOut"); }
		}
		server.close();
		}catch (IOException e) { System.out.println("exit(1)");	}// Coudn't connect to a socket
	}
	
	/**
	 * Run server on different thread
	 */
	@Override
	public void start(ClientHandler c) {
		new Thread(()->runServer(c)).start(); 
	}
	
	/**
	 * Disconnecting the server
	 */
	@Override
	public void stop() {
		stop = true; // Closing the server.
	}

}
