package entity;

import networking.NetworkObjectType;
import sound.SoundEffect;
import gfx.ResourceManager;
import baseGame.Rendering.RGBImage;
import baseGame.Rendering.Renderer;
import level.BasicLevel;

public class AirStrikeBeacon extends Projectile {
	RGBImage beaconSheet = ResourceManager.AIRSTRIKEBEACON;
	private boolean touchingGround = false;
	private boolean triggered = false;
	private double calldownTime = 150;
	private int explosionRadius = 10;
	private int explosionPower = 10;

	public AirStrikeBeacon(double x, double y, BasicLevel level, double speedPercent, double angle) {
		super(x, y, 6, 6, level, speedPercent, angle);
		this.maxSpeed = 9;
		this.frictionConstant = 0.005;
		this.angle = angle;
		this.dx = dx * maxSpeed;
		this.dy = dy * maxSpeed;
	}

	public void handleIntersections() {
		touchingGround = false;
		for (FloatingPoint point : hitbox) {
			if (level.getTerrain().hitTestpoint((int) (x + point.getX()), (int) (y + point.getY()))) {
				while (level.getTerrain().hitTestpoint((int) (x), (int) (y))) {
					setLocation(x - dx / 2, y - dy / 2);
				}
				setSpeed(0, 0);
				touchingGround = true;
				if (!triggered)
					trigger();
			}
		}
	}

	@Override
	public void gravitate() {
	};

	private void trigger() {
		triggered = true;
		SoundEffect.CALLDOWN1.play();
	}

	@Override
	public void explode() {
		level.getTerrain().addExplosion((int) (x - explosionRadius), (int) (y - explosionRadius), explosionRadius);
		level.addEntity(new Explosion(x, y, explosionRadius + 2, level, explosionPower));
		level.addEntity(new Rocket(x - 90, -900, level, 0.2, 270));
		level.addEntity(new Rocket(x - 30, -900, level, 0.2, 270));
		level.addEntity(new Rocket(x + 30, -900, level, 0.2, 270));
		level.addEntity(new Rocket(x + 90, -900, level, 0.2, 270));
		SoundEffect.CALLDOWN2.play();
	}

	@Override
	public void tick(double dt) {
		super.tick(dt);
		handleIntersections();
		if (triggered && calldownTime > 0) {
			calldownTime -= dt;
		}
		if (calldownTime <= 0) {
			explode();
			super.remove();
		}
		if (!touchingGround) {
			accelerate(0, 0.1);
		}
	}

	@Override
	public void remove(){
		super.remove();
		level.getTerrain().addExplosion((int) (x - explosionRadius), (int) (y - explosionRadius), explosionRadius);
		level.addEntity(new Explosion(x, y, explosionRadius + 2, level, explosionPower));
		
		
	}
	
	@Override
	public String getObject(){
		return super.getObject() + "'" +triggered;
	}
	
	public void handleMessage(String[] msg){
		super.handleMessage(msg);
		boolean triggered = Boolean.parseBoolean(msg[6]);
		this.triggered = triggered;
	}
	@Override
	public void render(Renderer renderer) {
		int subimageIndex = 0;
		if (triggered)
			subimageIndex = 1;

		RGBImage img = beaconSheet.getSubImage(12 * subimageIndex, 0, 12, 13);
		renderer.DrawImage(img, -1, (int) (x - 7), (int) (y - 4), img.getWidth(), img.getHeight());
	}

	@Override
	public void initNetworkValues() {
		// TODO Auto-generated method stub
		setNetworkObjectType(NetworkObjectType.AIR_STRIKE);
	}

}
