package de.ludumDare_DT.ludumDare_DT_2015;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import de.ludumDare_DT.ludumDare_DT_2015.game.Game;

public class Main {
	public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Kiss 'em all!?";
        config.width = 1024;
        config.height = 576;
        new LwjglApplication(new Game(), config);
    }
}
