package entity.weapon;

import sound.SoundEffect;
import entity.Rocket;
import level.BasicLevel;

public class Rocketlauncher implements Weapon {

	int cooldownTime = 240;
	int currentCooldown = 0;
	private int ammo = 3;

	@Override
	public void fire(double x, double y, BasicLevel level, double speedPercent, double angle) {
		if (currentCooldown <= 0 && ammo > 0) {
			level.addEntity(new Rocket(x, y, level, 0.1, angle));
			currentCooldown = cooldownTime;
			ammo--;
			SoundEffect.ROCKETLAUNCH.play();
		}
	}

	@Override
	public void setAmmo() {
		ammo += 3;
	}

	@Override
	public int getAmmo() {
		return ammo;
	}

	@Override
	public void addAmmo() {
		ammo += 3;
	}

	@Override
	public void tick(double dt) {
		if (currentCooldown > 0)
			currentCooldown -= dt;
	}

}
