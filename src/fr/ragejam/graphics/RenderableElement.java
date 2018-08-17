package fr.ragejam.graphics;

import java.util.ArrayList;
import java.util.List;

public abstract class RenderableElement {

	public static List<RenderableElement> renderableElements = new ArrayList<>();
	
	private boolean visible = false;
	
	public RenderableElement(){
		renderableElements.add(this);
	}
	
	public static void renderAll(){
		List<RenderableElement> toRender = new ArrayList<>(renderableElements);
		for(RenderableElement element : toRender){
			element.render();
		}
	}
	
	public static void updateAll(){
		List<RenderableElement> toUpdate = new ArrayList<>(renderableElements);
		for(RenderableElement element : toUpdate){
			element.render();
		}
	}
	
	public abstract void render();
	
	public abstract void update();
	
	public void destroy(){
		renderableElements.remove(this);
	}
	
	public void show(){
		visible = true;
	}
	
	public void hide(){
		visible = false;
	}
	
	public boolean isVisible(){
		return visible;
	}
	
}
