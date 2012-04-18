package gfx;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ButtonTest {

  public static void main(String[] a) {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JToggleButton toggleButton = new JToggleButton("Selected");

    ActionListener actionListener = new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
        boolean selected = abstractButton.getModel().isSelected();
        System.out.println("Action - selected=" + selected + "\n");
      }
    };

    ChangeListener changeListener = new ChangeListener() {
      public void stateChanged(ChangeEvent changeEvent) {
        AbstractButton abstractButton = (AbstractButton) changeEvent.getSource();
        ButtonModel buttonModel = abstractButton.getModel();
        boolean armed = buttonModel.isArmed();
        boolean pressed = buttonModel.isPressed();
        boolean selected = buttonModel.isSelected();
        System.out.println("Changed: " + armed + "/" + pressed + "/" + selected);
      }
    };


    ItemListener itemListener = new ItemListener() {
      public void itemStateChanged(ItemEvent itemEvent) {
        int state = itemEvent.getStateChange();
        if (state == ItemEvent.SELECTED) {
          System.out.println("Selected");
        } else {
          System.out.println("Deselected");
        }
      }
    };

    toggleButton.addActionListener(actionListener);
    toggleButton.addChangeListener(changeListener);
    toggleButton.addItemListener(itemListener);
    frame.add(toggleButton, BorderLayout.NORTH);

    JToggleButton toggleButton2 = new JToggleButton("Focused");
    frame.add(toggleButton2, BorderLayout.CENTER);

    JToggleButton toggleButton3 = new JToggleButton("Not Selected");
    frame.add(toggleButton3, BorderLayout.SOUTH);

    frame.setSize(300, 125);
    frame.setVisible(true);
  }
}