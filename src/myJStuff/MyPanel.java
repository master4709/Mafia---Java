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
	
	protected int titleFontSize;
	protected int buttonFontSize;
	protected int textFontSize;
	protected int roleFontSize;
	protected int infoFontSize;
	
	protected static final int width = 480;
	protected static final int height = 850;
	
	protected EmptyBorder emptyBorder = new EmptyBorder(5, 5, 5, 5);
	
	/** This default constructor sets up the layout for the GUI **/
	public MyPanel(){
		
		setFont();
		setColor();
		setTheme();

		//Everything gets displayed on this panel 
		contentPane.setBorder(emptyBorder);
		//contentPane.setBounds(0,0,width,height);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		contentPane.setName("MyPanel --- Rename your Panel");
		
		//These panels are what all JLabels, buttons etc. are added to
		north = new JPanel();
		contentPane.add(north, BorderLayout.NORTH);
		north.setLayout(new MigLayout("", "[grow,center]", "[]"));
		
		//Used as a side buffer with the screen edge for the center panel
		west = new JPanel();
		contentPane.add(west, BorderLayout.WEST);
		
		//Used as a side buffer with the screen edge for the center panel
		east = new JPanel();
		contentPane.add(east, BorderLayout.EAST);
		
		center = new JPanel();
		contentPane.add(center, BorderLayout.CENTER);
		center.setLayout(new MigLayout("", "[grow]", "[]"));
		
		
		south = new JPanel();
		contentPane.add(south, BorderLayout.SOUTH);
		south.setLayout(new MigLayout("", "[][grow]", "[]"));
		
		setBackground(Colors.defaultBackgroundColor);
	}
	
	/** Sets the default font **/
	private void setFont(){
		titleFontSize = 65;
		textFontSize = 25;
		infoFontSize = 20;
		buttonFontSize = 30;
		roleFontSize = 40;
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
	 * @param max - Maximum Int size for the lbl or button
	 * @param distance - Int value of space from edge of screen for text
	 * @return int -  value of font size for lbl or button
	 */
	public static int setFontSize(String text, int max, int distance){
		int font = 5;
		
		AffineTransform affinetransform = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
		int textWidth = (int)(new MyFont(font).getStringBounds(text, frc).getWidth());
		
		while(textWidth<width-distance && font<max){
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