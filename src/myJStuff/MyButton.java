package myJStuff;

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
	public MyButton(
			String text,
			Color foreground,
			Color background,
			Font font){
		setText(text);
		setForeground(foreground);
		setBackground(background);
		setFont(font);
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
		setFont(new MyFont(40));
	}
}
