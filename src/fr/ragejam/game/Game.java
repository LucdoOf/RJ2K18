package fr.ragejam.game;

import fr.ragejam.Component;

public class Game {
	
	public void translateView(float xa, float ya){
		if(xa > 0) xa = 0;
		if(ya > 0) ya = 0;
		//if(level != null && xa < -level.getWidth() * 16 + 16 + Display.getWidth() / Component.scale) xa = -level.getWidth() * 16 + 16 + Display.getWidth() / Component.scale;
		//if(level != null && ya < -level.getHeight() * 16 + 16 + Display.getHeight() / Component.scale) ya = -level.getHeight() * 16 + 16 + Display.getHeight() / Component.scale;		
		Component.xScroll = xa;
		Component.yScroll = ya;
	}

}
