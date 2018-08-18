package fr.ragejam.game.entities;

import org.lwjgl.input.Keyboard;

import fr.ragejam.Component;
import fr.ragejam.game.DeadMenu;
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
			Texture.player_classic.bind();
			Renderer.quadData(x, y, width, height, color, xo, yo, 4,3);
			Texture.player_classic.unbind();
		} else {
			float coef = (float)1/500;
			float opacity = 1 - (coef * (System.currentTimeMillis()-deadTime));
			float[] color = new float[]{1, 1, 1, opacity};
			Texture.player_classic.bind();
			Renderer.quadData(x, y, width, height, color, xo, yo, 4,3);
			Texture.player_classic.unbind();
		}
	}

	@Override
	public void update() {
		if(!isDead()){
			super.update();
			if(!isLanded()){
				Tile detectedTile = getLevel().getTileAt(fr.ragejam.utils.Math.getIntegralPart(x/Tile.SIZE)+1, fr.ragejam.utils.Math.getIntegralPart(y/Tile.SIZE)+1);
				if(detectedTile == null || detectedTile != null && !detectedTile.isLandable()) x+=2;
			} else {
				Tile detectedTile = getLevel().getTileAt(fr.ragejam.utils.Math.getIntegralPart(x/Tile.SIZE)+1, fr.ragejam.utils.Math.getIntegralPart((y+height/2)/Tile.SIZE));
				if(detectedTile == null || detectedTile != null && !detectedTile.isLandable()) x+=2;
			}
			if(System.currentTimeMillis() - lastUpdateTime > delay && isLanded()){
				lastUpdateTime = System.currentTimeMillis();
				if(xo == 3) xo = 0;
				else xo++;
			}
		} else {
			if(System.currentTimeMillis()-deadTime <= maxDeadTime){
				y--;
				x = (float)(x+Math.sin(y)*1);
			} else {
				super.kill();
				new DeadMenu().show();
			}
		}
	}


	@Override
	public void kill(){
		if(!died){
			Game.deadSound.playAsSoundEffect(1f, 1f, false);
			xo=0;
			yo =2;
			deadTime = System.currentTimeMillis();
			died = true;
		}
	}


}
