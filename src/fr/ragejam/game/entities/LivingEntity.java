package fr.ragejam.game.entities;

import fr.ragejam.game.level.Level;
import fr.ragejam.game.level.tiles.Tile;

public abstract class LivingEntity extends Entity {

	public double airTime = 0;
	
	public LivingEntity(EntityType type, Level level, float x, float y) {
		super(type, level, x, y);
	}

	public boolean isLanded(){
		return getLevel().getTileAt((int)getX()/Tile.SIZE, (int)(getY()/Tile.SIZE)+1) != null;
	}
	
	public Tile getLandedTile(){
		return getLevel().getTileAt((int)getX()/Tile.SIZE, (int)(getY()/Tile.SIZE)+1);
	}
	
	public void update(){
		if(!isLanded()){
			airTime++;
			y+= (Math.exp(airTime/20)-Math.log(airTime+20)+2)/1.1;
		} else {
			airTime = 0;
		}
	}
	
	
	
}
