package run;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;

import displayGame.GameController;
import displayMain.MainController;
import displaySetUp.SetUpController;
import logic.FileUtil;

public class Controller implements ActionListener{
	
	private JFrame frame = new JFrame();
	
	private FileUtil fu = new FileUtil();
	
	private MainController mc;
	private SetUpController suc;
	private GameController gc;
	
	public Controller(){
		setBounds();
	}
	
	public void run(){
		mc = new MainController(frame,this);
		suc = new SetUpController(frame,this);
		gc = new GameController(frame,this);
		
		mc.start();
	}
	
	private void setBounds(){
		//When changing theses must also change the values in setFont() in NightPanel
		int width = 480;
		int height = 852;
		frame.setBounds(550, 20, width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton)e.getSource();
		String name = source.getName();
		
		switch(name){
		case"NewGame_MainPanel":
			//Starts the SetUpController
			suc.start();
			break;
		case"ContinueGame_MainPanel":
			//Loads the save file
			fu.loadFile();
			//Starts the game with save file info
			gc.start(fu.getPlayerInfo(), fu.getLynchTarget(),false);
			break;
		case"Home":
			mc.start();
			break;
		case"Continue_RoleSelectionPanel":
			fu.newFile(suc.getPlayerNames(),suc.getRoles());
			gc.start(fu.getPlayerInfo(), fu.getLynchTarget(),false);
			break;
		case"Testing_MainPanel":
			test();
			break;
		default:
			break;
		}
	}
	
	private void test(){
		//12 players
		List<String> names = new ArrayList<>(Arrays.asList("Pierce","Mahsa","Christilyn","Elvin","Ronelle","Harry","Ron","Hermione","Tom Riddle","Albus D","Captain Jack","Sparkey Sparkey Boom Man"));
		List<String> roles = new ArrayList<>(Arrays.asList("Doctor","Detective","Mafia: Hitman","Town","Bodyguard","Survivor","Mafia: Barman","Lyncher","Mafia- GodFather","Vigilante","Town","Mafia: Goon"));
		fu.newFile(names,roles);
		gc.start(fu.getPlayerInfo(),fu.getLynchTarget(),true);
	}
}
