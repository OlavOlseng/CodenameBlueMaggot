package gfx;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MenuLevelButton extends Button {

	private Image thumb;
	private Image thumbBg;
	private int width = 100;
	private int height = 80;
	private File lvlTerrain;
	private File lvlBackground;
	private int border = 2;
	private Color btnColor = Color.black;
	private boolean isSelected = false;
	private boolean isHover = false;

	public MenuLevelButton(final File lvl, final MenuLevelSelect menuLevelSelect) {
		lvlTerrain = new File(lvl.getParent() + "\\" + lvl.getName().split("_")[0] + "_terrain.png");
		lvlBackground = new File(lvl.getParent() + "\\" + lvl.getName().split("_")[0] + "_background.png");

		if (!lvlBackground.exists())
			lvlBackground = new File("./lvl/default_background.png");

		setPreferredSize(new Dimension(width, height));
		try {
			thumb = ImageIO.read(lvlTerrain).getScaledInstance(width, height, Image.SCALE_FAST);
			thumbBg = ImageIO.read(lvlBackground).getScaledInstance(width, height, Image.SCALE_FAST);
			System.out.println("adding level: " + lvl.getName().split("_")[0]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// btnColor = Menu.pinkDark;
				repaint();
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				btnColor = Menu.green;
				menuLevelSelect.selectedLevelTerrain = lvlTerrain;
				menuLevelSelect.selectedLevelBackground = lvlBackground;
				for (MenuLevelButton btn : menuLevelSelect.lvlBtns) {
					btn.isSelected = false;
					btn.repaint();
				}
				isSelected = true;
				isHover = false;
				repaint();
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				isHover = false;
				repaint();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				isHover = true;
				repaint();
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
	}

	@Override
	public void paint(Graphics g) {
		if (isHover)
			btnColor = Menu.pinkDark;
		else {
			if (isSelected)
				btnColor = Menu.green;
			else
				btnColor = Color.black;
		}
		g.setColor(btnColor);
		g.fillRect(0, 0, width, height);
		g.drawImage(thumbBg, 0, 0, null);
		g.drawImage(thumb, 0, 0, null);
		g.fillRect(0, 0, border, height);
		g.fillRect(width - border, 0, border, height);
		g.fillRect(0, 0, width, border);
		g.fillRect(0, height - border, width, border);
	}
}
