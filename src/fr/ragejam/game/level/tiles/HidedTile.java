package fr.ragejam.game.level.tiles;

import fr.ragejam.game.level.Level;

public abstract class HidedTile extends Tile {

	protected boolean hided = true;
	
	public HidedTile(int id, Level level, int x, int y) {
		super(id, level, x, y, false);
	}
	
	public abstract boolean showCondition();
	
	@Override
	public void update(){
		if(showCondition()){
			show();
		}
	}
	
	public void show(){
		hided = false;
	}
	
	public void hide(){
		hided = true;
	}
	
	public boolean isHided(){
		return hided;
	}

}
