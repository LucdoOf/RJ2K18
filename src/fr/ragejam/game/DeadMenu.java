package fr.ragejam.game;

import org.lwjgl.input.Keyboard;

import fr.ragejam.Component;
import fr.ragejam.graphics.RenderableElement;
import fr.ragejam.graphics.Renderer;
import fr.ragejam.graphics.Texture;

public class DeadMenu extends RenderableElement {

	private long deadMenuTime = System.currentTimeMillis();
	private long deadMenuFadeInTime = 200;
	private boolean hasPressed = false;

	@Override
	public void render() {
		if(System.currentTimeMillis() - deadMenuTime > deadMenuFadeInTime){
			Texture.menu_death.bind();
			Renderer.quadData(-Component.getXScroll(), -Component.getYScroll(), Component.width, Component.height);
			Texture.menu_death.unbind();
		} else {
			float[] color = new float[]{1, 1, 1, (float)1/deadMenuFadeInTime*(System.currentTimeMillis()-deadMenuTime)};
			Texture.menu_death.bind();
			Renderer.quadData(-Component.getXScroll(), -Component.getYScroll(), Component.width, Component.height, color);
			Texture.menu_death.unbind();
		}
	}

	@Override
	public void update() {
		if(Keyboard.isKeyDown(Keyboard.KEY_R) && !hasPressed){
			System.out.println("test");
			hasPressed = true;
			Component.setGame(new Game("level_test2"));
			destroy();
		}
	}

}
