package entity;

import java.util.ArrayList;
import java.util.List;

import level.BasicLevel;
import level.Terrain;

import inputhandler.InputHandler;

public class Tank extends Entity {

	private int muzzleAngle;
	private int muzzleLength;
	private int hitpoints;
	private int maxHitpoints = 200;
	private InputHandler input;
	private int playerNumber;
	private int jetPackFuel = 100;
	private boolean canGoLeft = true;
	private boolean canGoRight = true;

	private List<Projectile> weaponList = new ArrayList<Projectile>();
	private PixelHitbox boxUnderCenter;
	private PixelHitbox boxLeft;
	private PixelHitbox boxRight;
	private PixelHitbox boxUp;
	private int currentWeapon = 0;

	public Tank(double x, double y, int playerNumber, InputHandler input,
			BasicLevel level) {
		super(x, y, 11, 6, level);
		muzzleAngle = 0;
		muzzleLength = 20;
		hitpoints = 200;
		this.playerNumber = playerNumber;
		this.input = input;
		boxUnderCenter = new PixelHitbox();
		boxUnderCenter.addPoint(new FloatingPoint(-2, yr));
		boxUnderCenter.addPoint(new FloatingPoint(0, yr));

		boxUnderCenter.addPoint(new FloatingPoint(2, yr));

		boxLeft = new PixelHitbox();
		boxRight = new PixelHitbox();
		boxUp = new PixelHitbox();
		boxUp.addPoint(new FloatingPoint(-2, -yr + 2));
		boxUp.addPoint(new FloatingPoint(+2, -yr + 2));
		for (int i = (int) -yr + 2; i < -2; i++) {
			boxLeft.addPoint(new FloatingPoint(-xr, i));
		}
		for (int ii = (int) -yr + 2; ii < -2; ii++) {
			boxRight.addPoint(new FloatingPoint(xr, ii));
		}

		/*
		 * for(int ii = 0;ii<yr-2;ii++){ boxLeft.addPoint(new FloatingPoint(0,
		 * ii)); }
		 */

	}

	public void setMuzzleAngle(int degrees) {
		this.muzzleAngle = degrees;
	}

	public void incrementMuzzleAngle(int degrees) {
		setMuzzleAngle(this.muzzleAngle + degrees);
		muzzleAngle = muzzleAngle % 60;
	}

	public void changeHp(int amount) {
		if (hitpoints + amount > maxHitpoints)
			hitpoints = maxHitpoints;
		else if (hitpoints + amount < 0) {
			hitpoints = 0;
			remove();
		} else
			hitpoints += amount;
	}

	public void fire(double speedPercent) {
	}

	@Override
	public boolean intersectsEntity() {
		for (Entity ent : level.getEntities()) {
			double[] p = ent.getLocation();
			double xLeft = p[0] - ent.xr;
			double xRight = p[0] + ent.xr;
			double yTop = p[1] - ent.yr;
			double yBot = p[1] + ent.yr;
			if (!(x + xr < xLeft || y + yr > yTop || x - xr > xRight || y - yr < yBot))
				return true;
		}
		return false;
	}

	public void handleTerrainIntersection() {
		canGoLeft = true;
		canGoRight = true;
		for (FloatingPoint point : boxLeft) {
			if (level.getTerrain().hitTestpoint((int) (point.getX() + x),
					(int) (point.getY() + y))) {

				canGoLeft = false;
				if (dx < 0)
					dx = 0;

			}

		}
		for (FloatingPoint point : boxRight) {
			if (level.getTerrain().hitTestpoint((int) (point.getX() + x),
					(int) (point.getY() + y))) {

				canGoRight = false;
				if (dx > 0)
					dx = 0;

			}

		}
		for (FloatingPoint point : boxUp) {
			if (level.getTerrain().hitTestpoint((int) (point.getX() + x),
					(int) (point.getY() + y))) {
				while (level.getTerrain().hitTestpoint(
						(int) (point.getX() + x), (int) (point.getY() + y))) {
					setLocation(x, y + 1);
					setSpeed(dx, 0.3);

				}
				break;
			}
		}
		for (FloatingPoint point : boxUnderCenter) {
			if (level.getTerrain().hitTestpoint((int) (point.getX() + x),
					(int) (point.getY() + y))) {
				while (level.getTerrain().hitTestpoint(
						(int) (point.getX() + x), (int) (point.getY() + y))) {
					setLocation(x, y - 1);
					setSpeed(dx, 0);

				}
				break;
			}
		}

	}

	public boolean intersectsTerrain() {
		if (level.getTerrain().hitTestpoint((int) x, (int) (y + yr * 2))) {
			return true;
		}

		return false;
	}

	public void jetPack() {
		int fuelTick = 10;
		if (jetPackFuel >= fuelTick) {
			accelerate(0, -0.8);
			jetPackFuel -= fuelTick;
		}
	}

	public void toggleWeapon() {
		if (currentWeapon == weaponList.size() - 1)
			currentWeapon = 0;
		else
			currentWeapon++;
	}

	private void player1Input() {
		if (input.up1.down)
			jetPack();
		if (input.down1.down)
			toggleWeapon();
		if (input.right1.down && canGoRight) {

			if (dx < 2)
				accelerate(0.2, 0);
		}
		if (input.left1.down && canGoLeft) {
			if (dx > -2)
				accelerate(-0.2, 0);
		}
	}

	private void player2Input() {

	}

	@Override
	public void tick() {
		super.tick();
		if (playerNumber == 1)
			player1Input();
		if (playerNumber == 2)
			player2Input();

		if (jetPackFuel + 5 > 100)
			jetPackFuel = 100;
		else
			jetPackFuel += 3;

		handleTerrainIntersection();
	}

}
