package fr.ragejam.game.level;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.Display;

import fr.ragejam.Component;
import fr.ragejam.game.entities.Bomber;
import fr.ragejam.game.entities.Entity;
import fr.ragejam.game.entities.Player;
import fr.ragejam.game.level.tiles.BumperTile;
import fr.ragejam.game.level.tiles.HidedSpikesTile;
import fr.ragejam.game.level.tiles.InvisibleTile;
import fr.ragejam.game.level.tiles.ModulableTile;
import fr.ragejam.game.level.tiles.SpikesTile;
import fr.ragejam.game.level.tiles.SurpriseTile;
import fr.ragejam.game.level.tiles.Tile;
import fr.ragejam.graphics.Renderer;
import fr.ragejam.graphics.Texture;

public class Level {

	private List<Tile> tiles = new ArrayList<>();
	private List<Entity> entities = new ArrayList<>();
	private String name;
	private int width, height;
	private boolean loaded = false;
	private float[] backgroundColor = {1, 1, 1, 1};
	private int columnChanging = 0;
	private boolean upChanging;
	private long lastUpdateTime;
	private long delay = 5000;
	
	public Level(String name){
		this.name = name;
	}

	public void render(){
		if(!isLoaded()) return;
		Texture.background_g.bind();
		for(int i = 0; i < 10; i++){
			Renderer.quadData(-Component.getXScroll() +Component.getXScroll()/3 + Component.width*i, 0, Component.width, Component.height, backgroundColor);
			Renderer.quadData(-Component.getXScroll() +Component.getXScroll()/3 + Component.width*i, Component.height, Component.width, Component.height, backgroundColor);
			Renderer.quadData(-Component.getXScroll() +Component.getXScroll()/3 + Component.width*i, Component.height*2, Component.width, Component.height, backgroundColor);
		}
		Texture.background_g.unbind();

		List<Tile> toRender = new ArrayList<>(tiles);
		for(Tile t : toRender){
			if(t.getX()*Tile.SIZE >= -Component.getXScroll()-Tile.SIZE && t.getX()*Tile.SIZE <= -Component.getXScroll()+Component.width)t.render();
		}
		List<Entity> toRender1 = new ArrayList<>(entities);
		for(Entity t : toRender1){
			if(t.getX() >= -Component.getXScroll() && t .getX() <= -Component.getXScroll()+Component.width) t.render();
		}
	}

