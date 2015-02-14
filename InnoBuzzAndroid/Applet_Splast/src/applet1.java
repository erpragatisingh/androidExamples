/*import java.awt.*;
import java.awt.event.*;
class AppFrame extends Frame
{
public void paint(Graphics g)
{
g.drawString("Hello from Java!", 60, 100);
}
}
public class applet15
{
public static void main(String [] args)
{
AppFrame f = new AppFrame();
f.setSize(200, 200);
f.addWindowListener(new WindowAdapter() {public void
windowClosing(WindowEvent e) {System.exit(0);}});
f.setVisible(true);
}
}
*/
/*
import java.awt.*;
import java.awt.event.*;
public class applet14 extends Frame implements ItemListener
{
	private Choice choice1;
    Label label1 = new Label("Make a choice");
    applet14()
    {
super("Sample Choice");
add(label1, BorderLayout.SOUTH);
choice1 = new Choice();
choice1.addItem("One");
choice1.addItem("Two");
choice1.addItem("Three");
choice1.addItemListener(this);
add(choice1, BorderLayout.CENTER);
pack();
setSize(170,getPreferredSize().height);
setLocation(25,25);
setVisible(true);
    }
    public void itemStateChanged(ItemEvent ie)
    {
	String state = "Deselected";
if(ie.getStateChange() == ItemEvent.SELECTED)
state = "Selected";
label1.setText(ie.getItem() + " " + state );
}

public static void main(String args[]) { 
new applet14(); 
}
}
*/
import java.applet.Applet;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
/*
<APPLET
CODE=applet3.class
WIDTH=200
HEIGHT=200 >
</APPLET>
*/
public class applet1 extends Applet implements FocusListener, MouseWheelListener
{
TextArea text1;
Button button1, button2, button3;
public void init()
{
text1 = new TextArea("",8,18);
add(text1);
button1 = new Button("Press Tab1!");
button2 = new Button("Press Tab2!");
button3 = new Button("Press Tab3!");
add(button1);
add(button2);
add(button3);
button1.addFocusListener(this);
button2.addFocusListener(this);
button3.addFocusListener(this);
button1.setFocusable(false);
button2.requestFocusInWindow();
text1.addMouseWheelListener(this);
}
public void focusGained(FocusEvent event)
{
String msg = new String ("Focus is on Button2!");
if(event.getSource() == button2){
text1.setText(msg);
transferFocusUpCycle();
}
if(event.getSource() == button3){
transferFocusBackward();
}
}
public void focusLost(FocusEvent e) {}
public void mouseWheelMoved(MouseWheelEvent e) {}
}

