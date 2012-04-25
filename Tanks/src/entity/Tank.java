package entity;

import java.util.ArrayList;
import java.util.List;

import entity.weapon.GrenadeGun;
import entity.weapon.Rocketlauncher;
import entity.weapon.ShellGun;
import entity.weapon.Weapon;

import level.BasicLevel;
import level.Terrain;

import inputhandler.InputHandler;

public class Tank extends Entity {

	private InputHandler input;
	private int muzzleAngle;
	private int muzzleLength = 16;
	private int score = 0;

	
	private int playerNumber;
	private int jetPackFuel = 100;
	private double cannonCharge = 0;
	private boolean canGoLeft = true;
	private boolean canGoRight = true;
	private boolean chargingCannon = false;
	private int currentWeapon = 0;

	private ArrayList<Weapon> weaponList = new ArrayList<Weapon>();
	private PixelHitbox boxUnderCenter;
	private PixelHitbox boxLeft;
	private PixelHitbox boxRight;
	private PixelHitbox boxUp;

	public Tank(double x, double y, int playerNumber, InputHandler input,
			BasicLevel level) {
		super(x, y, 11, 6, level);
		this.level.getPlayers().add(this);
		muzzleAngle = 0;
		muzzleLength = 20;
		this.playerNumber = playerNumber;
		this.input = input;

		weaponList.add(new ShellGun());
		weaponList.add(new GrenadeGun());
		weaponList.add(new Rocketlauncher());

		boxUnderCenter = new PixelHitbox();
		boxUnderCenter.addPoint(new FloatingPoint(-2, yr));
		boxUnderCenter.addPoint(new FloatingPoint(-1, yr));
		boxUnderCenter.addPoint(new FloatingPoint(0, yr));
		boxUnderCenter.addPoint(new FloatingPoint(1, yr));
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
		muzzleAngle = ((360 + muzzleAngle) % 360);

	}

	public void addScore(int amount) {
		this.score += amount;
	}

	public int getScore() {
		return score;
	}

	public FloatingPoint getCrosshairLocation() {
		return (new FloatingPoint(x + muzzleLength
				* Math.cos(Math.toRadians(muzzleAngle)), y - muzzleLength
				* Math.sin(Math.toRadians(muzzleAngle))));
	}


	public void fire(double speedPercent) {
		if (speedPercent < 0.2)
			speedPercent = 0.2;
		weaponList.get(currentWeapon).fire(
				this.x + muzzleLength * Math.cos(Math.toRadians(muzzleAngle)),
				this.y - muzzleLength * Math.sin(Math.toRadians(muzzleAngle)),
				this.level, speedPercent, this.muzzleAngle - 4);
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
		for (FloatingPoint point : boxUnderCenter) {
			if (level.getTerrain().hitTestpoint((int) (point.getX() + x),
					(int) (point.getY() + y))) {
				while (level.getTerrain().hitTestpoint((int) (point.getX() + x), (int) (point.getY() + y))) {
					setLocation(x, y - 1);
					setSpeed(dx, 0);

				}
				return;
			}
		}
		for (FloatingPoint point : boxUp) {
			if (level.getTerrain().hitTestpoint((int) (point.getX() + x),
					(int) (point.getY() + y))) {
				while (level.getTerrain().hitTestpoint((int) (point.getX() + x), (int) (point.getY() + y))) {
					setLocation(x, y + 1);
					setSpeed(dx, 0.3);

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

	private void tickWeapons() {
		for (Weapon wep : weaponList) {
			wep.tick();
		}
	}

	public void jetPack() {
		int fuelTick = 13;
		if (jetPackFuel >= fuelTick) {
			accelerate(0, -0.45);
			jetPackFuel -= fuelTick;
		}
	}

	public void toggleWeapon() {
		currentWeapon++;
		if (currentWeapon >= weaponList.size())
			currentWeapon = 0;
	}

	private void player1Input() {
		if (input.up1.down)
			jetPack();
		if (input.down1.clicked)
			toggleWeapon();
		if (input.right1.down && canGoRight) {

			if (dx < 2)
				accelerate(0.2, 0);
		}
		if (input.left1.down && canGoLeft) {
			if (dx > -2)
				accelerate(-0.2, 0);
		}
		if (input.fire1.clicked) {
			chargingCannon = true;
		}
		if (chargingCannon && !input.fire1.down || cannonCharge >= 1) {
			fire(cannonCharge);
			chargingCannon = false;
			cannonCharge = 0;
		}
		if (input.rotateL1.down)
			incrementMuzzleAngle(3);
		if (input.rotateR1.down)
			incrementMuzzleAngle(-3);
	}

	private void player2Input() {
		if (input.up2.down)
			jetPack();
		if (input.down2.clicked)
			toggleWeapon();
		if (input.right2.down && canGoRight) {

			if (dx < 2)
				accelerate(0.2, 0);
		}
		if (input.left2.down && canGoLeft) {
			if (dx > -2)
				accelerate(-0.2, 0);
		}
		if (input.fire2.clicked) {
			chargingCannon = true;
		}
		if (chargingCannon && !input.fire2.down || cannonCharge >= 1) {
			fire(cannonCharge);
			chargingCannon = false;
			cannonCharge = 0;
		}
		if (input.rotateL2.down)
			incrementMuzzleAngle(3);
		if (input.rotateR2.down)
			incrementMuzzleAngle(-3);
	}

	public int getMuzzleAngle() {
		return muzzleAngle;
	}
	
	public void takeDamage(double amount){
		this.damageTaken += amount;
	}

	@Override
	public void tick() {
		super.tick();
		if (playerNumber == 1)
			player1Input();
		if (playerNumber == 2)
			player2Input();

		if (chargingCannon)
			cannonCharge += 0.015;
		if (jetPackFuel + 5 > 100)
			jetPackFuel = 100;
		else
			jetPackFuel += 2;

		handleTerrainIntersection();
		tickWeapons();
	}

}
