package br.edu.ufabc.meuprimeirojogo;

public class Commands {
	public static boolean set[]= {false, false, false, false, false, false};
	public static final int UP    = 0;
	public static final int DOWN  = 1;
	public static final int LEFT  = 2;
	public static final int RIGHT = 3;
	public static final int SHOT  = 4;
	public static final int DEBUG = 5;
	
	public static boolean noCommand() {
		return !(set[UP] && set[DOWN] && set[LEFT] && set[RIGHT] && set[SHOT] && set[DEBUG]);
	}
	public static String str() {
		String r="{";
		for (int i=0;i<set.length;i++) {
			r+=set[i]+ "  ";
		}
		r+="}";
		return r;
	}
}
