package displayGame;

import logic.*;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameController {
	
	private static GameController instance = null;
	
	private JFrame frame;
	
	private Game g;
	private DayPanel dd;
	private NightPanel nd;
	private CheckPlayerPanel cpd;
	//All of the possible panels to be displayed on the frame
	private JPanel panelDay;
	private JPanel panelNight;
	private JPanel panelCheck;
	
	private int position = 0;
	
	private GameController(JFrame frame, List<Player> playerInfo, List<String> mafiaMembers, int lynchTarget){
		this.frame = frame;
		g = new Game(playerInfo,mafiaMembers,lynchTarget);
		start();
	}
	
	public static void createInstance(JFrame frame, List<Player> playerInfo, List<String> mafiaMembers, int lynchTarget){
		if(instance==null){
			instance = new GameController(frame,playerInfo,mafiaMembers,lynchTarget);
		}
	}
	
	public static GameController getInstance(){
		return instance;
	}
	
	public void start(){
		cpd = new CheckPlayerPanel();
		
		dd = new DayPanel(g.getPlayerInfo());
		panelDay = dd.getContentPane();
		setUpScreen();
	}
	
	public void setUpScreen(){
		//Sets current content pane to invisible
		frame.getContentPane().setVisible(false);
		//sets the frame's content pane to day screen
		frame.setContentPane(panelDay);
		//sets the current content pane to visible
		panelDay.setVisible(true);
	}
	
	/**
	 * switches the content panel to the dayCycle page
	 */
	public void switchDayCycle(){
		g.nightAction();
		frame.getContentPane().setVisible(false);
		dd = new DayPanel(g.getPlayerInfo());
		panelDay = dd.getContentPane();
		frame.setContentPane(panelDay);
		panelDay.setVisible(true);
	}
	
	public void switchNightCycle(int target){
		g.dayCycle(target);
		frame.getContentPane().setVisible(false);
		position = 0;
		nd = new NightPanel(g.getPlayerInfo(),g.getMafiaMember());
		for(int k=position;k<g.getPlayerInfo().size();k++){
			if(!g.getPlayerInfo().get(k).isDead()){
				position = k;
				checkPlayer();
				break;
			}
		}
	}
	
	/**
	 * finds the next alive player in the list and displays screen asking who they are
	 * once all players have gone goes to dayCycle 
	 * @param position
	 */
	public void rotateNightPlayer(int target){

		g.getPlayerInfo().get(position).setPlayerTarget(target);
		position++;
		if(position<g.getPlayerInfo().size()&& !g.getPlayerInfo().get(position).isDead()){
			checkPlayer();
		}else if(position==g.getPlayerInfo().size()){
			for(int m=0;m<g.getPlayerInfo().size();m++){
				System.out.println(g.getPlayerInfo().get(m).getName()+"|"+g.getPlayerInfo().get(m).getPlayerTarget());
			}
			switchDayCycle();
		}
		else{
			for(int k=position;k<g.getPlayerInfo().size();k++){
				if(!g.getPlayerInfo().get(k).isDead()){
					position = k;
					checkPlayer();
					break;
				}
			}
		}
	}
	
	public void setNightPlayer(){
		nd.setDisplay(position);
		frame.getContentPane().setVisible(false);
		panelNight = nd.getContentPane();
		frame.setContentPane(panelNight);
		panelNight.setVisible(true);
	}
	
	private void checkPlayer(){
		cpd.setPlayerName(g.getPlayerInfo().get(position).getName());
		frame.getContentPane().setVisible(false);
		panelCheck = cpd.getContentPane();
		frame.setContentPane(panelCheck);
		panelCheck.setVisible(true);
	}

	
}
