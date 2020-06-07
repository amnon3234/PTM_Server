package root;

import clientHandler.MyClientHandler;
import server.MySerialServer;
import server.Server;

public class Main {

    public static void main(String[] args) {
        final int port = 5555;
        Server server = new MySerialServer(port);
        server.start(new MyClientHandler());
    }
}
