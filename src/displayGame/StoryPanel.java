package displayGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import myJStuff.*;
import net.miginfocom.swing.MigLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.Story;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Story Panel
 * @author
 *
 */
public class StoryPanel {
	private Font titleFont;
	
	private Color textColor;
	private Color backgroundColor;
	
	private JPanel contentPane;
	private JPanel north;
	private JPanel west;
	private JPanel east;
	private JPanel south;
	private JPanel center;
	
	private JButton btnNextDay;
	private String name;
	private boolean isDead;
	private JLabel title;
	private JLabel text;
	private JLabel text2;
	
	public StoryPanel(String str, boolean dead){
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		name = str;
		isDead = dead;
		initalize();
	}

	
	public JPanel getContentPane(){
		return contentPane;
	}
	
	private void initalize() {
		
		setFont();
		setColor();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		north = new JPanel();
		contentPane.add(north, BorderLayout.NORTH);
		
		west = new JPanel();
		contentPane.add(west, BorderLayout.WEST);
		
		east = new JPanel();
		contentPane.add(east, BorderLayout.EAST);
		
		south = new JPanel();
		contentPane.add(south, BorderLayout.SOUTH);
		
		center = new JPanel();
		contentPane.add(center, BorderLayout.CENTER);
		center.setLayout(new MigLayout("", "[grow]", "[][][][][]"));
		
		//displayTop();
		//displayLeft();
		//displayRight();
		//displayCenter();
		displayBottom();
		
		setBackground(backgroundColor);
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
		btnNextDay = new MyButton("Continue");
		south.add(btnNextDay);
		btnNextDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GameController.getInstance().nextEventOrDay();}
		});
		
		
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
	private void setBackground(Color c){
		north.setBackground(c);
		south.setBackground(c);
		east.setBackground(c);
		west.setBackground(c);
		center.setBackground(c);
		//Creates a black border on the screen
		contentPane.setBackground(Colors.defaultBorderColor);
	}
	private void setFont(){
		titleFont = new MyFont(50);
	}
	
	private void setColor(){
		textColor = Colors.black;
		backgroundColor = Colors.defaultBackgroundColor;
	}
	

}
