package fr.ragejam.game;

import fr.ragejam.Component;
import fr.ragejam.graphics.menus.Button;

public class MainMenu {
	
	private float playButtonWidth = 150;
	private float playButtonHeight = 40;
	
	public MainMenu(){
		new Button(Component.width/2-playButtonWidth/2, Component.height/2-playButtonHeight/2, playButtonWidth, playButtonHeight, "Jouer", null, 14).show();
	}

}
