package jwizardcomponent.example;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import jwizardcomponent.JWizardComponents;
import jwizardcomponent.JWizardPanel;

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

public class SimpleDynamicWizardPanel extends JWizardPanel {

  /**
   * Note:  Although this says Simple, it is much easier to add the Panels
   * as you go in the next(), previous() using addPanel() and that way you
   * don't have to worry about keeping tracking of the indicies.
   */

  JCheckBox screen3CheckBox;
  JCheckBox screen4CheckBox;
  JCheckBox screen5CheckBox;

  JWizardPanel screen3Panel;
  JWizardPanel screen4Panel;
  JWizardPanel screen5Panel;

  public SimpleDynamicWizardPanel(JWizardComponents wizardComponents) {
    super(wizardComponents);

    screen3CheckBox = new JCheckBox();
    screen4CheckBox = new JCheckBox();
    screen5CheckBox = new JCheckBox();

    ItemListener itemListener = new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        if (e.getStateChange() == ItemEvent.DESELECTED) {
          if (source.equals(screen3CheckBox) &&
              getWizardComponents().getWizardPanelList().contains(screen3Panel)) {
            getWizardComponents().removeWizardPanel(screen3Panel);
          } else if (source.equals(screen4CheckBox) &&
                       getWizardComponents().getWizardPanelList().contains(screen4Panel)) {
              getWizardComponents().removeWizardPanel(screen4Panel);
          } else if (source.equals(screen5CheckBox) &&
                         getWizardComponents().getWizardPanelList().contains(screen5Panel)) {
              getWizardComponents().removeWizardPanel(screen5Panel);
          }
        } else { //ItemEvent.SELECTED
          if (source.equals(screen3CheckBox) &&
              !getWizardComponents().getWizardPanelList().contains(screen3Panel)) {

            screen3Panel = new SimpleLabelWizardPanel(getWizardComponents(),
                new JLabel("Screen 3"));
            getWizardComponents().addWizardPanelAfterCurrent(
                screen3Panel);

          } else if (source.equals(screen4CheckBox) &&
                     !getWizardComponents().getWizardPanelList().contains(screen4Panel)) {

            int index;
            if (getWizardComponents().getWizardPanelList().contains(screen3Panel)) {
              index =
                  getWizardComponents().getWizardPanelList().indexOf(screen3Panel)+1;
            } else {
              index = getWizardComponents().getCurrentIndex()+1;
            }
            screen4Panel = new SimpleLabelWizardPanel(getWizardComponents(),
                new JLabel("Screen 4"));
            getWizardComponents().addWizardPanel(
                index,
                screen4Panel);

          } else if (source.equals(screen5CheckBox) &&
                     !getWizardComponents().getWizardPanelList().contains(screen5Panel)) {

            int index;
			if (getWizardComponents().getWizardPanelList().contains(screen4Panel)) {
						  index =
							  getWizardComponents().getWizardPanelList().indexOf(screen4Panel)+1;
			} else if (getWizardComponents().getWizardPanelList().contains(screen3Panel)) {
              index =
                  getWizardComponents().getWizardPanelList().indexOf(screen3Panel)+1;
            } else {
              index = getWizardComponents().getCurrentIndex()+1;
            }
            screen5Panel = new SimpleLabelWizardPanel(getWizardComponents(),
                new JLabel("Screen 5"));
            getWizardComponents().addWizardPanel(
                index,
                screen5Panel);

          }
        }
      }
    };

    this.setLayout(new GridBagLayout());

    this.add(screen3CheckBox,
             new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
             ,GridBagConstraints.WEST, GridBagConstraints.BOTH,
             new Insets(0, 0, 0, 0), 0, 0));
    screen3CheckBox.addItemListener(itemListener);

    this.add(new JLabel("Show Screen 3"),
             new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
             ,GridBagConstraints.WEST, GridBagConstraints.BOTH,
             new Insets(0, 0, 0, 0), 0, 0));

    this.add(screen4CheckBox,
             new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
             ,GridBagConstraints.WEST, GridBagConstraints.BOTH,
             new Insets(0, 0, 0, 0), 0, 0));
    screen4CheckBox.addItemListener(itemListener);

    this.add(new JLabel("Show Screen 4"),
             new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
             ,GridBagConstraints.WEST, GridBagConstraints.BOTH,
             new Insets(0, 0, 0, 0), 0, 0));

    this.add(screen5CheckBox,
             new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
             ,GridBagConstraints.WEST, GridBagConstraints.BOTH,
             new Insets(0, 0, 0, 0), 0, 0));
    screen5CheckBox.addItemListener(itemListener);

    this.add(new JLabel("Show Screen 5"),
             new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
             ,GridBagConstraints.WEST, GridBagConstraints.BOTH,
             new Insets(0, 0, 0, 0), 0, 0));

  }

  public void next() {
	super.next();
	if (getWizardComponents().onLastPanel()) {
		getWizardComponents().getNextButton().setVisible(false);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				getWizardComponents().getFinishButton().setVisible(true);
				getWizardComponents().getFinishButton().requestFocus();
			}
		});
	} else {
		getWizardComponents().getNextButton().setVisible(true);
		getWizardComponents().getFinishButton().setVisible(false);  		
	}
  }

  public void back() {
	super.back();
	if (getWizardComponents().onLastPanel()) {
		getWizardComponents().getNextButton().setVisible(false);
		getWizardComponents().getFinishButton().setVisible(true);
	} else {
		getWizardComponents().getNextButton().setVisible(true);
		getWizardComponents().getFinishButton().setVisible(false);  		
	}
  }

}