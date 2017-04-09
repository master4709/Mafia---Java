package myJStuff;

/**
 * The template for panels. A MigLayout is used
 * 
 * @author Elvin Limpin 30018832
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public abstract class MyPanel {

	protected ActionListener packageListener;
	
	// the following fields are inherited by Panels.
	// they might be overridden
	protected Color textColor;
	protected Color backgroundColor;
	protected Color selectColor;
	protected Color btnTxtColor;
	protected Color btnBackgroundColor;
	
	//Panels that are added to the content pane.
	//All JObjects get added to these panels
	protected JPanel contentPane = new JPanel();
	protected JPanel north;
	protected JPanel south;
	protected JPanel west;
	protected JPanel east;
	protected JPanel center;
	
	protected int titleFont;
	protected int buttonFont;
	protected int textFont;
	protected int roleFont;
	protected int infoFont;
	
	/** This default constructor sets up the layout for the GUI **/
	public MyPanel(){
		
		setFont();
		setColor();
		setTheme();

		//Everything gets displayed on this panel 
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		contentPane.setName("MyPanel --- Rename your Panel");
		
		//These panels are what all JLabels, buttons etc. are added to
		north = new JPanel();
		contentPane.add(north, BorderLayout.NORTH);
		north.setLayout(new MigLayout("", "[grow,center]", "[]"));
		
		south = new JPanel();
		contentPane.add(south, BorderLayout.SOUTH);
		south.setLayout(new MigLayout("", "[grow]", "[]"));
		
		west = new JPanel();
		contentPane.add(west, BorderLayout.WEST);
		
		east = new JPanel();
		contentPane.add(east, BorderLayout.EAST);
		
		center = new JPanel();
		contentPane.add(center, BorderLayout.CENTER);
		center.setLayout(new MigLayout("", "[grow]", "[]"));
		
		setBackground(Colors.defaultBackgroundColor);
	}
	
	/** Sets the default font **/
	private void setFont(){
		titleFont = 65;
		textFont = 25;
		infoFont = 20;
		buttonFont = 30;
		roleFont = 40;
	}
	
	/** Sets the default colors **/
	private void setColor(){
		textColor = Colors.black;
		backgroundColor = Colors.defaultBackgroundColor;
		selectColor = Colors.blue;
		btnTxtColor = Colors.defaultButtonTextColor;
		btnBackgroundColor = Colors.defaultButtonBackgroundColor;
	}
	
	/** Ensures that the panel background is black
	 * @param c - color black**/
	public void setBackground(Color c){
		north.setBackground(c);
		south.setBackground(c);
		east.setBackground(c);
		west.setBackground(c);
		center.setBackground(c);
		//Creates a black border on the screen
		contentPane.setBackground(c);
	}
	
	/** Sets the default theme **/
	public void setTheme(){
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		   System.out.println("Nimbus theme is not found.");
		   e.printStackTrace();
		}
	}
	
	/** returns the content pane */
	public JPanel getContentPane(){
		return contentPane;
	}
}