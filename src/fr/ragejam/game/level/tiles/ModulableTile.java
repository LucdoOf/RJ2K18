package fr.ragejam.game.level.tiles;

import fr.ragejam.game.level.Level;
import fr.ragejam.graphics.Renderer;
import fr.ragejam.graphics.Texture;

public class ModulableTile extends Tile {

	private Texture texture;
	private int xo = 6, yo = 6;
	
	public ModulableTile(int id, Level level, int x, int y, Texture texture) {
		super(id, level, x, y);
		this.texture = texture;
	}

	@Override
	public void render() {
		
		
		boolean top = getLevel().getTileAt(getX(), getY()-1) == null ? false : getLevel().getTileAt(getX(), getY()-1).getId() == getId() ? true : false;
		boolean bot = getLevel().getTileAt(getX(), getY()+1) == null ? false : getLevel().getTileAt(getX(), getY()+1).getId() == getId() ? true : false;
		boolean left = getLevel().getTileAt(getX()-1, getY()) == null ? false : getLevel().getTileAt(getX()-1, getY()).getId() == getId() ? true : false;
		boolean right = getLevel().getTileAt(getX()+1, getY()) == null ? false : getLevel().getTileAt(getX()+1, getY()).getId() == getId() ? true : false;
		boolean topRight = getLevel().getTileAt(getX()+1, getY()-1) == null ? false : getLevel().getTileAt(getX()+1, getY()-1).getId() == getId() ? true : false;
		boolean topLeft = getLevel().getTileAt(getX()-1, getY()-1) == null ? false : getLevel().getTileAt(getX()-1, getY()-1).getId() == getId() ? true : false;
		boolean botRight = getLevel().getTileAt(getX()+1, getY()+1) == null ? false : getLevel().getTileAt(getX()+1, getY()+1).getId() == getId() ? true : false;
		boolean botLeft = getLevel().getTileAt(getX()-1, getY()+1) == null ? false : getLevel().getTileAt(getX()-1, getY()+1).getId() == getId() ? true : false;
		
		if(!top && bot && !left && right && botRight) setXoYo(0, 0);
		else if(!top && bot && left && right && botRight && botLeft) setXoYo(1, 0);
		else if(!top && bot && left && !right && botLeft) setXoYo(2, 0);
		else if(!top && bot && !left && !right) setXoYo(3, 0);
		else if(!top && !bot && left && !right) setXoYo(4, 0);
		else if(top && bot && !left && right && !topRight && !topLeft && botRight && !botLeft) setXoYo(5, 0);
		else if(top && bot && !left && right && topRight && !botRight) setXoYo(6, 0);
		
		else if(top && bot && !left && right && topRight && botRight) setXoYo(0, 1);
		else if(top && bot && left && right && topRight && topLeft && botRight && botLeft) setXoYo(1, 1);
		else if(top && bot && left && !right && topLeft && botLeft) setXoYo(2, 1);
		else if(!top && !bot && !left && right) setXoYo(3, 1);
		else if(top && !bot && !left && !right) setXoYo(4, 1);
		else if(top && bot && left && !right && topLeft && !botLeft) setXoYo(5, 1);
		else if(top && bot && left && !right && !topLeft && botLeft) setXoYo(6, 1);
		
		else if(top && !bot && !left && right && topRight) setXoYo(0, 2);
		else if(top && !bot && left && right && topRight && topLeft) setXoYo(1, 2);
		else if(top && !bot && left && !right && topLeft) setXoYo(2, 2);
		else if(top && bot && !left && !right) setXoYo(3, 2);
		else if(!top && !bot && left && right) setXoYo(4, 2);
		else if(top && !bot && left && right && topRight && !topLeft && !botRight && !botLeft) setXoYo(5, 2);
		else if(top && !bot && left && right && !topRight && topLeft) setXoYo(6, 2);
		
		else if(top && bot && left && right && !topRight && !topLeft && !botRight && !botLeft) setXoYo(0, 3);
		else if(top && bot && left && right && !topRight && !topLeft && botRight && !botLeft) setXoYo(1, 3);
		else if(top && bot && left & right && !topRight && !topLeft && !botRight && botLeft) setXoYo(2, 3);
		else if(top && bot && left && right && !topRight && topLeft && !botRight && !botLeft) setXoYo(3, 3);
		else if(top && bot && left && right && topRight && !topLeft && !botRight && !botLeft) setXoYo(4, 3);
		else if(!top && bot && left && right && botLeft && !botRight) setXoYo(5, 3);
		else if(!top && bot && left && right && !botLeft && botRight) setXoYo(6, 3);
		
		else if(!top && !bot && !left && !right) setXoYo(0, 4);
		else if(top && bot && left && right && botLeft && botRight && !topRight && !topLeft) setXoYo(1, 4);
		else if(top && bot && left && right && !botLeft && botRight && topRight && !topLeft) setXoYo(2, 4);
		else if(top && bot && left && right && !botLeft && !botRight && topRight && topLeft) setXoYo(3, 4);
		else if(top && bot && left && right && botLeft && !botRight && !topRight && topLeft) setXoYo(4, 4);
		
		else if(top && bot && left && right && botLeft && botRight && topRight && !topLeft) setXoYo(0, 5);
		else if(top && bot && left && right && botLeft && botRight && !topRight && topLeft) setXoYo(1, 5);
		else if(top && bot && left && right && botLeft && !botRight && topRight && topLeft) setXoYo(2, 5);
		else if(top && bot && left && right && !botLeft && botRight && topRight && topLeft) setXoYo(3, 5);
		else if(top && bot && !left && right && !botRight && !topRight) setXoYo(4, 5);
		else if(top && bot && left && !right && !botLeft && !topLeft) setXoYo(5, 5);
		
		else if(!top && bot && !left && right && !botRight) setXoYo(0, 6);
		else if(!top && bot && left && !right && !botLeft) setXoYo(1, 6);
		else if(top && !bot && right && !left && !topRight) setXoYo(2, 6);
		else if(top && !bot && !right && left && !topLeft) setXoYo(3, 6);
		else if(top && !bot && left && right && !topRight && !topLeft) setXoYo(4, 6);
		else if(!top && bot && left && right && !botLeft && !botRight) setXoYo(5, 6);
		
		Texture.tile.bind();
		Renderer.quadData(getX()*Tile.SIZE, getY()*Tile.SIZE, SIZE, SIZE, xo, yo, 7, 7);
		Texture.tile.unbind();
		
	}

	@Override
	public void update() {
		
	}
	
	public void setXoYo(int xo, int yo){
		this.xo = xo;
		this.yo = yo;
	}
	
	public Texture getTexture(){
		return texture;
	}

}
