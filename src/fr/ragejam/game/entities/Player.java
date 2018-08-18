package fr.ragejam.game.entities;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;

import fr.ragejam.Component;
import fr.ragejam.game.DeadMenu;
import fr.ragejam.game.Game;
import fr.ragejam.game.level.Level;
import fr.ragejam.game.level.tiles.Tile;
import fr.ragejam.graphics.Renderer;
import fr.ragejam.graphics.Texture;

public class Player extends LivingEntity {

	private long lastUpdateTime;
	private long delay = 200;
	private int xo=0, yo=1;
	private int width = 16, height = 16;
	private long deadTime;
	private long maxDeadTime = 500;

	public Player(Level level, float x, float y) {
		super(EntityType.PLAYER, level, x, y);
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

			if(isLanded()){
				if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
					Game.jumpSound.playAsSoundEffect(1f, 1f, false);
					addVelocity(new Velocity() {
						private long jumpTime = 20;
						@Override
						public void update() {
							jumpTime--;
							if(jumpTime <= 0) velocities.remove(this);
							else y-=Math.log(jumpTime);
							if(isLanded()) velocities.remove(this);
						}
					});
				}
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
		if(getY()>getLevel().getHeight()*Tile.SIZE) kill();
		getLevel().translateView(-x + Component.width / 2 - width / 2, -y + Component.height / 2 - height / 2);
	}
	

	@Override
	public void kill(){
		Game.deadSound.playAsSoundEffect(1f, 1f, false);
		xo=0;
		yo =2;
		deadTime = System.currentTimeMillis();
		died = true;
	}


}
