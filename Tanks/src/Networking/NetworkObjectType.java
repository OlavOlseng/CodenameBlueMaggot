package Networking;

public enum NetworkObjectType {

	TANK(0),GRENADE(1),ROCKET(2),SCORE_BUBBLE(3),SHELL(4),EXPLOSION(5),ANIMATION(6),MINE(7),PACKAGE(8),NO_SYNC(9);
	
	private final int type;
	
	public String toString(){
		String res = "-1";
		switch (this) {
		case TANK: res = "00";
		break;
		case GRENADE: res = "01";
		break;
		case ROCKET: res = "02";
		break;
		case SCORE_BUBBLE: res = "03";
		break;
		case SHELL:res = "04";
		break;
		case EXPLOSION:res = "05";
		break;
		case ANIMATION:res = "06";
		break;
		case MINE:res = "07";
		break;
		case PACKAGE:res = "08";
		break;
		case NO_SYNC:res = "09";
		break;

		}
		return res;
		}
	NetworkObjectType(int type) {
		this.type = type;

	}

	public boolean equals(int type) {
		return this.type == type;
	}

}
