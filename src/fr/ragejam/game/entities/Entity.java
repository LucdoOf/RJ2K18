package fr.ragejam.game.entities;

import fr.ragejam.game.level.Level;
import fr.ragejam.game.level.tiles.Tile;

public abstract class Entity {

	private EntityType type;
	protected float x, y;
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

	public boolean setXY(float x, float y){
		Tile topLeft = getLevel().getTileAt((int)x/Tile.SIZE, (int)y/Tile.SIZE);
		Tile botLeft = getLevel().getTileAt((int)x/Tile.SIZE, (int)(y+Tile.SIZE-1)/Tile.SIZE);
		Tile topRight = getLevel().getTileAt((int)(x+Tile.SIZE-1)/Tile.SIZE, (int)y/Tile.SIZE);
		Tile botRight = getLevel().getTileAt((int)(x+Tile.SIZE-1)/Tile.SIZE, (int)(y+Tile.SIZE-1)/Tile.SIZE);
		if(topLeft == null || topLeft != null && !topLeft.isLandable()){
			if(botLeft == null || botLeft != null && !botLeft.isLandable()){
				if(topRight == null || topRight != null && !topRight.isLandable()){
					if(botRight == null || botRight != null && !botRight.isLandable()){
						this.x = x;
						this.y = y;
						return true;
					}
				}
			}
		}
		return false;
	}

	public void remove() {
		getLevel().removeEntity(this);
	}


}
