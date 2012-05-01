package baseGame.Rendering;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import level.Terrain;

import blueMaggot.GameState;

public class Renderer {
	private int canvasWidth;
	private int canvasHeight;
	private int[] rgbPixels;
	private int backgroundColor;
	private Graphics2D g;

	private Rectangle[] rects = new Rectangle[2];
	private boolean rect1Changed;
	private boolean recr2Changed;
	public Renderer(Graphics2D g,int[] rgbPixels, int backgroundColor, int canvasWidth, int canvasHeight) {
		rects[0] = new Rectangle(GameState.getInstance().getWidth(), GameState.getInstance().getHeight()/2);
		rects[1] = new Rectangle(0,GameState.getInstance().getHeight()/2,GameState.getInstance().getWidth(), GameState.getInstance().getHeight()/2);
		this.g = g;
		this.rgbPixels = rgbPixels;
		this.canvasWidth = canvasWidth;
		this.canvasHeight = canvasHeight;
		this.backgroundColor = backgroundColor;
	}

	public void DrawImage(RGBImage img, int x, int y, int width, int height) {
		DrawPixelArrayRGB(img.getPixels(), x, y, width, height);
	}
	public void DrawTerrain(Terrain t,int x,int y,int width,int height){
		
		
	}
	public void DrawImage(RGBImage img, int transparentColor, int x, int y, int width, int height) {
		DrawPixelArrayRGB(img.getPixels(), transparentColor, x, y, width, height);
	}

	public void DrawPixelArrayRGB(int[] pixels, int x, int y, int width, int height) {
		int offset = y * canvasWidth;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int adr = offset + x + i * canvasWidth + j;
				if ((j + x) > canvasWidth || (j + x) < 0 || (i + y) < 0 || adr >= rgbPixels.length)
					continue;

				int p = pixels[i * width + j];
			
				rgbPixels[adr] = p;
		
				
			}
		}
	}

	
	public void DrawPixelArrayRGB(int[] pixels, int transparentColor, int x, int y, int width, int height) {
		int offset = y * canvasWidth;

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int adr = offset + x + i * canvasWidth + j;

				if ((j + x) > canvasWidth || (j + x) < 0 || (i + y) < 0 || adr >= rgbPixels.length)
					continue;

				
				int c = pixels[i * width + j];
				if (c != transparentColor) {
					rgbPixels[adr] = c;
					
				}
					
				
			}
		}
	}

	public void clearAllPixelData(int clearColor) {
		for (int i = 0; i < rgbPixels.length; i++) {
			if (rgbPixels[i] != clearColor) {
				rgbPixels[i] = clearColor;
			}
		}
	}

	public void makeTransparent(int color) {
		for (int i = 0; i < rgbPixels.length; i++) {
			if (rgbPixels[i] == color) {
				rgbPixels[i] = backgroundColor;
			}
		}
	}
	
	public Graphics2D getGraphics() {
		return g;
	}

}
