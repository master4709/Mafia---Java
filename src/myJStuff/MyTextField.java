package myJStuff;


import javax.swing.JTextField;

public class MyTextField extends JTextField {

	public MyTextField(String string, int i) {
		super(string, i);
		this.setFont(new MyFont(40));
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
}