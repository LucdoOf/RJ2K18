package fr.ragejam.game;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import fr.ragejam.Component;
import fr.ragejam.game.entities.Player;
import fr.ragejam.game.level.Level;
import fr.ragejam.game.level.tiles.ModulableTile;
import fr.ragejam.game.level.tiles.Tile;
import fr.ragejam.graphics.Renderer;
import fr.ragejam.graphics.Texture;

public class Game {
	
	private Level level;
	
	public Game(){
		this.level = new Level("level_test");
		level.loadLevel();
		level.addEntity(new Player(level, 20, 30));
	}
	
	public void render(){
		level.render();
		Texture.gradiant.bind();
		Renderer.quadData(0, 0, Component.width, Component.height);
		Texture.gradiant.unbind();
	}
	
	public void update(){
		
		if(Keyboard.isKeyDown(Keyboard.KEY_R)){
			Component.setGame(new Game());
		}
		
		level.update();
		
		if(level == null) return;
		if(Mouse.isButtonDown(0)){
			level.setTileAt((int)Component.getMouseX()/Tile.SIZE, (int)Component.getMouseY()/Tile.SIZE, new ModulableTile(0, level, (int)Component.getMouseX()/Tile.SIZE, (int)Component.getMouseY()/Tile.SIZE, Texture.tile));
		} else if(Mouse.isButtonDown(1)){
			level.setTileAt((int)Component.getMouseX()/Tile.SIZE, (int)Component.getMouseY()/Tile.SIZE, null);
		}
		
	}
	
	public void translateView(float xa, float ya){
		if(xa > 0) xa = 0;
		if(ya > 0) ya = 0;
		//if(level != null && xa < -level.getWidth() * 16 + 16 + Display.getWidth() / Component.scale) xa = -level.getWidth() * 16 + 16 + Display.getWidth() / Component.scale;
		//if(level != null && ya < -level.getHeight() * 16 + 16 + Display.getHeight() / Component.scale) ya = -level.getHeight() * 16 + 16 + Display.getHeight() / Component.scale;		
		Component.xScroll = xa;
		Component.yScroll = ya;
	}

}
