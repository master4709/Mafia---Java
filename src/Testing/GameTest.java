package Testing;

import displayGame.GameController;

public class GameTest {
	public static void run(){
		GameController.createInstance(Test.init());
		GameController.getInstance().start(Test.makeInfo(), Test.getLynchTargetID(), true);
	}
}