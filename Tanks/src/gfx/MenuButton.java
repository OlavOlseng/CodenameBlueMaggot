package gfx;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import blueMaggot.Game;

public class MenuButton extends Button {

	Font f;

	public MenuButton(final String label, final String action, final MenuTitle menuTitle, final Game game) {
		f = new Font("Helvetica", Font.BOLD, 0);
		// setContentAreaFilled(true);
		// setText(label);
		// setOpaque(true);
		// setBorderPainted(false);
		setBackground(Color.red);
		// setFont(new Font("Her"));

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
					game.requestFocus();
				}
				if (action.equals("newGame")) {
					game.runLoop.stop();
					game.startReuglarGame();
				}
				if (action.equals("newOnlineGame")) {
					game.runLoop.stop();
					game.startOnlineGame();
					game.initConnection(false);
				}
				if (action.equals("connFalse"))
					game.initConnection(false);
				if (action.equals("connTrue"))
					game.initConnection(false);
			}
		});
		repaint();
	}

	public void paint(Graphics g) {
		g.setFont(f);
		g.drawString("PIKKKUKKOSV", 0, 0);
	}
}
