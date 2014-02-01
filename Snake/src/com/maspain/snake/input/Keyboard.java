package com.maspain.snake.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.maspain.snake.game.Game;

public class Keyboard implements KeyListener {
	
	private boolean[] keys = new boolean[256];
	public boolean up, down, left, right, space, debug;
	
	private String theKey;
	
	public void update() {
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		space = keys[KeyEvent.VK_SPACE];
		debug = keys[KeyEvent.VK_BACK_QUOTE];
		
		//printKey();
	}
	
	public void keyTyped(KeyEvent e) {
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_BACK_QUOTE) keys[e.getKeyCode()] = !keys[e.getKeyCode()]; // toggles the boolean on press for debug mode
		else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			keys[e.getKeyCode()] = !keys[e.getKeyCode()]; // toggles the boolean on press for pause mode
			Game.togglePause();
		}
		else keys[e.getKeyCode()] = true;
	}
	
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_BACK_QUOTE) return; // ignore key release for debug button, want it to toggle on key press
		else if (e.getKeyCode() == KeyEvent.VK_SPACE) return; // ignore key release for pause button, want it to toggle on key press
		else keys[e.getKeyCode()] = false;
	}
	
	public void printKey() {
		for (int i = 0; i < keys.length; i++) {
			if (keys[i]) {
				switch (i) {
					case 32:
						theKey = "space";
						break;
					case 37:
						theKey = "left";
						break;
					case 65:
						theKey = "a";
						break;
					case 38:
						theKey = "up";
						break;
					case 87:
						theKey = "w";
						break;
					case 39:
						theKey = "right";
						break;
					case 68:
						theKey = "d";
						break;
					case 40:
						theKey = "down";
						break;
					case 83:
						theKey = "s";
						break;
					case 96:
						theKey = "`";
						break;
					default:
						theKey = "not a directional key";
						break;
				}
			}
		}
		System.out.println("KEY: " + theKey);
	}
}