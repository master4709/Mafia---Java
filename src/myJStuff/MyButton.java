package myJStuff;


import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class MyButton extends JButton{
	JButton lmao = new JButton();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyButton(String text, Color foreground, Color background, Font font){
		setText(text);
		setForeground(foreground);
		setBackground(background);
		setFont(font);
	}
}
