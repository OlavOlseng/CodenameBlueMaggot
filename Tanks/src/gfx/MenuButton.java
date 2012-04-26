package gfx;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import blueMaggot.Game;

public class MenuButton extends JButton {

	public MenuButton(final String label, final String action, final MenuTitle menuTitle, final Game game) {

		setContentAreaFilled(true);
		setText(label);
		setOpaque(true);
		setBorderPainted(false);
		setBackground(Color.red);

		addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent event) {
				System.out.println("Clicked: " + label);
				if (action.equals("exit"))
					System.exit(1);
				if (action.equals("return")) {
					Game.PAUSED = false;
					menuTitle.setVisible(false);
				}
				if (action.equals("newGame")) {
					game.runLoop.stop();
					game.startReuglarGame();
				}
			}
		});
	}
}
