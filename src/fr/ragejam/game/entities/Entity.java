package fr.ragejam.game.entities;

import fr.ragejam.game.level.Level;

public abstract class Entity {

	private EntityType type;
	public float x, y;
	private Level level;
	
	public Entity(EntityType type, Level level, float x, float y){
		this.type = type;
		this.x = x;
		this.y = y;
		this.level = level;
	}
	
	public abstract void render();
	public abstract void update();
	
	public EntityType getType(){
		return type;
	}
	
	public Level getLevel(){
		return level;
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}

	public void remove() {
		getLevel().removeEntity(this);
	}
	
	
}
