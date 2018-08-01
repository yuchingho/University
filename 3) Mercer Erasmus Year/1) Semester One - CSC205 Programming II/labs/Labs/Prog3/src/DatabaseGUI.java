import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class DatabaseGUI	{
	public static void main(String[] args)	{
		Frame window = new GUI("The Database");
		window.setSize(380, 550);
		window.setVisible(true);
		window.setResizable(false);
  	}
}

class GUI extends Frame	{
	
	private static Employee[] arrayEmployees = new Employee[1];
	private static int numberEmployees = 0;
	
	private static boolean buttonPressed = false;
	
	private static TextArea welcomeArea;
		private static TextField nameField;
		private static TextField hourlySalaryField;
		private static TextField annualSalaryField;
		private static TextField paycheckField;
		private static TextField raiseField;
	private static TextArea printArea;
	
	public static String name;
	public static Double hourlyPayDouble;
	public static String hourlyPayString;
	
	public GUI(String title)	{
		super(title);
		setLayout(new FlowLayout());
		addWindowListener(new WindowCloser());
		
		welcomeArea = new TextArea(3, 50);
		welcomeArea.setText("\t\t\t   Welcome to\n\n\t\t\tThe Database");
		welcomeArea.setEditable(false);
		add(welcomeArea);
		
		// Employee name
		nameField = new TextField(42);
		nameField.setText("Type in Employee's name");
		add(nameField);
		Button nameButton = new Button("Enter");
		add(nameButton);
		nameButton.addActionListener(new nameButtonListener());
		
		// Button for hourly pay
		Button hourlyPayButton = new Button("                  Hourly pay                  ");
		add(hourlyPayButton);
		hourlyPayButton.addActionListener(new hourlyButtonListener());
		
		// Button for salary pay
		Button annualPayButton = new Button("                  Annual pay                  ");
		add(annualPayButton);
		annualPayButton.addActionListener(new annualButtonListener());
		
		// Row for hourly 
		hourlySalaryField = new TextField(9);
		add(hourlySalaryField);
		Button hourlySalaryButton = new Button("Hourly salary");
		add(hourlySalaryButton);
		hourlySalaryButton.addActionListener(new hourlySalaryButtonListener());
		
		// Row for annual pay continued
		annualSalaryField = new TextField(9);
		add(annualSalaryField);
		Button annualSalaryButton = new Button("Annual salary");
		add(annualSalaryButton);
		annualSalaryButton.addActionListener(new annualSalaryButtonListener());
		
		// paycheck field
		paycheckField = new TextField(30);
		paycheckField.setText("Enter in number of hours");
		add(paycheckField);
		Button paycheckButton = new Button("Calculate paychecks");
		add(paycheckButton);
		paycheckButton.addActionListener(new paycheckButtonListener());
		
		// raise field
		raiseField = new TextField(34);
		raiseField.setText("Enter percentage increase");
		add(raiseField);
		Button raiseButton = new Button("Calculate raise");
		add(raiseButton);
		raiseButton.addActionListener(new raiseButtonListener());
		
		// download button
		Button downloadButton = new Button(" Download ");
		add(downloadButton);
		downloadButton.addActionListener(new downloadButtonListener());
		
		// upload button
		Button uploadButton = new Button("   Upload   ");
		add(uploadButton);
		uploadButton.addActionListener(new uploadButtonListener());
		
		// print button
		Button printButton = new Button("    Print    ");
		add(printButton);
		printButton.addActionListener(new printButtonListener());
		
		// quit button
		Button quitButton = new Button("    Quit     ");
		add(quitButton);
		quitButton.addActionListener(new quitButtonListener());
		
		printArea = new TextArea(17, 50);
		add(printArea);
	}
	
	class nameButtonListener implements ActionListener	{
		public void actionPerformed(ActionEvent evt)	{
			buttonPressed = true;
			newEmployee();
		}
	}
	
	class hourlyButtonListener implements ActionListener	{
		public void actionPerformed(ActionEvent evt)	{
			buttonPressed = true;
			annualSalaryField.setEditable(false);
		}
	}
	
	class annualButtonListener implements ActionListener	{
		public void actionPerformed(ActionEvent evt)	{
			buttonPressed = true;
			hourlySalaryField.setEditable(false);
		}
	}
	
	class hourlySalaryButtonListener implements ActionListener	{
		public void actionPerformed(ActionEvent evt)	{
			buttonPressed = true;
			hourlyPay(name);
		}
	}
	
	class annualSalaryButtonListener implements ActionListener	{
		public void actionPerformed(ActionEvent evt)	{
			buttonPressed = true;
			
		}
	}
	
	class paycheckButtonListener implements ActionListener	{
		public void actionPerformed(ActionEvent evt)	{
			buttonPressed = true;
			
		}
	}
	
	class raiseButtonListener implements ActionListener	{
		public void actionPerformed(ActionEvent evt)	{
			buttonPressed = true;
			
		}
	}
	
	class downloadButtonListener implements ActionListener	{
		public void actionPerformed(ActionEvent evt)	{
			buttonPressed = true;
			
		}
	}
	
	class uploadButtonListener implements ActionListener	{
		public void actionPerformed(ActionEvent evt)	{
			buttonPressed = true;
			
		}
	}
	
	class quitButtonListener implements ActionListener	{
		public void actionPerformed(ActionEvent evt)	{
			buttonPressed = true;
			printArea.setText("Exiting system...");
			timer();
			System.exit(0);
		}
	}
	
	class printButtonListener implements ActionListener	{
		public void actionPerformed(ActionEvent evt)	{
			buttonPressed = true;
			printArea.setText("Printing records...");
			print();
		}
	}

	public static void newEmployee()	{
		name = nameField.getText();
		printArea.setText(name);
	}
	
	public static void hourlyPay(String inputName)	{
		hourlyPayString = hourlySalaryField.getText();
		hourlyPayDouble = Double.parseDouble(hourlyPayString);
		printArea.setText(hourlyPayString);
	}
	
	public static void print()	{
		for (int i = 0; i < numberEmployees; i++)	{
			//printArea.setText(Arrays.toString(arrayEmployees[i]));
		}
	}

	public static void timer()	{
		try	{
			Thread.sleep(750);
		}
		catch (InterruptedException e)	{
		}
	}
	class WindowCloser extends WindowAdapter	{	 	
		public void windowClosing(WindowEvent evt)	{	
			System.exit(0);								
		}												
	}													
}