package entity.weapon;

import sound.SoundEffect;
import level.BasicLevel;
import entity.Shell;

public class ShellGun implements Weapon {

	int cooldownTime = 25;
	int currentCooldown = 0;

	@Override
	public void fire(double x, double y, BasicLevel level, double speedPercent, double angle) {
		if (currentCooldown <= 0) {
			level.addEntity(new Shell(x, y, level, speedPercent, angle));
			currentCooldown = cooldownTime;
			SoundEffect.SHOOT.play();
		}
	}

	@Override
	public void setAmmo() {
	}

	@Override
	public int getAmmo() {
		return 1;
	}

	@Override
	public void addAmmo() {
	}

	@Override
	public void tick(double dt) {
		if (currentCooldown > 0)
			currentCooldown -= dt;
	}

}
