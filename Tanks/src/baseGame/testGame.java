package baseGame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import level.Terrain;

import baseGame.Rendering.RGBImage;
import baseGame.Rendering.Renderer;

import entity.Entity;
import entity.Tank;

public class testGame extends BaseGame implements KeyListener {

	public static int WIDTH = 1024;
	public static int HEIGHT = 720;
	public static int ALPHA_MASK = 0xFF0077;
	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	GraphicsDevice gs = ge.getDefaultScreenDevice();
	private class Circle{
		double x;
		double y;
		double dx;
		double dy;
		Color color;
	}
	
	ArrayList<Circle> circles;
	Random randomc = new Random();
	
	int circleImg[] = {0xFF0077};
	RGBImage img;
	Terrain ground = new Terrain(new File("./res/temp_ground.png"));
	RGBImage tank;
	Circle tankEntity = new Circle();
	
	
	public testGame(){
	
		addKeyListener(this);
		setBackgroundColor(Color.BLACK);
		Random random = new Random();
		setBackgroundColor(Color.BLACK);
		
		img = new RGBImage(new File("./res/temp_background.png"));
		
		
		
		
		tank = new RGBImage(new BufferedImage(40, 60, BufferedImage.TYPE_INT_RGB));
		tankEntity.x = 500;
		tankEntity.y = 200;
		tankEntity.dx = 0;
		tankEntity.dy = 3;
		
		for(int ii = 0; ii<tank.getPixels().length;ii++){
			tank.getPixels()[ii] = 0xFF0000;
			
		}
	
		
		ground.addExplosion(500, 400, 50);
		
		
		
		circles = new ArrayList<Circle>();
		
		
	
		//Lager 400000 sirkler
	/*	for (int i = 0;i<10000;i++){
			Circle circle = new Circle();
			circle.x = 400;
			circle.y = 300;
			double rand = random.nextDouble()*2;
			circle.dx = Math.cos(6.28*rand)*random.nextDouble()*5;
			circle.dy = Math.sin(6.28*rand)*random.nextDouble()*5;
			circles.add(circle);
			
			circle.color = new Color(random.nextInt(0xFFFFFF));
		}
		*/
	}
	
	public void onUpdate(long deltaTime) {
		
		if(!ground.hitTestpoint((int)tankEntity.x, (int)tankEntity.y + 61)){
		tankEntity.x += tankEntity.dx*0.1*deltaTime;
		tankEntity.y += tankEntity.dy*0.1*deltaTime;
		}else{
			while(ground.hitTestpoint((int)tankEntity.x,(int)tankEntity.y +60)){
				tankEntity.y-=1;
			}
		
		}
		
		for(Circle circle:circles){
			circle.x += circle.dx*deltaTime*0.01;
			circle.y += circle.dy*deltaTime*0.01;
		
		}
		
		
	}

	@Override
	public void onDraw(Renderer renderer) {
		renderer.clearAllPixelData(Color.BLACK.getRGB());
		renderer.DrawImage(img, 0, 0, 800, 600);
		
		renderer.DrawPixelArrayRGB(ground.getPixels(),ALPHA_MASK, 0, 0, 800, 600);
		
		for(Circle circle:circles){
			renderer.DrawPixelArrayRGB(circleImg,ALPHA_MASK, (int)circle.x,(int) circle.y, 1, 1);
		}
		
		renderer.DrawPixelArrayRGB(tank.getPixels(), (int)tankEntity.x, (int)tankEntity.y, 40, 60);
	//renderer.makeTransparent(ALPHA_MASK);
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		tankEntity.x--;
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
