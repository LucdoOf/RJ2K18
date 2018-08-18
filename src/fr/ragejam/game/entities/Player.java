package fr.ragejam.game.entities;

import fr.ragejam.game.level.Level;
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
		Renderer.quadData(getX(), getY(), width, height, xo, yo, 4,2);
		Texture.player_classic.unbind();
	}

	@Override
	public void update() {
		if(System.currentTimeMillis() - lastUpdateTime > delay){
			lastUpdateTime = System.currentTimeMillis();
			if(xo == 3) xo = 0;
			else xo++;
		}
	}

}
