package Testing;

import javax.swing.JFrame;

public abstract class Test {
	
	static int width;
	static int height;
	public static JFrame frame;
		
		public static JFrame init(){
			width = 480;
			height = 720;
			frame.setBounds(550, 20, width, height);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(false);
			frame.setVisible(false);
			frame.setTitle("Mafia - Test");
			return frame;
		}
}
