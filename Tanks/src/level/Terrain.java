package level;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import baseGame.Rendering.RGBImage;
import blueMaggot.Game;

public class Terrain extends RGBImage {

	public Terrain(File file) throws IOException  {
		super(file);
	}

	public Terrain(BufferedImage img) {
		super(img);
	}

	public boolean hitTestpoint(int x, int y) {

		if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight())
			return false;

		return getPixel(x, y) != Game.ALPHA_MASK;

	}

	public void addExplosion(int x, int y, int radius) {
		DrawCircle(Game.ALPHA_MASK, x, y, radius);
	}

	private void DrawCircle(int color, int x0, int y0, int radius) {
		int width = radius * 2 + 2;
		int[] pixels = new int[width * width];

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = -100;
		}

		int xf = radius;
		int yf = radius;

		int f = 1 - radius;
		int ddF_x = 1;
		int ddF_y = -2 * radius;
		int x = 0;
		int y = radius;

		setPixel(pixels, width, color, xf, yf + radius);
		setPixel(pixels, width, color, xf, yf - radius);
		setPixel(pixels, width, color, xf + radius, yf);
		setPixel(pixels, width, color, xf - radius, yf);

		while (x < y) {

			if (f >= 0) {
				y--;
				ddF_y += 2;
				f += ddF_y;
			}
			x++;
			ddF_x += 2;
			f += ddF_x;
			setPixel(pixels, width, color, xf + x, yf + y);
			setPixel(pixels, width, color, xf - x, yf + y);
			setPixel(pixels, width, color, xf + x, yf - y);
			setPixel(pixels, width, color, xf - x, yf - y);
			setPixel(pixels, width, color, xf + y, yf + x);
			setPixel(pixels, width, color, xf - y, yf + x);
			setPixel(pixels, width, color, xf + y, yf - x);
			setPixel(pixels, width, color, xf - y, yf - x);
		}

		boolean doFill = false;
		int Y = 0;
		for (int X = 0; X < pixels.length * 0.25; X++) {

			if (X % (width / 2) == 0) {
				pixels[(X - 1) % (width / 2) + Y * width + 1] = color;
				pixels[(X - 1) % (width / 2) + Y * width + 1 + pixels.length / 2] = color;

				Y++;
				doFill = false;

			}
			int posX = X % (width / 2);
			int posY = Y * width;
			int pixelC = pixels[posX + posY];
			if (doFill) {

				pixels[posX + posY] = color;
				pixels[width - posX + posY] = color;

				pixels[pixels.length - (posX + posY)] = color;

				pixels[pixels.length - (width - posX + posY)] = color;
			}
			if (pixelC == color) {
				doFill = true;
			}

		}
		pixels[width / 2 * width + width / 2] = color;

		DrawRGBImage(pixels, -100, x0, y0, width, width);

	}
}
