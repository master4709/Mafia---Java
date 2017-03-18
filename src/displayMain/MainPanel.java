package displayMain;

import myJStuff.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.Debug;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;



import net.miginfocom.swing.MigLayout;
/**
 * This class creates panel for Main menu of Mafia game by creating different panels and putting them together. 
 * Each panel contains buttons or labels. Each button is assigned to an action.
 * @author Mahsa Lotfi
 *
 */
public class MainPanel{
	/**
	 * Instance variables
	 */
	//background color instance variable
	private Color backgroundColor;
	
	//Panel that gets set to the frame and displays the contents of this class
	private JPanel contentPane;
	
	//Panels that are added to the content pane. All JObjects get added to these panels
	private JPanel north;
	private JPanel west;
	private JPanel east;
	private JPanel south;
	private JPanel center;
	
	//buttons for center panel
	private JButton btnNewGame;	
	private JButton btnContinueGame;
	private JButton btnAbout;
	private JButton btnRules;
	
	//Jlabel that contains background image
	private JLabel lblMan;
	
	//button for south panel
	private JButton btnDebug;

	/**
	 * Create the main panel frame.
	 */
	public MainPanel() {
		//setting color
		setColor();
		
		//creating content pane which holds all the panels together.
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(2, 2, 2, 2));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		//creating layout for each panels
		north = new JPanel();
		contentPane.add(north, BorderLayout.NORTH);
		north.setLayout(new MigLayout("", "[grow]", "[]"));
		
		west = new JPanel();
		contentPane.add(west, BorderLayout.WEST);
		
		east = new JPanel();
		contentPane.add(east, BorderLayout.EAST);
		
		south = new JPanel();
		contentPane.add(south, BorderLayout.SOUTH);
		
		center = new JPanel();
		contentPane.add(center, BorderLayout.CENTER);
		center.setLayout(new MigLayout("", "[grow]", "[][][][][]"));
		
		//displaying contents of each panels
		displayNorth();
		displaySouth();
		displayCenter();
		
		//setting background of each panel.
		setBackground(backgroundColor);
	}

	/**
	 * Method to display content of north panel, which is an image.
	 */
	
	private void displayNorth(){

		//TODO img = ImageIO.read(new File("strawberry.jpg"));
		//adding image to background
		ImageIcon icon = new ImageIcon("data/pictures/mafia.png");
		lblMan = new JLabel(icon);

		north.add(lblMan, "cell 0 0, alignx center");
		north.add(lblMan, "center");
	}

	
	/**
	 * Method to display contents of south panel. This panel contains the debug button.
	 */
	private void displaySouth(){
		btnDebug = new MyButton("Debug is " + Debug.amOn());
		south.add(btnDebug, "cell 0 0 ,growx");
		// setting the action for the button
		btnDebug.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Debug.toggle();
				btnDebug.setText("Debug is " + Debug.amOn());
			}
		});
	}
	
	/**
	 * Method to display content of center panel. It will set up buttons. 
	 */
	private void displayCenter(){
		btnNewGame = new MyButton("New Game");
		center.add(btnNewGame, "cell 0 1,growx");
		// setting the action for new game button
		btnNewGame.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			MainController.getInstance().switchToSetUp();
    	}});
		
		btnContinueGame = new MyButton("Continue Game");
		center.add(btnContinueGame, "cell 0 2,growx");
		// setting the action for continue game button
		btnContinueGame.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
				
    	}});
		
		btnRules = new MyButton("Rules");
		center.add(btnRules, "cell 0 3,growx");
		// setting the action for Rules button
		btnRules.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			MainController.getInstance().switchRule();
		}});
		
		btnAbout = new MyButton("About");
		center.add(btnAbout, "cell 0 4,growx");
		// setting the action for about button
		btnAbout.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			MainController.getInstance().switchAbout();
		}});
		
	}
	
	/**
	 * Sets all of the panels background to the passed Color
	 * Also creates a black border around the edge of the screen
	 * @param c
	 */
	private void setBackground(Color c){
		north.setBackground(c);
		south.setBackground(c);
		east.setBackground(c);
		west.setBackground(c);
		center.setBackground(c);
		//Creates a black border on the screen
		contentPane.setBackground(Colors.defaultBorderColor);
	}
	
	
	/**
	 * Method to set the background color.
	 */
	private void setColor(){
		backgroundColor = Colors.defaultBackgroundColor;
	}
	
	/**
	 * Getter method for the content pane.
	 */
	public JPanel getContentPane(){
		return contentPane;
	}
}

