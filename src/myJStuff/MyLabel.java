package myJStuff;

/**
 * 
 * 
 */
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class MyLabel extends JLabel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyLabel(
			String text,
			Color color,
			Font font){
		
		setText(text);
		setForeground(new Color(250,250,250));
		setFont(font);
	}
}
