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
			if(element.isVisible())
			element.render();
		}
	}
	
	public static void updateAll(){
		List<RenderableElement> toUpdate = new ArrayList<>(renderableElements);
		for(RenderableElement element : toUpdate){
			if(element.isVisible())
			element.update();
		}
	}
	
	public abstract void render();
	
	public abstract void update();
	
	public void destroy(){
		renderableElements.remove(this);
	}
	
	public RenderableElement show(){
		visible = true;
		return this;
	}
	
	public RenderableElement hide(){
		visible = false;
		return this;
	}
	
	public boolean isVisible(){
		return visible;
	}
	
}
