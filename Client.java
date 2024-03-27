package MP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            // Connect to the server (replace "localhost" and 5000 with the actual server address and port)
            Socket socket = new Socket("192.168.250.18", 5000);

            // Set up input and output streams for communication
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Prompt the user to enter a unique client name
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter your name: ");
            String clientName = consoleInput.readLine();

            // Send the client name to the server
            out.println(clientName);

            // Start a thread to listen for messages from the server
            new Thread(() -> {
                try {
                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            // Allow the user to send messages to the server
            String userMessage;
            System.out.println("To send a message, type 'recipientName:message'.");
            while ((userMessage = consoleInput.readLine()) != null) {
                out.println(userMessage);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
