import java.awt.*;
import java.awt.event.*;

// Driver class
public class Dial2	{

	public static void main(String[] args)	{
		
		Frame frame = new DialFrame2("Dial2");
		frame.pack();
		frame.setVisible(true);
	}
}

// Frame class
class DialFrame2 extends Frame	{
	
	private TextField phoneNumber = new TextField();
	private String pastNumbers[] = new String[10];
	private int pastNumberCount = 0;
	private int pastNumberIndex = 0;
	private int arrowIndex = 0;

	// Constructor
	public DialFrame2(String title)	{
		
		super(title);	// Set title

		// set all pastNumbers to empty string
		for (int x = 0; x < pastNumbers.length; x++)
		pastNumbers[x] = "";

		// Make text field non-editable and put at top of frame
		phoneNumber.setEditable(false);
		add("North", phoneNumber);

		// Create panel to hold buttons and set its layout
		Panel buttonPanel = new Panel();
		buttonPanel.setLayout(new GridLayout(4, 3, 10, 10));

		DigitListener dl = new DigitListener();
		ArrowListener arrl = new ArrowListener();
	
		// Create first nine buttons, attach a listener to each, and add buttons to buttonPanel
		for (int i = 1; i <= 9; i++)	{
			Button b = new Button(i + "");
			b.addActionListener(dl);
			buttonPanel.add(b);
		}

		// **1** Add your two new buttons to your panel, along with an arrow listener for both
		Button b = new Button(">");
		buttonPanel.add(b);
		b.addActionListener(arrl);

		b = new Button("0");
		buttonPanel.add(b);

		b = new Button("<");
		buttonPanel.add(b);
		b.addActionListener(arrl);

		// Create a panel to hold buttonPanel; put it at the centre of the frame
		Panel centerPanel = new Panel();
		centerPanel.add(buttonPanel);
		add("Center", centerPanel);

		// Create a panel to hold the "Dial" button; put it at the bottom of the frame
		Panel bottomPanel = new Panel();
		b = new Button("Dial");
		b.addActionListener(new DialListener());
		bottomPanel.add(b);
		add("South", bottomPanel);

		// Attach window listener
		addWindowListener(new WindowCloser());
	}

	private void addPastNumber(String num)	{
		boolean numberAlreadyAdded = false;
		for (int x=0; x < pastNumbers.length; x++)	{
			if (pastNumbers[x].equals(num))	{
				numberAlreadyAdded = true;  break;
			}
		}
		if (!numberAlreadyAdded)	{
			pastNumbers[pastNumberIndex] = num;
			pastNumberIndex++;
			if (pastNumberCount < 10) pastNumberCount++;
			if (pastNumberIndex == pastNumbers.length) pastNumberIndex = 0;
		}
		arrowIndex = pastNumberIndex;
	}	  

	// Listener for all buttons
	class DigitListener implements ActionListener	{
		public void actionPerformed(ActionEvent evt)	{
			String buttonLabel = evt.getActionCommand();
			// append the digit on the newly pressed key
			phoneNumber.setText(phoneNumber.getText() + buttonLabel);
		}
	}

	// Listener for all buttons
	class DialListener implements ActionListener	{
		public void actionPerformed(ActionEvent evt)	{
			addPastNumber(phoneNumber.getText());
			phoneNumber.setText("Dialling...");
			// pause for two seconds, or 2000 millisecond  
			try {
				Thread.sleep(2000);
			} 
			catch (InterruptedException e)	{
			}
			phoneNumber.setText("");
		}
	}

	// Listener for "arrow" buttons
	class ArrowListener implements ActionListener	{
		public void actionPerformed(ActionEvent evt)	{
			// ** 2 **
			String buttonLabel = evt.getActionCommand();

			if(buttonLabel.equals("<"))	{
				arrowIndex = (arrowIndex + pastNumberCount - 1) % pastNumberCount;
				phoneNumber.setText(pastNumbers[arrowIndex]);
			}
			else	{
				arrowIndex = (arrowIndex + 1) % pastNumberCount;
				phoneNumber.setText(pastNumbers[arrowIndex]);
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