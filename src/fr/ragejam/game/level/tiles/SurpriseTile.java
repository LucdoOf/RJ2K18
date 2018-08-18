package fr.ragejam.game.level.tiles;

import fr.ragejam.game.Game;
import fr.ragejam.game.entities.Entity;
import fr.ragejam.game.entities.Player;
import fr.ragejam.game.level.Level;
import fr.ragejam.graphics.Renderer;
import fr.ragejam.graphics.Texture;

public class SurpriseTile extends HidedTile {

	public SurpriseTile(Level level, int x, int y) {
		super(31, level, x, y, false);
	}

	@Override
	public boolean showCondition() {
		for(Entity e : getLevel().getEntities()){
			if(e instanceof Player){
				if(Math.sqrt(Math.pow(e.getX() - getX()*Tile.SIZE, 2) + Math.pow(e.getY() - getY()*Tile.SIZE, 2)) <= Tile.SIZE + 2){
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public void show(){
		super.show();
		if(hided)Game.deadSound.playAsSoundEffect(1, 1, false);
	}

	@Override
	public void render() {
		if(!isHided()){
			Texture.tile_surprise.bind();
			Renderer.quadData(getX()*Tile.SIZE, getY()*Tile.SIZE, Tile.SIZE, Tile.SIZE);
			Texture.tile_surprise.unbind();
		} else {
			Texture.tile_surprise.bind();
			Renderer.quadData(getX()*Tile.SIZE, getY()*Tile.SIZE, Tile.SIZE, Tile.SIZE, new float[]{1, 1, 1, 0.5f});
			Texture.tile_surprise.unbind();
		}
	}

}
