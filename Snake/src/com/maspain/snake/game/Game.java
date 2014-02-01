package com.maspain.snake.game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import com.maspain.snake.graphics.Screen;
import com.maspain.snake.input.Keyboard;
import com.maspain.snake.input.Mouse;
import com.maspain.snake.level.Level;
import com.maspain.snake.utilities.Utility;

public class Game implements Runnable {
	
	public static final int segmentSize = 16;
	public static final int width = segmentSize * 40;
	public static final int height = width;
	public static final int borderWidth = segmentSize >> 1;
	
	public static final String name = "Snake";
	
	public static final int SPEED_NOVICE = 200;
	public static final int SPEED_SLOW = 100;
	public static final int SPEED_NORMAL = 75;
	public static final int SPEED_FAST = 50;
	public static final int SPEED_EXTREME = 25;
	public static int speed;
	public static String speedString;
	
	private static boolean running = false;
	private static boolean paused = false;
	private static boolean wasPaused = false;
	private static boolean debugEnabled = false;
	
	private static Keyboard keyboard = new Keyboard();
	
	private Canvas canvas = new Canvas();	// the canvas where images will be drawn
	private Thread gameThread;				// this thread handles the game itself
	private GameWindow gameWindow;			// this is the game window itself
	
	private Screen screen;			// this will handle all of the rendering operations
	private Level level;
	
	private long gameTime = 0;
	private Record record;
	private int currentHighScore;
	private int finalScore;
	
	public Game(Speed speed) {
		switch (speed) {
			case NOVICE:
				speedString = "Novice";
				Game.speed = SPEED_NOVICE;
				break;
			case SLOW:
				speedString = "Slow";
				Game.speed = SPEED_SLOW;
				break;
			case NORMAL:
				speedString = "Normal";
				Game.speed = SPEED_NORMAL;
				break;
			case FAST:
				speedString = "Fast";
				Game.speed = SPEED_FAST;
				break;
			case EXTREME:
				speedString = "Extreme";
				Game.speed = SPEED_EXTREME;
				break;
		}
		
		record = new Record(speedString);
		currentHighScore = Integer.parseInt(record.getHighScore());
		
		initComponents();
		setBehaviors();
		initFrame();
		
		start();
	}
	
	public synchronized void start() {
		running = true;
		gameThread = new Thread(this, "game");
		gameThread.start();
	}
	
	public synchronized void stop() {
		try {
			gameThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		// the timer code in this method normalizes the game play speed with the frame rate
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;	// this is simply a conversion factor
		double delta = 0.0;
		int frames = 0;
		
		canvas.requestFocus();
		
		while (running) {
			
			long now = System.nanoTime();
			
			// obtain the elapsed time since the previous iteration of the loop,
			// convert from nanoseconds to seconds and multiply by 60 (using the conversion factor),
			// then add that to delta. this is to ensure that the following while loop will execute
			// at most 60 times per second regardless of computer speed
			delta += (now - lastTime) / ns;
			lastTime = now;	// simply update the previous time for use in the next iteration
			
			// we want to render and update at most 60 times per second
			while (delta > 0) {
				playGame();
				frames++;
				delta--;
			}
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				gameTime = (paused) ? gameTime : gameTime + 1;
				
				gameWindow.lblScore.setText("Score: " + level.getScore());
				if (level.getScore() > currentHighScore) {
					currentHighScore = level.getScore();
					record.setHighScore("" + currentHighScore);
					gameWindow.lblHigh.setText("High: " + currentHighScore);
				}
				gameWindow.lblTime.setText("Time: " + Utility.covertToFormattedTimeString(gameTime));
				
				// display then reset the fps counter
				gameWindow.setTitle(name + "  |   " + frames + " fps");
				frames = 0;
			}
		}
		
		gameOver();
	}
	
	private void update() {
		if (wasPaused) {
			wasPaused = false;
			canvas.requestFocus();
		}
		keyboard.update();
		level.update();
	}
	
	private void render() {
		screen.render(level);
	}
	
	private void playGame() {
		if (!paused) update();
		render();
	}
	
	public static void endGame() {
		running = false;
	}
	
	private void gameOver() {
		finalScore = level.getScore();
		if (finalScore > currentHighScore) record.setHighScore("" + finalScore);
		
		gameWindow.dispose();
		new Result(finalScore, record, speedString, Utility.covertToFormattedTimeString(gameTime));
	}
	
	public static void togglePause() {
		paused = !paused;
		if (!paused) wasPaused = true;
	}
	
	public static boolean isPaused() {
		return paused;
	}
	
	public static boolean debugIsActive() {
		return (keyboard.debug && debugEnabled);
	}
	
	private void initFrame() {
		Dimension size = new Dimension(width, height);
		canvas.setPreferredSize(size);
		
		gameWindow.setResizable(false);
		gameWindow.setTitle(name);
		
		GridBagConstraints gbc_game = new GridBagConstraints();
		gbc_game.gridwidth = 6;
		gbc_game.insets = new Insets(0, 0, 0, 0);
		gbc_game.gridx = 0;
		gbc_game.gridy = 1;
		gameWindow.add(canvas, gbc_game);
		
		gameWindow.pack();	// sizes up the frame to the size of the game
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.setLocationRelativeTo(null);
		gameWindow.setVisible(true);
	}
	
	private void setBehaviors() {
		Mouse mouse = new Mouse();
		canvas.addKeyListener(keyboard);
		canvas.addMouseListener(mouse);
		canvas.addMouseMotionListener(mouse);
		
		gameWindow.btnNewGame.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				endGame();
			}
		});
		
		gameWindow.btnPause.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				togglePause();
			}
		});
	}
	
	private void initComponents() {
		screen = new Screen(canvas);
		gameWindow = new GameWindow(speedString, record.getHighScore());
		
		level = new Level(width, height, borderWidth);
		level.init(keyboard);
	}
}