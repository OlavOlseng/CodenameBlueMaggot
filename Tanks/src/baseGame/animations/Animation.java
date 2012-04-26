package baseGame.animations;

import level.BasicLevel;
import entity.Entity;
import gfx.ResourceManager;
import baseGame.Rendering.RGBImage;
import baseGame.Rendering.Renderer;

public class Animation extends Entity {

	private int duration;
	private RGBImage[] frames;
	private double currentFrame = 0;
	private double framesPerTick;

	public Animation(RGBImage[] frames, int duration, int startFrame, double x, double y, BasicLevel level) {
		super(x, y, frames[0].getWidth() / 2, frames[0].getHeight() / 2, level);
		framesPerTick = (double) frames.length / (double) duration;
		this.duration = duration;
		this.frames = frames;

		this.currentFrame = startFrame;

	}

	@Override
	public void tick(double dt) {
		if (currentFrame + 1 < frames.length) {

			currentFrame += framesPerTick;
		} else {
			remove();
		}
	}

	public RGBImage nextFrame() {

		return frames[(int) Math.floor(currentFrame)];
	}

	@Override
	public void render(Renderer renderer) {
		// TODO Auto-generated method stub
		RGBImage img = nextFrame();
		renderer.DrawImage(img, -1, (int) (x - getXr()), (int) (y - getYr()), img.getWidth(), img.getHeight());

	}
}
