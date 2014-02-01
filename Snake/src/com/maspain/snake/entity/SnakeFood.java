package com.maspain.snake.entity;

import com.maspain.snake.graphics.Sprite;
import com.maspain.snake.level.PixelCoordinate;

public class SnakeFood extends SnakeSegment {
	
	public SnakeFood(PixelCoordinate position) {
		super(position, Sprite.snakeFoodSprite);
	}
}
