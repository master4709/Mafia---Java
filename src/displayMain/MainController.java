package displayMain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * This class controls 3 panels which are related to the main menu. 
 * The panels are: AboutPanel, RulePanel and MainPanel. 
 * This class will be called first in Controller to begin the game. 
 * 
 * @author Mahsa Lotfi 10072013
 * 
 */

public class MainController implements ActionListener {
	/**
	 * Instance variables
	 */
	
	private ActionListener globalListener;
	
	private JFrame frame;
	
	private MainPanel mp;
	private RulePanel rp;
	private AboutPanel ap;
	
	private JPanel panelMain;
	private JPanel panelRule;
	private JPanel panelAbout;	
	
	/**
	 * Constructor with two arguments.
	 * This constructor will initialize the frame and actionListener
	 *  and set the bounds.
	 * @param frame 
	 * @param globalListener
	 */
	public MainController(JFrame frame, ActionListener globalListener){
		//listener = new Listener();
		this.globalListener = globalListener;		
		//setting up the frame 
		this.frame = frame;
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setResizable(false);
		this.frame.setVisible(true);
	}
	
	
	
	/**
	 * This method will initialize all the panels 
	 * related to the main menu.
	 */
	public void start(){
		mp = new MainPanel(this,globalListener);
		rp = new RulePanel(this);
		ap = new AboutPanel(this);
		
		panelMain = mp.getContentPane();
		panelRule = rp.getContentPane();
		panelAbout = ap.getContentPane();
					
		switchPanel(panelMain);
	}
	
	/**
	 * This method will switch the frame to the passed JPanel
	 * Sets current content pane to invisible
	 * Sets the frame to the new JPanel
	 * Sets new content pane to visible
	 * @param panel
	 */
	private void switchPanel(JPanel panel){
		//System.out.println(panel.getName());
		frame.getContentPane().setVisible(false);
		frame.setContentPane(panel);
		frame.getContentPane().setVisible(true);
	}
	
	
	/**
	 * This method will assign an action to each button in the displayMain Package
	 * When a button is pressed, the name String of the button is stored as a local variable
	 * A switch statement is used to compare the name with other string values 
	 * to find the correct button.
	 * @param e, ActionEvent e
	 */
	public void actionPerformed(ActionEvent e){
		//Gets the name (NOT TEXT) of the button that was pressed
		JButton source = (JButton)e.getSource();
		String name = source.getName();
		//Finds the button that was pressed and does the needed commands
		switch(name){
		case "Back_RulePanel":
			switchPanel(panelMain); 
			break;
		case "Back_AboutPanel":
			switchPanel(panelMain); 
			break;
		case "Rule_MainPanel":
			switchPanel(panelRule); 
			break;
		case "About_MainPanel":
			switchPanel(panelAbout); 
			break;
		case "Test_MainPanel":
			//switchTest();
		default:
			break;
		}
       
	}
    
}
