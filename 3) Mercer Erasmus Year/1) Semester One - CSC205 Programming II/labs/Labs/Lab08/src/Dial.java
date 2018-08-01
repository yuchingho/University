import java.awt.*;
import java.awt.event.*;

// Driver class
public class Dial	{
	
	public static void main(String[] args)	{
		
		Frame frame = new DialFrame("Dial");
		frame.pack();
		frame.setVisible(true);
	}
}

// Frame class
class DialFrame extends Frame	{
	
	private TextField phoneNumber = new TextField();

	// Constructor
	public DialFrame(String title)	{	
		
		super(title);	// Set title

		// Make text field non-editable and put at top of frame
		phoneNumber.setEditable(false);
		add("North", phoneNumber);

		// Create panel to hold buttons and set its layout
		Panel buttonPanel = new Panel();
		buttonPanel.setLayout(new GridLayout(4, 3, 10, 10));

		// Create a single listener for all digit buttons **1**
		DigitListener dl = new DigitListener();
	
		// Create first nine buttons, attach a listener to each,and add buttons to buttonPanel
		for (int i = 1; i <= 9; i++)	{
			Button b = new Button(i + "");
			// add the button listener to each button **2**
			buttonPanel.add(b);
			b.addActionListener(dl);
		}

    	// Create last three buttons and add to buttonPanel. Only the "0" button will have a listener
    	buttonPanel.add(new Button("*"));
    	Button b = new Button("0");
    	buttonPanel.add(b);
    	// add the button listener to button '0' **3**
    	b.addActionListener(dl);
    	
    	buttonPanel.add(new Button("#"));
    	// Create a panel to hold buttonPanel; put it at the centre of the frame
    	Panel centerPanel = new Panel();
    	centerPanel.add(buttonPanel);
    	add("Center", centerPanel);

    	// Create a panel to hold the "Dial" button; put it at the bottom of the frame
    	Panel bottomPanel = new Panel();
    	b = new Button("Dial");
    	// add the dial listener to the "Dial" button **4**
    	b.addActionListener(new DialListener());

    	bottomPanel.add(b);
    	add("South", bottomPanel);

    	// Attach window listener
    	addWindowListener(new WindowCloser());
	}

	// Listener for all buttons
	class DigitListener implements ActionListener	{
		public void actionPerformed(ActionEvent evt)	{
			String buttonLabel = evt.getActionCommand();
			// append the digit on the newly pressed key to display **5**
			phoneNumber.setText(phoneNumber.getText() + buttonLabel);
		}
	}

	// Listener for all buttons
	class DialListener implements ActionListener	{
		public void actionPerformed(ActionEvent evt)	{
			String buttonLabel = evt.getActionCommand();
			// set display to "Dialling..." **6**
			// to let the "Dialling..." to display **7**
			phoneNumber.setText("Dialling...");
			// pause for two seconds, or 2000 millisecond  
			try	{
				Thread.sleep(2000);
			}
			catch (InterruptedException e)	{
			}
			// set display to an empty string **8**
			phoneNumber.setText("");
		}
	}

	// Listener for window
	class WindowCloser extends WindowAdapter	{
		public void windowClosing(WindowEvent evt)	{
			System.exit(0);
		}
	}
}