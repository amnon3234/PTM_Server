package root;

import clientHandler.MyClientHandler;
import server.MySerialServer;
import server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        final int port = 5555;
        Server server = new MySerialServer(port);
        server.start(new MyClientHandler());
    }
}
