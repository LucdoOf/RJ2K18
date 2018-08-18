package fr.ragejam.game.entities;

import java.util.ArrayList;
import java.util.List;

import fr.ragejam.game.level.Level;
import fr.ragejam.game.level.tiles.Tile;

public abstract class LivingEntity extends Entity {

	public boolean died = false;
	public double airTime = 0;
	public List<Velocity> velocities = new ArrayList<>();

	public LivingEntity(EntityType type, Level level, float x, float y) {
		super(type, level, x, y);
	}

	public boolean isLanded(){
		Tile landingTile = getLevel().getTileAt(fr.ragejam.utils.Math.getIntegralPart((x+(Tile.SIZE-1))/Tile.SIZE), (int)(getY()/Tile.SIZE)+1);
		return landingTile != null && landingTile.isLandable();
	}

	public Tile getLandingTile(){
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
				setXY(getX(), (float) (integralFutur*Tile.SIZE));
			} else {
				setXY(getX(), (float) (getY()+(Math.exp(airTime/20)-Math.log(airTime+20)+2)/1.1));
			}
		} else {
			airTime = 0;
		}
		for(Velocity v : new ArrayList<>(velocities)) v.update();
	}

	public void addVelocity(Velocity v){
		velocities.add(v);
	}

	public void kill(){
		if(!died){
			died = true;
			remove();
		}
	}

	public boolean isDead(){
		return died;
	}


}
