package entity;

import baseGame.animations.Animation;
import baseGame.animations.AnimationFactory;
import level.BasicLevel;

public class Explosion extends Entity {

	double explosionPower;

	public Explosion(double x, double y, double radius, BasicLevel level,
			double explosionPower) {
		super(x, y, radius, radius, level);

		this.explosionPower = explosionPower;

	}

	public void handleIntersects() {
		for (int i = 0; i < level.getEntities().size(); i++) {
			Entity other = level.getEntities().get(i);
			double deltaX = (other.getLocation()[0] - this.x);
			double deltaY = (other.getLocation()[1] - this.y);
			double radius = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
			if (radius > this.xr)
				continue;
			other.accelerate(other.damageTaken*explosionPower * deltaX / (radius * 50),
					other.damageTaken*explosionPower * deltaY / (radius * 50));
			
			other.takeDamage(explosionPower/500);
//			level.addEntity(new Animation(AnimationFactory.getInstance().getAnimation(Animations.EXPLOSIONS,
//					Animations.EXPLOSIONS_BIG), 0, 0, x, y, level));
		}
	}

	@Override
	public void tick() {
		handleIntersects();
		this.remove();
	}

}
