package fr.ragejam.game.entities;

import org.lwjgl.input.Keyboard;

import fr.ragejam.game.level.Level;
import fr.ragejam.game.level.tiles.Tile;
import fr.ragejam.graphics.Renderer;
import fr.ragejam.graphics.Texture;

public class Player extends LivingEntity {

	private long lastUpdateTime;
	private long delay = 200;
	private int xo=0, yo=1;
	private int width = 16, height = 16;

	public Player(Level level, float x, float y) {
		super(EntityType.PLAYER, level, x, y);
	}

	@Override
	public void render() {
		Texture.player_classic.bind();
		Renderer.quadData(x, y, width, height, xo, yo, 4,2);
		Texture.player_classic.unbind();
	}

	@Override
	public void update() {
		if(getLevel().getTileAt(fr.ragejam.utils.Math.getIntegralPart(x/Tile.SIZE)+1, fr.ragejam.utils.Math.getIntegralPart((y+height/2)/Tile.SIZE)) == null) x+=2;
		super.update();
		if(System.currentTimeMillis() - lastUpdateTime > delay && isLanded()){
			lastUpdateTime = System.currentTimeMillis();
			if(xo == 3) xo = 0;
			else xo++;
		}
		
		if(isLanded()){
			if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
				addVelocity(new Velocity() {
					private long jumpTime = 30;
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
	}

	
}
