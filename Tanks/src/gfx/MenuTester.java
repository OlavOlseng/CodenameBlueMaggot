package gfx;

import input.InputHandler;

public class MenuTester {
	
	public static void main(String[] args) {
		MenuTitle title = new MenuTitle(new InputHandler());
		Thread t = new Thread(title);
		t.run();
//		while(true)
//			System.out.println(title.btnExit.getModel().isRollover());
	}

}
