package entity;

import networking.NetworkObjectType;
import gfx.ResourceManager;

import baseGame.Rendering.RGBImage;
import baseGame.Rendering.Renderer;

import level.BasicLevel;

public class Shell extends Projectile {

	int explosionRadius = 25;
	double explosionPower = 150;

	public Shell(double x, double y, BasicLevel level, double speedPercent, double angle) {
		super(x, y, 4, 4, level, speedPercent, angle);
		this.maxSpeed = 25;
		this.frictionConstant = 0.005;
		this.angle = angle;
		this.dx = dx * maxSpeed;
		this.dy = dy * maxSpeed;

	}

	@Override
	public void explode() {

		level.getTerrain().addExplosion((int) (x - explosionRadius), (int) (y - explosionRadius), explosionRadius);
		level.addEntity(new Explosion(x, y, explosionRadius + 2, level, explosionPower));
	}

	public void applyFriction() {
		accelerate(-dx * frictionConstant, -dy * frictionConstant);
	}

	public boolean intersectsTerrain() {
		for (FloatingPoint point : hitbox) {
			if (level.getTerrain().hitTestpoint((int) (x + point.getX()), (int) (y + point.getY()))){
				while(level.getTerrain().hitTestpoint((int) (x + point.getX()), (int) (y + point.getY()))){
					setLocation(x-dx/4, y-dy/4);
				}
				return true;
			}
				
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
	public void tick(double dt) {
		super.tick(dt);

		applyFriction();
		if (intersectsTerrain() || handleIntersections()) {
			explode();
			super.remove();
		}
		gravitate();

	}

	@Override
	public void remove(){
		explode();
		super.remove();
		
	}
	
	
		
	@Override
	public void render(Renderer renderer) {
		RGBImage img = ResourceManager.SHELL;
		renderer.DrawImage(img, -1, (int) (x - getXr()), (int) (y - getYr()), img.getWidth(), img.getHeight());
		// TODO Auto-generated method stub

	}
	
	@Override
	public void initNetworkValues() {
		// TODO Auto-generated method stub
		setNetworkObjectType(NetworkObjectType.SHELL);

	}
}
