package myJStuff;
/**
 * Default button
 * @author Elvin Limpin 30018832
 */
import java.awt.Color;

import javax.swing.JButton;

public class MyButton extends JButton{
	/**
	 * These constructors have a flexible set of parameters
	 * to ensure that any instance would follow default values
	 * if not specified
	 */
	private static final long serialVersionUID = 1L;

	public MyButton(String text, Color foreground, Color background, int i){
		setText(text);
		setForeground(foreground);
		setBackground(background);
		setFont(new MyFont(i));
	}

	public MyButton(String text){
		setText(text);
		setForeground(Colors.defaultButtonTextColor);
		setBackground(Colors.defaultButtonBackgroundColor);
		setFont(new MyFont(30));
	}

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
