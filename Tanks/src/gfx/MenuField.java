package gfx;

import inputhandler.Action;
import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class MenuField extends TextField {

	public enum FieldType {
		TEXT, IP, KEY;
	}

	public String msg;
	private FieldType type;
	private int colums;
	public Action action;

	public static ArrayList<MenuField> menuFields = new ArrayList<MenuField>();

	public MenuField(int colums, final FieldType type, Action action) {
		this.colums = colums;
		this.type = type;
		this.action = action;
		menuFields.add(this);
		setUp();
	}

	public MenuField(int colums, FieldType type) {
		this.colums = colums;
		this.type = type;
		setUp();
	}

	public void setUp() {
		setColumns(colums);
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String tmpMsg = ae.getActionCommand().toUpperCase();
				if (type == FieldType.IP
						&& tmpMsg.matches("^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$")) {
					msg = ae.getActionCommand();
					setBackground(Menu.green);
				} else if (type == FieldType.TEXT) {
					if (tmpMsg.matches("[A-Za-z0-9]{1,9}")) {
						msg = ae.getActionCommand();
						setBackground(Menu.green);
					} else {
						setBackground(Menu.pink);
						setText("max 9 chars!");
					}
				} else if (type == FieldType.KEY) {
					if (tmpMsg.length() == 1 || tmpMsg.equals("RIGHT") || tmpMsg.equals("LEFT") || tmpMsg.equals("DOWN") || tmpMsg.equals("UP")) {
						action.setBind(tmpMsg.toUpperCase().charAt(0));
						if (tmpMsg.equals("RIGHT"))
							action.setBind(KeyEvent.VK_RIGHT);
						else if (tmpMsg.equals("LEFT"))
							action.setBind(KeyEvent.VK_LEFT);
						else if (tmpMsg.equals("DOWN"))
							action.setBind(KeyEvent.VK_DOWN);
						else if (tmpMsg.equals("UP"))
							action.setBind(KeyEvent.VK_UP);
						setText(tmpMsg);
						setBackground(Menu.green);
						return;
					}
				} else
					setBackground(Menu.pink);
			}
		});
	}

	public void reset() {
		setText(KeyEvent.getKeyText((action.getBind())));
		setBackground(Color.WHITE);
	}
}
