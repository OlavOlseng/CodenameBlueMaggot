package entity.weapon;

import level.BasicLevel;
import entity.Grenade;

public class GrenadeGun implements Weapon {

	private int cooldownTime = 30;
	private int currentCooldown = 0;
	
	public void fire(double x, double y, BasicLevel level, double speedPercent, double angle) {
		if(currentCooldown <= 0){
		level.addEntity(new Grenade(x, y , level, speedPercent, angle));
		currentCooldown = cooldownTime;
		}
	}
	
	@Override
	public void tick(double dt) {
		if(currentCooldown > 0)
			currentCooldown-= dt;
	}
	
}
