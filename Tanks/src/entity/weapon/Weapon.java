package entity.weapon;

import entity.Projectile;
import level.BasicLevel;

public interface Weapon {
	public void fire(double x, double y, BasicLevel level, double speedPercent, double angle);
	public void tick(double dt);
}
