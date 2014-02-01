package com.maspain.snake.utilities;

public class Utility {
	
	public static boolean isPowerOf2(int value) {
		return ((value != 0) && ((value & (value - 1)) == 0));
	}
	
	public static int log2(int value) {
		if (!isPowerOf2(value)) return -1;
		int result = 0;
		while ((value >>= 1) != 0)
			result++;
		return result;
	}
	
	public static String covertToFormattedTimeString(long seconds) {
		String time = "";
		
		long minutes = seconds / 60;
		seconds -= minutes * 60;
		
		if (minutes < 10) time += "0";
		
		time += minutes + ":";
		
		if (seconds < 10) time += "0";
		
		time += seconds;
		
		return time;
	}
}
