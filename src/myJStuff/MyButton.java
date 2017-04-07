package myJStuff;
/**
 * Default button
 * @author Elvin Limpin 30018832
 */
import java.awt.Color;

import javax.swing.JButton;

public class MyButton extends JButton{
	/**
	 * These constructors have a flexible set of parameters
	 * to ensure that any instance would follow default values
	 * if not specified
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Custom constructor for buttons that comes
	 * with the default font
	 * @param text
	 * @param foreground
	 * @param background
	 * @param i
	 */
	public MyButton(String text, Color foreground, Color background, int i){
		setText(text);
		setForeground(foreground);
		setBackground(background);
		setFont(new MyFont(i));
	}

	/**
	 * Default constructor for buttons
	 * @param text
	 */
	public MyButton(String text){
		setText(text);
		setForeground(Colors.defaultButtonTextColor);
		setBackground(Colors.defaultButtonBackgroundColor);
		setFont(new MyFont(30));
	}

	/**
	 * Constructor that takes custom text and text-size
	 * @param text
	 * @param size
	 */
	public MyButton(String text, int size){
		setText(text);
		setForeground(Colors.defaultButtonTextColor);
		setBackground(Colors.defaultButtonBackgroundColor);
		setFont(new MyFont(size));
	}
	
	/**
	 * Alternative custom constructor that takes
	 * a specific font
	 * @param text
	 * @param btnTxtColor
	 * @param btnBackgroundColor
	 * @param buttonFont
	 */
	public MyButton(String text, Color btnTxtColor, Color btnBackgroundColor, MyFont buttonFont) {
		setText(text);
		setForeground(btnTxtColor);
		setBackground(btnBackgroundColor);
		setFont(buttonFont);
	}
}