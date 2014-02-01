package com.maspain.snake.graphics;

import java.util.Random;

public class CircleSprite extends Sprite {
	
	public final int DIAMETER;
	public final int RADIUS;
	
	private Random random = new Random();
	
	public CircleSprite(int diameter, int color) {
		super(diameter, color);
		DIAMETER = SIZE;
		RADIUS = HALF_OF_SIZE;
		if (color != Screen.colTransparent) {
			for (int y = 0; y < DIAMETER; y++) {
				for (int x = 0; x < DIAMETER; x++) {
					if (!inCircle(x, y)) pixels[x + y * DIAMETER] = Screen.colTransparent;
				}
			}
		}
	}
	
	public CircleSprite(int diameter, int color, int padding, int borderColor) {
		this(diameter, color);
		setBorder(padding, borderColor);
	}
	
	public boolean inCircle(int px, int py) {
		int w = px - RADIUS;
		int h = py - RADIUS;
		double r = Math.sqrt((w * w) + (h * h));
		return (r < RADIUS);
	}
	
	public boolean onEdge(int px, int py) {
		int w = px - RADIUS;
		int h = py - RADIUS;
		double r = Math.sqrt((w * w) + (h * h));
		return (r == RADIUS);
	}
	
	public boolean onEdge(int px, int py, int padding) {
		int w = px - RADIUS;
		int h = py - RADIUS;
		double r = Math.sqrt((w * w) + (h * h));
		return (r <= RADIUS && r >= (RADIUS - padding));
	}
	
	public void setBorder(int padding, int color) {
		for (int y = 0; y < DIAMETER; y++) {
			for (int x = 0; x < DIAMETER; x++) {
				if (onEdge(x, y, padding)) {
					pixels[x + y * DIAMETER] = color;
				}
			}
		}
	}
	
	public void pixelate(int color, int density) {
		for (int y = 0; y < DIAMETER; y++) {
			for (int x = 0; x < DIAMETER; x++) {
				if (inCircle(x, y) && random.nextInt(100) < density) pixels[x + y * DIAMETER] = color;
			}
		}
	}
}