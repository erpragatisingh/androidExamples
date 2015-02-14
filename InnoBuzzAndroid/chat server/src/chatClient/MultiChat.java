package chatClient;


import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * MultiChat -- A chat program between any number of clients.
 *
 * To run as a client, supply two parameters:
 *   1. The name of the person to identify this user
 *   2. the name the server:
 *   java MultiChat Spielberg bluehorse.com
 * or
 *   java MultiChat Spielberg local       // to run locally
 */

public class MultiChat extends Panel {
    TextArea  receivedText;
    Socket    sock;           // The communication socket.

    private GridBagConstraints c;
    private GridBagLayout      gridBag;
    private Frame              frame;
    private Label              label;
    private int                port = 5001;  // The default port.
    private TextField          sendText;
    private String             hostname;
    private String             username;
    private DataOutputStream   remoteOut;

    public static void main(String args[]) {
       if (args.length != 2) {
          System.out.println("format is: java MultiChat <username> <hostname>");
          return;
       }
       
       Frame f = new Frame(args[0]);

       MultiChat chat = new MultiChat(f, args[0], args[1]);
       f.add("Center", chat);
       f.setSize(350, 200);
       f.show();

       // Make the connection happen.
       chat.client();        

    }
       public MultiChat(Frame f, String user, String host) {     
        frame = f;
        frame.addWindowListener(new WindowExitHandler());
        username = user;
        hostname = host;

        // Build the user interface.
        Insets insets = new Insets(10, 20, 5, 10); // bot, lf, rt, top
        gridBag = new GridBagLayout();
        setLayout(gridBag);

        c = new GridBagConstraints();

        c.insets = insets;
        c.gridy = 0;
        c.gridx = 0;

        label = new Label("Text to send:");
        gridBag.setConstraints(label, c);
        add(label);

        c.gridx = 1;

        sendText = new TextField(20);
        sendText.addActionListener(new TextActionHandler());
        gridBag.setConstraints(sendText, c);
        add(sendText);

        c.gridy = 1;
        c.gridx = 0;

        label = new Label("Text received:");
        gridBag.setConstraints(label, c);
        add(label);

        c.gridx = 1;

        receivedText = new TextArea(3, 20); 
        gridBag.setConstraints(receivedText, c);
        add(receivedText);
    
    }

    // As a client, we create a socket bound to the specified port,
    // connect to the specified host, and then spawn a thread to 
    // read data coming coming in over the network via the socket.

    private void client() {
        try {
            if (hostname.equals("localhost"))
               hostname = null;

            InetAddress serverAddr = InetAddress.getByName(hostname);
            sock = new Socket(serverAddr.getHostName(), port);           
            remoteOut = new DataOutputStream(sock.getOutputStream());

            System.out.println("Connected to server " +
                      serverAddr.getHostName() +
                      " on port " + sock.getPort());

            new MultiChatReceive(this).start();

        } catch (IOException e) {
            System.out.println(e.getMessage() +
               ": Failed to connect to server.");
        }
    }


    protected void finalize() throws Throwable {
       try {
          if (remoteOut != null)
             remoteOut.close();
          if (sock != null)
             sock.close();
       } catch (IOException x) {
       }
       super.finalize();
    }

    class WindowExitHandler extends WindowAdapter {
      public void windowClosing(WindowEvent e) {
        Window w = e.getWindow();
        w.setVisible(false);
        w.dispose();
        System.exit(0);
      }
    }
    }
