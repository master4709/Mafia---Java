package displayGame;

import myJStuff.*;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import logic.Story;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.event.ActionListener;
/**
 * Story Panel
 * @author: Ronelle Bakima
 * 30005568
 *
 */
public class StoryPanel extends MyPanel {
	/**
	 * initializes variables
	 */
	private JButton btnContinue;
	private JLabel name;
	private JTextArea story;
	private JLabel location;
	private JScrollPane scrollPane;
	/**
	 * constructor method for the story panel
	 * @param actionListener
	 */
	public StoryPanel(ActionListener actionListener){
		this.packageListener = actionListener;
		
		contentPane.setName("Story Panel");
		
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
		name = new MyLabel("", 30);
		north.add(name, "cell 0 1");
		location = new MyLabel("", 30);
		north.add(location, "cell 0 2");
		
	}
	/**
	 * displays the continue bottom at bottom of the screen
	 * case in game controller: switches to day panel
	 */
	private void displayBottom() {
		btnContinue = new MyButton("Continue");
		south.add(btnContinue);
		btnContinue.setName("Continue_StoryPanel");
		btnContinue.addActionListener(packageListener);
	}
	/**
	 * displays the story and whether they lived or died in
	 * the center of the panel
	 */
	private void displayCenter() {
		story = new MyTextArea("", 30);
		center.add(story, "cell 0 1");
		
		//sets up a scroll bar so you can scroll down the page
		scrollPane = new JScrollPane(story);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(500, 750));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		center.add(scrollPane);
	}

	/**Call the story class here also to receive the getters for the location, event, etc....
	 * Sets all of the labels and such for the story in here
	 */
	public void setStory(String str, boolean dead, int screenWidth){
		Story s = new Story(str);
		s.information();
		/**
		 * wraps the story text to display all the words on the screen
		 */
		String l = "Location: " + s.getLocation();
		location.setText(l);
		String n = "Name: " + s.getName();
		name.setText(n);
		story.setEditable(false);
		String str1 = s.getStory();
		story.setLineWrap(true);
		story.setBounds(0, 0, screenWidth-50, 100);
		String str2 = "So here's what happened... ";

		String eventTxt = "";
			if(dead) {
				eventTxt = " Sadely, They were killed by the mafia.";
			}
			else {
				eventTxt = " Thankfully, They were healed by the doctor.";
			}
			
		story.setText(str2 + str1 + eventTxt);
		
	}
}