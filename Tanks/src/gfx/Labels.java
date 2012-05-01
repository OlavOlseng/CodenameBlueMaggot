package gfx;

public enum Labels {
	RETURN("return"), //
	APPLY("apply"), //
	NEW_LAN_GAME("newLanGame"), //
	NEW_GAME("newGame"), //
	EXIT("exit"), //
	LVL_SELECT("lvls"), //
	WELCOME("welcome"), //
	PLAYER_ONE("playerOne"), //
	PLAYER_TWO("playerTwo"), //
	LOGO("codenameBlueMaggot"), //
	LAN_NICK("lanNick"), //
	CONNECT_IP("connectIp"), //
	IS_HOST("isHost"), //
	START_GAME("startGame"), //
	START_LAN_GAME("startLanGame"), //
	OPTIONS("options"), //
	ABOUT("about"), //
	ABOUT_MSG("aboutMsg"), //
	KEYS("control"), //
	ANGLE_COUNTERCLOCKWISE("angleLeft"), //
	ANGLE_CLOCKWISE("angleRight"), //
	CYCLE_WEAPON("cycleWep"), //
	SHOOT("fire"), //
	JETPACK("jetpack"), //
	RIGHT("right"), //
	LEFT("left"), //
	MENU("menu"), //
	ONE("one"), //
	TWO("two"), //
	DEFAULT("default");

	private String fileName;

	Labels(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return fileName;
	}
}