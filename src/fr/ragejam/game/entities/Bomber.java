package fr.ragejam.game.entities;

import fr.ragejam.game.Game;
import fr.ragejam.game.level.Level;
import fr.ragejam.game.level.tiles.Tile;
import fr.ragejam.graphics.Renderer;
import fr.ragejam.graphics.Texture;

public class Bomber extends LivingEntity {

	private long lastUpdateTime;
	private long delay = 200;
	private int xo=0, yo=1;
	private int width = 16, height = 16;
	private long deadTime;
	private long maxDeadTime = 500;

	public Bomber(Level level, float x, float y) {
		super(EntityType.BOMBER, level, x, y);
	}

	@Override
	public void render() {
		if(!isDead()){
			float[] color = new float[]{1, 1, 1, 1};
			Texture.bomber_classic.bind();
			Renderer.quadData(x, y, width, height, color, xo, yo, 4,3);
			Texture.bomber_classic.unbind();
		} else {
			float coef = (float)1/500;
			float opacity = 1 - (coef * (System.currentTimeMillis()-deadTime));
			float[] color = new float[]{1, 1, 1, opacity};
			Texture.bomber_classic.bind();
			Renderer.quadData(x, y, width, height, color, xo, yo, 4,3);
			Texture.bomber_classic.unbind();
		}
	}

	@Override
	public void update() {
		if(!isDead()){
			super.update();
			setXY(getX()-4, getY());
			if(System.currentTimeMillis() - lastUpdateTime > delay && isLanded()){
				lastUpdateTime = System.currentTimeMillis();
				if(xo == 0) xo = 3;
				else xo--;
			}
			for(Entity e : getLevel().getEntities()){
				if(e instanceof Player){
					if(Math.sqrt(Math.pow(e.getX() - getX(), 2) + Math.pow(e.getY() - getY(), 2)) <= Tile.SIZE-2){
						((Player) e).kill();
						kill();
					}
				}
			}
		} else {
			if(System.currentTimeMillis()-deadTime <= maxDeadTime){
				y--;
				x = (float)(x+Math.sin(y)*1);
			} else {
				super.kill();
			}
		}
	}


	@Override
	public void kill(){
		if(!died){
			Game.deadSound.playAsSoundEffect(1f, 1f, false);
			xo=3;
			yo =2;
			deadTime = System.currentTimeMillis();
			died = true;
		}
	}
	


}
