package baseGame;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import entity.Entity;
import entity.Tank;

public class testGame extends BaseGame {
	private class Circle{
		double x;
		double y;
		double dx;
		double dy;
		Color color;
	}
	
	ArrayList<Circle> circles;
	public testGame(){
		
		
	setBackgroundColor(Color.BLACK);
		Random random = new Random();
		
		circles = new ArrayList<Circle>();
		//Lager 3000 sirkler
		for (int i = 0;i<3000;i++){
			Circle circle = new Circle();
			circle.x = 400;
			circle.y = 300;
			double rand = random.nextDouble()*2;
			circle.dx = Math.cos(6.28*rand)*random.nextDouble()*5;
			circle.dy = Math.sin(6.28*rand)*random.nextDouble()*5;
			circles.add(circle);
			
			circle.color = new Color(random.nextFloat(),random.nextFloat(),random.nextFloat());
		}
		
	}
	
	public void onUpdate(long deltaTime) {
	
		for(Circle circle:circles){
			circle.x += circle.dx*deltaTime*0.01;
			circle.y += circle.dy*deltaTime*0.01;
		
		}
		
	}

	@Override
	public void onDraw(Renderer renderer) {
		
		for(Circle circle:circles){
			renderer.DrawCircle((int)circle.x,(int) circle.y, 1, circle.color);
		}
		
		
	}

}