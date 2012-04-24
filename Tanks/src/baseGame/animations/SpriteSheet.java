package baseGame.animations;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import baseGame.Rendering.RGBImage;

public class SpriteSheet extends RGBImage {
	private HashMap<Integer,RGBImage[]> animations;
	
	private int spriteWidth;
	private int spriteHeight;
	
	public SpriteSheet(File file,int spriteWidth,int spriteHeigt) {
		super(file);
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeigt;
		animations = new HashMap<Integer,RGBImage[]>();
		System.out.println("yow");
		// TODO Auto-generated constructor stub
		
	}
	
	public RGBImage[] getAnimation(int index) {
		
		RGBImage[] animation = null;
		if(index*spriteHeight <= getHeight() - spriteHeight){
			
			if(animations.containsKey((Integer)index)){
				
				animation = animations.get((Integer)index);
				
			}else{
				
				RGBImage allFrames = getSubImage(0, index*spriteHeight, getWidth(), spriteHeight);
				
				animation = new RGBImage[getWidth()/spriteWidth];
				for(int i = 0;i<animation.length;i++){
					animation[i] = allFrames.getSubImage(i*spriteWidth,0 , spriteWidth, spriteHeight);
					
				}
				animations.put((Integer)index, animation);
				
			}
		}
		return animation;
		
		
	}

}
