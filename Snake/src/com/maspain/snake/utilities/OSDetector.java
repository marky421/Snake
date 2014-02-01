package com.maspain.snake.utilities;

public class OSDetector {
	
	public enum Platform {
		Windows, Mac, Unix, Solaris, unsupported
	}
	
	private static Platform platform = null;
	
	public static Platform getOS() {
		if (platform == null) {
			String os = System.getProperty("os.name").toLowerCase();
			if (os.indexOf("win") >= 0) platform = Platform.Windows;
			else if (os.indexOf("mac") >= 0) platform = Platform.Mac;
			else if (os.indexOf("nux") >= 0) platform = Platform.Unix;
			else if (os.indexOf("nix") >= 0) platform = Platform.Unix;
			else if (os.indexOf("aix") >= 0) platform = Platform.Unix;
			else if (os.indexOf("sunos") >= 0) platform = Platform.Solaris;
			else platform = Platform.unsupported;
		}
		return platform;
	}
	
	public static boolean isWindows() {
		return (getOS() == Platform.Windows);
	}
	
	public static boolean isMac() {
		return (getOS() == Platform.Mac);
	}
	
	public static boolean isUnix() {
		return (getOS() == Platform.Unix);
	}
	
	public static boolean isSolaris() {
		return (getOS() == Platform.Solaris);
	}
}
