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
			int size){
		
		setText(text);
		setForeground(Colors.white);
		setFont(new MyFont(size));
	}

	public MyLabel(String string) {
		this(string, Colors.white, 25);
	}

	public MyLabel(String string, int i) {
		this(string, Colors.white, i);
	}
}
