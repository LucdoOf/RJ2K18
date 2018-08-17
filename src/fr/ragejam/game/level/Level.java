package fr.ragejam.game.level;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import fr.ragejam.game.level.tiles.ModulableTile;
import fr.ragejam.game.level.tiles.Tile;
import fr.ragejam.graphics.Texture;

public class Level {

	private List<Tile> tiles = new ArrayList<>();
	private String name;
	private int width, height;
	private boolean loaded = false;
	
	public Level(String name){
		this.name = name;
	}
	
	public void render(){
		for(Tile t : tiles) t.render();
	}
	
	public void update(){
		for(Tile t : tiles) t.update();
	}
	
	public void loadLevel(){
		int pixels[];
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("res/" + name + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		width = image.getWidth();
		height = image.getHeight();
		pixels = new int[width * height];
		image.getRGB(0, 0, width, height, pixels, 0, width);
		
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
				if(pixels[x + y * width] == 0xFF08C874){
					tiles.add(new ModulableTile(0, this, x, y, Texture.tile));
				}
			}
		}
		
		loaded = true;
		
	}
	
	public Tile getTileAt(int x, int y){
		for(Tile t : tiles){
			if(t.getX() == x && t.getY() == y){
				return t;
			}
		}
		return null;
	}
	
	public boolean isLoaded(){
		return loaded;
	}
	
	
}
