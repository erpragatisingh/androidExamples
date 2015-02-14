package chatServer;

import java.io.*;
import java.net.*;
import java.util.Vector;
import java.util.Enumeration;

/**
 * MultiChatServer -- A chat program between any number of clients.
 *
 * The server acts as the central clearing house of all messages.
 * To run as a server, do not supply any command line arguments.
 * MultiChat will start listening on the default port, waiting
 * for a connection:
 *
 *   java MultiChatServer
 */

public class MultiChatServer {
    private int       port = 5001;    // The default port.
    private boolean   listening = true;
    private Vector    clients = new Vector();

    public static void main(String args[]) {
       System.out.println("Hit control-c to exit the server.");
       new MultiChatServer().server();
    }
   
    // As a server, we create a server socket bound to the specified
    // port, wait for a connection, and then spawn a thread to 
    // read data coming in over the network via the socket.
    
    private void server() {
        ServerSocket serverSock = null; 

        try {
            InetAddress serverAddr = InetAddress.getByName(null);
            
            System.out.println("Waiting for first connection on " +
                      serverAddr.getHostName() +
                      " on port " + port);

            // Accept up to 50 connection at a time. 
            // (This limit is just for the sake of performance.)
            serverSock = new ServerSocket(port, 50);

        } catch (IOException e) {
            System.out.println(e.getMessage() +
               ": Failed to create server socket.");
            return;
        }

        while (listening) {

           try {
               Socket socket = serverSock.accept();
               System.out.println("Accepted connection from " +
                   socket.getInetAddress().getHostName());
               DataOutputStream remoteOut =
                   new DataOutputStream(socket.getOutputStream());
               clients.addElement(remoteOut);
               new ServerHelper(socket, remoteOut, this).start();
 
           } catch (IOException e) {
               System.out.println(e.getMessage() +
                  ": Failed to connect to client.");
           } 
        }

        if (serverSock != null) {
           try {
               serverSock.close();
           } catch (IOException x) {
           }
        }
    }

    synchronized Vector getClients() {
       return clients;
    }

    synchronized void removeFromClients(DataOutputStream remoteOut) {
       clients.removeElement(remoteOut);
    }
       
}
