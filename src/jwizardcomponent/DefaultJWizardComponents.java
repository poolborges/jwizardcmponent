package jwizardcomponent;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * <p>Title: DefaultJWizardComponents</p>
 * <p>Description: Swing-Based Wizard Framework for Wizards</p>
 * <p>Copyright (C) 2003 William Ready
 * 
 * <br>This library is free software; you can redistribute it and/or
 * <br>modify it under the terms of the GNU Lesser General Public
 * <br>License as published by the Free Software Foundation; either
 * <br>version 2.1 of the License, or (at your option) any later version.
 *
 * <br>This library is distributed in the hope that it will be useful,
 * <br>but WITHOUT ANY WARRANTY; without even the implied warranty of
 * <br>MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * <br>See the GNU Lesser General Public License for more details.
 *
 * <br>To receive a copy of the GNU Lesser General Public License 
 * <br>write to:  The Free Software Foundation, Inc., 
 * <br>59 Temple Place, Suite 330 
 * <br>Boston, MA 02111-1307 USA</p>
 * @author William Ready
 * @version 1.0
 */

public class DefaultJWizardComponents implements JWizardComponents {

	JButton backButton;
	JButton nextButton;
	JButton finishButton;
	JButton cancelButton;

	FinishAction finishAction;
	CancelAction cancelAction;

	List panelList;
	int currentIndex;
	JPanel wizardPanelsContainer;
	
