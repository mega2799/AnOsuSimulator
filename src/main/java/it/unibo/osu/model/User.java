package it.unibo.osu.model;

public class User {
	private static String username;

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		User.username = username;
	}
}
