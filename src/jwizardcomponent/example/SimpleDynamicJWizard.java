package jwizardcomponent.example;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import jwizardcomponent.Utilities;
import jwizardcomponent.frame.SimpleJWizardFrame;

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

public class SimpleDynamicJWizard extends SimpleJWizardFrame {

  public static void main(String [] args) {
    try {
      SimpleJWizardFrame wizardFrame = new SimpleJWizardFrame();
      wizardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      SwingUtilities.updateComponentTreeUI(wizardFrame);

      wizardFrame.setTitle("Simple Dynamic JWizardComponent");

      wizardFrame.getWizardComponents().addWizardPanel(
          new SimpleLabelWizardPanel(wizardFrame.getWizardComponents(),
          new JLabel("Dynamic Test")));

      wizardFrame.getWizardComponents().addWizardPanel(
          new SimpleDynamicWizardPanel(wizardFrame.getWizardComponents()));

      wizardFrame.getWizardComponents().addWizardPanel(
          new SimpleLabelWizardPanel(wizardFrame.getWizardComponents(),
          new JLabel("Done!")));
      wizardFrame.setSize(500, 300);
      Utilities.centerComponentOnScreen(wizardFrame);
      wizardFrame.show();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}