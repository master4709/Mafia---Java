package Testing;

/**
 * Creates a test scenario for the game module
 * @author Elvin Limpin 30018832
 */

import displayGame.GameController;

public class GameTest {
	
	private static int playerTotal = 8;
	
	public static void run(){

		GameController.createInstance(TestingTools.init());
		GameController.getInstance().start(TestingTools.makeInfo(playerTotal), TestingTools.getLynchTargetID(), true);
	}
}