package entity;

import baseGame.Rendering.Renderer;
import baseGame.animations.Animation;
import baseGame.animations.AnimationFactory;
import level.BasicLevel;
import baseGame.animations.*;
public class Explosion extends Entity {

	double explosionPower;
	Animation ani;
	public Explosion(double x, double y, double radius, BasicLevel level,
			double explosionPower) {
		super(x, y, radius, radius, level);

		this.explosionPower = explosionPower;
<<<<<<< HEAD
		if(radius >= 25)
			ani = new Animation( AnimationFactory.getInstance().getAnimation(Animations.EXPLOSIONS,Animations.SMALLEXPLOSION),9,0,x,y,level);
		ani = new Animation( AnimationFactory.getInstance().getAnimation(Animations.EXPLOSIONS,Animations.BIGEXPLOSION),13,0,x,y,level);
=======
		Animation ani = new Animation( AnimationFactory.getInstance().getAnimation(Animations.EXPLOSIONS,Animations.SMALLEXPLOSION),18,0,x,y,level);
>>>>>>> 15408c03b393657f6d041149724f1fdacd83c727
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
			other.accelerate(other.damageTaken*explosionPower * deltaX / (radius * 50),
					other.damageTaken*explosionPower * deltaY / (radius * 50));
			
			other.takeDamage(explosionPower/500);
//			level.addEntity(new Animation(AnimationFactory.getInstance().getAnimation(Animations.EXPLOSIONS,
//					Animations.EXPLOSIONS_BIG), 0, 0, x, y, level));
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

}