	/**
	 * This class is the "bread and butter" of this framework.  All of these
	 * components can be used visually however you want, as shown in the
	 * frame and example packages, but all a developer really needs is this,
	 * and they can even instead implement JWizardComponents and choose to do this
	 * portion any way they wish.
	 */
	public DefaultJWizardComponents() {
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addWizardPanel(WizardPanel panel) {
		getWizardPanelList().add(panel);
		wizardPanelsContainer.add(panel,
			getWizardPanelList().size() - 1 + "");
	}

	public void addWizardPanel(int index, WizardPanel panel) {
		getWizardPanelList().add(index, panel);
		wizardPanelsContainer.add(panel, index + "", index);
		if (index < getWizardPanelList().size() - 1) {
			for (int i = index + 1; i < getWizardPanelList().size(); i++) {
				wizardPanelsContainer.add(
					(WizardPanel)getWizardPanelList().get(i),
					i + "");
			}
		}
	}

	public void addWizardPanelAfter(
		WizardPanel panelToBePlacedAfter,
		WizardPanel panel) {
		addWizardPanel(
			getWizardPanelList().indexOf(panelToBePlacedAfter) + 1,
			panel);
	}

	public void addWizardPanelBefore(
		WizardPanel panelToBePlacedBefore,
		WizardPanel panel) {
		addWizardPanel(
			getWizardPanelList().indexOf(panelToBePlacedBefore) - 1,
			panel);
	}

	public void addWizardPanelAfterCurrent(WizardPanel panel) {
		addWizardPanel(getCurrentIndex()+1, panel);
	}
	
	public WizardPanel removeWizardPanel(WizardPanel panel) {
		int index = getWizardPanelList().indexOf(panel);
		getWizardPanelList().remove(panel);
		wizardPanelsContainer.remove(panel);
		for (int i = index; i < getWizardPanelList().size(); i++) {
			wizardPanelsContainer.add(
				(WizardPanel) getWizardPanelList().get(i),
				i + "");
		}
		return panel;
	}

	public WizardPanel removeWizardPanel(int index) {
		wizardPanelsContainer.remove(index);
		WizardPanel panel = (WizardPanel) getWizardPanelList().remove(index);
		for (int i = index; i < getWizardPanelList().size(); i++) {
			wizardPanelsContainer.add(
				(WizardPanel) getWizardPanelList().get(i),
				i + "");
		}
		return panel;
	}

	public WizardPanel removeWizardPanelAfter(WizardPanel panel) {
		return removeWizardPanel(getWizardPanelList().indexOf(panel) + 1);
	}

	public WizardPanel removeWizardPanelBefore(WizardPanel panel) {
		return removeWizardPanel(getWizardPanelList().indexOf(panel) - 1);
	}

	public WizardPanel getWizardPanel(int index) {
		return (WizardPanel) getWizardPanelList().get(index);
	}

	public int getIndexOfPanel(WizardPanel panel) {
		return getWizardPanelList().indexOf(panel);
	}

	public boolean onLastPanel() {
		return (getCurrentIndex() == getWizardPanelList().size() - 1);
	}

	private void init() throws Exception {

		backButton = new JButton();
		nextButton = new JButton();
		finishButton = new JButton();
		cancelButton = new JButton();
		
		panelList = new ArrayList();
		currentIndex = 0;
		wizardPanelsContainer = new JPanel();

		backButton.setText("< Back");
		backButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backButton_actionPerformed(e);
			}
		});

		nextButton.setText("Next >");
		nextButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextButton_actionPerformed(e);
			}
		});

		cancelButton.setText("Cancel");
		cancelButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getCancelAction().performAction();
			}
		});

		finishButton.setText("Finish ");
		finishButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFinishAction().performAction();
			}
		});

		wizardPanelsContainer.setLayout(new CardLayout());
	}

	void cancelButton_actionPerformed(ActionEvent e) {
		cancelAction.performAction();
	}

	void nextButton_actionPerformed(ActionEvent e) {
		try {
			getCurrentPanel().next();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	void backButton_actionPerformed(ActionEvent e) {
		try {
			getCurrentPanel().back();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public WizardPanel getCurrentPanel() throws Exception {
		if (getWizardPanelList().get(currentIndex) != null) {
			return (WizardPanel) getWizardPanelList().get(currentIndex);
		} else {
			throw new Exception("No panels in panelList");
		}
	}

	public void updateComponents() {
		try {
			CardLayout cl = (CardLayout) (wizardPanelsContainer.getLayout());
			cl.show(wizardPanelsContainer, currentIndex + "");

			if (currentIndex == 0) {
				backButton.setEnabled(false);
			} else {
				backButton.setEnabled(true);
			}

			if (onLastPanel()) {
				nextButton.setEnabled(false);
				finishButton.setEnabled(true);
			} else {
				finishButton.setEnabled(false);
				nextButton.setEnabled(true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Getters and Setters from here on ...

	public List getWizardPanelList() {
		return this.panelList;
	}

	public void setWizardPanelList(ArrayList panelList) {
		this.panelList = panelList;
	}

	public FinishAction getFinishAction() {
		return finishAction;
	}

	public void setFinishAction(FinishAction aFinishAction) {
		finishAction = aFinishAction;
	}

	public CancelAction getCancelAction() {
		return cancelAction;
	}

	public void setCancelAction(CancelAction aCancelAction) {
		cancelAction = aCancelAction;
	}

	public int getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(int aCurrentIndex) {
		currentIndex = aCurrentIndex;
	}

	public JPanel getWizardPanelsContainer() {
		return wizardPanelsContainer;
	}

	public void setWizardPanelsContainer(JPanel aWizardPanelsContainer) {
		wizardPanelsContainer = aWizardPanelsContainer;
	}

	public JButton getBackButton() {
		return backButton;
	}

	public void setBackButton(JButton aBackButton) {
		backButton = aBackButton;
	}

	public JButton getNextButton() {
		return nextButton;
	}

	public void setNextButton(JButton aNextButton) {
		nextButton = aNextButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(JButton aCancelButton) {
		cancelButton = aCancelButton;
	}

	public JButton getFinishButton() {
		return finishButton;
	}

	public void setFinishButton(JButton button) {
		finishButton = button;
	}

	public void setWizardPanelList(List panelList) {
		this.panelList = panelList;		
	}

}