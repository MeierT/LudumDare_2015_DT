package de.ludumDare_DT.ludumDare_DT_2015.input;

import com.badlogic.gdx.Gdx;

public class InputManager {
	
	public InputManager() {
		this.initialize();
	}
	
	private void initialize() {
		KeyboardMouseProcessor inputProcessor = new KeyboardMouseProcessor();
        Gdx.input.setInputProcessor(inputProcessor);
	}
}
