package com.maspain.snake.entity;

import java.util.Hashtable;

import com.maspain.snake.game.Game;
import com.maspain.snake.graphics.Screen;
import com.maspain.snake.entity.SnakeHead;
import com.maspain.snake.entity.Entity;
import com.maspain.snake.entity.SnakeFood;
import com.maspain.snake.level.PixelCoordinate;

public class FoodSpawner extends Entity {
	
	private int maxFood;
	private int minFood;
	
	public static int spriteSize = SnakeHead.spriteSize;
	public static int halfSpriteSize = spriteSize >> 1;
	
	private static int[] validXLocations;
	private static int[] validYLocations;
	
	private Hashtable<PixelCoordinate, SnakeFood> food = new Hashtable<PixelCoordinate, SnakeFood>();
	
	public FoodSpawner(int minFood, int maxFood) {
		this.minFood = minFood;
		this.maxFood = maxFood;
		
		int xMax = Game.width - Game.borderWidth - halfSpriteSize;
		int xMin = Game.borderWidth + halfSpriteSize;
		int yMax = Game.height - Game.borderWidth - halfSpriteSize;
		int yMin = Game.borderWidth + halfSpriteSize;
		
		int xSize = (xMax - xMin) / spriteSize;
		int ySize = (yMax - yMin) / spriteSize;
		
		validXLocations = new int[xSize];
		validYLocations = new int[ySize];
		
		for (int i = 0; i < xSize; i++) {
			validXLocations[i] = (i * spriteSize) + spriteSize;
		}
		
		for (int i = 0; i < ySize; i++) {
			validYLocations[i] = (i * spriteSize) + spriteSize;
		}
	}
	
	public void init() {
		for (int i = 0; i < maxFood; i++)
			spawn();
	}
	
	public void spawn() {
		PixelCoordinate p;
		do
			p = getValidLocation();
		while (food.contains(p));
		addFood(new SnakeFood(p));
	}
	
	public void addFood(SnakeFood s) {
		food.put(s.location(), s);
	}
	
	public boolean removeFoodAt(PixelCoordinate p) {
		return (food.remove(p) != null);
	}
	
	private PixelCoordinate getValidLocation() {
		int xa = random.nextInt(validXLocations.length);
		int ya = random.nextInt(validYLocations.length);
		
		return (new PixelCoordinate(validXLocations[xa], validYLocations[ya]));
	}
	
	public void update() {
		int foodCount = food.size();
		if (foodCount < minFood) {
			for (int i = foodCount; i < maxFood; i++) {
				spawn();
			}
		}
	}
	
	public void render(Screen screen) {
		for (SnakeFood f : food.values())
			f.render(screen);
	}
}
