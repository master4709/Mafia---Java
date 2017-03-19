
package displayGame;

import myJStuff.*;
import logic.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
/**
 *
 *
 */
public class ViewPlayerPanel extends MyPanel{
	
	private JPanel contentPane;

	private JPanel south;
	
	private JButton btnBack;

	private Player player;
	
	private List<String> mafiaMembers;

	/**
	 * Create the panel.
	 */
	public ViewPlayerPanel(List<String> mafiaMembers) {
		this.mafiaMembers = mafiaMembers;
		
		displaySouth();
	}
	
	private void displaySouth(){
		//New Button using the default button presets and text Continue
		btnBack = new MyButton("Back");
		south.add(btnBack, "cell 0 0");
		btnBack.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			GameController.getInstance().switchViewAllPlayers();
		}});
	}
	
	public void setPlayer(Player player){
		this.player = player;
	}
	
	
	/**
	 * Returns the contentPane with everything added to it
	 * @return contentPane
	 */
	public JPanel getContentPane(){
		return contentPane;
	}
}
