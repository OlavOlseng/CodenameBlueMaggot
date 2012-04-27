package entity;

import sound.SoundEffect;
import Networking.NetworkObjectType;
import baseGame.Rendering.Renderer;
import baseGame.animations.Animation;
import baseGame.animations.AnimationFactory;
import level.BasicLevel;

public class Explosion extends Entity {

	double explosionPower;
	Animation ani;

	public Explosion(double x, double y, double radius, BasicLevel level, double explosionPower) {
		super(x, y, radius, radius, level);

		this.explosionPower = explosionPower;
		if (radius <= 40) {
			SoundEffect.EXPLOSION1.play();

			ani = new Animation(AnimationFactory.getInstance().getAnimation(Animations.EXPLOSIONS, Animations.SMALLEXPLOSION), 18, 0, x, y, level);
		} else {
			SoundEffect.EXPLOSION2.play();
			ani = new Animation(AnimationFactory.getInstance().getAnimation(Animations.EXPLOSIONS2, Animations.BIGEXPLOSION), 26, 0, x, y, level);
		}
		level.addEntity(ani);
	}

	public void handleIntersects() {
		for (int i = 0; i < level.getEntities().size(); i++) {
			Entity other = level.getEntities().get(i);
			double deltaX = (other.getLocation()[0] - this.x);
			double deltaY = (other.getLocation()[1] - this.y);
			double radius = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
			if (radius > this.xr)
				continue;
			other.accelerate(other.damageTaken * explosionPower * deltaX / (radius * 50), other.damageTaken * explosionPower * deltaY / (radius * 50));

			other.takeDamage(explosionPower / 500);
		}
	}

	@Override
	public void tick(double dt) {
		handleIntersects();
		this.remove();
	}

	@Override
	public void render(Renderer renderer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initNetworkValues() {
		// TODO Auto-generated method stub
		setNetworkObjectType(NetworkObjectType.EXPLOSION);
	}
}
