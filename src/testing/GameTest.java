package testing;

import java.awt.event.ActionListener;
import java.util.List;

/**
 * Creates a test scenario for the game module
 * @author Elvin Limpin 30018832
 */

import displayGame.GameController;
import logic.Player;

public class GameTest {
	
	private static int playerTotal = 5;
	
	private static ActionListener a;
	
	public static void run(){
		System.out.println("Testing not working sorry");
		// For parameters hanged, edit Testing Tools
		//GameController.createInstance(TestingTools.init());
		TestingTools t = new TestingTools();
		List<Player> playerInfo = t.makeInfo(playerTotal);
		
		GameController gc = new GameController(t.init(),a);
		gc.start(playerInfo, -1);
	//	GameController.getInstance().start(TestingTools.makeInfo(playerTotal), TestingTools.getLynchTargetID(), false);
	}
}