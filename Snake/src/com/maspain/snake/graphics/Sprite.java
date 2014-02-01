package com.maspain.snake.graphics;

import java.awt.Color;

import com.maspain.snake.game.Game;

public class Sprite {
	
	// Solid color Sprites:
	public static Sprite voidSprite  = new Sprite(Game.segmentSize, 0x1B87E0);
	public static Sprite blackSprite = new Sprite(Game.segmentSize, Color.black.getRGB());
	public static Sprite whiteSprite = new Sprite(Game.segmentSize, Color.white.getRGB());
	public static Sprite greenSprite = new Sprite(Game.segmentSize, Color.green.getRGB());
	public static Sprite redSprite   = new Sprite(Game.segmentSize, Color.red.getRGB());
	
	// Snake Sprites:
	public static Sprite snakeBodySprite = new Sprite(Game.segmentSize, Color.blue.getRGB(), 2, Color.green.getRGB(), 1, Screen.colTransparent);
	public static Sprite snakeHeadSprite = new Sprite(Game.segmentSize, Color.red.getRGB(), 6, Color.blue.getRGB(), 2, Color.green.getRGB(), 1, Screen.colTransparent);
	public static Sprite snakeFoodSprite = new CircleSprite(Game.segmentSize, Color.blue.getRGB(), 2, Color.green.getRGB());
	
	// Background Sprites:
	public static Sprite backgroundSprite = new Sprite(Game.width, Game.height, Color.black.getRGB(), Game.borderWidth, Color.white.getRGB());
	
	public final int SIZE;
	public final int HALF_OF_SIZE;
	public int[] pixels;
	
	private int width, height;
	
	public Sprite(int size, int color) {
		SIZE = width = height = size;
		HALF_OF_SIZE = SIZE >> 1;
		
		pixels = new int[SIZE * SIZE];
		setColor(color);
	}
	
	public Sprite(int width, int height, int color) {
		this.width = width;
		this.height = height;
		
		SIZE = (width == height) ? width : -1;
		HALF_OF_SIZE = (SIZE != -1) ? (SIZE >> 1) : SIZE;
		
		pixels = new int[width * height];
		setColor(color);
	}
	
	public Sprite(int size, int color, int padding, int borderColor) {
		this(size, color);
		setBorder(padding, borderColor);
	}
	
	public Sprite(int width, int height, int color, int padding, int borderColor) {
		this(width, height, color);
		setBorder(padding, borderColor);
	}
	
	public Sprite(int size, int color, int innerPadding, int innerColor, int outerPadding, int outerColor) {
		this(size, color, innerPadding, innerColor);
		setBorder(outerPadding, outerColor);
	}
	
	public Sprite(int width, int height, int color, int innerPadding, int innerColor, int outerPadding, int outerColor) {
		this(width, height, color, innerPadding, innerColor);
		setBorder(outerPadding, outerColor);
	}
	
	public Sprite(int size, int color, int innerPadding, int innerColor, int middlePadding, int middleColor, int outerPadding, int outerColor) {
		this(size, color, innerPadding, innerColor, middlePadding, middleColor);
		setBorder(outerPadding, outerColor);
	}
	
	public Sprite(int width, int height, int color, int innerPadding, int innerColor, int middlePadding, int middleColor, int outerPadding, int outerColor) {
		this(width, height, color, innerPadding, innerColor, middlePadding, middleColor);
		setBorder(outerPadding, outerColor);
	}
	
	private void setColor(int color) {
		for (int i = 0; i < width * height; i++)
			pixels[i] = color;
	}
	
	public void setBorder(int padding, int color) {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (x < padding || y < padding || x >= (width - padding) || y >= (height - padding)) {
					pixels[x + y * width] = color;
				}
			}
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
