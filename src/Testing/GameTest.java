package Testing;

import displayGame.GameController;

public class GameTest {
	public static void run(){
		GameController test = new GameController(Test.init());
		test.start(Test.makeInfo(), Test.getLynchTargetID());
	}
}