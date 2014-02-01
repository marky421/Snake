package com.maspain.snake.entity;

import java.util.Random;

import com.maspain.snake.game.Game;
import com.maspain.snake.graphics.Screen;
import com.maspain.snake.graphics.Sprite;
import com.maspain.snake.level.Level;
import com.maspain.snake.level.PixelCoordinate;

public abstract class Entity {
	
	protected Level level;
	protected PixelCoordinate position;
	protected Sprite sprite;
	protected final Random random = new Random();
	
	public PixelCoordinate location() {
		return position;
	}
	
	public void init(Level level) {
		this.level = level;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void update() {}
	
	public void render(Screen screen) {
		screen.renderSprite(position.x() - sprite.HALF_OF_SIZE, position.y() - sprite.HALF_OF_SIZE, sprite);
	}
	
	public boolean isOutsideMap() {
		int padding = level.getBorderPadding();
		return ((position.x() >= Game.width - padding) || (position.x() <= padding) || (position.y() >= Game.height - padding) || (position.y() <= padding));
	}
}
