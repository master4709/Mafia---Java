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
	private StoryPanel sp;
	
	//All of the possible panels to be displayed on the frame
	private JPanel panelDay;
	private JPanel panelNight;
	private JPanel panelCheck;
	private JPanel panelStory;
	
	//Location inside the list of players for the night cycle
	private int position = 0;
	
	private GameController(JFrame frame){
		
		this.frame = frame;
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void createInstance(JFrame frame){
		if(instance==null){
			instance = new GameController(frame);
		}
	}
	
	public static GameController getInstance(){
		return instance;
	}
	
	public void start(List<Player> playerInfo, int lynchTarget){
		g = new Game(playerInfo,lynchTarget);
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
		frame.getContentPane().setVisible(false);
		dd = new DayPanel(g.getPlayerInfo());
		panelDay = dd.getContentPane();
		frame.setContentPane(panelDay);
		panelDay.setVisible(true);
	}
	/**
	 * This method starts the cycle of rotating through ever player that is alive
	 * Also calls the game method to set the status of the day lynching to dead
	 * @param target
	 */
	public void switchNightCycle(int target){
		g.dayCycle(target);
		position = 0;
		nd = new NightPanel(g.getPlayerInfo(),g.getMafiaMember());
		findNextPlayer();
	}
	
	/**
	 * Updates the north panel of the Night Panel text with the information of the next player
	 * Hide the current view
	 * set the nightPanel to the update ContentPane
	 * update the frame with new content pane
	 */
	public void switchNightPlayer(){
		nd.setDisplay(position);
		frame.getContentPane().setVisible(false);
		panelNight = nd.getContentPane();
		frame.setContentPane(panelNight);
		panelNight.setVisible(true);
	}
	/**
	 * Updates the Check Player panel text with the next player
	 * Switch the frame to the CheckPlayerPanel
	 */
	public void switchCheckPlayer(){
		cpd.setPlayerName(g.getPlayerInfo().get(position).getName());
		frame.getContentPane().setVisible(false);
		panelCheck = cpd.getContentPane();
		frame.setContentPane(panelCheck);
		panelCheck.setVisible(true);
	}
	/**
	 * Switches the frame to the Story Panel to display the death or savior of a player
	 * @param name
	 * @param dead
	 */
	public void switchStoryPanel(String name, boolean dead){
		sp = new StoryPanel(name,dead);
		panelStory = sp.getContentPane();
		frame.getContentPane().setVisible(false);
		frame.setContentPane(panelStory);
		panelStory.setVisible(true);
	}
	
	/**
	 * finds the next alive player in the list and displays screen asking if they are that person
	 * once all players have gone goes to dayCycle 
	 * @param position
	 */
	public void rotateNightPlayer(int target){
		//Sets the target of the player to the button that was pressed on the night panel
		g.setPlayerTarget(position, target);
		//Goes to the next player
		position++;
		
		//Checks if the next player is alive and the position is not out of bounds
		if(position<g.getPlayerInfo().size()&& !g.getPlayerInfo().get(position).isDead()){
			//If alive then switch to the CheckPlayer screen and prompt the user if they are the next player
			switchCheckPlayer();
		
		//If the position is out of bounds(gone through every player)
		//All of the night logic is then run
		//Switches to the dayCycle screen for the next round of player
		}else if(position==g.getPlayerInfo().size()){
			
			//prints every players target and their name
			for(int m=0;m<g.getPlayerInfo().size();m++){
				System.out.print(g.getPlayerInfo().get(m).getName()+"|"+g.getPlayerInfo().get(m).getPlayerTarget()+" ");
			}
			System.out.println();
			
			//Calls the actions of each player
			g.nightAction();
			
			//
			//switchDayCycle();
		}
		//Loops through the list of players until the 
		else{
			findNextPlayer();
		}
	}
	/**
	 * Loops through the remaining list of Players until an alive player is found 
	 */
	public void findNextPlayer(){
		for(int k=position;k<g.getPlayerInfo().size();k++){
			System.out.println(k);
			//If the next player is not dead
			if(!g.getPlayerInfo().get(k).isDead()){
				position = k;
				//Prompts the user if they are the next player
				switchCheckPlayer();
				//Stops the loop once the next alive player has been found
				break;
			}
		}
	}
	

	
}
