import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

public class RolesSelectionPanel {

	private JPanel contentPane;
	
    private int totalPlayers;
    private ArrayList<JSpinner> spinners;
    private ArrayList<Role> rolesSelected;

	/**
	 * Constructor takes in num of total players
	 */
	public RoleSelectionPanel(int totalPlayers) {
		
		this.totalPlayers = totalPlayers;
		
		spinners = new ArrayList<>();
		rolesSelected = new ArrayList<>();
		contentPane = new JPanel(new MigLayout( "",
		        "[][grow, grow][]",
		        ""));
		
		initialize();
		
	}
	
	/**
	 * Initialize contents.
	 */
	private void initialize() {
		
		JButton goToMain = new MyButton("Main menu", Colors.white, Colors.black, new MyFont(15));
		contentPane.add(goToMain);
		
		JButton continueButton = new MyButton("Continue", Colors.white, Colors.black, new MyFont(15));
		continueButton.addActionListener(e -> {
			getTotalRoles();
			// TODO this line should continue to/ display the game.
		});
		contentPane.add(continueButton, "wrap, right");
		
		JLabel selectRolesLbl = new MyLabel("Select Roles", Colors.black, new MyFont(40));
        selectRolesLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        Border selectRolesMargin = new EmptyBorder(10, 10, 20, 10);
        selectRolesLbl.setBorder(new CompoundBorder(contentPane.getBorder(), selectRolesMargin));
        contentPane.add(selectRolesLbl, "span, center, wrap");
        
        createRoleComponents();
		
	}

	private void getTotalRoles() {
		// TODO Auto-generated method stub
		
	}

	private void createRoleComponents() {
		
		for (Role role : Role.values()) {

            JLabel roleName = new MyLabel(role.getRoleID(), Colors.black, new MyFont(25));
            Border roleNameMargin = new EmptyBorder(10, 10, 10, 50);
            roleName.setBorder(new CompoundBorder(contentPane.getBorder(), roleNameMargin));
            contentPane.add(roleName, "right");

            if (role.getRoleID().equals("Mafia: Hitman")) {

                JLabel roleLimit = new MyLabel("1", Colors.black, new MyFont(20));
                contentPane.add(roleLimit, "wrap, left");

            } else {

                JSpinner numRoleSpin = createSpinner(role);
                
                Dimension d = new Dimension(50, 30);
                numRoleSpin.setPreferredSize(d);
                numRoleSpin.setMinimumSize(d);
                
                JComponent editor = numRoleSpin.getEditor();
                if (editor instanceof JSpinner.DefaultEditor) {
                	JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor)editor;
                	spinnerEditor.getTextField().setHorizontalAlignment(JTextField.CENTER);
                }
                
                contentPane.add(numRoleSpin, "wrap, left");

            }

        }
		
		
	}

	private JSpinner createSpinner(Role role) {

		JSpinner numRoleSpin = new JSpinner(new SpinnerNumberModel());
        spinners.add(numRoleSpin);
        numRoleSpin.addChangeListener(new SpinnerListener(role));
        
        return numRoleSpin;
        
	}
	
	public JPanel getContentPane() {
		return contentPane;
	}
	
	private class SpinnerListener implements ChangeListener {

        private Role associatedRole;

        SpinnerListener(Role role) {
            associatedRole = role;
        }

        @Override
        public void stateChanged(ChangeEvent e) {

            JSpinner spinner = (JSpinner) e.getSource();
            int value = (Integer) spinner.getValue();

            if (value < 0) {
                ((JSpinner) e.getSource()).setValue(0);
            }

            if (value >= totalPlayers) { //TODO fix
                ((JSpinner) e.getSource()).setValue(value);
            }

        }

        private int getAllSpinnerValues() {

            int totalRolesSelected = 0;
            for (JSpinner spin : spinners) {
                totalRolesSelected += (Integer) spin.getValue();
            }

            return totalRolesSelected;

        }

    }

}
