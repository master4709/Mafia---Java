package displayGame;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class StoryPanel {
	
	private JPanel contentPane;
	
	public StoryPanel(String name, boolean dead){
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
	}
	
	
	
	
	
	public JPanel getContentPane(){
		return contentPane;
	}

}
