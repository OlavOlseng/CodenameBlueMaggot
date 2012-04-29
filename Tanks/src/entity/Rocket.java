package entity;

import networking.NetworkObjectType;
import sound.SoundEffect;
import gfx.ResourceManager;
import baseGame.Rendering.RGBImage;
import baseGame.Rendering.Renderer;
import level.BasicLevel;

public class Rocket extends Projectile {

	RGBImage rocketSheet = ResourceManager.ROCKET;
	int explosionRadius = 45;
	double explosionPower = 350;
	double thrust = 0.65;
	double fuel = 45;
	double burnTime = 0;

	public Rocket(double x, double y, BasicLevel level, double speedPercent, double angle) {
		super(x, y, 4, 4, level, speedPercent, angle);
		this.maxSpeed = 8;
		this.frictionConstant = 0.004;
		this.angle = angle;
		this.dx = dx * maxSpeed;
		this.dy = dy * maxSpeed;

	}

	@Override
	public void explode() {
		level.getTerrain().addExplosion((int) (x - explosionRadius), (int) (y - explosionRadius), explosionRadius);
		level.addEntity(new Explosion(x, y, explosionRadius + 2, level, explosionPower));
		SoundEffect.ROCKETLAUNCH.stop();
	}

	public boolean intersectsTerrain() {
		for (FloatingPoint point : hitbox) {
			if (level.getTerrain().hitTestpoint((int) (x + point.getX()), (int) (y + point.getY()))) {
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
		if (Math.sqrt(dx * dx + dy * dy) < maxSpeed && fuel > burnTime) {
			this.accelerate(thrust * Math.cos(Math.toRadians(angle)), -thrust * Math.sin(Math.toRadians(angle)));
		}
		this.angle = (Math.toDegrees(Math.atan2(-dy, dx)) + 360) % 360;
		if (intersectsTerrain() || handleIntersections()) {
			explode();
			super.remove();
		}

		burnTime += dt;
	}

	@Override
	public void render(Renderer renderer) {
		int subimageIndex = 0;
		if (angle > 337.5 || (angle >= 0 && angle <= 22.5))
			subimageIndex = 0;
		else if (angle > 22.5 && angle <= 67.5)
			subimageIndex = 1;
		else if (angle > 67.5 && angle <= 112.5)
			subimageIndex = 2;
		else if (angle > 112.5 && angle <= 157.5)
			subimageIndex = 3;
		else if (angle > 157.5 && angle <= 202.5)
			subimageIndex = 4;
		else if (angle > 202.5 && angle <= 247.5)
			subimageIndex = 5;
		else if (angle > 247.5 && angle <= 292.5)
			subimageIndex = 6;
		else
			subimageIndex = 7;

		RGBImage img = rocketSheet.getSubImage(14 * subimageIndex, 0, 14, 14);
		renderer.DrawImage(img, -1, (int) (x - getXr()), (int) (y - getYr()), img.getWidth(), img.getHeight());

	}
	@Override
	public void remove(){
		super.remove();
		explode();
	}
	@Override
	public String getObject(){
		return super.getObject()+ "'" + encodeToDouble(angle);
	}
	
	@Override
	public void handleMessage(String[] msg){
		super.handleMessage(msg);
		double angle = decodeToDouble(Integer.parseInt(msg[6]));
		this.angle = angle;
	}
	
	@Override
	public void initNetworkValues() {
		// TODO Auto-generated method stub
		
		setNetworkObjectType(NetworkObjectType.ROCKET);

	}

}
