package gfx;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

	public MenuLevelButton(final File lvl, final MenuLevelSelect menuLevelSelect) {
		lvlTerrain = new File(lvl.getParent() + "\\" + lvl.getName().split("_")[0] + "_terrain.png");
		lvlBackground = new File(lvl.getParent() + "\\" + lvl.getName().split("_")[0] + "_background.png");
		setPreferredSize(new Dimension(width, height));
		try {
			thumb = ImageIO.read(lvlTerrain).getScaledInstance(width, height, Image.SCALE_FAST);
			thumbBg = ImageIO.read(lvlBackground).getScaledInstance(width, height, Image.SCALE_FAST);
			System.out.println("adding level: " + lvl.getName().split("_")[0]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				menuLevelSelect.selectedLevelTerrain = lvlTerrain;
				menuLevelSelect.selectedLevelBackground = lvlBackground;
			}
		});
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		g.drawImage(thumbBg, 0, 0, null);
		g.drawImage(thumb, 0, 0, null);
	}
}
