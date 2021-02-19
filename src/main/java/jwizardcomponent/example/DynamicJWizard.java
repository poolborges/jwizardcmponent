package jwizardcomponent.example;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import jwizardcomponent.Utilities;
import jwizardcomponent.JWizardPanel;
import jwizardcomponent.JWizardComponents;
import jwizardcomponent.frame.JWizardFrame;

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
 * @author Piotr Kaminski
 * @version 1.0
 */

public class DynamicJWizard extends JWizardFrame {
    
    public static final int PANEL_FIRST = 0;
    public static final int PANEL_CHOOSER = 1;
    public static final int PANEL_OPTION_A = 2;
    public static final int PANEL_OPTION_B = 3;
    public static final int PANEL_LAST = 4;
    
    public DynamicJWizard() {
        super();
        init();
    }
    
    private void init() {
        this.setTitle("Dynamic JWizardComponent example");
        
        JWizardPanel panel = null;
        
        panel = new FirstWizardPanel(getWizardComponents());
        getWizardComponents().addWizardPanel(PANEL_FIRST, panel);
        
        panel = new ChooserWizardPanel(getWizardComponents());
        getWizardComponents().addWizardPanel(PANEL_CHOOSER, panel);
        
        panel = new OptionWizardPanel(getWizardComponents(), "A");
        getWizardComponents().addWizardPanel(PANEL_OPTION_A, panel);
        
        panel = new OptionWizardPanel(getWizardComponents(), "B");
        getWizardComponents().addWizardPanel(PANEL_OPTION_B, panel);
        
        panel = new LastWizardPanel(getWizardComponents());
        getWizardComponents().addWizardPanel(PANEL_LAST, panel);
        
        setSize(500, 300);
        Utilities.centerComponentOnScreen(this);
    }
    
    public static void main(String [] args) {
        DynamicJWizard wizard = new DynamicJWizard();
        wizard.setVisible(true);
    }
}

class LabelWizardPanel extends JWizardPanel {
    public LabelWizardPanel(JWizardComponents wizardComponents, String label) {
        super(wizardComponents);
        this.setLayout(new GridBagLayout());
        this.add(new JLabel(label)
        , new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
        , GridBagConstraints.CENTER, GridBagConstraints.BOTH
        , new Insets(0, 0, 0, 0), 0, 0));
    }
}
class FirstWizardPanel extends LabelWizardPanel {
    public FirstWizardPanel(JWizardComponents wizardComponents) {
        super(wizardComponents, "First panel");
        setPanelTitle("First simple static panel");
    }
}

class ChooserWizardPanel extends JWizardPanel {
    private JRadioButton optionARadioButton;
    private JRadioButton optionBRadioButton;
    private JRadioButton optionFRadioButton;
    private ButtonGroup bg;
    private char selectedOption = 'N'; // 'N' is no option selected
    // 'A', 'B' & 'F' stands for options
    public ChooserWizardPanel(JWizardComponents wizardComponents) {
        super(wizardComponents, "Choose option A or B or F for finish ");
        init();
    }
    private void init() {
        optionARadioButton = new JRadioButton();
        optionBRadioButton = new JRadioButton();
        optionFRadioButton = new JRadioButton();
        ButtonGroup bg = new ButtonGroup();
        bg.add(optionARadioButton);
        bg.add(optionBRadioButton);
        bg.add(optionFRadioButton);
        this.setLayout(new GridBagLayout());
        
        this.add(optionARadioButton
        , new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.BOTH
        , new Insets(0, 0, 0, 0), 0, 0));
        optionARadioButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedOption = 'A';
                    update();
                }
            }
        });
        this.add(new JLabel("Choose option A")
        , new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.BOTH
        , new Insets(0, 0, 0, 0), 0, 0));
        
        this.add(optionBRadioButton,
        new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.BOTH
        , new Insets(0, 0, 0, 0), 0, 0));
        optionBRadioButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedOption = 'B';
                    update();
                }
            }
        });
        this.add(new JLabel("Choose option B")
        , new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.BOTH
        , new Insets(0, 0, 0, 0), 0, 0));
        
        this.add(optionFRadioButton,
        new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.BOTH
        , new Insets(0, 0, 0, 0), 0, 0));
        optionFRadioButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedOption = 'F';
                    update();
                }
            }
        });
        this.add(new JLabel("Choose option F")
        , new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.BOTH
        , new Insets(0, 0, 0, 0), 0, 0));
    }
    
    public void update() {
        setNextButtonEnabled((selectedOption == 'A') || (selectedOption == 'B') );
        setFinishButtonEnabled(selectedOption == 'F');
        setBackButtonEnabled(false); // there is no way back
    }
    
    public void next() {
        if (selectedOption == 'A') {
            switchPanel(DynamicJWizard.PANEL_OPTION_A);
        } else if (selectedOption == 'B') {
            switchPanel(DynamicJWizard.PANEL_OPTION_B);
        }
    }
    public void back() {
    }
}

class OptionWizardPanel extends LabelWizardPanel {
    public OptionWizardPanel(JWizardComponents wizardComponents, String option) {
        super(wizardComponents, "Option " + option + " was choosed");
        setPanelTitle("Option " + option + " panel");
    }
    public void next() {
        switchPanel(DynamicJWizard.PANEL_LAST);
    }
    public void back() {
        switchPanel(DynamicJWizard.PANEL_CHOOSER);
    }
}

class LastWizardPanel extends LabelWizardPanel {
    public LastWizardPanel(JWizardComponents wizardComponents) {
        super(wizardComponents, "Last panel, you can finish now");
        setPanelTitle("Last simple static panel");
    }
}