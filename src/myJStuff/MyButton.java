package myJStuff;
/**
 * 
 */
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class MyButton extends JButton{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor
	 * Creates a new JButton with the the passed values
	 *
	 * @param text, foreground, background, font
	 */
	public MyButton(String text, Color foreground, Color background, int i){
		setText(text);
		setForeground(foreground);
		setBackground(background);
		setFont(new MyFont(i));
	}
	/**
	 * Constructor
	 * Creates a JButton with the pre-set values for everything but text
	 *
	 * @param text
	 */
	public MyButton(String text){
		setText(text);
		setForeground(Colors.defaultButtonTextColor);
		setBackground(Colors.defaultButtonBackgroundColor);
		setFont(new MyFont(30));
	}
	/**
	 * Constructor
	 * 
	 *
	 * @param text, size
	 */
	public MyButton(String text, int size){
		setText(text);
		setForeground(Colors.defaultButtonTextColor);
		setBackground(Colors.defaultButtonBackgroundColor);
		setFont(new MyFont(size));
	}
	public MyButton(String text, Color btnTxtColor, Color btnBackgroundColor, MyFont buttonFont) {
		setText(text);
		setForeground(btnTxtColor);
		setBackground(btnBackgroundColor);
		setFont(buttonFont);
	}
}
