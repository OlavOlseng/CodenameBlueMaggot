package entity.weapon;

public enum Gun {

	SHELLGUN, GRENADE, ROCKET, MINE, AIRSTRIKE, MINIGUN;
	
	@Override
	public String toString(){
		return this.name();
	}
}
