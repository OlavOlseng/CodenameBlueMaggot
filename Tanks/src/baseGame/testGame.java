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
	
	public void onUpdate(long deltaTime) {
		tank.tick();
		System.out.println("LOLOL");
	}

	@Override
	public void onDraw(Renderer renderer) {
		renderer.DrawImage(img, new Tank(0,0));
		renderer.DrawImage(img, tank);
	}

}
