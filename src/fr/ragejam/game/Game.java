package fr.ragejam.game;

import java.io.IOException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.util.ResourceLoader;

import fr.ragejam.Component;
import fr.ragejam.game.entities.Bomber;
import fr.ragejam.game.entities.Player;
import fr.ragejam.game.level.Level;
import fr.ragejam.game.level.tiles.ModulableTile;
import fr.ragejam.game.level.tiles.Tile;
import fr.ragejam.graphics.Font;
import fr.ragejam.graphics.Renderer;
import fr.ragejam.graphics.Texture;
import fr.ragejam.utils.Math;

public class Game {

	public static Audio backgroundMusic, jumpSound, deadSound;
	private static Player player;
	public static boolean hasCharged = false;
	static {
		try {
			backgroundMusic = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream("res/Delight.ogg"));
			jumpSound = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream("res/Jump.ogg"));
			deadSound = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream("res/Hit_Hurt.ogg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private Level level;

	public Game(String levelname){
		levelname = "level_test";
		this.level = new Level(levelname);
		level.loadLevel();
		player = new Player(level, 33*Tile.SIZE, 17*Tile.SIZE);
		level.addEntity(player);

		if(backgroundMusic != null){
			backgroundMusic.playAsMusic(1, 0.2f, true);
			SoundStore.get().poll(0);
		}
	}

	public void render(){
		GL11.glTranslatef(Component.xScroll, Component.yScroll, 0);
		level.render();
		Texture.gradiant.bind();
		Renderer.quadData(-Component.getXScroll(), -Component.getYScroll(), Component.width, Component.height);
		Texture.gradiant.unbind();
		
		Font.render(getScore() + "", 10 - Component.getXScroll(), 10 - Component.getYScroll(), 8, new float[]{1, 1, 1, 1});
	}
	
	static long lastRTime;
	
	public void update(){
		if(Keyboard.isKeyDown(Keyboard.KEY_R) && System.currentTimeMillis()-lastRTime > 1000){
			Component.setGame(new Game("level_test2"));
			lastRTime = System.currentTimeMillis();
			System.out.println("Reset !");
		}

		level.update();

		if(level == null) return;
		if(Mouse.isButtonDown(0)){
			level.setTileAt((int)Component.getMouseX()/Tile.SIZE, (int)Component.getMouseY()/Tile.SIZE, new ModulableTile(0, level, (int)Component.getMouseX()/Tile.SIZE, (int)Component.getMouseY()/Tile.SIZE, Texture.tile_modulable_A));
		} else if(Mouse.isButtonDown(1)){
			level.setTileAt((int)Component.getMouseX()/Tile.SIZE, (int)Component.getMouseY()/Tile.SIZE, null);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_RETURN)){
			level.addEntity(new Bomber(level, Math.getIntegralPart(Component.getMouseX()), Math.getIntegralPart(Component.getMouseY())));
		}
	}
	
	public static int getScore(){
		return (int)player.getX()/Tile.SIZE;
	}

}
