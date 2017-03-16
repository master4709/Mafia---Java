package displayGame;

import logic.*;
import myJStuff.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;

public class CheckPlayerPanel{

	/**
	 * Create the panel.
	 */
	private Color textColor;
	private Color btnBackgroundColor;
	private Color backgroundColor;
	
	private Font titleFont;
	private Font buttonFont;

	private JPanel contentPane;
	private JPanel north;
	private JPanel south;
	
	private JLabel lblPlayer;
	private JButton btnYes;
	private JPanel west;
	private JPanel east;
	private JPanel center;
	private JLabel lblAreYouReally;
	/**
	 * Create the frame.
	 */
	public CheckPlayerPanel() {
		
		setFont();
		setColor();

		//This is the pane that every other pane (north,south etc) is put on
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		//Creates all panes 
		north = new JPanel();
		contentPane.add(north, BorderLayout.NORTH);
		north.setLayout(new MigLayout("", "[grow]", "[][]"));
		
		south = new JPanel();
		contentPane.add(south, BorderLayout.SOUTH);
		south.setLayout(new MigLayout("", "[grow]", "[]"));
		
		west = new JPanel();
		contentPane.add(west, BorderLayout.WEST);
		west.setLayout(new MigLayout("", "[]", "[]"));
		
		east = new JPanel();
		contentPane.add(east, BorderLayout.EAST);
		east.setLayout(new MigLayout("", "[]", "[]"));
		
		center = new JPanel();
		contentPane.add(center, BorderLayout.CENTER);
		
		displayNorth();
		displaySouth();
		
		setBackground(backgroundColor);
	}
;	
	private void displayNorth(){
		
		lblAreYouReally = new MyLabel("Are You Really", textColor, buttonFont);
		north.add(lblAreYouReally, "cell 0 0,alignx center");
		
		lblPlayer = new MyLabel("", textColor, titleFont);
		north.add(lblPlayer, "cell 0 1,alignx center");
	}
	
	private void displaySouth(){
		btnYes = new MyButton("Back", textColor, btnBackgroundColor, buttonFont);
		btnYes.setText("Yes");
		south.add(btnYes, "cell 0 0,growx");
		btnYes.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			GameController.getInstance().setNightPlayer();
		}});
		
	}
	
	private void setBackground(Color c){
		north.setBackground(c);
		south.setBackground(c);
		east.setBackground(c);
		west.setBackground(c);
		center.setBackground(c);
	}
	
	public void setPlayerName(String text){
		lblPlayer.setText(text);
	}
	
	private void setFont(){
		titleFont = new MyFont(100);
		buttonFont = new MyButtonFont();
	}
	
	private void setColor(){
		textColor = Colors.black;
		btnBackgroundColor = Colors.white;
		backgroundColor = Colors.grey;
	}
	
	public JPanel getContentPane(){
		return contentPane;
	}

}
