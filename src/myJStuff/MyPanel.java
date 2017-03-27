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

	
	protected ActionListener actionListener;
	
	// the following fields are inherited by Panels.
	// they might be overriden
	protected Color textColor;
	protected Color backgroundColor;
	protected Color selectColor;
	protected Color btnTxtColor;
	protected Color btnBackgroundColor;
	
	
	
	//Panels that are added to the content pane. All JObjects get added to these panels
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
	
	/**
	 * This default constructor sets up the layout for the GUI
	 * 
	 */
	public MyPanel(){
		
		setFont();
		setColor();
		setTheme();

		//Everything gets displayed on this panel 
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
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
		
		//Once panels are set, background color is then set
		setBackground();
	}
	
	// Default sizes for certain font templates
	private void setFont(){
		titleFont = 65;
		textFont = 25;
		infoFont = 20;
		buttonFont = 30;
		roleFont = 40;
	}
	
	// Defaullt colors for certain components
	private void setColor(){
		textColor = Colors.black;
		backgroundColor = Colors.defaultBackgroundColor;
		selectColor = Colors.blue;
		btnTxtColor = Colors.defaultButtonTextColor;
		btnBackgroundColor = Colors.defaultButtonBackgroundColor;
	}
	
	// Ensure the background color for all panels are black
	public void setBackground(){
		north.setBackground(Colors.black);
		south.setBackground(Colors.black);
		east.setBackground(Colors.black);
		west.setBackground(Colors.black);
		center.setBackground(Colors.black);
		//Creates a black border on the screen
		contentPane.setBackground(Colors.black);
	}
	
	public void setTheme(){
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		   System.out.println("Not Found");
		}
	}
}
