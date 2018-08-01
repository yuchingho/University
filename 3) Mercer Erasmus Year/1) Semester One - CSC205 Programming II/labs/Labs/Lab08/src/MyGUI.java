/*
 * Displays a frame containing a text field and a "Enter"
 * button. The content in the text field can be changed
 * by pressing the button.
 */ 

import java.awt.*;
import java.awt.event.*;

// Driver class
public class MyGUI	{

	public static void main(String[] args)	{
		Frame f = new MyGUIFrame("My GUI");
    	//f.setSize(200, 100);
    	f.pack();
    	f.setVisible(true);
  	}
}

// Frame class
class MyGUIFrame extends Frame	{
	private final String MESSAGE = "Welcome to My GUI.";
	private boolean isOn = true;
	private TextField tf;
	
	public MyGUIFrame(String title)	{
		super(title);
	
		setLayout(new FlowLayout());
	
		tf = new TextField(20);
		tf.setText(MESSAGE);
		tf.setEditable(false);
		add(tf);
		Button b = new Button("Enter");
		add(b);
	
		b.addActionListener(new ButtonListener());	// Button listener		
		addWindowListener(new WindowCloser());		// Window listener
	}

  // Listener for button
	class ButtonListener implements ActionListener	{
		public void actionPerformed(ActionEvent evt)	{
			if (isOn)	{
				isOn = false;
				tf.setText("");
			}
			else	{
				isOn = true;
				tf.setText(MESSAGE);
			}
		}
	}

  // Listener for window
	class WindowCloser extends WindowAdapter	{
		public void windowClosing(WindowEvent evt)	{
			System.exit(0);
		}
	}
}