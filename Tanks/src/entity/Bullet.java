package entity;

import gfx.ResourceManager;
import baseGame.Rendering.RGBImage;
import baseGame.Rendering.Renderer;
import level.BasicLevel;

public class Bullet extends Projectile {
	
	int explosionRadius = 10;
	int explosionPower = 50;
	
	public Bullet(double x, double y, BasicLevel level, double angle) {
		super(x, y, 2, 2, level, 1, angle);
		this.maxSpeed = 10;
		this.frictionConstant = 0.00005;
		this.angle = angle;
		this.dx = dx * maxSpeed;
		this.dy = dy * maxSpeed;
	}

	@Override
	public void explode() {
		level.getTerrain().addExplosion((int) (x - explosionRadius), (int) (y - explosionRadius), explosionRadius);
		level.addEntity(new Explosion(x, y, explosionRadius + 2, level, explosionPower));
	}

	@Override
	public void initNetworkValues() {
		// TODO Auto-generated method stub
		
	}

	public boolean intersectsTerrain() {
		for (FloatingPoint point : hitbox) {
			if (level.getTerrain().hitTestpoint((int) (x + point.getX()), (int) (y + point.getY())))
				return true;
		}
		return false;
	}
	
	public boolean handleIntersections() {
		for (int i = 0; i < level.getEntities().size(); i++) {
			Entity ent = level.getEntities().get(i);
			if (ent == this)
				continue;
			if (intersectsEntity(level.getEntities().get(i)))
				return true;
		}

		return false;
	}
	
	@Override
	public void tick(double dt){
		super.tick(dt);
		if(intersectsTerrain() || handleIntersections()){
			explode();
			remove();
		}
	}
	
	@Override
	public void render(Renderer renderer) {
		RGBImage img = ResourceManager.SHELL;
		renderer.DrawImage(img, -1, (int) (x - getXr()), (int) (y - getYr()), img.getWidth(), img.getHeight());
	}
	

}
