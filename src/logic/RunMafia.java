package logic;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import displayMain.MainController;
/**
 * This class runs the entire game
 * @authors Team 18: Christilyn, Elvin, Masha, Pierce, Ronelle 
 */
public class RunMafia extends Debug {
	
	public static JFrame frame = new JFrame();
	
	public static void main(String[] args){
		makeFrame();
		MainController.createInstance(frame);
		MainController.getInstance().start();
	}
	
	private static void makeFrame(){
		int width = 480;
		int height = 852;
		Image image = new ImageIcon("../../data/icon.png").getImage();
		try {
			frame.setIconImage(image);
		} catch(Exception e){ $("Image not found.");}

		frame.setBounds(550, 20, width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
