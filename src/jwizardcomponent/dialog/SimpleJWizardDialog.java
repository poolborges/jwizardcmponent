package jwizardcomponent.dialog;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;


import jwizardcomponent.CancelAction;
import jwizardcomponent.FinishAction;
import jwizardcomponent.JWizardComponents;
import jwizardcomponent.DefaultJWizardComponents;
import jwizardcomponent.frame.SimpleButtonPanel;

public class SimpleJWizardDialog extends JDialog {
	
	JWizardComponents wizardComponents;
	
	JPanel buttonPanel;
	JLabel statusLabel = new JLabel();
	JPanel bottomPanel = new JPanel();
	
	public SimpleJWizardDialog() {
		wizardComponents = new DefaultJWizardComponents();
		init();
	}
	
	public SimpleJWizardDialog(Frame owner, boolean modal) {
		super(owner, modal);
		wizardComponents = new DefaultJWizardComponents();
		init();
	}
	
	private void init() {
		
		this.getContentPane().setLayout(new GridBagLayout());
		this.getContentPane().add(wizardComponents.getWizardPanelsContainer(),
				new GridBagConstraints(0, 0, 1, 1, 1.0, 0.9
						, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 0, 0), 0, 0));
		
		this.getContentPane().add(new JSeparator(),
				new GridBagConstraints(0, 1, 1, 0, 1.0, 0.01
						,GridBagConstraints.WEST, GridBagConstraints.BOTH,
						new Insets(1, 1, 1, 1), 0, 0));
		
		buttonPanel = new SimpleButtonPanel(wizardComponents);
		this.getContentPane().add(buttonPanel,
				new GridBagConstraints(0, 2, 1, 1, 1.0, 0.09
						,GridBagConstraints.WEST, GridBagConstraints.BOTH,
						new Insets(0, 0, 0, 0), 0, 0));
		
		wizardComponents.setFinishAction(new FinishAction(wizardComponents) {
			public void performAction() {
				dispose();
			}
		});
		wizardComponents.setCancelAction(new CancelAction(wizardComponents) {
			public void performAction() {
				dispose();
			}
		});
		
	}
	
	public JWizardComponents getWizardComponents(){
		return wizardComponents;
	}
	
	public void setWizardComponents(JWizardComponents aWizardComponents){
		wizardComponents = aWizardComponents;
	}
	
	public void show() {
		wizardComponents.updateComponents();
		super.show();
	}
	
}