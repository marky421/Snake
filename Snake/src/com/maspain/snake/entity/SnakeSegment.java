package com.maspain.snake.entity;

import com.maspain.snake.entity.Entity;
import com.maspain.snake.graphics.Sprite;
import com.maspain.snake.level.PixelCoordinate;

public class SnakeSegment extends Entity {
	
	public SnakeSegment(PixelCoordinate position) {
		this.position = position;
	}
	
	public SnakeSegment(PixelCoordinate position, Sprite sprite) {
		this.position = position;
		setSprite(sprite);
	}
}
