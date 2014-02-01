package com.maspain.snake.game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;

import com.maspain.snake.utilities.OSDetector;

public class Record {
	
	private BufferedReader reader;
	private BufferedWriter writer;
	private File file;
	private String fileName = "high_score.txt";
	private String folderPath;
	private String filePath;
	private String difficulty;
	private String highScore = "";
	private boolean newHighScore = false;
	private boolean fileIONotSupported = false;
	
	private String[] speeds = { "Novice", "Slow", "Normal", "Fast", "Extreme" };
	
	private Hashtable<String, Integer> scores = new Hashtable<String, Integer>();
	
	public Record(String difficulty) {
		this.difficulty = difficulty;
		if (OSDetector.isMac()) {
			folderPath = System.getProperty("user.home") + "/Library/Application Support/Snake/records";
			filePath = folderPath + "/" + fileName;
			file = new File(filePath);
		}
		else if (OSDetector.isWindows()) {
			folderPath = System.getenv("AppData") + "\\Snake\\records";
			filePath = folderPath + "\\" + fileName;
			file = new File(filePath);
		}
		else {
			fileIONotSupported = true;
			highScore = "0";
			return;
		}
		
		File dir = new File(folderPath);
		if (!dir.isDirectory() || recordIsDeprecated()) {
			dir.mkdirs();
			
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			initNewRecordFile();
		}
		
		readFile();
		highScore = "" + scores.get(difficulty);
	}
	
	private void readFile() {
		if (fileIONotSupported) return;
		String line = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			while ((line = reader.readLine()) != null) {
				String k = line.substring(0, line.indexOf('='));
				String v = line.substring(line.indexOf('=') + 1, line.length());
				scores.put(k, Integer.parseInt(v));
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void writeFile(String s) {
		if (fileIONotSupported) return;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(s);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void initNewRecordFile() {
		String out = "";
		for (String s : speeds) {
			scores.put(s, 0);
			out += s + "=0\n";
		}
		writeFile(out);
	}
	
	private String generateOutput() {
		String out = "";
		for (Enumeration<String> e = scores.keys(); e.hasMoreElements();) {
			String key = e.nextElement();
			out += key + "=" + scores.get(key) + "\n";
		}
		return out;
	}
	
	public String getHighScore() {
		return highScore;
	}
	
	public void setHighScore(String score) {
		if (highScore.equals(score)) return;
		newHighScore = true;
		highScore = score;
		scores.put(difficulty, Integer.parseInt(score));
		writeFile(generateOutput());
	}
	
	public boolean newHighScore() {
		return this.newHighScore;
	}
	
	public void resetHighScore() {
		setHighScore("0");
	}
	
	private boolean recordIsDeprecated() {
		boolean deprecated = true;
		
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			for (String s : speeds)
				if (line.contains(s)) deprecated = false;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return deprecated;
	}
}
