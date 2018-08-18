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

			double futurY = (y+(Math.exp(airTime/20)-Math.log(airTime+20)+2)/1.1)/Tile.SIZE;
			double oldY = y/Tile.SIZE;
			double integralFutur = fr.ragejam.utils.Math.getIntegralPart(futurY);
			double integralOld = fr.ragejam.utils.Math.getIntegralPart(oldY);
			if(integralFutur != integralOld && getLevel().getTileAt((int)getX()/Tile.SIZE, (int)integralFutur+1) != null){
				y = (float) integralFutur*Tile.SIZE;
			} else {
				y+= (Math.exp(airTime/20)-Math.log(airTime+20)+2)/1.1;
			}
		} else {
			airTime = 0;
		}
	}



}
