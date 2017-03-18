package logic;
import displayMain.MainController;
/**
 * This class runs the entire game
 * @authors Christilyn, Elvin, Masha, Pierce, Ronelle 
 */
public class RunMafia {
	
	public static void main(String[] args){
		MainController.createInstance();
		MainController.getInstance().start();
	}
}
