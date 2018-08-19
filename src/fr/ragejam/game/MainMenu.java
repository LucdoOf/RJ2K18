package fr.ragejam.game;

import fr.ragejam.Component;
import fr.ragejam.graphics.menus.Button;
import fr.ragejam.utils.Callback;

public class MainMenu {
	
	private float buttonsWidth = 150;
	private float buttonsHeight = 40;
	private float space = 10;
	private Button playButton, quitButton;
	
	public MainMenu(){
		playButton = (Button) new Button(Component.width/2-buttonsWidth/2, Component.height/2-buttonsHeight/2 - buttonsHeight - space/2, buttonsWidth, buttonsHeight, "Play", new Callback<Button>() {
			@Override
			public void done(Button t) {
				playButton.destroy();
				quitButton.destroy();
				Component.setGame(new Game("level_test2"));
				Component.setGame(null);
				Component.setGame(new Game("level_test2"));
			}
		}, 14).show();
		quitButton = (Button) new Button(Component.width/2-buttonsWidth/2, Component.height/2-buttonsHeight/2 + space/2, buttonsWidth, buttonsHeight, "Exit", new Callback<Button>() {
			@Override
			public void done(Button t) {
				System.exit(1);
			}
		}, 14).show();
	}

}
