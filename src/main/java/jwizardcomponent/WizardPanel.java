package jwizardcomponent;

import javax.swing.JPanel;

/**
 * <p>Title: JWizardComponent</p>
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

public abstract class WizardPanel extends JPanel {

  JWizardComponents wizardComponents;

  public WizardPanel(JWizardComponents wizardComponents) {
    this.wizardComponents = wizardComponents;
  }

  public void next() {
    if (wizardComponents.getWizardPanelList().size() 
    		> wizardComponents.getCurrentIndex()+1 ) {
      wizardComponents.setCurrentIndex(wizardComponents.getCurrentIndex()+1);
      wizardComponents.updateComponents();
    } 
  }

  public void back() {
    if (wizardComponents.getCurrentIndex()-1 >= 0) {
      wizardComponents.setCurrentIndex(wizardComponents.getCurrentIndex()-1);
      wizardComponents.updateComponents();
    }
  }

  public JWizardComponents getWizardComponents(){
    return wizardComponents;
  }

  public void setWizardComponents(JWizardComponents awizardComponents){
    wizardComponents = awizardComponents;
  }

}