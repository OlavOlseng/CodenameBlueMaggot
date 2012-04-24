package baseGame.animations;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import entity.Animations;

import baseGame.Rendering.RGBImage;

public class AnimationFactory {

	private HashMap<String, SpriteSheet> spriteSheets;
	
	private AnimationFactory(){
		spriteSheets = new HashMap<String, SpriteSheet>();
		
	}
	public void addSpriteSheet(File path,String name,int spriteWidth,int spriteHeight){
		spriteSheets.put(name,new SpriteSheet(path, spriteWidth, spriteHeight));
	}
	public RGBImage[] getAnimation(String spriteSheetName,Integer index){
		
		return spriteSheets.get(spriteSheetName).getAnimation(index);
		
	}
	
	
	private  static AnimationFactory instance = null;
	public static  AnimationFactory getInstance(){
		if(instance == null){
			instance = new AnimationFactory();
		}
		return instance;
	}
}
