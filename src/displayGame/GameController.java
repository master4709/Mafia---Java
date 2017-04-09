package displayGame;

import logic.*;
import playerInfo.Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * Class GameController
 * 
 * This class controls all of the panels and switching for the main game part
 * 
 * 
 * @author Pierce de Jong 30006609
 *
 */
public class GameController implements ActionListener{
	//JFrame reference 
	private JFrame frame; 
	
	private ActionListener globalListener;
	
	private SaveFile sf;
	
	private Game g;
	private DayPanel dp;
	private NightPanel np;
	private CheckPlayerPanel cpp;
	private StoryPanel sp;
	private ViewAllPlayersPanel vapp;
	private ViewPlayerPanel vpp;
	private VictoryPanel vp;
	
	//All of the possible panels to be displayed on the frame
	private JPanel panelDay;
	private JPanel panelNight;
	private JPanel panelCheck;
	private JPanel panelStory;
	private JPanel panelViewAllPlayers;
	private JPanel panelViewPlayer;
	private JPanel panelVictory;
	//Location inside the list of players for the night cycle
	private int position = -1;
	//Boolean for if the game is in test mode
	private boolean test = false;
	//Location of the current target for both the day lynching and every night player
	private int target = -1;
	
	//Must be private. so only one instance can be made
	public GameController(JFrame frame, ActionListener globalListener){
		this.frame = frame;
		this.globalListener = globalListener;
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setVisible(true);
	}
	/**
	 * Initializes all of the Panels and Game Class with:
	 * @param playerInfo - list of all of the players, stored in a list of Player classes
	 * @param lynchTarget - index value of the target for the lyncher player, -1 if no lyncher player
	 * @param test - if true, bypasses specific screens, (ViewAllPlayersPanel and CheckPlayerPanel)
	 */
	public void start(List<Player> playerInfo, int lynchTarget, int round, boolean test){
		this.test = test;
		
		g = new Game(playerInfo,lynchTarget,round,test);
		dp = new DayPanel(this,globalListener);
		cpp = new CheckPlayerPanel(this);
		np = new NightPanel(this,g.getMafiaMember());
		sp = new StoryPanel(this);
		vapp = new ViewAllPlayersPanel(this,globalListener);
		vpp = new ViewPlayerPanel(this,g.getMafiaMember());
		vp = new VictoryPanel();
		
		sf = new SaveFile();
		
		//Creates the buttons of all of the players in the game
		fillPanels();

		panelCheck = cpp.getContentPane();
		panelNight = np.getContentPane();
		panelDay = dp.getContentPane();
		panelStory = sp.getContentPane(); 
		panelViewAllPlayers = vapp.getContentPane();
		panelViewPlayer = vpp.getContentPane();
		panelVictory = vp.getContentPane();
		
		if(this.test){//Bypass the viewAllPlayers panel if the game is in test mode
			switchDay();
		}else{
			switchPanel(panelViewAllPlayers);
		}
	}
	
	private void fillPanels(){
		int y = 0;
		for(String name: g.getPlayerNames()){
			if(!name.contains("Dead")){
				dp.displayPlayerButton(name, y);
				np.displayPlayerButton(name, y);
			}
			vapp.displayPlayerButton(name, y);
			y++;
		}
		
		vp.setPlayerInfo(g.getPlayerNames());
		
	}
	/**
	 * Switch to the ViewPlayer JPanel and display the current plays information
	 * @param i - the index value for which player to show
	 */
	private void switchViewPlayer(int i){
		vpp.setPlayer(g.getPlayer(i));
		switchPanel(panelViewPlayer);
	}
	
