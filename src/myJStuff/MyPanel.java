package myJStuff;

/**
 * The template for panels. A MigLayout is used
 * 
 * @author Elvin Limpin 30018832
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

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
	
	protected EmptyBorder emptyBorder = new EmptyBorder(5, 5, 5, 5);
	
	/** This default constructor sets up the layout for the GUI **/
	public MyPanel(){
		
		setFont();
		setColor();
		setTheme();

		//Everything gets displayed on this panel 
		contentPane.setBorder(emptyBorder);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		contentPane.setName("MyPanel --- Rename your Panel");
		
		//These panels are what all JLabels, buttons etc. are added to
		north = new JPanel();
		contentPane.add(north, BorderLayout.NORTH);
		north.setLayout(new MigLayout("gap rel 0", "[grow,center]", "[]"));
		
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
	protected void setBackground(Color c){
		north.setBackground(c);
		south.setBackground(c);
		east.setBackground(c);
		west.setBackground(c);
		center.setBackground(c);
		contentPane.setBackground(c);
	}
	
	/** Sets the default theme **/
	protected void setTheme(){
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
	
	/**
	 * Sets the font size for the current lbl or button
	 * Ensures that the button does not proceed the size of the screen
	 * @param text - String displayed on lbl or button
	 * @param screen - Int value of space from edge of screen for text
	 * @param max - Maximum Int size for the lbl or button
	 * @return int -  value of font size for lbl or button
	 */
	protected int setFont(String text, int screen, int max){
		int font = 10;
		
		AffineTransform affinetransform = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
		int textWidth = (int)(new MyFont(font).getStringBounds(text, frc).getWidth());
		
		int screenWidth = 480;
		while(textWidth<screenWidth-screen && font<max){
			font++;
			textWidth = (int)(new MyFont(font).getStringBounds(text, frc).getWidth());
		}
		return font;
	}
	
	/** returns the content pane */
	public JPanel getContentPane(){
		return contentPane;
	}
}