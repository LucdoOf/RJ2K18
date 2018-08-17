package fr.ragejam.graphics.menus;

import org.lwjgl.input.Mouse;

import fr.ragejam.Component;
import fr.ragejam.graphics.Font;
import fr.ragejam.graphics.RenderableElement;
import fr.ragejam.graphics.Renderer;
import fr.ragejam.graphics.Texture;
import fr.ragejam.utils.Callback;

public class Button extends RenderableElement {
	
	private float x, y, width, height;
	private String text;
	private Callback<Button> callback;
	private boolean highlighted = false;
	private int fontScale;
	
	public Button(float x, float y, float width, float height, String text, Callback<Button> callback, int fontScale){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		this.fontScale = fontScale;
	}
	
	public void render(){
		Texture.button_classic.bind();
		if(isHighlighted()) Renderer.quadData(x, y, width, height, 0, 0, 1, 2);
		else Renderer.quadData(x, y, width, height, 0, 1, 1, 2);
		Texture.button_classic.unbind();
		
		float textSize = Font.getTextSize(text, fontScale);
		Font.render(text, x + width/2 - textSize/2, y + height/2 - fontScale/2, fontScale, new float[]{1, 1, 1, 1});
	}
	
	public void update(){
		if(Component.getMouseX() > x && Component.getMouseX() < x+width && Component.getMouseY() > y && Component.getMouseY() < y+height){
			highlighted = true;
			if(Mouse.next()){
				if(Mouse.getEventButtonState() == false && Mouse.getEventButton() == 0){
					callback.done(this);
				}
			}
		} else highlighted = false;
	}
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
	public int getFontScale(){
		return fontScale;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public String getText() {
		return text;
	}
	
	public Callback<Button> getCallback(){
		return callback;
	}
	
	public boolean isHighlighted(){
		return highlighted;
	}
	
}
