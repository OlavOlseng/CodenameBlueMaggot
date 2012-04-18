package gfx;

import input.InputHandler;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TitleMenu extends JPanel implements Runnable {

	public static void main(String[] args) {
		Thread t = new Thread(new TitleMenu(new InputHandler()));
		t.run();
	}

	public TitleMenu(InputHandler input) {
		window = rootFrame.getContentPane();
		rootFrame.setPreferredSize(new Dimension(800, 600));
		rootFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(new GridBagLayout());
		

		setPreferredSize(new Dimension(250, 400));

		rootFrame.setFocusable(true);
		rootFrame.addKeyListener(input);

	}

	private Container window;
	private JFrame rootFrame = new JFrame();
	private GridBagConstraints c = new GridBagConstraints();

	@Override
	public void run() {
		c.gridheight = 10;
		c.gridwidth = 3;

		setBackground(Color.black);
//		 setOpaque(false);

		c.gridx = 2;
		c.gridy = 1;
		c.fill = GridBagConstraints.VERTICAL;
		window.add(this);
		
		c.fill = GridBagConstraints.NONE;
		
		window.add(new JButton("asdasd"), c);

		rootFrame.pack();
		rootFrame.setVisible(true);
		rootFrame.setLocationRelativeTo(null);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(new Color(255, 10, 250, 70));
		g.fillRect(0, 0, this.getHeight(), this.getWidth());
	}
}
