package level;

import java.awt.Color;
import java.io.File;

import baseGame.testGame;
import baseGame.Rendering.RGBImage;

public class Terrain extends RGBImage {

	public Terrain(File file) {
		super(file);
		// TODO Auto-generated constructor stub
	}
	public boolean hitTestpoint(int x,int y){
		return getPixel(x, y) != testGame.ALPHA_MASK;
		
	}
	public void addExplosion(int x,int y,int radius){
		DrawCircle(testGame.ALPHA_MASK, x, y, radius);
	}

	
}
