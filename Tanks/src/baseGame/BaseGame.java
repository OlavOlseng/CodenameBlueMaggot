package baseGame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import java.awt.image.BufferStrategy;

import entity.Entity;

public abstract class BaseGame extends Canvas implements Runnable {
	
	private Thread runLoop;
	private BufferStrategy buffer;
	private boolean showFps = true;
	private long lastTime;
	private double fps;
	private long msDelay;
	private int width = 800;
	private int height = 600;
	private Color backgroundColor = Color.cyan;

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
		
	}

	private Rectangle gameRect;

	public void init(int fps) {
		
		this.fps = fps;
		
		msDelay = 1000/(long)fps;
		
		gameRect = new Rectangle(0, 0, width, height);
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

			long deltaTime = System.currentTimeMillis() - lastTime;
		
			if (deltaTime < msDelay){
				
				try {
					Thread.sleep(msDelay - deltaTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		
			
			
			
				deltaTime = System.currentTimeMillis() - lastTime;
				
				lastTime = System.currentTimeMillis();
				
			
				onUpdate(deltaTime);
			

			Renderer renderer = new Renderer();
	
			onDraw(renderer);
			
			Graphics2D g = (Graphics2D) buffer.getDrawGraphics();
			g.setClip(0, 0, 800, 600);
			g.setColor(backgroundColor);
			g.fillRect(0, 0, 800, 600);
			
			for (Renderable renderable : renderer.getToBeDrawn()) {
				switch (renderable.getRenderType()) {
				case BUFFEREDIMAGE:
					
					DrawBuffImage(g, renderable);
					break;
				case CIRCLE:
					DrawCircle(g, renderable);
				default:
					break;
				}
			}
		

		
			if (showFps)
				DrawfpsCounter(g,deltaTime);
			
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
		//if (onScreen(renderable.getX(), renderable.getY(),
		//		renderable.getRadius())) {
			g.setColor(renderable.getColor());
			g.fillOval(renderable.getX(), renderable.getY(),
					renderable.getRadius() * 2, renderable.getRadius() * 2);
		//}

	}

	private void DrawBuffImage(Graphics2D g, Renderable renderable) {
	//	if (onScreen(renderable.getX(), renderable.getY(),
	//			renderable.getWidth(), renderable.getHeight()))
			g.drawImage(renderable.getImg(), renderable.getX(),
					renderable.getY(), renderable.getWidth(),
					renderable.getHeight(), Color.black, null);
	}

	private void DrawfpsCounter(Graphics2D g, long deltaTime) {
		String fpsCounter = "FPS: " + (1000 / (int) deltaTime);
		g.setColor(Color.white);
		g.drawChars(fpsCounter.toCharArray(), 0, fpsCounter.length(), 10, 10);
	}

	public abstract void onUpdate(long deltaTime);

	public abstract void onDraw(Renderer renderer);

}