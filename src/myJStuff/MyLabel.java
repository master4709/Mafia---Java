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
		setForeground(Colors.white);
		setFont(font);
	}

	public MyLabel(String string) {
		this(string, Colors.white, new MyFont(25));
	}

	public MyLabel(String string, int i) {
		this(string, Colors.white, new MyFont(i));
	}
}
