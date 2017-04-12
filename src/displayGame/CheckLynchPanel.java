package displayGame;

import myJStuff.*;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
/**
 * This Panel is used to check if the players chose the correct Lynch Target for the day
 * @author Pierce de Jong 30006609
 *
 */
public class CheckLynchPanel extends MyPanel{
	
	private JLabel lblName;
	private JLabel lblCheck;
	
	private JButton btnUndo;
	private JButton btnContinue;
	/**
	 * Constructor 
	 * @param packageListener - ActionListener
	 */
	public CheckLynchPanel(ActionListener packageListener){
		this.packageListener = packageListener;
		contentPane.setName("CheckLynch Panel");
		displayNorth();
		displaySouth();
	}
	/**
	 * Create the Label asking if the correct player has been lynched
	 */
	private void displayNorth(){
		lblName = new MyLabel("",titleFontSize);
		center.add(lblName,"cell 0 0, alignx center");
		lblCheck = new MyLabel("has been Lynched",55);
		center.add(lblCheck,"cell 0 1, alignx center");
	}
	/**
	 * Creates the undo and continue button
	 */
	private void displaySouth(){
		btnUndo = new MyButton("Undo",buttonFontSize);
		south.add(btnUndo,"cell 0 0,alignx left");
		btnUndo.addActionListener(packageListener);
		btnUndo.setName("Undo_CheckLynchPanel");
		
		btnContinue = new MyButton("Continue to Night", buttonFontSize);
		south.add(btnContinue,"cell 1 0,growx");
		btnContinue.addActionListener(packageListener);
		btnContinue.setName("Continue_CheckLynchPanel");
	}
	/**
	 * Sets the lblName to the current player been killed 
	 * @param name -String
	 */
	public void setPlayer(String name){
		lblName.setText(name);
		lblName.setFont(new MyFont(setFontSize(name,120,50)));
	}
}
