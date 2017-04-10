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
import logic.LoadFile;
import logic.NewFile;

public class Controller implements ActionListener{
	
	private JFrame frame = new JFrame();
	
	private MainController mc;
	private SetUpController suc;
	private GameController gc;
	
	private NewFile nf;
	private LoadFile lf;
	
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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton)e.getSource();
		String name = source.getName();
		
		switch(name){
		case"NewGame_MainPanel":
			suc.start();
			break;
		case"ContinueGame_MainPanel":
			lf = new LoadFile();
			lf.scan();
			gc.start(lf.getPlayers(), lf.getLynchTarget(),1,false);
			break;
		case"Home":
			mc.start();
			break;
		case"Continue_RoleSelectionPanel":
			nf = new NewFile(suc.getPlayerNames(),suc.getRoles());
			gc.start(nf.getPlayerInfo(), nf.getLynchTarget(),1,false);
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
		List<String> names = new ArrayList<>(Arrays.asList("Pierce","Mahsa","Christilyn","Elvin","Ronelle","Harry","Ron","Hermione","Tom Riddle","Albus D","Captain Jack","Frodo"));
		List<String> roles = new ArrayList<>(Arrays.asList("Doctor","Detective","Mafia: Hitman","Town","Bodyguard","Survivor","Mafia: Barman","Lyncher","Mafia- Godfather","Vigilante","Town","Mafia: Goon"));
		nf = new NewFile(names,roles);
		gc.start(nf.getPlayerInfo(),nf.getLynchTarget(),1,true);
	}
}
