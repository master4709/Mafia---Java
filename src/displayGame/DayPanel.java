package displayGame;

import myJStuff.*;
import logic.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class DayPanel{
	
	private Color textColor;
	private Color btnBackgroundColor;
	private Color backgroundColor;
	private Color selectColor;
	
	private Font titleFont;
	private Font btnFont;

	private JPanel contentPane;
	private JPanel north;
	private JPanel south;
	private JPanel west;
	private JPanel east;
	private JPanel center;
	
	private JLabel lblDayTime;
	private JLabel lblDiscription;
	
	private JButton btnContinue;
	
	private List<Player> playerInfo;
	private List<JButton> buttonList = new ArrayList<>();
	private int target;

	/**
	 * Create the panel.
	 */
	public DayPanel(List<Player> playerInfo) {
		this.playerInfo = playerInfo;
		target = -1;
		setFont();
		setColor();

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		north = new JPanel();
		contentPane.add(north, BorderLayout.NORTH);
		north.setLayout(new MigLayout("", "[grow,center]", "[][grow]"));
		
		south = new JPanel();
		contentPane.add(south, BorderLayout.SOUTH);
		south.setLayout(new MigLayout("", "[grow,fill]", "[]"));
		
		west = new JPanel();
		contentPane.add(west, BorderLayout.WEST);
		west.setLayout(new MigLayout("", "[]", "[]"));
		
		east = new JPanel();
		contentPane.add(east, BorderLayout.EAST);
		east.setLayout(new MigLayout("", "[]", "[]"));
		
		center = new JPanel();
		contentPane.add(center, BorderLayout.CENTER);
		center.setLayout(new MigLayout("", "[grow]", "[]"));
		
		displayNorth();
		displaySouth();
		displayCenter();
		displayEast();
		displayWest();
		
		setBackground(backgroundColor);
	}
	/**
	 * Displays that it is Day Time and rules of the day
	 */
	private void displayNorth(){
		lblDayTime = new MyLabel("Day Time", textColor, titleFont);
		north.add(lblDayTime, "flowy,cell 0 0");
		
		String text = "One player must be voted out each day.";// There must be a 50% majority to lynch him/her";
		lblDiscription = new MyLabel(text, textColor, btnFont);
		//lblDiscription.setWrapStyleWord(true);
		//lblDiscription.setLineWrap(true);
		north.add(lblDiscription, "cell 0 1,");
	
	}
	/**
	 * Creates button needed to be pressed to go to next screen
	 */
	private void displaySouth(){
		btnContinue = new MyButton("Continue", textColor, btnBackgroundColor, btnFont);
		south.add(btnContinue, "cell 0 0");
		btnContinue.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			GameController.getInstance().switchNightCycle(target);
		}});
	}
	
	private void displayCenter(){
		for(int i=0;i<playerInfo.size();i++){
			if(!playerInfo.get(i).isDead()){
				displayPlayerButton(i);
			}
		}
	}
	
	private void displayEast(){
		
	}
	
	private void displayWest(){
		
	}
	
	private void displayPlayerButton(int i){
		String text = playerInfo.get(i).getName()+"|"+playerInfo.get(i).getRole();
		JButton btnPlayer = new MyButton(text,textColor,btnBackgroundColor,btnFont);
		String position = "cell 0 "+i+",growx";
		center.add(btnPlayer, position);
		btnPlayer.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			for(int m=0;m<buttonList.size();m++){
    				buttonList.get(m).setBackground(btnBackgroundColor);
    			}
    			btnPlayer.setBackground(selectColor);
    			target = i;
		}});
		buttonList.add(btnPlayer);
	}
	
	private void setBackground(Color c){
		north.setBackground(c);
		south.setBackground(c);
		east.setBackground(c);
		west.setBackground(c);
		center.setBackground(c);
	}
	
	private void setFont(){
		titleFont = new MyFont(100);
		btnFont = new MyFont(30);
	}
	
	private void setColor(){
		textColor = Colors.black;
		btnBackgroundColor = Colors.white;
		backgroundColor = Colors.grey;
		selectColor = Colors.blue;
	}
	
	public JPanel getContentPane(){
		return contentPane;
	}

}
