package myJStuff;

/**
 * Default font with the size as a parameter
 * @author Elvin Limpin 30018832
 */

import java.awt.Font;

public class MyFont extends Font{
	private static final long serialVersionUID = 1L;
	
	public MyFont(int size) {
		super("Georgia", Font.PLAIN, size);
	}
}