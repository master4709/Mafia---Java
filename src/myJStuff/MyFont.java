package myJStuff;


import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;

public class MyFont extends Font{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MyFont(int size, String ...str) {
		super("Times New Roman", Font.PLAIN, size);
		try{
			Font mafiaType = super.createFont(Font.TRUETYPE_FONT, getClass().getResource("../../data/Cinzel-Regular.ttf").openStream());
			GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
			genv.registerFont(mafiaType);
			mafiaType = mafiaType.deriveFont(size);
			}
		catch(IOException | FontFormatException | NullPointerException ex){
		    //
			}
	}
}
// createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/CUSTOMFONT-MEDIUM.TTF")); 