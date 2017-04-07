package myJStuff;

/**
 * @author Elvin Limpin 30018832
 */

import javax.swing.JTextField;

public class MyTextField extends JTextField {

	/** default constructor **/
	public MyTextField(String string, int i) {
		super(string, i);
		this.setFont(new MyFont(40));
	}

	private static final long serialVersionUID = 1L;
	
	
	
}
