package chat.server;

import chat.XMLMessageSerializer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.logging.Logger;

public class ChatServer implements AutoCloseable, Runnable {
    private static final Logger logger = Logger.getLogger(ChatServer.class.getName());

    private final ServerSocket serverSocket;
    private final ClientsRegistry registry;
    private final ClientsCommunicator communicator;

    public ChatServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        registry = new ClientsRegistry();
        communicator = new ClientsCommunicator(registry);
    }

    private void serverLoop() throws IOException {
        Socket clientSocket = serverSocket.accept();
        logger.info("Accepted new connection from " + clientSocket.getInetAddress());
        ClientProcessor processor = new ClientProcessor(clientSocket, registry, communicator,
                new XMLMessageSerializer());
        processor.run();
    }

    @Override
    public void run() {
        logger.info("Starting chat server at " + serverSocket.getLocalSocketAddress());

        while (true) {
            try {
                serverLoop();
            } catch (IOException e) {
                logger.severe("Error: " + e.getMessage());
                return;
            }
        }
    }

    @Override
    public void close() {
        logger.info("Stopping chat server...");

        registry.forEach((i, client) -> client.stop());

        logger.info("Stopped all clients");
    }
}
