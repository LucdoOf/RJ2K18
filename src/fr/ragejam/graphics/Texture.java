package fr.ragejam.graphics;

import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_RGBA8;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_S;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_T;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameteri;
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;

public class Texture {
	
	public static final Texture button_classic = loadTexture("res/BoutonMenu.png");
	public static final Texture font_classic = loadTexture("res/font.png");
	public static final Texture tile_modulable_A = loadTexture("res/Block_A.png");
	public static final Texture tile_modulable_B = loadTexture("res/Block_B.png");
	public static final Texture tile_modulable_C = loadTexture("res/Block_C.png");
	public static final Texture tile_modulable_D = loadTexture("res/Block_D.png");
	public static final Texture tile_modulable_E = loadTexture("res/Block_E.png");
	public static final Texture tile_modulable_F = loadTexture("res/Block_F.png");
	public static final Texture tile_modulable_G = loadTexture("res/Block_G.png");
	public static final Texture player_classic = loadTexture("res/OrangeMan.png");
	public static final Texture background_g = loadTexture("res/BG_A_BIG.png");
	public static final Texture gradiant = loadTexture("res/Gradient.png");
	public static final Texture tile_spike_small = loadTexture("res/Spikes_Small.png");
	public static final Texture tile_spike_big = loadTexture("res/Spikes_Big.png");
	public static final Texture menu_death = loadTexture("res/Nice_Try.png");
	public static final Texture tile_modulable_2_A = loadTexture("res/Block2_A.png");
	public static final Texture tile_modulable_2_B = loadTexture("res/Block2_B.png");
	public static final Texture tile_modulable_2_C = loadTexture("res/Block2_C.png");
	public static final Texture tile_modulable_2_D = loadTexture("res/Block2_D.png");
	public static final Texture tile_modulable_2_E = loadTexture("res/Block2_E.png");
	public static final Texture tile_modulable_2_F = loadTexture("res/Block2_F.png");
	public static final Texture tile_modulable_2_G = loadTexture("res/Block2_G.png");
	public static final Texture tile_modulable_3_A = loadTexture("res/Block3_A.png");
	public static final Texture tile_modulable_3_B = loadTexture("res/Block3_B.png");
	public static final Texture tile_modulable_3_C = loadTexture("res/Block3_C.png");
	public static final Texture tile_modulable_3_D = loadTexture("res/Block3_D.png");
	public static final Texture tile_modulable_3_E = loadTexture("res/Block3_E.png");
	public static final Texture tile_modulable_3_F = loadTexture("res/Block3_F.png");
	public static final Texture tile_modulable_3_G = loadTexture("res/Block3_G.png");
	public static final Texture tile_modulable_4_A = loadTexture("res/Block4_A.png");
	public static final Texture tile_modulable_4_B = loadTexture("res/Block4_B.png");
	public static final Texture tile_modulable_4_C = loadTexture("res/Block4_C.png");
	public static final Texture tile_modulable_4_D = loadTexture("res/Block4_D.png");
	public static final Texture tile_modulable_4_E = loadTexture("res/Block4_E.png");
	public static final Texture tile_modulable_4_F = loadTexture("res/Block4_F.png");
	public static final Texture tile_modulable_4_G = loadTexture("res/Block4_G.png");
	public static final Texture bomber_classic = loadTexture("res/Green_Monster.png");
	public static final Texture tile_bumper = loadTexture("res/Bumper.png");
	public static final Texture tile_surprise = loadTexture("res/Block_Invisible.png");
	public static final Texture tile_surprise_bad = loadTexture("res/Block_Invisible_Bad.png");
	
	private int width, height;
	private int id;
	private String path;
	
	public Texture(int width, int height, int id){
		this.width = width;
		this.height = height;
		this.id = id;
	}
	
	public static Texture loadTexture(String path){
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int w = image.getWidth();
		int h = image.getHeight();
		
		int[] pixels = new int[w * h];
		image.getRGB(0, 0, w, h, pixels, 0, w);
		
		ByteBuffer buffer = BufferUtils.createByteBuffer(w * h * 4); //4 pour 4 couleurs
		
		
		for(int y = 0; y < h; y++){
			for(int x = 0; x < w; x++){
				int i = pixels[x + y * w];
				buffer.put((byte) ((i >> 16) & 0xFF));
				buffer.put((byte) ((i >> 8) & 0xFF));
				buffer.put((byte) ((i) & 0xFF));
				buffer.put((byte) ((i >> 24) & 0xFF));
			}
		}
		
		buffer.flip();
		
		int id = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, id);
		
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
		
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, w, h, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
		
		
		Texture t = new Texture(w, h, id);
		t.path = path;
		return t;
	}
	
	public Texture genSimilar(){
		return loadTexture(path);
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void bind(){
		glBindTexture(GL_TEXTURE_2D, id);
	}
	
	public void unbind(){
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	
}
