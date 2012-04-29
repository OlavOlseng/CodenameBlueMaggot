package entity.weapon;

public enum Gun {

	SHELLGUN, GRENADE, ROCKETLAUNCHER, MINELAUNCHER, AIRSTRIKE, MINIGUN;
	
	@Override
	public String toString(){
		return this.name();
	}
}