	public void update(){

		if((backgroundColor[columnChanging] >= 0.8f || backgroundColor[columnChanging] <= 0.5f) && System.currentTimeMillis() - lastUpdateTime >= delay){
			lastUpdateTime = System.currentTimeMillis();
			Random random = new Random();
			columnChanging = random.nextInt(3-0+1)+0;
			if(backgroundColor[columnChanging] >= 1f){
				upChanging = false;
			} else {
				upChanging = true;
			}
		}
		if(upChanging && backgroundColor[columnChanging] <= 0.8f){
			backgroundColor[columnChanging] = backgroundColor[columnChanging] + 1/127f;
		} else if(!upChanging && backgroundColor[columnChanging] >= 0.5f){
			backgroundColor[columnChanging] = backgroundColor[columnChanging] - 1/127f;
		}



		if(!isLoaded()) return;
		List<Tile> toUpdate = new ArrayList<>(tiles);
		for(Tile t : toUpdate){
			if(t.getX()*Tile.SIZE >= -Component.getXScroll()-Tile.SIZE && t.getX()*Tile.SIZE <= -Component.getXScroll()+Component.width)t.update();
		}
		List<Entity> toUpdate1 = new ArrayList<>(entities);
		for(Entity t : toUpdate1){
			if((t.getX() >= -Component.getXScroll() && t .getX() <= -Component.getXScroll()+Component.width) || t instanceof Player) t.update();
		}
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
					tiles.add(new ModulableTile(0, this, x, y, Texture.tile_modulable_A));
				} else if(pixels[x + y * width] == 0xFFFFE345){
					tiles.add(new ModulableTile(1, this, x, y, Texture.tile_modulable_B));
				} else if(pixels[x + y * width] == 0xFFE94525){
					tiles.add(new ModulableTile(2, this, x, y, Texture.tile_modulable_C));
				} else if(pixels[x + y * width] == 0xFFAA56B9){
					tiles.add(new ModulableTile(3, this, x, y, Texture.tile_modulable_D));
				} else if(pixels[x + y * width] == 0xFF2EA2EB){
					tiles.add(new ModulableTile(4, this, x, y, Texture.tile_modulable_E));
				} else if(pixels[x + y * width] == 0xFF12CBAF){
					tiles.add(new ModulableTile(5, this, x, y, Texture.tile_modulable_F));
				} else if(pixels[x + y * width] == 0xFFC8CDCF){
					tiles.add(new ModulableTile(6, this, x, y, Texture.tile_modulable_G));
				} else if(pixels[x + y * width] == 0xFF656A73){
					tiles.add(new SpikesTile(this, x, y, 0));
				} else if(pixels[x + y * width] == 0xFF40414C){
					tiles.add(new SpikesTile(this, x, y, 1));
				} else if(pixels[x + y * width] == 0xFF262933){
					tiles.add(new HidedSpikesTile(this, x, y, 0, false));
				} else if(pixels[x + y * width] == 0xFF191A23){
					tiles.add(new HidedSpikesTile(this, x, y, 1, false));
				} else if(pixels[x + y * width] == 0xFF08E77B){
					tiles.add(new ModulableTile(9, this, x, y, Texture.tile_modulable_2_A));
				} else if(pixels[x + y * width] == 0xFFFFF56C){
					tiles.add(new ModulableTile(10, this, x, y, Texture.tile_modulable_2_B));
				} else if(pixels[x + y * width] == 0xFFFA6A39){
					tiles.add(new ModulableTile(11, this, x, y, Texture.tile_modulable_2_C));
				} else if(pixels[x + y * width] == 0xFFDE73DF){
					tiles.add(new ModulableTile(12, this, x, y, Texture.tile_modulable_2_D));
				} else if(pixels[x + y * width] == 0xFF32BBF8){
					tiles.add(new ModulableTile(13, this, x, y, Texture.tile_modulable_2_E));
				} else if(pixels[x + y * width] == 0xFF21E9C1){
					tiles.add(new ModulableTile(14, this, x, y, Texture.tile_modulable_2_F));
				} else if(pixels[x + y * width] == 0xFFDDE3E3){
					tiles.add(new ModulableTile(15, this, x, y, Texture.tile_modulable_2_G));
				} else if(pixels[x + y * width] == 0xFF0E9E5E){
					tiles.add(new ModulableTile(16, this, x, y, Texture.tile_modulable_3_A));
				} else if(pixels[x + y * width] == 0xFFEAB208){
					tiles.add(new ModulableTile(17, this, x, y, Texture.tile_modulable_3_B));
				} else if(pixels[x + y * width] == 0xFFBE3522){
					tiles.add(new ModulableTile(18, this, x, y, Texture.tile_modulable_3_C));
				} else if(pixels[x + y * width] == 0xFF8846A0){
					tiles.add(new ModulableTile(19, this, x, y, Texture.tile_modulable_3_D));
				} else if(pixels[x + y * width] == 0xFF2A81CB){
					tiles.add(new ModulableTile(20, this, x, y, Texture.tile_modulable_3_E));
				} else if(pixels[x + y * width] == 0xFF0C9D94){
					tiles.add(new ModulableTile(21, this, x, y, Texture.tile_modulable_3_F));
				} else if(pixels[x + y * width] == 0xFFA8B0B3){
					tiles.add(new ModulableTile(22, this, x, y, Texture.tile_modulable_3_G));
				} else if(pixels[x + y * width] == 0xFF0A8057){
					tiles.add(new ModulableTile(23, this, x, y, Texture.tile_modulable_4_A));
				} else if(pixels[x + y * width] == 0xFFE48914){
					tiles.add(new ModulableTile(24, this, x, y, Texture.tile_modulable_4_B));
				} else if(pixels[x + y * width] == 0xFFA22828){
					tiles.add(new ModulableTile(25, this, x, y, Texture.tile_modulable_4_C));
				} else if(pixels[x + y * width] == 0xFF693C81){
					tiles.add(new ModulableTile(26, this, x, y, Texture.tile_modulable_4_D));
				} else if(pixels[x + y * width] == 0xFF2561A5){
					tiles.add(new ModulableTile(27, this, x, y, Texture.tile_modulable_4_E));
				} else if(pixels[x + y * width] == 0xFF0E7079){
					tiles.add(new ModulableTile(28, this, x, y, Texture.tile_modulable_4_F));
				} else if(pixels[x + y * width] == 0xFF848D90){
					tiles.add(new ModulableTile(29, this, x, y, Texture.tile_modulable_4_D));
				} else if(pixels[x + y * width] == 0xFFFFFFFF){
					tiles.add(new BumperTile(this, x, y));
				} else if(pixels[x + y * width] == 0xFF782029){
					tiles.add(new SurpriseTile(this, x, y, false));
				} else if(pixels[x + y * width] == 0xFF1B2037){
					tiles.add(new InvisibleTile(this, x, y));
				} else if(pixels[x + y * width] == 0xFF102030){
					addEntity(new Bomber(this, x*Tile.SIZE, y*Tile.SIZE));
				} else if(pixels[x + y * width] == 0xFF511922){
					tiles.add(new HidedSpikesTile(this, x, y, 0, true));
				} else if(pixels[x + y * width] == 0xFF34131A){
					tiles.add(new HidedSpikesTile(this, x, y, 1, true));
				} else if(pixels[x + y * width] == 0xFF076146){
					tiles.add(new SurpriseTile(this, x, y, true));
				}
			}
		}

		loaded = true;

	}

	public void translateView(float xa, float ya){
		if(xa > 0) xa = 0;
		if(ya > 0) ya = 0;
		if(this != null && xa < -this.getWidth() * 16 + 16 + Display.getWidth() / Component.scale) xa = -this.getWidth() * 16 + 16 + Display.getWidth() / Component.scale;
		if(this != null && ya < -this.getHeight() * 16 + 16 + Display.getHeight() / Component.scale) ya = -this.getHeight() * 16 + 16 + Display.getHeight() / Component.scale;		
		Component.xScroll = xa;
		Component.yScroll = ya;
	}

	public Tile getTileAt(int x, int y){
		for(Tile t : tiles){
			if(t.getX() == x && t.getY() == y){
				return t;
			}
		}
		return null;
	}

	public void setTileAt(int x, int y, Tile tile){
		Tile underTile = getTileAt(x, y);
		if(underTile != null) tiles.remove(underTile);
		if(tile != null) tiles.add(tile);
	}

	public boolean isLoaded(){
		return loaded;
	}

	public int getWidth(){
		return width;
	}

	public int getHeight(){
		return height;
	}

	public List<Entity> getEntities(){
		return entities;
	}

	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	public void removeEntity(Entity entity) {
		entities.remove(entity);
	}


}
