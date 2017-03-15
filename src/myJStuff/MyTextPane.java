package myJStuff;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextPane;

public class MyTextPane extends JTextPane{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyTextPane(String text, Color textColor, Color backgroundColor, Font font){
		setText(text);
		setForeground(textColor);
		setBackground(backgroundColor);
		setFont(font);
	}
}
