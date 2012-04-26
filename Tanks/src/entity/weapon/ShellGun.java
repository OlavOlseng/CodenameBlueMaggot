package entity.weapon;

import level.BasicLevel;
import entity.Projectile;
import entity.Shell;

public class ShellGun implements Weapon {
	
	int cooldownTime = 25;
	int currentCooldown = 0;
	
	@Override
	public void fire(double x, double y, BasicLevel level, double speedPercent, double angle) {
		if(currentCooldown <= 0){
		level.addEntity(new Shell(x, y , level, speedPercent, angle));
		currentCooldown = cooldownTime;
		}
	}
	
	@Override
	public void tick(double dt) {
		if(currentCooldown > 0)
			currentCooldown-= dt;
	}
	
}
