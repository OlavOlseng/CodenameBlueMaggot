package gfx;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuField extends TextField {

	public String msg;

	public MenuField(int colums, final String type) {
		setColumns(colums);
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String tmpMsg = ae.getActionCommand();
				if (type.equals("ip")
						&& tmpMsg.matches("^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$")) {
					msg = ae.getActionCommand();
					setBackground(Menu.green);
				} else if (type.equals("text") && tmpMsg.matches("[A-Za-z0-9]{1,9}")) {
					msg = ae.getActionCommand();
					setBackground(Menu.green);
				} else
					setBackground(Menu.pink);
				setText(msg);
			}
		});
	}
}
