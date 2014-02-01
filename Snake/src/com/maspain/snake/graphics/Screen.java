package com.maspain.snake.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import com.maspain.snake.game.Game;
import com.maspain.snake.input.Mouse;
import com.maspain.snake.level.Level;

public class Screen {
	
	public static final int colTransparent = 0xffff00ff;
	
	private Canvas canvas;
	private BufferStrategy bufferStrategy;
	private Graphics graphics;
	private BufferedImage image;
	
	// RGB pixel array where pixels[x + y * width] == the color of pixel at position (x, y) and where (0, 0) is the top-left corner
	private int[] pixels;
	
	public Screen(Canvas canvas) {
		this.canvas = canvas;
		image = new BufferedImage(Game.width, Game.height, BufferedImage.TYPE_INT_RGB);
		
		// getRaster() returns a WriteableRaster, which we then extract the pixel array from. this allows us to manipulate our image object by manipulating the elements of pixels,
		// which is our RGB pixel array where pixels[x + y * width] == the color of pixel at position (x, y) in image and where (0, 0) is the top-left corner
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	}
	
	public void clear() {
		for (int i = 0; i < pixels.length; i++)
			pixels[i] = 0;
	}
	
	public void render(Level level) {
		// use a buffer strategy (from the Canvas class) so that frames can be loaded to the screen while others are being rendered
		bufferStrategy = canvas.getBufferStrategy();
		
		// we don't want to create a buffer strategy every time render() is called
		if (bufferStrategy == null) {
			canvas.createBufferStrategy(3); // we are using triple buffering, improves performance
			return;
		}
		
		renderBackground();
		level.render(this);
		
		// creates a graphics context for the drawing buffer
		// all graphics that get displayed to the screen should be handled here (before g.dispose())
		graphics = bufferStrategy.getDrawGraphics();
		
		graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		graphics.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null); // draw the image to the buffer
		
		if (Game.debugIsActive()) debug();
		if (Game.isPaused()) displayPauseMessage();
		
		graphics.dispose();
		bufferStrategy.show();
	}
	
	public void renderSprite(int xp, int yp, Sprite sprite) {
		int ya, xa;	// xa and ya are the absolute positions
		int col;
		
		int w = sprite.getWidth();
		int h = sprite.getHeight();
		
		for (int y = 0; y < h; y++) {
			ya = y + yp;
			for (int x = 0; x < w; x++) {
				xa = x + xp;
				col = sprite.pixels[x + y * w];
				if (col != colTransparent) pixels[xa + ya * Game.width] = col;
			}
		}
	}
	
	public void renderBackground() {
		renderSprite(0, 0, Sprite.backgroundSprite);
	}
	
	private void displayPauseMessage() {
		String pausedMessage = "Paused";
		int fontSize = 50;
		int fontWidth;
		graphics = bufferStrategy.getDrawGraphics();
		graphics.setColor(Color.red);
		graphics.setFont(new Font("Veranda", Font.ITALIC, fontSize));
		fontWidth = graphics.getFontMetrics().stringWidth(pausedMessage);
		graphics.drawString(pausedMessage, (Game.width - fontWidth) >> 1, Game.height >> 1);
		bufferStrategy.show();
	}
	
	private void debug() {
		graphics.setColor(new Color(0xffff00ff));
		graphics.setFont(new Font("Veranda", 0, 50));
		
		int X = Mouse.getX();
		int Y = Mouse.getY();
		
		graphics.drawString("X: " + X + ", Y: " + Y, 20, 60);	// displays the current tile location where the player is being drawn (on the map)
		if (Mouse.getButton() != -1) graphics.drawString("Button: " + Mouse.getButton(), 80, 80); // displays which mouse button was clicked
	}
}
