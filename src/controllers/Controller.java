package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;

import util.LoadFileUtil;

/**
 * Controller switches between the main menu, game setup,
 * and game cycle.
 * This controller facilitates both the default gameplay
 * and test gameplay.
 * 
 * Game File IO is also handled by this controller
 * @author Pierce de Jong 30006609
 *
 */
public class Controller implements ActionListener{
	
	private JFrame frame;
	
	private LoadFileUtil fu;
	
	private MainController mc;
	private SetUpController suc;
	private GameController gc;
	
	public Controller(){
		createFrame();
	}
	
	/** creates the controllers and file IO utility */
	public void run(){
		mc = new MainController(frame,this);
		suc = new SetUpController(frame,this);
		gc = new GameController(frame,this);
		
		fu = new LoadFileUtil();
		
		mc.start();
	}
	
	/** algorithm to create the frame facilitating all panels */
	private void createFrame(){
		//When changing theses must also change the values in setFont() in NightPanel
		int width = 480;
		int height = 850;
		frame = new JFrame();
		frame.setBounds(500, 20, width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	/**
	 * Action listener that switches panels based on the button clicked
	 * @param e - Action Event
	 * */
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton)e.getSource();
		String name = source.getName();
		
		switch(name){
		
		/** initializes a new game */
		case"NewGame_MainPanel":
			/** Starts the SetUpController */
			suc.start();
			break;
		/** loads the save file */
		case"ContinueGame_MainPanel":
			fu.loadFile();
			//Starts the game with save file info
			gc.start(fu.getPlayerInfo(), fu.getLynchTarget(),false);
			break;
		/** returns to home */
		case"Home":
			mc.start();
			break;
		/** ends set up phase and continues to the game cycle */
		case"Continue_RoleSelectionPanel":
			fu.newFile(suc.getPlayerNames(),suc.getRoles());
			gc.start(fu.getPlayerInfo(), fu.getLynchTarget(),false);
			break;
		/** initializes a test game, skipping the set up phase */
		case"Testing_MainPanel":
			test();
			break;
		default:break;
		}
	}
	
	/** Test utility to skip the set up phase to test the game */
	private void test(){
		
		/** twelve arbitrary names */
		List<String> names = new ArrayList<>(Arrays.asList(
				"Pierce","Mahsa","Christilyn","Elvin","Ronelle","Harry",
				"Ron","Hermione","Tom Riddle","Albus D","Captain Jack","Sparkey Sparkey Boom Man"));
		
		/** initaizlies all possible roles for easy test troubleshoot */
		List<String> roles = new ArrayList<>(Arrays.asList(
				"Doctor","Detective","Mafia: Hitman","Town","Bodyguard","Survivor",
				"Mafia: Barman","Lyncher","Mafia- GodFather","Vigilante","Prostitute","Mafia: Goon"));
		
		fu.newFile(names,roles);
		gc.start(fu.getPlayerInfo(),fu.getLynchTarget(),true);
	}
}