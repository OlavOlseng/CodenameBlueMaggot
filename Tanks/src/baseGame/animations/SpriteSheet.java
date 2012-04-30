package baseGame.animations;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import baseGame.Rendering.RGBImage;

public class SpriteSheet extends RGBImage {
	private HashMap<Integer, RGBImage[]> animations;

	private int spriteWidth;
	private int spriteHeight;

	public SpriteSheet(File file, int spriteWidth, int spriteHeigt) {
		super(file);
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeigt;
		animations = new HashMap<Integer, RGBImage[]>();
	}
	public SpriteSheet(BufferedImage img, int spriteWidth, int spriteHeigt) {
		super(img);
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeigt;
		
		animations = new HashMap<Integer, RGBImage[]>();
	}

	public RGBImage[] getAnimation(int index) {
		RGBImage[] animation = null;
		if (index * spriteHeight <= getHeight() - spriteHeight) {
			if (animations.containsKey(index)) {
				animation = animations.get(index);
			} else {
				RGBImage allFrames = getSubImage(0, index * spriteHeight, getWidth(), spriteHeight);
				animation = new RGBImage[getWidth() / spriteWidth];
	
				for (int i = 0; i < animation.length; i++) {
					animation[i] = allFrames.getSubImage(i * spriteWidth, 0, spriteWidth, spriteHeight);
				}
				animations.put(index, animation);
			}
		}
		return animation;
	}
}
