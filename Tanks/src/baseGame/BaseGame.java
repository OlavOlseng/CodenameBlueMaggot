package baseGame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.TimerTask;

import entity.Entity;

public abstract class BaseGame extends Canvas implements Runnable {

	private Thread runLoop;
	private BufferStrategy buffer;
	private boolean showFps = true;
	private long lastTime;
	private double fps;

	public void init(double fps) {
		this.fps = fps;
		setIgnoreRepaint(true);
		createBufferStrategy(2);
		buffer = getBufferStrategy();

		runLoop = new Thread(this);
		lastTime = System.currentTimeMillis();
		runLoop.run();

	}

	@Override
	public void run() {

		while (true) {
			long deltaTime1 = System.currentTimeMillis() - lastTime;
			long deltaTime2 = System.currentTimeMillis() - lastTime;

			if (deltaTime1 < 1000 / fps)
				continue;
			onUppdate(deltaTime1);
			Renderer renderer = new Renderer();

			onDraw(renderer);
			Graphics2D g = (Graphics2D) buffer.getDrawGraphics();
			g.clearRect(0, 0, 800, 600);
			for (Renderable renderable : renderer.getToBeDrawn()) {
				Entity ent = renderable.getEnt();
				Point loc = ent.getLocation();
				g.drawImage(renderable.getImg(), loc.x, loc.y, ent.getXr(),
						ent.getYr(), Color.black, null);

			}

			if (showFps)
				DrawfpsCounter(g, deltaTime2);

			buffer.show();
			g.dispose();
			lastTime = System.currentTimeMillis();

		}
	}

	private void DrawfpsCounter(Graphics2D g, long deltaTime) {
		String fpsCounter = "FPS: " + (1000 / (int) deltaTime);
		g.drawChars(fpsCounter.toCharArray(), 0, fpsCounter.length(), 10, 10);
	}

	public abstract void onUppdate(long deltaTime);

	public abstract void onDraw(Renderer renderer);

}
