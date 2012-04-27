package gfx;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuField extends TextField {

	public String msg;

	public MenuField(int colums) {
		setColumns(colums);
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				System.out.println(ae.getActionCommand());
				msg = ae.getActionCommand();
				setText("OK! " + msg);
			}
		});
	}
}
