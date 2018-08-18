package fr.ragejam.game.entities;

import fr.ragejam.game.level.Level;
import fr.ragejam.game.level.tiles.Tile;

public abstract class LivingEntity extends Entity {

	public LivingEntity(EntityType type, Level level, float x, float y) {
		super(type, level, x, y);
	}

	public boolean isLanded(){
		return getLevel().getTileAt((int)getX()/Tile.SIZE, (int)(getY()/Tile.SIZE)+1) != null;
	}
	
	
}
