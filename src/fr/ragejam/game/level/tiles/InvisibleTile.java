package fr.ragejam.game.level.tiles;

import fr.ragejam.game.level.Level;

public class InvisibleTile extends Tile {

	public InvisibleTile(Level level, int x, int y) {
		super(32, level, x, y, true);
	}

	@Override
	public void render() {}

	@Override
	public void update() {}

}
