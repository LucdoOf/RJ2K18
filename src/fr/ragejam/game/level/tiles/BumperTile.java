package fr.ragejam.game.level.tiles;

import fr.ragejam.game.entities.Entity;
import fr.ragejam.game.entities.LivingEntity;
import fr.ragejam.game.entities.Velocity;
import fr.ragejam.game.level.Level;
import fr.ragejam.graphics.Renderer;
import fr.ragejam.graphics.Texture;

public class BumperTile extends Tile {

	private boolean enabled = false;
	private long lastUpdateTime;
	private long delay = 50;
	private int xo = 0, yo = 0;

	public BumperTile(Level level, int x, int y) {
		super(30, level, x, y, false);
	}

	@Override
	public void render() {
		Texture.tile_bumper.bind();
		Renderer.quadData(getX()*Tile.SIZE, getY()*Tile.SIZE, Tile.SIZE, Tile.SIZE, xo, yo, 4, 1);
		Texture.tile_bumper.unbind();
	}

	@Override
	public void update() {
		if(enabled){
			if(System.currentTimeMillis() - lastUpdateTime > delay){
				lastUpdateTime = System.currentTimeMillis();
				if(xo == 3){
					xo = 0;
					enabled = false;
				} else {
					xo++;
				}
			}
		}

		for(Entity e : getLevel().getEntities()){
			if(e instanceof LivingEntity){
				if(Math.sqrt(Math.pow((getX()+0.5)*Tile.SIZE - e.getX(), 2) + Math.pow((getY()+0.5)*Tile.SIZE - e.getY(), 2)) <= Tile.SIZE/2){
					lastUpdateTime = System.currentTimeMillis();
					enabled = true;
					((LivingEntity) e).addVelocity(new Velocity() {
						private long jumpTime = 20;
						@Override
						public void update() {
							jumpTime--;
							if(jumpTime <= 0){
								((LivingEntity) e).removeVelocity(this);
							} else {
								e.setXY(e.getX(), (float) (e.getY()-Math.log(jumpTime)*2));
							}
							if(((LivingEntity) e).isLanded()) ((LivingEntity) e).removeVelocity(this);
						}
					});
				}
			}
		}
	}

}
