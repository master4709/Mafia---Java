package displayGame;

import logic.*;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * Class GameController
 * 
 * 
 * 
 * 
 * 
 * @author Pierce de Jong 30006609
 *
 */
public class GameController {
	
	private static GameController instance = null;
	
	private JFrame frame;
	
	private Game g;
	private DayPanel dd;
	private NightPanel nd;
	private CheckPlayerPanel cpd;
	private StoryPanel sp;
	private ViewAllPlayersPanel vapp;
	private ViewPlayerPanel vpp;
	
	//All of the possible panels to be displayed on the frame
	private JPanel panelDay;
	private JPanel panelNight;
	private JPanel panelCheck;
	private JPanel panelStory;
	private JPanel panelViewAllPlayers;
	private JPanel panelViewPlayer;
	//Location inside the list of players for the night cycle
	private int position = 0;
	
	private int events = 1;
	
	private GameController(JFrame frame){
		this.frame = frame;
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setVisible(true);
	}
	/**
	 * This method ensures only one instance of the GameController Class can be made
	 * @param frame
	 */
	public static void createInstance(JFrame frame){
		if(instance==null){
			instance = new GameController(frame);
		}
	}
	
	public static GameController getInstance(){
		if(instance!=null){
			return instance;
		}else{
			return null;
		}
	}
	
	public void start(List<Player> playerInfo, int lynchTarget){
		g = new Game(playerInfo,lynchTarget);
		cpd = new CheckPlayerPanel();
		dd = new DayPanel(g.getPlayerInfo());
		vapp = new ViewAllPlayersPanel(g.getPlayerInfo());
		vpp = new ViewPlayerPanel();
		
		switchViewAllPlayers();
	}
	
	public void switchViewAllPlayers(){
		//Hides the current content Pane
		frame.getContentPane().setVisible(false);
		//
		panelViewAllPlayers = vapp.getContentPane();
		//sets the frame's content pane to day screen
		frame.setContentPane(panelViewAllPlayers);
		//sets the current content pane to visible
		panelViewAllPlayers.setVisible(true);
	}
	
	public void switchViewPlayer(int i){
		frame.getContentPane().setVisible(false);
		vpp.setPlayer(g.getPlayerInfo().get(i));
		panelViewPlayer = vpp.getContentPane();
		frame.setContentPane(panelViewPlayer);
		panelViewPlayer.setVisible(true);
	}
	
	/**
	 * switches the content panel to the dayCycle page
	 */
	public void switchDay(){
		events=0;
		System.out.println("Day Panel");
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
	public void switchNightFirst(int target){
		System.out.println("Night Panel First Time");
		//Lynches the player who was the target during the day 
		g.dayCycle(target);
		//Index value for where the nightPanel is in the loop of players for selecting his/her target of the night
		position = 0;
		//creates the new night panel with a list of buttons of every person that is alive
		nd = new NightPanel(g.getPlayerInfo(),g.getMafiaMember());
		//Finds the next player in the list of players that is not dead and displays this/her CheckPlayerPanel
		findNextPlayer();
	}
	
	/**
	 * Updates the north panel of the Night Panel text with the information of the next player
	 * Hide the current view
	 * set the nightPanel to the update ContentPane
	 * update the frame with new content pane
	 */
	public void switchNight(){
		System.out.println("Night Panel");
		//Sets current content pane to invisible
		frame.getContentPane().setVisible(false);
		//Sets the display for current player to select his/her target
		nd.setDisplay(position);
		//Refreshes the content pane to adjust for updates
		panelNight = nd.getContentPane();
		//Sets frame to the new night content pane
		frame.setContentPane(panelNight);
		//Sets pane to visible
		panelNight.setVisible(true);
	}
	/**
	 * Updates the Check Player panel text with the next player
	 * Switch the frame to the CheckPlayerPanel
	 */
	public void switchCheckPlayer(){
		System.out.println("Check Player Panel");
		frame.getContentPane().setVisible(false);
		cpd.setPlayerName(g.getPlayerInfo().get(position).getName());
		panelCheck = cpd.getContentPane();
		frame.setContentPane(panelCheck);
		panelCheck.setVisible(true);
	}
	/**
	 * Switches the frame to the Story Panel to display the death or savior of a player
	 * @param name
	 * @param dead
	 */
	public void switchStory(String name, boolean dead){
		System.out.println("Story Panel");
		frame.getContentPane().setVisible(false);
		sp = new StoryPanel(name,dead);
		panelStory = sp.getContentPane(); 
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
		//System.out.println(g.getPlayerInfo().get(position).getName()+"  "+ g.getPlayerInfo().get(target).getName());
		g.setPlayerTarget(position, target);
		//Goes to the next player
		position++;
		
		//Checks if the next player is alive and the position is not out of bounds
		if(position<g.getPlayerInfo().size()){
			//if the next player is not dead call the CheckPlayerPanel
			if(!g.getPlayerInfo().get(position).isDead()){
				switchCheckPlayer();
			//else find the next player that is alive in the game
			}else{
				findNextPlayer();
			}
		//If the position in the list 
		}else if(position==g.getPlayerInfo().size()){
			//prints every players target and their name
			for(int m=0;m<g.getPlayerInfo().size();m++){
				System.out.print(g.getPlayerInfo().get(m).getName()+"|"+g.getPlayerInfo().get(m).getPlayerTarget()+" ");
			}
			System.out.println();
			
			//Calls the actions of each player
			g.nightAction();
		}
	}
	/**
	 * Loops through the remaining list of Players until an alive player is found 
	 */
	public void findNextPlayer(){
		for(int k=position;k<g.getPlayerInfo().size();k++){
			//If the next player is not dead
			if(!g.getPlayerInfo().get(k).isDead()){
				position = k;
				//Prompts the user if they are the next player
				switchCheckPlayer();
				//Stops the loop once the next alive player has been found
				break;
			}
			//If there is no next player go to night actions
			if(k==g.getPlayerInfo().size()-1){
				g.nightAction();
			}
		}
		
	}
	/**
	 * Checks if more than one person died each night
	 * If yes than displays another Story Panel
	 * If not goes to the next round and the day lynching starts
	 */
	public void nextEventOrDay(){
		events++;
		g.eventCount(events);
		
	}
}
