package baseGame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import baseGame.Rendering.RGBImage;
import baseGame.Rendering.Renderer;
import baseGame.animations.AnimationFactory;

public abstract class BaseGame extends Canvas implements Runnable {

	private int canvasWidth;
	private int canvasHeight;
	public Thread runLoop;
	private BufferStrategy buffer;
	private boolean showFps = true;
	private long lastTime;
	private double fps;
	private long msDelay;
	private RGBImage backgroundImage;
	private int backgroundColor = Color.BLACK.getRGB();
	private BufferedImage backGround = null;
	private int[] pixels;
	private boolean once = false;

	private BufferedImage mainCanvas;

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor.getRGB();
	}

	public void SetBackgroundImage(RGBImage img) {
		this.backgroundImage = img;
	}

	public Color getBackGroundColor() {
		return new Color(backgroundColor);
	}

	private Rectangle gameRect;

	public void init(int width, int height, int fps) {
		AnimationFactory.getInstance();
		canvasWidth = width;
		canvasHeight = height;
		mainCanvas = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) mainCanvas.getRaster().getDataBuffer()).getData();

		this.fps = fps;

		msDelay = 1000 / (long) fps;

		gameRect = new Rectangle(0, 0, canvasWidth, canvasHeight);
		setIgnoreRepaint(true);
		createBufferStrategy(2);

		buffer = getBufferStrategy();

		runLoop = new Thread(this);

		lastTime = System.currentTimeMillis();

		runLoop.start();
	}

	@Override
	public void run() {

		while (true) {

			long deltaTime = System.currentTimeMillis() - lastTime;

			while (deltaTime < msDelay) {

				try {
					Thread.sleep(msDelay - deltaTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				deltaTime = System.currentTimeMillis() - lastTime;
			}

			// System.out.println(deltaTime);
			lastTime = System.currentTimeMillis();

			onUpdate(deltaTime);
			Renderer renderer = new Renderer(pixels, backgroundColor, canvasWidth, canvasHeight);

			onDraw(renderer);

			Graphics2D g = (Graphics2D) buffer.getDrawGraphics();
			g.drawImage(mainCanvas, 0, 0, canvasWidth, canvasHeight, Color.BLACK, null);

			if (showFps)
				DrawfpsCounter(g, deltaTime);

			buffer.show();
			g.dispose();
		}
	}

	private boolean onScreen(int x, int y, int width, int height) {
		Rectangle rect = new Rectangle(x, y, width, height);
		return gameRect.intersects(rect);

	}

	private boolean onScreen(int x, int y, int radius) {
		return gameRect.intersects(new Rectangle(x, y, radius * 2, radius * 2));
	}

	private void DrawCircle(Graphics2D g, Renderable renderable) {
		// if (onScreen(renderable.getX(), renderable.getY(),
		// renderable.getRadius())) {

		g.setColor(renderable.getColor());
		g.fillOval(renderable.getX(), renderable.getY(), renderable.getRadius() * 2, renderable.getRadius() * 2);
		// }
	}

	private void DrawBuffImage(Graphics2D g, Renderable renderable) {
		// if (onScreen(renderable.getX(), renderable.getY(),
		// renderable.getWidth(), renderable.getHeight()))
		g.drawImage(renderable.getImg(), renderable.getX(), renderable.getY(), renderable.getWidth(), renderable.getHeight(), Color.black, null);
	}

	private void DrawfpsCounter(Graphics2D g, long deltaTime) {
		String fpsCounter = "FPS: " + (1000 / (int) deltaTime);
		g.setColor(Color.ORANGE);
		g.drawChars(fpsCounter.toCharArray(), 0, fpsCounter.length(), 10, 10);
	}

	public abstract void onUpdate(double deltaTime);

	public abstract void onDraw(Renderer renderer);
}
