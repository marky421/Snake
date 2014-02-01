package com.maspain.snake.level;

public class PixelCoordinate {
	
	private int x, y;
	
	public PixelCoordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public PixelCoordinate() {
		this.x = -1;
		this.y = -1;
	}
	
	public PixelCoordinate(PixelCoordinate p) {
		this.x = p.x();
		this.y = p.y();
	}
	
	public int x() {
		return x;
	}
	
	public int y() {
		return y;
	}
	
	public int[] xy() {
		int[] r = new int[2];
		r[0] = x;
		r[1] = y;
		return r;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.x;
		result = prime * result + this.y;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		PixelCoordinate other = (PixelCoordinate) obj;
		if (this.x != other.x) return false;
		if (this.y != other.y) return false;
		return true;
	}
	
	// public boolean equals(PixelCoordinate target) { return (x == target.x() && y == target.y()); }
}
