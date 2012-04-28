package entity.weapon;

import sound.SoundEffect;
import entity.AirStrikeBeacon;
import entity.Shell;
import level.BasicLevel;

public class Airstrike implements Weapon{
	
	int ammo = 5;
	int cooldownTime = 30;
	int currentCooldown = 0;
	
	@Override
	public void fire(double x, double y, BasicLevel level, double speedPercent, double angle) {
		if (currentCooldown <= 0 && ammo > 0) {
			level.addEntity(new AirStrikeBeacon(x, y, level, speedPercent, angle));
			currentCooldown = cooldownTime;
			SoundEffect.SHOOT.play();
			ammo --;
			
		}
	}
	
	@Override
	public void addAmmo() {
		ammo += 1;
		
	}
	
	@Override
	public int getAmmo() {
		return ammo;
	}
	
	@Override
	public void setAmmo() {
		ammo = 0;
	}
	
	@Override
	public void tick(double dt) {
		if (currentCooldown > 0)
			currentCooldown -= dt;
	}
	

}