	/**
	 * switches the content panel to the dayCycle page
	 * Sets the target of the day cylce to -1 -- ensures that a player button must be pressed to continue the game 
	 * Saves the game to the saveGame.txt document
	 * If there was no winner, continues the cycle else goes to the win game panel
	 */
	private void switchDay(){
		dp.resetButtonColor();
		target = -1;
		sf.save(g.getPlayerInfo(), g.getLynchTarget());
		String win = g.checkWinner();
		if(win.contains("None")){

			switchPanel(panelDay);
		}else{
			vp.setWinner(win);
			switchPanel(panelVictory);
			dp.setContinueButtonVisible(false);
		}
	}

	
	/**
	 * Updates Night Panel text with the information of the next player
	 * Set the frame to new updated panelNight content pane 
	 */
	private void switchNight(){
		np.setDisplay(g.getPlayer(position));//Sets the display for current player to select his/her target
		switchPanel(panelNight);
	}
	/**
	 * Updates the Check Player panel text with the next player
	 * Switch the frame to the CheckPlayerPanel
	 */
	private void switchCheckPlayer(){
		cpp.setPlayerName(g.getPlayer(position).getName());
		switchPanel(panelCheck);
	}
	/**
	 * Switches the frame to the Story Panel to display the death or savior of a player
	 * @param name - String of the players name
	 * @param dead - Boolean if the player is dead or alive
	 */
	private void switchStory(String name, boolean dead){
		sp.setStory(name,dead,(int) frame.getWidth());
		switchPanel(panelStory);
	}
	/**
	 * Switches the frame to the passed JPanel
	 * Sets current content pane to invisible
	 * Sets the frame to the new JPanel
	 * Sets new contnet pane to visible
	 * @param panel
	 */
	private void switchPanel(JPanel panel){
		//System.out.println(panel.getName().toUpperCase());
		frame.getContentPane().setVisible(false);
		frame.setContentPane(panel);
		frame.getContentPane().setVisible(true);
	}
	/**
	 * Calls the night Action method in the game class and odes all of the logic for each player each night
	 * If there was a target of action that night
	 */
	private void checkStory(int position){
		
		if(g.getEvents().size()>position){
			System.out.println("Story is here boiiii");
			Player p = g.getPlayer(g.getEvents().get(position));
			if(p.getStatus()==2){
				g.setPlayerStatus(p.getPosition(), 0);
				removePlayerButton(p.getPosition());
				switchStory(p.getName(),true);
			}else if(p.getStatus()==3){//Else
				g.setPlayerStatus(p.getPosition(), 1);
				switchStory(p.getName(),false);
			}else{
				switchDay();
			}
		}else{
			switchDay();
		}
	}
	/**
	 * Resets the target for the next player to -1
	 * Moves the position in the list up one
	 * Finds the net player in the list that is not dead and sets it to position
	 */
	private void findNextPlayer(){
		target = -1;
		position++;
		position = nextPlayer(position);
		if(position ==-1){
			System.out.println("End of Night");
			g.nightAction();
			checkStory(0);
		}else{
			//Skips the checkPlayerPanel is the game is in testing mode
			if(test){
				switchNight();
			}else{
				switchCheckPlayer();
			}
		}
	}
	/**
	 * Recursive method to find the next player in the list of players that is alive for the night cycle
	 * @param position - the current position in the list 
	 * @return
	 */
	private int nextPlayer(int position){
		System.out.print("Finding next player: ");
		
		if(position>=g.getPlayerInfo().size()){//If position is at the end of the list
			System.out.println("End of list");
			return -1;
		}else if(g.getPlayer(position).getStatus()==0){//If the player is dead, find the next one
			System.out.println(g.getPlayer(position).getName().toUpperCase()+ " is Dead");
			g.setPlayerTarget(position, -1);//Sets the target for any dead player to -1
			position++;
			return nextPlayer(position);
		}else{
			System.out.println(g.getPlayer(position).getName().toUpperCase()+ " is alive");
			return position;
		}
	}
	/**
	 * Removes the button on the night and day panel for the specific player
	 * Used when a player dies 
	 * @param target - place in the list to remove the button
	 */
	private void removePlayerButton(int target){
		dp.removePlayerButton(target);
		np.removePlayerButton(target);
		vapp.setPlayerButtonDead(target);
	}
	/**
	 * This method sets the message for the detective during the night
	 * It looks at the index value of the target of the detective
	 * THen displays if they are art of the Mafia or not
	 * @param target
	 */
	private void detective(){
		//If the target of the detective player is part of the Mafia
		//*Note* the Mafia GodFather is hidden from the detective
		if(g.getPlayer(target).getRole().contains("Mafia:")){
			np.setDetectiveMessage("Part of the Mafia");
		}else{
			np.setDetectiveMessage("Not part of the Mafia");
		}
	}
	/**
	 * This is the ActionListenr for all of the buttons in the displayGame Package
	 * When a button is pressed, the name String of the button is stored as a local variable
	 * A switch statement is used to compare the name with other string values to find the correct button
	 */
	public void actionPerformed(ActionEvent e){
		//Gets the name (NOT TEXT) of the button that was pressed
		JButton source = (JButton)e.getSource();
		String name = source.getName();
		//Finds the button that was pressed and does the needed commands
		switch(name){
		case "Continue_ViewAllPlayersPanel":
			switchDay(); break;
		case "Continue_DayPanel":
			if(target!=-1){//Makes sure a target has been chosen for the day 
				g.dayCycle(target);
				removePlayerButton(target);
				findNextPlayer();//Finds the first person in the list of players that is alive and displays his/her night screen
			} break;
		case "Continue_CheckPlayerPanel":
			switchNight(); break;
		case "Continue_NightPanel":
			if(target!=-1) System.out.println(g.getPlayer(position)+" has targeted player "+g.getPlayer(target)+ "for night action");
			g.setPlayerTarget(position, target);//Set the target of the player who just finished his/her night round
			findNextPlayer(); break;
		case "Continue_StoryPanel":
			checkStory(1); break;
		case "Back_ViewPlayerPanel":
			switchPanel(panelViewAllPlayers); break;
		case "ViewPlayers_DayPanel":
			switchPanel(panelViewAllPlayers); break;
		case "Detective":
			if(target!=-1){//Only if the detective has selected a target will the button be pressed
				detective();//Reveals the team of the target of the detective
			} break;
		default://If the button pressed was not a continue or back button
			int player=-1;
			if(name.contains("Select")){
				player = Integer.parseInt(name.substring(7, name.length()));
				switchViewPlayer(player);
			}else if(name.contains("Day")){
				player = Integer.parseInt(name.substring(4, name.length()));
				dp.setButtonSelected(target,name);
				target = player;
			}else if(name.contains("Night")){
				player = Integer.parseInt(name.substring(6, name.length()));
				np.setButtonSelected(target,name);
				target = player;
			}break;
		} 
    }
}
