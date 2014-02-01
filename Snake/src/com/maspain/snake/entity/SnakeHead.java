package com.maspain.snake.entity;

import java.util.HashSet;
import java.util.LinkedList;

import com.maspain.snake.game.Game;
import com.maspain.snake.entity.SnakeSegment;
import com.maspain.snake.graphics.Screen;
import com.maspain.snake.graphics.Sprite;
import com.maspain.snake.input.Keyboard;
import com.maspain.snake.level.Level;
import com.maspain.snake.level.PixelCoordinate;

public class SnakeHead extends SnakeSegment {
	
	private int startLength = 8;
	
	public static int spriteSize = Game.segmentSize;
	public static int halfSpriteSize = spriteSize >> 1;
	
	private Keyboard input;
	private boolean didGrow = false;
	
	private Direction dir = Direction.NORTH;
	private int distance = spriteSize;
	private int score = 0;
	private int speed;
	
	private long lastTime = System.currentTimeMillis();
	private long currentTime;
	
	private LinkedList<SnakeSegment> segments = new LinkedList<SnakeSegment>();
	private HashSet<PixelCoordinate> segmentLocations = new HashSet<PixelCoordinate>();
	
	public SnakeHead(PixelCoordinate position) {
		super(position);
	}
	
	public void init(Level level, int speed, Keyboard input) {
		super.init(level);
		this.speed = speed;
		this.input = input;
		
		addHead(position);
		
		for (int i = 1; i < startLength; i++) {
			PixelCoordinate bodyPosition = new PixelCoordinate(position.x(), position.y() + (i * spriteSize));
			SnakeSegment body = new SnakeSegment(bodyPosition, Sprite.snakeBodySprite);
			segments.addLast(body);
			segmentLocations.add(bodyPosition);
		}
	}
	
	public void update() {
		if (input.up && dir != Direction.SOUTH) dir = Direction.NORTH;
		else if (input.right && dir != Direction.WEST) dir = Direction.EAST;
		else if (input.down && dir != Direction.NORTH) dir = Direction.SOUTH;
		else if (input.left && dir != Direction.EAST) dir = Direction.WEST;
		
		currentTime = System.currentTimeMillis();
		if (currentTime - lastTime > speed) {
			lastTime = currentTime;
			move(dir);
		}
	}
	
	private void move(Direction dir) {
		// convert the old head segment into a body segment and add its PixelCoordinate to segmentLocations set
		convertHead();
		
		// add a new segment to the front of the list and update the location of the SnakeHead
		addHead(calculateNextPosition(dir));
		
		if (collision()) Game.endGame();
		
		if (didGrow) didGrow = false;
		else removeTail();
		
		if (foundFood()) eatFood();
	}
	
	private PixelCoordinate calculateNextPosition(Direction dir) {
		PixelCoordinate nextPosition = new PixelCoordinate(position);
		
		if (dir == Direction.NORTH) nextPosition.setY(position.y() - distance);
		else if (dir == Direction.EAST) nextPosition.setX(position.x() + distance);
		else if (dir == Direction.SOUTH) nextPosition.setY(position.y() + distance);
		else if (dir == Direction.WEST) nextPosition.setX(position.x() - distance);
		
		return handleWalls(nextPosition);
	}
	
	private PixelCoordinate handleWalls(PixelCoordinate p) {
		PixelCoordinate nextPosition = new PixelCoordinate(p);
		
		int padding = level.getBorderPadding();
		
		if (p.x() > Game.width - padding - halfSpriteSize) nextPosition.setX(padding + halfSpriteSize);
		if (p.x() < padding + halfSpriteSize) nextPosition.setX(Game.width - padding - halfSpriteSize);
		if (p.y() > Game.height - padding - halfSpriteSize) nextPosition.setY(padding + halfSpriteSize);
		if (p.y() < padding + halfSpriteSize) nextPosition.setY(Game.height - padding - halfSpriteSize);
		
		return nextPosition;
	}
	
	private void convertHead() {
		segments.getFirst().setSprite(Sprite.snakeBodySprite);
		segmentLocations.add(position);
	}
	
	private void addHead(PixelCoordinate position) {
		SnakeSegment head = new SnakeSegment(position, Sprite.snakeHeadSprite);
		segments.addFirst(head);
		this.position = position;
	}
	
	private void removeTail() {
		segmentLocations.remove(segments.removeLast().location());
	}
	
	private boolean collision() {
		return (segmentLocations.contains(new PixelCoordinate(position.x(), position.y())));
	}
	
	private boolean foundFood() {
		return level.getFoodSpawner().removeFoodAt(position);
	}
	
	private void eatFood() {
		didGrow = true;
		score++;
	}
	
	public int getScore() {
		return score;
	}
	
	public void render(Screen screen) {
		for (SnakeSegment s : segments)
			s.render(screen);
	}
}
