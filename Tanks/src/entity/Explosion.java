package entity;

import level.BasicLevel;

public class Explosion extends Entity{
	
	double explosionPower;
	
	public Explosion(double x, double y, double radius, BasicLevel level, double explosionPower) {
		super(x, y, radius, radius, level);
		this.explosionPower = explosionPower;
	}
	
	
	public void handleIntersects(){
		for(int i = 0; i < level.getEntities().size(); i++){
			Entity other = level.getEntities().get(i);
			if(intersectsEntity(other)){
				double deltaX = (other.getLocation()[0] - this.x);
				double deltaY = (other.getLocation()[1] - this.y);
				other.accelerate(explosionPower/(deltaX*10), explosionPower/(deltaY*10));
				
			}
		}
	}
	
	@Override
	public void tick() {
		handleIntersects();
		this.remove();
	}

}
