package chatClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.RemoteException;

public class TextActionHandler implements ActionListener {
RemoteException remotout;
	@Override
	public void actionPerformed(ActionEvent e) {
		 try {
	          Object sendText;
			// Send it.      
	          remotout.writeUTF(sendText.getText());         
	          // Clear it.
	          ((Object) sendText).setText("");           
	         } catch (IOException x) {
	           System.out.println(x.getMessage() + 
	             ": Connection to peer lost.");
	         }
	}
}
		// TODO Auto-generated method stub

	/*
	
		
	    class TextActionHandler implements ActionListener {
	      public void actionPerformed(ActionEvent e) {
	       
	      }
	    }
	}


	
	}

}*/
