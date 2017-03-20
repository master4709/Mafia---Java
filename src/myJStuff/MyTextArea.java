package myJStuff;

import java.awt.Color;

import javax.swing.JTextArea;
/**
 * Default instance for a text area
 * @author Elvin Limpin 30018832
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
			int font){
		
		setText(text);
		setForeground(Colors.white);
		setBackground(backgroundColor);
		setFont(new MyFont(font));
	}
	
	
}
