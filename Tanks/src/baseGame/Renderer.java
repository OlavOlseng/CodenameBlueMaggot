package baseGame;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entity.Entity;


public class Renderer {

	public ArrayList<Renderable> getToBeDrawn() {
		return toBeDrawn;
	}
	
	private ArrayList<Renderable> toBeDrawn;	
	
	public Renderer(){
		
		toBeDrawn = new ArrayList<Renderable>();
	}
	public void DrawImage(BufferedImage img,int x,int y,int width,int height){
		
		toBeDrawn.add(new Renderable(img,x,y,width,height));
		
	}
	public void DrawCircle(int x,int y,int radius,Color color){
		toBeDrawn.add(new Renderable(x, y, radius, color));
	}
}
