package gfx;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;


public class MenuBackground extends Menu {
	
	private MenuTitle menuTitle;

	public MenuBackground(MenuTitle menuTitle) {
		this.menuTitle = menuTitle;
		setBackground(Color.black);
		width += border * 2;
		height += border * 2;
		setPreferredSize(new Dimension(width, height));
		putCenter(this, width, height);
	}

	@Override
	public void paint(Graphics g) {
		setVisible(menuTitle.isVisible());
		System.out.println("painting");
	}
}
