package fr.ragejam.game.level.tiles;

import java.util.ArrayList;

import fr.ragejam.game.entities.Entity;
import fr.ragejam.game.entities.LivingEntity;
import fr.ragejam.game.entities.Player;
import fr.ragejam.game.level.Level;
import fr.ragejam.graphics.Renderer;
import fr.ragejam.graphics.Texture;

public class HidedSpikesTile extends HidedTile {

	private long showTime;
	private long animDelay = 40;
	private int size;
	private int xo = 0, yo = 0;

	public HidedSpikesTile(Level level, int x, int y, int size) {
		super(8, level, x, y);
		this.size = size;
	}

	@Override
	public boolean showCondition() {
		for(Entity e : getLevel().getEntities()){
			if(e instanceof Player){
				if(Math.sqrt(Math.pow(e.getX() - getX()*Tile.SIZE, 2)) <= Tile.SIZE + 1){
					show();
				}
			}
		}
		return false;
	}

	@Override
	public void show(){
		if(!hided) return;
		hided = false;
		showTime = System.currentTimeMillis();
	}

	@Override
	public void render() {
		if(!isHided()){

			boolean top = getLevel().getTileAt(getX(), getY()-1) == null ? false : getLevel().getTileAt(getX(), getY()-1).getId() != getId() ? true : false;
			boolean bot = getLevel().getTileAt(getX(), getY()+1) == null ? false : getLevel().getTileAt(getX(), getY()+1).getId() != getId() ? true : false;
			boolean left = getLevel().getTileAt(getX()-1, getY()) == null ? false : getLevel().getTileAt(getX()-1, getY()).getId() != getId() ? true : false;
			boolean right = getLevel().getTileAt(getX()+1, getY()) == null ? false : getLevel().getTileAt(getX()+1, getY()).getId() != getId() ? true : false;

			if(bot){
				xo = 1;
				yo = 0;
			} else if(top){
				xo = 1;
				yo = 1;
			} else if(left){
				xo = 0; 
				yo = 0;
			} else if(right){
				xo = 0;
				yo = 1;
			}

			if(System.currentTimeMillis() - showTime <= animDelay){
				if(size == 0) Texture.tile_spike_small.bind();
				else Texture.tile_spike_big.bind();
				Renderer.quadData(getX()*Tile.SIZE, (getY()+1)*Tile.SIZE - (float)(((float)Tile.SIZE/animDelay)*(System.currentTimeMillis()-showTime)), Tile.SIZE, Tile.SIZE, xo, yo, 2, 2);
				if(size == 0) Texture.tile_spike_small.unbind();
				else Texture.tile_spike_big.unbind();
			} else {
				if(size == 0) Texture.tile_spike_small.bind();
				else Texture.tile_spike_big.bind();
				Renderer.quadData(getX()*Tile.SIZE, getY()*Tile.SIZE, Tile.SIZE, Tile.SIZE, xo, yo, 2, 2);
				if(size == 0) Texture.tile_spike_small.unbind();
				else Texture.tile_spike_big.unbind();
			}
		}
	}
	
	@Override
	public void update(){
		super.update();
		if(!isHided())
		for(Entity e : new ArrayList<>(getLevel().getEntities())){
			if(e instanceof LivingEntity){
				
				if(this.equals(getLevel().getTileAt((int)(e.getX()+Tile.SIZE+1)/Tile.SIZE, (int)e.getY()/Tile.SIZE))){
					((LivingEntity) e).kill();
				}
				if(size == 1 && this.equals(((LivingEntity) e).getLandingTile())){
					((LivingEntity) e).kill();
				} else if(size == 0 && this.equals(((LivingEntity) e).getLandingTile()) && e.getY()%1 > 0.5){
					((LivingEntity) e).kill();
				}
			}
		}
	}

}
