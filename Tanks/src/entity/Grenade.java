package entity;

import networking.NetworkObjectType;
import gfx.ResourceManager;
import baseGame.Rendering.RGBImage;
import baseGame.Rendering.Renderer;
import blueMaggot.GameState;
import level.BasicLevel;

public class Grenade extends Projectile {

	private int liveTime = 0;
	private int explosionTime = 150;
	int explosionRadius = 40;
	double explosionPower = 250;

	public Grenade(double x, double y, BasicLevel level, double speedPercent, double angle) {
		super(x, y, 4, 4, level, speedPercent, angle);
		this.maxSpeed = 13;
		this.frictionConstant = 0.0004;
		this.angle = angle;
		this.dx = dx * maxSpeed;
		this.dy = dy * maxSpeed;
		// hitbox = new PixelHitbox();
	}

	@Override
	public void explode() {
		level.getTerrain().addExplosion((int) (x - explosionRadius), (int) (y - explosionRadius), explosionRadius);
		level.addEntity(new Explosion(x, y, explosionRadius + 2, level, explosionPower));

	}

	public void applyFriction() {
		accelerate(-dx * frictionConstant, -dy * frictionConstant/2);
	}

	public void handleIntersects() {
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
			liveTime += dt * 5;
			setLocation(x - dx * 2, y - dy * 2);
			setSpeed(-dx * 0.4, -dy * 0.4);
		} else if (up && down && left) {
			setLocation(x - dx, y);
			setSpeed(-dx * 0.4, dy * 0.6);
		} else if (up && down && right) {
			setLocation(x - dx, y);
			setSpeed(-dx * 0.4, dy * 0.6);
		} else if (up && left && right) {
			setLocation(x, y - dy);
			setSpeed(dx * 0.5, -dy * 0.6);
		} else if (down && left && right) {
			setLocation(x, y - dy);
			setSpeed(dx * 0.4, -dy * 0.4);
		} else if (right || left) {
			setLocation(x - dx, y);
			setSpeed(-dx * 0.5, dy*0.7);
		} else if (down || up) {
			setLocation(x, y - dy);
			setSpeed(dx * 0.4, -dy * 0.4);
		}

	}
	
	@Override
	public void gravitate() {
		accelerate(0, 0.14);
	}

	@Override
	public void tick(double dt) {
		this.dt = dt;
		super.move(dt);
		gravitate();
		if (x > GameState.getInstance().getWidth() + 100 || x < -100 || y > GameState.getInstance().getHeight() + 100 || y < -1000)
			remove();

		handleIntersects();
		applyFriction();
		liveTime += dt;
		if (liveTime >= explosionTime) {
			explode();
			super.remove();
		}
	}

	@Override
	public void remove(){
		super.remove();
		explode();
	}
	@Override
	public void render(Renderer renderer) {
		RGBImage img = ResourceManager.GRENADE;
		renderer.DrawImage(img, -1, (int) (x - getXr()), (int) (y - getYr()), img.getWidth(), img.getHeight());

	}

	@Override
	public void initNetworkValues() {
		// TODO Auto-generated method stub
		setNetworkObjectType(NetworkObjectType.GRENADE);
	}
}
