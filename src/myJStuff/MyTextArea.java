package myJStuff;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextArea;
/**
 * 
 * @author Pierce de Jong 30006609
 *
 */
public class MyTextArea extends JTextArea{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyTextArea(
			String text,
			Color textColor,
			Color backgroundColor,
			Font font){
		
		setText(text);
		setForeground(textColor);
		setBackground(backgroundColor);
		setFont(font);
	}
}
