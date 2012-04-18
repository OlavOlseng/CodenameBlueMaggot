package baseGame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import entity.Tank;

public class testGame extends BaseGame {
	
	BufferedImage img;
	Entity tank;
	public testGame(){
		try {
			img = ImageIO.read(new File("./res/temp_background.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tank  = new Tank(0,200);
		tank.setSpeed(1 , 0);
		
	}
	
	
	
	@Override
<<<<<<< HEAD
	public void onUpdate(long deltaTime) {
		// TODO Auto-generated method stub
		
		
		
		
		
=======
	public void onUppdate(long deltaTime) {
		tank.tick();
		System.out.println("LOLOL");
>>>>>>> 7f729edd6a1948449460b17fdcd20043d78ad6d7
	}

	@Override
	public void onDraw(Renderer renderer) {
<<<<<<< HEAD
		// TODO Auto-generated method stub
		
		
=======
		renderer.DrawImage(img, new Tank(0,0));
		renderer.DrawImage(img, tank);
>>>>>>> 7f729edd6a1948449460b17fdcd20043d78ad6d7
	}

}
