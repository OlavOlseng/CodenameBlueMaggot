package gfx;


import java.awt.Graphics;
import java.awt.Panel;

public class UIElement extends Panel {

	int x, y, width, height, player;

	public UIElement(int x, int y, int width, int height, int player) {
		this.width = width;
		this.height = height;
		this.y = y;
		this.x = x;
		this.player = player;
		setBounds(x, x, width, height);
		
		System.out.println("Creating ui element");
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Menu.blue);
		g.fillRect(0, 0, width ,height);
		if (player == 1) {
			g.drawString("WATTUP", 10, 10);
			System.out.println("sdasd");
		}
		if (player == 2) {
			g.drawString("player", 10, 10);
			System.out.println("lkjlkjlkjs");
		}
	}
}
