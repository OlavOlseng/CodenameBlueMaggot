package gfx;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MenuButton extends JButton {

	public MenuButton(final String label, final String action) {

		setContentAreaFilled(true);
		setText(label);
		setOpaque(true);
		setBorderPainted(false);
		setBackground(Color.red);

		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Clicked: " + label);
				if (action.equals("exit"))
					System.exit(1);
			}
		});
	}
}
