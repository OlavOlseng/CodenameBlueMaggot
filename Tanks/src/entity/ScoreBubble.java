package entity;

import networking.NetworkObjectType;
import sound.SoundEffect;
import gfx.ResourceManager;
import baseGame.Rendering.RGBImage;
import baseGame.Rendering.Renderer;
import level.BasicLevel;

public class ScoreBubble extends Entity {

	PixelHitbox hitbox;
	private int scoreAmount;
	private int owner = 0;
	private double lifeTime = 600; 
	private double timeAlive = 0;
	
	public ScoreBubble(FloatingPoint point, BasicLevel level, double speedPercent, int angle, int scoreAmount) {
		super(point.getX(), point.getY(), 4, 4, level);
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
		boolean up = false;
		boolean down = false;
		boolean left = false;
		boolean right = false;

		FloatingPoint point = hitbox.getPoint(0);
		if (level.getTerrain().hitTestpoint((int) (point.getX() + x), (int) (point.getY() + y))) {
			left = true;
		}
		point = hitbox.getPoint(3);
		if (level.getTerrain().hitTestpoint((int) (point.getX() + x), (int) (point.getY() + y))) {
			right = true;
		}
		point = hitbox.getPoint(1);
		if (level.getTerrain().hitTestpoint((int) (point.getX() + x), (int) (point.getY() + y))) {
			up = true;
		}
		point = hitbox.getPoint(4);
		if (level.getTerrain().hitTestpoint((int) (point.getX() + x), (int) (point.getY() + y))) {
			down = true;
		}

		if (up && down && left && right) {
			setLocation(x - dx * 2, y - dy * 2);
			setSpeed(-dx * 0.5, -dy * 0.5);
		} else if (up && down && left) {
			setLocation(x - dx, y);
			setSpeed(-dx * 0.5, dy);
		} else if (up && down && right) {
			setLocation(x - dx, y);
			setSpeed(-dx * 0.5, dy);
		} else if (up && left && right) {
			setLocation(x, y - dy);
			setSpeed(dx, -dy * 0.8);
		} else if (down && left && right) {
			setLocation(x, y - dy);
			setSpeed(dx * 0.6, -dy * 0.5);
		} else if (right || left) {
			setLocation(x - dx, y);
			setSpeed(-dx * 0.5, dy);
		} else if (down || up) {
			setLocation(x, y - dy);
			setSpeed(dx * 0.6, -dy * 0.5);
		}

	}

	public void handlePlayerIntersections() {
		for (Tank player : level.getPlayers()) {
			if (intersectsEntity(player)) {
				owner = player.getPlayerNumber();
				player.addScore(scoreAmount);
				SoundEffect.BUBBLE.play();
				remove();
			}
		}
	}

	@Override
	public void tick(double dt) {
		super.tick(dt);
		handleTerrainIntersections();
		handlePlayerIntersections();
		if (timeAlive > lifeTime)
			remove();
		else
			timeAlive += dt;
		
	}

	@Override
	public void render(Renderer renderer) {
		// TODO Auto-generated method stub
		RGBImage img = ResourceManager.SCOREBUBBLE;
		renderer.DrawImage(img, -1, (int) (x - getXr()), (int) (y - 2 * getYr()), img.getWidth(), img.getHeight());
	}

	@Override
	public String getObject(){
		return super.getObject() + "'" + owner;
	}
	@Override
	public void handleMessage(String[] msg){
		super.handleMessage(msg);
		boolean willDie = Boolean.parseBoolean(msg[3]);
		int owner = Integer.parseInt(msg[6]);
		
		
		
		if(willDie){
			if(owner == 1){
				SoundEffect.BUBBLE.play();
				level.getPlayers().get(0).addScore(100);
				
				remove();
			}else 
				if(owner == 2){
			
			
				SoundEffect.BUBBLE.play();
				level.getPlayers().get(1).addScore(100);
				remove();
			}
			remove();
			
			
		}
	}
	@Override
	public void initNetworkValues() {
		// TODO Auto-generated method stub
		setNetworkObjectType(NetworkObjectType.SCORE_BUBBLE);
	}

}
