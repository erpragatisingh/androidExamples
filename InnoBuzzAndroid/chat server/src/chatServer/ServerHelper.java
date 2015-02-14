package chatServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Vector;

/*
 * ServerHelper handles one client. The server creates one new
 * ServerHelper thread for each client that connects to it.
 */
class ServerHelper extends Thread {
    private Socket sock;
    private DataOutputStream remoteOut;
    private MultiChatServer server;
    private boolean listening = true;
    private DataInputStream remoteIn;

    ServerHelper(Socket sock, DataOutputStream remoteOut,
       MultiChatServer server) throws IOException
    {
        this.sock = sock;
        this.remoteOut = remoteOut;
        this.server = server;
        remoteIn = new DataInputStream(sock.getInputStream());
    }

    public synchronized void run() {
        String s;

        try {
            while (listening) {
                s = remoteIn.readUTF();
                broadcast(s);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage() +
               ": Connection to peer lost.");

        } finally {
            try {
               cleanUp();
            } catch (IOException x) {
            }
        }
    }

    // Send the message to all the sockets connected to the server.
    private void broadcast(String s) {
       Vector clients = server.getClients();
       DataOutputStream dataOut = null;
       for (Enumeration e = clients.elements(); e.hasMoreElements(); ) {
          dataOut = (DataOutputStream)(e.nextElement());
          if (!dataOut.equals(remoteOut)) {
             try {
                dataOut.writeUTF(s);

             } catch (IOException x) {
                System.out.println(x.getMessage() +
                   ": Failed to broadcast to client.");
                server.removeFromClients(dataOut);
             }             
          }
       }
    }
    private void cleanUp() throws IOException {
       if (remoteOut != null) {
          server.removeFromClients(remoteOut);
          remoteOut.close();
          remoteOut = null;
       }
       if (remoteIn != null) {
          remoteIn.close();
          remoteIn = null;
       }

       if (sock != null) {
          sock.close();
          sock = null;
       }
    }
    protected void finalize() throws Throwable {
       try {
          cleanUp();
       } catch (IOException x) {
       }
       super.finalize();
    }

}

