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
import logic.SetUp;

public class Controller implements ActionListener{
	
	private JFrame frame = new JFrame();
	
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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton)e.getSource();
		String name = source.getName();
		
		switch(name){
		case"NewGame_MainPanel":
			suc.start();
			break;
		case"Home_DayPanel":
			mc.start();
			break;
		case"Continue_RoleSelectionPanel":
			SetUp up = new SetUp(suc.getPlayerNames(),suc.getRoles());
			gc.start(up.getPlayerInfo(), up.getLynchTarget(),false);
			break;
		case"Testing_MainPanel":
			test();
		default:
			break;
		}
		
	}
	
	private void test(){
		List<String> names = new ArrayList<>(Arrays.asList(
				"Pierce","Mahsa","Christilyn","Elvin","Ronelle",
				"Nathaly","Nick","Tai Lopez","Kevin O'Leary","Bill&Melinda Gates"));
		List<String> roles = new ArrayList<>(Arrays.asList("Doctor","Detective","Mafia: Hitman","Town","Town","Survivor","Mafia: Barman","Lyncher","Mafia- Godfather","Vigilante"));
		SetUp up = new SetUp(names,roles);
		gc.start(up.getPlayerInfo(), 0,true);
	}
}
