package baseGame;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Renderable {

	private int x;
	private int y;
	private int radius;
	private Color color;

	public Color getColor() {
		return color;
	}

	public int getRadius() {
		return radius;
	}

	private int height;
	private BufferedImage img;
	private int width;
	private RenderableType renderType;

	public RenderableType getRenderType() {
		return renderType;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public BufferedImage getImg() {
		return img;
	}

	public Renderable(BufferedImage img, int x, int y, int w, int h) {

		renderType = RenderableType.BUFFEREDIMAGE;
		this.img = img;
		width = w;
		height = h;
		this.y = y;
		this.x = x;
	}

	public Renderable(int x, int y, int radius, Color color) {
		renderType = RenderableType.CIRCLE;
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.color = color;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
