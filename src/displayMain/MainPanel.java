package displayMain;

import myJStuff.*;

import java.awt.Font;
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
 * 
 * @author
 *
 */
public class MainPanel{
	
	private Font titleFont;
	
	private Color textColor;
	private Color backgroundColor;
	
	private JPanel contentPane;
	private JPanel north;
	private JPanel west;
	private JPanel east;
	private JPanel south;
	private JPanel center;
	
	private JButton btnNewGame;
	private JButton btnDebug;
	private JButton btnContinueGame;
	private JButton btnAbout;
	private JButton btnRules;
	private JLabel lblMan;

	/**
	 * Create the application.
	 */
	public MainPanel() {

		setFont();
		setColor();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
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
		
		displayTop();
		displayLeft();
		displayRight();
		displayBottom();
		displayCenter();
		
		setBackground(backgroundColor);
	}

	/**
	 * Initialize the contents of the 
	 */
	
	private void displayTop(){
		//TODO img = ImageIO.read(new File("strawberry.jpg"));
		ImageIcon icon = new ImageIcon("data/pictures/mafia.png");
		lblMan = new JLabel(icon);
		//north.add(lblMan, "cell 0 0, alignx center");
	}

	private void displayLeft(){
		
	}
	
	private void displayRight(){
		
	}
	
	private void displayBottom(){
		btnDebug = new MyButton("Debug is " + Debug.amOn());
		south.add(btnDebug, "cell 0 0 ,growx");
		btnDebug.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Debug.toggle();
				btnDebug.setText("Debug is " + Debug.amOn());
			}
		});
	}
	
	private void displayCenter(){
		btnNewGame = new MyButton("New Game");
		center.add(btnNewGame, "cell 0 1,growx");
		btnNewGame.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			MainController.getInstance().switchToSetUp();
    	}});
		
		btnContinueGame = new MyButton("Continue Game");
		center.add(btnContinueGame, "cell 0 2,growx");
		btnContinueGame.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
				
    	}});
		
		btnRules = new MyButton("Rules");
		center.add(btnRules, "cell 0 3,growx");
		btnRules.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			MainController.getInstance().switchRule();
		}});
		
		btnAbout = new MyButton("About");
		center.add(btnAbout, "cell 0 4,growx");
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
	
	private void setFont(){
		titleFont = new MyFont(50);
	}
	
	private void setColor(){
		textColor = Colors.black;
		backgroundColor = Colors.defaultBackgroundColor;
	}
	
	public JPanel getContentPane(){
		return contentPane;
	}
}

