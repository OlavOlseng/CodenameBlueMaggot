package entity.weapon;

import sound.SoundEffect;
import entity.Bullet;
import entity.Shell;
import entity.Tank;
import level.BasicLevel;

public class Minigun implements Weapon{
	
	public boolean firing = false;
	int bulletsToFire = 20;
	int bulletsFired = 0;
	double currentCooldown = 0;
	double cooldownTime = 120;
	int ammo = 5;
	Tank owner;
	BasicLevel level;
	
	public Minigun(Tank tank){
		this.owner = tank;
	}
	
	@Override
	public void fire(double x, double y, BasicLevel level, double speedPercent, double angle) {
		this.level = level; 
		if (currentCooldown <= 0 && ammo > 0) {
			firing = true;
			bulletsFired = 0;
			currentCooldown = cooldownTime;
		}
	}
	
	@Override
	public void addAmmo() {
		ammo += 2;
	}
	
	@Override
	public int getAmmo() {
		return ammo;
	}
	
	@Override
	public void setAmmo() {
		ammo = 0;
	}
	
	public void fireEvent(){
		level.addEntity(new Bullet(owner.getX(), owner.getY(), level, owner.getAngle()));
	}

	@Override
	public void tick(double dt) {
		if (currentCooldown > 0)
			currentCooldown -= dt;
		if(firing){
			fireEvent();
			bulletsFired++;
		}
	}
}
