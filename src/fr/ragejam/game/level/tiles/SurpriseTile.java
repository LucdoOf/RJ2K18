package fr.ragejam.game.level.tiles;

import fr.ragejam.game.entities.Entity;
import fr.ragejam.game.entities.LivingEntity;
import fr.ragejam.game.level.Level;
import fr.ragejam.graphics.Renderer;
import fr.ragejam.graphics.Texture;

public class SurpriseTile extends HidedTile {

	private boolean bad;
	private LivingEntity e;

	public SurpriseTile(Level level, int x, int y, boolean bad) {
		super(31, level, x, y, true);
		this.bad = bad;
	}

	@Override
	public boolean showCondition() {
		for(Entity e : getLevel().getEntities()){
			if(e instanceof LivingEntity){
				if(Math.sqrt(Math.pow(e.getX() - getX()*Tile.SIZE, 2) + Math.pow(e.getY() - getY()*Tile.SIZE, 2)) <= Tile.SIZE + 2){
					this.e = (LivingEntity) e;
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void show(){
		if(isHided()){
			if(e != null && bad){
				e.kill();
				e = null;
			}
			super.show();
		}
	}

	@Override
	public void render() {
		if(!isHided()){
			if(!this.bad) Texture.tile_surprise.bind();
			else Texture.tile_surprise_bad.bind();
			Renderer.quadData(getX()*Tile.SIZE, getY()*Tile.SIZE, Tile.SIZE, Tile.SIZE);
			if(!this.bad) Texture.tile_surprise.unbind();
			else Texture.tile_surprise_bad.unbind();
		} else {
			if(!this.bad) Texture.tile_surprise.bind();
			else Texture.tile_surprise_bad.bind();
			Renderer.quadData(getX()*Tile.SIZE, getY()*Tile.SIZE, Tile.SIZE, Tile.SIZE, new float[]{1, 1, 1, 0.5f});
			if(!this.bad) Texture.tile_surprise.unbind();
			else Texture.tile_surprise_bad.unbind();
		}
	}

}
