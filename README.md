# Project Report: Chat Box Implementation

## Introduction
This project is centered on constructing a fundamental chat box system, pivotal for facilitating seamless communication among multiple clients via a centralized server. The triad of classes - Server, ClientHandler, and Client - collectively forms the backbone of this system. The Server class acts as the gateway, initializing a connection-receptive ServerSocket, managing incoming connections, and orchestrating message dissemination. ClientHandler operates at the heart of individual client connections, expertly handling I/O streams, and enabling message relay between clients and the server. Finally, the Client class serves as the user interface, responsible for client-server interfacing, message exchange, and user input/output management. Together, these classes harmoniously enable the establishment of robust communication channels, adeptly managing connections and ensuring the seamless exchange of messages among interconnected clients within the chat box environment.

## Server Class
### ServerSocket Initialization (java.net.ServerSocket):
- The ServerSocket is initiated within the Server class, setting up the server-side socket to listen for incoming connections on a specified port.
- Accepting Clients - ClientHandler (PROJECT.ClientHandler):
- The Server class continuously waits for incoming client connections using a ServerSocket within a loop.
- Upon accepting a client connection, it creates an instance of the ClientHandler class (ClientHandler clientHandler = new ClientHandler(clientSocket, this);), passing the connected client's socket and the reference to the server itself.
- The ClientHandler instance manages the specific client's connection and communication with the server, handling I/O streams and message relay.
### Managing Connected Clients (java.util.List - ArrayList):
- The Server class maintains a List of ClientHandler instances (private List<ClientHandler> clients = new ArrayList<>();) to keep track of all connected clients.
- This list enables the server to iterate through connected clients when broadcasting messages (for (ClientHandler client : clients)), excluding the sender of the message.
### Broadcasting Messages:
- The Server class contains a method broadcastMessage() that facilitates the distribution of messages to all connected clients except the sender.
- It iterates through the list of ClientHandler instances and utilizes each instance's sendMessage() method to relay the message to all clients except the sender (if (client != sender)).

## ClientHandler Class
### Socket (java.net.Socket):
- The Socket class is utilized within the ClientHandler to establish a communication channel between the server and a specific connected client.
- It provides the means to create input and output streams for communication (clientSocket.getInputStream() and clientSocket.getOutputStream()).
### Input/Output Handling (java.io.BufferedReader, java.io.PrintWriter):
- The BufferedReader and PrintWriter classes are used within the ClientHandler to manage input and output streams, respectively.
- BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); sets up the input stream to read data from the connected client.
- PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); initializes the output stream to send data back to the connected client.
- These I/O streams enable communication between the server and the specific client managed by this ClientHandler.
### Receiving and Broadcasting Messages:
- The ClientHandler class continuously listens for messages from the specific client it manages using the BufferedReader (in.readLine()).
- Once a message is received from a client, the ClientHandler relays this message to the server by invoking the broadcastMessage() method in the Server class, which handles message broadcasting to other connected clients.

## Client Class
### Socket (java.net.Socket):
- The Socket class within the Client is crucial for initiating a connection to the server.
- It establishes a socket connection to the server by providing the server's IP address and port number (Socket socket = new Socket("192.168.192.234", 5000);).
### Input/Output Handling (java.io.BufferedReader, java.io.PrintWriter):
- The BufferedReader and PrintWriter classes are employed to manage the input and output streams, respectively, for the client's communication with the server.
- BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); sets up the input stream to read messages sent from the server.
- PrintWriter out = new PrintWriter(socket.getOutputStream(), true); initializes the output stream to send user messages to the server.
### Sending and Receiving Messages:
- The Client class handles user input through the command-line interface (BufferedReader consoleInput) and sends the user's messages to the server using the PrintWriter (out.println(userMessage)).
- It continuously listens for incoming messages from the server in a separate thread, using the BufferedReader to read messages (while ((message = in.readLine()) != null)).

## Difference Between ClientHandler and Client Class
The ClientHandler and Client classes serve distinct purposes within a client-server architecture:
### ClientHandler Class: ( Server-Side Interaction )
- Role: The ClientHandler operates on the server-side, managing individual client connections.
- Communication Handling: It manages input/output streams between the server and each connected client.
- Responsibilities: Listens for messages from specific clients and relays these messages to the server for distribution to other clients. Manages Client-Specific Tasks: Handles communication tasks specific to each connected client, such as receiving messages from a client and broadcasting them.
### Client Class: ( Client-Side Interaction )
- Role: The Client class operates on the client-side, representing the interface for individual users.
- Establishes Connection: Establishes a connection to the server by creating a socket and initiating communication.
- User Interaction: Manages user input/output, allowing the user to input messages and sending them to the server.
- Receives Server Messages: Listens for incoming messages from the server and displays them to the user.

## How It Works
1. Server Initialization:
- Start and Listen: The Server class initializes a ServerSocket on a specified port, setting it up to listen for incoming connections. Upon successful initialization, the server is ready to accept incoming client connections.
2. Client Connection:
- Client-Side Connection: Clients initiate a connection to the server by providing the server's IP address and the specific port the server is listening on. Once a connection is established, the server accepts the client's connection request, and a socket connection is formed between the client and the server.
3. Client Identification:
- Unique Client Names: Upon successful connection, clients are prompted to input their unique names or identifiers using the console interface provided by the Client class. These unique identifiers help distinguish one client from another within the chat system.
4. Message Exchange:
- Sending and Receiving Messages: Clients interact via the console interface provided by the Client class, enabling them to input messages. When a client sends a message, the Client class sends it over the established socket connection to the server. The Server class, through its ClientHandler instances, receives the message from the sender client. The server then relays this message to all other connected clients except the sender using the broadcastMessage() method.
5. Continuous Communication:
- Real-Time Communication: The system enables continuous communication by maintaining open socket connections between the server and connected clients. As clients send messages or receive messages from other clients through the server, the communication remains active until a client actively disconnects or encounters an error.

## Conclusion
This chat box project effectively demonstrates a basic client-server model for real-time communication. The server manages multiple client connections while the clients interact through a simple command-line interface. It showcases the fundamental principles of socket programming, enabling users to exchange messages in a synchronized and distributed manner.
This project serves as a foundational framework for more complex chat applications, showcasing the core concepts of networking, threading, and input/output handling in Java.
