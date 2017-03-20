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
	private JLabel event;
	
	private JLabel line_name;
	private JLabel line_location;
	private JLabel line_story;
	
	//private MyTextArea myTxtPane;
	//private JScrollPane scrollPane;
	
	public StoryPanel(ActionListener actionListener){
		this.actionListener = actionListener;
		initalize();
	}

	/**
	 * 
	 * @return content pane
	 */
	public JPanel getContentPane(){
		return contentPane;
	}
	/**
	 * calls displays for the top, bottom, and
	 * center of screen
	 * 
	 */
	private void initalize() {
		
		displayTop();
		displayCenter();
		displayBottom();
	}
	/**
	 * displays the name and the location of death at
	 * the top of the screen
	 */
	private void displayTop(){
		line_name = new MyLabel("Name: ", textColor, textFont);
		north.add(line_name, "cell 0 1");
		name = new MyLabel("", 20);
		north.add(name, "cell 1 1");
		line_location = new MyLabel("Location: ", textColor, textFont);
		north.add(line_location, "cell 0 2");
		location = new MyLabel("",20);
		north.add(location, "cell 1 2");
		
	}
	/**
	 * displays the continue bottom at bottom of the screen
	 * case in game controller: switches to day panel
	 */
	private void displayBottom() {
		btnContinue = new MyButton("Continue");
		south.add(btnContinue);
		btnContinue.setName("Continue_StoryPanel");
		btnContinue.addActionListener(actionListener);
	}
	/**
	 * displays the story and whether they lived or died in
	 * the center of the panel
	 */
	private void displayCenter() {
		line_story = new MyLabel("Here's what happened...", textColor, textFont);
		center.add(line_story, "cell 0 3");
		story = new MyLabel("", 20);
		center.add(story, "cell 0 4");
		event = new MyLabel("", 20);
		center.add(event, "cell 0 5");	
	}

	/**Call the story class here also to receive the getters for the location, event, etc....
	 * Sets all of the labels and such for the story in here
	 */
	public void setStory(String str, boolean dead){
		Story s = new Story(str);
		s.information();;
		story.setText(s.getStory());
		location.setText(s.getLocation());
		name.setText(s.getName());
		
		String eventTxt = "";
			if(dead) {
				eventTxt = "They were killed by the mafia.";
			}
			else {
				eventTxt = "They were healed by the doctor.";
			}
		event.setText(eventTxt);
	}
	

}
