package fr.ragejam.game.level.tiles;

import fr.ragejam.game.level.Level;

public abstract class Tile {

	public static final int SIZE = 16;
	
	private int id, x, y;
	private Level level;

	public Tile(int id, Level level, int x, int y){
		this.id = id;
		this.x = x;
		this.y = y;
		this.level = level;
	}
	
	public abstract void render();
	public abstract void update();
	
	public int getId(){
		return id;
	}
	
	public Level getLevel(){
		return level;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
}
