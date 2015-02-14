package chatClient;

import java.io.DataInputStream;
import java.io.IOException;

/*
 * MultiChatReceive takes data sent on a socket and displays it in
 * a text area. This receives it from the network.
 */
class MultiChatReceive extends Thread {
    private MultiChat chat;

    MultiChatReceive(MultiChat chat) {
        this.chat = chat;
    }

    public synchronized void run() {
        String s;
        DataInputStream remoteIn = null;
        try {
            remoteIn = new DataInputStream(chat.sock.getInputStream());

            while (true) {
                s = remoteIn.readUTF();
                chat.receivedText.setText(s);    
            }

        } catch (IOException e) {
            System.out.println(e.getMessage() +
               ": Connection to peer lost.");

        } finally {
            try {
               if (remoteIn != null)
                  remoteIn.close();
            } catch (IOException x) {
            }
         }
    }
}



