package entity.weapon;

import entity.Rocket;
import level.BasicLevel;

public class Rocketlauncher implements Weapon {
	
	int cooldownTime = 240;
	int currentCooldown = 0;
	
	@Override
	public void fire(double x, double y, BasicLevel level, double speedPercent, double angle) {
		if(currentCooldown <= 0){
		level.addEntity(new Rocket(x, y , level, 0.1, angle));
		currentCooldown = cooldownTime;
		}
	}
	
	@Override
	public void tick() {
		if(currentCooldown > 0)
			currentCooldown--;
	}
}
