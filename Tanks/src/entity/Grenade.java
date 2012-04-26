package entity;

import gfx.ResourceManager;
import baseGame.Rendering.RGBImage;
import baseGame.Rendering.Renderer;
import baseGame.animations.Animation;
import baseGame.animations.AnimationFactory;
import level.BasicLevel;

public class Grenade extends Projectile {

	private int liveTime = 0;
	private int explosionTime = 150;
	int explosionRadius = 30;
	double explosionPower = 150;

	public Grenade(double x, double y, BasicLevel level, double speedPercent, double angle) {
		super(x, y, 4, 4, level, speedPercent, angle);
		this.maxSpeed = 9;
		this.frictionConstant = 0.0005;
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
		accelerate(-dx * frictionConstant, -dy * frictionConstant);
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
			
		} else if (up && down && left) {
			setLocation(x - dx, y);
		} else if (up && down && right) {
			setLocation(x - dx, y);
			setSpeed(-dx * 0.4, dy);
		} else if (up && left && right) {
			setLocation(x, y - dy);
			setSpeed(dx * 0.8, -0.8 * dy);
		} else if (down && left && right) {
			setLocation(x, y - dy);
			setSpeed(dx * 0.8, -0.8 * dy);
		} else if (right || left) {
			setLocation(x - dx, y);
			setSpeed(-dx * 0.4, dy);
		} else if (down || up) {
			setLocation(x, y - dy);
			setSpeed(0.6 * dx, -dy * 0.4);
		}

	}

	public void tick(double dt) {
		super.tick(dt);
		handleIntersects();
		applyFriction();
		liveTime += dt;
		if (liveTime >= explosionTime) {
			explode();
			remove();
		}
	}

	@Override
	public void render(Renderer renderer) {
		RGBImage img = ResourceManager.GRENADE;
		renderer.DrawImage(img, -1, (int) (x - getXr()), (int) (y - getYr()), img.getWidth(), img.getHeight());

	}
}
