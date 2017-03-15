package displayMain;

import myJStuff.*;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;



import net.miginfocom.swing.MigLayout;

public class MainPanel{
	
	private Font titleFont;
	private Font buttonFont;
	
	private Color textColor;
	private Color btnBackgroundColor;
	private Color backgroundColor;
	
	private JPanel contentPane;
	private JPanel north;
	private JPanel west;
	private JPanel east;
	private JPanel south;
	private JPanel center;
	
	private JButton btnNewGame;
	private JButton btnContinueGame;
	private JButton btnAbout;
	private JButton btnRules;
	private JLabel lblMan;

	/**
	 * Create the application.
	 */
	public MainPanel() {
		initialize();
	}

	/**
	 * Initialize the contents of the 
	 */
	private void initialize() {
		
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
	
	public void displayTop(){
		JLabel lblTitle = new MyLabel("Mafia", textColor, titleFont);
		north.add(lblTitle, "cell 0 0,alignx center");
		
		ImageIcon icon = new ImageIcon("data/pictures/man.png");
		lblMan = new JLabel(icon);
		north.add(lblMan, "cell 1 0");
	}

	public void displayLeft(){
		
	}
	
	public void displayRight(){
		
	}
	
	public void displayBottom(){
		
	}
	
	public void displayCenter(){
		btnNewGame = new MyButton("New Game", textColor, btnBackgroundColor, buttonFont);
		center.add(btnNewGame, "cell 0 0,growx");
		btnNewGame.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			MainController.getInstance().switchToGame();
    	}});
		
		btnContinueGame = new MyButton("Continue Game", textColor, btnBackgroundColor, buttonFont);
		center.add(btnContinueGame, "cell 0 1,growx");
		btnContinueGame.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
				
    	}});
		
		btnRules = new MyButton("Rules", textColor, btnBackgroundColor, buttonFont);;
		center.add(btnRules, "cell 0 2,growx");
		btnRules.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			MainController.getInstance().switchRule();
		}});
		
		btnAbout = new MyButton("About", textColor, btnBackgroundColor, buttonFont);
		center.add(btnAbout, "cell 0 3,growx");
		btnAbout.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			MainController.getInstance().switchAbout();
		}});
		
	}
	
	private void setBackground(Color c){
		north.setBackground(c);
		south.setBackground(c);
		east.setBackground(c);
		west.setBackground(c);
		center.setBackground(c);
	}
	
	private void setFont(){
		titleFont = new MyFont(100);
		buttonFont = new MyFont(50);
	}
	
	private void setColor(){
		textColor = Colors.black;
		btnBackgroundColor = Colors.white;
		backgroundColor = Colors.grey;
	}
	
	public JPanel getContentPane(){
		return contentPane;
	}
}

