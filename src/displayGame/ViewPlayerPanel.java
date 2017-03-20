
package displayGame;

import myJStuff.*;
import logic.*;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
/**
 *
 *
 */
public class ViewPlayerPanel extends MyPanel{
	
	private JButton btnBack;

	private Player player;
	
	private List<String> mafiaMembers;

	/**
	 * Create the panel.
	 */
	public ViewPlayerPanel(ActionListener actionListener, List<String> mafiaMembers) {
		this.actionListener = actionListener;
		this.mafiaMembers = mafiaMembers;
		
		displaySouth();
	}
	
	private void displaySouth(){
		//Create Button using the default button presets and text Continue
		btnBack = new MyButton("Back");
		south.add(btnBack, "cell 0 0,alignx center");
		btnBack.setName("Back_ViewPlayerPanel");
		btnBack.addActionListener(actionListener);
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
