
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
public class ViewPlayerPanel{
	
	//All of the Color variables needed for the screen
	private Color backgroundColor;
	
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
		setFont();
		setColor();

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		south = new JPanel();
		contentPane.add(south, BorderLayout.SOUTH);
		south.setLayout(new MigLayout("", "[grow,fill]", "[]"));
		
		displaySouth();
		setBackground(backgroundColor);
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

	
	private void setBackground(Color c){
		south.setBackground(c);
		//Creates a black border on the screen
		contentPane.setBackground(Colors.defaultBorderColor);
	}

	private void setFont(){

	}

	private void setColor(){
		backgroundColor = Colors.defaultBackgroundColor;
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
