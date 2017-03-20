package myJStuff;

/**
 * 
 * 
 */
import java.awt.Color;

import javax.swing.JLabel;

/**
 * Default layout for labels
 * @author Elvin Limpin 30018832
 *
 */

public class MyLabel extends JLabel{
	
	/**
	 * These constructors have a flexible set of parameters
	 * to ensure that any instance would follow default values
	 * if not specified
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
