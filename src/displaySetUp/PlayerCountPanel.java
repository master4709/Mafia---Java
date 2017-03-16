package displaySetUp;

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

import myJStuff.Colors;
import myJStuff.MyButton;
import myJStuff.MyButtonFont;
import myJStuff.MyFont;
import myJStuff.MyLabel;
import net.miginfocom.swing.MigLayout;

public class PlayerCountPanel{
	
	private Color textColor;
	private Color btnBackgroundColor;
	private Color backgroundColor;
	private Color selectColor;
	
	private Font titleFont;
	private Font btnFont;

	private JPanel contentPane;
	private JPanel north;
	private JPanel south;
	
	private JLabel lblText;
	private JLabel lblText2;
	private JButton btnContinue;
	private JPanel west;
	private JPanel east;
	private JPanel center;
	
	private int playerTotal;
	
	private List<JButton> buttonList = new ArrayList<>();

	/**
	 * Create the frame.
	 */
	public PlayerCountPanel() {
		playerTotal = 5;
		setFont();
		setColor();

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		north = new JPanel();
		contentPane.add(north, BorderLayout.NORTH);
		north.setLayout(new MigLayout("", "[grow,center]", "[]"));
		
		south = new JPanel();
		contentPane.add(south, BorderLayout.SOUTH);
		south.setLayout(new MigLayout("", "[grow]", "[]"));
		
		west = new JPanel();
		contentPane.add(west, BorderLayout.WEST);
		
		east = new JPanel();
		contentPane.add(east, BorderLayout.EAST);
		
		center = new JPanel();
		contentPane.add(center, BorderLayout.CENTER);
		center.setLayout(new MigLayout("", "[grow]", "[]"));
		
		displayNorth();
		displaySouth();
		displayCenter();
		
		setBackgroundColor(backgroundColor);
	}
	
	private void displayNorth(){
		lblText = new MyLabel("How Many", textColor, titleFont);
		north.add(lblText, "cell 0 0,alignx center");
		
		lblText2 = new MyLabel("Playes?", textColor, titleFont);
		north.add(lblText2, "cell 0 1,alignx center");
	}

	private void displayCenter(){
		for(int i=5;i<11;i++){
			displayPlayerButton(i);
		}
	}
	private void displaySouth(){
		btnContinue = new MyButton("Continue", textColor, btnBackgroundColor, btnFont);
		south.add(btnContinue, "cell 0 0,growx");
		btnContinue.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			SetUpController.getInstance().switchToGame(playerTotal);
		}});
		
	}
	
	private void displayPlayerButton(int i){
		JButton btnPlayer = new MyButton(Integer.toString(i), textColor, btnBackgroundColor, btnFont);
		String position = "cell 0 "+i+",growx";
		center.add(btnPlayer, position);
		btnPlayer.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			for(int m=0;m<buttonList.size();m++){
    				buttonList.get(m).setBackground(btnBackgroundColor);
    			}
    			btnPlayer.setBackground(selectColor);
    			playerTotal = i;
		}});
		buttonList.add(btnPlayer);
		
	}
	
	private void setBackgroundColor(Color c){
		north.setBackground(c);
		south.setBackground(c);
		east.setBackground(c);
		west.setBackground(c);
		center.setBackground(c);
	}
	
	private void setFont(){
		titleFont = new MyFont(80);
		btnFont = new MyButtonFont();
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