package MP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private ServerSocket ServerSocket;
    private List<ClientHandler> clients = new ArrayList<>();

    public Server(int port) {
        try {
            ServerSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);
            acceptClients();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void acceptClients() {
        while (true) {
            try {
                Socket clientSocket = ServerSocket.accept();
                System.out.println("New client connected: " + clientSocket);
                ClientHandler clientHandler = new ClientHandler(clientSocket, this);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void broadcastMessage(String message, ClientHandler sender) {
        String[] parts = message.split(":", 2);
        if (parts.length < 2) {
            return;  // Invalid message format
        }
        String recipientName = parts[0];
        String text = parts[1];

        for (ClientHandler client : clients) {
            if (client.getClientName().equals(recipientName)) {
                client.sendMessage(sender.getClientName() + ": " + text);
                break;
            }
        }
    }

    public static void main(String[] args) {
        int port = 5000; // Choose any available port
        new Server(port);
    }
}