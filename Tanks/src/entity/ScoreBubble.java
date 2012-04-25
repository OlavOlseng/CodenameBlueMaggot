package entity;

import gfx.ResourceManager;
import baseGame.Rendering.RGBImage;
import baseGame.Rendering.Renderer;
import level.BasicLevel;

public class ScoreBubble extends Entity {

	PixelHitbox hitbox;
	private int scoreAmount;
	
	public ScoreBubble(double x, double y, int size, BasicLevel level,
			double speedPercent, int angle, int scoreAmount) {
		super(x + size * Math.cos(Math.toDegrees(angle)), y - size
				* Math.sin(Math.toDegrees(angle)), size, size, level);
		this.angle = angle;
		this.dx = (speedPercent * Math.cos(angle % 360 * 2 * Math.PI / 360));
		this.dy = (speedPercent * -Math.sin(angle % 360 * 2 * Math.PI / 360));
		this.hitbox = new PixelHitbox();
		this.scoreAmount = scoreAmount;
		init();
	}

	public void init() {
		hitbox.addPoint(new FloatingPoint(-xr, 0));
		hitbox.addPoint(new FloatingPoint(0, -yr));
		hitbox.addPoint(new FloatingPoint(0, 0));
		hitbox.addPoint(new FloatingPoint(0, yr));
		hitbox.addPoint(new FloatingPoint(xr, 0));
	}

	public void handleTerrainIntersections() {
		FloatingPoint point = hitbox.getPoint(0);
		if (level.getTerrain().hitTestpoint((int) (point.getX() + x),
				(int) (point.getY() + y))) {
			this.setSpeed(-dx * 0.55, dy);
			this.setLocation(x + 1, y);
		}
		point = hitbox.getPoint(4);
		if (level.getTerrain().hitTestpoint((int) (point.getX() + x),
				(int) (point.getY() + y))) {
			this.setSpeed(-dx * 0.55, dy);
			this.setLocation(x - 1, y);
		}

		point = hitbox.getPoint(1);
		if (level.getTerrain().hitTestpoint((int) (point.getX() + x),
				(int) (point.getY() + y))) {
			this.setSpeed(dx, -dy * 0.55);
			this.setLocation(x, y + 1);
		}
		point = hitbox.getPoint(3);
		if (level.getTerrain().hitTestpoint((int) (point.getX() + x),
				(int) (point.getY() + y))) {
			this.setSpeed(dx, -dy * 0.3);
			this.setLocation(x, y - 1);
		}
	}
	
	public void handlePlayerIntersections(){
		for (Tank player : level.getPlayers()) {
			if(intersectsEntity(player)){
				player.addScore(scoreAmount);
				remove();
			}
		}
	}
	
	public void tick(double dt){
		super.tick(dt);
		handleTerrainIntersections();
		handlePlayerIntersections();
	}

	@Override
	public void render(Renderer renderer) {
		// TODO Auto-generated method stub
		RGBImage img = ResourceManager.SCOREBUBBLE;
		renderer.DrawImage(img, -1, (int) (x - getXr()),(int) (y - getYr()), img.getWidth(), img.getHeight());
	}
	
}
