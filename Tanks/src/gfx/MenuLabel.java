package gfx;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MenuLabel extends Panel {


	private Labels label;
	private BufferedImage lblImage;
	
	public MenuLabel(Labels logo) {
		this.label = logo;
		setUp();
	}

	public MenuLabel(Labels label, Dimension size) {
		this.label = label;
		setPreferredSize(size);
		setUp();
	}

	public void setUp() {
		setPreferredSize(new Dimension(170, 13));
		String img = "/titleMenu/" + label + ".png";
		try {
			lblImage = ImageIO.read(getClass().getResourceAsStream(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
//		setText(label.toString());
		setBackground(Menu.blue);
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Menu.blue);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(lblImage, 0, (getHeight() - lblImage.getHeight()) / 2, null);
	}

}
