package fr.ragejam;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glViewport;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;

import fr.ragejam.game.MainMenu;
import fr.ragejam.graphics.RenderableElement;

public class Component {

	public boolean running = false;
	public static String title = "Jeu";
	public static int scale = 4;
	public static int width = Display.getDesktopDisplayMode().getWidth() / scale;
	public static int height = Display.getDesktopDisplayMode().getHeight() / scale;
	public static boolean tick = false;
	public static boolean render = false;
	public static float xScroll;
	public static float yScroll;
	
	DisplayMode mode = Display.getDesktopDisplayMode();

	public Component(){
		initDisplay();
		new MainMenu();
	}

	public static void main(String[] args){
		Component main = new Component();
		main.start();
	}

	public void update(){
		RenderableElement.updateAll();
	}

	public void render(){
		width = Display.getWidth() / scale;
		height = Display.getHeight() / scale;
		view2D(width, height);
		glClear(GL_COLOR_BUFFER_BIT);
		RenderableElement.renderAll();
	}

	public void start(){
		running = true;
		loop();
	}

	public void stop(){
		running = false;
	}

	public void exit(){
		Display.destroy();
		System.exit(0);
	}

	public void initDisplay(){
		try {
			Display.setDisplayMode(mode);
			Display.setResizable(false);
			Display.setFullscreen(false);
			Display.setTitle(title);
			Display.create();
			view2D(width, height);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}

	private void view2D(int width, int height){
		glViewport(0, 0, width * scale, height * scale);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		GLU.gluOrtho2D(0, width, height, 0);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();

		glEnable(GL_TEXTURE_2D);

		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}

	public void loop(){

		long timer = System.currentTimeMillis();

		long before = System.nanoTime();
		double elapsed = 0;
		double nanoSeconds = 1000000000.0 / 60.0;

		int ticks = 0;
		int frames = 0;

		while(running){
			if(Display.isCloseRequested()) stop();
			Display.update();

			tick = false;
			render = false;

			long now = System.nanoTime();
			elapsed = now - before;

			if(elapsed > nanoSeconds){
				before += nanoSeconds;
				tick = true;
				ticks++;
			} else {
				render = true;
				frames++;
			}

			if(tick) update();
			if(render) render();

			if(System.currentTimeMillis() - timer > 1000){
				timer+= 1000;
				Display.setTitle("ticks: " + ticks + " ,fps: " + frames);
				ticks = 0;
				frames = 0;
			}

		}
		exit();
	}


	public static float getMouseX() {
		return Mouse.getX() / Component.scale - xScroll;
	}

	public static float getMouseY() {
		return (Display.getHeight() - Mouse.getY()) / Component.scale - yScroll;
	}

	public static float getXScroll(){
		return xScroll;
	}

	public static float getYScroll(){
		return yScroll;
	}


}
