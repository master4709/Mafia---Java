package displayGame;

import java.awt.BorderLayout;

import myJStuff.*;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.Story;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
/**
 * Story Panel
 * @author
 *
 */
public class StoryPanel extends MyPanel {
	
	private JButton btnContinue;
	private String name;
	private boolean isDead;
	private JLabel title;
	private JLabel text;
	private JLabel text2;
	
	public StoryPanel(ActionListener actionListener){
		this.actionListener = actionListener;
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		initalize();
	}

	
	public JPanel getContentPane(){
		return contentPane;
	}
	
	private void initalize() {
		
		//Put "" for all of the labels lblLocation = new MyLabel("",...);
		displayTop();
		//displayLeft();
		//displayRight();
		//displayCenter();
		displayBottom();
		
	}
	private void displayTop(){
		title = new MyLabel("Story", textColor, titleFont);
		north.add(title);
	}
	private void displayLeft(){
		
	}
	private void displayRight(){
		
	}
	
	private void displayBottom() {
		btnContinue = new MyButton("Continue");
		south.add(btnContinue);
		btnContinue.setName("Continue_StoryPanel");
		btnContinue.addActionListener(actionListener);
	}
	private void displayCenter() {
		Story s = new Story(name, isDead);
		s.information();
		String[] initalize = s.initialScenario();
		for (int i = 0; i < initalize.length; i++){
			text.setText(initalize[i].toString());
		}
		center.add(text, "cell 0 0");
		if(isDead == true){
			String[] dead = s.dead();
			for (int i = 0; i < dead.length; i++){
				text2.setText(dead[i].toString());
			}
			center.add(text2, "cell 0 1");
		}else if (isDead == false){
			String[] healed = s.healed();
			for (int i = 0; i < healed.length; i++){
				text2.setText(healed[i].toString());
			}
			center.add(text2, "cell 0 1");
		}
	}
	//Call the story class here also to receive the getters for the location, event, etc....
	//Set all of the labels and such for the story in here
	public void setStory(String name, boolean dead){
		
	}
	

}
