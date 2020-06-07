package server;

import clientHandler.ClientHandler;

public interface Server {
	void start(ClientHandler c);
	void stop();
}
