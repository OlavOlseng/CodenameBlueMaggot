package gfx;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MenuLabel extends Label {

	private String label;
	private BufferedImage lblImage;

	public MenuLabel(String label) {
		this.label = label;
		setUp();
	}

	public MenuLabel(String label, Dimension size) {
		this.label = label;
		super.setPreferredSize(size);

		setUp();
	}

	public void setUp() {
		setPreferredSize(new Dimension(170, 13));
		File img = new File("./res/titleMenu/" + label + ".png");
		try {
			lblImage = ImageIO.read(img);
		} catch (IOException e) {
			e.printStackTrace();
		}
		setText(label);
		setBackground(MenuTitle.MENU_BG);
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(MenuTitle.MENU_BG);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(lblImage, 0, (getHeight() - lblImage.getHeight()) / 2, null);
	}

}
