/*
 * Created on Oct 15, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package jwizardcomponent;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * <p>Title: JWizardComponents.java</p>
 * <p>Project: JWizardComponent</p>
 * <p>Copyright (C) 2003 William Ready
 * 
 * <br>This library is free software; you can redistribute it and/or
 * <br>modify it under the terms of the GNU Lesser General Public
 * <br>License as published by the Free Software Foundation; either
 * <br>version 2.1 of the License, or (at your option) any later version.
 *
 * <br>This library is distributed in the hope that it will be useful,
 * <br>but WITHOUT ANY WARRANTY; without even the 
 * <br>implied warranty of MERCHANTABILITY or 
 * <br>FITNESS FOR A PARTICULAR PURPOSE. 
 * <br>See the GNU Lesser General Public License for more details.
 *
 * <br>To receive a copy of the GNU Lesser General Public License 
 * <br>write to:  The Free Software Foundation, Inc., 
 * <br>59 Temple Place, Suite 330 
 * <br>Boston, MA 02111-1307 USA</p>
 * @author William Ready
 * @version 0.9
 */
public interface JWizardComponents extends JWizard {

	public void addWizardPanel(WizardPanel panel);
	
	public void addWizardPanel(int index, WizardPanel panel);

	public void addWizardPanelAfter(
		WizardPanel panelToBePlacedAfter,
		WizardPanel panel);

	public void addWizardPanelBefore(
		WizardPanel panelToBePlacedBefore,
		WizardPanel panel);
		
	public void addWizardPanelAfterCurrent(WizardPanel panel);
	
	public WizardPanel removeWizardPanel(WizardPanel panel);

	public WizardPanel removeWizardPanel(int index);

	public WizardPanel removeWizardPanelAfter(WizardPanel panel);

	public WizardPanel removeWizardPanelBefore(WizardPanel panel);

	public WizardPanel getWizardPanel(int index);

	public int getIndexOfPanel(WizardPanel panel);	

	public void updateComponents();

	public WizardPanel getCurrentPanel() throws Exception;

	public FinishAction getFinishAction();

	public void setFinishAction(FinishAction aFinishAction);

	public CancelAction getCancelAction();

	public void setCancelAction(CancelAction aCancelAction);

	public int getCurrentIndex();

	public void setCurrentIndex(int aCurrentIndex);

	public JPanel getWizardPanelsContainer();

	public void setWizardPanelsContainer(JPanel aWizardPanelsContainer);

	public JButton getBackButton();

	public void setBackButton(JButton aBackButton);

	public JButton getNextButton();

	public void setNextButton(JButton aNextButton);

	public JButton getCancelButton();

	public void setCancelButton(JButton aCancelButton);

	public JButton getFinishButton();

	public void setFinishButton(JButton button);
	
	public List getWizardPanelList();

	public void setWizardPanelList(List panelList);
	
	public boolean onLastPanel();		

}
