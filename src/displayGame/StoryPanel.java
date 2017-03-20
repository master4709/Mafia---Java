package displayGame;

import myJStuff.*;
import javax.swing.JPanel;

import logic.Story;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
/**
 * Story Panel
 * @author: Ronelle Bakima
 * 30005568
 *
 */
public class StoryPanel extends MyPanel {
	
	private JButton btnContinue;
	private JLabel name;
	
	private JLabel story;
	private JLabel location;
	private JLabel causeOfDeath;
	private JLabel event;
	
	private JLabel line_name;
	private JLabel line_location;
	private JLabel line_story;
	private JLabel line_event;
	private JLabel line_cause;
	
	public StoryPanel(ActionListener actionListener){
		this.actionListener = actionListener;
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
		displayCenter();
		displayBottom();
		
	}
	private void displayLeft() {
		
	}
	private void displayRight() {
		
	}
	private void displayTop(){
		name = new MyLabel("", 20);
		north.add(name);
		location = new MyLabel("",20);
		north.add(location, "cell 0 1");
		line_location = new MyLabel("Location: ", textColor, textFont);
		center.add(line_location, "cell 0 2");
		
		
	}
	
	private void displayBottom() {
		btnContinue = new MyButton("Continue");
		south.add(btnContinue);
		btnContinue.setName("Continue_StoryPanel");
		btnContinue.addActionListener(actionListener);
	}
	private void displayCenter() {
		story = new MyLabel("", 20);
		center.add(story, "cell 0 2");
		event = new MyLabel("", 20);
		center.add(event, "cell 0 3");
		//line_name = new MyLabel("Name: ", textColor, textFont);
		//center.add(line_name);
		//center.add(name);
		
		//line_story = new MyLabel("Here's what happened...", textColor, textFont);
		//center.add(line_story);
		//center.add(event);
		
		
		
	}

	//Call the story class here also to receive the getters for the location, event, etc....
	//Set all of the labels and such for the story in here
	public void setStory(String str, boolean dead){
		Story s = new Story(str, dead);
		s.information();;
		story.setText(s.getStory());
		location.setText(s.getLocation());
		//causeOfDeath.setText(s.getCause());
		event.setText(s.getEvent());
		name.setText(s.getName());
	}
	

}
