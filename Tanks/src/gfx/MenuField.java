package gfx;

import gfx.MenuField.FieldType;
import inputhandler.Action;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MenuField extends TextField {

	public enum FieldType {
		TEXT, IP, KEY;
	}

	public String msg;
	private FieldType type;
	private int colums;
	private Action action;

	public MenuField(int colums, final FieldType type, Action action) {
		this.colums = colums;
		this.type = type;
		this.action = action;
		setText(KeyEvent.getKeyText((action.getBind())));
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
				String tmpMsg = ae.getActionCommand();
				if (type == FieldType.IP
						&& tmpMsg.matches("^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$")) {
					msg = ae.getActionCommand();
					setBackground(Menu.green);
				} else if (type == FieldType.TEXT && tmpMsg.matches("[A-Za-z0-9]{1,9}")) {
					msg = ae.getActionCommand();
					setBackground(Menu.green);
				} else if (type == FieldType.KEY && tmpMsg.length() == 1) {
					System.out.println(KeyEvent.getExtendedKeyCodeForChar(tmpMsg.charAt(0)));
					action.setBind(KeyEvent.getExtendedKeyCodeForChar(tmpMsg.charAt(0)));
					setText(KeyEvent.getKeyText(tmpMsg.charAt(0)));
					setBackground(Menu.green);
				} else
					setBackground(Menu.pink);
				setText(msg);
			}
		});

	}
}
