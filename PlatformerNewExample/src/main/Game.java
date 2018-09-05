package main;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import main.display.Display;

public class Game implements Runnable{

	private String title;
	private int width;
	private int height;
	
	private Display display;
	
	private Thread thread;
	private boolean running;
	
	private BufferStrategy bs;
	private Graphics g;
	
	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
	}
	
	private void init() {
		display = new Display(title, width, height);
	}
	
	@Override
	public void run() {
		init();
		
		int fps = 60;
		double delta = 0;
		double timePerTicks = 1000000000 / fps;
		long now;
		long lastTime = System.nanoTime();
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTicks;
			lastTime = now;
			
			if(delta >= 1) {
				update();
				render();
				delta--;
			}
		}
	}
	
	int x = 10;
	
	public void update() {
		x += 1;
	}
	
	public void render() {
		bs = display.getCanvas().getBufferStrategy();
		
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		
		// clear
		g.clearRect(0, 0, width, height);
		
		// draw
		
		g.fillRect(x, 10, 100, 100);
		
		// end draw
		bs.show();
		g.dispose();
	}
	
	public synchronized void start() {
		if(running)
			return;
		
		running = true;
		
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running)
			return;
		
		running = false;
		
		try{
			thread.join();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
