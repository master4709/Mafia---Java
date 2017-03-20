package logic;
import javax.swing.JFrame;

import displayMain.MainController;
/**
 * This class runs the entire game
 * @authors Christilyn, Elvin, Masha, Pierce, Ronelle 
 */
public class RunMafia {
	
	public static JFrame frame = new JFrame();
	
	public static void main(String[] args){
		makeFrame();
		MainController.createInstance(frame);
		MainController.getInstance().start();
	}
	
	private static void makeFrame(){
		int width = 480;
		int height = 852;
		frame.setBounds(550, 20, width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
